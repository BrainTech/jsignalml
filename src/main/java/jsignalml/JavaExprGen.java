package jsignalml;

import java.util.List;
import java.util.Map;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JOp;

/**
 * Create JExpression representations of Expressions. The implementation is
 * based on Type.
 */
public class JavaExprGen extends ExpressionVisitor<JExpression> {

	final JCodeModel model;
	final JavaNameResolver context;

	final JClass Type_t;
	final JClass TypeInt_t;
	final JClass TypeFloat_t;
	final JClass TypeString_t;
	final JClass TypeList_t;
	final JClass TypeMap_t;
	final JClass TypeBool_t;
	final JClass TypeBytes_t;

	JavaExprGen(JCodeModel model, JavaNameResolver context)
	{
		this.model = model;
		this.context = context;

		this.Type_t = model.ref(Type.class);
		this.TypeInt_t = model.ref(TypeInt.class);
		this.TypeFloat_t = model.ref(TypeFloat.class);
		this.TypeString_t = model.ref(TypeString.class);
		this.TypeList_t = model.ref(TypeList.class);
		this.TypeMap_t = model.ref(TypeMap.class);
		this.TypeBool_t = model.ref(TypeBool.class);
		this.TypeBytes_t = model.ref(TypeBytes.class);
	}

	JavaExprGen(ASTNode context)
	{
		this(new JCodeModel(), new
		     JavaClassGen().createResolver(context, null, JavaClassGen.GET));
	}

	public interface JavaNameResolver {
		/**
		 * Return a JExpression which can be used to call item named
		 * id. In case of functions, the JExpression shall be a
		 * JInvocation, to which args can be added.
		 */
		JExpression lookup(String id);
	}

	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////

	@Override
	public JExpression visit(Expression.BinaryOp op,
				 JExpression left, JExpression right)
	{
		if (op.op.javaMethod == "cmp") {
			final JExpression cmp_res =
				left.invoke("compareTo").arg(right);

			final JExpression cond;
			switch(op.op){
			case EQ: cond = cmp_res.eq(JExpr.lit(0)); break;
			case NE: cond = cmp_res.ne(JExpr.lit(0)); break;
			case LT: cond = cmp_res.lt(JExpr.lit(0)); break;
			case GT: cond = cmp_res.gt(JExpr.lit(0)); break;
			case LE: cond = cmp_res.lte(JExpr.lit(0)); break;
			case GE: cond = cmp_res.gte(JExpr.lit(0)); break;
			default: throw new RuntimeException();
			}

			return JOp.cond(cond,
					TypeInt_t.staticRef("True"),
					TypeInt_t.staticRef("False"));
		} else {
			return left.invoke(op.op.javaMethod).arg(right);
		}

	}

	@Override
	public JExpression visit(Expression.LogicalBinaryOp op, JExpression left)
	{
		JExpression right = op.right.accept(this);
		switch (op.op) {
		case LOG_AND:
			return JOp.cond(left.invoke("isTrue"), right, left);
		case LOG_OR:
			return JOp.cond(left.invoke("isTrue"), left, right);
		default:
			throw new RuntimeException();
		}
	}

	@Override
	public JExpression visit(Expression.UnaryOp op, JExpression sub)
	{
		return JExpr.invoke(sub, op.op.javaMethod);
	}

	@Override
	public JExpression visit(Expression.Call fun, JExpression what, List<JExpression> args)
	{
 		final JInvocation inv = what.invoke("call");
		for (JExpression arg: args)
			inv.arg(arg);
		return inv;
	}

	@Override
	public JExpression visit(Expression.Ref ref)
	{
		return context.lookup(ref.name);
	}

	@Override
	public JExpression visit(Expression.Access accessor, JExpression struct)
	{
		return struct.invoke("access").arg(accessor.item);
	}

	@Override
	public JExpression visit(Expression.List_ list, List<? extends JExpression> args)
	{
		JInvocation cons = JExpr._new(TypeList_t);

		for (JExpression arg: args)
			cons.arg(arg);

		return cons;
	}

	@Override
	public JExpression visit(Expression.Map_ map,
				 List<Map.Entry<JExpression, JExpression>> args)
	{
		JInvocation cons = JExpr._new(TypeMap_t);

		for (Map.Entry<JExpression, JExpression> entry: args) {
			cons.arg(entry.getKey());
			cons.arg(entry.getValue());
		}

		return cons;
	}

	@Override
	public JExpression visit(Expression.Index op, JExpression seq, JExpression index)
	{
		return JExpr.invoke(seq, "index").arg(index);
	}

	@Override
	public JExpression visit(Expression.Slice op, JExpression seq,
				 JExpression start, JExpression stop, JExpression step)
	{
		if(start == null)
			start = JExpr._null();
		if(stop == null)
			stop = JExpr._null();
		if(step == null)
			step = JExpr._null();
		return JExpr.invoke(seq, "slice").arg(start).arg(stop).arg(step);
	}

	@Override
	public JExpression visit(Expression.Const val)
	{
		final Class<? extends Type> type;
		final JExpression repr;

		// XXX

		if (val.value instanceof TypeInt) {
			type = TypeInt.class;
			// TODO: check if representation is not outside range
			repr = JExpr.lit(((TypeInt)val.value).safeIntValue()); // XXX
		} else if (val.value instanceof TypeFloat) {
			type = TypeFloat.class;
			repr = JExpr.lit(((TypeFloat)val.value).getValue());
		} else if (val.value instanceof TypeString) {
			type = TypeString.class;
			repr = JExpr.lit(((TypeString)val.value).getValue());
		} else if (val.value instanceof TypeBool) {
			type = TypeBool.class;
			repr = JExpr.lit(((TypeBool)val.value).getValue());
		} else if (val.value instanceof TypeBytes) {
			type = TypeBytes.class;
			repr = TypeBytes.ByteSequence.lit(((TypeBytes)val.value).getValue());
		} else {
			throw new RuntimeException();
		}

		return JExpr._new(this.model.ref(type)).arg(repr);
	}

	@Override
	public JExpression visit(Expression.Ternary op, JExpression cond)
	{
		return JOp.cond(cond.invoke("isTrue"), op.a.accept(this), op.b.accept(this));
	}

	@Override
	public JExpression visit(Expression.Assign op)
	{
		// XXX
		return op.value.accept(this);
	}
}
