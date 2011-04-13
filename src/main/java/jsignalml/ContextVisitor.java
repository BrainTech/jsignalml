package jsignalml;
import jsignalml.codec.Signalml;
import jsignalml.codec.Signalml.FileClass;
import jsignalml.codec.OuterLoopClass;
import jsignalml.codec.OuterLoopClass.LoopClass;
import jsignalml.codec.ConditionalClass;
import jsignalml.codec.ConditionalClass.ElseBranchClass;
import jsignalml.codec.Param;
import jsignalml.codec.FunctionParam;

public interface ContextVisitor<T> {
	T visit(Signalml node, String name, T parent);
	T visit(FileClass node, String name, T parent);
	T visit(OuterLoopClass node, String name, T parent);
	T visit(LoopClass node, String name, T parent);
	T visit(ConditionalClass node, String name, T parent);
	T visit(ElseBranchClass node, String name, T parent);
	T visit(Param node, String name, T parent);
	T visit(FunctionParam node, String name, T parent);
}
