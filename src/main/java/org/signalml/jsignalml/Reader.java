package org.signalml.jsignalml;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.signalml.jsignalml.FileType;
import org.signalml.jsignalml.sexpression.*;
import org.signalml.jsignalml.Machine.MachineError;

public class Reader extends Frame implements CallHelper {
    public static final Logger log = new Logger(Reader.class);

    final CodecyThing codec;

    /* HashMap because TreeMap would require comparable. */
    final Map<FileHandle<FileType>, FileType> files =
	new HashMap<FileHandle<FileType>, FileType>();

    final LinkedList<File> filehints = new LinkedList<File>();

    public Reader(CodecyThing codec, String...filenames){
	super(null);
	this.codec = codec;
	for(String filename: filenames)
	    this.filehints.add(new File(filename));
    }

    @Override
    public <T extends FileType> T getFile(FileHandle<T> handle)
	throws ExpressionFault, MachineError,
	       IOException, FileNotFoundException
    {
	log.info("request for %s", handle);
	T file = (T) this.files.get(handle);
	if(file != null)
	    return file;

	log.info("attempting to open %s", handle);
	file = handle.open(this, this.filehints.poll());
	this.files.put((FileHandle<FileType>) handle, (FileType) file);
			//XXX WTF?
	return file;
    }

    @Override
    public Type frame_call(String id, Type...args)
	throws ExpressionFault, FrameNameError,
	       IOException, FileNotFoundException
    {
	Machine.Param p;
	try{
	    p = codec.getParam(id);
	}catch(MachineError.ParamNotFound e){
	    throw new FrameNameError();
	}

	try{
	    Type v = p.eval(this, args);
	    return v;
	}catch(Machine.MachineError.ArgMismatch e){
	    throw new ExpressionFault.ArgMismatch();
	}catch(Machine.MachineError e){
	    throw new ExpressionFault.CodecError(e);
	}
    }
}
