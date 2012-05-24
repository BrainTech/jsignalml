package jsignalml;

import static jsignalml.Type.typename;

import java.util.List;
import java.util.Map;

import jsignalml.logging.Logger;

/*
 * Walks the expression tree and tries to set the expression type, i.e. the type
 * of the value of the expression after evaluation in specific context, given by
 * the CallHelper parameter.
 *
 * type is set to null if not known.
 */
public class TypeVisitor extends ExpressionVisitor<Type> {
	public static final Logger log = new Logger(TypeVisitor.class);

	final CallHelper context;

	public TypeVisitor(CallHelper context)
	{
		this.context = context;
	}

	@Override
	public Type visit(Expression.BinaryOp op, Type left, Type right)
	{
		log.debug("checking binary op %s: %s, %s", op,
			  typename(left), typename(right));

		if (left != null)
			return op.setType(left.binaryOpType(op.op, right));
		else if (right != null)
			return op.setType(right.binaryOpTypeRight(op.op));
		else
			return op.setType(null);
	}

	@Override
	public Type visit(Expression.LogicalBinaryOp op, Type left)
	{
		Type right = op.right.accept(this);

		log.debug("checking logical binary op %s: %s, %s", op,
			  typename(left), typename(right));

		if (left != null)
			return op.setType(left.binaryOpType(op.op, right));
		else
			return op.setType(null);
	}

	@Override
	public Type visit(Expression.UnaryOp op, Type sub)
	{
		log.debug("checking unary op %s: %s", op, typename(sub));

		return op.setType(sub != null ? sub.unaryOpType(op.op) : null);
	}

	@Override
	public Type visit(Expression.Call call, Type what, List<Type> args)
	{
		log.debug("checking call %s: %s", call, typename(what));

		return call.setType(what.callType(args));
	}

	@Override
	public Type visit(Expression.Ref ref)
	{
		log.debug("checking ref %s", ref);
		Type type = this.context.lookup(ref.name);

		return ref.setType(type);
	}

	@Override
	public Type visit(Expression.Access accessor, Type struct)
	{
		log.debug("checking access %s: %s", accessor, typename(struct));

		return accessor.setType(struct == null ? null :
					struct.access(accessor.item) );
	}

	@Override
	public Type visit(Expression.List_ list, List<? extends Type> args)
	{
		log.debug("List");
		return list.setType(TypeList.I);
	}

	/**
	 * If all elements are of the same type, return this type, otherwise null.
	 */
	public Type elementType(Expression.List_ list)
	{
		for (Expression el: list.args) {
			Type t = el.accept(this);
			if (t == null)
				return list.setType(null);
			if (list.getType() != null && !t.getClass().equals(list.getType().getClass()))
				return list.setType(null);
			list.setType(t);
		}
		return list.getType();
	}

	@Override
	public Type visit(Expression.Map_ map, List<Map.Entry<Type,Type>> args)
	{
		log.debug("Map");
		return map.setType(TypeMap.I);
	}

	@Override
	public Type visit(Expression.Index op, Type seq, Type index)
	{
		log.debug("Index %s[%s]", typename(seq), typename(index));
		if (index != null && !(index instanceof TypeInt))
			throw new ExpressionFault.TypeError(index, new TypeInt());

		if (seq != null && !(seq instanceof TypeList || seq instanceof TypeString))
			throw new ExpressionFault.TypeError();

		if (op.item instanceof Expression.List_)
			op.setType(this.elementType((Expression.List_)op.item));
		else if (seq instanceof TypeString)
			op.setType(seq);
		else
			op.setType(null);

		return op.getType();
	}

	@Override
	public Type visit(Expression.Slice op, Type seq, Type start, Type stop, Type step)
	{
		log.debug("Index %s[%s:%s:%s]", typename(seq),
			  typename(start), typename(stop), typename(step));
		if (start != null && !(start instanceof TypeInt))
			throw new ExpressionFault.TypeError(start, new TypeInt());
		if (stop != null && !(stop instanceof TypeInt))
			throw new ExpressionFault.TypeError(stop, new TypeInt());
		if (step != null && !(step instanceof TypeInt))
			throw new ExpressionFault.TypeError(step, new TypeInt());

		if (seq != null && !(seq instanceof TypeList || seq instanceof TypeString))
			throw new ExpressionFault.TypeError();

		if (op.item instanceof Expression.List_)
			op.setType(this.elementType((Expression.List_)op.item));
			// XXX: is this correct?
		else if (seq instanceof TypeString)
			op.setType(seq);
		else
			op.setType(null);

		return op.getType();
	}

	@Override
	public Type visit(Expression.Const val)
	{
		log.debug("Const %s", val);
		return val.setType(val.value);
	}

	@Override
	public Type visit(Expression.Ternary op, Type cond)
	{
		log.debug("Ternary %s ? ...", typename(cond));

		final Type a = op.a.accept(this);
		final Type b = op.b.accept(this);
		final Type type;
		if (a == null || b == null)
			type = null;
		else if (a.getClass().isAssignableFrom(b.getClass()))
			type = a;
		else if (b.getClass().isAssignableFrom(a.getClass()))
			type = b;
		else
			type = null;
		return op.setType(type);
	}

	@Override
	public Type visit(Expression.Assign op)
	{
		log.debug("Assign");

		return op.setType(op.value.accept(this));
	}
}
