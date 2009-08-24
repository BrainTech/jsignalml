package org.signalml.jsignalml;

import java.util.Map;
import org.signalml.jsignalml.sexpression.*;

public class LocalState extends Frame implements CallHelper {
    final Map<String, Type> locals;
    public LocalState(CallHelper parent, Map<String,Type> locals){
	super(parent);
	this.locals = locals;
    }

    @Override
    public Type frame_call(String id, Type...args)
	throws Frame.FrameNameError, ExpressionFault.ArgMismatch
    {
	Type v = locals.get(id);
	if(v == null)
	    throw new Frame.FrameNameError();

	if(args.length > 0)
	    throw new ExpressionFault.ArgMismatch();

	return v;
    }
}
