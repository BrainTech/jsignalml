package jsignalml;

import java.util.Map;

public class ASTTypeVisitor extends ASTVisitor<Type> {
	public static final Logger log = new Logger(ASTTypeVisitor.class);

	Map<ASTNode, Type> _cache = util.newHashMap();
	int _nestedness = 0;

	static class _ShouldNotLeak extends TypeObject {
		public String toString() { return getClass().getSimpleName(); };
	}
	static Type _null_repl = new _ShouldNotLeak();

	Type getCached(ASTNode node) {
		final Type cached = this._cache.get(node);
		if (cached != null) {
			log.trace("got cached %s -> %s", node,
				  cached == _null_repl ? "unknown" : Type.typename(cached));
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
		log.trace("caching %s -> %s", node, Type.typename(value));

		this._cache.put(node, value == null ? _null_repl : value);
		return value;
	}

	// All Expression fields defined in subclasses of ASTNode should be
	// checked.

	TypeVisitor _typeVisitor(ASTNode node)
	{
		return new TypeVisitor(new Frame(this, node));
	}

	@Override
	public Type visit(ASTNode.ExprParam node, Type parent)
	{
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		assert node.expr != null;
		cached = node.expr.accept(_typeVisitor(node));
		log.info("%s expr.type=%s", node, Type.typename(cached));
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
			log.info("%s format.type=%s", node, Type.typename(t1));
			assert_type(t1, TypeString.I);
		}

		{
			assert node.offset != null;
			final Type t2 = node.offset.accept(checker);
			log.info("%s offset.type=%s", node, Type.typename(t2));
			assert_type(t2, TypeInt.I);
		}

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

		Type type = node.type != null ? node.type : readtype;

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
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		return putCached(node, node.type);
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
		log.info("%s expr.type=%s", node, Type.typename(t1));

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
		log.info("%s condition.type=%s", node, Type.typename(t1));

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
		Type cached = this.getCached(node);
		if (cached != null)
			return cached == _null_repl ? null : cached;

		assert node.sequence != null;
		Type t1 = node.sequence.accept(_typeVisitor(node));
		log.info("%s sequence.type=%s", node, Type.typename(t1));

		return putCached(node, null);
	}

	void assert_type(Type var, Type type)
	{
		assert type != null;
		if (var != null &&
		    ! type.getClass().isAssignableFrom(var.getClass()))
			throw new ExpressionFault.TypeError(var.getClass(), type.getClass());
	}
}
