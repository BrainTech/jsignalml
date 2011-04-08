package jsignalml;
import jsignalml.codec.Signalml;
import jsignalml.codec.Param;
import jsignalml.codec.OuterLoopClass;
import jsignalml.codec.OuterLoopClass.LoopClass;

public abstract class ContextVisitor<T> {
	public abstract T visit(Signalml node, String name, T parent);
	public abstract T visit(Signalml.FileClass node, String name, T parent);
	public abstract T visit(OuterLoopClass node, String name, T parent);
	public abstract T visit(LoopClass node, String name, T parent);
	public abstract T visit(Param node, String name, T parent);
}
