package jsignalml;

import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FilenameFilter;

import org.testng.annotations.Test;
import org.testng.annotations.Factory;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class TestCodecCreation {

	public class TestOneCodec {
		public final String specfile;
		TestOneCodec(String specfile) {
			this.specfile = specfile;
		}

		public String toString() {
			return getClass().getSimpleName() + ": " + specfile;
		}

		@Test
		public void test_filename_valid() {
			assertTrue(new File(specfile).exists());
		}

		@Test(dependsOnMethods={"test_filename_valid"})
			public void test_xml()
			throws Exception
		{
			final InputStream stream = new FileInputStream(specfile);
			final XMLDocument doc = new XMLDocument(stream);

			// check required fields
			doc.getElement_re("/signalml");
			doc.getElement_re("/signalml/header");
		}

		@Test(dependsOnMethods={"test_xml"})
		public void test_makeCodec()
			throws Exception
		{
			final ASTNode codec = CodecParser.makeCodec(new File(specfile));
		}

		@Test(dependsOnMethods={"test_makeCodec"})
		public void test_NameCheck()
			throws Exception
		{
			final ASTNode codec = CodecParser.makeCodec(new File(specfile));
			final NameCheck check = new NameCheck();
			codec.accept(check, null);
		}

		ASTNode codec = null;
		ASTTypeVisitor typer = null;

		@Test(dependsOnMethods={"test_makeCodec"})
		public void test_ASTTypeVisitor()
			throws Exception
		{
			this.codec = CodecParser.makeCodec(new File(specfile));
			this.typer  = new ASTTypeVisitor();
			this.codec.accept(this.typer, null);
		}

		@Test(dependsOnMethods={"test_ASTTypeVisitor"})
		public void test_JavaClassGen()
			throws Exception
		{
			final JavaClassGen gen =
				new JavaClassGen(this.typer.getTypeResolver());
			this.codec.accept(gen, null);
			gen.write(System.out);
		}
	}

	@Factory
	public Object[] factory() {
		List<Object> result = util.newArrayList();
		for(String filename: specfiles())
			result.add(new TestOneCodec(filename));
		return result.toArray(new TestOneCodec[] {});
	}

	public static final File specdir = new File("specs/");
	public static final FilenameFilter xml_files =
		new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		};
	public static final String FILE_SEP = System.getProperty("file.separator");

	public static String[] specfiles() {
		String[] names = specdir.list(xml_files);
		for(int i=0; i<names.length; i++)
			names[i] = specdir + FILE_SEP + names[i];
		return names;
	}
}
