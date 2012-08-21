package jsignalml;

import java.util.Collection;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FilenameFilter;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class TestCodecCreation {

	@Test(dataProvider="valid")
	public void test_filename_valid(final String specfile) {
		assertTrue(new File(specfile).exists());
	}

	@Test(dataProvider="valid", dependsOnMethods={"test_filename_valid"})
	public void test_xml(final String specfile)
		throws Exception
	{
		final InputStream stream = new FileInputStream(specfile);
		final XMLDocument doc = new XMLDocument(stream);

		// check required fields
		doc.getElement_re("/signalml");
		doc.getElement_re("/signalml/header");
	}

	@Test(dataProvider="valid", dependsOnMethods={"test_xml"})
	public void test_makeCodec(final String specfile)
		throws Exception
	{
		final ASTNode codec = CodecParser.makeCodec(new File(specfile));
	}

	@Test(dataProvider="valid", dependsOnMethods={"test_makeCodec"})
	public void test_ASTTypeVisitor(final String specfile)
		throws Exception
	{
		final ASTNode codec = CodecParser.makeCodec(new File(specfile));
		final ASTTypeVisitor typer = new ASTTypeVisitor();
		codec.accept(typer, null);
	}

	@Test(dataProvider="valid", dependsOnMethods={"test_makeCodec"})
	public void test_NameCheck(final String specfile)
		throws Exception
	{
		final ASTNode codec = CodecParser.makeCodec(new File(specfile));
		final NameCheck check = new NameCheck();
		codec.accept(check, null);
	}


	public static final File specdir = new File("specs/");
	public static final FilenameFilter xml_files =
		new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		};
	public static final String FILE_SEP = System.getProperty("file.separator");

	@DataProvider
	public static Object[][] valid() {
		String[] names = specdir.list(xml_files);
		Object[][] data = new Object[names.length][];
		for(int i=0; i<names.length; i++)
			data[i] = new Object[] {
				specdir + FILE_SEP + names[i]
			};
		return data;
	}
}
