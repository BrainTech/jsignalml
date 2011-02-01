package jsignalml;

import java.util.List;
import javax.lang.model.type.NullType;
import org.apache.log4j.BasicConfigurator;

public class NameCheck extends ASTVisitor<NullType> {
	public static final Logger log = new Logger(NameCheck.class);

	public NullType visit(ASTNode.ExprParam node, NullType parent){
		log.debug("checking %s", node);
		node.expr.accept(new ExpressionNameCheck(node));
		return null;
	}

	public static class ExpressionNameCheck extends ExpressionVisitor<NullType> {
		final ASTNode.ExprParam context;
		ExpressionNameCheck(ASTNode.ExprParam context)
		{
			this.context = context;
		}

		@Override
		public NullType visit(Expression.Call call, List<? extends NullType> args)
		{
			log.debug("checking %s", call.name);
			this.context.find(call.name);
			return null;
		}
	}
}
