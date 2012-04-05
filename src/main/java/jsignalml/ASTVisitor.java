package jsignalml;

public abstract class ASTVisitor<T> {
	public T visit(ASTNode.Signalml node, T parent){ return parent; }
	public T visit(ASTNode.ChannelSet node, T parent){ return parent; }
	public T visit(ASTNode.Channel node, T parent){ return parent; }
	public T visit(ASTNode.BinaryParam node, T parent){ return parent; }
	public T visit(ASTNode.ExprParam node, T parent){ return parent; }
	public T visit(ASTNode.Assert node, T parent){ return parent; }
	public T visit(ASTNode.FileHandle node, T parent){ return parent; }
	public T visit(ASTNode.BuiltinFunction node, T parent){ return parent; }
	public T visit(ASTNode.Positional node, T parent){ return parent; }
	public T visit(ASTNode.ForLoop node, T parent){ return parent; }
	public T visit(ASTNode.Itername node, T parent){ return parent; }
	public T visit(ASTNode.Conditional node, T parent){ return parent; }
	public T visit(ASTNode.ElseBranch node, T parent){ return parent; }
	public T visit(ASTNode.ElseIfBranch node, T parent){ return parent; }
	public T visit(ASTNode.TextParam node, T parent){ return parent; }
	public T visit(Builtins node, T parent){ return parent; }

	public String toString(){ return getClass().getSimpleName(); }
}
