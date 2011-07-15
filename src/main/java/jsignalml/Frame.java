package jsignalml;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import static java.lang.String.format;

public class Frame implements CallHelper {
	static Logger log = new Logger(Frame.class);

	// final Frame parent; // may be null
	final ASTNode node;
	final Map<String, Type> env;

	public Frame(ASTNode node, Map<String, Type> locals)
	{
		//		this.parent = parent;
		assert node != null;
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
	public Type lookup(String name)
	{
		log.info("lookup =%s=", name);

		Type val = this.env.get(name);
		if (val != null)
			return val;

		ASTNode where = this.node.find(name);
		return new TypeFunction(where, this);
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
				locals.put(arg.static_id, args.get(i));
			}
		}
		return this.localize(node, locals);
	}

	public static class TypeFunction extends TypeObject {
		final ASTNode node;
		final Frame frame;

		TypeFunction(ASTNode node, Frame frame) {
			this.node = node;
			this.frame = frame;
		}

		public Type call(List<Type> args) {
			ASTEvalVisitor visitor = new ASTEvalVisitor(this.frame, args);
			return this.node.accept(visitor, null);
		}

		public Type callType(List<Type> args)
		{
			if(!(this.node instanceof ASTNode.Param))
				throw new ExpressionFault.TypeError();
			return ((ASTNode.Param)this.node).type;
			// TODO: actually look at args
		}

		@Override
		public java.lang.String toString() {
			return format("Function[%s] in %s", node, frame);
		}
	}
}
