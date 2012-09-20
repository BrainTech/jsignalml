package jsignalml.codec;

import jsignalml.ContextVisitor;
import jsignalml.Type;

public abstract class Param<T extends Type> extends Context {
	public T cache = null; /* This is public so it can be accessed
				* from within generated code. Maybe an
				* abstract "proxy" class should be added
				* so IndexClass can inherit and get protected
				* access
				*/

	abstract protected T _get();
	public T get()
	{
		if (this.cache == null)
			this.cache = this._get();
		return this.cache;
	}

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
