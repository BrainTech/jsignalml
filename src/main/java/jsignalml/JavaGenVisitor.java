package jsignalml;

import java.util.List;
import java.util.Map;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JStringLiteral;
import com.sun.codemodel.JOp;

/**
 * Create JExpression representations of Expressions.
 */
public class JavaGenVisitor extends ExpressionVisitor<JExpression> {

	final JCodeModel codemodel;
	final JavaNameResolver context;

	JavaGenVisitor(JCodeModel codemodel, JavaNameResolver context)
	{
		this.codemodel = codemodel;
		this.context = context;
	}

	JavaGenVisitor(ASTNode context)
	{
		this(new JCodeModel(), JavaGen.createResolver(context));
	}

	public interface JavaNameResolver {
		/**
		 * Return a JInvocation which can be used to call
		 * item named id.
		 */
		JInvocation lookup(String id);
	}

	static class NullResolver implements JavaNameResolver {
		@Override
		public JInvocation lookup(String id)
		{
			throw new ExpressionFault.NameError(id);
		}
	}

	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////

	@Override
	public JExpression visit(Expression.BinaryOp op,
				 JExpression left, JExpression right)
	{
		if (op.op.javaMethod == "cmp") {
			JExpression cmp_res = left.invoke("cmp").arg(right);
			JExpression cond;
			switch(op.op){
			case EQ: cond = cmp_res.eq(JExpr.lit(0)); break;
			case NE: cond = cmp_res.ne(JExpr.lit(0)); break;
			case LT: cond = cmp_res.lt(JExpr.lit(0)); break;
			case GT: cond = cmp_res.gt(JExpr.lit(0)); break;
			case LE: cond = cmp_res.lte(JExpr.lit(0)); break;
			case GE: cond = cmp_res.gte(JExpr.lit(0)); break;
			default: throw new RuntimeException();
			}
			JClass int_t = this.codemodel.ref(TypeInt.class);
			return JOp.cond(cond, int_t.staticRef("True"),
					int_t.staticRef("False"));
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
			return JOp.cond(left, right, left);
		case LOG_OR:
			return JOp.cond(left, left, right);
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
 		final JInvocation inv = (JInvocation) what;
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
		JInvocation cons = JExpr._new(this.codemodel.ref(TypeList.class));

		for (JExpression arg: args)
			cons.arg(arg);

		return cons;
	}

	@Override
	public JExpression visit(Expression.Map_ map,
				 List<Map.Entry<JExpression, JExpression>> args)
	{
		JInvocation cons = JExpr._new(this.codemodel.ref(TypeMap.class));

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
	public JExpression visit(Expression.Const val)
	{
		Class<? extends Type> type;
		JExpression repr;

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
		} else {
			throw new RuntimeException();
		}

		return JExpr._new(this.codemodel.ref(type)).arg(repr);
	}

	@Override
	public JExpression visit(Expression.Ternary op, JExpression cond)
	{
		return JOp.cond(cond, op.a.accept(this), op.b.accept(this));
	}

	@Override
	public JExpression visit(Expression.Assign op)
	{
		// XXX
		return op.value.accept(this);
	}
}
