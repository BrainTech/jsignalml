package jsignalml;

import java.util.Map;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Frame implements CallHelper {
    final CallHelper parent; // may be null

    protected Frame(CallHelper parent){
	this.parent = parent;
    }

    @Override
    public void assign(String id, Expression expr)
    {
	// assign-ment is disallowed
	throw new ExpressionFault.AssignmentError();
    }

    static public class FrameNameError extends Exception {}

    public Type call(String id, Type...args)
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
	throws FrameNameError;

    @Override
    public <T extends FileType> T getFile(FileHandle<T> handle)
    {
	assert false: "no one want to getFile";
	return parent.getFile(handle);
    }

    public LocalState localize(Map<String,Type> locals){
	return new LocalState(this, locals);
    }

    public static class Builtins extends Frame {
	public Builtins(Frame parent){
	    super(parent);
	}

	public Type frame_call(String id, Type...args)
	    throws FrameNameError
	{
	    if(id.equals("strip"))
		return do_strip(args);
	    throw new FrameNameError();
	}

	Type.String do_strip(Type...args){
	    if(args.length != 1)
		throw new ExpressionFault.ArgMismatch();
	    if(!(args[0] instanceof Type.String))
		throw new ExpressionFault.TypeError();
	    Type.String arg = (Type.String)args[0];
	    return new Type.String(arg.getValue().trim());
	}
    }
}
