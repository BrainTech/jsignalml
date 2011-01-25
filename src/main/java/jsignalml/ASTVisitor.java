package jsignalml;
public abstract class ASTVisitor {
	public void visitSignalml(ASTNode.Signalml node){}
	public void visitBinaryParam(ASTNode.BinaryParam node){}
	public void visitExprParam(ASTNode.ExprParam node){}
	public void visitFileHandle(ASTNode.FileHandle node){}
	public void visitDataHandle(ASTNode.DataHandle node){}
	public void visitBuiltinFunction(ASTNode.BuiltinFunction node){}
	public void visitPositional(ASTNode.Positional node){}
}
