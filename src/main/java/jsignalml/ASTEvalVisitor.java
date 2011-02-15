package jsignalml;

import java.util.List;
import java.util.Map;

public class ASTEvalVisitor extends ASTVisitor<Type> {
	final List<Type> args;
	final Frame state;

	ASTEvalVisitor(Frame state, List<Type> args){
		this.args = args;
		this.state = state;
	}

	public Type visit(ASTNode.ExprParam p, Type dummy) {
		assert dummy == null;

		if(p.args.size() != this.args.size())
			throw new ExpressionFault.ArgMismatch();

		Map<String, Type> locals = util.newHashMap();

		for (int i=0; i<p.args.size(); i++) {
			ASTNode.Positional arg =  p.args.get(i);
			locals.put(arg.id, this.args.get(i));
		}

		Frame newstate = this.state.localize(locals);
		return p.expr.accept(new EvalVisitor(newstate, p));
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
