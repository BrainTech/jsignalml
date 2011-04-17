package jsignalml;
import java.util.List;
import org.apache.commons.lang.StringUtils;

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
	static final Logger log = new Logger(ContextDumper.class);

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
		node.createChannels();
		return dumper.put(level, "ChannelSet %s number_of_channels=%d\n",
				  name, node.getNumberOfChannels());
	}

	@Override public Integer visit(ChannelClass node, String name, Integer level)
	{
		long count = node.getNumberOfSamples();
		int ans = dumper.put(level, "Channel %s name=%s length=%d\n", name,
				     node.getChannelName(), count);

		List<String> array = util.newLinkedList();
		for(long i: new long[]{0, 1, 2, 3, -1, count-3, count-1, count-1})
			array.add(i!=-1 ? "" + node.getSample(i) : "...");
		dumper.put(level+1, "[%s]\n", StringUtils.join(array, ", "));
		return ans;
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
