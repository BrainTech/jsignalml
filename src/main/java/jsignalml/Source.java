package jsignalml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Source { /* implements Iterable<> */
	// public SignalMLCodec getCodec() {

	/*
	 * Open a file in the specific format and read the header. Fail if the
	 * file is in the wrong format or broken.
	 */
	void open(File filename) throws FileNotFoundException, IOException;
	void close();

	String getFormatID();
	String getFormatDescription();

	String getFormatName();
	String getFormatProvider();
	String getFormatVersion();
	String getCodecProvider();
	String getCodecVersion();

	File getCurrentFilename();

	ChannelSet get_set(/* for one only one, so no need to specify which one,
			    * but XXX */);
}
