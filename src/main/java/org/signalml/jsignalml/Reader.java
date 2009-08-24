package org.signalml.jsignalml;

import java.util.Map;

import org.signalml.jsignalml.sexpression.*;
import org.signalml.jsignalml.CodecyThing.CodecError;

public class Reader implements CallHelper {
    public static final Logger log = new Logger(Reader.class);

    @Override
    public void assign(String id, Expression expr)
	throws ExpressionFault
    {
	// assign-ment is disallowed
	throw new ExpressionFault();
    }

    @Override
    public <T extends FileType> T getFile(FileHandle<T> handle)
	throws ExpressionFault
    {
	return null;
    }

    final CodecyThing codec;
    public Reader(CodecyThing codec){
	this.codec = codec;
    }

    @Override
    public Type call(String id, Type...args)
	throws ExpressionFault
    {
	Machine.Param p;
	try{
	    p = codec.getParam(id);
	}catch(CodecError.KeyError e){
	    throw new ExpressionFault.NameError(id);
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

    public LocalState localize(Map<String,Type> locals){
	return new LocalState(this, locals);
    }
}
