package jsignalml;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class EvalVisitor extends ExpressionVisitor<Type> {
	final CallHelper context;

	public EvalVisitor(CallHelper context)
	{
		this.context = context;
	}

	public static EvalVisitor create(ASTNode start)
	{
		return new EvalVisitor(new Frame(start));
	}

	@Override
	public Type visit(Expression.BinaryOp op, Type left, Type right)
	{
		return left.binaryOp(op.op, right);
	}

	@Override
	public Type visit(Expression.LogicalBinaryOp op, Type left)
	{
		switch (op.op) {
		case LOG_AND:
			if (!left.isTrue())
				return left;
			break;
		case LOG_OR:
			if (left.isTrue())
				return left;
			break;
		default:
			throw new RuntimeException();
		}

		Type right = op.right.accept(this);
		return right;
	}

	@Override
	public Type visit(Expression.UnaryOp op, Type sub)
	{
		if (op.op == Type.UnaryOp.LOG_NOT)
			return sub.logical_not();
		else
			return sub.unaryOp(op.op);
	}

	@Override
	public Type visit(Expression.Call fun, Type what, List<Type> args)
	{
		return what.call(args);
	}

	@Override
	public Type visit(Expression.Ref ref)
	{
		return this.context.lookup(ref.name);
	}

	@Override
	public Type visit(Expression.Access op, Type struct)
	{
		return struct.access(op.item);
	}

	@Override
	public Type visit(Expression.Index op, Type seq, Type index)
	{
		return seq.index(index);
	}

	@Override
	public Type visit(Expression.Slice op, Type seq, Type start, Type stop, Type step)
	{
		return seq.slice(start, stop, step);
	}

	@Override
	public Type visit(Expression.List_ list, List<? extends Type> args)
	{
		return new TypeList(args);
	}

	@Override
	public Type visit(Expression.Map_ map, List<Map.Entry<Type, Type>> args)
	{
		return new TypeMap(args);
	}

	@Override
	public Type visit(Expression.Const val)
	{
		return val.value;
	}

	@Override
	public Type visit(Expression.Ternary op, Type cond)
	{
		Expression which = cond.isTrue() ? op.a : op.b;
		Type value = which.accept(this);
		return value;
	}

	@Override
	public Type visit(Expression.Assign op)
	{
		Type value = op.value.accept(this);
		this.context.assign(op.id, value);
		return null;
	}


	public static Type evaluate(Expression expr)
	{
		CallHelper builtins = new Frame(Builtins.instance());
		EvalVisitor visitor = new EvalVisitor(builtins);
		return expr.accept(visitor);
	}

	/**
	 * Try to evaluate an expression. NameErrors in the expression
	 * are assumed to be a result of an incomplete context, not an error in
	 * the expression: a null is returned, not an exception.
	 *
	 * If expr is null, null is returned.
	 *
	 * If expr can be evaluted, the return value is check to be of type
	 * expected. If not, a TypeError is thrown.
	 */
	public Type quickEval(Type expected, Expression expr)
	{
		if (expr == null)
			return null;

		if(expr.type != null && expected != null
		   && !expected.getClass().isAssignableFrom(expr.type.getClass()))
			throw new ExpressionFault.TypeError();

		Type ans = null;
		try {
			ans = EvalVisitor.evaluate(expr);
			assert ans != null;
		} catch(ExpressionFault.NameError e) {
			// pass
		}

		if (ans != null && expected != null
		    && !expected.getClass().isAssignableFrom(ans.getClass()))
			throw new ExpressionFault.TypeError();

		return ans;
	}
}
