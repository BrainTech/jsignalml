package jsignalml;

import static java.lang.String.format;

/**
 * Produces a human readable representation of the AST.
 */
public class ASTDumper extends ASTVisitor<Integer> {
	final private TreeDumper dumper = new TreeDumper();

	private int header(int level, ASTNode node, String display,
			    String extrafmt, Object...args)
	{
		this.dumper.put(level,
				"%s [%s] %s ", display, node.id,
				node.parent == null
				? "no parent" : format("parent=[%s]", node.parent.id));
		this.dumper.format(extrafmt + "\n", args);
		return level + 1;
	}

	private int attrib(int level, String name, Object object)
	{
		this.dumper.put(level + 1, "* %s=%s\n", name, object);
		return level + 1;
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
		return this.header(parent, node, "signalml", "");
	}

	@Override
	public Integer visit(ASTNode.ChannelSet node, Integer parent)
	{
		return this.header(parent, node, "channelset", "");
	}

	@Override
	public Integer visit(ASTNode.Channel node, Integer parent)
	{
		return this.header(parent, node, "data", "format=%s mapping=%s",
				   node.format, node.mapping);
	}

	@Override
	public Integer visit(ASTNode.BinaryParam node, Integer parent)
	{
		this.header(parent, node, "param (read binary)", "");
		this.attrib(parent, "format", node.format);
		return this.attrib(parent, "offset", node.offset);
	}

	@Override
	public Integer visit(ASTNode.ExprParam node, Integer parent)
	{
		this.header(parent, node, "param (expression)", "");
		return this.attrib(parent, "expression", node.expr);
	}

	@Override
	public Integer visit(ASTNode.Assert node, Integer parent)
	{
		this.header(parent, node, "assert", "");
		return this.attrib(parent, "expression", node.expr);
	}

	@Override
	public Integer visit(ASTNode.FileHandle node, Integer parent)
	{
		return this.header(parent, node, "file", "filename=%s\n", node.filename);
	}

	@Override
	public Integer visit(ASTNode.ForLoop node, Integer parent)
	{
		return this.header(parent, node, "for-each", "%s in %s",
				   node.itername, node.sequence);
	}

	@Override
	public Integer visit(ASTNode.Conditional node, Integer parent)
	{
		return this.header(parent, node, "if", "%s", node.condition);
	}

	@Override
	public Integer visit(ASTNode.ElseBranch node, Integer parent)
	{
		return this.header(parent, node, "else", "");
	}

	@Override
	public Integer visit(ASTNode.Positional node, Integer parent)
	{
		return this.header(parent, node, "arg", "type=%s",
				   Type.typename(node.type));
	}
}
