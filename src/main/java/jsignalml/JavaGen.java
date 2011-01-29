package jsignalml;

import static java.lang.String.format;
import java.io.File;
import java.io.OutputStream;
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

public class JavaGen extends ASTVisitor<JDefinedClass> {
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

	public JavaGen()
		throws JClassAlreadyExistsException
	{
		this.model = new JCodeModel();
	}

	@Override
	public JDefinedClass visit(ASTNode.Signalml node, JDefinedClass dummy)
	{
		assert dummy == null;
		final String name = "signalml"; // XXX

		final JDefinedClass klass;
		try {
			klass = this.model._class(name);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._implements(jsignalml.Source.class);
		this.mainMethod(klass);
		this.openMethod(klass);
		this.getSetMethod(klass);
		this.getCurrentFilenameMethod(klass);
		this.getFormatDescriptionMethod(klass);
		this.getFormatIDMethod(klass);
		this.closeMethod(klass);
		return klass;
	}

	public JMethod mainMethod(JDefinedClass klass)
	{
		final JMethod main = klass.method(JMod.STATIC | JMod.PUBLIC,
						  klass.owner().VOID, "main");
		final JVar args = main.varParam(String.class, "args");

		final JInvocation bc_configure =
			this.model.ref(BasicConfigurator.class).staticInvoke("configure");

		JBlock body = main.body();
		body.add(bc_configure);

		JVar reader = body.decl(klass, "reader", JExpr._new(klass));
		JExpression file = JExpr._new(this.model.ref(File.class))
			.arg(args.component(JExpr.lit(0)));
		body.add(reader.invoke("open").arg(file));
		JExpression input = reader.invoke(makeGetter("readTestConv"));
		body.add(this.model.ref(System.class).staticRef("out")
			 .invoke("println").arg(input) );
		return main;
	}

	public JMethod fileConstructor(JDefinedClass klass)
	{
		JMethod method = klass.constructor(JMod.NONE);
		JVar filename = method.param(File.class, "filename");
		method.body().add(JExpr._this().invoke("open").arg(filename));
		return method;
	}

	public JMethod openMethod(JDefinedClass klass)
	{
		final JClass mybuffer = this.model.ref(MyBuffer.class);
		final JMethod open = klass.method(JMod.PUBLIC, this.model.VOID, "open");
		final JVar arg = open.param(File.class, "filename");
	        final JFieldVar buffer = klass.field(JMod.NONE, mybuffer, "buffer");
		open.body().assign(JExpr.ref(JExpr._this(), buffer),
				   mybuffer.staticInvoke("open").arg(arg));
		return open;
	}

	public JMethod getSetMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PUBLIC,
						    this.model.ref(ChannelSet.class),
						    "get_set");
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod getCurrentFilenameMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PUBLIC,
						    this.model.ref(File.class),
						    "getCurrentFilename");
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod getFormatDescriptionMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PUBLIC,
						    this.model.ref(String.class),
						    "getFormatDescription");
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod getFormatIDMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PUBLIC,
						    this.model.ref(String.class),
						    "getFormatID");
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod closeMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PUBLIC,
						    this.model.VOID,
						    "close");
		return method;
	}

	public JMethod exprParam(JDefinedClass klass, String ident,
				 Type type, Expression expr)
	{
		Class<? extends JavaType> javatype = convertType(type);
		if (javatype == null)
			javatype = JavaType.class;

		final JMethod impl = klass.method(JMod.NONE, javatype,
						  makeGetterImpl(ident));
		final JavaGenVisitor javagen = new JavaGenVisitor(this.model);
		impl.body()._return( expr.accept(javagen) );

		return this.cacheParam(klass, ident, impl);
	}

	public JMethod readParam(JDefinedClass klass, String ident,
				 Type type, BitForm format, int offset)
	{
		final String prefixed = makeIdentifier(ident);

		Class<? extends JavaType> javatype_ = convertType(type);
		if (javatype_ == null)
			javatype_ = JavaType.class;
		final JClass javatype = this.model.ref(javatype_);

		final JMethod impl = klass.method(JMod.NONE, javatype,
						  makeGetterImpl(ident));
		final JBlock body = impl.body();
		body.directStatement(format("// type=%s", type));
		body.directStatement(format("// format=%s", format));
		final JClass javatype2 = bitform2javatype(format, this.model);
		final JExpression expr = bitform2j(format).invoke("read2")
			.arg(JExpr.ref(JExpr.ref(JExpr._this(), "buffer"),
				       "source"))
			.arg(JExpr.lit(offset));
		final JVar input = body.decl(javatype2, "input", expr);
		final JVar var = body.decl(javatype, "var",
					   javatype.staticInvoke("make").arg(input));
		body._return(var);

		return this.cacheParam(klass, ident, impl);
	}

	public JMethod cacheParam(JDefinedClass klass, String ident, JMethod impl)
	{
		final String prefixed = makeIdentifier(ident);
		final JFieldVar stor = klass.field(JMod.NONE, impl.type(),
						   prefixed, JExpr._null());

		final JMethod getter = klass.method(JMod.PUBLIC, impl.type(),
						    makeGetter(ident));
		final JBlock then = getter.body()._if(stor.eq(JExpr._null()))._then();
		then.assign(stor, JExpr.invoke(impl));
		getter.body()._return(stor);

		return getter;
	}

	public void write(OutputStream outputstream)
		throws java.io.IOException
	{
		this.model.build(new SingleStreamCodeWriter(outputstream));
	}
	public void write(File outputdir)
		throws java.io.IOException
	{
		this.model.build(new FileCodeWriter(outputdir));
	}

	public static void main(String...args)
		throws jsignalml.SyntaxError,
		       java.io.IOException,
		       JClassAlreadyExistsException
	{
		BasicConfigurator.configure();

		File outputdir = new File(args[0]);

		JavaGen gen = new JavaGen();

		Expression expr = Processor.parse(args[1]);

		final String field_name = "duration_of_data_record";
		ASTNode signalml = new ASTNode.Signalml("Test");
		new ASTNode.ExprParam(signalml, field_name, new Type.Int(),
				      new ASTNode.Positional[0], expr);

		Expression expr2 = Processor.parse(field_name + "() + 1");
		new ASTNode.ExprParam(signalml, field_name+"2", new Type.Int(),
				      new ASTNode.Positional[0], expr2);

		new ASTNode.BinaryParam(signalml, "readTest", new Type.Int(),
					Expression.Const.make("'<i4'"), Expression.Const.make(25));
					//new BitForm.Int.Int32.LE(), 25);
		new ASTNode.BinaryParam(signalml, "readTestConv", new Type.Int(),
					Expression.Const.make("'|S8'"), Expression.Const.make(0));
					//new BitForm.Str(8), 0);
		signalml.accept(gen, null);
		gen.write(System.out);
		gen.write(outputdir);
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
