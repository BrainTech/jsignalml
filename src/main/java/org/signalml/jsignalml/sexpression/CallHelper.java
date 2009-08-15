package org.signalml.jsignalml.sexpression;

import java.util.List;

public interface CallHelper {
    public Type call(String id, List<Type> args)
	throws ExpressionFault;

    public void assign(String id, Expression expr);
}
