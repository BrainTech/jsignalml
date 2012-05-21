
/**
 *
 */

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

/**
 * @author Grzegorz Stadnik
 *
 */
public class TestEASYS {

	public static void main(java.lang.String[] args) {
		TestEASYS t = new TestEASYS();
		t.test();
	}

	@Test public void test() {
		CheckCodec checkCodec = null; //place for codec test object
		Properties properties = new Properties(); //place for properties from the configuration of the codec test
		String fileName = null; //name of the file currently read -- taken separately because of possible exception occurs
		try {
			fileName = "src/test/resources/" + this.getClass().getName() + ".properties";
			properties.load(new FileInputStream(fileName));
			final String inDtaFileName = properties.getProperty("inDtaFileName");
			final String outHdrFileName = properties.getProperty("outHdrFileName");
			final String outDtaFileName = properties.getProperty("outDtaFileName");
			final boolean useContextDumper = Boolean.parseBoolean(properties.getProperty("useContextDumper", "false"));
			final boolean usePreCheckingOfTheDataFromCodec = Boolean.parseBoolean(properties.getProperty("usePreCheckingOfTheDataFromCodec", "false"));
			final boolean exitOnNrsOfChannelsError = Boolean.parseBoolean(properties.getProperty("exitOnNrsOfChannelsError", "false"));;
			final int testPrecisionForSample = CheckCodec.precisionFloat;
			final int testPrecisionForSamplingFrequency = CheckCodec.precisionDouble;
			final float testDeltaForSample = Float.parseFloat(properties.getProperty("testDeltaForSample", String.valueOf(CheckCodec.precisionFloat)));
			final double testDeltaForSamplingFrequency = Double.parseDouble(properties.getProperty("testDeltaForSamplingFrequency", String.valueOf(CheckCodec.precisionDouble)));
			float verificationMultiplyFactor = Float.parseFloat(properties.getProperty("verificationMultiplyFactor", "1.0f"));
			checkCodec = new CheckCodec(new EASYS());
			//giving input to codec and getting result from it
			fileName = inDtaFileName;
			boolean inDtaStatus = checkCodec.getInDta(inDtaFileName, useContextDumper, usePreCheckingOfTheDataFromCodec);
			assertEquals(inDtaStatus, true);
			//read output header file (.hdr) and output body file (.float)
			fileName = outHdrFileName + " (or) " + outDtaFileName;
			boolean outDtaStatus = checkCodec.getOutDta(outHdrFileName, outDtaFileName, exitOnNrsOfChannelsError);
			assertEquals(outDtaStatus, true);
			//then compare everything (outBody with outHdr and with in)
			fileName = "(unknown)";
			boolean verificationStatus = checkCodec.checkChannels(verificationMultiplyFactor,
				testPrecisionForSample, testPrecisionForSamplingFrequency,
				testDeltaForSample, testDeltaForSamplingFrequency);
			assertEquals(verificationStatus, true);
		} catch (FileNotFoundException e) {
			CheckCodec.logFileOperationException(e, fileName, "Cannot find a", "Check command-line arguments to the running program.");
		} catch (IOException e) {
			CheckCodec.logFileOperationException(e, fileName, "I/O error during reading the", "Please verify that it is a proper file.");
		} finally {
			checkCodec.close();
		}
	}

}
