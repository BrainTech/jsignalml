package jsignalml.codec;

import jsignalml.Type;
import jsignalml.ContextVisitor;

public abstract class Param<T extends Type> extends Context {
	T cache = null;

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

	@Override
	public void createChannels()
	{
		// do nothing
	}
}
