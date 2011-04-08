package jsignalml;

/**
 * Helps to produces a human readable representation of a tree.
 */

public class TreeDumper {
	final StringBuilder buffer = new StringBuilder();

	/**
	 * Add indentation of level times "    " at current point.
	 */
	private void indent(int level)
	{
		for (int indent = 0; indent < level; indent++)
			this.buffer.append("    ");
	}

	public void put(int level, String fmt, Object...args)
	{
		this.indent(level + 1);
		this.buffer.append(String.format(fmt, args));
	}

	public void format(String fmt, Object...args)
	{
		this.buffer.append(String.format(fmt, args));
	}

	public String getText()
	{
		return this.buffer.toString();
	}
}
