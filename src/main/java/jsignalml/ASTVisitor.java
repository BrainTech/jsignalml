package jsignalml;

public abstract class ASTVisitor {
	public void visit(ASTNode.Signalml node){}
	public void visit(ASTNode.BinaryParam node){}
	public void visit(ASTNode.ExprParam node){}
	public void visit(ASTNode.FileHandle node){}
	public void visit(ASTNode.DataHandle node){}
	public void visit(ASTNode.BuiltinFunction node){}
	public void visit(ASTNode.Positional node){}
	public void visit(Builtins node){}
}
