package org.signalml.jsignalml;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

import org.signalml.jsignalml.sexpression.Type;

public abstract class FileType {
    static final Logger log = new Logger(FileType.class);

    public static class BinaryFile extends FileType {
	final MyBuffer buffer;

	public BinaryFile(File filename)
	    throws IOException, FileNotFoundException
	{
	    log.info("opening buffer for %s", filename);
	    buffer = new MyBuffer(filename);
	}

	public Type read(BitForm format, int offset)
	{
	    return this.buffer.read(format, offset);
	}
    }

    public static <T extends FileType>
    T open(Class<T> klass, File filename)
	throws IOException, FileNotFoundException
    {
	// if(klass TODO
	return (T) new BinaryFile(filename);
    }
}
