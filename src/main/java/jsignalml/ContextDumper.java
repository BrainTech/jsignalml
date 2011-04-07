package jsignalml;
import jsignalml.codec.Context;
import jsignalml.codec.Signalml;
import jsignalml.codec.Param;

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

	@Override public Integer visit(Signalml.LoopClass node, String name, Integer parent)
	{
		dumper.put(parent + 1, "Loop XXX %s", name);
		return parent + 1;
	}

	@Override public Integer visit(Param node, String name, Integer parent)
	{
		dumper.put(parent + 1, "%s => %s", name, node.get());
		return parent + 1;
	}

	public static String dump(Context node)
	{
		final ContextDumper dumper = new ContextDumper();
		node.accept(dumper, "ROOT", 0);
		return dumper.dumper.getText();
	}
}
