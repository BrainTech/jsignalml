package jsignalml.codec;

import java.util.Map;
import java.util.Set;

import jsignalml.ContextVisitor;
import jsignalml.ExpressionFault;
import jsignalml.SyntaxError;
import jsignalml.Type;
import jsignalml.TypeObject;
import jsignalml.TypeString;
import jsignalml.logging.Logger;

public abstract class Context extends TypeObject {
	static final Logger log = new Logger(Context.class);

	public void register(String name, Context child) {
		Context old = this.param_map.get(name);
		if (old != null)
			throw new SyntaxError("duplicate name " + name); // what exception?
		
		TypeString typeOld = null;
		TypeString typeChild = null;
		try {
			typeOld = (old != null && old instanceof Param<?> && ((Param<?>) old)._get() instanceof TypeString) ? (TypeString) ((Param<?>) old)._get() : null;
		} catch (RuntimeException ex) {
			//ex.printStackTrace();
		}
		try {
			typeChild = (child != null && child instanceof Param<?> && ((Param<?>) child)._get() instanceof TypeString) ? (TypeString) ((Param<?>) child)._get() : null;
		} catch (RuntimeException ex) {
			//ex.printStackTrace();
		}
		String typeOldName = (typeOld != null) ? typeOld.toString() : null;
		String typeChildName = (typeChild != null) ? typeChild.toString() : null;
		String c = (typeChildName != null) ? typeChildName : (typeOldName != null) ? typeOldName : ""; //jsignalml.TypeString@217c59=Fp1
		if (c.length() > 0) {
			int eq = c.indexOf("=");
			if (eq >= 0) {
				c = c.substring(eq + 1);
			}
		}
		/*
		if (c.startsWith("F7")) {
			System.out.println("F7 registered");
		} else if (c.startsWith("F8")) {
			System.out.println("F8 registered");
		} else if (c.startsWith("RES")) {
			System.out.println("RES registered");
		}
		*/
		
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
		String visitorInfo = "[" + v + "]";
		visitorInfo = visitorInfo.replaceAll("[\t ]+", "");
		visitorInfo = visitorInfo.replaceAll("[\r\n]+", " ");
		log.info("%s : accept(%s, %s, %s)", this, visitorInfo, name, data);
		T newdata = this._accept(v, name, data);

		Set<Map.Entry<String, Context>> entries = this.param_map.entrySet();
		for(Map.Entry<String, Context> entry: entries) {
			String key = entry.getKey();
			Context val = entry.getValue();
			val.accept(v, key, newdata);
		}
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

	@Override
	public String toString() {
		String result = /*"Context id=" +*/ this.id() + " = [";
		Set<Map.Entry<String, Context>> entries = this.param_map.entrySet();
		int i = 0;
		for(Map.Entry<String, Context> entry: entries) {
			String key = entry.getKey();
			Context val = entry.getValue();
			if (i > 0) result += ", ";
			result += key + " = " + val;
			i ++;
		}
		result += "]";
		return result;
	}
	
}
