package jsignalml;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class EvalVisitor extends ExpressionVisitor<Type> {
	final Frame state;
	final ASTNode context;

	public EvalVisitor(Frame state, ASTNode context)
	{
		this.state = state;
		this.context = context;
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
	public Type visit(Expression.Call fun, List<Type> args)
	{
		Type ret = this.state.lookup(fun.name, args);
		if (ret != null)
			return ret;

		ASTNode node = this.context.find(fun.name);
		if (node != null) {
			ASTEvalVisitor visitor = new ASTEvalVisitor(this.state, args);
			ret = node.accept(visitor, null);
			if(ret == null)
				throw new ExpressionFault.TypeError();
			return ret;
		}

		throw new ExpressionFault.NameError(fun.name);
	}

	@Override
	public Type visit(Expression.Index op, Type seq, Type index)
	{
		return seq.index(index);
	}

	@Override
	public Type visit(Expression.List_ list, List<? extends Type> args)
	{
		return new TypeList(args);
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
		this.state.assign(op.id, value);
		return null;
	}
}
