package jsignalml.codec;

import jsignalml.Type;
import jsignalml.TypeObject;
import jsignalml.SyntaxError;
import java.util.Map;

public abstract class Context extends TypeObject {
	public void register(String name, Context child) {
		Context old = this.param_map.get(name);
		if (old != null)
			throw new SyntaxError("duplicate name " + name); // what exception?
		this.param_map.put(name, child);
	}

	public Context getChild(String name) {
		return this.param_map.get(name);
	}

	Map<String, Context> param_map = jsignalml.util.newHashMap();
}
