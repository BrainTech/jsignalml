package jsignalml;

public class JavaGenVisitor extends ExpressionVisitor<JExpression> {

	final Context context;
	JavaGenVisitor(Context context){
		this.context = context;
	}

	JExpression visitBinaryOp(Expression.BinaryOp op,
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
			JClass int_t = this.context.klass.owner().ref(JavaType.Int.class);
			return JOp.cond(cond, int_t.staticRef("True"),
					int_t.staticRef("False"));
		} else {
			return left.invoke(op.op.javaMethod).arg(right);
		}

	}

public class EvalVisitor extends ExpressionVisitor<Type> {
	public Type visitBinaryOp(Expression.BinaryOp op, Type left, Type right)
	{
		return left.binaryOp(this.op, right);
	}
}

public class TypeVisitor extends ExpressionVisitor<Type> {
	public Type visitBinaryOp(Expression.BinaryOp op, Type left, Type right)
	{
		return left.binaryOpType(this.op, right);
	}
}