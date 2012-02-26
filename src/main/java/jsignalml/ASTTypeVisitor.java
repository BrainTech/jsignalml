package jsignalml;

import java.util.Map;

import jsignalml.logging.Logger;
import static jsignalml.Type.typename;

public class ASTTypeVisitor extends ASTVisitor<Type> {
	public static final Logger log = new Logger(ASTTypeVisitor.class);

	Map<ASTNode, Type> _cache = util.newHashMap();
	int _nestedness = 0;

	static private class _ShouldNotLeak extends TypeObject {
		public String toString() { return getClass().getSimpleName(); };
	}
	static private Type _null_repl = new _ShouldNotLeak();

	Type getCached(ASTNode node) {
		final Type cached = this._cache.get(node);
		if (cached != null) {
			log.trace("got cached %s -> %s", node,
				  cached == _null_repl ? "unknown" : typename(cached));
			return cached;
		} else {
			this._nestedness++;
			log.debug("checking[%d] %s %x", this._nestedness, node, node.hashCode());
			this._cache.put(node, _null_repl);
			return null;
		}
	}

	Type putCached(ASTNode node, Type value) {
		this._nestedness--;
		log.info("found %s -> %s", node, typename(value));

		this._cache.put(node, value == null ? _null_repl : value);
		return value;
	}

	/**
	 * Returns a ASTTypeResolver instance which can be used to query the
	 * results of this ASTVisitor.
	 */
	public ASTTypeResolver getTypeResolver() {
		return new ASTTypeResolver() {
			public Type getType(ASTNode node) {
				Type cached = ASTTypeVisitor.this.getCached(node);
				if (cached == null || cached == _null_repl)
					return null;
				else
					return cached;
			}
		};
	}

	// All Expression fields defined in subclasses of ASTNode should be
	// checked.

	TypeVisitor _typeVisitor(ASTNode node)
	{
		return new TypeVisitor(new Frame(this, node));
	}

	@Override
	public Type visit(ASTNode.Channel node, Type parent)
	{
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		assert node.mapping != null;
		assert node.format != null;
		assert node.length != null;
		assert node.fast != null;
		Type mapping_t = node.mapping.accept(_typeVisitor(node));
		Type format_t = node.format.accept(_typeVisitor(node));
		Type length_t = node.length.accept(_typeVisitor(node));
		Type fast_t = node.fast.accept(_typeVisitor(node));
		log.info("%s mapping.type=%s format.type=%s length.type=%s fast.type=%s",
			 node,
			 typename(mapping_t),
			 typename(format_t),
			 typename(length_t),
			 typename(fast_t));

		return putCached(node, null);
	}

	@Override
	public Type visit(ASTNode.ExprParam node, Type parent)
	{
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		assert node.expr != null;
		cached = node.expr.accept(_typeVisitor(node));
		log.info("%s expr.type=%s", node, typename(cached));
		return putCached(node, cached);
	}

	@Override
	public Type visit(ASTNode.BinaryParam node, Type parent)
	{
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		final TypeVisitor checker = _typeVisitor(node);

		{
			assert node.format != null;
			final Type t1 = node.format.accept(checker);
			log.info("%s format.type=%s", node, typename(t1));
			assert_type(t1, TypeString.I);
		}

		{
			assert node.offset != null;
			final Type t2 = node.offset.accept(checker);
			log.info("%s offset.type=%s", node, typename(t2));
			assert_type(t2, TypeInt.I);
		}

		final Type type;
		{
			// XXX: change the visitor to use outer context
			// this will only work for very simple expressions
			final EvalVisitor valuator = EvalVisitor.create(node);
			Type value;
			try{
				value = node.format.accept(valuator);
			} catch(Frame.CannotEvaluate e) {
				value = null;
			}
			assert_type(value, TypeString.I);
			final Type readtype;
			if (value != null)
				readtype = BitForm.get(((TypeString)value)).readType();
			else
				readtype = null;

			node._read_type = readtype;
			type = node.type != null ? node.type : readtype;
		}

		return putCached(node, type);
	}

	@Override
	public Type visit(ASTNode.Positional node, Type parent)
	{
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		return putCached(node, node.type);
	}

	@Override
	public Type visit(ASTNode.Itername node, Type parent)
	{
		final Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		final ASTNode.ForLoop loop = (ASTNode.ForLoop) node.parent;
		final Expression index = new
			Expression.Index(loop.sequence, Expression.Const.make(-1));
		final Type calculated = index.accept(_typeVisitor(loop));

		final Type type = node.type != null ? node.type : calculated;

		return putCached(node, type);
	}

	/**
	 * Common implementation for all trivial Params, which share a 'type' field.
	 */
	Type _visit(ASTNode.Param node)
	{
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		return putCached(node, node.type);
	}

	@Override
	public Type visit(ASTNode.Assert node, Type parent)
	{
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		assert node.expr != null;
		Type t1 = node.expr.accept(_typeVisitor(node));
		log.info("%s expr.type=%s", node, typename(t1));

		return putCached(node, null);
	}

	@Override
	public Type visit(ASTNode.Conditional node, Type parent)
	{
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		assert node.condition != null;
		Type t1 = node.condition.accept(_typeVisitor(node));
		log.info("%s condition.type=%s", node, typename(t1));

		return putCached(node, null);
	}

	@Override
	public Type visit(ASTNode.FileHandle node, Type parent)
	{
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		if (node.filename != null)
			node.filename.accept(_typeVisitor(node));

		return putCached(node, null);
	}

	@Override
	public Type visit(ASTNode.ForLoop node, Type parent)
	{
		final Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		{
			assert node.sequence != null;
			final Type t1 = node.sequence.accept(_typeVisitor(node));
			log.info("%s sequence.type=%s", node, typename(t1));
		}

		return putCached(node, null);
	}

	void assert_type(Type var, Type type)
	{
		assert type != null;
		if (var != null &&
		    ! type.getClass().isAssignableFrom(var.getClass()))
			throw new ExpressionFault.TypeError(var, type);
	}
}
