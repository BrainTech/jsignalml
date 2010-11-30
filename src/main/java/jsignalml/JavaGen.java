package jsignalml;

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
	public String accessMethod(MyStringBuilder o, String ident,
			    Class<? extends Type> type, Expression expr)
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
			throw new RuntimeException();

		o.line("%s %s = null;", stortype, ident);
		o.block("public %s get_%s()",  stortype, ident);
		o.line("if (%s == null)", ident).indent();
		o.line("%s = _get_%s();", ident, ident).dedent();
		o.line("return %s;", ident);
		o.deblock();
		o.block("%s _get_%s()", stortype, ident);
		o.line("return %s;", expr.toJava());
		o.deblock();
		return o.print();
	}
}
