package org.signalml.jsignalml;

import java.util.Map;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.signalml.jsignalml.Machine.MachineError;
import org.signalml.jsignalml.sexpression.*;

public abstract class Frame implements CallHelper {
    final CallHelper parent; // may be null

    protected Frame(CallHelper parent){
	this.parent = parent;
    }

    @Override
    public void assign(String id, Expression expr)
	throws ExpressionFault
    {
	// assign-ment is disallowed
	throw new ExpressionFault();
    }

    static public class FrameNameError extends Exception {}

    public Type call(String id, Type...args)
	throws ExpressionFault
    {
	try{
	    return this.frame_call(id, args);
	}catch(FrameNameError e){}

	if(parent != null)
	    return this.parent.call(id, args);
	else
	    throw new ExpressionFault.NameError(id);
    }

    public abstract Type frame_call(String id, Type...args)
	throws FrameNameError, ExpressionFault;

    @Override
    public <T extends FileType> T getFile(FileHandle<T> handle)
	throws ExpressionFault, MachineError,
	       IOException, FileNotFoundException
    {
	if(parent != null)
	    return parent.getFile(handle);
	else
	    throw new ExpressionFault();
    }

    public LocalState localize(Map<String,Type> locals){
	return new LocalState(this, locals);
    }
}
