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
	    buffer = new MyBuffer(filename);
	}

	public Type read(BitForm format, int offset)
	{
	    return this.buffer.read(format, offset);
	}
    }
}
