package jsignalml;

import static java.lang.String.format;

public class MyStringBuilder {
	int indent;
	StringBuilder value;
	MyStringBuilder()
	{
		value = new StringBuilder();
		indent = 0;
	}

	MyStringBuilder line(String fmt, Object...args)
	{
		for (int i=0; i<indent; i++)
			this.value.append('\t');
		this.value.append(format(fmt, args));
		this.value.append('\n');
		return this;
	}

	MyStringBuilder block(String fmt, Object...args)
	{
		this.line(fmt + "{", args);
		return this.indent();
	}

	MyStringBuilder deblock()
	{
		return this.dedent().line("}");
	}

	MyStringBuilder indent()
	{
		this.indent++;
		return this;
	}

	MyStringBuilder dedent()
	{
		this.indent--;
		return this;
	}

	String print()
	{
		return this.value.toString();
	}
}
