import static java.lang.String.format;

public class MyStringBuilder extends StringBuilder {
	int indent;
	MyStringBuilder(){
		indent = 0;
	}

	MyStringBuilder line(String fmt, Object...args)
	{
		for (int i=0; i<indent; i++)
			this.append('\t');
		this.append(format(fmt, args));
		this.append('\n');
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
}
