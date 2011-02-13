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

	static class Invocation implements Comparable<Invocation> {
		final String id;
		final List<Type> args;

		Invocation (String id, List<Type> args)
		{
			this.id = id;
			this.args = unmodifiableList(args);
		}

		Invocation (String id)
		{
			this(id, Collections.EMPTY_LIST);
		}

		public int compareTo(Invocation other)
		{
			int r = this.id.compareTo(other.id);
			if (r != 0)
				return r;

			Iterator<Type> itother = other.args.iterator();

			for(Type item: this.args){
				if(!itother.hasNext())
					return 1;
				Type itemother = itother.next();
				int cmp = item.compareTo(itemother);
				if(cmp != 0)
					return cmp;
			}

			if(itother.hasNext())
				return -1;
			return 0;
		}

		public static Map<Invocation, Type> map(Map<String, Type> locals)
		{
			Map<Invocation, Type> map = util.newTreeMap();
			for(Map.Entry<String, Type> entry: locals.entrySet())
				map.put(new Invocation(entry.getKey()), entry.getValue());
			return map;
		}
	}

	final Frame parent; // may be null
	final Map<Invocation, Type> env;

	public Frame(Frame parent) {
		this.parent = parent;
		this.env  = util.newTreeMap();
	}

	private Frame(Frame parent, Map<String, Type> locals)
	{
		this.parent = parent;
		this.env = unmodifiableMap(Invocation.map(locals));
	}

	public void assign(String id, List<Type> args, Type expr)
	{
		log.info("%s = %s", id, expr);
		this.env.put(new Invocation(id, args), expr);
	}

	public Type lookup(String id, List<Type> args)
	{
		log.info("lookup %s(%s)", id, StringUtils.join(args, ", "));

		Type value = this.frame_lookup(id, args);
		if (value != null)
			return value;

		if (parent != null)
			return this.parent.lookup(id, args);
		else
			return null;
	}

	public Type frame_lookup(String id, List<Type> args)
	{
		return this.env.get(new Invocation(id, args));
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
