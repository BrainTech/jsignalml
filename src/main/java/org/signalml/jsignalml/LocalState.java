package org.signalml.jsignalml;

import java.util.Map;
import java.util.TreeMap;
import static java.util.Collections.unmodifiableMap;

import org.signalml.jsignalml.sexpression.*;

public class LocalState extends Frame implements CallHelper {
    public final /*immutable*/ Map<String, Type> locals;
    public LocalState(CallHelper parent, Map<String,Type> locals){
	super(parent);
	this.locals = unmodifiableMap(new TreeMap<String,Type>(locals));
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
