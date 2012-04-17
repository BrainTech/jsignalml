import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import jsignalml.Channel;
import jsignalml.ChannelSet;
import jsignalml.ContextDumper;
import jsignalml.codec.Signalml;

import org.apache.log4j.BasicConfigurator;
import org.junit.experimental.theories.Theory;


/**
 * @author Grzegorz Stadnik
 *
 */
public class TestCodec {

	private final static String outHdrNameNrOfSamples = "number_of_samples";
	private final static String outHdrNameNrOfChannels = "number_of_channels";
	private final static String outHdrNameSamplingFrequency = "sampling_frequency";
	private final static String outHdrNameLabelsOfChannels = "channel_labels";
	private final static String outHdrNameScaled = "scaled";
	private final static String outHdrNameScalingGain = "scaling_gain";
	private final static String outHdrNameScalingOffset = "scaling_offset";
	private final static String outHdrNameCalibrationGain = "calibration_gain";
	private final static String outHdrNameCalibrationOffset = "calibration_offset";
	private final static String outHdrNameCalibrationUnits = "calibration_units";
	
	private final static float  precisionFloat = 0.001f;
	private final static double precisionDouble = 0.000001d;
	
	private final static boolean LOG_DEBUG = false;
	private final static boolean LOG_INFO = true;
	private final static boolean LOG_WARNING = true;
	private final static boolean LOG_ERROR = true;
	private final static boolean LOG_FATAL = true;
	private final static boolean LOG_TEST = true;

	public static byte[] readFileUsingParts(String fileName, int bigBufSize, int smallBufSize)
	throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(fileName);
		FileChannel channel = fis.getChannel();
		long fSize = channel.size();
		byte[] buffer = new byte[(int)fSize];
		for (long offset = 0L; offset < fSize; offset += smallBufSize) {
			long toRead = Math.min(fSize - offset, smallBufSize);
			MappedByteBuffer mapBuffer = channel.map(FileChannel.MapMode.READ_ONLY, offset, toRead);
			long index = offset;
			while(mapBuffer.hasRemaining()) {
				long nGet = Math.min(mapBuffer.remaining(), toRead);
				mapBuffer.get(buffer, (int)index, (int)nGet);
				index += nGet;
			}
		}
		return buffer;
	}

	public static String[] readTextFileUsingParts(String fileName, String charset, String lineDelimiter, int bigBufSize, int smallBufSize)
	throws FileNotFoundException, IOException {
		byte[] buffer = TestCodec.readFileUsingParts(fileName, bigBufSize, smallBufSize);
		String bufStr = new String(buffer, charset);
		String[] lines = bufStr.split(lineDelimiter);
		return lines;
	}
	
	public static void main(java.lang.String[] args) {
		if (args.length < 4) {
			System.out.println("Using:\njava " + TestCodec.class.getSimpleName() + " input_header input_data output_header.hdr output_data.float");
			return;
		}
		String      codecName = args[0]; //String  inHdrFileName = args[0]; //codec to use (EASYS, M4D, ...)
		String  inDtaFileName = args[1]; //d:/grst/projects/UW/data/for_EASYS_codec/inb02.d                          //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.m4d
		String outHdrFileName = args[2]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.hdr   //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.hdr
		String outDtaFileName = args[3]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.float //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.float
		final boolean useContextDumper = false; //true; /////////////////////////////////////////////////////////////////////////////
		float verificationMultiplyFactor = 1.0f;
		if (args.length > 4) {
			try {
				verificationMultiplyFactor = Float.parseFloat(args[4]); //i.e. 20 for scaled, no or 1 for unscaled
			} catch (NumberFormatException nfe) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! Wrong 5th parameter: '" + args[4] + "'. Instead of those value pelase use value for verification multiply factor, i.e. 1.0f or 20 or other float numeric value.");
				return;
			}
		}
		File inDtaFile = null;
		FileInputStream outDtaFis = null;
		String fileName = null; //name of the file currently read -- taken separately because of possible exceptions occurings
		float testDeltaForSample = TestCodec.precisionFloat;
		double testDeltaForSamplingFrequency = TestCodec.precisionDouble;
		try {
			//giving input to codec and getting result from it
			fileName = inDtaFileName;
			Signalml reader = null; //TODO please make abstract method getSignalml() and superclass of the TestCodec will give us there codec EASYS or M4D or ...
			if (codecName.equalsIgnoreCase("EASYS")) {
				if (TestCodec.LOG_INFO) System.out.println("You selected EASYS codec. Trying to use it.");
				reader = new EASYS();
			} else if (codecName.equalsIgnoreCase("4D") || codecName.equalsIgnoreCase("M4D")) {
				if (TestCodec.LOG_INFO) System.out.println("You selected M4D codec. Trying to use it.");
				reader = new M4D();
				//if (TestCodec.LOG_FATAL) System.out.println("Fatal error! 4D aka M4D codec is not supported yet. It is under construction.");
				//return;
			} else if (codecName.equalsIgnoreCase("EDF")) {
				if (TestCodec.LOG_FATAL) System.out.println("Fatal error! EDF codec is not supported yet. It is under construction.");
				return;
			} else if (codecName.equalsIgnoreCase("GDF")) {
				if (TestCodec.LOG_FATAL) System.out.println("Fatal error! GDF codec is not supported yet. It is under construction.");
				return;
			} else if (codecName.equalsIgnoreCase("BDF")) {
				if (TestCodec.LOG_FATAL) System.out.println("Fatal error! BDF codec is not supported yet. It is under construction.");
				return;
			} else {
				if (TestCodec.LOG_ERROR) System.out.println("Error! Unknown codec name '" + codecName + "'. Instead of it pelase use one of the codec names from the list: EASYS, M4D, EDF, GDF, BDF.");
				return;
			}
			inDtaFile = new File(fileName);
			long inDtaFileLength = inDtaFile.length();
			long inDtaFileFragmentSize = Math.min(200240, inDtaFileLength);
			if (TestCodec.LOG_INFO) {
				System.out.println("Reading the input header/data file " + fileName);
				System.out.println("Length of the file is " + inDtaFileLength + " bytes");
		//		System.out.println("Reading fragment [0, " + inDtaFileFragmentSize + "] bytes from that file");
			}
			reader.open(inDtaFile);
			reader.createParams();
		//	reader.setRange(0, inDtaFileFragmentSize);
			reader.createChannels();
			if (useContextDumper) {
				System.out.print(ContextDumper.dump(reader));
			}
			int inDtaNumberOfChannelSets = reader.getNumberOfChannelSets(); //@Theory, do not know if this is fully supported
			if (TestCodec.LOG_INFO) {
				System.out.println("Number of channel sets: " + inDtaNumberOfChannelSets);
			}
			ChannelSet inDtaChannelSet = reader.get_set();
			int inDtaChannelSetNrOfChannels = inDtaChannelSet.getNumberOfChannels();
			if (TestCodec.LOG_INFO) {
				System.out.println("Number of channels: " + inDtaChannelSetNrOfChannels);
			}
			/*
			long inDtaChannelSetNrOfSamples = inDtaChannelSet.getNumberOfSamples(); //@Deprecated, please take that info from the channel
			double inDtaChannelSetSamplingFrequency = inDtaChannelSet.getSamplingFrequency(); //@Deprecated, please take that info from the channel
			for (int channelNr = 0; channelNr < inDtaChannelSetNrOfChannels; channelNr ++) {
				Channel inDtaChannelObject = inDtaChannelSet.getChannel(channelNr);
				long inDtaChannelObjectNumberOfSamplesPerChannel = inDtaChannelObject.getNumberOfSamples();
				String inDtaChannelObjectChannelName = inDtaChannelObject.getChannelName();
				double inDtaChannelObjectSamplingFrequency = inDtaChannelObject.getSamplingFrequency();
				for (long sampleNr = 0; sampleNr < inDtaChannelObjectNumberOfSamplesPerChannel; sampleNr ++) {
					//FloatBuffer inDtaChannelFloatBuffer = FloatBuffer.allocate(((int) inDtaChannelObjectNumberOfSamplesPerChannel));
					//inDtaChannelObject.getSamples(inDtaChannelFloatBuffer, sampleNr);
					//float[] buffer = inDtaChannelFloatBuffer.array();
					//float sampleUnitValue = inDtaChannelFloatBuffer.get((int) sampleNr);
				//	if (sampleNr >= 3284) {
				//		System.out.println("HERE WE ARE, NOW DEBUGGING THIS");
				//	}
					try {
						float sampleUnitValue = inDtaChannelObject.getSample(sampleNr);
						if (TestCodec.LOG_DEBUG) System.out.println(" " + sampleUnitValue); //System.out.format(" %.3f", sampleUnitValue);
					} catch (Throwable t) {
						System.out.println("Critical error during getting sample " + sampleNr + " of the channel " + channelNr);
						t.printStackTrace();
						return;
					}
					//this is only for test; far far below will be made sth similar to this above
				}
			}
			*/
			//TODO show on the screen (optionally -- for INFO log only) some informations from the codec (from above)
			//...
			//firstly read output header file (.hdr)
			fileName = outHdrFileName;
			Integer   outHdrNrOfChannels = null;
			Long      outHdrNrOfSamples = null;
			Long[]    outHdrNrsOfSamples = new Long[0];
			Double    outHdrSamplingFrequency = null;
			Double[]  outHdrSamplingFrequencies = new Double[0];
			String[]  outHdrLabelsOfChannels = null;
			Integer   outHdrScaled = null;
			Double    outHdrScalingGain = null;
			Double[]  outHdrScalingGains = new Double[0];
			Double    outHdrScalingOffset = null;
			Double[]  outHdrScalingOffsets = new Double[0];
			Double    outHdrCalibrationGain = null;
			Double[]  outHdrCalibrationGains = new Double[0];
			Double    outHdrCalibrationOffset = null;
			Double[]  outHdrCalibrationOffsets = new Double[0];
			String[]  outHdrCalibrationUnits = null;
			String    outHdrFileCharset = "UTF-8";
			String    outHdrFileLineDelimiter = "\n";
			final int outHdrFileBigBufSize = 262144;
			final int outHdrFileSmallBufSize = 16384;
			if (TestCodec.LOG_INFO) System.out.println("Reading the output header file " + fileName);
			String[] outHdrLines = TestCodec.readTextFileUsingParts(outHdrFileName, outHdrFileCharset, outHdrFileLineDelimiter, outHdrFileBigBufSize, outHdrFileSmallBufSize);
			if (TestCodec.LOG_INFO) System.out.println("File " + outHdrFileName + " has been read out successfully to the buffer.");
			if (TestCodec.LOG_INFO) System.out.println("Parsing content of the buffer...");
			for (int i = 0; i < outHdrLines.length; i++) {
				String line = outHdrLines[i].trim();
				if (line.startsWith("#")) {
					//this is comment => skip line
				} else if (line.trim().length() < 1) {
					//empty => skip line
				} else if (line.startsWith(TestCodec.outHdrNameNrOfSamples)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameNrOfSamples.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					String[] valueArrayStr = valueStr.replaceAll("[\t ]+", "").split(",");
					if (valueArrayStr.length < 2) {
						long valueLong = -1L;
						try {
							valueLong = Long.parseLong(valueStr, 10);
						} catch (NumberFormatException ex) {
							if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + outHdrFileName + ": line #" + i + " contains value which is not a long number, but " + TestCodec.outHdrNameNrOfSamples + " item wants it!");
							return;
						}
						if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameNrOfSamples + " item is (int)" + valueLong);
						outHdrNrOfSamples = new Long(valueLong);
					} else {
						ArrayList<Long> nrOfSamplesForAllChannels = new ArrayList<Long>();
						for (int j = 0; j < valueArrayStr.length; j++) {
							String nfOfSamplesPerChannelStr = valueArrayStr[j];
							long nrOfSamplesPerChannelLong = -1L;
							try {
								nrOfSamplesPerChannelLong = Long.parseLong(nfOfSamplesPerChannelStr, 10);
							} catch (NumberFormatException ex) {
								if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + outHdrFileName + ": line #" + i + " contains value which is not a long number: " + nfOfSamplesPerChannelStr);
								return;
							}
							nrOfSamplesForAllChannels.add(new Long(nrOfSamplesPerChannelLong));
						}
						outHdrNrsOfSamples = nrOfSamplesForAllChannels.toArray(outHdrNrsOfSamples);
					}
				} else if (line.startsWith(TestCodec.outHdrNameNrOfChannels)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameNrOfChannels.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					int valueInt = -1;
					try {
						valueInt = Integer.parseInt(valueStr, 10);
					} catch (NumberFormatException ex) {
						if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not an integer number, but " + TestCodec.outHdrNameNrOfChannels + " item wants it!");
						return;
					}
					if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameNrOfChannels + " item is (int)" + valueInt);
					outHdrNrOfChannels = new Integer(valueInt);
					
				} else if (line.startsWith(TestCodec.outHdrNameSamplingFrequency)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameSamplingFrequency.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					String[] valueArrayStr = valueStr.replaceAll("[\t ]+", "").split(",");
					if (valueArrayStr.length < 2) {
						double valueDouble = Double.NaN; //-1.0f;
						try {
							valueDouble = Double.parseDouble(valueStr);
						} catch (NumberFormatException ex) {
							if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number, but " + TestCodec.outHdrNameSamplingFrequency + " item wants it!");
							return;
						}
						if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameSamplingFrequency + " item is (double)" + valueDouble);
						outHdrSamplingFrequency = new Double(valueDouble);
					} else {
						ArrayList<Double> samplingFrequenciesForAllChannels = new ArrayList<Double>();
						for (int j = 0; j < valueArrayStr.length; j++) {
							String samplingFrequencyPerChannelStr = valueArrayStr[j];
							double samplingFrequencyPerChannelDouble = Double.NaN; //-1.0f;
							try {
								samplingFrequencyPerChannelDouble = Double.parseDouble(samplingFrequencyPerChannelStr);
							} catch (NumberFormatException ex) {
								if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number: " + samplingFrequencyPerChannelStr);
								return;
							}
							samplingFrequenciesForAllChannels.add(new Double(samplingFrequencyPerChannelDouble));
						}
						outHdrSamplingFrequencies = samplingFrequenciesForAllChannels.toArray(outHdrSamplingFrequencies);
					}
				} else if (line.startsWith(TestCodec.outHdrNameLabelsOfChannels)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameLabelsOfChannels.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					outHdrLabelsOfChannels = valueStr.replaceAll("[\t ]+", "").split(",");
				} else if (line.startsWith(TestCodec.outHdrNameScaled)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameScaled.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					int valueInt = -1;
					try {
						valueInt = Integer.parseInt(valueStr, 10);
					} catch (NumberFormatException ex) {
						if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not an integer number, but " + TestCodec.outHdrNameScaled + " item wants it!");
						return;
					}
					if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameScaled + " item is (int)" + valueInt);
					outHdrScaled = new Integer(valueInt);
				} else if (line.startsWith(TestCodec.outHdrNameScalingGain)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameScalingGain.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					String[] valueArrayStr = valueStr.replaceAll("[\t ]+", "").split(",");
					if (valueArrayStr.length < 2) {
						double valueDouble = Double.NaN; //-1.0f;
						try {
							valueDouble = Double.parseDouble(valueStr);
						} catch (NumberFormatException ex) {
							if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number, but " + TestCodec.outHdrNameScalingGain + " item wants it!");
							return;
						}
						if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameScalingGain + " item is (double)" + valueDouble);
						outHdrScalingGain = new Double(valueDouble);
					} else {
						ArrayList<Double> valuesForAllChannels = new ArrayList<Double>();
						for (int j = 0; j < valueArrayStr.length; j++) {
							String valuePerChannelStr = valueArrayStr[j];
							double valuePerChannelDouble = Double.NaN; //-1.0f;
							try {
								valuePerChannelDouble = Double.parseDouble(valuePerChannelStr);
							} catch (NumberFormatException ex) {
								if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number: " + valuePerChannelStr);
								return;
							}
							valuesForAllChannels.add(new Double(valuePerChannelDouble));
						}
						outHdrScalingGains = valuesForAllChannels.toArray(outHdrScalingGains);
					}
				} else if (line.startsWith(TestCodec.outHdrNameScalingOffset)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameScalingOffset.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					String[] valueArrayStr = valueStr.replaceAll("[\t ]+", "").split(",");
					if (valueArrayStr.length < 2) {
						double valueDouble = Double.NaN; //-1.0f;
						try {
							valueDouble = Double.parseDouble(valueStr);
						} catch (NumberFormatException ex) {
							if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number, but " + TestCodec.outHdrNameScalingOffset + " item wants it!");
							return;
						}
						if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameScalingOffset + " item is (double)" + valueDouble);
						outHdrScalingOffset = new Double(valueDouble);
					} else {
						ArrayList<Double> valuesForAllChannels = new ArrayList<Double>();
						for (int j = 0; j < valueArrayStr.length; j++) {
							String valuePerChannelStr = valueArrayStr[j];
							double valuePerChannelDouble = Double.NaN; //-1.0f;
							try {
								valuePerChannelDouble = Double.parseDouble(valuePerChannelStr);
							} catch (NumberFormatException ex) {
								if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number: " + valuePerChannelStr);
								return;
							}
							valuesForAllChannels.add(new Double(valuePerChannelDouble));
						}
						outHdrScalingOffsets = valuesForAllChannels.toArray(outHdrScalingOffsets);
					}
				} else if (line.startsWith(TestCodec.outHdrNameCalibrationGain)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameCalibrationGain.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					String[] valueArrayStr = valueStr.replaceAll("[\t ]+", "").split(",");
					if (valueArrayStr.length < 2) {
						double valueDouble = Double.NaN; //-1.0f;
						try {
							valueDouble = Double.parseDouble(valueStr);
						} catch (NumberFormatException ex) {
							if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number, but " + TestCodec.outHdrNameCalibrationGain + " item wants it!");
							return;
						}
						if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameCalibrationGain + " item is (double)" + valueDouble);
						outHdrCalibrationGain = new Double(valueDouble);
					} else {
						ArrayList<Double> valuesForAllChannels = new ArrayList<Double>();
						for (int j = 0; j < valueArrayStr.length; j++) {
							String valuePerChannelStr = valueArrayStr[j];
							double valuePerChannelDouble = Double.NaN; //-1.0f;
							try {
								valuePerChannelDouble = Double.parseDouble(valuePerChannelStr);
							} catch (NumberFormatException ex) {
								if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number: " + valuePerChannelStr);
								return;
							}
							valuesForAllChannels.add(new Double(valuePerChannelDouble));
						}
						outHdrCalibrationGains = valuesForAllChannels.toArray(outHdrCalibrationGains);
					}
				} else if (line.startsWith(TestCodec.outHdrNameCalibrationOffset)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameCalibrationOffset.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					String[] valueArrayStr = valueStr.replaceAll("[\t ]+", "").split(",");
					if (valueArrayStr.length < 2) {
						double valueDouble = Double.NaN; //-1.0f;
						try {
							valueDouble = Double.parseDouble(valueStr);
						} catch (NumberFormatException ex) {
							if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number, but " + TestCodec.outHdrNameCalibrationOffset + " item wants it!");
							return;
						}
						if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameCalibrationOffset + " item is (double)" + valueDouble);
						outHdrCalibrationOffset = new Double(valueDouble);
					} else {
						ArrayList<Double> valuesForAllChannels = new ArrayList<Double>();
						for (int j = 0; j < valueArrayStr.length; j++) {
							String valuePerChannelStr = valueArrayStr[j];
							double valuePerChannelDouble = Double.NaN; //-1.0f;
							try {
								valuePerChannelDouble = Double.parseDouble(valuePerChannelStr);
							} catch (NumberFormatException ex) {
								if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number: " + valuePerChannelStr);
								return;
							}
							valuesForAllChannels.add(new Double(valuePerChannelDouble));
						}
						outHdrCalibrationOffsets = valuesForAllChannels.toArray(outHdrCalibrationOffsets);
					}
				} else if (line.startsWith(TestCodec.outHdrNameCalibrationUnits)) {
					int valueIdx = line.indexOf("=", TestCodec.outHdrNameCalibrationUnits.length());
					if (valueIdx == -1) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #" + i + ": " + line);
						return;
					}
					String valueStr = line.substring(valueIdx + 1).trim();
					outHdrCalibrationUnits = valueStr.replaceAll("[\t ]+", "").split(",");
				} else {
					if (TestCodec.LOG_WARNING) System.out.println("Warning! Unknown line format: " + line);
				}
			}
			if (outHdrNrOfChannels == null) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! There was no specified value with number of channels in the output header file. Please add it there!");
				return;
			}
			if (outHdrLabelsOfChannels == null) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! There was no specified value with label of channel per every individual channel in the output header file. Please add it there!");
				return;
			}
			if (outHdrNrOfSamples == null && outHdrNrsOfSamples.length == 0) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! There was no specified value with number of sample units per all or per every individual channel in the output header file. Please add it there!");
				return;
			}
			if (outHdrNrsOfSamples.length > 0 && outHdrNrOfChannels.longValue() != outHdrNrsOfSamples.length) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! Number of channels specified in output header file was different in two places: " + outHdrNrOfChannels + " in line dedicated to it and " + outHdrNrsOfSamples.length + " in line with the list of the numbers of samples. Please correct it!");
				return;
			}
			if (outHdrSamplingFrequency == null && outHdrSamplingFrequencies.length == 0) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! There was no specified value with sampling frequency per all or per every individual channel in the output header file. Please add it there!");
				return;
			}
			if (outHdrSamplingFrequencies.length > 0 && outHdrNrOfChannels.longValue() != outHdrSamplingFrequencies.length) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! Number of channels specified in output header file was different in two places: " + outHdrNrOfChannels + " in line dedicated to it and " + outHdrSamplingFrequencies.length + " in line with the list of sampling freqiencies. Please correct it!");
				return;
			}
			if (outHdrScaled == null) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! There was no specified value determining if samples were scaled. Please add line scaled=0 or line scaled=1 to the .hdr file!");
				return;
			}
			//secondly read output body file (.float)
			fileName = outDtaFileName;
			final int outDtaFisLengthNrOfChannels = 4;           //one int   fits in 4 bytes
			final int outDtaFisLengthNrOfSamplesPerChannel = 8;  //one long  fits in 8 bytes
			final int outDtaFisLengthSampleUnitValue = 4;        //one float fits in 4 bytes
			if (TestCodec.LOG_INFO) System.out.println("Reading and parsing the output data file: " + fileName);
			if (!fileName.endsWith(".float")) {
				if (TestCodec.LOG_WARNING) System.out.println("Warning! File does not have the .float extension!");
			}
			outDtaFis = new FileInputStream(fileName);
			FileChannel outDtaFisChannel = outDtaFis.getChannel();
			final long outDtaFisOffsetNrOfChannels = 0;
			MappedByteBuffer outDtaFisMapBuffer = outDtaFisChannel.map(FileChannel.MapMode.READ_ONLY, outDtaFisOffsetNrOfChannels, outDtaFisLengthNrOfChannels);
			final long outDtaFisSize = outDtaFisChannel.size();
			if (TestCodec.LOG_INFO) System.out.println("File size: " + outDtaFisSize + " bytes = " + (outDtaFisSize/1024f) + " kB = " + (outDtaFisSize/1048576f) + " MB = " + (outDtaFisSize/1073741824f) + " GB");
			final int outDtaFisNrOfChannels = outDtaFisMapBuffer.getInt(0);
			if (TestCodec.LOG_INFO) System.out.println("Nr of channels found: " + outDtaFisNrOfChannels);
			if (outDtaFisNrOfChannels * outDtaFisLengthNrOfSamplesPerChannel >= outDtaFisSize) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! File has bad format, too big number of channels specified!");
				return;
			}
			if (outDtaFisNrOfChannels != outHdrNrOfChannels.longValue()) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! Number of channels specified in output header file differs from that specified here. Please correct .hdr file or use another .float file!");
				return;
			}
			if (inDtaChannelSetNrOfChannels != outDtaFisNrOfChannels) {
				if (TestCodec.LOG_TEST) System.out.println("CODEC TEST RESULT: ERROR. Details: Number of channels get from codec (" + inDtaChannelSetNrOfChannels + ") differs from that specified in output files .hdr and .float (" + outDtaFisNrOfChannels + ").");
				return;
			}
			long outDtaFisOffset = outDtaFisOffsetNrOfChannels + outDtaFisLengthNrOfChannels;
			channels: for (int channelNr = 0; channelNr < outDtaFisNrOfChannels && outDtaFisOffset < outDtaFisSize; channelNr ++) {
				//info from output
				if (TestCodec.LOG_INFO) System.out.print("Channel " + channelNr);
				outDtaFisMapBuffer = outDtaFisChannel.map(FileChannel.MapMode.READ_ONLY, outDtaFisOffset, outDtaFisLengthNrOfSamplesPerChannel);
				final long outDtaFisNrOfSamplesPerChannel = outDtaFisMapBuffer.getLong(0);
				if (TestCodec.LOG_INFO) System.out.print(" has " + outDtaFisNrOfSamplesPerChannel + " sample units in output data file");
				if (outHdrNrOfSamples != null) {
					if (outDtaFisNrOfSamplesPerChannel != outHdrNrOfSamples) {
						if (TestCodec.LOG_ERROR) System.out.println("\nError! In output header file there were specified " + outHdrNrOfSamples + " sample units for every channel. Here is other value, please correct .hdr file or change output data file used!");
						return;
					}
				} else {
					if (outDtaFisNrOfSamplesPerChannel != outHdrNrsOfSamples[channelNr]) {
						if (TestCodec.LOG_WARNING) System.out.println("\nWarning! In output header file there were specified " + outHdrNrOfSamples + " sample units for channel #" + channelNr + ". Here is other value!");
					}
				}
				if (outDtaFisNrOfSamplesPerChannel * outDtaFisLengthSampleUnitValue >= outDtaFisSize) {
					if (TestCodec.LOG_ERROR) System.out.println("\nError! File has bad format, too big number of samples specified!");
					return;
				}
				outDtaFisOffset += outDtaFisLengthNrOfSamplesPerChannel;
				long outDtaFisOffsetStart = outDtaFisOffset;
				//info from input
				Channel inDtaChannelObject = inDtaChannelSet.getChannel(channelNr);
				long inDtaChannelObjectNumberOfSamplesPerChannel = inDtaChannelObject.getNumberOfSamples();
				String inDtaChannelObjectChannelName = inDtaChannelObject.getChannelName();
				double inDtaChannelObjectSamplingFrequency = inDtaChannelObject.getSamplingFrequency();
				boolean gotoNextChannel = false;
				boolean gotoNextSamplePortionBig = false;
				boolean gotoNextSamplePortionSmall = false;
				//comparing header info
				if (!inDtaChannelObjectChannelName.equals(outHdrLabelsOfChannels[channelNr])) {
					if (TestCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: ERROR. Details: Label or name of the channel #" + channelNr + " get from codec (" + inDtaChannelObjectChannelName + ") differs from that specified in output file .hdr (" + outHdrLabelsOfChannels[channelNr] + ").");
					////TODO increment offset
					//break channels; //return;
				}
				double outHdrSamplingFrequencyPerChannel = ((outHdrSamplingFrequency == null) ? outHdrSamplingFrequencies[channelNr] : outHdrSamplingFrequency);
				if (Math.abs(inDtaChannelObjectSamplingFrequency - outHdrSamplingFrequencyPerChannel) > testDeltaForSamplingFrequency) {
					if (TestCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: ERROR. Details: Sampling frequency of the channel #" + channelNr + " get from codec (" + inDtaChannelObjectSamplingFrequency + ") differs from that specified in output file .hdr (" + outHdrSamplingFrequencyPerChannel + ").");
					////TODO increment offset
					//break channels; //return;
				}
				if (inDtaChannelObjectNumberOfSamplesPerChannel != outDtaFisNrOfSamplesPerChannel) {
					if (TestCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: ERROR. Details: Number of samples per channel #" + channelNr + " get from codec (" + inDtaChannelObjectNumberOfSamplesPerChannel + ") differs from that specified in output file .hdr (" + outDtaFisNrOfSamplesPerChannel + ").");
					//TODO increment offset
					gotoNextChannel = true; //continue channels; //return;
				}
				int howMuchErrorsToRemember = 10; //100;
				int howMuchErrorsFound = 0;
				float[] rememberedErrorsValueShouldBe = new float[howMuchErrorsToRemember];
				float[] rememberedErrorsValueThereIs = new float[howMuchErrorsToRemember];
				//comparing data info
				long samplesBytesAlreadyRead = outDtaFisOffset - outDtaFisOffsetStart;
				long samplesAlreadyRead = samplesBytesAlreadyRead / outDtaFisLengthSampleUnitValue;
				long samplesToReadAllRest = outDtaFisNrOfSamplesPerChannel - samplesAlreadyRead;
				final long samplesToReadMax = 4096;
				long samplesToRead = Math.min(samplesToReadMax, samplesToReadAllRest);
				long bytesToRead = samplesToRead * outDtaFisLengthSampleUnitValue;
				samplesBig: for (int sampleUnitNr = 0; sampleUnitNr < outDtaFisNrOfSamplesPerChannel; ) {
					/*final long*/ samplesBytesAlreadyRead = outDtaFisOffset - outDtaFisOffsetStart;
					/*final long*/ samplesAlreadyRead = samplesBytesAlreadyRead / outDtaFisLengthSampleUnitValue;
					/*final long*/ samplesToReadAllRest = outDtaFisNrOfSamplesPerChannel - samplesAlreadyRead;
					/*final long samplesToReadMax = 4096; */
					/*final long*/ samplesToRead = Math.min(samplesToReadMax, samplesToReadAllRest);
					/*final long*/ bytesToRead = samplesToRead * outDtaFisLengthSampleUnitValue;
					if (gotoNextChannel == false && gotoNextSamplePortionBig == false) {
						outDtaFisMapBuffer = outDtaFisChannel.map(FileChannel.MapMode.READ_ONLY, outDtaFisOffset, bytesToRead);
						int sampleUnitPosInCurrentBuffer = 0;
						int sampleUnitNrInCurrentBuffer = 0;
						samplesSmall: for (; sampleUnitPosInCurrentBuffer < bytesToRead; sampleUnitNrInCurrentBuffer ++, sampleUnitPosInCurrentBuffer += outDtaFisLengthSampleUnitValue) {
							final float outDtaFisSampleUnitValue = outDtaFisMapBuffer.getFloat((int) sampleUnitPosInCurrentBuffer) * verificationMultiplyFactor;
							if (TestCodec.LOG_DEBUG) System.out.print(" " + outDtaFisSampleUnitValue); //System.out.format(" %.3f", outDtaFisSampleUnitValue);
							int fullSampleNr = sampleUnitNr + sampleUnitNrInCurrentBuffer;
							float inDtaCodecSampleUnitValue = inDtaChannelObject.getSample(fullSampleNr);
							if (Math.abs(inDtaCodecSampleUnitValue - outDtaFisSampleUnitValue) > testDeltaForSample) {
								if (howMuchErrorsFound < howMuchErrorsToRemember) {
									rememberedErrorsValueShouldBe[howMuchErrorsFound] = outDtaFisSampleUnitValue;
									rememberedErrorsValueThereIs[howMuchErrorsFound] = inDtaCodecSampleUnitValue;
									if (TestCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: ERROR. Details: Value of the sample #" + fullSampleNr + " of the channel #" + channelNr + " taken from the codec (" + inDtaCodecSampleUnitValue + ") differs from that specified in output file .float (" + outDtaFisSampleUnitValue + ").");
								}
								//increment offset -- if only we break it, but my last decision is not to break the flow
								//continue samplesBig; //break channels; //return;
								howMuchErrorsFound ++;
							//} else {
							//	if (howMuchErrorsFound < howMuchErrorsToRemember) {
							//		if (TestCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: OK. Details: Value of the sample #" + fullSampleNr + " of the channel #" + channelNr + " taken from the codec (" + inDtaCodecSampleUnitValue + ") is equal to that specified in output file .float (" + outDtaFisSampleUnitValue + ").");
							//	}
							}
						}
					}
					outDtaFisOffset += bytesToRead;
					sampleUnitNr += samplesToRead;
					if (gotoNextChannel == true) {
						gotoNextChannel = false;
						continue channels;
					}
				}
				if (TestCodec.LOG_INFO) System.out.println();
			}
		} catch (FileNotFoundException e) {
			if (TestCodec.LOG_ERROR) {
				System.out.println("Error! Cannot find a file " + fileName + ". Check command-line arguments to the running program.");
				if (TestCodec.LOG_ERROR) e.printStackTrace();
			}
		} catch (IOException e) {
			if (TestCodec.LOG_ERROR) {
				System.out.println("Error! I/O error during reading the file" + fileName + ". Please verify that it is a proper file.");
				if (TestCodec.LOG_ERROR) e.printStackTrace();
			}
		} finally {
			if (outDtaFis != null)
				try {
					outDtaFis.close();
				} catch (IOException e) {
					if (TestCodec.LOG_ERROR) e.printStackTrace();
				}
		}
	}
	
}

