package jsignalml;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.util.Collections.unmodifiableMap;

import org.apache.commons.lang.StringUtils;

public class Frame {
	static Logger log = new Logger(Frame.class);

	final Frame parent; // may be null
	final Map<String, Type> env;

	public Frame(Frame parent) {
		this.parent = parent;
		this.env  = util.newTreeMap();
	}

	private Frame(Frame parent, Map<String, Type> locals) {
		this.parent = parent;
		this.env = unmodifiableMap(new TreeMap<String,Type>(locals));
	}

	public void assign(String id, List<? extends Type> args, Type expr)
	{
		log.info("%s = %s", id, expr);
		if (args.size() > 0)
			throw new RuntimeException("not implemented");
		this.env.put(id, expr);
	}

	public Type lookup(String id, List<? extends Type> args)
	{
		log.info("lookup %s(%s)", id, StringUtils.join(args, ", "));

		Type value = this.frame_lookup(id, args);
		if (value != null)
			return value;

		if (parent != null)
			return this.parent.lookup(id, args);
		else
			throw new ExpressionFault.NameError(id);
	}

	public Type frame_lookup(String id, List<? extends Type> args)
	{
		if (args.size() > 0)
			throw new RuntimeException("not implemented");
		Type value = env.get(id);
		return value;
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
