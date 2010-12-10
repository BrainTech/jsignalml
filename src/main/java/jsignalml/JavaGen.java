package jsignalml;
import org.apache.log4j.BasicConfigurator;

/*
	double duration_of_data_record = null;
	public double get_duration_of_data_record() {
		if (duration_of_data_record == null)
			duration_of_data_record = _get_duration_of_data_record();
		return duration_of_data_record;
	}
	double _get_duration_of_data_record() {
		long offset = 244;
		Type.String str = (Type.String) buffer.read(new BitForm.STR(8), offset);
		return new Type.Float().make(str).getValue();
	}
*/

public class JavaGen {
	public static final String PREFIX = "_jsignalml_";
	static String makeIdentifier(String name)
	{
		return PREFIX + name;
	}

	public static void accessMethod(MyStringBuilder o, String ident,
					Class<? extends Type> type,
					Expression expr)
	{
		String stortype;
		if (type == null)
			stortype = "JavaType";
		else if (type.equals(Type.Int.class))
			stortype = "JavaType.Int";
		else if (type.equals(Type.Float.class))
			stortype = "JavaType.Float";
		else if (type.equals(Type.String.class))
			stortype = "JavaType.Str";
		else if (type.equals(Type.List.class))
			stortype = "JavaType.List";
		else
			throw new RuntimeException("unkown type");

		String prefixed = makeIdentifier(ident);
		o.line("%s %s = null;", stortype, prefixed);
		o.block("public %s get_%s()",  stortype, ident);
		o.line("if (%s == null)", prefixed).indent();
		o.line("%s = _get_%s();", prefixed, ident).dedent();
		o.line("return %s;", prefixed);
		o.deblock();
		o.block("%s _get_%s()", stortype, ident);
		o.line("return new %s(%s);", stortype, expr.toJava());
		o.deblock();
	}

	public static void main(String...args) throws jsignalml.SyntaxError
	{
		BasicConfigurator.configure();

		MyStringBuilder o = new MyStringBuilder();
		Expression expr = Processor.parse(args[0]);
		accessMethod(o, "duration_of_data_record", Type.Int.class, expr);
		System.out.println(o.toString());
	}
}
