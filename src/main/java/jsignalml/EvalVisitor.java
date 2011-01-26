package jsignalml;

import java.util.Arrays;

public class EvalVisitor extends ExpressionVisitor<Type> {
	final CallHelper state;

	public EvalVisitor(CallHelper state)
	{
		this.state = state;
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
	public Type visit(Expression.Call fun, Type...args)
	{
		return state.call(fun.name, args);
	}

	@Override
	public Type visit(Expression.List_ list, Type...args)
	{
		return new Type.List(Arrays.asList(args));
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
		this.state.assign(op.id, op.value);
		return null;
	}
}
