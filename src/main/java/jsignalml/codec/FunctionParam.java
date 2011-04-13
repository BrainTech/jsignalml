package jsignalml.codec;

import jsignalml.Type;
import jsignalml.ContextVisitor;
import java.util.List;

public abstract class FunctionParam<T extends Type> extends Context {
	@Override
	public abstract Type call(List<Type> args);

	@Override
	public <T> T _accept(ContextVisitor<T> v, String name, T data)
	{
		return v.visit(this, name, data);
	}

	@Override
	public void createParams()
	{
		// nothing to be done here
	}
}
