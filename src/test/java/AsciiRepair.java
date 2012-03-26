import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Grzegorz Stadnik
 *
 */
public class AsciiRepair {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		//syntax:
		// in out
		//sample arguments:
		// d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-scaled/inb02.ascii d:/grst/projects/UW/data/for_EASYS_codec/EASYS/inb02-scaled/inb02.ascii.repaired
		if (args.length < 2) {
			System.out.println("Using:\njava " + AsciiRepair.class.getSimpleName() + " output_data.ascii output_data.ascii.repaired");
			return;
		}
		String fi = args[0];
		String fo = args[1];
		RandomAccessFile fir = new RandomAccessFile(fi, "r");
		RandomAccessFile fow = new RandomAccessFile(fo, "rw");
		fow.setLength(0);
		int sampleNr = 0;
		int numberOfChannels = -1;
		int fileLinesWithSampleNr = 0;
		for (String line = fir.readLine(); (sampleNr < 100000000) && (line != null); sampleNr ++, line = fir.readLine()) {
			String[] floatValuesStr = line.split(" ");
			if (sampleNr == 0) {
				numberOfChannels = floatValuesStr.length;
			} else if (floatValuesStr.length + fileLinesWithSampleNr != numberOfChannels) {
				System.out.println("Error! Sumarized number of all channels found in the line #" + sampleNr + " of the file " + fi + " (value " + (floatValuesStr.length + fileLinesWithSampleNr) + ") should be equal to the corresponding value from the first line (value " + numberOfChannels + ") of the same file!");
				return;
			}
			if (floatValuesStr.length < 3) {
				System.out.println("Error! Unproper line #" + sampleNr + " should have three or more channel sample values, but it has " + floatValuesStr.length + "!");
				return;
			}
			if (!floatValuesStr[1].equals(floatValuesStr[2])) {
				System.out.println("Error! Unproper line #" + sampleNr + " should have channel_sample[1] == channel_sample[2], but now those values are: " + floatValuesStr[1] +  ", " + floatValuesStr[2] + "!");
				return;
			}
			for (int ch = 0; ch < floatValuesStr.length; ch++) {
				if (ch != 2) {
					fow.write(floatValuesStr[ch].getBytes());
					if (ch + 1 < floatValuesStr.length) fow.write(" ".getBytes());
					else fow.write("\n".getBytes());
				}
			}
			if (sampleNr % 10000 == 0) System.out.println((sampleNr + 1) + " lines parsed");
		}
		System.out.println("Information found in the file " + fi);
		System.out.println("\tnumber_of_channels = " + numberOfChannels);
		System.out.println("\tnumber_of_samples = " + sampleNr);
		System.out.println("Repaired data saved to the file " + fo);
		System.out.println("\tnumber_of_channels = " + --numberOfChannels);
		System.out.println("\tnumber_of_samples = " + sampleNr);
		fir.close();
		fow.close();
	}

}
