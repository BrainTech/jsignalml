package jsignalml;

import java.util.List;
import java.util.Map;

public class ASTEvalVisitor extends ASTVisitor<Type> {
	final List<Type> args;

	ASTEvalVisitor(List<Type> args){
		this.args = args;
	}

	public Frame scope(ASTNode node) {
		assert node != null;
		Frame state;

		if (node.parent != null) {
			state = new Frame(scope(node.parent));
		}
		else {
			state = new Frame(null);
		}

		if (node instanceof ASTNode.ExprParam) {
			Map<String, Type> locals = util.newHashMap();

			for (int i=0; i<((ASTNode.ExprParam)node).args.size(); i++) {
				ASTNode.Positional arg =  ((ASTNode.ExprParam)node).args.get(i);
				locals.put(arg.id, this.args.get(i));
			}
			return state.localize(locals);
		}
		return state;
	}

	public Type visit(ASTNode.ExprParam p, Type dummy) {
		assert dummy == null;

		if(p.args.size() != this.args.size())
			throw new ExpressionFault.ArgMismatch();

		return p.expr.accept(new EvalVisitor(scope(p), p));
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
