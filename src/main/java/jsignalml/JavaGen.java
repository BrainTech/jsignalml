package jsignalml;

import static java.lang.String.format;
import java.util.List;
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
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JType;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JTryBlock;
import com.sun.codemodel.JCatchBlock;
import com.sun.codemodel.JForEach;
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
	public static final Logger log = new Logger(JavaGen.class);

	public static final String PREFIX = ""; //"_jsignalml_";
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

		final JDefinedClass klass;
		try {
			klass = this.model._class(node.id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.Signalml.class);

		JMethod readall = this.readallMethod(klass);
		klass.metadata = new Metadata(readall);
		log.info("%s.metadata has been set", klass);

		this.mainMethod(klass);
		this.getSetMethod(klass);
		this.getCurrentFilenameMethod(klass);
		this.getFormatDescriptionMethod(klass);
		this.getFormatIDMethod(klass);
		this.codecOpenMethod(klass);
		this.closeMethod(klass);
		return klass;
	}

	class Metadata {
		final JBlock rungetters;
		Metadata(JMethod readall)
		{
			this.rungetters = readall.body();
		}

		void registerGetter(String ident, JMethod getter)
		{
			final JExpression inv = JExpr._this().invoke(getter);
			final JFieldRef system_out =
				JavaGen.this.model.ref(System.class).staticRef("out");
			final JExpression output = JExpr.lit(ident).plus(JExpr.lit(": ")).plus(inv);
			this.rungetters.add(system_out.invoke("println").arg(output));
		}

		void registerLoopGetter(String ident, JDefinedClass klass, JMethod getter)
		{
			final JExpression inv = JExpr._this().invoke(getter);
			final JFieldRef system_out =
				JavaGen.this.model.ref(System.class).staticRef("out");
			final JExpression output = JExpr.lit(ident).plus(JExpr.lit(": ")).plus(inv);
			JForEach loop = this.rungetters.forEach(klass, "var", inv);
			loop.body().add(loop.var().invoke("readall"));
		}

		void registerContextAccessor(JMethod getter)
		{
			final JInvocation inv = JExpr._this().invoke(getter).invoke("readall");
			this.rungetters.add(inv);
		}
	}

	public JMethod readallMethod(JDefinedClass klass)
	{
		final JMethod readall = klass.method(JMod.PUBLIC, this.model.VOID, "readall");
		return readall;
	}

	public JMethod mainMethod(JDefinedClass klass)
	{
		final JMethod main = klass.method(JMod.STATIC | JMod.PUBLIC,
						  this.model.VOID, "main");
		final JVar args = main.varParam(String.class, "args");

		final JInvocation bc_configure =
			this.model.ref(BasicConfigurator.class).staticInvoke("configure");

		final JBlock body = main.body();
		body.add(bc_configure);

		final JVar reader = body.decl(klass, "reader", JExpr._new(klass));
		final JExpression file = JExpr._new(this.model.ref(File.class))
			.arg(args.component(JExpr.lit(0)));
		body.add(reader.invoke("open").arg(file));
		body.add(reader.invoke("readall"));
		return main;
	}

	public JMethod fileConstructor(JDefinedClass klass)
	{
		JMethod method = klass.constructor(JMod.NONE);
		JVar filename = method.param(File.class, "filename");
		method.body().add(JExpr._this().invoke("open").arg(filename));
		return method;
	}

	public JMethod codecOpenMethod(JDefinedClass klass)
	{
		final JClass file_class = this.model.ref(File.class);
		final JMethod open = klass.method(JMod.PUBLIC, this.model.VOID, "open");
		final JVar arg = open.param(File.class, "filename");
		open.body().assign(JExpr._this().ref("default_filename"), arg);
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

	@Override
	public JDefinedClass visit(ASTNode.ExprParam node, JDefinedClass klass)
	{
		assert klass != null;
		final JMethod impl = exprFunction(klass, node, node.id, node.type, node.expr);
		final JMethod cache = cacheFunction(klass, node, node.id,
						    node.type, JExpr.invoke(impl));
		return klass;
	}

	@Override
	public JDefinedClass visit(ASTNode.BinaryParam node, JDefinedClass klass)
	{
		assert klass != null;
		final JMethod impl = readParamFunction(klass, node, node.id, node.type,
						       node.format, node.offset);
		final JMethod cache = cacheFunction(klass, node, node.id,
						    node.type, JExpr.invoke(impl));
		return klass;
	}


	public JMethod readParamFunction(JDefinedClass klass, ASTNode node, String ident,
					 Type type, Expression format, Expression offset)
	{
		assert klass != null;

		final JMethod formatfunc = exprFunction(klass, node, node.id + "_format", // XXX
							new Type.String(), format);
		final JMethod offsetfunc = exprFunction(klass, node, node.id + "_offset", // XXX
							new Type.Int(), offset);

		final JMethod readfunc = readFunction(klass, node, node.id + "_read", // XXX
						      type);

		final JClass javatype = convertTypeToJClass(type);
		final JMethod impl = klass.method(JMod.NONE, javatype, makeGetterImpl(ident));
		// -- generated code --
		// JavaType.Str _jsignalml__get_readX_format();
		// JavaType.Int _jsignalml__get_readX_offset();
		// JavaType.Int _jsignalml__get_readX_read(BitForm bitform, JavaType.Int offset)
		// JavaType.Int _jsignalml__get_readX() {
		//     JavaType.Str format = _jsignalml__get_readX_format();
		//     JavaType.Int offset = _jsignalml__get_readX_offset();
		//     return _jsignalml__get_readX_read(BitForm.get(format), offset);
	        // }
		final JBlock body = impl.body();
		final JVar format_ = body.decl(this.model.ref(JavaType.Str.class), "format",
					       JExpr._this().invoke(formatfunc));
		final JVar offset_ = body.decl(this.model.ref(JavaType.Int.class), "offset",
					       JExpr._this().invoke(offsetfunc));
		final JClass bitform_class = this.model.ref(BitForm.class);
		final JVar theformat = body.decl(bitform_class, "theformat");
		final JTryBlock tryblock = body._try();
		tryblock.body().assign(theformat,
				       bitform_class.staticInvoke("get").arg(format_));
		final JClass badbitform = this.model.ref(BitForm.BadBitForm.class);
		final JClass expressionfault = this.model.ref(ExpressionFault.class);
		tryblock._catch(badbitform).body()
			._throw(JExpr._new(expressionfault).arg(JExpr.ref("_x")));
		impl.body()._return(JExpr._this().invoke(readfunc).arg(theformat).arg(offset_));
		return impl;
	}

	JavaGenVisitor.JavaNameResolver createResolver(final ASTNode start)
	{
		return new JavaGenVisitor.JavaNameResolver() {
			public JInvocation call(String id)
			{
				final ASTNode target = start.find(id);
				return JExpr.invoke(makeGetter(id));
			}
		};
	}

	public JMethod exprFunction(JDefinedClass klass, ASTNode node, String ident,
				    Type type, Expression expr)
	{
		final JClass javatype = convertTypeToJClass(type);
		final JMethod impl = klass.method(JMod.NONE, javatype,
						  makeGetterImpl(ident));
		final JavaGenVisitor javagen =
			new JavaGenVisitor(this.model, this.createResolver(node));
		impl.body()._return( expr.accept(javagen) );
		return impl;
	}

	public JMethod readFunction(JDefinedClass klass, ASTNode node, String ident,
				    Type type)
	{
		final JClass javatype = this.convertTypeToJClass(type);

		final JMethod impl = klass.method(JMod.NONE, javatype,
						  makeGetterImpl(ident));
		final JVar bitform_param = impl.param(BitForm.class, "bitform");
		final JVar offset_param = impl.param(JavaType.Int.class, "offset");
		final JBlock body = impl.body();
		body.directStatement(format("// type=%s", type));

		final JClass javatype2 = this.model.ref(JavaType.class);
		final JExpression expr = bitform_param.invoke("read2")
			.arg(JExpr.ref(JExpr._this().invoke("buffer"), "source"))
			.arg(offset_param);
		final JVar input = body.decl(javatype2, "input", expr);
		final JVar var = body.decl(javatype, "var",
					   javatype.staticInvoke("make").arg(input));
		body._return(var);
		return impl;
	}

	public JMethod cacheFunction(JDefinedClass klass, ASTNode node, String ident,
				     Type type, JInvocation impl_inv)
	{
		final String prefixed = makeIdentifier(ident);
		final JType type_ = this.convertTypeToJClass(type);
		final JFieldVar stor = klass.field(JMod.NONE, type_,
						   prefixed, JExpr._null());
		final JMethod getter = klass.method(JMod.PUBLIC, type_,
						    makeGetter(ident));
		final JBlock then = getter.body()._if(stor.eq(JExpr._null()))._then();
		then.assign(stor, impl_inv);
		getter.body()._return(stor);

		Metadata metadata = (Metadata) klass.metadata;
		metadata.registerGetter(ident, getter);

		return getter;
	}


	@Override
	public JDefinedClass visit(ASTNode.FileHandle node, JDefinedClass parent)
	{
		final JDefinedClass klass = this.fileClass(node, parent, node.id);
		contextAccessor(parent, node, node.id, klass);
		return klass;
	}

	public JDefinedClass fileClass(ASTNode.FileHandle node, JDefinedClass parent, String id)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("File_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}

		klass._extends(jsignalml.codec.Signalml.FileClass.class);

		JMethod readall = this.readallMethod(klass);
		klass.metadata = new Metadata(readall);
		log.info("%s.metadata has been set", klass);

		return klass;
	}

	public JMethod contextAccessor(JDefinedClass klass, ASTNode node, String ident,
				       JDefinedClass context)
	{
		final JFieldVar stor = klass.field(JMod.NONE, context,
						   "file_" + ident, JExpr._null());

		final JMethod getter = klass.method(JMod.PUBLIC, context,
						    makeGetter("file_" + ident));
		final JBlock then = getter.body()._if(stor.eq(JExpr._null()))._then();
		then.assign(stor, JExpr._new(context));
		getter.body()._return(stor);

		Metadata metadata = (Metadata) klass.metadata;
		metadata.registerContextAccessor(getter);

		return getter;
	}

	@Override
	public JDefinedClass visit(ASTNode.BuiltinFunction node, JDefinedClass klass)
	{
		builtinFunctionAccessor(node, klass, node.id);
		return klass;
	}

	public JMethod builtinFunctionAccessor(ASTNode.BuiltinFunction node, JDefinedClass klass,
					       String ident)
	{
		final JClass builtins_class = this.model.ref(Builtins.class);
		final JInvocation impl_inv = builtins_class.staticInvoke(ident);
		return cacheFunction(klass, node, ident, node.type, impl_inv);
	}

	@Override
	public JDefinedClass visit(ASTNode.Itername node, JDefinedClass klass)
	{
		iternameGetter(node, klass);
		return klass;
	}

	JMethod iternameGetter(ASTNode.Itername node, JDefinedClass klass)
	{
		JClass type = this.convertTypeToJClass(node.type);
		final JMethod getter = klass.method(JMod.PUBLIC, type,
						    makeGetter(node.id));
		getter.body()._return(JExpr._this().ref("index"));
		return getter;
	}

	@Override
	public JDefinedClass visit(ASTNode.ForLoop node, JDefinedClass parent)
	{
		final JDefinedClass klass = loopClass(node, parent);
		loopAccessor(parent, node, klass);
		loopIndexAccessor(parent, node, klass);
		return klass;
	}

	public JDefinedClass loopClass(ASTNode.ForLoop node, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("Loop_" + node.id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}

		klass._extends(jsignalml.codec.Signalml.LoopClass.class);

		JMethod constructor = klass.constructor(JMod.NONE);
		JVar index = constructor.param(JavaType.class, "index");
		constructor.body().directStatement("super(index);"); // TODO: convert to proper codemodel magic
		JMethod readall = this.readallMethod(klass);
		klass.metadata = new Metadata(readall);
		log.info("%s.metadata has been set", klass);

		return klass;
	}

	public JMethod loopAccessor(JDefinedClass klass, ASTNode.ForLoop node,
				    JDefinedClass context)
	{
		final JClass javatype_class = this.model.ref(JavaType.class);
		final JClass jt_list_class = this.model.ref(JavaType.List.class);
		final JClass jt_int_class = this.model.ref(JavaType.Int.class);
		final JClass util_class = this.model.ref(util.class);
		final String ident = "loop_" + node.id;
		final JType list_type = this.model.ref(List.class).narrow(context);
		final JMethod getter = klass.method(JMod.PUBLIC, list_type,
						    makeGetter(ident));
		final JBlock body = getter.body();

		final JavaGenVisitor javagen =
			new JavaGenVisitor(this.model, this.createResolver(node));
		final JVar range = body.decl(jt_list_class, "range",
					     node.sequence.accept(javagen));
		final JFieldVar stor = klass.field(JMod.NONE, list_type, ident, JExpr._null());
		final JBlock then = body._if(stor.eq(JExpr._null()))._then();
		then.assign(stor, util_class.staticInvoke("newLinkedList"));
		final JForEach loop = then.forEach(javatype_class, "var", range);
		loop.body().add(stor.invoke("add")
				.arg(JExpr._new(context).arg(loop.var())));
		body._return(stor);

		Metadata metadata = (Metadata) klass.metadata;
		metadata.registerLoopGetter(ident, context, getter);

		return getter;
	}

	public JMethod loopIndexAccessor(JDefinedClass klass, ASTNode.ForLoop node,
					 JDefinedClass context)
	{
		final JClass jt_int_class = this.model.ref(JavaType.Int.class);
		final JClass efte = this.model.ref(ExpressionFault.TypeError.class);
		final String ident = "loop_" + node.id;
		final JMethod getter = klass.method(JMod.PUBLIC, context,
						    makeGetter(ident));
		final JVar index = getter.param(JavaType.class, "index");
		final JBlock body = getter.body();

		final JFieldRef stor = JExpr._this().ref(ident);

		final JConditional typecheck = body._if(index._instanceof(jt_int_class));
		typecheck._then()._return(stor.invoke("get")
					  .arg(((JExpression)JExpr.cast(jt_int_class, index))
					       .invoke("safeIntValue")));
		typecheck._else()._throw(JExpr._new(efte));
		return getter;
	}

	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

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

		final File outputdir = new File(args[0]);

		final Expression expr = Processor.parse(args[1]);

		final String field_name = "duration_of_data_record";
		final ASTNode signalml = new ASTNode.Signalml("Test");
		new ASTNode.ExprParam(signalml, field_name, new Type.Int(), expr);

		final Expression expr2 = Processor.parse(field_name + "() + 1");
		new ASTNode.ExprParam(signalml, field_name+"2", new Type.Int(), expr2);

		ASTNode.FileHandle thefile = new ASTNode.FileHandle(signalml, "thefile", null);

		new ASTNode.BinaryParam(thefile, "readTest", new Type.Int(),
					Expression.Const.make("<i4"), Expression.Const.make(25));
		new ASTNode.BinaryParam(thefile, "readTestConv", new Type.Int(),
					Expression.Const.make("|S8"), Expression.Const.make(0));

		final NameCheck check = new NameCheck();
		signalml.accept(check, null);

		final JavaGen gen = new JavaGen();
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

	JClass convertTypeToJClass(Type type)
	{
		Class<? extends JavaType> javatype_ = convertType(type);
		if (javatype_ == null)
			javatype_ = JavaType.class;
		return this.model.ref(javatype_);
	}
}
