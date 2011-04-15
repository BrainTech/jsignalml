package jsignalml;
import jsignalml.codec.Context;
import jsignalml.codec.Signalml;
import jsignalml.codec.OuterLoopClass;
import jsignalml.codec.ConditionalClass;
import jsignalml.codec.Param;
import jsignalml.codec.FunctionParam;
import jsignalml.codec.ChannelSetClass;
import jsignalml.codec.ChannelClass;

/**
 * Produces a human readable representation of the Context tree.
 */
public class ContextDumper implements ContextVisitor<Integer> {
	final TreeDumper dumper = new TreeDumper();

	@Override public Integer visit(Signalml node, String name, Integer level)
	{
		return dumper.put(level, "Signalml %s\n", name);
	}

	@Override public Integer visit(Signalml.FileClass node, String name, Integer level)
	{
		return dumper.put(level, "File %s\n", name);
	}

	@Override public Integer visit(OuterLoopClass node, String name, Integer level)
	{
		return dumper.put(level, "%s => loop\n", name);
	}

	@Override public Integer visit(OuterLoopClass.LoopClass node,
				       String name, Integer level)
	{
		return dumper.put(level, "%s => %s\n", name, node);
	}

	@Override public Integer visit(ConditionalClass node, String name, Integer level)
	{
		return dumper.put(level, "%s => Condition is %s\n", name,
				  node.getCondition().isTrue() ? "True" : "False");
	}

	@Override public Integer visit(ConditionalClass.ElseBranchClass node,
				       String name, Integer level)
	{
		// do nothing
		return level;
	}

	@Override public Integer visit(Param node, String name, Integer level)
	{
		return dumper.put(level, "%s => %s\n", name, node.get().repr());
	}

	@Override public Integer visit(ChannelSetClass node, String name, Integer level)
	{
		return dumper.put(level, "Channelset %s\n", name);
	}

	@Override public Integer visit(ChannelClass node, String name, Integer level)
	{
		return dumper.put(level, "Channel %s\n", name);
	}

	@Override public Integer visit(FunctionParam node, String name, Integer level)
	{
		return dumper.put(level, "%s => %s\n", name, node.get().repr());
	}

	public static String dump(Context node)
	{
		final ContextDumper dumper = new ContextDumper();
		node.accept(dumper, "ROOT", 0);
		return dumper.dumper.getText();
	}
}
