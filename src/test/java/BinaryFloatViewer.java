import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Grzegorz Stadnik
 *
 */
public class BinaryFloatViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Using:\njava " + BinaryFloatViewer.class.getSimpleName() + " output_data.float");
			return;
		}
		String outDtaFileName = args[0];
		//for example argument can have value similar to that presented below:
		// d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.float
		// d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.float
		try {
			final int outDtaFisLengthNrOfChannels = 4;           //one int   fits in 4 bytes
			final int outDtaFisLengthNrOfSamplesPerChannel = 8;  //one long  fits in 8 bytes
			final int outDtaFisLengthSampleUnitValue = 4;        //one float fits in 4 bytes
			System.out.println("Reading file: " + outDtaFileName);
			if (!outDtaFileName.endsWith(".float")) {
				System.out.println("Warning! File does not have .float extension!");
			}
			FileInputStream outDtaFis = new FileInputStream(outDtaFileName);
			FileChannel outDtaFisChannel = outDtaFis.getChannel();
			final long outDtaFisOffsetNrOfChannels = 0;
			MappedByteBuffer outDtaFisMapBuffer = outDtaFisChannel.map(FileChannel.MapMode.READ_ONLY, outDtaFisOffsetNrOfChannels, outDtaFisLengthNrOfChannels);
			final long outDtaFisSize = outDtaFisChannel.size();
			System.out.println("File size: " + outDtaFisSize + " bytes = " + (outDtaFisSize/1024f) + " kB = " + (outDtaFisSize/1048576f) + " MB = " + (outDtaFisSize/1073741824f) + " GB");
			final int outDtaFisNrOfChannels = outDtaFisMapBuffer.getInt(0);
			System.out.println("Nr of channels found: " + outDtaFisNrOfChannels);
			if (outDtaFisNrOfChannels * outDtaFisLengthNrOfSamplesPerChannel >= outDtaFisSize) {
				System.out.println("Error! File has bad format, too big number of channels specified!");
				return;
			}
			long outDtaFisOffset = outDtaFisOffsetNrOfChannels + outDtaFisLengthNrOfChannels;
			for (int channelNr = 0; channelNr < outDtaFisNrOfChannels && outDtaFisOffset < outDtaFisSize; channelNr ++) {
				System.out.print("Channel " + channelNr);
				outDtaFisMapBuffer = outDtaFisChannel.map(FileChannel.MapMode.READ_ONLY, outDtaFisOffset, outDtaFisLengthNrOfSamplesPerChannel);
				final long outDtaFisNrOfSamplesPerChannel = outDtaFisMapBuffer.getLong(0);
				System.out.print(" has " + outDtaFisNrOfSamplesPerChannel + " sample units:");
				if (outDtaFisNrOfSamplesPerChannel * outDtaFisLengthSampleUnitValue >= outDtaFisSize) {
					System.out.println("Error! File has bad format, too big number of samples specified!");
					return;
				}
				outDtaFisOffset += outDtaFisLengthNrOfSamplesPerChannel;
				long outDtaFisOffsetStart = outDtaFisOffset;
				for (int sampleUnitNr = 0; sampleUnitNr < outDtaFisNrOfSamplesPerChannel; ) {
					final long samplesBytesAlreadyRead = outDtaFisOffset - outDtaFisOffsetStart;
					final long samplesAlreadyRead = samplesBytesAlreadyRead / outDtaFisLengthSampleUnitValue;
					final long samplesToReadAllRest = outDtaFisNrOfSamplesPerChannel - samplesAlreadyRead;
					final long samplesToRead = Math.min(4096, samplesToReadAllRest);
					final long bytesToRead = samplesToRead * outDtaFisLengthSampleUnitValue;
					outDtaFisMapBuffer = outDtaFisChannel.map(FileChannel.MapMode.READ_ONLY, outDtaFisOffset, bytesToRead);
					/*
					for (long sampleUnitNrInCurrentBuffer = 0; sampleUnitNrInCurrentBuffer < samplesToRead; sampleUnitNrInCurrentBuffer ++) {
						final float outDtaFisSampleUnitValue = outDtaFisMapBuffer.getFloat((int) sampleUnitNrInCurrentBuffer);
						System.out.print(" " + outDtaFisSampleUnitValue); //System.out.format(" %.3f", outDtaFisSampleUnitValue);
					}
					*/
					for (long sampleUnitPosInCurrentBuffer = 0; sampleUnitPosInCurrentBuffer < bytesToRead; sampleUnitPosInCurrentBuffer += outDtaFisLengthSampleUnitValue) {
						final float outDtaFisSampleUnitValue = outDtaFisMapBuffer.getFloat((int) sampleUnitPosInCurrentBuffer);
						System.out.print(" " + outDtaFisSampleUnitValue); //System.out.format(" %.3f", outDtaFisSampleUnitValue);
					}
					outDtaFisOffset += bytesToRead;
					sampleUnitNr += samplesToRead;
				}
				System.out.println();
			}
			outDtaFis.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error! Cannot find a file " + outDtaFileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error! I/O error during reading the file " + outDtaFileName);
			e.printStackTrace();
		}
	}

}
