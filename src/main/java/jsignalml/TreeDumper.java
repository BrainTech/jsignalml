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

	public int put(int level, String fmt, Object...args)
	{
		this.indent(level);
		this.format(fmt, args);
		return level + 1;
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
