package org.signalml.jsignalml;

import org.signalml.jsignalml.sexpression.Type;

public abstract class FileType {
    static final Logger log = new Logger(FileType.class);

    public final String filename;
    public FileType(String filename){
	this.filename = filename;
    }

    public abstract Type read(BitForm format, int offset);
}
