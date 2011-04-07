package jsignalml;

import static java.lang.String.format;

/**
 * Produces a human readable representation of the AST.
 */
public class ASTDumper extends ASTVisitor<Integer> {
	final private TreeDumper dumper = new TreeDumper();

	private void header(int level, ASTNode node, String display,
			    String extrafmt, Object...args)
	{
		this.dumper.put(level,
				"%s [%s] %s ", display, node.id,
				node.parent == null
				? "no parent" : format("parent=[%s]", node.parent.id));
		this.dumper.format(extrafmt + "\n", args);
	}

	public static String dump(ASTNode node)
	{
		final ASTDumper dumper = new ASTDumper();
		node.accept(dumper, 0);
		return dumper.dumper.getText();
	}

	@Override
	public Integer visit(ASTNode.Signalml node, Integer parent)
	{
		this.header(parent, node, "signalml", "");
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.ChannelSet node, Integer parent)
	{
		this.header(parent, node, "channelset", "");
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.Channel node, Integer parent)
	{
		this.header(parent, node, "channel", "");
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.BinaryParam node, Integer parent)
	{
		this.header(parent, node, "param (read binary)", "");
		this.dumper.put(parent + 1, "* format=%s", node.format);
		this.dumper.put(parent + 1, "* offset=%s", node.offset);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.ExprParam node, Integer parent)
	{
		this.header(parent, node, "param (expression)", "");
		this.dumper.put(parent + 1, "* expression=%s", node.expr);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.Assert node, Integer parent)
	{
		this.header(parent, node, "assert", "");
		this.dumper.put(parent + 1, "* expression=%s", node.expr);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.FileHandle node, Integer parent)
	{
		this.header(parent, node, "file", "filename=%s", node.filename);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.ForLoop node, Integer parent)
	{
		this.header(parent, node, "for-each", "%s in %s", node.itername, node.sequence);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.DataHandle node, Integer parent)
	{
		this.header(parent, node, "data", "format=%s", node.format);
		this.dumper.put(parent + 1, "* mapping=%s", node.mapping);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.Positional node, Integer parent)
	{
		this.header(parent, node, "arg", "type=%s", node.type.getClass());
		return parent + 1;
	}
}
