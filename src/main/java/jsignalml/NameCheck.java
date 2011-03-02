package jsignalml;

import java.util.List;
import javax.lang.model.type.NullType;
import org.apache.log4j.BasicConfigurator;

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
	public NullType visit(ASTNode.Assert node, NullType parent)
	{
		log.debug("checking %s", node);
		assert node.expr != null;
		node.expr.accept(new ExpressionNameCheck(node));
		return null;
	}

	@Override
	public NullType visit(ASTNode.FileHandle node, NullType parent)
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
