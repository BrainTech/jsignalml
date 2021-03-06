package jsignalml;

import java.util.List;

import javax.lang.model.type.NullType;

import jsignalml.logging.Logger;

public class NameCheck extends ASTVisitor<NullType> {
	public static final Logger log = new Logger(NameCheck.class);

	// All Expression fields defined in subclasses of ASTNode should be
	// checked.

	@Override
	public NullType visit(ASTNode.ExprParam node, NullType parent)
	{
		log.debug("checking %s", node);
		assert node.expr != null;
		node.expr.accept(new ExpressionNameCheck(node));
		return null;
	}

	@Override
	public NullType visit(ASTNode.BinaryParam node, NullType parent)
	{
		log.debug("checking %s", node);
		ExpressionNameCheck checker = new ExpressionNameCheck(node);
		assert node.format != null;
		assert node.offset != null;
		node.format.accept(checker);
		node.offset.accept(checker);
		return null;
	}

	@Override
	public NullType visit(ASTNode.TextParam node, NullType parent)
	{
		log.debug("checking %s", node);
		ExpressionNameCheck checker = new ExpressionNameCheck(node);
		assert node.line != null;
		assert node.pattern != null;
		assert node.group != null;
		node.line.accept(checker);
		node.pattern.accept(checker);
		node.group.accept(checker);
		return null;
	}

	@Override
	public NullType visit(ASTNode.XmlParam node, NullType parent)
	{
		log.debug("checking %s", node);
		ExpressionNameCheck checker = new ExpressionNameCheck(node);
		assert node.xpathPattern != null;
		assert node.xpathEvaluationType != null;
		node.xpathPattern.accept(checker);
		return null;
	}

	@Override
	public NullType visit(ASTNode.CodecID node, NullType parent)
	{
		log.debug("checking %s", node);
		ExpressionNameCheck checker = new ExpressionNameCheck(node);
		assert node.provider != null;
		assert node.version != null;
		node.provider.accept(checker);
		node.version.accept(checker);
		return null;
	}

	@Override
	public NullType visit(ASTNode.FormatID node, NullType parent)
	{
		log.debug("checking %s", node);
		ExpressionNameCheck checker = new ExpressionNameCheck(node);
		assert node.name != null;
		assert node.provider != null;
		assert node.version != null;
		node.name.accept(checker);
		node.provider.accept(checker);
		node.version.accept(checker);
		return null;
	}

	@Override
	public NullType visit(ASTNode.Assert node, NullType parent)
	{
		log.debug("checking %s", node);
		assert node.expr != null;
		node.expr.accept(new ExpressionNameCheck(node));
		return null;
	}

	@Override
	public NullType visit(ASTNode.FileHandle<?> node, NullType parent)
	{
		log.debug("checking %s", node);
		if (node.filename != null)
			node.filename.accept(new ExpressionNameCheck(node));
		return null;
	}

	public static class ExpressionNameCheck extends ExpressionVisitor<NullType> {
		final ASTNode context;
		ExpressionNameCheck(ASTNode context)
		{
			this.context = context;
		}

		@Override
		public NullType visit(Expression.Call call, NullType what_, List<NullType> args_)
		{
			log.debug("checking %s", call.what);
			if (call.what instanceof Expression.Const) {
				Expression.Const what = (Expression.Const) call.what;
				if (what.value instanceof TypeString)
					this.context.find(((TypeString)what.value).getValue());
				else
					throw new ExpressionFault.AttributeError(what.toString());
			}
			return null;
		}
	}
}
