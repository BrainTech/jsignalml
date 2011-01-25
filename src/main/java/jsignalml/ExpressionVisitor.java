package jsignalml;

public abstract class ExpressionVisitor<T> {
	public T visitBinaryOp(Expression.BinaryOp op, T left, T right){}
	public T visitLogicalBinaryOp(Expression.LogicalBinaryOp op, T left, T right){}
	public T visitUnaryOp(Expression.UnaryOp op, T sub){}
	public T visitCall(Expression.Call fun, T...args){}
	public T visitList(Expression.List_ val){}
	public T visitIndex(Expression.Index op, T seq, T index){}
	public T visitConst(Expression.Const val){}
	public T visitTernary(Expression.Ternary op, T cond, T left, T right){}
	public T visitAssign(Expression.Assign op, T val){}
}
