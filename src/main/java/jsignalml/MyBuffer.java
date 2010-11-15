package jsignalml;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.io.File;

/* 'My' so it doesn't collide with java.nio.whatever . */
public class MyBuffer {
	protected static final Logger log = new Logger(MyBuffer.class);
	final java.nio.ByteBuffer source;
	final java.io.File filename;

	public MyBuffer(File name)
		throws java.io.FileNotFoundException, java.io.IOException
	{
		log.info("opening binary file %s", name);
		RandomAccessFile file = new RandomAccessFile(name, "r");
		FileChannel channel = file.getChannel();
		source = channel.map(MapMode.READ_ONLY, 0, channel.size());
		this.filename = name;
	}

	public Type read(BitForm format, int byteoffset) {
		return format.read(this.source, byteoffset);
	}

	public File getFilename() {
		return this.filename;
	}

	public void close() {
		// TODO
	}
}
