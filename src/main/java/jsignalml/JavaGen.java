package jsignalml;
import static java.lang.String.format;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.sun.codemodel.JTryBlock;
import com.sun.codemodel.JCatchBlock;
import com.sun.codemodel.JVar;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.writer.SingleStreamCodeWriter;
import com.sun.codemodel.writer.FileCodeWriter;

/*
	double duration_of_data_record = null;
	public double get_duration_of_data_record() {
		if (duration_of_data_record == null)
			duration_of_data_record = _get_duration_of_data_record();
		return duration_of_data_record;
	}
	double _get_duration_of_data_record() {
		long offset = 244;
		Type.String str = (Type.String) buffer.read(new BitForm.Str(8), offset);
		return new Type.Float().make(str).getValue();
	}
*/

public class JavaGen {
	public static final String PREFIX = "_jsignalml_";
	static String makeIdentifier(String name)
	{
		return PREFIX + name;
	}
	static String makeGetter(String name)
	{
		return PREFIX + "get_" + name;
	}
	static String makeGetterImpl(String name)
	{
		return PREFIX + "_get_" + name;
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
		this.mainMethod(this.root);
		this.openMethod(this.root);
		this.getSetMethod(this.root);
		this.getCurrentFilenameMethod(this.root);
		this.getFormatDescriptionMethod(this.root);
		this.getFormatIDMethod(this.root);
		this.closeMethod(this.root);
		return klass;
	}

	public JMethod mainMethod(Context context)
	{
		final JDefinedClass klass = context.klass;
		final JMethod main = klass.method(JMod.STATIC | JMod.PUBLIC,
						  klass.owner().VOID, "main");
		final JVar args = main.varParam(String.class, "args");

		final JInvocation bc_configure =
			klass.owner().ref(BasicConfigurator.class)
			.staticInvoke("configure");

		JBlock body = main.body();
		body.add(bc_configure);

		JVar reader = body.decl(context.klass, "reader",
					JExpr._new(klass));
		JExpression file = JExpr._new(klass.owner().ref(File.class))
			.arg(args.component(JExpr.lit(0)));
		body.add(reader.invoke("open").arg(file));
		JExpression input = reader.invoke(makeGetter("readTestConv"));
		body.add(klass.owner().ref(System.class).staticRef("out")
			 .invoke("println").arg(input) );
		return main;
	}

	public JMethod openMethod(Context context)
	{
		final JDefinedClass klass = context.klass;
		final JType mybuffer = klass.owner().ref(MyBuffer.class);
		final JMethod open = klass.method(JMod.PUBLIC, klass.owner().VOID, "open");
		final JVar arg = open.param(File.class, "filename");
	        JFieldVar buffer = klass.field(JMod.NONE, mybuffer, "buffer");
		JTryBlock tryblock = open.body()._try();
		tryblock.body().assign(JExpr.ref(JExpr._this(), buffer),
				       JExpr._new(mybuffer).arg(arg));

		final JClass fnfe = klass.owner().ref(FileNotFoundException.class);
		final JClass ioe = klass.owner().ref(IOException.class);
		final JClass efee = klass.owner().ref(ExpressionFault.ExternalError.class);
		tryblock._catch(fnfe).body()
			._throw(JExpr._new(efee).arg(JExpr.ref("_x")));
		tryblock._catch(ioe).body()
			._throw(JExpr._new(efee).arg(JExpr.ref("_x")));

		return open;
	}

	public JMethod getSetMethod(Context context)
	{
		final JDefinedClass klass = context.klass;
		final JMethod method = klass.method(JMod.PUBLIC,
						    klass.owner().ref(ChannelSet.class),
						    "get_set");
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod getCurrentFilenameMethod(Context context)
	{
		final JDefinedClass klass = context.klass;
		final JMethod method = klass.method(JMod.PUBLIC,
						    klass.owner().ref(File.class),
						    "getCurrentFilename");
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod getFormatDescriptionMethod(Context context)
	{
		final JDefinedClass klass = context.klass;
		final JMethod method = klass.method(JMod.PUBLIC,
						    klass.owner().ref(String.class),
						    "getFormatDescription");
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod getFormatIDMethod(Context context)
	{
		final JDefinedClass klass = context.klass;
		final JMethod method = klass.method(JMod.PUBLIC,
						    klass.owner().ref(String.class),
						    "getFormatID");
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod closeMethod(Context context)
	{
		final JDefinedClass klass = context.klass;
		final JMethod method = klass.method(JMod.PUBLIC,
						    klass.owner().VOID,
						    "close");
		return method;
	}

	public JMethod exprParam(Context context, String ident,
				 Type type, Expression expr)
	{
		final JDefinedClass klass = context.klass;

		Class<? extends JavaType> javatype = convertType(type);
		if (javatype == null)
			javatype = JavaType.class;

		final JMethod impl = klass.method(JMod.NONE, javatype,
						  makeGetterImpl(ident));
		impl.body()._return( expr.toJava(context) );

		return this.cacheParam(context, ident, impl);
	}

	public JMethod readParam(Context context, String ident,
				 Type type, BitForm format, int offset)
	{
		final String prefixed = makeIdentifier(ident);
		final JDefinedClass klass = context.klass;

		Class<? extends JavaType> javatype_ = convertType(type);
		if (javatype_ == null)
			javatype_ = JavaType.class;
		JClass javatype = klass.owner().ref(javatype_);

		final JMethod impl = klass.method(JMod.NONE, javatype,
						  makeGetterImpl(ident));
		final JBlock body = impl.body();
		body.directStatement(format("// type=%s", type));
		body.directStatement(format("// format=%s", format));
		JClass javatype2 = bitform2javatype(format, klass.owner());
		JExpression expr = bitform2j(format).invoke("read2")
			.arg(JExpr.ref(JExpr.ref(JExpr._this(), "buffer"),
				       "source"))
			.arg(JExpr.lit(offset));
		JVar input = body.decl(javatype2, "input", expr);
		JVar var = body.decl(javatype, "var",
				     javatype.staticInvoke("make").arg(input));
		body._return(var);

		return this.cacheParam(context, ident, impl);
	}

	public JMethod cacheParam(Context context, String ident, JMethod impl)
	{
		final String prefixed = makeIdentifier(ident);
		final JDefinedClass klass = context.klass;
		final JFieldVar stor = klass.field(JMod.NONE, impl.type(),
						   prefixed, JExpr._null());

		final JMethod getter = klass.method(JMod.PUBLIC, impl.type(),
						    makeGetter(ident));
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
		final String field_name = "duration_of_data_record";

		BasicConfigurator.configure();

		File outputdir = new File(args[0]);

		JavaGen gen = new JavaGen(new JCodeModel(), "Test");

		Expression expr = Processor.parse(args[1]);
		gen.exprParam(gen.root, field_name, new Type.Int(), expr);

		Expression expr2 = Processor.parse(field_name + "() + 1");
		gen.exprParam(gen.root, field_name+"2", new Type.Int(), expr2);
		gen.readParam(gen.root, "readTest", new Type.Int(),
			      new BitForm.Int.Int32.LE(), 25);
		gen.readParam(gen.root, "readTestConv", new Type.Int(),
			      new BitForm.Str(8), 0);

		gen.model.build(new SingleStreamCodeWriter(System.out));
		gen.model.build(new FileCodeWriter(outputdir));
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

	static JExpression bitform2j(BitForm format)
	{
		assert format != null;

		return JExpr.direct("new " + format.toString());
	}

	static JClass bitform2javatype(BitForm format, JCodeModel model)
	{
		assert format != null;

		Class<? extends JavaType> klass;
		if (format instanceof BitForm.Int)
			klass = JavaType.Int.class;
		else if (format instanceof BitForm.Str)
			klass = JavaType.Str.class;
		else
			throw new RuntimeException("format not implemented");
		return model.ref(klass);
	}
}
