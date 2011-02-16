package jsignalml;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableList;

import org.apache.commons.lang.StringUtils;

public class Frame {
	static Logger log = new Logger(Frame.class);

	final Frame parent; // may be null

	final Map<String, Type> env;

	public Frame(Frame parent) {
		this.parent = parent;
		this.env  = util.newTreeMap();
	}

	private Frame(Frame parent, Map<String, Type> locals)
	{
		this.parent = parent;
		this.env = unmodifiableMap(locals);
	}

	public void assign(String id, Type val)
	{
		log.info("%s = %s", id, val);
		this.env.put(id, val);
	}

	public Type lookup(String id, List<Type> args)
	{
		log.info("lookup %s(%s)", id, StringUtils.join(args, ", "));

		Type val = this.env.get(id);
		if (val != null) {
			if (args.size() != 0)
				throw new ExpressionFault.TypeError();
			return val;
		}

    if(parent != null)
      return parent.lookup(id, args);

		return null;
	}

	// @Override
	// public <T extends FileType> T getFile(FileHandle<T> handle)
	// {
	// 	assert false: "no one want to getFile";
	// 	return parent.getFile(handle);
	// }

	public Frame localize(Map<String,Type> locals) {
		return new Frame(this, locals);
	}

}
