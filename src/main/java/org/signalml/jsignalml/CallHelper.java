package org.signalml.jsignalml;

import java.util.List;

import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.ExpressionFault;

public interface CallHelper {
    public Type call(String id, Type...args)
	throws ExpressionFault;

    public FileType getFile(Machine.FileHandle handle)
	throws ExpressionFault;

    public void assign(String id, Expression expr);
}
