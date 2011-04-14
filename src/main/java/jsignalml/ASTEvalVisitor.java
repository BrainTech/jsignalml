package jsignalml;

import java.util.List;
import java.util.Map;

public class ASTEvalVisitor extends ASTVisitor<Type> {
	final Frame context;
	final List<Type> args;

	ASTEvalVisitor(Frame context, List<Type> args){
		this.context = context;
		this.args = args;
	}

	public Type visit(ASTNode.ExprParam p, Type dummy) {
		assert dummy == null;

		if(p.args.size() != this.args.size())
			throw new ExpressionFault.ArgMismatch();

		CallHelper context = this.context.localize(p, this.args);
		return p.expr.accept(new EvalVisitor(context));
	}

	public Type visit(ASTNode.BuiltinFunction p, Type dummy) {
		assert dummy == null;

		return p.function.call(this.args);
	}
}
