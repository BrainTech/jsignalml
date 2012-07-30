import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import jsignalml.codec.Signalml;

/**
 * @author Grzegorz Stadnik
 *
 */
public class TestCodecs {

	private static final String ftCommon = "test/common.properties";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCodecs t = new TestCodecs();
		t.test();
	}

	private void test() {
		CheckCodec checkCodec = null; //place for codec test object
		Properties properties = new Properties(); //place for properties from the configuration of the codec test
		String fileName = null; //name of the file currently read -- taken separately because of possible exception occurs
		try {
			fileName = ftCommon;
			properties.load(new FileInputStream(fileName));
			final String  pathToDataFiles = properties.getProperty("pathToDataFiles");
			final String  pathToTestFiles = properties.getProperty("pathToTestFiles");
			final String  listOfTestFiles = properties.getProperty("listOfTestFiles");
			final String[] arrOfTestFiles = listOfTestFiles.split("[,\t ]+");
			for (int k = 0; k < arrOfTestFiles.length; k ++) {
				properties.clear();
				String ftFile = arrOfTestFiles[k];
				fileName = pathToTestFiles + "/" + ftFile;
				try {
					properties.load(new FileInputStream(fileName));
					final String  codecClassName = properties.getProperty("codecClassName");
					final String  inDtaFileName  = pathToDataFiles + "/" + properties.getProperty("inDtaFileName");
					final String  outHdrFileName = pathToDataFiles + "/" + properties.getProperty("outHdrFileName");
					final String  outDtaFileName = pathToDataFiles + "/" + properties.getProperty("outDtaFileName");
					final boolean useContextDumper                 = Boolean.parseBoolean(properties.getProperty("useContextDumper",                 "false"));
					final boolean usePreCheckingOfTheDataFromCodec = Boolean.parseBoolean(properties.getProperty("usePreCheckingOfTheDataFromCodec", "false"));
					final boolean exitOnNrsOfChannelsError         = Boolean.parseBoolean(properties.getProperty("exitOnNrsOfChannelsError",         "false"));
					final float   verificationMultiplyFactor  = Float.parseFloat(properties.getProperty("verificationMultiplyFactor",  "1.0f"));
					final float   testPrecisionRelForSample   = Float.parseFloat(properties.getProperty("testPrecisionRelForSample",   String.valueOf(CheckCodec.precisionRelFloat)));
					final float   testPrecisionDeltaForSample = Float.parseFloat(properties.getProperty("testPrecisionDeltaForSample", String.valueOf(CheckCodec.precisionDeltaFloat)));
					final int     testPrecisionUlpForSample   = Integer.parseInt(properties.getProperty("testPrecisionUlpForSample",   String.valueOf(CheckCodec.precisionUlpFloat)));
					final double  testPrecisionRelForSamplingFrequency   = Double.parseDouble(properties.getProperty("testPrecisionRelForSamplingFrequency",   String.valueOf(CheckCodec.precisionRelDouble)));
					final double  testPrecisionDeltaForSamplingFrequency = Double.parseDouble(properties.getProperty("testPrecisionDeltaForSamplingFrequency", String.valueOf(CheckCodec.precisionDeltaDouble)));
					final long    testPrecisionUlpForSamplingFrequency   =   Integer.parseInt(properties.getProperty("testPrecisionUlpForSamplingFrequency",   String.valueOf(CheckCodec.precisionUlpDouble)));
					String startMessage = "# Testing codec: " + codecClassName + " #\r\n";
					String startMassage = "###############################################".substring(0, startMessage.length() - 2) + "\r\n";
					System.out.print(startMassage + startMessage + startMassage);
					try {
						Signalml reader = (Signalml) Class.forName(codecClassName).newInstance();
						checkCodec = new CheckCodec(reader);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						continue;
					} catch (InstantiationException e) {
						e.printStackTrace();
						continue;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						continue;
					}
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
						testPrecisionRelForSample, testPrecisionRelForSamplingFrequency,
						testPrecisionDeltaForSample, testPrecisionDeltaForSamplingFrequency,
						testPrecisionUlpForSample, testPrecisionUlpForSamplingFrequency);
					assertEquals(verificationStatus, true);
					startMessage = "# SUCCESS #\r\n";
					startMassage = "###############################################".substring(0, startMessage.length() - 2) + "\r\n";
					System.out.print(startMassage + startMessage + startMassage);
				} catch (FileNotFoundException e) {
					CheckCodec.logFileOperationException(e, fileName, "Cannot find a", "Please configure " + ftCommon + " file properly.");
				} catch (IOException e) {
					CheckCodec.logFileOperationException(e, fileName, "I/O error during reading the", "Please verify that it is a proper test file.");
				} finally {
					checkCodec.close();
				}
			}
		} catch (FileNotFoundException e) {
			CheckCodec.logFileOperationException(e, fileName, "Cannot find a", "Check directories of the running program.");
		} catch (IOException e) {
			CheckCodec.logFileOperationException(e, fileName, "I/O error during reading the", "Please verify that it is a proper configurational file.");
		} finally {
			checkCodec.close();
		}
	}

}
