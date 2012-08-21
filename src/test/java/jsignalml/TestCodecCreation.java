package jsignalml;

import java.util.Collection;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;

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

	@DataProvider
	public static Object[][] valid() {
		return valid;
	}

	public static final Object[][] valid = new Object[][] {
		{"specs/EASYS.xml"},
		{"specs/M4D.xml"},
	};
}
