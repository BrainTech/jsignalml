package jsignalml.codec;

import java.util.Map;
import jsignalml.ContextVisitor;
import jsignalml.SyntaxError;
import jsignalml.ExpressionFault;
import jsignalml.Type;
import jsignalml.TypeObject;
import jsignalml.logging.Logger;

public abstract class Context extends TypeObject {
	static final Logger log = new Logger(Context.class);

	public void register(String name, Context child) {
		Context old = this.param_map.get(name);
		if (old != null)
			throw new SyntaxError("duplicate name " + name); // what exception?
		this.param_map.put(name, child);
	}

	public Type access(String name) {
		Context cont = this.param_map.get(name);
		if (cont == null) {
			throw new ExpressionFault.AttributeError(this.id(), name);
		}
		return cont.get();
	}

	final Map<String, Context> param_map = jsignalml.util.newLinkedHashMap();

	public abstract String id();

	/**
	 * Visitor pattern implementation.
	 *
	 * Visit this context and subcontexts starting at this, calling v.visit
	 * on all nodes. A context calls v.visit on itself and then on it's
	 * registered parameters in turn.
	 */
	public <T> T accept(ContextVisitor<T> v, String name, T data) {
		log.info("%s accept(%s, %s, %s)", this, v, name, data);
		T newdata = this._accept(v, name, data);

		for(Map.Entry<String, Context> entry: this.param_map.entrySet())
			entry.getValue().accept(v, entry.getKey(), newdata);
		return newdata;
	}

	/**
	 * This function must exist in all non-abstract children (even if
	 * identical), to allow overloaded v.visit method resolution based on
	 * this.
	 */
	public abstract <T> T _accept(ContextVisitor<T> v, String name, T data);

	/**
	 * Register names in this namespace. After this function executes, all
	 * static names in this namespace should be available.
	 */
	public abstract void createParams();

	/**
	 * Register channels in this namespace. After this function executes, all
	 * channels in this namespace should be available.
	 */
	public abstract void createChannels();
}
