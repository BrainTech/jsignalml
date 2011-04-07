package jsignalml;
import jsignalml.codec.Signalml;
import jsignalml.codec.Param;

public abstract class ContextVisitor<T> {
	public abstract T visit(Signalml node, String name, T parent);
	public abstract T visit(Signalml.FileClass node, String name, T parent);
	public abstract T visit(Signalml.LoopClass node, String name, T parent);
	public abstract T visit(Param node, String name, T parent);
}
