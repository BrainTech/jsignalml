package jsignalml;
import jsignalml.codec.Signalml;
import jsignalml.codec.Param;
import jsignalml.codec.OuterLoopClass;
import jsignalml.codec.OuterLoopClass.LoopClass;
import jsignalml.codec.ConditionalClass;
import jsignalml.codec.ConditionalClass.ElseBranchClass;

public abstract class ContextVisitor<T> {
	public abstract T visit(Signalml node, String name, T parent);
	public abstract T visit(Signalml.FileClass node, String name, T parent);
	public abstract T visit(OuterLoopClass node, String name, T parent);
	public abstract T visit(LoopClass node, String name, T parent);
	public abstract T visit(ConditionalClass node, String name, T parent);
	public abstract T visit(ConditionalClass.ElseBranchClass node, String name, T parent);
	public abstract T visit(Param node, String name, T parent);
}
