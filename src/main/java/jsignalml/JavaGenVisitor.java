package jsignalml;

import java.util.List;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
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

	JavaGenVisitor()
	{
		this(new JCodeModel(), nullResolver);
	}

	public interface JavaNameResolver {
		/**
		 * Return a JInvocation which can be used to call
		 * item named id.
		 */
		JInvocation call(String id);
	}

	static class NullResolver implements JavaNameResolver {
		public JInvocation call(String id)
		{
			throw new ExpressionFault.NameError(id);
		}
	}
	static JavaNameResolver nullResolver = new NullResolver();

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
			JClass int_t = this.codemodel.ref(JavaType.Int.class);
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
	public JExpression visit(Expression.Call fun, List<? extends JExpression> args)
	{
		final JInvocation inv = context.call(fun.name);
		for (JExpression arg: args)
			inv.arg(arg);
		return inv;
	}

	@Override
	public JExpression visit(Expression.List_ list, List<? extends JExpression> args)
	{
		JInvocation cons = JExpr._new(this.codemodel.ref(JavaType.List.class));

		for (JExpression arg: args)
			cons.arg(arg);

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
		Class<? extends JavaType> type;
		JExpression repr;

		// XXX

		if (val.value instanceof Type.Int) {
			type = JavaType.Int.class;
			// TODO: check if representation is not outside range
			repr = JExpr.lit(((Type.Int)val.value).getValue());
		} else if (val.value instanceof Type.Float) {
			type = JavaType.Float.class;
			repr = JExpr.lit(((Type.Float)val.value).getValue());
		} else if (val.value instanceof Type.String) {
			type = JavaType.Str.class;
			repr = JExpr.lit(((Type.String)val.value).getValue());
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
