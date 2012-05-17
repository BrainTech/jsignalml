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
 * Create JExpression representations of Expressions. For numbers and strings
 * some java primitives are used. The rest of the implementation is based on
 * Type.
 */
public class JavaPrimitiveGen extends JavaExprGen {

	JavaPrimitiveGen(JCodeModel model, JavaNameResolver context)
	{
		super(model, context);
	}

	JavaPrimitiveGen(ASTNode context)
	{
		super(context);
	}

	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////

	JExpression binaryOp_int_int(Expression.BinaryOp op,
				     JExpression left, JExpression right)
	{
		switch(op.op) {
		case ADD: return left.plus(right);
		case SUB: return left.minus(right);
		case MUL: return left.mul(right);
		case DIV:
			JExpression fl_right =
				JExpr.cast(convertTypeToJClass_p(null, TypeFloat.I), right);
			return left.div(fl_right);
		case FLOORDIV:
			  return left.div(right);
		case EQ:  return left.eq(right);
		case NE:  return left.ne(right);
		case LT:  return left.lt(right);
		case GT:  return left.gt(right);
		case LE:  return left.lte(right);
		case GE:  return left.gte(right);
		default:
			  return null; // handle upstream
		}
	}

	@Override
	public JExpression visit(Expression.BinaryOp op,
				 JExpression left, JExpression right)
	{
		if (op.left.type instanceof TypeInt &&
		    op.right.type instanceof TypeInt) {
			JExpression expr = binaryOp_int_int(op, left, right);
			if (expr != null)
				return expr;
		}

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

			JClass int_t = this.model.ref(TypeInt.class);
			return JOp.cond(cond, int_t.staticRef("True"),
					      int_t.staticRef("False"));
		} else {
			return left.invoke(op.op.javaMethod).arg(right);
		}

	}

	@Override
	public JExpression visit(Expression.Const val)
	{
		final JExpression repr;

		if (val.value instanceof TypeInt) {
			// TODO: check if representation is not outside range
			repr = JExpr.lit(((TypeInt)val.value).safeIntValue()); // XXX
		} else if (val.value instanceof TypeFloat) {
			repr = JExpr.lit(((TypeFloat)val.value).getValue());
		} else if (val.value instanceof TypeString) {
			repr = JExpr.lit(((TypeString)val.value).getValue());
		} else if (val.value instanceof TypeBool) {
			repr = JExpr.lit(((TypeBool)val.value).getValue());
		} else {
			throw new ExpressionFault.Unsupported(val.getType().getClass(),
				"primitive evaluation. Type was not specified correctly, evaluation cannot be used"
				+" for unspecified or wrong types. Please check content of the constans: " + val + ".");
		}

		return repr;
	}

	JType convertTypeToJClass_p(ASTNode.ExprParam node, Type type)
	{
		if (type == null)
			throw new ExpressionFault.Unsupported(Type.class,
			   "primitive evaluation. Type was not specified correctly, evaluation cannot be used"
			   +" for unspecified or wrong types. Please check content of the expresion: " + node + ".");

		if (type instanceof TypeInt)
			return this.model.LONG;
		else if (type instanceof TypeFloat)
			return this.model.DOUBLE;
		else if (type instanceof TypeString)
			return this.model.ref(String.class);
		else if (type instanceof TypeBool)
			return this.model.BOOLEAN;
		else
			throw new ExpressionFault.Unsupported(type.getClass(),
				"primitive evaluation. Type was not specified correctly, evaluation cannot be used"
				+" for unspecified or wrong types. Please check content of the expresion: " + node + ".");
	}
}
