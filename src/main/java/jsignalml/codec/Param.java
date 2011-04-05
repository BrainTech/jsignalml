package jsignalml.codec;

import jsignalml.Type;
import jsignalml.SyntaxError;
import java.util.Map;

public abstract class Param<T extends Type> extends Context {
	T cache = null;

	abstract protected T _get();
	public T get() {
		if (this.cache == null)
			this.cache = this._get();
		return this.cache;
	}
}
