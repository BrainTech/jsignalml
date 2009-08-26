package org.signalml.jsignalml;

import java.util.Map;

import org.signalml.jsignalml.sexpression.*;
import org.signalml.jsignalml.CodecyThing.CodecError;

public class Reader extends Frame implements CallHelper {
    public static final Logger log = new Logger(Reader.class);

    @Override
    public <T extends FileType> T getFile(FileHandle<T> handle)
	throws ExpressionFault
    {
	return null;
    }

    final CodecyThing codec;
    public Reader(CodecyThing codec){
	super(null);
	this.codec = codec;
    }

    @Override
    public Type frame_call(String id, Type...args)
	throws ExpressionFault, FrameNameError
    {
	Machine.Param p;
	try{
	    p = codec.getParam(id);
	}catch(CodecyThing.CodecError.KeyError e){
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
