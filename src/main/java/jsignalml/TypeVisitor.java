package jsignalml;

import java.util.List;

public class TypeVisitor extends ExpressionVisitor<Type> {
	@Override
	public Type visit(Expression.BinaryOp op, Type left, Type right)
	{
		return left != null ? left.binaryOpType(op.op, right) : null;
	}

	@Override
	public Type visit(Expression.LogicalBinaryOp op, Type left)
	{
		Type right = op.right.accept(this);
		return left != null ? left.binaryOpType(op.op, right) :  null;
	}

	@Override
	public Type visit(Expression.UnaryOp op, Type sub)
	{
		return sub != null ? sub.unaryOpType(op.op) : null;
	}

	@Override
	public Type visit(Expression.Call call, Type what, List<Type> args)
	{
		// TODO
		return null;
	}

	@Override
	public Type visit(Expression.List_ list, List<? extends Type> args)
	{
		return new TypeList();
	}

	/**
	 * If all elements are of the same type, return this type, otherwise null.
	 */
	public Type elementType(Expression.List_ list)
	{
		Type type = null;
		for (Expression el: list.args) {
			Type t = el.accept(this);
			if (t == null)
				return null;
			if (type != null && !t.getClass().equals(type.getClass()))
				return null;
			type = t;
		}
		return type;
	}

	@Override
	public Type visit(Expression.Index op, Type seq, Type index)
	{
		if (index != null && !(index instanceof TypeInt))
			throw new ExpressionFault.TypeError();

		if (seq != null && !(seq instanceof TypeList || seq instanceof TypeString))
			throw new ExpressionFault.TypeError();

		if (op.item instanceof Expression.List_)
			return this.elementType((Expression.List_)op.item);

		return null;
	}

	@Override
	public Type visit(Expression.Const val)
	{
		return val.value;
	}

	@Override
	public Type visit(Expression.Ternary op, Type cond)
	{
		Type a = op.a.accept(this);
		Type b = op.b.accept(this);
		if (a.getClass().equals(b.getClass())) // XXX: what if a or b is null?
			return a;
		else
			return null;
	}

	@Override
	public Type visit(Expression.Assign op)
	{
		// XXX: add a marker for "no type"
		return null;
	}
}
