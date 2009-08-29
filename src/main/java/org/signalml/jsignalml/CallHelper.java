package org.signalml.jsignalml;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.signalml.jsignalml.Machine.MachineError;
import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.ExpressionFault;

public interface CallHelper {
    public interface FileHandle<T extends FileType>
    {
	public T open(CallHelper state, File filename)
	    throws ExpressionFault, MachineError,
		   IOException, FileNotFoundException;
    }

    public Type call(String id, Type...args)
	throws ExpressionFault;

    public <T extends FileType> T getFile(FileHandle<T> handle)
	throws ExpressionFault, MachineError,
	       IOException, FileNotFoundException;

    public void assign(String id, Expression expr)
	throws ExpressionFault;

    public CallHelper localize(Map<String,Type> locals);
}
