package jsignalml;

import static org.testng.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

public class PatternTest {


	@Test
	public void test_float() {

		String pattern = "[^:]*:([0-9]+\\.[0-9]*)";
		String number = read("this is some666 test:1234.33e with digits ", pattern, 1);
		assertTrue("1234.33".equals(number));
	}

	@Test
	public void test_float_1() {

		String pattern = "[^:]*sampling.frequency:([0-9]+.[0-9]*)";
		String number = read("sampling frequency:888.777", pattern, 1);
		assertTrue("888.777".equals(number));
	}

	@Test
	public void test_string_m4d_data_filename() {
		String line = "MSI.FileDescriptor: kbh215p;FWLegasyd;05.04.03 11:41;1;Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a";

		String pattern = "MSI.FileDescriptor: (.+;){4}([a-zA-Z0-9,._-]+)";

		String expectedFilename = "Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a";

		String filename = read(line, pattern, 2);
		assertTrue(expectedFilename.equals(filename));

	}

	@Test
	public void test_string_channels() {
		String line = "MSI.MegChanNames: A68,A58,A148,A83,A91";

		String pattern = "MSI.MegChanNames: ([a-zA-Z0-9]+,){0}([A-Za-z0-9]+)";

		String number = read(line, pattern, 2);
		assertTrue("A68".equals(number));

		pattern = "MSI.MegChanNames: ([a-zA-Z0-9]+,){1}([A-Za-z0-9]+)";
		number = read(line, pattern, 2);
		assertTrue("A58".equals(number));

		pattern = "MSI.MegChanNames: ([a-zA-Z0-9]+,){2}([A-Za-z0-9]+)";
		number = read(line, pattern, 2);
		assertTrue("A148".equals(number));

		pattern = "MSI.MegChanNames: ([a-zA-Z0-9]+,){3}([A-Za-z0-9]+)";
		number = read(line, pattern, 2);
		assertTrue("A83".equals(number));

		pattern = "MSI.MegChanNames: ([a-zA-Z0-9]+,){4}([A-Za-z0-9]+)";
		number = read(line, pattern, 2);
		assertTrue("A91".equals(number));
	}

	public void print(String exp, String recv) {
		System.out.println("\nExpected '" + exp + "'");
		System.out.println("Received '" + recv + "'");

	}

	 @Test
	public void test_int() {

		String pattern = ":([0-9]+)";
		String number = read("here is the channel number:666 and some text", pattern, 1);
		assertTrue("666".equals(number));
	}

	public String read(String text, String pattern, int group) {

		String found = null;
		Pattern pat = Pattern.compile(pattern);
		Matcher matcher = pat.matcher(text);

		if (matcher.find()) {
			found = matcher.group(group);
			return found;
		}

		return found;
	}

}
