import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import jsignalml.Channel;
import jsignalml.ChannelSet;
import jsignalml.ContextDumper;
import jsignalml.codec.Signalml;

/**
 * @author Grzegorz Stadnik
 *
 */
public class CheckCodec {

	private static final String  outHdrNameNrOfSamples = "number_of_samples";
	private static final String  outHdrNameNrOfChannels = "number_of_channels";
	private static final String  outHdrNameSamplingFrequency = "sampling_frequency";
	private static final String  outHdrNameLabelsOfChannels = "channel_labels";
	private static final String  outHdrNameTypesOfChannels = "channel_types";
	private static final String  outHdrNameScaled = "scaled";
	private static final String  outHdrNameScalingGain = "scaling_gain";
	private static final String  outHdrNameScalingOffset = "scaling_offset";
	private static final String  outHdrNameCalibrationGain = "calibration_gain";
	private static final String  outHdrNameCalibrationOffset = "calibration_offset";
	private static final String  outHdrNameCalibrationUnits = "calibration_units";

	private Integer              outHdrNrOfChannels = null;
	private Long                 outHdrNrOfSamples = null;
	private Long[]               outHdrNrsOfSamples = new Long[0];
	private Double               outHdrSamplingFrequency = null;
	private Double[]             outHdrSamplingFrequencies = new Double[0];
	private String[]             outHdrLabelsOfChannels = null;
	private String[]             outHdrTypesOfChannels = null;
	private Integer              outHdrScaled = null;
	private Double               outHdrScalingGain = null;
	private Double[]             outHdrScalingGains = new Double[0];
	private Double               outHdrScalingOffset = null;
	private Double[]             outHdrScalingOffsets = new Double[0];
	private Double               outHdrCalibrationGain = null;
	private Double[]             outHdrCalibrationGains = new Double[0];
	private Double               outHdrCalibrationOffset = null;
	private Double[]             outHdrCalibrationOffsets = new Double[0];
	private String[]             outHdrCalibrationUnits = null;
	private String               outHdrFileCharset = "UTF-8";
	private String               outHdrFileLineDelimiter = "\n";
	private final int            outHdrFileBigBufSize = 262144;
	private final int            outHdrFileSmallBufSize = 16384;

	private final int            howMuchErrorsToRemember = 100; //10;
	private int                  howMuchErrorsFound = 0;
	private float[]              rememberedErrorsValueShouldBe = new float[howMuchErrorsToRemember];
	private float[]              rememberedErrorsValueThereIs = new float[howMuchErrorsToRemember];

	private ChannelSet           inDtaChannelSet = null;
	private int                  inDtaChannelSetNrOfChannels = 0;
	private FileInputStream      outDtaFis = null;
	private FileChannel          outDtaFisChannel = null;
	private int                  outDtaFisNrOfChannels = 0;
	private long                 outDtaFisOffset = 0;
	private long                 outDtaFisSize = 0;
	private final int            outDtaFisLengthNrOfChannels = 4;           //one int   fits in 4 bytes
	private final int            outDtaFisLengthNrOfSamplesPerChannel = 8;  //one long  fits in 8 bytes
	private final int            outDtaFisLengthSampleUnitValue = 4;        //one float fits in 4 bytes

	private Signalml             codec = null;

	public  static final float   FLP_EPSILON = calculateMachineEpsilonFloat();  //probably FLP_EPSILON = POW(2.0F, -23.0F) = 1.19209290E-07F
	public  static final double  DBL_EPSILON = calculateMachineEpsilonDouble(); //probably DBL_EPSILON = POW(2.0D, -52.0D) = 2.2204460492503131E-16F

	public  static final float   precisionRelFloat = FLP_EPSILON;
	public  static final double  precisionRelDouble = DBL_EPSILON;
	public  static final float   precisionDeltaFloat = 0.01f; //0.001f;
	public  static final double  precisionDeltaDouble = 0.0001d; //0.000001d;
	public  static final int     precisionUlpFloat = 6;
	public  static final long    precisionUlpDouble = 7;

	private static final boolean LOG_DEBUG = false;
	private static final boolean LOG_INFO = true;
	private static final boolean LOG_WARNING = true;
	private static final boolean LOG_ERROR = true;
	private static final boolean LOG_TEST = true;

	/**
	 * CheckCodec constructor. It stores Signalml codec object given as a parameter.
	 *
	 * @param reader Signalml codec object to be stored and than used by methods
	 *
	 * Note! After running this constructor please invoke also:
	 *
	 *  boolean inDtaStatus = getInDta(inDtaFileName, useContextDumper,
	 *  	usePreCheckingOfTheDataFromCodec);
	 *  boolean outDtaStatus = getOutDta(outHdrFileName, outDtaFileName);
	 *  boolean verificationStatus = checkChannels(verificationMultiplyFactor,
	 *  	testDeltaForSample, testDeltaForSamplingFrequency);
	 *
	 *  See main() method to see an example of how to use CheckCodec class.
	 *  Nothing more is needed to use this class completely.
	 */
	public CheckCodec(Signalml reader) {
		this.codec = reader;
	}

	/**
	 * @return the outHdrNrOfChannels
	 */
	public Integer getOutHdrNrOfChannels() {
		return outHdrNrOfChannels;
	}

	/**
	 * @param outHdrNrOfChannels the outHdrNrOfChannels to set
	 */
	public void setOutHdrNrOfChannels(Integer outHdrNrOfChannels) {
		this.outHdrNrOfChannels = outHdrNrOfChannels;
	}

	/**
	 * @return the outHdrNrOfSamples
	 */
	public Long getOutHdrNrOfSamples() {
		return outHdrNrOfSamples;
	}

	/**
	 * @param outHdrNrOfSamples the outHdrNrOfSamples to set
	 */
	public void setOutHdrNrOfSamples(Long outHdrNrOfSamples) {
		this.outHdrNrOfSamples = outHdrNrOfSamples;
	}

	/**
	 * @return the outHdrNrsOfSamples
	 */
	public Long[] getOutHdrNrsOfSamples() {
		return outHdrNrsOfSamples;
	}

	/**
	 * @param outHdrNrsOfSamples the outHdrNrsOfSamples to set
	 */
	public void setOutHdrNrsOfSamples(Long[] outHdrNrsOfSamples) {
		this.outHdrNrsOfSamples = outHdrNrsOfSamples;
	}

	/**
	 * @return the outHdrSamplingFrequency
	 */
	public Double getOutHdrSamplingFrequency() {
		return outHdrSamplingFrequency;
	}

	/**
	 * @param outHdrSamplingFrequency the outHdrSamplingFrequency to set
	 */
	public void setOutHdrSamplingFrequency(Double outHdrSamplingFrequency) {
		this.outHdrSamplingFrequency = outHdrSamplingFrequency;
	}

	/**
	 * @return the outHdrSamplingFrequencies
	 */
	public Double[] getOutHdrSamplingFrequencies() {
		return outHdrSamplingFrequencies;
	}

	/**
	 * @param outHdrSamplingFrequencies the outHdrSamplingFrequencies to set
	 */
	public void setOutHdrSamplingFrequencies(Double[] outHdrSamplingFrequencies) {
		this.outHdrSamplingFrequencies = outHdrSamplingFrequencies;
	}

	/**
	 * @return the outHdrLabelsOfChannels
	 */
	public String[] getOutHdrLabelsOfChannels() {
		return outHdrLabelsOfChannels;
	}

	/**
	 * @param outHdrLabelsOfChannels the outHdrLabelsOfChannels to set
	 */
	public void setOutHdrLabelsOfChannels(String[] outHdrLabelsOfChannels) {
		this.outHdrLabelsOfChannels = outHdrLabelsOfChannels;
	}

	/**
	 * @return the outHdrTypesOfChannels
	 */
	public String[] getOutHdrTypesOfChannels() {
		return outHdrTypesOfChannels;
	}

	/**
	 * @param outHdrTypesOfChannels the outHdrTypesOfChannels to set
	 */
	public void setOutHdrTypesOfChannels(String[] outHdrTypesOfChannels) {
		this.outHdrTypesOfChannels = outHdrTypesOfChannels;
	}

	/**
	 * @return the outHdrScaled
	 */
	public Integer getOutHdrScaled() {
		return outHdrScaled;
	}

	/**
	 * @param outHdrScaled the outHdrScaled to set
	 */
	public void setOutHdrScaled(Integer outHdrScaled) {
		this.outHdrScaled = outHdrScaled;
	}

	/**
	 * @return the outHdrScalingGain
	 */
	public Double getOutHdrScalingGain() {
		return outHdrScalingGain;
	}

	/**
	 * @param outHdrScalingGain the outHdrScalingGain to set
	 */
	public void setOutHdrScalingGain(Double outHdrScalingGain) {
		this.outHdrScalingGain = outHdrScalingGain;
	}

	/**
	 * @return the outHdrScalingGains
	 */
	public Double[] getOutHdrScalingGains() {
		return outHdrScalingGains;
	}

	/**
	 * @param outHdrScalingGains the outHdrScalingGains to set
	 */
	public void setOutHdrScalingGains(Double[] outHdrScalingGains) {
		this.outHdrScalingGains = outHdrScalingGains;
	}

	/**
	 * @return the outHdrScalingOffset
	 */
	public Double getOutHdrScalingOffset() {
		return outHdrScalingOffset;
	}

	/**
	 * @param outHdrScalingOffset the outHdrScalingOffset to set
	 */
	public void setOutHdrScalingOffset(Double outHdrScalingOffset) {
		this.outHdrScalingOffset = outHdrScalingOffset;
	}

	/**
	 * @return the outHdrScalingOffsets
	 */
	public Double[] getOutHdrScalingOffsets() {
		return outHdrScalingOffsets;
	}

	/**
	 * @param outHdrScalingOffsets the outHdrScalingOffsets to set
	 */
	public void setOutHdrScalingOffsets(Double[] outHdrScalingOffsets) {
		this.outHdrScalingOffsets = outHdrScalingOffsets;
	}

	/**
	 * @return the outHdrCalibrationGain
	 */
	public Double getOutHdrCalibrationGain() {
		return outHdrCalibrationGain;
	}

	/**
	 * @param outHdrCalibrationGain the outHdrCalibrationGain to set
	 */
	public void setOutHdrCalibrationGain(Double outHdrCalibrationGain) {
		this.outHdrCalibrationGain = outHdrCalibrationGain;
	}

	/**
	 * @return the outHdrCalibrationGains
	 */
	public Double[] getOutHdrCalibrationGains() {
		return outHdrCalibrationGains;
	}

	/**
	 * @param outHdrCalibrationGains the outHdrCalibrationGains to set
	 */
	public void setOutHdrCalibrationGains(Double[] outHdrCalibrationGains) {
		this.outHdrCalibrationGains = outHdrCalibrationGains;
	}

	/**
	 * @return the outHdrCalibrationOffset
	 */
	public Double getOutHdrCalibrationOffset() {
		return outHdrCalibrationOffset;
	}

	/**
	 * @param outHdrCalibrationOffset the outHdrCalibrationOffset to set
	 */
	public void setOutHdrCalibrationOffset(Double outHdrCalibrationOffset) {
		this.outHdrCalibrationOffset = outHdrCalibrationOffset;
	}

	/**
	 * @return the outHdrCalibrationOffsets
	 */
	public Double[] getOutHdrCalibrationOffsets() {
		return outHdrCalibrationOffsets;
	}

	/**
	 * @param outHdrCalibrationOffsets the outHdrCalibrationOffsets to set
	 */
	public void setOutHdrCalibrationOffsets(Double[] outHdrCalibrationOffsets) {
		this.outHdrCalibrationOffsets = outHdrCalibrationOffsets;
	}

	/**
	 * @return the outHdrCalibrationUnits
	 */
	public String[] getOutHdrCalibrationUnits() {
		return outHdrCalibrationUnits;
	}

	/**
	 * @param outHdrCalibrationUnits the outHdrCalibrationUnits to set
	 */
	public void setOutHdrCalibrationUnits(String[] outHdrCalibrationUnits) {
		this.outHdrCalibrationUnits = outHdrCalibrationUnits;
	}

	/**
	 * @return the outHdrFileCharset
	 */
	public String getOutHdrFileCharset() {
		return outHdrFileCharset;
	}

	/**
	 * @param outHdrFileCharset the outHdrFileCharset to set
	 */
	public void setOutHdrFileCharset(String outHdrFileCharset) {
		this.outHdrFileCharset = outHdrFileCharset;
	}

	/**
	 * @return the outHdrFileLineDelimiter
	 */
	public String getOutHdrFileLineDelimiter() {
		return outHdrFileLineDelimiter;
	}

	/**
	 * @param outHdrFileLineDelimiter the outHdrFileLineDelimiter to set
	 */
	public void setOutHdrFileLineDelimiter(String outHdrFileLineDelimiter) {
		this.outHdrFileLineDelimiter = outHdrFileLineDelimiter;
	}

	/**
	 * @return the howMuchErrorsFound
	 */
	public int getHowMuchErrorsFound() {
		return howMuchErrorsFound;
	}

	/**
	 * @param howMuchErrorsFound the howMuchErrorsFound to set
	 */
	public void setHowMuchErrorsFound(int howMuchErrorsFound) {
		this.howMuchErrorsFound = howMuchErrorsFound;
	}

	/**
	 * @return the rememberedErrorsValueShouldBe
	 */
	public float[] getRememberedErrorsValueShouldBe() {
		return rememberedErrorsValueShouldBe;
	}

	/**
	 * @param rememberedErrorsValueShouldBe the rememberedErrorsValueShouldBe to set
	 */
	public void setRememberedErrorsValueShouldBe(
			float[] rememberedErrorsValueShouldBe) {
		this.rememberedErrorsValueShouldBe = rememberedErrorsValueShouldBe;
	}

	/**
	 * @return the rememberedErrorsValueThereIs
	 */
	public float[] getRememberedErrorsValueThereIs() {
		return rememberedErrorsValueThereIs;
	}

	/**
	 * @param rememberedErrorsValueThereIs the rememberedErrorsValueThereIs to set
	 */
	public void setRememberedErrorsValueThereIs(float[] rememberedErrorsValueThereIs) {
		this.rememberedErrorsValueThereIs = rememberedErrorsValueThereIs;
	}

	/**
	 * @return the inDtaChannelSet
	 */
	public ChannelSet getInDtaChannelSet() {
		return inDtaChannelSet;
	}

	/**
	 * @param inDtaChannelSet the inDtaChannelSet to set
	 */
	public void setInDtaChannelSet(ChannelSet inDtaChannelSet) {
		this.inDtaChannelSet = inDtaChannelSet;
	}

	/**
	 * @return the inDtaChannelSetNrOfChannels
	 */
	public int getInDtaChannelSetNrOfChannels() {
		return inDtaChannelSetNrOfChannels;
	}

	/**
	 * @param inDtaChannelSetNrOfChannels the inDtaChannelSetNrOfChannels to set
	 */
	public void setInDtaChannelSetNrOfChannels(int inDtaChannelSetNrOfChannels) {
		this.inDtaChannelSetNrOfChannels = inDtaChannelSetNrOfChannels;
	}

	/**
	 * @return the outDtaFis
	 */
	public FileInputStream getOutDtaFis() {
		return outDtaFis;
	}

	/**
	 * @param outDtaFis the outDtaFis to set
	 */
	public void setOutDtaFis(FileInputStream outDtaFis) {
		this.outDtaFis = outDtaFis;
	}

	/**
	 * @return the outDtaFisChannel
	 */
	public FileChannel getOutDtaFisChannel() {
		return outDtaFisChannel;
	}

	/**
	 * @param outDtaFisChannel the outDtaFisChannel to set
	 */
	public void setOutDtaFisChannel(FileChannel outDtaFisChannel) {
		this.outDtaFisChannel = outDtaFisChannel;
	}

	/**
	 * @return the outDtaFisNrOfChannels
	 */
	public int getOutDtaFisNrOfChannels() {
		return outDtaFisNrOfChannels;
	}

	/**
	 * @param outDtaFisNrOfChannels the outDtaFisNrOfChannels to set
	 */
	public void setOutDtaFisNrOfChannels(int outDtaFisNrOfChannels) {
		this.outDtaFisNrOfChannels = outDtaFisNrOfChannels;
	}

	/**
	 * @return the outDtaFisOffset
	 */
	public long getOutDtaFisOffset() {
		return outDtaFisOffset;
	}

	/**
	 * @param outDtaFisOffset the outDtaFisOffset to set
	 */
	public void setOutDtaFisOffset(long outDtaFisOffset) {
		this.outDtaFisOffset = outDtaFisOffset;
	}

	/**
	 * @return the outDtaFisSize
	 */
	public long getOutDtaFisSize() {
		return outDtaFisSize;
	}

	/**
	 * @param outDtaFisSize the outDtaFisSize to set
	 */
	public void setOutDtaFisSize(long outDtaFisSize) {
		this.outDtaFisSize = outDtaFisSize;
	}

	/**
	 * @return the codec
	 */
	public Signalml getCodec() {
		return codec;
	}

	/**
	 * @param codec the codec to set
	 */
	public void setCodec(Signalml codec) {
		this.codec = codec;
	}

	/**
	 * @return the outHdrNameNrOfSamples
	 */
	public static String getOutHdrNameNrOfSamples() {
		return outHdrNameNrOfSamples;
	}

	/**
	 * @return the outHdrNameNrOfChannels
	 */
	public static String getOutHdrNameNrOfChannels() {
		return outHdrNameNrOfChannels;
	}

	/**
	 * @return the outHdrNameSamplingFrequency
	 */
	public static String getOutHdrNameSamplingFrequency() {
		return outHdrNameSamplingFrequency;
	}

	/**
	 * @return the outHdrNameLabelsOfChannels
	 */
	public static String getOutHdrNameLabelsOfChannels() {
		return outHdrNameLabelsOfChannels;
	}

	/**
	 * @return the outHdrNameTypesOfChannels
	 */
	public static String getOutHdrNameTypesOfChannels() {
		return outHdrNameTypesOfChannels;
	}

	/**
	 * @return the outHdrNameScaled
	 */
	public static String getOutHdrNameScaled() {
		return outHdrNameScaled;
	}

	/**
	 * @return the outHdrNameScalingGain
	 */
	public static String getOutHdrNameScalingGain() {
		return outHdrNameScalingGain;
	}

	/**
	 * @return the outHdrNameScalingOffset
	 */
	public static String getOutHdrNameScalingOffset() {
		return outHdrNameScalingOffset;
	}

	/**
	 * @return the outHdrNameCalibrationGain
	 */
	public static String getOutHdrNameCalibrationGain() {
		return outHdrNameCalibrationGain;
	}

	/**
	 * @return the outHdrNameCalibrationOffset
	 */
	public static String getOutHdrNameCalibrationOffset() {
		return outHdrNameCalibrationOffset;
	}

	/**
	 * @return the outHdrNameCalibrationUnits
	 */
	public static String getOutHdrNameCalibrationUnits() {
		return outHdrNameCalibrationUnits;
	}

	/**
	 * @return the outHdrFileBigBufSize
	 */
	public int getOutHdrFileBigBufSize() {
		return outHdrFileBigBufSize;
	}

	/**
	 * @return the outHdrFileSmallBufSize
	 */
	public int getOutHdrFileSmallBufSize() {
		return outHdrFileSmallBufSize;
	}

	/**
	 * @return the howMuchErrorsToRemember
	 */
	public int getHowMuchErrorsToRemember() {
		return howMuchErrorsToRemember;
	}

	/**
	 * @return the outDtaFisLengthNrOfChannels
	 */
	public int getOutDtaFisLengthNrOfChannels() {
		return outDtaFisLengthNrOfChannels;
	}

	/**
	 * @return the outDtaFisLengthNrOfSamplesPerChannel
	 */
	public int getOutDtaFisLengthNrOfSamplesPerChannel() {
		return outDtaFisLengthNrOfSamplesPerChannel;
	}

	/**
	 * @return the outDtaFisLengthSampleUnitValue
	 */
	public int getOutDtaFisLengthSampleUnitValue() {
		return outDtaFisLengthSampleUnitValue;
	}

	/**
	 * @return the precisionFloat
	 */
	public static float getPrecisionRelFloat() {
		return precisionRelFloat;
	}

	/**
	 * @return the precisionDouble
	 */
	public static double getPrecisionRelDouble() {
		return precisionRelDouble;
	}

	/**
	 * @return the precisionFloat
	 */
	public static float getPrecisionDeltaFloat() {
		return precisionDeltaFloat;
	}

	/**
	 * @return the precisionDouble
	 */
	public static double getPrecisionDeltaDouble() {
		return precisionDeltaDouble;
	}

	/**
	 * @return the precisionFloat
	 */
	public static int getPrecisionUlpFloat() {
		return precisionUlpFloat;
	}

	/**
	 * @return the precisionDouble
	 */
	public static long getPrecisionUlpDouble() {
		return precisionUlpDouble;
	}

	/**
	 * readFileUsingParts
	 *
	 * @param fileName
	 * @param bigBufSize
	 * @param smallBufSize
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static final byte[] readFileUsingParts(String fileName, int bigBufSize, int smallBufSize)
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

	/**
	 * readTextFileUsingParts
	 *
	 * @param fileName
	 * @param charset
	 * @param lineDelimiter
	 * @param bigBufSize
	 * @param smallBufSize
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static final String[] readTextFileUsingParts(String fileName, String charset, String lineDelimiter,
			int bigBufSize, int smallBufSize) throws FileNotFoundException, IOException {
		byte[] buffer = CheckCodec.readFileUsingParts(fileName, bigBufSize, smallBufSize);
		String bufStr = new String(buffer, charset);
		String[] lines = bufStr.split(lineDelimiter, 0);
		return lines;
	}

	/**
	 * getValueOfItemFromLine
	 *
	 * @param line
	 * @param lineNr
	 * @param item
	 * @return
	 */
	public static final String getValueOfItemFromLine(String line, int lineNr, String item) {
		int valueIdx = line.indexOf("=", item.length());
		if (valueIdx == -1) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! Can not find '=' character at the line #"
				+ lineNr + ": " + line);
			return null;
		}
		String valueStr = line.substring(valueIdx + 1).trim();
		return valueStr;
	}

	/**
	 * getValuesOfItemFromLine
	 *
	 * @param line
	 * @param lineNr
	 * @param item
	 * @return
	 */
	public static final String[] getValuesOfItemFromLine(String line, int lineNr, String item) {
		String valueStr = getValueOfItemFromLine(line, lineNr, item);
		if (valueStr == null) return null;
		String[] valueArrayStr = valueStr.replaceAll("[\t ]+", "").split(",", -1);
		return valueArrayStr;
	}

	/**
	 * convertValueFromStringToSpecifiedType
	 *
	 * @param fileName
	 * @param lineNr
	 * @param item
	 * @param valueStr
	 * @param type
	 * @return
	 */
	public static final Object convertValueFromStringToSpecifiedType(String fileName, int lineNr,
			String item, String valueStr, Object type) {
		try {
			Object value = null;
			if (type instanceof String) {
				value = valueStr;
			} else if (type instanceof Long) {
				value = new Long(Long.parseLong(valueStr, 10));
			} else if (type instanceof Integer) {
				value = new Integer(Integer.parseInt(valueStr, 10));
			} else if (type instanceof Double) {
				value = new Double(Double.parseDouble(valueStr));
			} else if (type instanceof Float) {
				value = new Float(Float.parseFloat(valueStr));
			} else {
				if (CheckCodec.LOG_ERROR) System.out.println("Failed during convertion: value for " + item
					+ " item cannot have not supported type (" + type.getClass().getName() + ")");
				return null;
			}
			if (CheckCodec.LOG_DEBUG) System.out.println("Value for " + item + " item is"
				+ " (" + type.getClass().getName() + ")" + valueStr);
			return value;
		} catch (NumberFormatException ex) {
			if (CheckCodec.LOG_ERROR) System.out.println("Wrong format of the file " + fileName + ": line #"
				+ lineNr + " contains value which is not a " + type.getClass().getName() + " number, but "
				+ item + " item wants it!");
			return null;
		}
	}

	/**
	 * convertValuesFromStringToSpecifiedType
	 *
	 * @param fileName
	 * @param lineNr
	 * @param item
	 * @param valueArrayStr
	 * @param type
	 * @return
	 */
	public static final Object convertValuesFromStringToSpecifiedType(String fileName, int lineNr,
			String item, String[] valueArrayStr, Object type) {
		if (type instanceof String) {
			return valueArrayStr;
		} else if (type instanceof Long) {
			Long[] typeArray = new Long[0];
			ArrayList<Long> arrayList = new ArrayList<Long>();
			for (int j = 0; j < valueArrayStr.length; j++) {
				String singleValueStr = valueArrayStr[j];
				Long singleValue = (Long) convertValueFromStringToSpecifiedType(fileName, lineNr,
					item, singleValueStr, new Long(-1L));
				if (singleValue == null) return null;
				arrayList.add(singleValue);
			}
			return arrayList.toArray(typeArray);
		} else if (type instanceof Integer) {
			Integer[] typeArray = new Integer[0];
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for (int j = 0; j < valueArrayStr.length; j++) {
				String singleValueStr = valueArrayStr[j];
				Integer singleValue = (Integer) convertValueFromStringToSpecifiedType(fileName, lineNr,
					item, singleValueStr, new Integer(-1));
				if (singleValue == null) return null;
				arrayList.add(singleValue);
			}
			return arrayList.toArray(typeArray);
		} else if (type instanceof Double) {
			Double[] typeArray = new Double[0];
			ArrayList<Double> arrayList = new ArrayList<Double>();
			for (int j = 0; j < valueArrayStr.length; j++) {
				String singleValueStr = valueArrayStr[j];
				Double singleValue = (Double) convertValueFromStringToSpecifiedType(fileName, lineNr,
					item, singleValueStr, new Double(-1.0D));
				if (singleValue == null) return null;
				arrayList.add(singleValue);
			}
			return arrayList.toArray(typeArray);
		} else if (type instanceof Float) {
			Float[] typeArray = new Float[0];
			ArrayList<Float> arrayList = new ArrayList<Float>();
			for (int j = 0; j < valueArrayStr.length; j++) {
				String singleValueStr = valueArrayStr[j];
				Float singleValue = (Float) convertValueFromStringToSpecifiedType(fileName, lineNr,
					item, singleValueStr, new Float(-1.0F));
				if (singleValue == null) return null;
				arrayList.add(singleValue);
			}
			return arrayList.toArray(typeArray);
		} else {
			if (CheckCodec.LOG_ERROR) System.out.println("Failed during convertion: values for " + item
				+ " item cannot have not supported type (" + type.getClass().getName() + ")");
			return null;
		}
	}

	/**
	 * convertValueOrValuesFromStringToSpecifiedType
	 *
	 * @param fileName
	 * @param lineNr
	 * @param item
	 * @param valueStr
	 * @param type
	 * @return
	 */
	public static final Object convertValueOrValuesFromStringToSpecifiedType(String fileName, int lineNr,
			String item, String valueStr, Object type) {
		String[] valueArrayStr = valueStr.replaceAll("[\t ]+", "").split(",", -1);
		if (valueArrayStr.length < 2) {
			return convertValueFromStringToSpecifiedType(fileName, lineNr, item, valueStr, type);
		} else {
			return convertValuesFromStringToSpecifiedType(fileName, lineNr, item, valueArrayStr, type);
		}
	}

	/**
	 * getValueOfItemFromLineAsSpecifiedType
	 *
	 * @param fileName
	 * @param line
	 * @param lineNr
	 * @param item
	 * @param type
	 * @return
	 */
	public static final Object getValueOfItemFromLineAsSpecifiedType(String fileName, String line, int lineNr,
			String item, Object type) {
		String valueStr = getValueOfItemFromLine(line, lineNr, item);
		if (valueStr == null) return null;
		return convertValueFromStringToSpecifiedType(fileName, lineNr, item, valueStr, type);
	}

	/**
	 * getValuesOfItemFromLineAsSpecifiedType
	 *
	 * @param fileName
	 * @param line
	 * @param lineNr
	 * @param item
	 * @param type
	 * @return
	 */
	public static final Object getValuesOfItemFromLineAsSpecifiedType(String fileName, String line, int lineNr,
			String item, Object type) {
		String[] valueArrayStr = getValuesOfItemFromLine(line, lineNr, item);
		if (valueArrayStr == null) return null;
		return convertValuesFromStringToSpecifiedType(fileName, lineNr, item, valueArrayStr, type);
	}

	/**
	 * getValueOrValuesOfItemFromLineAsSpecifiedType
	 *
	 * @param fileName
	 * @param line
	 * @param lineNr
	 * @param item
	 * @param type
	 * @return
	 */
	public static final Object getValueOrValuesOfItemFromLineAsSpecifiedType(String fileName, String line, int lineNr,
			String item, Object type) {
		String valueStr = getValueOfItemFromLine(line, lineNr, item);
		if (valueStr == null) return null;
		return convertValueOrValuesFromStringToSpecifiedType(fileName, lineNr, item, valueStr, type);
	}

	/**
	 * greaterThanUsingMaxDelta
	 *
	 * @param left
	 * @param right
	 * @param delta
	 * @return
	 */
	public static final boolean greaterThanUsingMaxDelta(float left, float right, float delta) {
		boolean result = left - right > delta;
		return result;
	}

	/**
	 * greaterThanUsingMaxDelta
	 *
	 * @param left
	 * @param right
	 * @param delta
	 * @return
	 */
	public static final boolean greaterThanUsingMaxDelta(double left, double right, double delta) {
		boolean result = left - right > delta;
		return result;
	}

	/**
	 * lesserThanUsingMinDelta
	 *
	 * @param left
	 * @param right
	 * @param delta
	 * @return
	 */
	public static final boolean lesserThanUsingMinDelta(float left, float right, float delta) {
		boolean result = left - right < delta;
		return result;
	}

	/**
	 * lesserThanUsingMinDelta
	 *
	 * @param left
	 * @param right
	 * @param delta
	 * @return
	 */
	public static final boolean lesserThanUsingMinDelta(double left, double right, double delta) {
		boolean result = left - right < delta;
		return result;
	}

	/**
	 * equalToUsingULP
	 *
	 * ULP -- unit in the last place (unit of least precision) is the spacing between floating-point numbers
	 * If x has exponent E, then ULP(x) = machine epsilon * x * 2^E
	 *
	 * @param left
	 * @param right
	 * @param precision, i.e. 5
	 * @return
	 */
	public static final boolean equalToUsingULP(float left, float right, int precision) {
		if (left == right) return true;
		if (left < 0 && right > 0 || left > 0 && right < 0 || left == 0 || right == 0) return false;
		int leftInt = Float.floatToIntBits(left);
		int rightInt = Float.floatToIntBits(right);
		int absSub = Math.abs(leftInt - rightInt);
		boolean result = false;
		if (precision > 5 && precision < 8) {
			result = absSub < ((precision == 6) ? 125 : 10);
			return result;
		}
		//int comparisonResult = Float.compare(left, right);
		double logAbsSub = Math.log10(absSub);
		if (logAbsSub == 0.0f) return true;
		double floorLogAbsSub = Math.floor(logAbsSub);
		double restLogAbsSub = logAbsSub - floorLogAbsSub;
		//double powFloorLogAbsSub = Math.pow(10.d, floorLogAbsSub);
		double powRestLogAbsSub = Math.pow(10.d, restLogAbsSub);
		double floorPowRestLogAbsSub = Math.floor(powRestLogAbsSub);
		int unbiasedExponentAbsSub = Math.getExponent(absSub);
		//float min = Math.min(left, right);
		//float max = Math.max(left, right);
		result = precision > floorPowRestLogAbsSub - unbiasedExponentAbsSub && precision < powRestLogAbsSub;
		return result;
	}

	/**
	 * equalToUsingULP
	 *
	 * @param left
	 * @param right
	 * @param precision, i.e. 7
	 * @return
	 */
	public static final boolean equalToUsingULP(double left, double right, long precision) {
		if (left == right) return true;
		if (left < 0 && right > 0 || left > 0 && right < 0 || left == 0 || right == 0) return false;
		long leftInt = Double.doubleToLongBits(left);
		long rightInt = Double.doubleToLongBits(right);
		long absSub = Math.abs(leftInt - rightInt);
		boolean result = false;
		if (precision > 5 && precision < 8) {
			result = absSub < ((precision == 6) ? 125 : 10);
			return result;
		}
		//int comparisonResult = Double.compare(left, right);
		double logAbsSub = Math.log10(absSub);
		if (logAbsSub == 0.0f) return true;
		double floorLogAbsSub = Math.floor(logAbsSub);
		double restLogAbsSub = logAbsSub - floorLogAbsSub;
		//double powFloorLogAbsSub = Math.pow(10.d, floorLogAbsSub);
		double powRestLogAbsSub = Math.pow(10.d, restLogAbsSub);
		double floorPowRestLogAbsSub = Math.floor(powRestLogAbsSub);
		long unbiasedExponentAbsSub = Math.getExponent(absSub);
		//double min = Math.min(left, right);
		//double max = Math.max(left, right);
		result = precision > floorPowRestLogAbsSub - unbiasedExponentAbsSub && precision < powRestLogAbsSub;
		return result;
	}

	/**
	 * equalToUsingMaxDelta
	 *
	 * @param left
	 * @param right
	 * @param maxDelta, i.e. 0.0001
	 * @return
	 */
	public static final boolean equalToUsingMaxDelta(float left, float right, float maxDelta) {
		boolean result = Math.abs(left - right) <= maxDelta;
		return result;
	}

	/**
	 * equalToUsingMaxDelta
	 *
	 * @param left
	 * @param right
	 * @param maxDelta, i.e. 0.00000001
	 * @return
	 */
	public static final boolean equalToUsingMaxDelta(double left, double right, double maxDelta) {
		boolean result = Math.abs(left - right) <= maxDelta;
		return result;
	}

	/**
	 * equalToUsingRelative
	 *
	 * @param left
	 * @param right
	 * @return
	 */
	public static final boolean equalToUsingRelative(float left, float right) {
		float gain = FLP_EPSILON;
		boolean result = equalToUsingRelative(left, right, gain);
		return result;
	}

	/**
	 * equalToUsingRelative
	 *
	 * @param left
	 * @param right
	 * @param gain, i.e. FLP_EPSILON
	 * @return
	 */
	public static final boolean equalToUsingRelative(float left, float right, float gain) {
		// Calculate the difference.
		float diff = Math.abs(left - right);
		float a = Math.abs(left);
		float b = Math.abs(right);
		// Find the largest
		float largest = Math.max(a,b);
		// Set result
		boolean result = (diff <= largest * gain);
		return result;
	}

	/**
	 * equalToUsingRelative
	 *
	 * @param left
	 * @param right
	 * @return
	 */
	public static final boolean equalToUsingRelative(double left, double right) {
		double gain = DBL_EPSILON;
		boolean result = equalToUsingRelative(left, right, gain);
		return result;
	}

	/**
	 * equalToUsingRelative
	 *
	 * @param left
	 * @param right
	 * @param gain, i.e. DBL_EPSILON
	 * @return
	 */
	public static final boolean equalToUsingRelative(double left, double right, double gain) {
		// Calculate the difference.
		double diff = Math.abs(left - right);
		double a = Math.abs(left);
		double b = Math.abs(right);
		// Find the largest
		double largest = Math.max(a,b);
		// Set result
		boolean result = (diff <= largest * gain);
		return result;
	}

	/**
	 * calculateMachineEpsilonFloat
	 *
	 * @return  Probably it will be FLP_EPSILON = POW(2.0F, -23.0F) = 1.19209290E-07F
	 */
	public static final float calculateMachineEpsilonFloat() {
		float machEps = 1.0f;
		do {
			machEps /= 2.0f;
		}
		while ((float)(1.0f + (machEps/2.0f)) != 1.0f);
		return machEps;
	}

	/**
	 * calculateMachineEpsilonDouble
	 *
	 * @return  Probably it will be DBL_EPSILON = POW(2.0D, -52.0D) = 2.2204460492503131E-16F
	 */
	public static final double calculateMachineEpsilonDouble() {
		double machEps = 1.0d;
		do {
			machEps /= 2.0d;
		}
		while ((float)(1.0d + (machEps/2.0d)) != 1.0d);
		return machEps;
	}

	/**
	 * equalToCombined
	 *
	 * @param a
	 * @param b
	 * @param maxRelDiff
	 * @param maxDelta
	 * @param maxUlpsDiff
	 * @return
	 */
	public static final boolean equalToCombined(float a, float b, float maxRelDiff, float maxDelta, int maxUlpsDiff) {
		boolean equal = false;
		equal = equalToUsingRelative(a, b, maxRelDiff);
		if (equal) return true;
		equal = equalToUsingMaxDelta(a, b, maxDelta);
		if (equal) return true;
		equal = equalToUsingULP(a, b, maxUlpsDiff);
		return equal;
	}

	/**
	 * equalToCombined
	 *
	 * @param a
	 * @param b
	 * @param maxRelDiff
	 * @param maxDelta
	 * @param maxUlpsDiff
	 * @return
	 */
	public static final boolean equalToCombined(double a, double b, double maxRelDiff, double maxDelta, long maxUlpsDiff) {
		boolean equal = false;
		equal = equalToUsingRelative(a, b, maxRelDiff);
		if (equal) return true;
		equal = equalToUsingMaxDelta(a, b, maxDelta);
		if (equal) return true;
		equal = equalToUsingULP(a, b, maxUlpsDiff);
		return equal;
	}

	/**
	 * logFileOperationException
	 *
	 * @param e
	 * @param fileName
	 * @param prefix
	 * @param suffix
	 */
	public static final void logFileOperationException(Exception e, String fileName, String prefix, String suffix) {
		if (CheckCodec.LOG_ERROR) {
			System.out.println("Error! " + prefix + " file " + fileName + ". " + suffix);
			e.printStackTrace();
		}
	}

	/**
	 * getAttributesFromOutHdr
	 *
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public final boolean getAttributesFromOutHdr(String fileName) throws FileNotFoundException, IOException {
		if (CheckCodec.LOG_INFO) System.out.println("Reading the output header file " + fileName);
		String[] outHdrLines = CheckCodec.readTextFileUsingParts(fileName, outHdrFileCharset, outHdrFileLineDelimiter,
			outHdrFileBigBufSize, outHdrFileSmallBufSize);
		if (CheckCodec.LOG_INFO) System.out.println("File " + fileName + " has been read out successfully to the buffer.");
		if (CheckCodec.LOG_INFO) System.out.println("Parsing content of the buffer...");
		for (int i = 0; i < outHdrLines.length; i++) {
			String line = outHdrLines[i].trim();
			if (line.startsWith("#")) {
				//this is comment => skip line
			} else if (line.trim().length() < 1) {
				//empty => skip line
			} else if (line.startsWith(CheckCodec.outHdrNameNrOfSamples)) {
				Object o = getValueOrValuesOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameNrOfSamples, new Long(Long.MIN_VALUE));
				if (o == null) return false;
				else if (o instanceof Long[]) outHdrNrsOfSamples = (Long[]) o;
				else outHdrNrOfSamples = (Long) o;
			} else if (line.startsWith(CheckCodec.outHdrNameNrOfChannels)) {
				Object o = getValueOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameNrOfChannels, new Integer(Integer.MIN_VALUE));
				if (o == null) return false;
				else outHdrNrOfChannels = (Integer) o;
			} else if (line.startsWith(CheckCodec.outHdrNameSamplingFrequency)) {
				Object o = getValueOrValuesOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameSamplingFrequency, new Double(Double.NaN));
				if (o == null) return false;
				else if (o instanceof Double[]) outHdrSamplingFrequencies = (Double[]) o;
				else outHdrSamplingFrequency = (Double) o;
			} else if (line.startsWith(CheckCodec.outHdrNameLabelsOfChannels)) {
				Object o = getValuesOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameLabelsOfChannels, new String());
				if (o == null) return false;
				else outHdrLabelsOfChannels = (String[]) o;
			} else if (line.startsWith(CheckCodec.outHdrNameTypesOfChannels)) {
				Object o = (String[]) getValuesOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameTypesOfChannels, new String());
				if (o == null) return false;
				else outHdrTypesOfChannels = (String[]) o;
			} else if (line.startsWith(CheckCodec.outHdrNameScaled)) {
				Object o = getValueOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameScaled, new Integer(Integer.MIN_VALUE));
				if (o == null) return false;
				else outHdrScaled = (Integer) o;
			} else if (line.startsWith(CheckCodec.outHdrNameScalingGain)) {
				Object o = getValueOrValuesOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameScalingGain, new Double(Double.NaN));
				if (o == null) return false;
				else if (o instanceof Double[]) outHdrScalingGains = (Double[]) o;
				else outHdrScalingGain = (Double) o;
			} else if (line.startsWith(CheckCodec.outHdrNameScalingOffset)) {
				Object o = getValueOrValuesOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameScalingOffset, new Double(Double.NaN));
				if (o == null) return false;
				else if (o instanceof Double[]) outHdrScalingOffsets = (Double[]) o;
				else outHdrScalingOffset = (Double) o;
			} else if (line.startsWith(CheckCodec.outHdrNameCalibrationGain)) {
				Object o = getValueOrValuesOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameCalibrationGain, new Double(Double.NaN));
				if (o == null) return false;
				else if (o instanceof Double[]) outHdrCalibrationGains = (Double[]) o;
				else outHdrCalibrationGain = (Double) o;
			} else if (line.startsWith(CheckCodec.outHdrNameCalibrationOffset)) {
				Object o = getValueOrValuesOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameCalibrationOffset, new Double(Double.NaN));
				if (o == null) return false;
				else if (o instanceof Double[]) outHdrCalibrationOffsets = (Double[]) o;
				else outHdrCalibrationOffset = (Double) o;
			} else if (line.startsWith(CheckCodec.outHdrNameCalibrationUnits)) {
				Object o = getValuesOfItemFromLineAsSpecifiedType(fileName, line, i,
					CheckCodec.outHdrNameCalibrationUnits, new String());
				if (o == null) return false;
				else outHdrCalibrationUnits = (String[]) o;
			} else {
				if (CheckCodec.LOG_WARNING) System.out.println("Warning! Unknown line format: " + line);
			}
		}
		return true;
	}

	/**
	 * checkAttributesFromOutHdr
	 *
	 * @return
	 */
	public final boolean checkAttributesFromOutHdr() {
		if (outHdrNrOfChannels == null) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! There was no specified value with number of channels" +
				" in the output header file. Please add it there!");
			return false;
		}
		if (outHdrLabelsOfChannels == null) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! There was no specified value with label of channel per every" +
				" individual channel in the output header file. Please add it there!");
			return false;
		}
		if (outHdrTypesOfChannels == null) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! There was no specified value with type of channel" +
				" per every individual channel in the output header file. Please add it there!");
			return false;
		}
		if (outHdrNrOfSamples == null && outHdrNrsOfSamples.length == 0) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! There was no specified value with number of sample units per all" +
				" or per every individual channel in the output header file. Please add it there!");
			return false;
		}
		if (outHdrNrsOfSamples.length > 0 && outHdrNrOfChannels.longValue() != outHdrNrsOfSamples.length) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! Number of channels specified in output header file was different in two places: "
				+ outHdrNrOfChannels + " in line dedicated to it and " + outHdrNrsOfSamples.length
				+ " in line with the list of the numbers of samples. Please correct it!");
			return false;
		}
		if (outHdrSamplingFrequency == null && outHdrSamplingFrequencies.length == 0) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! There was no specified value with sampling frequency per all"
				+ "or per every individual channel in the output header file. Please add it there!");
			return false;
		}
		if (outHdrSamplingFrequencies.length > 0 && outHdrNrOfChannels.longValue() != outHdrSamplingFrequencies.length) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! Number of channels specified in output header file was different in two places: "
				+ outHdrNrOfChannels + " in line dedicated to it and " + outHdrSamplingFrequencies.length
				+ " in line with the list of sampling freqiencies. Please correct it!");
			return false;
		}
		if (outHdrScaled == null) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! There was no specified value determining if samples were scaled. " +
				"Please add line scaled=0 or line scaled=1 to the .hdr file!");
			return false;
		}
		return true;
	}

	/**
	 * checkSampleUnitAtPos
	 *
	 * @param outDtaFisSampleUnitValue
	 * @param inDtaChannelObject
	 * @param channelNr
	 * @param sampleUnitNr
	 * @param sampleUnitNrInCurrentBuffer
	 * @param testDeltaForSample
	 * @return
	 */
	public final boolean checkSampleUnitAtPos(final float outDtaFisSampleUnitValue, final Channel inDtaChannelObject,
			final int channelNr, final int sampleUnitNr, final int sampleUnitNrInCurrentBuffer,
			final float testPrecisionRelForSample, final float testPrecisionDeltaForSample, final int testPrecisionUlpForSample) {
		if (CheckCodec.LOG_DEBUG) System.out.print(" " + outDtaFisSampleUnitValue); //System.out.format(" %.3f", outDtaFisSampleUnitValue);
		int fullSampleNr = sampleUnitNr + sampleUnitNrInCurrentBuffer;
		float inDtaCodecSampleUnitValue = inDtaChannelObject.getSample(fullSampleNr);
		boolean error = !(equalToCombined(inDtaCodecSampleUnitValue, outDtaFisSampleUnitValue,
			testPrecisionRelForSample, testPrecisionDeltaForSample, testPrecisionUlpForSample));
		if (error) {
			if (howMuchErrorsFound < howMuchErrorsToRemember) {
				rememberedErrorsValueShouldBe[howMuchErrorsFound] = outDtaFisSampleUnitValue;
				rememberedErrorsValueThereIs[howMuchErrorsFound] = inDtaCodecSampleUnitValue;
				if (CheckCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: ERROR. Details: Value of the sample #" + fullSampleNr
					+ " of the channel #" + channelNr + " taken from the codec (" + inDtaCodecSampleUnitValue + ")"
					+ " differs from that specified in output file .float (" + outDtaFisSampleUnitValue + ").");
			}
			//increment offset -- if only we break it, but my last decision is not to break the flow
			//continue samplesBig; //break channels; //return;
			howMuchErrorsFound ++;
		//} else {
		//	if (howMuchErrorsFound < howMuchErrorsToRemember) {
		//		if (CheckCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: OK. Details: Value of the sample #" + fullSampleNr
		//			+ " of the channel #" + channelNr + " taken from the codec (" + inDtaCodecSampleUnitValue + ")"
		//			+ "is equal to that specified in output file .float (" + outDtaFisSampleUnitValue + ").");
		//	}
		}
		return error;
	}

	/**
	 * checkSampleUnitsAtPos
	 *
	 * @param outDtaFisChannel
	 * @param outDtaFisOffset
	 * @param bytesToRead
	 * @param outDtaFisLengthSampleUnitValue
	 * @param verificationMultiplyFactor
	 * @param inDtaChannelObject
	 * @param channelNr
	 * @param sampleUnitNr
	 * @param testDeltaForSample
	 * @throws IOException
	 */
	public final void checkSampleUnitsAtPos(final FileChannel outDtaFisChannel, final long outDtaFisOffset, final long bytesToRead,
			final int outDtaFisLengthSampleUnitValue, final float verificationMultiplyFactor, final Channel inDtaChannelObject,
			final int channelNr, final int sampleUnitNr,
			final float testPrecisionRelForSample, final float testPrecisionDeltaForSample, final int testPrecisionUlpForSample)
			throws IOException {
		MappedByteBuffer outDtaFisMapBuffer = outDtaFisChannel.map(FileChannel.MapMode.READ_ONLY, outDtaFisOffset, bytesToRead);
		int sampleUnitPosInCurrentBuffer = 0;
		int sampleUnitNrInCurrentBuffer = 0;
		/*samplesSmall:*/
		for (; sampleUnitPosInCurrentBuffer < bytesToRead; sampleUnitNrInCurrentBuffer ++, sampleUnitPosInCurrentBuffer += outDtaFisLengthSampleUnitValue) {
			final float outDtaFisSampleUnitValue = outDtaFisMapBuffer.getFloat((int) sampleUnitPosInCurrentBuffer) * verificationMultiplyFactor;
			checkSampleUnitAtPos(outDtaFisSampleUnitValue, inDtaChannelObject, channelNr, sampleUnitNr,
				sampleUnitNrInCurrentBuffer, testPrecisionRelForSample, testPrecisionDeltaForSample, testPrecisionUlpForSample);
		}
	}

	/**
	 * checkSamples
	 *
	 * @param gotoNextChannel
	 * @param gotoNextSamplePortionBig
	 * @param outDtaFisOffsetStart
	 * @param outDtaFisLengthSampleUnitValue
	 * @param outDtaFisNrOfSamplesPerChannel
	 * @param outDtaFisChannel
	 * @param verificationMultiplyFactor
	 * @param inDtaChannelObject
	 * @param channelNr
	 * @param testDeltaForSample
	 * @return
	 * @throws IOException
	 */
	public final boolean checkSamples(boolean gotoNextChannel, boolean gotoNextSamplePortionBig,
			final long outDtaFisOffsetStart, final int outDtaFisLengthSampleUnitValue,
			final long outDtaFisNrOfSamplesPerChannel, final FileChannel outDtaFisChannel,
			final float verificationMultiplyFactor, final Channel inDtaChannelObject, final int channelNr,
			final float testPrecisionRelForSample, final float testPrecisionDeltaForSample, final int testPrecisionUlpForSample)
			throws IOException {
		long samplesBytesAlreadyRead = outDtaFisOffset - outDtaFisOffsetStart;
		long samplesAlreadyRead = samplesBytesAlreadyRead / outDtaFisLengthSampleUnitValue;
		long samplesToReadAllRest = outDtaFisNrOfSamplesPerChannel - samplesAlreadyRead;
		final long samplesToReadMax = 4096;
		long samplesToRead = Math.min(samplesToReadMax, samplesToReadAllRest);
		long bytesToRead = samplesToRead * outDtaFisLengthSampleUnitValue;
		/*samplesBig:*/
		for (int sampleUnitNr = 0; sampleUnitNr < outDtaFisNrOfSamplesPerChannel; ) {
			samplesBytesAlreadyRead = outDtaFisOffset - outDtaFisOffsetStart;
			samplesAlreadyRead = samplesBytesAlreadyRead / outDtaFisLengthSampleUnitValue;
			samplesToReadAllRest = outDtaFisNrOfSamplesPerChannel - samplesAlreadyRead;
			samplesToRead = Math.min(samplesToReadMax, samplesToReadAllRest);
			bytesToRead = samplesToRead * outDtaFisLengthSampleUnitValue;
			if (gotoNextChannel == false && gotoNextSamplePortionBig == false) {
				checkSampleUnitsAtPos(outDtaFisChannel, outDtaFisOffset, bytesToRead,
					outDtaFisLengthSampleUnitValue, verificationMultiplyFactor, inDtaChannelObject, channelNr, sampleUnitNr,
					testPrecisionRelForSample, testPrecisionDeltaForSample, testPrecisionUlpForSample);
			}
			outDtaFisOffset += bytesToRead;
			sampleUnitNr += samplesToRead;
			if (gotoNextChannel == true) {
				gotoNextChannel = false;
				return gotoNextChannel; //continue channels;
			}
		}
		if (CheckCodec.LOG_INFO) System.out.println();
		return gotoNextChannel;
	}

	/**
	 * checkChannel
	 *
	 * @param outDtaFisOffsetStart
	 * @param outDtaFisLengthSampleUnitValue
	 * @param outDtaFisNrOfSamplesPerChannel
	 * @param outDtaFisChannel
	 * @param verificationMultiplyFactor
	 * @param inDtaChannelObject
	 * @param channelNr
	 * @param testDeltaForSample
	 * @param testDeltaForSamplingFrequency
	 * @throws IOException
	 */
	public final void checkChannel(final long outDtaFisOffsetStart, final int outDtaFisLengthSampleUnitValue,
			final long outDtaFisNrOfSamplesPerChannel, final FileChannel outDtaFisChannel,
			final float verificationMultiplyFactor, final Channel inDtaChannelObject, final int channelNr,
			final float testPrecisionRelForSample, final double testPrecisionRelForSamplingFrequency,
			final float testPrecisionDeltaForSample, final double testPrecisionDeltaForSamplingFrequency,
			final int testPrecisionUlpForSample, final long testPrecisionUlpForSamplingFrequency)
			throws IOException {
		long inDtaChannelObjectNumberOfSamplesPerChannel = inDtaChannelObject.getNumberOfSamples();
		String inDtaChannelObjectChannelName = inDtaChannelObject.getChannelName();
		String inDtaChannelObjectChannelTypeName = inDtaChannelObject.getChannelTypeName();
		double inDtaChannelObjectSamplingFrequency = inDtaChannelObject.getSamplingFrequency();
		boolean gotoNextChannel = false;
		boolean gotoNextSamplePortionBig = false;
		//boolean gotoNextSamplePortionSmall = false;
		//comparing header info
		if (!inDtaChannelObjectChannelName.equals(outHdrLabelsOfChannels[channelNr])) {
			if (CheckCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: " + "WARNING"/* + "ERROR"*/ + ". Details: Label or name of the channel #"
				+ channelNr + " get from codec (" + inDtaChannelObjectChannelName
				+ ") differs from that specified in output file .hdr (" + outHdrLabelsOfChannels[channelNr] + ").");
			////TODO increment offset
			//break channels; //return;
		}
		if (!inDtaChannelObjectChannelTypeName.equals(outHdrTypesOfChannels[channelNr])) {
			if (CheckCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: " + "WARNING"/* + "ERROR"*/ + ". Details: Type of the channel #"
				+ channelNr + " get from codec (" + inDtaChannelObjectChannelTypeName
				+ ") differs from that specified in output file .hdr (" + outHdrTypesOfChannels[channelNr] + ").");
			////TODO increment offset
			//break channels; //return;
		}
		double outHdrSamplingFrequencyPerChannel = ((outHdrSamplingFrequency == null) ? outHdrSamplingFrequencies[channelNr] : outHdrSamplingFrequency);
		if (!equalToCombined(inDtaChannelObjectSamplingFrequency, outHdrSamplingFrequencyPerChannel,
				testPrecisionRelForSamplingFrequency, testPrecisionDeltaForSamplingFrequency, testPrecisionUlpForSamplingFrequency)) {
			if (CheckCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: " + "WARNING"/* + "ERROR"*/ + ". Details: Sampling frequency of the channel #"
				+ channelNr + " get from codec (" + inDtaChannelObjectSamplingFrequency + ") differs from that specified in output file .hdr ("
				+ outHdrSamplingFrequencyPerChannel + ").");
			////TODO increment offset
			//break channels; //return;
		}
		if (inDtaChannelObjectNumberOfSamplesPerChannel != outDtaFisNrOfSamplesPerChannel) {
			if (CheckCodec.LOG_TEST) System.out.println("\nCODEC TEST RESULT: " + "WARNING"/* + "ERROR"*/ + ". Details: Number of samples per channel #"
				+ channelNr + " get from codec (" + inDtaChannelObjectNumberOfSamplesPerChannel + ") differs from that specified in output file .hdr ("
				+ outDtaFisNrOfSamplesPerChannel + ").");
			//TODO increment offset
			gotoNextChannel = true; //continue channels; //return;
		}
		//comparing data info
		gotoNextChannel = checkSamples(gotoNextChannel, gotoNextSamplePortionBig, outDtaFisOffsetStart,
			outDtaFisLengthSampleUnitValue, outDtaFisNrOfSamplesPerChannel, outDtaFisChannel,
			verificationMultiplyFactor, inDtaChannelObject, channelNr,
			testPrecisionRelForSample, testPrecisionDeltaForSample, testPrecisionUlpForSample);
	}

	/**
	 * checkChannels
	 *
	 * @return
	 * @throws IOException
	 */
	public final boolean checkChannels() throws IOException {
		final float verificationMultiplyFactor = 1.0f;
		return checkChannels(verificationMultiplyFactor);
	}

	/**
	 * checkChannels
	 *
	 * @param verificationMultiplyFactor
	 * @return
	 * @throws IOException
	 */
	public final boolean checkChannels(final float verificationMultiplyFactor) throws IOException {
		final float testPrecisionRelForSample = precisionRelFloat;              //FLT_EPSILON
		final double testPrecisionRelForSamplingFrequency = precisionRelDouble;  //DBL_EPSILON
		boolean result = checkChannels(verificationMultiplyFactor, testPrecisionRelForSample, testPrecisionRelForSamplingFrequency);
		return result;
	}

	/**
	 * checkChannels
	 *
	 * @param verificationMultiplyFactor
	 * @param testPrecisionRelForSample
	 * @param testPrecisionRelForSamplingFrequency
	 * @return
	 * @throws IOException
	 */
	public final boolean checkChannels(final float verificationMultiplyFactor,
			final float testPrecisionRelForSample, final double testPrecisionRelForSamplingFrequency)
			throws IOException {
		final float testPrecisionDeltaForSample = precisionDeltaFloat;
		final double testPrecisionDeltaForSamplingFrequency = precisionDeltaDouble;
		boolean result = checkChannels(verificationMultiplyFactor,
				testPrecisionRelForSample, testPrecisionRelForSamplingFrequency,
				testPrecisionDeltaForSample, testPrecisionDeltaForSamplingFrequency);
		return result;
	}

	/**
	 * checkChannels
	 *
	 * @param verificationMultiplyFactor
	 * @param testPrecisionRelForSample
	 * @param testPrecisionRelForSamplingFrequency
	 * @param testPrecisionDeltaForSample
	 * @param testPrecisionDeltaForSamplingFrequency
	 * @return
	 * @throws IOException
	 */
	public final boolean checkChannels(final float verificationMultiplyFactor,
			final float testPrecisionRelForSample, final double testPrecisionRelForSamplingFrequency,
			final float testPrecisionDeltaForSample, final double testPrecisionDeltaForSamplingFrequency)
			throws IOException {
		final int testPrecisionUlpForSample = precisionUlpFloat;
		final long testPrecisionUlpForSamplingFrequency = precisionUlpDouble;
		boolean result = checkChannels(verificationMultiplyFactor,
				testPrecisionRelForSample, testPrecisionRelForSamplingFrequency,
				testPrecisionDeltaForSample, testPrecisionDeltaForSamplingFrequency,
				testPrecisionUlpForSample, testPrecisionUlpForSamplingFrequency);
		return result;
	}

	/**
	 * checkChannels
	 *
	 * @param verificationMultiplyFactor
	 * @param testPrecisionRelForSample
	 * @param testPrecisionRelForSamplingFrequency
	 * @param testPrecisionDeltaForSample
	 * @param testPrecisionDeltaForSamplingFrequency
	 * @param testPrecisionUlpForSample
	 * @param testPrecisionUlpForSamplingFrequency
	 * @return
	 * @throws IOException
	 */
	public final boolean checkChannels(final float verificationMultiplyFactor,
			final float testPrecisionRelForSample, final double testPrecisionRelForSamplingFrequency,
			final float testPrecisionDeltaForSample, final double testPrecisionDeltaForSamplingFrequency,
			final int testPrecisionUlpForSample, final long testPrecisionUlpForSamplingFrequency)
			throws IOException {
		final int startAtLeastFromChannel = Integer.MIN_VALUE;
		final int finishAtMostBeforeChannel = Integer.MAX_VALUE;
		boolean result = checkChannels(verificationMultiplyFactor,
			testPrecisionRelForSample, testPrecisionRelForSamplingFrequency,
			testPrecisionDeltaForSample, testPrecisionDeltaForSamplingFrequency,
			testPrecisionUlpForSample, testPrecisionUlpForSamplingFrequency,
			startAtLeastFromChannel, finishAtMostBeforeChannel);
		return result;
	}

	/**
	 * checkChannels
	 *
	 * @param verificationMultiplyFactor
	 * @param testPrecisionRelForSample
	 * @param testPrecisionRelForSamplingFrequency
	 * @param testPrecisionDeltaForSample
	 * @param testPrecisionDeltaForSamplingFrequency
	 * @param testPrecisionUlpForSample
	 * @param testPrecisionUlpForSamplingFrequency
	 * @param startAtLeastFromChannel
	 * @param finishAtMostBeforeChannel
	 * @return
	 * @throws IOException
	 */
	public final boolean checkChannels(final float verificationMultiplyFactor,
			final float testPrecisionRelForSample, final double testPrecisionRelForSamplingFrequency,
			final float testPrecisionDeltaForSample, final double testPrecisionDeltaForSamplingFrequency,
			final int testPrecisionUlpForSample, final long testPrecisionUlpForSamplingFrequency,
			final int startAtLeastFromChannel, final int finishAtMostBeforeChannel)
			throws IOException {
		/*channels:*/
		int nrOfChannelsMin = Math.min(Math.min(outDtaFisNrOfChannels, (int) outHdrNrOfChannels.longValue()), inDtaChannelSetNrOfChannels);
		final int chStart = Math.max(0, startAtLeastFromChannel);
		final int chFinish = Math.min(nrOfChannelsMin, finishAtMostBeforeChannel);
		for (int channelNr = chStart; channelNr < chFinish && outDtaFisOffset < outDtaFisSize; channelNr ++) {
			//info from output
			if (CheckCodec.LOG_INFO) System.out.print("Channel " + channelNr);
			MappedByteBuffer outDtaFisMapBuffer = outDtaFisChannel.map(FileChannel.MapMode.READ_ONLY,
				outDtaFisOffset, outDtaFisLengthNrOfSamplesPerChannel);
			final long outDtaFisNrOfSamplesPerChannel = outDtaFisMapBuffer.getLong(0);
			if (CheckCodec.LOG_INFO) System.out.print(" has " + outDtaFisNrOfSamplesPerChannel + " sample units in output data file");
			if (outHdrNrOfSamples != null) {
				if (outDtaFisNrOfSamplesPerChannel != outHdrNrOfSamples) {
					if (CheckCodec.LOG_ERROR) System.out.println("\nError! In output header file there were specified "
						+ outHdrNrOfSamples + " sample units for every channel. "
						+ "Output data file has other value: " + outDtaFisNrOfSamplesPerChannel + ". "
						+ "Please correct .hdr file or change output data file used!");
					return false;
				}
			} else {
				if (outDtaFisNrOfSamplesPerChannel != outHdrNrsOfSamples[channelNr]) {
					if (CheckCodec.LOG_WARNING) System.out.println("\nWarning! In output header file there were specified "
						+ outHdrNrsOfSamples[channelNr] + " sample units for channel #" + channelNr + ". "
						+ "Output data file has other value: " + outDtaFisNrOfSamplesPerChannel + "!");
				}
			}
			if (outDtaFisNrOfSamplesPerChannel * outDtaFisLengthSampleUnitValue >= outDtaFisSize) {
				if (CheckCodec.LOG_ERROR) System.out.println("\nError! File has bad format, too big number of samples specified!");
				return false;
			}
			outDtaFisOffset += outDtaFisLengthNrOfSamplesPerChannel;
			long outDtaFisOffsetStart = outDtaFisOffset;
			//info from input
			Channel inDtaChannelObject = inDtaChannelSet.getChannel(channelNr);
			checkChannel(outDtaFisOffsetStart, outDtaFisLengthSampleUnitValue,
				outDtaFisNrOfSamplesPerChannel, outDtaFisChannel,
				verificationMultiplyFactor, inDtaChannelObject, channelNr,
				testPrecisionRelForSample, testPrecisionRelForSamplingFrequency,
				testPrecisionDeltaForSample, testPrecisionDeltaForSamplingFrequency,
				testPrecisionUlpForSample, testPrecisionUlpForSamplingFrequency);
			//continue with next channel
		}
		return true;
	}

	/**
	 * checkInDtaAccess
	 *
	 * @return
	 */
	public final boolean checkInDtaAccess() {
		/*long inDtaChannelSetNrOfSamples = */inDtaChannelSet.getNumberOfSamples(); //@Deprecated, please take that info from the channel
		/*double inDtaChannelSetSamplingFrequency = */inDtaChannelSet.getSamplingFrequency(); //@Deprecated, please take that info from the channel
		for (int channelNr = 0; channelNr < inDtaChannelSetNrOfChannels; channelNr ++) {
			Channel inDtaChannelObject = inDtaChannelSet.getChannel(channelNr);
			long inDtaChannelObjectNumberOfSamplesPerChannel = inDtaChannelObject.getNumberOfSamples();
			/*String inDtaChannelObjectChannelName = /*inDtaChannelObject.getChannelName();
			/*double inDtaChannelObjectSamplingFrequency = */inDtaChannelObject.getSamplingFrequency();
			for (long sampleNr = 0; sampleNr < inDtaChannelObjectNumberOfSamplesPerChannel; sampleNr ++) {
				try {
					float sampleUnitValue = inDtaChannelObject.getSample(sampleNr);
					if (CheckCodec.LOG_DEBUG) System.out.println(" " + sampleUnitValue); //System.out.format(" %.3f", sampleUnitValue);
				} catch (Throwable t) {
					System.out.println("Critical error during getting sample " + sampleNr + " of the channel " + channelNr);
					t.printStackTrace();
					return false;
				}
				//this is only for test; far far below will be made sth similar to this above
			}
		}
		return true;
	}

	/**
	 * getInDta
	 *
	 * @param fileName
	 * @param useContextDumper
	 * @param useInDtaChecking
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public final boolean getInDta(String fileName, boolean useContextDumper, boolean useInDtaChecking)
			throws FileNotFoundException, IOException {
		File inDtaFile = new File(fileName);
		long inDtaFileLength = inDtaFile.length();
		//long inDtaFileFragmentSize = Math.min(200240, inDtaFileLength);
		if (CheckCodec.LOG_INFO) {
			System.out.println("Reading the input header/data file " + fileName);
			System.out.println("Length of the file is " + inDtaFileLength + " bytes");
	//		System.out.println("Reading fragment [0, " + inDtaFileFragmentSize + "] bytes from that file");
		}
		codec.open(inDtaFile);
		codec.createParams();
	//	reader.setRange(0, inDtaFileFragmentSize);
		codec.createChannels();
		if (useContextDumper) {
			String allCodecDataDumped = ContextDumper.dump(codec); //it takes too much time
			System.out.print(allCodecDataDumped);
		}
		int inDtaNumberOfChannelSets = codec.getNumberOfChannelSets(); //@Theory, do not know if this is fully supported
		if (CheckCodec.LOG_INFO) {
			System.out.println("Number of channel sets: " + inDtaNumberOfChannelSets);
		}
		inDtaChannelSet = codec.get_set();
		inDtaChannelSetNrOfChannels = inDtaChannelSet.getNumberOfChannels();
		if (CheckCodec.LOG_INFO) {
			System.out.println("Number of channels: " + inDtaChannelSetNrOfChannels);
		}
		if (useInDtaChecking) {
			boolean inDtaAccessStatus = checkInDtaAccess(); //it takes quite much time
			if (!inDtaAccessStatus) return false;
		}
		//TODO show on the screen (optionally -- for INFO log only) some informations from the codec (from above)
		//...
		return true;
	}

	/**
	 * getOutDtaHdr
	 *
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public final boolean getOutDtaHdr(String fileName) throws FileNotFoundException, IOException {
		boolean outHdrSuccessfulyTaken = getAttributesFromOutHdr(fileName);
		if (!outHdrSuccessfulyTaken) return false;
		boolean outHdrAttribsStatus = checkAttributesFromOutHdr();
		if (!outHdrAttribsStatus) return false;
		return true;
	}

	/**
	 * getOutDtaBody
	 *
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public final boolean getOutDtaBody(String fileName, boolean exitOnNrsOfChannelsError) throws IOException {
		if (CheckCodec.LOG_INFO) System.out.println("Reading and parsing the output data file: " + fileName);
		if (!fileName.endsWith(".float")) {
			if (CheckCodec.LOG_WARNING) System.out.println("Warning! File does not have the .float extension!");
		}
		outDtaFis = new FileInputStream(fileName);
		outDtaFisChannel = outDtaFis.getChannel();
		final long outDtaFisOffsetNrOfChannels = 0;
		MappedByteBuffer outDtaFisMapBuffer = outDtaFisChannel.map(FileChannel.MapMode.READ_ONLY,
			outDtaFisOffsetNrOfChannels, outDtaFisLengthNrOfChannels);
		outDtaFisSize = outDtaFisChannel.size();
		if (CheckCodec.LOG_INFO) System.out.println("File size: " + outDtaFisSize + " bytes = " + (outDtaFisSize/1024f)
			+ " kB = " + (outDtaFisSize/1048576f) + " MB = " + (outDtaFisSize/1073741824f) + " GB");

		outDtaFisNrOfChannels = outDtaFisMapBuffer.getInt(0);
		if (CheckCodec.LOG_INFO) System.out.println("Nr of channels found: " + outDtaFisNrOfChannels);
		if (outDtaFisNrOfChannels * outDtaFisLengthNrOfSamplesPerChannel >= outDtaFisSize) {
			if (CheckCodec.LOG_ERROR) System.out.println("Error! File has bad format, too big number of channels specified!");
			return false;
		}
		if (outDtaFisNrOfChannels != outHdrNrOfChannels.longValue()) {
			if (CheckCodec.LOG_ERROR) System.out.println("Warning! Number of channels specified in output header file (.hdr)"
				+ " differs from that specified in output data file (.float). Please correct .hdr file or use another .float file!");
			if (exitOnNrsOfChannelsError) return false;
			////outHdrNrOfChannels = inDtaChannelSetNrOfChannels;
		}
		if (inDtaChannelSetNrOfChannels != outDtaFisNrOfChannels) {
			if (CheckCodec.LOG_TEST) System.out.println("CODEC TEST RESULT: ERROR. Details: Number of channels get from codec ("
				+ inDtaChannelSetNrOfChannels + ") differs from that specified in output files .hdr and/or .float ("
				+ outDtaFisNrOfChannels + ").");
			if (exitOnNrsOfChannelsError) return false;
			////outDtaFisNrOfChannels = inDtaChannelSetNrOfChannels;
		}
		outDtaFisOffset = outDtaFisOffsetNrOfChannels + outDtaFisLengthNrOfChannels;
		return true;
	}

	/**
	 * getOutDta
	 *
	 * @param dataHeaderFileName
	 * @param dataBodyFileName
	 * @return
	 * @throws IOException
	 */
	public final boolean getOutDta(String dataHeaderFileName, String dataBodyFileName,
			boolean exitOnNrsOfChannelsError) throws IOException {
		//firstly read output header file (.hdr)
		boolean outDtaHdrStatus = getOutDtaHdr(dataHeaderFileName);
		if (!outDtaHdrStatus) return false;
		//secondly read output body file (.float)
		boolean outDtaBodyStatus = getOutDtaBody(dataBodyFileName, exitOnNrsOfChannelsError);
		if (!outDtaBodyStatus) return false;
		return true;
	}

	/**
	 * close
	 */
	public final void close() {
		if (outDtaFis != null) {
			try {
				outDtaFis.close();
			} catch (IOException e) {
				logFileOperationException(e, "", "I/O error during closing output data", "");
			}
		}
	}

	/**
	 * main
	 *
	 * @param args
	 */
	public static void main(java.lang.String[] args) {
		if (args.length < 4) {
			System.out.println("Using:\njava " + CheckCodec.class.getSimpleName() + " input_header input_data output_header.hdr output_data.float");
			return;
		}
		String      codecName = args[0]; //String  inHdrFileName = args[0]; //codec to use (ALT, EASYS, M4D, New4D, New4DAscii, TextFile, XML, ...)
		String  inDtaFileName = args[1]; //d:/grst/projects/UW/data/for_EASYS_codec/inb02.d                          //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.m4d
		String outHdrFileName = args[2]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.hdr   //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.hdr
		String outDtaFileName = args[3]; //d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.float //d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.float
		final boolean useContextDumper = false; //true;
		final boolean usePreCheckingOfTheDataFromCodec = false;
		final boolean exitOnNrsOfChannelsError = false;
		final float testPrecisionRelForSample = CheckCodec.precisionRelFloat;
		final double testPrecisionRelForSamplingFrequency = CheckCodec.precisionRelDouble;
		final float testPrecisionDeltaForSample = CheckCodec.precisionDeltaFloat;
		final double testPrecisionDeltaForSamplingFrequency = CheckCodec.precisionDeltaDouble;
		final int testPrecisionUlpForSample = CheckCodec.precisionUlpFloat;
		final long testPrecisionUlpForSamplingFrequency = CheckCodec.precisionUlpDouble;
		final int startAtLeastFromChannel = 0; //78;
		final int finishAtMostBeforeChannel = 80;
		float verificationMultiplyFactor = 1.0f;
		if (args.length > 4) {
			try {
				verificationMultiplyFactor = Float.parseFloat(args[4]); //i.e. 20 for scaled, no or 1 for unscaled
			} catch (NumberFormatException nfe) {
				if (CheckCodec.LOG_ERROR) System.out.println("Error! Wrong 5th parameter: '" + args[4] +
					"'. Instead of those value pelase use value for verification multiply factor, i.e. 1.0f or 20 or other float numeric value.");
				return;
			}
		}
		Signalml reader = null; //TODO please make abstract method getSignalml() and superclass of the CheckCodec will give us there codec EASYS or M4D or ...
		try {
			reader = (Signalml) Class.forName(codecName).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return;
		}
		CheckCodec checkCodec = new CheckCodec(reader);
		String fileName = null; //name of the file currently read -- taken separately because of possible exception occurs
		try {
			//giving input to codec and getting result from it
			fileName = inDtaFileName;
			boolean inDtaStatus = checkCodec.getInDta(inDtaFileName, useContextDumper, usePreCheckingOfTheDataFromCodec);
			if (!inDtaStatus) return;
			//read output header file (.hdr) and output body file (.float)
			fileName = outHdrFileName + " (or) " + outDtaFileName;
			boolean outDtaStatus = checkCodec.getOutDta(outHdrFileName, outDtaFileName, exitOnNrsOfChannelsError);
			if (!outDtaStatus) return;
			//then compare everything (outBody with outHdr and with in)
			fileName = "(unknown)";
			boolean verificationStatus = checkCodec.checkChannels(verificationMultiplyFactor,
				testPrecisionRelForSample, testPrecisionRelForSamplingFrequency,
				testPrecisionDeltaForSample, testPrecisionDeltaForSamplingFrequency,
				testPrecisionUlpForSample, testPrecisionUlpForSamplingFrequency,
				startAtLeastFromChannel, finishAtMostBeforeChannel);
			if (!verificationStatus) return;
		} catch (FileNotFoundException e) {
			CheckCodec.logFileOperationException(e, fileName, "Cannot find a", "Check command-line arguments to the running program.");
		} catch (IOException e) {
			CheckCodec.logFileOperationException(e, fileName, "I/O error during reading the", "Please verify that it is a proper file.");
		} finally {
			checkCodec.close();
		}
	}

}

