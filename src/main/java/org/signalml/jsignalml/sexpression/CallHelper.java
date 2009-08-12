package org.signalml.jsignalml.sexpression;

public interface CallHelper {
    public Type call(String id, Object ... args)
	throws ExpressionFault;
}
