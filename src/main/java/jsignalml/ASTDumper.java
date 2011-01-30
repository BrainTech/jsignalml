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

	public static String dump(ASTNode node)
	{
		final ASTDumper dumper = new ASTDumper();
		node.accept(dumper, 0);
		return dumper.buffer.toString();
	}

	@Override
	public Integer visit(ASTNode.Signalml node, Integer parent)
	{
		this.indent(parent + 1);
		this.buffer.append(format("signalml\n"));
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.BinaryParam node, Integer parent)
	{
		this.indent(parent + 1);
		this.buffer.append(format("param (read binary)\n"));
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.ExprParam node, Integer parent)
	{
		this.indent(parent + 1);
		this.buffer.append(format("param (expression)\n"));
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.Assert node, Integer parent)
	{
		this.indent(parent + 1);
		this.buffer.append(format("assert\n"));
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.FileHandle node, Integer parent)
	{
		this.indent(parent + 1);
		this.buffer.append(format("file\n"));
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.DataHandle node, Integer parent)
	{
		this.indent(parent + 1);
		this.buffer.append(format("data\n"));
		return parent + 1;
	}

	@Override
	public Integer visit(ASTNode.Positional node, Integer parent)
	{
		this.indent(parent + 1);
		this.buffer.append(format("arg\n"));
		return parent + 1;
	}
}
