package jsignalml;

import java.util.List;

public abstract class ExpressionVisitor<T> {
	public T visit(Expression.BinaryOp op, T left, T right){ return null; }
	public T visit(Expression.LogicalBinaryOp op, T left){ return null; }
	public T visit(Expression.UnaryOp op, T sub){ return null; }
	public T visit(Expression.Call fun, T what, List<T> args){ return null; }
	public T visit(Expression.Ref ref){ return null; }
	public T visit(Expression.Access accessor, T struct){ return null; }
	public T visit(Expression.List_ list, List<? extends T> args){ return null; }
	public T visit(Expression.Index op, T seq, T index){ return null; }
	public T visit(Expression.Const val){ return null; }
	public T visit(Expression.Ternary op, T cond){ return null; }
	public T visit(Expression.Assign op){ return null; }
}
