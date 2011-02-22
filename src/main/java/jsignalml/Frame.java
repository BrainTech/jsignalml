package jsignalml;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

public class Frame implements CallHelper {
	static Logger log = new Logger(Frame.class);

	// final Frame parent; // may be null
	final ASTNode node;
	final Map<String, Type> env;

	public Frame(ASTNode node, Map<String, Type> locals)
	{
		//		this.parent = parent;
		this.node = node;
		this.env = locals;
	}

	public Frame(ASTNode node) {
		this(node, new TreeMap<String, Type>());
	}

	@Override
	public void assign(String id, Type val)
	{
		log.info("%s = %s", id, val);
		this.env.put(id, val);
	}

	@Override
	public Type call(String id, List<Type> args)
	{
		log.info("lookup %s(%s)", id, StringUtils.join(args, ", "));

		Type val = this.env.get(id);
		if (val != null) {
			if (args.size() != 0)
				throw new ExpressionFault.TypeError();
			return val;
		}

		ASTNode where = this.node.find(id);
		ASTEvalVisitor visitor = new ASTEvalVisitor(this, args);
		return where.accept(visitor, null);
	}

	// @Override
	// public <T extends FileType> T getFile(FileHandle<T> handle)
	// {
	// 	assert false: "no one want to getFile";
	// 	return parent.getFile(handle);
	// }

	public Frame localize(ASTNode node, Map<String,Type> locals) {
		return new Frame(node, locals);
	}

	public Frame localize(ASTNode node, List<Type> args)
	{
		Map<String, Type> locals = util.newHashMap();

		if(node instanceof ASTNode.Param) {
			ASTNode.Param param = (ASTNode.Param)node;
			for (int i=0; i<param.args.size(); i++) {
				ASTNode.Positional arg =  param.args.get(i);
				locals.put(arg.id, args.get(i));
			}
		}
		return this.localize(node, locals);
	}
}
