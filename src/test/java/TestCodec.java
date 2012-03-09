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
	private final static String outHdrNameSamplesOfChannels = "channel_samples"; //virtual
	private final static String outHdrNameScaled = "scaled";
	
	private final static float precision = 0.0001f;
	
	private final static boolean LOG_DEBUG = false;
	private final static boolean LOG_INFO = true;
	private final static boolean LOG_WARNING = true;
	private final static boolean LOG_ERROR = true;
	private final static boolean LOG_FATAL = true;
	private final static boolean LOG_TEST = true;

	public static byte[] readFile(String fileName, int bufSize)
	throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(fileName);
		FileChannel channel = fis.getChannel();
		long fSize = channel.size();
		MappedByteBuffer mapBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0L, fSize);
		byte[] buffer = new byte[(int)fSize];
		int nGet = 0;
		int idx = 0;
		while(mapBuffer.hasRemaining()) {
			nGet = Math.min(mapBuffer.remaining(), bufSize);
			mapBuffer.get(buffer, idx, nGet);
			idx += nGet;
		}
		return buffer;
	}

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

	public static String[] readTextFile(String fileName, String charset, String lineDelimiter, int bufSize)
	throws FileNotFoundException, IOException {
		byte[] buffer = TestCodec.readFile(fileName, bufSize);
		String bufStr = new String(buffer, charset);
		String[] lines = bufStr.split(lineDelimiter);
		return lines;
	}
	
	public static String[] readTextFileUsingParts(String fileName, String charset, String lineDelimiter, int bigBufSize, int smallBufSize)
	throws FileNotFoundException, IOException {
		byte[] buffer = TestCodec.readFileUsingParts(fileName, bigBufSize, smallBufSize);
		String bufStr = new String(buffer, charset);
		String[] lines = bufStr.split(lineDelimiter);
		return lines;
	}
	
	public static AbstractMap<String, Object> readCorrectOutputHeader(String fileName, AbstractMap<String, Object> allData)
	throws FileNotFoundException, IOException {
		String charset = "UTF-8";
		String lineDelimiter = "\n";
		int bufSize = 16384;
		String[] lines = TestCodec.readTextFile(fileName, charset, lineDelimiter, bufSize);
		ArrayList<ArrayList<Float>> samples = new ArrayList<ArrayList<Float>>();
		ArrayList<String> channelLabels = new ArrayList<String>();
		if (allData == null) allData = new LinkedHashMap<String, Object>();
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			if (line.startsWith("#")) {
				//this is comment => skip line
			} else if (line.trim().length() < 1) {
				//empty => skip line
			} else if (line.startsWith(TestCodec.outHdrNameNrOfSamples)) {
				int valueIdx = line.indexOf("=", TestCodec.outHdrNameNrOfSamples.length());
				if (valueIdx == -1) {
					if (TestCodec.LOG_FATAL) System.out.println("Fatal error occured. Class was implemented not properly!");
					return null;
				}
				String valueStr = line.substring(valueIdx + 1).trim();
				String[] valueArrayStr = valueStr.split(",");
				if (valueArrayStr.length < 2) {
					long valueLong = -1L;
					try {
						valueLong = Long.parseLong(valueStr, 10);
					} catch (NumberFormatException ex) {
						if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a long number, but " + TestCodec.outHdrNameNrOfSamples + " item wants it!");
						return null;
					}
					if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameNrOfSamples + " item is (int)" + valueLong);
					allData.put(TestCodec.outHdrNameNrOfSamples, new Long(valueLong));
				} else {
					ArrayList<Long> nrOfSamplesForAllChannels = new ArrayList<Long>();
					for (int j = 0; j < valueArrayStr.length; j++) {
						String nfOfSamplesPerChannelStr = valueArrayStr[j];
						long nrOfSamplesPerChannelLong = -1L;
						try {
							nrOfSamplesPerChannelLong = Long.parseLong(nfOfSamplesPerChannelStr, 10);
						} catch (NumberFormatException ex) {
							if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a long number: " + nfOfSamplesPerChannelStr);
							return null;
						}
						nrOfSamplesForAllChannels.add(new Long(nrOfSamplesPerChannelLong));
					}
					allData.put(TestCodec.outHdrNameNrOfSamples, nrOfSamplesForAllChannels);
				}
			} else if (line.startsWith(TestCodec.outHdrNameNrOfChannels)) {
				int valueIdx = line.indexOf("=", TestCodec.outHdrNameNrOfChannels.length());
				if (valueIdx == -1) {
					if (TestCodec.LOG_FATAL) System.out.println("Fatal error occured. Class was implemented not properly!");
					return null;
				}
				String valueStr = line.substring(valueIdx + 1).trim();
				int valueInt = -1;
				try {
					valueInt = Integer.parseInt(valueStr, 10);
				} catch (NumberFormatException ex) {
					if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not an integer number, but " + TestCodec.outHdrNameNrOfChannels + " item wants it!");
					return null;
				}
				if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameNrOfChannels + " item is (int)" + valueInt);
				allData.put(TestCodec.outHdrNameNrOfChannels, new Integer(valueInt));
			} else if (line.startsWith(TestCodec.outHdrNameSamplingFrequency)) {
				int valueIdx = line.indexOf("=", TestCodec.outHdrNameSamplingFrequency.length());
				if (valueIdx == -1) {
					if (TestCodec.LOG_FATAL) System.out.println("Fatal error occured. Class was implemented not properly!");
					return null;
				}
				String valueStr = line.substring(valueIdx + 1).trim();
				String[] valueArrayStr = valueStr.split(",");
				if (valueArrayStr.length < 2) {
					double valueDouble = Double.NaN; //-1.0f;
					try {
						valueDouble = Double.parseDouble(valueStr);
					} catch (NumberFormatException ex) {
						if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number, but " + TestCodec.outHdrNameSamplingFrequency + " item wants it!");
						return null;
					}
					if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameSamplingFrequency + " item is (float)" + valueDouble);
					allData.put(TestCodec.outHdrNameSamplingFrequency, new Double(valueDouble));
				} else {
					ArrayList<Double> samplingFrequenciesForAllChannels = new ArrayList<Double>();
					for (int j = 0; j < valueArrayStr.length; j++) {
						String samplingFrequencyPerChannelStr = valueArrayStr[j];
						double samplingFrequencyPerChannelDouble = Double.NaN; //-1.0f;
						try {
							samplingFrequencyPerChannelDouble = Double.parseDouble(samplingFrequencyPerChannelStr);
						} catch (NumberFormatException ex) {
							if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number: " + samplingFrequencyPerChannelStr);
							return null;
						}
						samplingFrequenciesForAllChannels.add(new Double(samplingFrequencyPerChannelDouble));
					}
					allData.put(TestCodec.outHdrNameSamplingFrequency, samplingFrequenciesForAllChannels);
				}
			} else if (line.startsWith(TestCodec.outHdrNameLabelsOfChannels)) {
				int valueIdx = line.indexOf("=", TestCodec.outHdrNameLabelsOfChannels.length());
				if (valueIdx == -1) {
					if (TestCodec.LOG_FATAL) System.out.println("Fatal error occured. Class was implemented not properly!");
					return null;
				}
				if (!allData.containsKey(TestCodec.outHdrNameLabelsOfChannels)) {
					allData.put(TestCodec.outHdrNameLabelsOfChannels, channelLabels);
				}
				//if (!allData.containsKey(TestCodec.outHdrNameSamplesOfChannels)) {
				//	allData.put(TestCodec.outHdrNameSamplesOfChannels, samples);
				//}
				Long maxNrOfSamplesPerChannel = -1L;
				Object nrOrNrsOfSamplesObj = allData.get(TestCodec.outHdrNameNrOfSamples);
				if (nrOrNrsOfSamplesObj instanceof ArrayList<?>) {
					ArrayList<?> nrsOfSamples = (ArrayList<?>) nrOrNrsOfSamplesObj;
					Iterator<?> itObj = nrsOfSamples.iterator();
					while (itObj.hasNext()) {
						Long nrOfSamples = (Long) itObj.next();
						if (nrOfSamples == null) {
							if (TestCodec.LOG_ERROR) System.out.println("Error! Number of samples is not known! It should be specified in the file " + fileName + ".");
							return null;
						} else if (nrOfSamples.longValue() < 0) {
							if (TestCodec.LOG_ERROR) System.out.println("Error! Number of samples should be greater than zero! It should be specified correctly in the file " + fileName + ".");
							return null;
						}
						if (nrOfSamples > maxNrOfSamplesPerChannel) {
							maxNrOfSamplesPerChannel = nrOfSamples;
						}
					}
				} else if (nrOrNrsOfSamplesObj instanceof Long) {
					Long nrOfSamples = (Long) nrOrNrsOfSamplesObj;
					if (nrOfSamples.longValue() < 0) {
						if (TestCodec.LOG_ERROR) System.out.println("Error! Number of samples should be greater than zero! It should be specified correctly in the file " + fileName + ".");
						return null;
					}
					if (nrOfSamples > maxNrOfSamplesPerChannel) {
						maxNrOfSamplesPerChannel = nrOfSamples;
					}
				} else {
					if (TestCodec.LOG_FATAL) System.out.println("Fatal error occured. Object nrOfSamples should be Integer of ArrayList<Integer>, but other type was found: " + nrOrNrsOfSamplesObj.getClass().getName());
					return null;
				}
				String valueStr = line.substring(valueIdx + 1).trim();
				String[] valueArrayStr = valueStr.split(",");
				ArrayList<Float> oneSample = new ArrayList<Float>();
				for (int j = 0; j < valueArrayStr.length; j++) {
					String key = valueArrayStr[j];
					oneSample.add(null);
					//oneSample.put(key, null);
					channelLabels.add(key);
				}
				if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameLabelsOfChannels + " item is " + channelLabels);
				samples.add(oneSample);
				for (int k = 1; k < /*nrOfSamples*/maxNrOfSamplesPerChannel.intValue(); k++) {
					ArrayList<Float> nextSample = new ArrayList<Float>();
					//Iterator<String> keysIt = oneSample.keySet().iterator();
					Iterator<String> keysIt = channelLabels.iterator();
					while (keysIt.hasNext()) {
						//String key = keysIt.next();
						nextSample.add(null);
						//nextSample.put(key, null); // place causing error java.lang.OutOfMemoryError: Java heap space
					}
					samples.add(nextSample);
				}
			} else if (line.startsWith(TestCodec.outHdrNameScaled)) {
				int valueIdx = line.indexOf("=", TestCodec.outHdrNameScaled.length());
				if (valueIdx == -1) {
					if (TestCodec.LOG_FATAL) System.out.println("Fatal error occured. Class was implemented not properly!");
					return null;
				}
				String valueStr = line.substring(valueIdx + 1).trim();
				int valueInt = -1;
				try {
					valueInt = Integer.parseInt(valueStr, 10);
				} catch (NumberFormatException ex) {
					if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not an integer number, but " + TestCodec.outHdrNameScaled + " item wants it!");
					return null;
				}
				if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameScaled + " item is (int)" + valueInt);
				allData.put(TestCodec.outHdrNameScaled, new Integer(valueInt));
			} else {
				if (TestCodec.LOG_WARNING) System.out.println("Warning! Unknown line format: " + line);
			}
		}
		return allData;
	}

	public static AbstractMap<String, Object> readCorrectOutputBody(String fileName, AbstractMap<String, Object> allData)
	throws FileNotFoundException, IOException {
		if (allData == null) {
			if (TestCodec.LOG_FATAL) System.out.println("Fatal error! Given map allData is a null object!");
			return null;
		}
		String charset = "UTF-8";
		String lineDelimiter = "\n";
		int bufSize = 16384;
		String[] lines = TestCodec.readTextFile(fileName, charset, lineDelimiter, bufSize);
		Integer channelsNrOf = (Integer) allData.get(TestCodec.outHdrNameNrOfChannels);
		long maxNrOfSamplesPerChannel = -1;
		if (allData.get(TestCodec.outHdrNameNrOfSamples) == null) {
		} else if (allData.get(TestCodec.outHdrNameNrOfSamples) instanceof Long) {
			maxNrOfSamplesPerChannel = ((Long) allData.get(TestCodec.outHdrNameNrOfSamples)).longValue();
		} else if (allData.get(TestCodec.outHdrNameNrOfSamples) instanceof ArrayList<?>) {
			ArrayList<?> nrsOfSamples = (ArrayList<?>) allData.get(TestCodec.outHdrNameNrOfSamples);
			Iterator<?> itObj = nrsOfSamples.iterator();
			while (itObj.hasNext()) {
				Long nrOfSamples = (Long) itObj.next();
				if (nrOfSamples == null) {
					//TODO error handling
				} else if (nrOfSamples.longValue() > maxNrOfSamplesPerChannel) {
					maxNrOfSamplesPerChannel = nrOfSamples.longValue();
				}
			}
		}
		ArrayList<String> channelLabels = (ArrayList<String>)allData.get(TestCodec.outHdrNameLabelsOfChannels);
		//TODO
		Object samplesObj = allData.get(TestCodec.outHdrNameSamplesOfChannels);
		ArrayList<ArrayList<Float>> samples = null;
		if (samplesObj instanceof ArrayList<?> && !((ArrayList<?>) samplesObj).isEmpty()) {
			ArrayList<?> samplesOb = (ArrayList<?>) samplesObj;
			Object sampleOb = samplesOb.get(0);
			if (sampleOb instanceof ArrayList<?>) {
				ArrayList<?> sampleO = (ArrayList<?>) sampleOb;
				ArrayList<Float> sample = (ArrayList<Float>) sampleO;
				if (!sample.isEmpty()) {
					samples = (ArrayList<ArrayList<Float>>) samplesObj;
				} else {
					if (TestCodec.LOG_FATAL) System.out.println("Fatal error! Bad format of the first sample of the item in the samplesObj list!");
					return null;
				}
			} else {
				if (TestCodec.LOG_FATAL) System.out.println("Fatal error! Bad format of the item in the samplesObj list!");
				return null;
			}
		} else {
			if (TestCodec.LOG_FATAL) System.out.println("Fatal error! Bad format of the samplesObj!");
			return null;
		}
		if (maxNrOfSamplesPerChannel/*samplesNrOf*/ != lines.length) {
			if (TestCodec.LOG_ERROR) System.out.println("Error! Wrong number of the samples found in the file " + fileName + "! Should be " + maxNrOfSamplesPerChannel + " but is " + lines.length + "!");
			return null;
		} else {
			if (TestCodec.LOG_DEBUG) System.out.println("Number of the samples found in the file " + fileName + " is equal to the value read from the header file: " + lines.length);
		}
		for (int i = 0; i < lines.length; i++) { //lines are just samples, so i is sample number
			String lineSample = lines[i]; //sample number and sample itself (list of channels)
			String[] channelsAtSample = lineSample.split(" ");
			String sampleNumberStr = channelsAtSample[0];
			long sampleNumberInt = -1L;
			try {
				sampleNumberInt = Long.parseLong(sampleNumberStr);
			} catch (NumberFormatException ex) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! Bad format of the sample #" + i + "! First in line should be the number of the sample, but is '" + sampleNumberStr + "'!");
				return null;
			}
			if (sampleNumberInt != i) {
				if (TestCodec.LOG_WARNING) System.out.println("Warning! Sample #" + i + " was numberized as #" + sampleNumberInt);
			}
			if (channelsAtSample.length == 0 + 1) {
				if (TestCodec.LOG_WARNING) System.out.println("Warning! Sample #" + i + " is empty!");
			} else if (channelsAtSample.length < channelsNrOf + 1) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! Sample #" + i + " is too short! Its length is " + channelsAtSample.length + ", while it should be " + channelsNrOf);
				return null;
			} else if (channelsAtSample.length > channelsNrOf + 1) {
				if (TestCodec.LOG_WARNING) System.out.println("Warning! Sample #" + i + " is too long! Its length is " + channelsAtSample.length + ", while it should be " + channelsNrOf);
			}
			ArrayList<Float> oneSample = samples.get(i); //i is a sample number
			//Collection<Float> valuesForSample = oneSample.values();
			Float[] valuesForSampleArr = new Float[/*valuesForSample*/oneSample.size()];
			valuesForSampleArr = /*valuesForSample*/oneSample.toArray(valuesForSampleArr);
			for (int j = 1; j < channelsAtSample.length; j++) {
				String channelSampleStr = channelsAtSample[j];
				float channelSampleFloat = Float.NaN; //-1.0f;
				if (!channelSampleStr.equals("--")) {
					try {
						channelSampleFloat = Float.parseFloat(channelSampleStr);
					} catch (NumberFormatException ex) {
						if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " at the index #" + j + " contains value which is not an float number, but it should be!");
						return null;
					}
				}
				String oneLabel = channelLabels.get(j); //label of channel j
				valuesForSampleArr[j - 1] = channelSampleFloat;
			}
			//Set<String> keysForSample = oneSample.keySet();
			String[] keysForSampleArr = new String[/*keysForSample*/channelLabels.size()];
			keysForSampleArr = /*keysForSample*/channelLabels.toArray(keysForSampleArr);
			if (keysForSampleArr.length != valuesForSampleArr.length) {
				if (TestCodec.LOG_ERROR) System.out.println("Error! Number of the keys (" + keysForSampleArr.length + ") should be the same as number of the values (" + valuesForSampleArr.length + ")!");
				return null;
			}
			for (int j = 0; j < valuesForSampleArr.length; j++) {
				samples.get(i).add(j, valuesForSampleArr[j]);
			}
			/*Iterator<Float> valuesForSampleIter = valuesForSample.iterator();
			for (int j = 0; j < channelsAtSample.length && valuesForSampleIter.hasNext(); j++) {
				Float el = valuesForSampleIter.next();
				if (el != null) {
					if (TestCodec.LOG_FATAL) System.out.println("Fatal error! Wrong moment for put float value into collection!");
					return null;
				}
				el = valuesForSampleArr[j];
			}*/
		}
		return allData;
	}

	public static AbstractMap<String, Object> readRealOutputHeader(String fileName, AbstractMap<String, Object> allData)
	throws FileNotFoundException, IOException {
		if (allData == null) allData = new LinkedHashMap<String, Object>();
		//TODO not needed additional operations yet
		return allData;
	}

	public static AbstractMap<String, Object> readRealOutputBody(Signalml reader, String fileName, AbstractMap<String, Object> allData)
	throws FileNotFoundException, IOException {
		//safety against NullPointerException
		if (allData == null) {
			if (TestCodec.LOG_FATAL) System.out.println("Fatal error! Given map allData is a null object!");
			return null;
		}
		//prepare CODEC reader
		if (reader == null) {
			if (TestCodec.LOG_WARNING) System.out.println("Warning! No CODEC selected. Trying to use EASYS!");
			reader = new EASYS();
		}
		reader.open(new File(fileName));
		reader.createParams();
		reader.createChannels();
		System.out.print(ContextDumper.dump(reader));
		//prepare collection for storing it in the future and for returning it as the result
		ArrayList<LinkedHashMap<String, Float>> samples = new ArrayList<LinkedHashMap<String, Float>>();
		allData.put(TestCodec.outHdrNameSamplesOfChannels, samples);
		//find number of samples for matrix (what is equal to maximum number of samples per channel)
		int nrOfChannels = reader.get_set().getNumberOfChannels();
		long maxNumberOfSamplesPerChannel = 0; // used instead of wrong and not usefull: reader.get_set().getNumberOfSamples();
		for (int channelNr = 0; channelNr < nrOfChannels; channelNr ++) {
			Channel channel = reader.get_set().getChannel(channelNr);
			if (channel == null) {
				if (TestCodec.LOG_WARNING) System.out.println("Warning! Channel #" + channelNr + " can not be read!");
				continue;
			}
			long numberOfSamplesPerChannel = channel.getNumberOfSamples();
			if (numberOfSamplesPerChannel > maxNumberOfSamplesPerChannel) {
				maxNumberOfSamplesPerChannel = numberOfSamplesPerChannel;
			}
		}
		//preparing empty samples
		for (long sampleNr = 0; sampleNr < maxNumberOfSamplesPerChannel; sampleNr ++) {
			samples.add(new LinkedHashMap<String, Float>());
		}
		//storing data in samples
		for (int channelNr = 0; channelNr < nrOfChannels; channelNr ++) {
			//store sampling frequency for every channel
			double samplingFrequency = reader.get_set().getSamplingFrequency();
			allData.put(TestCodec.outHdrNameSamplingFrequency, new Double(samplingFrequency));
			//fill buffer with channel data samples
			Channel channel = reader.get_set().getChannel(channelNr);
			long numberOfSamplesPerChannel = channel.getNumberOfSamples(); // used instead of wrong and not usefull: reader.get_set().getNumberOfSamples();
			FloatBuffer floatBuffer = FloatBuffer.allocate(((int) numberOfSamplesPerChannel));
			channel.getSamples(floatBuffer, 0);
			float[] buffer = floatBuffer.array();
			int nrOfSamplesInBuffer = buffer.length;
			if (nrOfSamplesInBuffer < numberOfSamplesPerChannel) {
				if (TestCodec.LOG_FATAL) System.out.println("Fatal error! Number of samples found in buffer get from CODEC (" + nrOfSamplesInBuffer + ") should be equal to or greater than number of samples specified in channel property numberOfSamples (taken from input file header) dedicated for it (which property is equal to " + numberOfSamplesPerChannel + "). Please verify correctness of the input file: " + fileName);
				return null;
			}
			//TODO write buffer to the file ? not needed yet
			//...
			//parsing buffer and filling collection (prepared before) with samples
			String channelName = channel.getChannelName();
			for (long sampleNr = 0; sampleNr < maxNumberOfSamplesPerChannel; sampleNr ++) {
				float valueOfSample = Float.NaN;
				if (sampleNr < numberOfSamplesPerChannel) {
					valueOfSample = buffer[(int) sampleNr];
				}
				System.out.println("channel #" + channelNr + ", sample # " + sampleNr + " = " + valueOfSample);
				samples.get((int) sampleNr).put(channelName, valueOfSample); //very important line
			}
			//goto next channel
		}
		//return result
		return allData;
	}

	public static void printOutput(AbstractMap<String, Object> allData) {
		// instead of simple System.out.println(allData) using detailed printing :
		Set<Entry<String, Object>> keysAndValues = allData.entrySet();
		Iterator<Entry<String, Object>> iterator = keysAndValues.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			//System.out.println("\t" + key + "\t=\t" + value);
			if (value == null) {
				System.out.println("Output is null");
			} else if (key.equals(TestCodec.outHdrNameNrOfSamples) && value instanceof Long) {
				Long nrOfSamples = (Long) value;
				System.out.println("Number of samples equal for every channel = " + nrOfSamples);
			} else if (key.equals(TestCodec.outHdrNameNrOfChannels) && value instanceof Integer) {
				Integer nrOfChannels = (Integer) value;
				System.out.println("Number of channels = " + nrOfChannels);
			} else if (key.equals(TestCodec.outHdrNameSamplingFrequency) && value instanceof Double) {
				Double samplingFrequency = (Double) value;
				System.out.println("Sampling frequency equal for every channel = " + samplingFrequency);
			} else if (key.equals(TestCodec.outHdrNameLabelsOfChannels) && value instanceof ArrayList<?>) {
				ArrayList<ArrayList<Float>> samples = (ArrayList<ArrayList<Float>>) value;
				//TODO
			} else if (key.equals(TestCodec.outHdrNameSamplesOfChannels) && value instanceof ArrayList<?>) {
				ArrayList<LinkedHashMap<String, Float>> samples = (ArrayList<LinkedHashMap<String, Float>>) value;
				System.out.println("Samples and their numbers, channel numbers and names, and their values:");
				for (long sampleNr = 0; sampleNr < samples.size(); sampleNr ++) {
					LinkedHashMap<String, Float> sample = samples.get((int) sampleNr);
					Set<Entry<String, Float>> sampleKeysAndValues = sample.entrySet();
					Iterator<Entry<String, Float>> sampleIterator = sampleKeysAndValues.iterator();
					int channelNumber = 0;
					System.out.print(sampleNr + "\t=");
					while (sampleIterator.hasNext()) {
						Entry<String, Float> sampleEntry = sampleIterator.next();
						String channelName = sampleEntry.getKey();
						Float sampleValue = sampleEntry.getValue();
						System.out.print("\t" + channelNumber + ":" + channelName + "=" + sampleValue);
						channelNumber ++;
					}
					System.out.println();
				}
			} else if (key.equals(TestCodec.outHdrNameScaled) && value instanceof Integer) {
				Integer scaled = (Integer) value;
				System.out.println("Scaled = " + scaled);
			}
		}
	}
	
	public static boolean compareCorrectAndRealOutputs(AbstractMap<String, Object> correctOutput, AbstractMap<String, Object> realOutput) {
		ArrayList<ArrayList<Float>> correctSamples = (ArrayList<ArrayList<Float>>) correctOutput.get(TestCodec.outHdrNameSamplesOfChannels);
		ArrayList<ArrayList<Float>>    realSamples = (ArrayList<ArrayList<Float>>) realOutput.get(TestCodec.outHdrNameSamplesOfChannels);
		ArrayList<String> correctChannelLabels = (ArrayList<String>) correctOutput.get(TestCodec.outHdrNameLabelsOfChannels);
		ArrayList<String>    realChannelLabels = (ArrayList<String>) realOutput.get(TestCodec.outHdrNameLabelsOfChannels);
		if (correctSamples == null && realSamples == null) {
			System.out.println("TEST RESULT: unknown. Reason: Both correct samples and real samples are null. Cannot make comparison.");
			return false;
		} else if (correctSamples == null || realSamples == null) {
			System.out.println("TEST RESULT: false. Reason: One of the samples is null, and second is not. Null value have the " + ((correctSamples == null) ? "correct" : "real") + " samples.");
			return false;
		}
		if (correctSamples.isEmpty() && realSamples.isEmpty()) {
			System.out.println("TEST RESULT: unknown. Reason: Both correct samples and real samples are empty. Cannot make comparison.");
			return false;
		} else if (correctSamples.isEmpty() || realSamples.isEmpty()) {
			System.out.println("TEST RESULT: false. Reason: One of the samples is empty, and second is not. Empty are the " + ((correctSamples == null) ? "correct" : "real") + " samples.");
			return false;
		}
		if (correctSamples.size() != realSamples.size()) {
			System.out.println("TEST RESULT: false. Reason: number of samples differs between correct output (" + correctSamples.size() + ") and real output (" + realSamples.size() + ").");
			return false;
		}
		for (long sampleNr = 0; sampleNr < correctSamples.size(); sampleNr ++) {
			ArrayList<Float> correctSampleChannels = correctSamples.get((int) sampleNr);
			ArrayList<Float> realSampleChannels = realSamples.get((int) sampleNr);
			if (correctSampleChannels.size() != realSampleChannels.size()) {
				System.out.println("TEST RESULT: false. Reason: number of channels in sample #" + sampleNr + " differs between correct output  (" + correctSampleChannels.size() + ") and real output (" + realSampleChannels.size() + ").");
				return false;
			}
			Iterator<Float> correctSampleIterator = correctSampleChannels.iterator();
			Iterator<Float> realSampleIterator = realSampleChannels.iterator();
			int channelNumber = 0;
			while (correctSampleIterator.hasNext() || realSampleIterator.hasNext()) {
				Float correctSampleValue = correctSampleIterator.next();
				Float realSampleValue = realSampleIterator.next();
				String correctChannelName = correctChannelLabels.get(channelNumber);
				String realChannelName = realChannelLabels.get(channelNumber);
				if (!correctChannelName.equals(realChannelName) || Math.abs(realSampleValue - correctSampleValue) > TestCodec.precision) {
					System.out.print("TEST RESULT: false. Reason: Sample pairs (channelName=sampleValue) for sample # " + sampleNr + " channel #" + channelNumber + " differs between correct output (" + correctChannelName + "=" + correctSampleValue + ") and real output (" + realChannelName + "=" + realSampleValue + ").");
					return false;
				}
				channelNumber ++;
			}
		}
		return true;
	}
	
	public static void main1(java.lang.String... args) {
		if (args.length < 4) {
			System.out.println("Using:\njava " + TestCodec.class.getSimpleName() + " input_header input_data output_header.hdr output_data.ascii");
			return;
		}
		BasicConfigurator.configure();
		String  inHdrFileName = args[0]; //?
		String  inDtaFileName = args[1]; //d:/grst/projects/UW/data/for_EASYS_codec/inb02.d
		String outHdrFileName = args[2]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.hdr   //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.hdr
		String outDtaFileName = args[3]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.ascii //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.ascii
		try {
			// output data for verification
			AbstractMap<String, Object> dataCorrect = new LinkedHashMap<String, Object>();
			dataCorrect = TestCodec.readCorrectOutputHeader(outHdrFileName, dataCorrect);
			dataCorrect = TestCodec.readCorrectOutputBody(outDtaFileName, dataCorrect);
			TestCodec.printOutput(dataCorrect);
			// output data from real device
			Signalml codec = new EASYS();
			AbstractMap<String, Object> dataReal = new LinkedHashMap<String, Object>();
			dataReal = TestCodec.readRealOutputHeader(inHdrFileName, dataReal);
			dataReal = TestCodec.readRealOutputBody(codec, inDtaFileName, dataReal);
			TestCodec.printOutput(dataReal);
			// compare them
			boolean result = compareCorrectAndRealOutputs(dataCorrect, dataReal);
			System.out.println(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main2(java.lang.String... args) {
		if (args.length < 4) {
			System.out.println("Using:\njava " + TestCodec.class.getSimpleName() + " input_header input_data output_header.hdr output_data.ascii");
			return;
		}
		String  inHdrFileName = args[0]; //?
		String  inDtaFileName = args[1]; //d:/grst/projects/UW/data/for_EASYS_codec/inb02.d
		String outHdrFileName = args[2]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.hdr   //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.hdr
		String outDtaFileName = args[3]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.ascii //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.ascii
		String charset = "UTF-8";
		String lineDelimiter = "\n";
		int bigBufSize = 1048576;
		int bufSize = 16384;
		String fileName = null;
		try {
			fileName = outHdrFileName;
			System.out.println("Reading file " + fileName);
			String[] outHdrLines = TestCodec.readTextFileUsingParts(fileName, charset, lineDelimiter, bigBufSize, bufSize);
			System.out.println("File " + fileName + " has been read out successfully");
			fileName = outDtaFileName;
			System.out.println("Reading file " + fileName);
			String[] outDtaLines = TestCodec.readTextFileUsingParts(fileName, charset, lineDelimiter, bigBufSize, bufSize);
			System.out.println("File " + fileName + " has been read out successfully");
		} catch (FileNotFoundException e) {
			System.out.println("Error! Cannot find file " + fileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error! I/O error during reading file " + fileName);
			e.printStackTrace();
		}
	}
	
	public static void main(java.lang.String[] args) {
		if (args.length < 4) {
			System.out.println("Using:\njava " + TestCodec.class.getSimpleName() + " input_header input_data output_header.hdr output_data.float");
			return;
		}
		//String  inHdrFileName = args[0]; //?
		String  inDtaFileName = args[1]; //d:/grst/projects/UW/data/for_EASYS_codec/inb02.d
		String outHdrFileName = args[2]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.hdr   //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.hdr
		String outDtaFileName = args[3]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.float //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.float
		FileInputStream outDtaFis = null;
		String fileName = null; //name of the file currently read -- taken separately because of possible exceptions occurings
		float testDeltaForSample = 0.001f;
		double testDeltaForSamplingFrequency = 0.000001d;
		try {
			//giving input to codec and getting result from it
			fileName = inDtaFileName;
			Signalml reader = null; //TODO please make abstract method getSignalml() and superclass of the TestCodec will give us there codec EASYS or M4D or ...
			if (reader == null) {
				if (TestCodec.LOG_WARNING) System.out.println("Warning! No CODEC selected. Trying to use EASYS!");
				reader = new EASYS();
			}
			reader.open(new File(fileName));
			reader.createParams();
			reader.createChannels();
			int inDtaNumberOfChannelSets = reader.getNumberOfChannelSets(); //@Theory, do not know what is this
			ChannelSet inDtaChannelSet = reader.get_set();
			int inDtaChannelSetNrOfChannels = inDtaChannelSet.getNumberOfChannels();
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
					float sampleUnitValue = inDtaChannelObject.getSample(sampleNr);
					if (TestCodec.LOG_DEBUG) System.out.println(" " + sampleUnitValue); //System.out.format(" %.3f", sampleUnitValue);
					//this is only for test; far far below will be made sth similar to this above
				}
			}
			//TODO show on the screen (optionally -- for INFO log only) some informations from the codec (from above)
			//...
			//firstly read output header file (.hdr)
			fileName = outHdrFileName;
			Integer outHdrNrOfChannels = null;
			Long outHdrNrOfSamples = null;
			Long[] outHdrNrsOfSamples = new Long[0];
			Double outHdrSamplingFrequency = null;
			Double[] outHdrSamplingFrequencies = new Double[0];
			String[] outHdrLabelsOfChannels = null;
			Integer outHdrScaled = null;
			if (TestCodec.LOG_INFO) System.out.println("Reading the output header file " + fileName);
			String outHdrFileCharset = "UTF-8";
			String outHdrFileLineDelimiter = "\n";
			final int outHdrFileBigBufSize = 262144;
			final int outHdrFileSmallBufSize = 16384;
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
					String[] valueArrayStr = valueStr.split(",");
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
					String[] valueArrayStr = valueStr.split(",");
					if (valueArrayStr.length < 2) {
						double valueDouble = Double.NaN; //-1.0f;
						try {
							valueDouble = Double.parseDouble(valueStr);
						} catch (NumberFormatException ex) {
							if (TestCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #" + i + " contains value which is not a double number, but " + TestCodec.outHdrNameSamplingFrequency + " item wants it!");
							return;
						}
						if (TestCodec.LOG_DEBUG) System.out.println("Value for " + TestCodec.outHdrNameSamplingFrequency + " item is (float)" + valueDouble);
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
					outHdrLabelsOfChannels = valueStr.split(",");
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
					if (TestCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: ERROR. Details: Label or name of the channel #" + channelNr + " get from codec (" + inDtaChannelObjectChannelName + ") differs from that specified in output file .float (" + outHdrLabelsOfChannels[channelNr] + ").");
					////TODO increment offset
					//break channels; //return;
				}
				double outHdrSamplingFrequencyPerChannel = ((outHdrSamplingFrequency == null) ? outHdrSamplingFrequencies[channelNr] : outHdrSamplingFrequency);
				if (Math.abs(inDtaChannelObjectSamplingFrequency - outHdrSamplingFrequencyPerChannel) > testDeltaForSamplingFrequency) {
					if (TestCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: ERROR. Details: Sampling frequency of the channel #" + channelNr + " get from codec (" + inDtaChannelObjectSamplingFrequency + ") differs from that specified in output file .float (" + outHdrSamplingFrequencyPerChannel + ").");
					////TODO increment offset
					//break channels; //return;
				}
				if (inDtaChannelObjectNumberOfSamplesPerChannel != outDtaFisNrOfSamplesPerChannel) {
					if (TestCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: ERROR. Details: Number of samples per channel #" + channelNr + " get from codec (" + inDtaChannelObjectNumberOfSamplesPerChannel + ") differs from that specified in output file .float (" + outDtaFisNrOfSamplesPerChannel + ").");
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
							final float outDtaFisSampleUnitValue = outDtaFisMapBuffer.getFloat((int) sampleUnitPosInCurrentBuffer);
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

