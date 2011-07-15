package jsignalml;

import java.util.List;
import java.util.Map;

public class TypeVisitor extends ExpressionVisitor<Type> {
	final CallHelper context;

	public TypeVisitor(CallHelper context)
	{
		this.context = context;
	}

	@Override
	public Type visit(Expression.BinaryOp op, Type left, Type right)
	{
		if(left != null)
			return op.type = left.binaryOpType(op.op, right);
		else
			return op.type = null;
	}

	@Override
	public Type visit(Expression.LogicalBinaryOp op, Type left)
	{
		Type right = op.right.accept(this);
		if(left != null)
			return op.type = left.binaryOpType(op.op, right);
		else
			return op.type = null;
	}

	@Override
	public Type visit(Expression.UnaryOp op, Type sub)
	{
		return op.type = sub != null ? sub.unaryOpType(op.op) : null;
	}

	@Override
	public Type visit(Expression.Call call, Type what, List<Type> args)
	{
		return call.type = what.callType(args);
	}

	@Override
	public Type visit(Expression.Ref ref)
	{
		return ref.type = this.context.lookup(ref.name);
	}

	@Override
	public Type visit(Expression.Access accessor, Type struct)
	{
		return accessor.type = struct.access(accessor.item);
	}

	@Override
	public Type visit(Expression.List_ list, List<? extends Type> args)
	{
		return list.type = new TypeList();
	}

	/**
	 * If all elements are of the same type, return this type, otherwise null.
	 */
	public Type elementType(Expression.List_ list)
	{
		for (Expression el: list.args) {
			Type t = el.accept(this);
			if (t == null)
				return list.type = null;
			if (list.type != null && !t.getClass().equals(list.type.getClass()))
				return list.type = null;
			list.type = t;
		}
		return list.type;
	}

	@Override
	public Type visit(Expression.Map_ map, List<Map.Entry<Type,Type>> args)
	{
		return map.type = new TypeMap();
	}

	@Override
	public Type visit(Expression.Index op, Type seq, Type index)
	{
		if (index != null && !(index instanceof TypeInt))
			throw new ExpressionFault.TypeError();

		if (seq != null && !(seq instanceof TypeList || seq instanceof TypeString))
			throw new ExpressionFault.TypeError();

		if (op.item instanceof Expression.List_)
			op.type = this.elementType((Expression.List_)op.item);
		else if (seq instanceof TypeString)
			op.type = seq;
		else
			op.type = null;

		return op.type;
	}

	@Override
	public Type visit(Expression.Slice op, Type seq, Type start, Type stop, Type step)
	{
		if (start != null && !(start instanceof TypeInt))
			throw new ExpressionFault.TypeError();
		if (stop != null && !(stop instanceof TypeInt))
			throw new ExpressionFault.TypeError();
		if (step != null && !(step instanceof TypeInt))
			throw new ExpressionFault.TypeError();

		if (seq != null && !(seq instanceof TypeList || seq instanceof TypeString))
			throw new ExpressionFault.TypeError();

		if (op.item instanceof Expression.List_)
			op.type = this.elementType((Expression.List_)op.item);
			// XXX: is this correct?
		else if (seq instanceof TypeString)
			op.type = seq;
		else
			op.type = null;

		return op.type;
	}

	@Override
	public Type visit(Expression.Const val)
	{
		return val.type = val.value;
	}

	@Override
	public Type visit(Expression.Ternary op, Type cond)
	{
		Type a = op.a.accept(this);
		Type b = op.b.accept(this);
		if (a.getClass().equals(b.getClass())) // XXX: what if a or b is null?
			return op.type = a;
		else
			return op.type = null;
	}

	@Override
	public Type visit(Expression.Assign op)
	{
		return op.type = op.value.accept(this);
	}
}
