package jsignalml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import jsignalml.logging.Logger;

/* 'My' so it doesn't collide with java.nio.whatever . */
public class MyBuffer {
	protected static final Logger log = new Logger(MyBuffer.class);
	final public java.nio.ByteBuffer source;
	final java.io.File filename;

	public static final ByteOrder byteorder = ByteOrder.LITTLE_ENDIAN;

	public MyBuffer(File name)
		throws java.io.FileNotFoundException, java.io.IOException
	{
		log.info("opening binary file %s", name);
		this.filename = name;

		FileInputStream file = new FileInputStream(name);
		FileChannel channel = file.getChannel();
		this.source = channel.map(MapMode.READ_ONLY, 0, channel.size());

		log.info("setting byteorder %s", MyBuffer.byteorder);
		source.order(byteorder);
	}

	public int getLimit() {
		return source.limit();
	}
	
	public Type read(BitForm format, TypeInt byteoffset) {
		return format.read(this.source, byteoffset);
	}

	public File getFilename() {
		return this.filename;
	}

	public void close() {
		// TODO
	}

	public static MyBuffer open(File filename)
	{
		try {
			return new MyBuffer(filename);
		} catch (FileNotFoundException e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		} catch (IOException e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		}
	}
}
