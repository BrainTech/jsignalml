package jsignalml;

import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.ByteArrayOutputStream;

import jsignalml.compiler.CompiledClass;
import jsignalml.compiler.MemoryWriter;
import jsignalml.codec.Signalml;

import org.testng.annotations.Test;
import org.testng.annotations.Factory;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
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
			assertTrue(new File(specfile).exists(), specfile);
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

		JavaClassGen java_class_gen = null;

		@Test(dependsOnMethods={"test_ASTTypeVisitor"})
		public void test_JavaClassGen()
			throws Exception
		{
			this.java_class_gen =
				new JavaClassGen(this.typer.getTypeResolver());
			this.codec.accept(this.java_class_gen, null);
		}

		String class_name = null;
		CharSequence class_code = null;

		@Test(dependsOnMethods={"test_JavaClassGen"})
		public void test_MemoryWriter()
			throws Exception
		{
			assert this.java_class_gen != null;
			final MemoryWriter writer = new MemoryWriter();
			java_class_gen.write(writer);
			final String name = java_class_gen.getClassName();
			final CharSequence code = writer.getCode();
			assertNotNull(name);
			assertNotNull(code);
			assertTrue(code.toString().contains("class " + name));
			this.class_name = name;
			this.class_code = code;
		}

		File dir_with_code = null;

		@Test(dependsOnMethods={"test_JavaClassGen"})
		public void test_writing_to_disk()
			throws Exception
		{
			assert this.java_class_gen != null;
			File dir = helpers.temporaryDir("jsignalml-generated");
			this.java_class_gen.write(dir);
			assertTrue(dir.list(java_files).length > 0);
			assertTrue(new File(dir, this.class_name + ".java").exists());
			this.dir_with_code = dir;
		}

		@Test(dependsOnMethods={"test_MemoryWriter"})
		public void test_compilation_from_memory()
			throws Exception
		{
			assert this.class_name != null;
			assert this.class_code != null;
			assert this.class_code.length() > 0;
			CompiledClass klass = new CompiledClass(this.class_name,
								this.class_code);
			Object instance = klass.newInstance();
			helpers.assertInstanceOf(instance, jsignalml.codec.Signalml.class);
			assertEquals(instance.getClass().getSimpleName(), this.class_name);
		}

		Signalml signalml;

		@Test(dependsOnMethods={"test_writing_to_disk"})
		public void test_compilation_from_disk()
			throws Exception
		{
			assert this.dir_with_code != null;
			File file = new File(this.dir_with_code,
					     this.class_name + ".java");

			Object instance = CompiledClass.fromFile(file).newInstance();
			assertEquals(instance.getClass().getSimpleName(),
				     this.class_name);
			helpers.assertInstanceOf(instance, Signalml.class);
			this.signalml = (Signalml) instance;
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
	public static String[] specfiles() {
		String[] names = specdir.list(xml_files);
		for(int i=0; i<names.length; i++)
			names[i] = specdir + helpers.FILE_SEP + names[i];
		return names;
	}

	public static final FilenameFilter java_files =
		new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		};
}
