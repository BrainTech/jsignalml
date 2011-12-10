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

import jsignalml.logging.Logger;

public class Frame implements CallHelper {
	static Logger log = new Logger(Frame.class);

	final ASTVisitor<? extends Object> visitor; // may be null
	final ASTNode node;
	final Map<String, Type> locals;

	final private Throwable _trace = new Throwable();

	public Frame(ASTVisitor<? extends Object> visitor, ASTNode node, Map<String, Type> locals)
	{
		assert node != null;
		assert locals != null;
		this.visitor = visitor;
		this.node = node;
		this.locals = locals;
	}

	public Frame(ASTVisitor<? extends Object> visitor, ASTNode node)
	{
		this(visitor, node, new TreeMap<String, Type>());
	}

	public Frame(ASTNode node) {
		this(null, node);
	}

	@Override
	public void assign(String id, Type val)
	{
		log.info("%s = %s", id, val);
		this.locals.put(id, val);
	}

	@Override
	public Type lookup(String name)
	{
		log.debug("lookup %s?", name);

		final Type val = this.locals.get(name);
		if (val != null)
			return val;

		final ASTNode where = this.node.find(name);
		if (where.isAFunction()) {
			return new TypeFunction(where, this);
		} else {
			if (this.visitor == null) {
				log.exception("null visitor while trying to lookup '%s'",
					      this._trace, name);
				throw new CannotEvaluate(
					"cannot lookup variables because the outer visitor is null");
			}
			return (Type) where.accept(this.visitor, null);
		}
	}

	// @Override
	// public <T extends FileType> T getFile(FileHandle<T> handle)
	// {
	// 	assert false: "no one want to getFile";
	// 	return parent.getFile(handle);
	// }

	public Frame localize(ASTNode node, Map<String,Type> locals) {
		return new Frame(this.visitor, this.node, locals);
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
			return ((ASTNode.Param)this.node).type;
			// TODO: actually look at args
		}

		@Override
		public java.lang.String toString() {
			return format("Function[%s] in %s", node, frame);
		}
	}

	public static class TypeVariable extends TypeObject {
		final ASTNode node;
		final Frame frame;

		TypeVariable(ASTNode node, Frame frame) {
			this.node = node;
			this.frame = frame;
		}

		@Override public Object getValue(){
			ASTEvalVisitor visitor = new ASTEvalVisitor(this.frame);
			return this.node.accept(visitor, null);
		}

		@Override
		public java.lang.String toString() {
			return format("Variable[%s]", node);
		}
	}

	public static class CannotEvaluate extends RuntimeException {
		public CannotEvaluate(String reason)
		{
			super(reason);
		}
	}
}
