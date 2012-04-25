package jsignalml;

import java.nio.FloatBuffer;
import java.util.List;

import jsignalml.codec.ChannelClass;
import jsignalml.codec.ChannelSetClass;
import jsignalml.codec.ConditionalClass;
import jsignalml.codec.Context;
import jsignalml.codec.FunctionParam;
import jsignalml.codec.OuterLoopClass;
import jsignalml.codec.Param;
import jsignalml.codec.Signalml;
import jsignalml.logging.Logger;

import org.apache.commons.lang.StringUtils;

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

	@Override public Integer visit(ConditionalClass.ElseIfBranchClass node, String name, Integer level)
	{
		return dumper.put(level, "%s => ElseIfCondition is %s\n", name,
				  node.getCondition().isTrue() ? "True" : "False");
	}

	@Override public Integer visit(Param<?> node, String name, Integer level)
	{
		return dumper.put(level, "%s => %s\n", name, node.get().repr());
	}

	@Override public Integer visit(ChannelSetClass node, String name, Integer level)
	{
		int nrOfChannels = node.getNumberOfChannels();
		if (nrOfChannels == 0) {
			node.createChannels();
		}
		return dumper.put(level, "ChannelSet %s number_of_channels=%d\n",
				  name, nrOfChannels);
	}

	@Override public Integer visit(ChannelClass node, String name, Integer level)
	{
		long count = node.getNumberOfSamples();
		int ans = dumper.put(level, "Channel %s name=%s length=%d\n", name,
				     node.getChannelName(), count);
		if (count < 3) throw new RuntimeException("Number of samples must be at least 3!");

		List<String> array = util.newLinkedList();

		for(long i: new long[]{0, 1, 2, 3, -1, count-3, count-2, count-1})
			array.add(i!=-1 ? "" + node.getSample(i) : "...");
		dumper.put(level+1, "[%s]\n", StringUtils.join(array, ", "));
		dumper.put(level+1, "samples 0..%d @ %d..%d\n", count-1,
			   node.mapSample(0).safeIntValue(),
			   node.mapSample(count-1).safeIntValue());
		FloatBuffer buffer = FloatBuffer.allocate((int)count);
		node.getSamples(buffer, 0);
		return ans;
	}

	@Override public Integer visit(FunctionParam<?> node, String name, Integer level)
	{
		return dumper.put(level, "%s => %s\n", name, node.get().repr());
	}

	public static String dump(Context node)
	{
		final ContextDumper dumper = new ContextDumper();
		node.accept(dumper, "ROOT", 0);
		return dumper.dumper.getText();
	}
	
	@Override public String toString() {
		return dumper.getText();
	}
	
}
