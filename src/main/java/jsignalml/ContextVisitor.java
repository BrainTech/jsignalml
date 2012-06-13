package jsignalml;

import jsignalml.codec.ChannelClass;
import jsignalml.codec.ChannelSetClass;
import jsignalml.codec.ConditionalClass;
import jsignalml.codec.FunctionParam;
import jsignalml.codec.Header;
import jsignalml.codec.OuterLoopClass;
import jsignalml.codec.OuterLoopClass.LoopClass;
import jsignalml.codec.Param;
import jsignalml.codec.Signalml;
import jsignalml.codec.Signalml.FileClass;

public interface ContextVisitor<T> {
	T visit(Signalml node, String name, T parent);
	T visit(FileClass node, String name, T parent);
	T visit(OuterLoopClass node, String name, T parent);
	T visit(LoopClass node, String name, T parent);
	T visit(ConditionalClass node, String name, T parent);
	T visit(ConditionalClass.ElseBranchClass node, String name, T parent);
	T visit(ConditionalClass.ElseIfBranchClass node, String name, T parent);
	T visit(Param<?> node, String name, T parent);
	T visit(FunctionParam<?> node, String name, T parent);
	T visit(ChannelSetClass node, String name, T parent);
	T visit(ChannelClass node, String name, T parent);
	T visit(Header node, String name, T parent);
}
