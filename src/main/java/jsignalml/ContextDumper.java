package jsignalml;
import jsignalml.codec.Context;
import jsignalml.codec.Signalml;
import jsignalml.codec.Param;
import jsignalml.codec.OuterLoopClass;

/**
 * Produces a human readable representation of the Context tree.
 */
public class ContextDumper extends ContextVisitor<Integer> {
	TreeDumper dumper = new TreeDumper();

	@Override public Integer visit(Signalml node, String name, Integer parent)
	{
		dumper.put(parent + 1, "Signalml %s", name);
		return parent + 1;
	}

	@Override public Integer visit(Signalml.FileClass node, String name, Integer parent)
	{
		dumper.put(parent + 1, "File %s", name);
		return parent + 1;
	}

	@Override public Integer visit(OuterLoopClass node, String name, Integer parent)
	{
		dumper.put(parent + 1, "%s => loop", name);
		return parent + 1;
	}

	@Override public Integer visit(OuterLoopClass.LoopClass node,
				       String name, Integer parent)
	{
		dumper.put(parent + 1, "%s => %s", name, node);
		return parent + 1;
	}

	@Override public Integer visit(Param node, String name, Integer parent)
	{
		dumper.put(parent + 1, "%s => %s", name, node.get().repr());
		return parent + 1;
	}

	public static String dump(Context node)
	{
		final ContextDumper dumper = new ContextDumper();
		node.accept(dumper, "ROOT", 0);
		return dumper.dumper.getText();
	}
}
