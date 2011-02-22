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

		if (p.id.equals("factorial")) {
			if(this.args.size() != 1)
				throw new ExpressionFault.ArgMismatch();
			Type arg = this.args.get(0);
			if(!(arg instanceof TypeInt))
				throw new ExpressionFault.TypeError();
			return Builtins.factorial((TypeInt)arg);
		}

		throw new RuntimeException("???");
	}
}
