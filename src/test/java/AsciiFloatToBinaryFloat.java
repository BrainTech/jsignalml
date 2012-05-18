import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Grzegorz Stadnik
 *
 */
public class AsciiFloatToBinaryFloat {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//sample arguments:
		// sample (a)  d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.ascii d:/grst/projects/UW/data/for_4D_format/m4d/example_data/Art_e,rfhp0.1Hz,n,ccfbp10-40-508-2,cag,c,n,tm,bahe001-1High350,a.float
		// sample (b)  d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.ascii d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-unscaled/inb02.float
		if (args.length < 2) {
			System.out.println("Using:\njava " + AsciiFloatToBinaryFloat.class.getSimpleName() + " output_data.ascii output_data.float");
			return;
		}
		String fi = args[0];
		String fo = args[1];
		boolean skipEveryFirstValueInLine = (args.length > 2 && args[2].equalsIgnoreCase("skip"));
		int rev2 = 2 + ((skipEveryFirstValueInLine) ? 1 : 0);
		boolean reversedChannelsWithSamples = (args.length > rev2 &&
			args[rev2].equalsIgnoreCase("reversed")); //false => channels in line, next sample in next line, true => samples in line, next channel in next line
		RandomAccessFile fir = new RandomAccessFile(fi, "r");
		RandomAccessFile fow = new RandomAccessFile(fo, "rw");
		RandomAccessFile[] fows = null;
		fow.setLength(0);
		int channelNr = 0;
		int sampleNr = 0;
		int numberOfChannels = -1;
		int numberOfSamples = -1;
		int fileLinesWithSampleNr = 0;
		long[] numberOfSamplesPerChannel = null;
		boolean lineStartsWithNrOfChannels = true;
		if (reversedChannelsWithSamples) {
			//ArrayList<RandomAccessFile> fowsList = new ArrayList<RandomAccessFile>();
			fow.writeInt(numberOfChannels); //it will be overwritten when it will be known the number of channels
			for (String line = fir.readLine(); (channelNr < 100000000) && (line != null); channelNr ++, line = fir.readLine()) {
				RandomAccessFile fowTemp = new RandomAccessFile(fo + "." + (channelNr), "rw");
				fowTemp = new RandomAccessFile(fo + "." + (channelNr), "rw");
				fowTemp.setLength(0);
				String[] floatValuesStr = line.split("[ \t]+");
				numberOfSamples = floatValuesStr.length;
				fow.writeLong(numberOfSamples);
				for (sampleNr = 0; sampleNr < numberOfSamples; sampleNr ++) {
					String floatValueStr = floatValuesStr[sampleNr];
					float floatValue = Float.parseFloat(floatValueStr);
					fowTemp.writeFloat(floatValue);
					fow.writeFloat(floatValue);
				}
				//fowsList.add(fowTemp);
			}
			numberOfChannels = channelNr;
			//fows = fowsList.toArray(fows);
			fow.seek(0);
			fow.writeInt(numberOfChannels); //overwritten known number of channels
			fir.close();
			fow.close();
			System.out.println("Information found in the file " + fi);
			System.out.println("\tnumber_of_channels = " + numberOfChannels);
			System.out.println("\tnumber_of_samples = " + sampleNr);
			System.out.println("\t" + sampleNr + " x " + numberOfChannels + " = " + (sampleNr*numberOfChannels) + " float numbers");
			System.out.println("All data saved to the file " + fo);
			System.out.println("\t" + (sampleNr) + " numbers of channels x 4 bytes = " + ((sampleNr)/256f) + " kB");
			System.out.println("\t" + (sampleNr*numberOfChannels) + " float numbers x 4 bytes = " + ((sampleNr*numberOfChannels)/256f) + " kB");
			System.out.println("\tprojected file size = " + ((sampleNr*(numberOfChannels+1))/256f) + " kB = " + sampleNr*(numberOfChannels+1)*4 + " B");
			return;
		}
		for (String line = fir.readLine(); (sampleNr < 100000000) && (line != null); sampleNr ++, line = fir.readLine()) {
			String[] floatValuesStr = line.split("[ \t]+");
			if (sampleNr == 0) {
				numberOfChannels = floatValuesStr.length;
			} else if (floatValuesStr.length + fileLinesWithSampleNr != numberOfChannels) {
				System.out.println("Error! Sumarized number of all channels found in the line #" + sampleNr + " of the file " + fi + " (value " + (floatValuesStr.length + fileLinesWithSampleNr) + ") should be equal to the corresponding value from the first line (value " + numberOfChannels + ") of the same file!");
				return;
			}
			for (channelNr = 0; channelNr < floatValuesStr.length; channelNr ++) {
				String floatValueStr = floatValuesStr[channelNr];
				if (channelNr == 0 && lineStartsWithNrOfChannels) {
					try {
						int intValue = (skipEveryFirstValueInLine) ? sampleNr : Integer.parseInt(floatValueStr);
						//int found (sample number)
						fileLinesWithSampleNr = -1;
						if (sampleNr == 0) {
							numberOfChannels = floatValuesStr.length + fileLinesWithSampleNr;
						} else if (numberOfChannels != floatValuesStr.length + fileLinesWithSampleNr) {
							System.out.println("Error! Number of channels (" + (floatValuesStr.length + fileLinesWithSampleNr) + ") found at the line #" + sampleNr + " in the file " + fi + " should be equal to the number of channels (" + numberOfChannels + ") found in the first line!");
							return;
						}
						if (intValue != sampleNr) {
							System.out.println("Error! Sample number (" + intValue + ") specified at the start of the line #" + sampleNr + " in the file " + fi + " should be equal to the line number!");
							return;
						}
						if (fows == null) {
							fows = new RandomAccessFile[numberOfChannels - fileLinesWithSampleNr];
						}
						if (fows[channelNr] == null) {
							fows[channelNr] = new RandomAccessFile(fo + "." + (channelNr), "rw");
							fows[channelNr].setLength(0);
						}
						if (sampleNr == 0) {
							fow.writeInt(numberOfChannels);
						}
						continue;
					} catch (NumberFormatException nfe) {
						lineStartsWithNrOfChannels = false;
						if (fows == null) {
							fows = new RandomAccessFile[numberOfChannels - fileLinesWithSampleNr];
						}
						if (fows[channelNr] == null) {
							fows[channelNr] = new RandomAccessFile(fo + "." + (channelNr), "rw");
							fows[channelNr].setLength(0);
						}
						if (sampleNr == 0) {
							fow.writeInt(numberOfChannels);
						}
						//and go to next try
					}
				}
				try {
					float floatValue = Float.parseFloat(floatValueStr);
					//float found
					if (fows == null) {
						fows = new RandomAccessFile[numberOfChannels/* - fileLinesWithSampleNr*/];
					}
					if (fows[channelNr/* - fileLinesWithSampleNr*/] == null) {
						fows[channelNr/* - fileLinesWithSampleNr*/] = new RandomAccessFile(fo + "." + (channelNr + fileLinesWithSampleNr), "rw");
						fows[channelNr/* - fileLinesWithSampleNr*/].setLength(0);
					}
					fows[channelNr/* - fileLinesWithSampleNr*/].writeFloat(floatValue);
					if (numberOfSamplesPerChannel == null) {
						numberOfSamplesPerChannel = new long[numberOfChannels - fileLinesWithSampleNr];
						for (int channel = 0; channel < numberOfChannels - fileLinesWithSampleNr; channel ++) {
							numberOfSamplesPerChannel[channel] = 0;
						}
					}
					numberOfSamplesPerChannel[channelNr/* - fileLinesWithSampleNr*/] ++; //increment
				} catch (NumberFormatException nfe) {
					//-- found
					if (fows == null) {
						fows = new RandomAccessFile[numberOfChannels/* - fileLinesWithSampleNr*/];
					}
					if (fows[channelNr/* - fileLinesWithSampleNr*/] == null) {
						fows[channelNr/* - fileLinesWithSampleNr*/] = new RandomAccessFile(fo + "." + (channelNr + fileLinesWithSampleNr), "rw");
						fows[channelNr/* - fileLinesWithSampleNr*/].setLength(0);
					}
					fows[channelNr/* - fileLinesWithSampleNr*/].writeFloat(Float.NaN);
					if (numberOfSamplesPerChannel == null) {
						numberOfSamplesPerChannel = new long[numberOfChannels - fileLinesWithSampleNr];
						for (int channel = 0; channel < numberOfChannels - fileLinesWithSampleNr; channel ++) {
							numberOfSamplesPerChannel[channel] = 0;
						}
					}
					//do not increment  numberOfSamplesPerChannel[channelNr/* - fileLinesWithSampleNr*/] ++;
				}
				//end of the channel
			}
			//end of the sample line, we are now after last channel of that sample
		}
		for (channelNr = 0 - fileLinesWithSampleNr; channelNr < numberOfChannels - fileLinesWithSampleNr; channelNr ++) {
			////numberOfSamplesPerChannel[channelNr/* - fileLinesWithSampleNr*/] = sampleNr;
			//fows[channelNr].writeLong(numberOfSamplesPerChannel[channelNr/* - fileLinesWithSampleNr*/]);
			//fows[channelNr].close();
			//fows[channelNr] = new RandomAccessFile(fo + "." + (channelNr), "r");
			long l = numberOfSamplesPerChannel[channelNr/* - fileLinesWithSampleNr*/];
			fow.writeLong(l);
			int len = 16384;
			////int offset = 0;
			byte[] buf = new byte[len];
			fows[channelNr].seek(0);
			////fows[channelNr].readFully(buf, offset, len);
			while ((len = fows[channelNr].read(buf)) > 0) {
				fow.write(buf, 0, len);
			}
			fows[channelNr].close();
		}
		System.out.println("Information found in the file " + fi);
		System.out.println("\tnumber_of_channels = " + numberOfChannels);
		System.out.println("\tnumber_of_samples = " + sampleNr);
		System.out.println("\t" + sampleNr + " x " + numberOfChannels + " = " + (sampleNr*numberOfChannels) + " float numbers");
		System.out.println("All data saved to the file " + fo);
		System.out.println("\t" + (sampleNr) + " numbers of channels x 4 bytes = " + ((sampleNr)/256f) + " kB");
		System.out.println("\t" + (sampleNr*numberOfChannels) + " float numbers x 4 bytes = " + ((sampleNr*numberOfChannels)/256f) + " kB");
		System.out.println("\tprojected file size = " + ((sampleNr*(numberOfChannels+1))/256f) + " kB = " + sampleNr*(numberOfChannels+1)*4 + " B");
		fir.close();
		fow.close();
	}

}
