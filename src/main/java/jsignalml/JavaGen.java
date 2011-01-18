package jsignalml;
import java.io.File;
import org.apache.log4j.BasicConfigurator;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JType;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JVar;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JClassAlreadyExistsException;

import com.sun.codemodel.writer.SingleStreamCodeWriter;

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

	JCodeModel model;
	Context root;

	public JavaGen(JCodeModel model, String name)
		throws JClassAlreadyExistsException
	{
		this.model = model;
		this.signalmlCodec(name);
	}

	public JDefinedClass signalmlCodec(String name)
		throws JClassAlreadyExistsException
	{
		final JDefinedClass klass = this.model._class(name);
		this.root = new Context(klass, null, name);
		klass._implements(jsignalml.Source.class);
		final JMethod main = klass.method(JMod.STATIC | JMod.PUBLIC,
						  this.model.VOID, "main");
		JVar args = main.varParam(String.class, "args");

		JInvocation bc_configure = this.model.ref(BasicConfigurator.class).staticInvoke("configure");

		JBlock mainbody = main.body();
		mainbody.add(bc_configure);

		JVar reader = mainbody.decl(klass, "reader", JExpr._new(klass));
		JExpression file = JExpr._new(this.model.ref(File.class)).arg(args.component(JExpr.lit(0)));
		mainbody.add(JExpr.invoke(reader, "open").arg(file));

		return klass;
	}



	public JMethod accessMethod(Context context, String ident,
				    Type type, Expression expr)
	{
		final String prefixed = makeIdentifier(ident);
		final JDefinedClass klass = context.klass;

		Class<? extends JavaType> javatype = convertType(type);
		if (javatype == null)
			javatype = JavaType.class;

		final JMethod impl = klass.method(JMod.NONE, javatype,
							  "_get" + prefixed);
		impl.body()._return( expr.toJava(context) );

		final JFieldVar stor = klass.field(JMod.NONE, javatype, prefixed, JExpr._null());

		final JMethod getter = klass.method(JMod.PUBLIC, javatype, "get_" + prefixed);
		final JBlock then = getter.body()._if(stor.eq(JExpr._null()))._then();
		then.assign(stor, JExpr.invoke(impl));
		getter.body()._return(stor);

		return getter;
	}

	public static void main(String...args)
		throws jsignalml.SyntaxError,
		       java.io.IOException,
		       JClassAlreadyExistsException
	{
		BasicConfigurator.configure();

		Expression expr = Processor.parse(args[0]);

		SingleStreamCodeWriter out = new SingleStreamCodeWriter(System.out);
		JavaGen gen = new JavaGen(new JCodeModel(), "Test");
		gen.accessMethod(gen.root,
				 "duration_of_data_record", new Type.Int(), expr);
		gen.model.build(out);
	}


	/* Those two static classes go here and not in JavaType, because it's an
	 * interface and cannot contain methods. They cannot go into Type
	 * either, in order to keep Type clean from Java implementation
	 * details.
	 */
	static Class<? extends JavaType> convertType(Type type)
	{
		if(type == null)
			return null;
		if(type instanceof Type.Int)
			return JavaType.Int.class;
		if(type instanceof Type.Float)
			return JavaType.Float.class;
		if(type instanceof Type.String)
			return JavaType.Str.class;
		if(type instanceof Type.List)
			return JavaType.List.class;
		throw new RuntimeException("unknown Type");
	}
	static Type unconvertType(Class<? extends JavaType> type)
	{
		if(type == null)
			return null;
		if(type.equals(JavaType.Int.class))
			return new Type.Int();
		if(type.equals(JavaType.Float.class))
			return new Type.Float();
		if(type.equals(JavaType.Str.class))
			return new Type.String();
		if(type.equals(JavaType.List.class))
			return new Type.List();
		throw new RuntimeException("unknown JavaType");
	}
}
