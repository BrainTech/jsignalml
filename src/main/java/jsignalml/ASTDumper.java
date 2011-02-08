package jsignalml;

import static java.lang.String.format;

/**
 * Produces a human readable representation of the AST.
 */
public class ASTDumper extends ASTVisitor<Integer> {
	final StringBuilder buffer = new StringBuilder();

	private void indent(int level)
	{
		for (int indent = 0; indent < level; indent++)
			this.buffer.append("    ");
	}

	private void put(int level, String fmt, Object...args)
	{
		this.indent(level + 1);
		this.buffer.append(format(fmt + "\n", args));
	}

	private void header(int level, ASTNode node, String display,
			    String extrafmt, Object...args)
	{
		this.indent(level + 1);
		this.buffer.append
			(format("%s [%s] %s ", display, node.id,
				node.parent == null ? "no parent"
				: format("parent=[%s]", node.parent.id)));
		this.buffer.append(format(extrafmt + "\n", args));
	}

	public static String dump(ASTNode node)
	{
		final ASTDumper dumper = new ASTDumper();
		node.accept(dumper, 0);
		return dumper.buffer.toString();
	}

	@Override
	public Integer visit(ASTNode.Signalml node, Integer parent)
	{
		this.header(parent, node, "signalml", "");
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.BinaryParam node, Integer parent)
	{
		this.header(parent, node, "param (read binary)", "");
		this.put(parent + 1, "* format=%s", node.format);
		this.put(parent + 1, "* offset=%s", node.offset);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.ExprParam node, Integer parent)
	{
		this.header(parent, node, "param (expression)", "");
		this.put(parent + 1, "* expression=%s", node.expr);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.Assert node, Integer parent)
	{
		this.header(parent, node, "assert", "");
		this.put(parent + 1, "* expression=%s", node.expr);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.FileHandle node, Integer parent)
	{
		this.header(parent, node, "file", "filename=%s", node.filename);
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.DataHandle node, Integer parent)
	{
		this.header(parent, node, "data", "");
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.Positional node, Integer parent)
	{
		this.header(parent, node, "arg", "type=%s", node.type.getClass());
		return parent + 1;
	}
}
