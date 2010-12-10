package jsignalml;
import org.apache.log4j.BasicConfigurator;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMod;

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

	public static JMethod accessMethod(JDefinedClass klass, String ident,
					Class<? extends Type> type,
					Expression expr)
	{
		final String prefixed = makeIdentifier(ident);

		Class<? extends JavaType> javatype;
		if (type == null)
			javatype = JavaType.class;
		else if (type.equals(Type.Int.class))
			javatype = JavaType.Int.class;
		else if (type.equals(Type.Float.class))
			javatype = JavaType.Float.class;
		else if (type.equals(Type.String.class))
			javatype = JavaType.Str.class;
		else if (type.equals(Type.List.class))
			javatype = JavaType.List.class;
		else
			throw new RuntimeException("unkown type");

		final JMethod impl = klass.method(JMod.NONE, javatype,
						  "_get" + prefixed);
		impl.body()._return( JExpr._new(javatype).arg(expr.toJava(klass.owner())));

		final JFieldVar stor = klass.field(JMod.NONE, javatype, prefixed, JExpr._null());

		final JMethod getter = klass.method(JMod.PUBLIC, javatype, "get_" + prefixed);
		final JExpression assign = stor.assign(JExpr.invoke(impl));
		getter.body()._if(stor.eq(JExpr._null()))._then(assign);
		getter.body()._return(stor);

		return getter;
	}

	public static void main(String...args) throws jsignalml.SyntaxError
	{
		BasicConfigurator.configure();

		Expression expr = Processor.parse(args[0]);

		SingleStreamCodeWriter out = new SingleStreamCodeWriter(System.out);
		JCodeModel model = new JCodeModel();
		accessMethod(model._class("Test"),
			     "duration_of_data_record", Type.Int.class, expr);
		codeModel.build(out);
	}
}
