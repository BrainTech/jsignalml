package jsignalml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileReader;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Collection;

import java.lang.reflect.Method;

import jsignalml.compiler.CompiledClass;
import jsignalml.compiler.MemoryWriter;
import jsignalml.codec.Signalml;

import org.testng.annotations.Test;
import org.testng.annotations.Factory;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestCodecCreation {

	public class TestOneCodec {
		public final File specfile;
		TestOneCodec(File specfile) {
			this.specfile = specfile;
		}

		public String toString() {
			return getClass().getSimpleName() + ": " + specfile;
		}

		@Test
		public void test_filename_valid() {
			assertTrue(specfile.exists(), specfile.toString());
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
			doc.getElement_re("/signalml/header/format_id");
			doc.getElement_re("/signalml/header/format_id/name");
		}

		ASTNode codec = null;

		@Test(dependsOnMethods={"test_xml"})
		public void test_make_codec()
			throws Exception
		{
			final CodecParser parser = new CodecParser(specfile);
			final ASTNode codec = parser.codec;
			this.codec = codec;
		}

		@Test(dependsOnMethods={"test_make_codec"})
		public void test_NameCheck()
			throws Exception
		{
			final NameCheck check = new NameCheck();
			codec.accept(check, null);
		}

		ASTTypeVisitor typer = null;

		@Test(dependsOnMethods={"test_make_codec"})
		public void test_ASTTypeVisitor()
			throws Exception
		{
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
			final String name = java_class_gen.getClassName();
			final CharSequence code = java_class_gen.getSourceCode();
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
			assertTrue(dir.list(helpers.java_file_filter).length > 0);
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

		@Test(dataProvider="format_fields",
		      dependsOnMethods={"test_compilation_from_disk"})
		public void test_codec_implements_header_field(String method_name)
			throws Exception
		{
			assert this.signalml != null;
			Method m = this.signalml.getClass().getMethod(method_name);
			Object value = m.invoke(this.signalml);
			helpers.assertInstanceOf(value, String.class);
			String str = (String) value;
			assertTrue(str.length() > 0);
		}

		@DataProvider
		public Object[][] format_fields() {
			return new Object[][] {
				{"getFormatID"},
				{"getFormatDescription"},
				{"getFormatName"},
				{"getFormatProvider"},
				{"getFormatVersion"},
				{"getCodecProvider"},
				{"getCodecVersion"},
			};
		}
	}

	@Factory
	public Object[] factory() {
		List<Object> result = util.newArrayList();
		for(File filename: specfiles())
			result.add(new TestOneCodec(filename));
		return result.toArray();
	}

	public static final File specdir = new File("specs/");
	public static File[] specfiles() {
		String[] names = specdir.list(helpers.xml_file_filter);
		File[] files = new File[names.length];
		for(int i=0; i<files.length; i++)
			files[i] = new File(specdir, names[i]);
		return files;
	}
}
