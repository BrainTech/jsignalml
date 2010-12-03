package jsignalml;
import static java.lang.String.format;

public class MyStringBuilder {
	StringBuilder builder;
	int indent;
	MyStringBuilder(){
		builder =  new StringBuilder();
		indent = 0;
	}

	MyStringBuilder line(String fmt, Object...args)
	{
		for (int i=0; i<indent; i++)
			builder.append('\t');
		builder.append(format(fmt, args));
		builder.append('\n');
		return this;
	}

	MyStringBuilder block(String fmt, Object...args)
	{
		line(fmt + "{", args);
		return indent();
	}

	MyStringBuilder deblock()
	{
		return dedent().line("}");
	}

	MyStringBuilder indent()
	{
		indent++;
		return this;
	}

	MyStringBuilder dedent()
	{
		indent--;
		return this;
	}
}
