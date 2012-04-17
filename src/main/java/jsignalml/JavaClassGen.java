package jsignalml;

import static java.lang.String.format;
import static jsignalml.Type.typename;
import static jsignalml.codec.Signalml.isPrimGeneration;

import java.io.File;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.List;

import jsignalml.ASTNode.ElseIfBranch;
import jsignalml.codec.Signalml.FileClass;
import jsignalml.logging.Logger;

import org.apache.log4j.BasicConfigurator;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JForLoop;
import com.sun.codemodel.JGenerable;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.codemodel.writer.FileCodeWriter;
import com.sun.codemodel.writer.SingleStreamCodeWriter;

public class JavaClassGen extends ASTVisitor<JDefinedClass> {
	public static final Logger log = new Logger(JavaClassGen.class);

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
	static String makeParamClass(String name)
	{
		return PREFIX + "_param_" + name;
	}

	public static final String
		GET = "get",
		GET_PRIV = "_get",
		CALL = "call",
		GET_P = "get_p",
		CALL_P = "call_p";

	public boolean calibrGainPresent = false;

	public boolean calibOffsPresent = false;

	public static final boolean _comments =
		System.getProperties().getProperty("jsignalml.comments", "1").equals("1");

	private int numbered_expression_number = 0;
	private String makeGeneratedID(String part)
	{
		String ans = PREFIX + part + "_" + numbered_expression_number++;
		log.info("generated id %s", ans);
		return ans;
	}

	final JCodeModel model = new JCodeModel();
	final JClass Integer_t = this.model.ref(Integer.class);
	final JClass Long_t = this.model.ref(Long.class);
	final JClass Double_t = this.model.ref(Double.class);
	final JClass Type_t = this.model.ref(Type.class);
	final JClass TypeInt_t = this.model.ref(TypeInt.class);
	final JClass TypeFloat_t = this.model.ref(TypeFloat.class);
	final JClass TypeString_t = this.model.ref(TypeString.class);
	final JClass TypeList_t = this.model.ref(TypeList.class);
	final JClass TypeMap_t = this.model.ref(TypeMap.class);
	final JClass List_of_Type_t = this.model.ref(List.class).narrow(Type.class);

	final JFieldRef TypeInt_I = TypeInt_t.staticRef("I");
	final JFieldRef TypeFloat_I = TypeFloat_t.staticRef("I");
	final JFieldRef TypeString_I = TypeString_t.staticRef("I");
	final JFieldRef TypeList_I = TypeList_t.staticRef("I");
	final JFieldRef TypeMap_I = TypeMap_t.staticRef("I");

	final JClass ArgMismatch_t = this.model.ref(ExpressionFault.ArgMismatch.class);
	final JClass ContextDumper_t = this.model.ref(ContextDumper.class);
	final JClass ChannelSet_t = this.model.ref(ChannelSet.class);
	final JClass Channel_t = this.model.ref(Channel.class);
	final JClass MyBuffer_t = this.model.ref(MyBuffer.class);
	final JClass TextBuffer_t = this.model.ref(TextBuffer.class);
	final JClass BitForm_t = this.model.ref(BitForm.class);
	final JClass Builtins_t = this.model.ref(Builtins.class);
	final JClass RuntimeException_t = this.model.ref(RuntimeException.class);
	final JClass Param_t = this.model.ref(jsignalml.codec.Param.class);
	final JClass FunctionParam_t = this.model.ref(jsignalml.codec.FunctionParam.class);

	final JClass BasicConfigurator_t = this.model.ref(BasicConfigurator.class);
	final JClass File_t = this.model.ref(File.class);
	final JClass String_t = this.model.ref(String.class);
	final JClass Logger_t = this.model.ref(Logger.class);
	final JClass System_t = this.model.ref(System.class);
	final JClass FloatBuffer_t = this.model.ref(FloatBuffer.class);
	final JClass ByteBuffer_t = this.model.ref(ByteBuffer.class);
	final JClass FileClass_t = this.model.ref(FileClass.class);

	JFieldVar log_var = null; // this should be set when Signalml class is created.
	final ASTTypeResolver typeresolver;

	private static ASTTypeResolver nullTypeResolver() {
		return new ASTTypeResolver() {
			public Type getType(ASTNode node) {
				return null;
			}
		};
	}

	public JavaClassGen() {
		this(nullTypeResolver());
	}

	public JavaClassGen(ASTTypeResolver typeresolver) {
		this.typeresolver = typeresolver;
	}

	/**
	 * What is the type of this node? Query our typeresolver and node.type.
	 */
	private Type nodeType(ASTNode node) {
		return this.typeresolver.getType(node);
	}

	private String dynamicID(ASTNode start, Expression id)
	{
		final EvalVisitor valuator = EvalVisitor.create(start);
		String value = valuator.quickEval(TypeString.I, id);
		if (value != null)
			return value;

		// expression cannot be evaluated statically
		return this.makeGeneratedID("gen_id");
	}

	@Override
	public JDefinedClass visit(ASTNode.Signalml node, JDefinedClass dummy)
	{
		log.info("visit((Signalml) %s, %s)", node, dummy);
		assert dummy == null;

		final JDefinedClass klass;
		final String theid = dynamicID(node, node.id);
		try {
			klass = this.model._class(theid);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.Signalml.class);
		comment_stamp(klass);

		this.log_var = klass.field(JMod.STATIC|JMod.FINAL, Logger_t, "log",
					   JExpr._new(Logger_t).arg(klass.dotclass()));

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		idMethod(klass, node, theid);
		this.mainMethod(klass);
		this.getCurrentFilenameMethod(klass);
		this.getFormatDescriptionMethod(klass);
		this.getFormatIDMethod(klass);
		this.codecOpenMethod(klass);
		this.closeMethod(klass);
		return klass;
	}

	class Metadata {
		final JBlock create_params;
		final JBlock create_channels;
		final JDefinedClass klass;

		Metadata(JDefinedClass klass, String method_suffix)
		{
			this.klass = klass;

			{
				final JMethod method =
					klass.method(JMod.PUBLIC, JavaClassGen.this.model.VOID,
						     "createParams" + method_suffix);
				comment_stamp(method.body());
				this.create_params = method.body();
				final String msg = format("%s.%s()", klass.name(), method.name());
				assert JavaClassGen.this.log_var != null;
				this.create_params.add(JavaClassGen.this.log_var
						       .invoke("debug").arg(msg));
			}

			{
				final JMethod method =
					klass.method(JMod.PUBLIC, JavaClassGen.this.model.VOID,
						     "createChannels" + method_suffix);
				comment_stamp(method.body());
				this.create_channels = method.body();
				final String msg = format("%s.%s()", klass.name(), method.name());
				assert JavaClassGen.this.log_var != null;
				this.create_channels.add(JavaClassGen.this.log_var
							 .invoke("debug").arg(msg));
			}
		}

		Metadata(JDefinedClass klass)
		{
			this(klass, "");
		}

		void registerParam(String name, JClass klass, JExpression param_inv)
		{
			log.info("register %s", name);
			final JBlock block = this.create_params.block();
			comment_stamp(block);
			block.add(JExpr.invoke("register").arg(name).arg(param_inv));

			if (name.equals("calibration_gain")) {
				log.info("calibration_gain present");
				calibrGainPresent = true;
			} else if (name.equals("calibration_offset")) {
				log.info("calibration_offset present");
				calibOffsPresent = true;
			}
		}

		void registerContext(String name, JDefinedClass context_class, JExpression get)
		{
			log.info("register context %s=>%s", name, context_class);
			{
				final JBlock block = this.create_params.block();
				comment_stamp(block);
				final JVar obj = block.decl(context_class, "obj", get);
				block.add(JExpr.invoke("register").arg(name).arg(obj));
				block.add(obj.invoke("createParams"));
			}
			{
				final JBlock block = this.create_channels.block();
				comment_stamp(block);
				final JVar obj = block.decl(context_class, "obj", get);
				block.add(obj.invoke("createChannels"));

				final JClass outerloop_class = JavaClassGen.this.model
					.ref(jsignalml.codec.OuterLoopClass.class);
				if(context_class._extends().equals(outerloop_class))
					block.add(obj.invoke("createLoopChannels"));
			}
		}

		void registerChannel(String name, JExpression channel)
		{
			log.info("register channel %s", name);
			this.create_channels.add(JExpr.invoke("registerChannel").arg(channel));
		}

		void registerChannelSet(String name, JExpression set)
		{
			log.info("register channel set %s", name);
			this.create_channels.add(JExpr.invoke("registerChannelSet").arg(set));
		}
	}

	/**
	 * Metadata which adds parameters to createIfParams function, for use with an if.
	 */
	class MetadataIfBranch extends Metadata {
		final Metadata elseBranch;
		final Metadata elseIfBranch;
		MetadataIfBranch(JDefinedClass klass)
		{
			super(klass, "If");
			this.elseIfBranch = new Metadata(this.klass, "ElseIf");
			this.elseBranch = new Metadata(this.klass, "Else");
		}
	}

	public JMethod mainMethod(JDefinedClass klass)
	{
		final JMethod main = klass.method(JMod.STATIC | JMod.PUBLIC,
						  this.model.VOID, "main");
		comment_stamp(main.body());
		final JVar args = main.varParam(String.class, "args");

		final JBlock body = main.body();

		body.add(BasicConfigurator_t.staticInvoke("configure"));

		final JVar reader = body.decl(klass, "reader", JExpr._new(klass));
		final JExpression file = JExpr._new(File_t).arg(args.component(JExpr.lit(0)));
		body.add(reader.invoke("open").arg(file));

		body.add(reader.invoke("createParams"));
		body.add(reader.invoke("createChannels"));

		final JExpression dumper_dump =
			ContextDumper_t.staticInvoke("dump").arg(reader);
		body.add(System_t.staticRef("out").invoke("print").arg(dumper_dump));

		{
			final JForLoop for_ =  body._for();
			final JVar i = for_.init(this.model.INT, "i", JExpr.lit(1));
			for_.test(i.lt(args.ref("length")));
			for_.update(i.incr());
			final JBlock forbody = for_.body();
			final JVar count = forbody.decl(this.model.LONG, "count",
							reader.invoke("get_set")
							      .invoke("getNumberOfSamples"));
			final JInvocation buffer_init = FloatBuffer_t
				.staticInvoke("allocate")
				.arg(JExpr.cast(this.model.INT, count));
			final JVar buffer = forbody.decl(FloatBuffer_t, "buffer", buffer_init);
			final JInvocation channel_num =
				Integer_t.staticInvoke("decode").arg(args.component(i));
			forbody.add(reader
				    .invoke("get_set")
				    .invoke("getChannel").arg(channel_num)
				    .invoke("getSamples").arg(buffer).arg(JExpr.lit(0)));
		}

		return main;
	}

	public JMethod codecOpenMethod(JDefinedClass klass)
	{
		final JMethod open = klass.method(JMod.PUBLIC, this.model.VOID, "open");
		comment_stamp(open.body());
		final JVar arg = open.param(File.class, "filename");
		open.body().assign(JExpr._this().ref("default_filename"), arg);
		return open;
	}

	public JMethod getCurrentFilenameMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PUBLIC, File_t,
						    "getCurrentFilename");
		comment_stamp(method.body());
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod getFormatDescriptionMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PUBLIC, String_t,
						    "getFormatDescription");
		comment_stamp(method.body());
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod getFormatIDMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PUBLIC, String_t,
						    "getFormatID");
		comment_stamp(method.body());
		method.body()._return(JExpr._null());
		return method;
	}

	public JMethod closeMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PUBLIC, this.model.VOID,
						    "close");
		comment_stamp(method.body());
		return method;
	}

	@Override
	public JDefinedClass visit(ASTNode.ExprParam node, JDefinedClass klass)
	{
		log.info("visit((ExprParam) %s, %s)", node, klass);
		assert klass != null;
		final String theid = dynamicID(node, node.id);
		final JDefinedClass nested = paramClass(klass, theid, node);
		idMethod(nested, node, theid);
		if(node.args.isEmpty()) {
			getExprMethod(nested, node);
		} else {
			getThisMethod(nested, node);
			callExprMethod(nested, node);
			if (isPrimGeneration())
				callExprMethod_p(nested, node);
		}
		return nested;
	}

	@Override
	public JDefinedClass visit(ASTNode.BinaryParam node, JDefinedClass klass)
	{
		log.info("visit((BinaryParam) %s, %s)", node, klass);
		assert klass != null;
		final String theid = dynamicID(node, node.id);
		JDefinedClass nested = paramClass(klass, theid, node);
		idMethod(nested, node, theid);
		readParamFunction(nested, node);
		return nested;
	}

	@Override
	public JDefinedClass visit(ASTNode.TextParam node, JDefinedClass klass)
	{
		log.info("visit((TextParam) %s, %s)", node, klass);
		assert klass != null;
		final String theid = dynamicID(node, node.id);
		JDefinedClass nested = paramClass(klass, theid, node);
		idMethod(nested, node, theid);
		readParamFunction(nested, node);
		return nested;
	}

	JDefinedClass paramClass(JDefinedClass parent, String theid, ASTNode.Param node)
	{
		final Type nodetype = this.nodeType(node);
		final JClass typeref = convertTypeToJClass(nodetype);

		final JClass klass_type = node.args.isEmpty() ? Param_t : FunctionParam_t;
		final JClass param_class = klass_type.narrow(typeref);
		final JDefinedClass nested;
		try {
			String parentParamClass = makeParamClass(theid);
			comment(parent, "parent paramClass=%s", parentParamClass);
			nested = parent._class(parentParamClass);
		} catch(JClassAlreadyExistsException e) {
			throw new SyntaxError(format("duplicate name: '%s'", theid));
		}
		nested._extends(param_class);
		comment_stamp(nested);
		comment(nested, "node.type=%s", typename(node.type));
		comment(nested, "--> nodetype=%s", typename(nodetype));

		final JMethod getter = classCacheMethod(parent, theid, nested);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerParam(theid, nested, JExpr.invoke(getter));

		return nested;
	}

	JMethod idMethod(JDefinedClass klass, ASTNode node, String theid)
	{
		final JMethod method = klass.method(JMod.PUBLIC, String_t, "id");
		comment_stamp(method.body());
		final JExpression ret;
		if (node.id != null) {
			final EvalVisitor valuator = EvalVisitor.create(node);
			String value = valuator.quickEval(TypeString.I, node.id);
			if (value != null) {
				ret = JExpr.lit(value);
			} else {
				JavaExprGen javagen = createExprGen(node, null);
				JExpression jvalue = node.id.accept(javagen);
				JExpression jcast = TypeString_I.invoke("make").arg(jvalue);
				ret = jcast.invoke("getValue");
			}
		} else {
			ret = JExpr.lit(theid);
		}
		method.body()._return(ret);
		return method;
	}

	public JMethod readParamFunction(JDefinedClass klass, ASTNode.BinaryParam node)
	{
		assert klass != null;

		final JavaExprGen javagen = createExprGen(node, null);
		final Type nodetype = nodeType(node);
		final JClass nodetype_t = convertTypeToJClass(nodetype);
		final JMethod impl = klass.method(JMod.PROTECTED, nodetype_t, GET_PRIV);
		final JBlock body = impl.body();
		comment_stamp(body);

		comment(body, "node.type=%s", typename(node.type));
		comment(body, "node._read_type=%s", typename(node._read_type));
		comment(body, "--> nodetype=%s", typename(nodetype));
		comment(body, "format=(%s)", node.format);
		comment(body, "format.type=%s", typename(node.format.type));
		comment(body, "offset=(%s)", node.offset);
		comment(body, "offset.type=%s", typename(node.offset.type));

		final JVar offset_ = body.decl(TypeInt_t, "offset",
					       do_cast(TypeInt.I,
						       node.offset.accept(javagen),
						       node.offset.type));

		final BitForm form = staticBitform(node, node.format);
		final JVar theformat;
		final Type expected;
		if(form != null){
			expected = form.readType();
			JClass form_t = this.model.ref(form.getClass());
			theformat = body.decl(form_t, "theformat",
					      JExpr.direct("new " + form));
		} else {
			expected = null;
			JVar format_ = body.decl(TypeString_t, "format",
						 do_cast(TypeString.I,
							 node.format.accept(javagen),
							 node.format.type));
			theformat = body.decl(BitForm_t, "theformat",
					      BitForm_t.staticInvoke("get").arg(format_));
		}
		final JClass expected_t = convertTypeToJClass(expected);
		final JExpression expr = theformat.invoke("read")
			.arg(JExpr.ref(JExpr.invoke("buffer"), "source"))
			.arg(offset_);

		final JVar input = body.decl(expected_t, "input", expr);
		body._return(make_or_cast(nodetype, input, expected));

		if (isPrimGeneration())
			getMethod_p(klass, nodetype);

		return impl;
	}
	
	public JMethod readParamFunction(JDefinedClass klass, ASTNode.TextParam node)
	{
		assert klass != null;

		final JavaExprGen javagen = createExprGen(node, null);
		final Type nodetype = nodeType(node);
		final JClass nodetype_t = convertTypeToJClass(nodetype);
		final JMethod impl = klass.method(JMod.PROTECTED, nodetype_t, GET_PRIV);
		final JBlock body = impl.body();
		comment_stamp(body);

		comment(body, "node.type=%s", typename(node.type));
		comment(body, "node._read_type=%s", typename(node._read_type));
		comment(body, "--> nodetype=%s", typename(nodetype));
		comment(body, "format=(%s)", node.format);
		comment(body, "format.type=%s", typename(node.format.type));
		comment(body, "line=(%s)", node.line);
		comment(body, "line.type=%s", typename(node.line.type));
		comment(body, "pattern=(%s)", node.pattern);
		comment(body, "pattern.type=%s", typename(node.pattern.type));
		comment(body, "group=(%s)", node.group);
		comment(body, "group.type=%s", typename(node.group.type));

		
		final JVar line_ = body.decl(TypeInt_t, "line", node.line.accept(javagen));
		final JVar pattern_ = body.decl(TypeString_t, "pattern", node.pattern.accept(javagen));
		final JVar group_ = body.decl(TypeInt_t, "group", node.group.accept(javagen));

		
		final JVar textBuf = body.decl(TextBuffer_t, "textBuf", JExpr.invoke("textBuffer"));
		final JVar _t = body.decl(nodetype_t, "_t", JExpr._null());
		
		final JVar value = body.decl(nodetype_t, "value", 
				textBuf.invoke("read").arg(line_).arg(pattern_).arg(group_).arg(_t));
		

		body._return(value);

		if (isPrimGeneration())
			getMethod_p(klass, nodetype);

		return impl;
	}

	BitForm staticBitform(ASTNode start, Expression format)
	{
		EvalVisitor valuator = EvalVisitor.create(start);
		String value = valuator.quickEval(TypeString.I, format);
		if (value == null)
			return null;
		try {
			return BitForm.get(value);
		} catch(BitForm.BadBitForm e) {
			throw new ExpressionFault.ValueError("bad bitform: " + value);
		}
	}

	public JavaExprGen.JavaNameResolver createResolver(final ASTNode start,
							   final List<JVar> locals,
							   final String getterName)
	{
		return new JavaExprGen.JavaNameResolver() {
			@Override
			public JExpression lookup(String id)
			{
				if(locals != null)
					for(JVar var: locals)
						if(id.equals(var.name()))
						   return var;

				final ASTNode target = start.find(id);
				if (target instanceof ASTNode.BuiltinFunction)
					return JavaClassGen.this.Builtins_t.staticInvoke(id);
				else
					return JExpr.invoke(makeGetter(id)).invoke(getterName);
			}
		};
	}

	JavaExprGen createExprGen(final ASTNode start, final List<JVar> locals)
	{
		JavaExprGen.JavaNameResolver resolver = createResolver(start, locals, GET);
		return new JavaExprGen(this.model, resolver);
	}

	JavaPrimitiveGen createExprGen_p(final ASTNode start, final List<JVar> locals)
	{
		JavaExprGen.JavaNameResolver resolver = createResolver(start, locals, GET_P);
		return new JavaPrimitiveGen(this.model, resolver);
	}

	public JMethod getExprMethod(JDefinedClass klass, ASTNode.ExprParam node)
	{
		final Type nodetype = nodeType(node);
		final JClass javatype = convertTypeToJClass(nodetype);
		final JMethod impl = klass.method(JMod.PROTECTED, javatype, GET_PRIV);
		comment_stamp(impl.body());
		comment(impl.body(), "node.type=%s", typename(node.type));
		comment(impl.body(), "node.expr.type=%s", typename(node.expr.type));
		comment(impl.body(), "--> nodetype=%s", typename(nodetype));
		final JavaExprGen javagen = createExprGen(node, null);
		final JExpression value = node.expr.accept(javagen);
		impl.body()._return(do_cast(nodetype, value, node.expr.type));

		if (isPrimGeneration())
			getMethod_p(klass, nodetype);

		return impl;
	}

	/**
	 * Create a getter returning "this". This method is useful because it
	 * narrows the returned type to exact implementation class. The java
	 * compiler then knows what are the types returned by call().
	 */
	public JMethod getThisMethod(JDefinedClass klass, ASTNode.ExprParam node)
	{
		final JMethod getter = klass.method(JMod.PUBLIC, klass, GET);
		comment_stamp(getter.body());
		getter.body()._return(JExpr._this());
		return getter;
	}

	public JMethod getMethod_p(JDefinedClass klass, Type wanted)
	{
		JExpression get = JExpr._this().invoke(GET);
		final JType type;
		if (wanted instanceof TypeInt) {
			get = get.invoke("safeLongValue");
			type = Long_t;
		} else if (wanted instanceof TypeFloat) {
			get = get.invoke("getValue");
			type = Double_t;
		} else {
			return null;
		}

		return _cacheMethod(klass, type, GET_P, get);
	}

	public JMethod callExprMethod(JDefinedClass klass, ASTNode.ExprParam node)
	{
		final Type nodetype = nodeType(node);
		final JClass javatype = convertTypeToJClass(nodetype);
		final JMethod impl = klass.method(JMod.PUBLIC, javatype, CALL);
		comment_stamp(impl.body());

		List<JVar> locals = util.newLinkedList();
		for (ASTNode.Positional arg: node.args) {
			JVar var = impl.param(convertTypeToJClass(arg.type),
					      dynamicID(node, arg.id));
			locals.add(var);
		}

		final JMethod cast = klass.method(JMod.PUBLIC, javatype, CALL);
		comment_stamp(cast.body());
		final JVar cast_args = cast.param(List_of_Type_t, "args");
		final JBlock cast_body = cast.body();
		cast_body._if(cast_args.invoke("size").ne(JExpr.lit(locals.size())))
			._then()._throw(JExpr._new(ArgMismatch_t)
					.arg(cast_args.invoke("size"))
					.arg(JExpr.lit(locals.size())));

		final JInvocation subcall = JExpr._this().invoke(CALL);
		int i = 0;
		for (JVar arg: locals) {
			final JExpression arg_i = cast_args.invoke(GET).arg(JExpr.lit(i++));
			subcall.arg(JExpr.cast(arg.type(), arg_i));
		}
		cast_body._return(subcall);

		final JavaExprGen javagen = createExprGen(node, locals);
		final JExpression value = node.expr.accept(javagen);

		comment(impl.body(), "node.type=%s", typename(node.type));
		comment(impl.body(), "node.expr=(%s)", node.expr);
		comment(impl.body(), "node.expr.type=%s", typename(node.expr.getType()));
		comment(impl.body(), "--> nodetype=%s", typename(nodetype));

		impl.body()._return(do_cast(nodetype, value, node.expr.getType()));
		return impl;
	}

	public JMethod callExprMethod_p(JDefinedClass klass, ASTNode.ExprParam node)
	{
		final List<JVar> locals = util.newLinkedList();
		final JavaExprGen.JavaNameResolver resolver = createResolver(node, locals, GET_P);
		final JavaPrimitiveGen gen = new JavaPrimitiveGen(this.model, resolver);

		final Type nodetype = nodeType(node);
		final JType javatype = gen.convertTypeToJClass_p(nodetype);
		final JMethod impl = klass.method(JMod.PUBLIC, javatype, CALL_P);
		final JBlock body = impl.body();
		comment_stamp(body);

		for (ASTNode.Positional arg: node.args) {
			JVar var = impl.param(gen.convertTypeToJClass_p(arg.type),
					      dynamicID(node, arg.id));
			locals.add(var);
		}

		final JavaExprGen javagen = createExprGen_p(node, locals);
		final JExpression value = node.expr.accept(javagen);

		comment(body, "node.type=%s", typename(node.type));
		comment(body, "node.expr=(%s)", node.expr);
		comment(body, "node.expr.type=%s", typename(node.expr.getType()));
		comment(body, "--> nodetype=%s", typename(nodetype));

		body._return(value);
		return impl;
	}

	@Override
	public JDefinedClass visit(ASTNode.FileHandle node, JDefinedClass parent)
	{
		log.info("visit((FileHandle) %s, %s)", node, parent);

		final String theid = dynamicID(node, node.id);
		final JDefinedClass klass = this.fileClass(node, theid, parent);
		idMethod(klass, node, theid);
		return klass;
	}

	public JDefinedClass fileClass(ASTNode.FileHandle node, String id,
				       JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("File_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.Signalml.FileClass.class);
		comment_stamp(klass);

		/* add methods
		   - T access(String name) { return super.access(name); }
		   - void register(String name, Context child) { super.register(name, child); }
		   because otherwise methods abstract super classes cannot be called
		   from nested classes.
		*/

		{
			final JMethod constructor =
					klass.constructor(JMod.PUBLIC);
			JBlock body = constructor.body();

			if(node.filename != null){

				final JVar mainFile = body.decl(File_t, "main", 
						JExpr.ref("default_filename"));
				final JVar endIndex = body.decl(Integer_t, "endIndex", 
						mainFile.invoke("getAbsolutePath").invoke("length").
									minus(mainFile.invoke("getName").invoke("length")));
				final JVar dirname = body.decl(String_t, "dirname", 
						mainFile.invoke("getAbsolutePath").invoke("substring").arg(JExpr.lit(0)).arg(endIndex));

				final JavaExprGen javagen = createExprGen(node, null);
				final JVar filename = body.decl(String_t, "filename",
						dirname.plus(node.filename.accept(javagen).ref("value")));

				body.assign(JExpr.ref("currentFilename"),
						JExpr._new(File_t).arg(filename));
			}
		}

		{
			final JMethod get_child =
				klass.method(JMod.PUBLIC, jsignalml.Type.class, "access");
			comment_stamp(get_child.body());
			final JVar name = get_child.param(String.class, "name");
			get_child.body()._return(JExpr._super().invoke("access").arg(name));
		}

		{
			final JMethod register =
				klass.method(JMod.PUBLIC, this.model.VOID, "register");
			comment_stamp(register.body());
			final JVar name = register.param(String.class, "name");
			final JVar object = register.param(jsignalml.codec.Context.class, "child");
			register.body().add(JExpr._super()
					    .invoke("register").arg(name).arg(object));
		}

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		final JMethod getter = classCacheMethod(parent, id, klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(id, klass, JExpr.invoke(getter));

		return klass;
	}

	/**
	 * If wanted is not null, make sure that value is of this type, by
	 * invoking type.make(value). Otherwise just make body return value.
	 * expected is a result of type analysis and must be the superset of
	 * possible types of expression.
	 */
	public void return_make_or_cast(JBlock body, Type wanted,
					JExpression value, Type expected)
	{
		body._return(make_or_cast(wanted, value, expected));
	}

	/**
	 * If wanted is not null, make sure that value is of this type, by
	 * invoking type.make(value). Return the expression holding the value
	 * after conversion. expected is a result of type analysis and must be
	 * the superset of possible types of expression.
	 */
	public JExpression make_or_cast(Type wanted, JExpression value, Type expected)
	{
		final JClass wanted_t = convertTypeToJClass(wanted);
		if (wanted != null && (expected == null ||
				       !wanted.getClass().isAssignableFrom(expected.getClass()))) {
			// conversion requiered
			return wanted_t.staticRef("I").invoke("make").arg(value);
		} else {
			// no need to convert
			return JExpr.cast(wanted_t, value);
		}
	}

	/**
	 * If wanted is not null, make sure that value is of this type, by
	 * invoking a cast to value. Return the expression holding the value
	 * after cast. expected is a result of type analysis and must be the
	 * superset of possible types of expression, this is checked and
	 * an TypeError is thrown if this doesn't hold.
	 */
	public JExpression do_cast(Type wanted, JExpression value, Type expected)
	{
		final JClass wanted_t = convertTypeToJClass(wanted);
		if (wanted != null && expected != null &&
		    !wanted.getClass().isAssignableFrom(expected.getClass()))
			throw new ExpressionFault.TypeError(expected, wanted);
		if (wanted != null)
			// cast requiered
			return JExpr.cast(wanted_t, value);
		else
			return value;
	}

        public JMethod classCacheMethod(JDefinedClass parent, String id, JDefinedClass klass)
        {
		return _cacheMethod(parent, klass, makeGetter(id),
				    JExpr._new(klass));
        }

	JMethod _cacheMethod(JDefinedClass parent, JType klass, String methodname,
			     JExpression init)
	{
                final JFieldVar stor = parent.field(JMod.NONE, klass, methodname,
						    JExpr._null());
                final JMethod getter = parent.method(JMod.PUBLIC, klass, methodname);
		comment_stamp(getter.body());

                getter.body()
			._if(stor.eq(JExpr._null()))
			._then().assign(stor, init);
                getter.body()._return(stor);
                return getter;
	}

	@Override
	public JDefinedClass visit(ASTNode.Itername node, JDefinedClass parent)
	{
		log.info("visit((Itername) %s, %s)", node, parent);
		final String theid = dynamicID(node, node.id);
		final JDefinedClass klass = indexClass(parent, theid, node);
		iternameGetter(parent, theid, klass);
		return klass;
	}

	JMethod iternameGetter(JDefinedClass klass, String id, JDefinedClass indexclass)
	{
		final JMethod getter = klass.method(JMod.PUBLIC, indexclass, makeGetter(id));
		comment_stamp(getter.body());
		getter.body()._return(JExpr.refthis("index"));

		Metadata metadata = (Metadata) klass.metadata;
		metadata.registerParam(id, indexclass, JExpr.refthis("index"));
		return getter;
	}

	@Override
	public JDefinedClass visit(ASTNode.ForLoop node, JDefinedClass parent)
	{
		log.info("visit((ForLoop) %s, %s)", node, parent);
		final String theid = dynamicID(node, node.id);
		final JDefinedClass outer = outerLoopClass(theid, parent);
		comment_stamp(outer);
		idMethod(outer, node, theid);
		sequenceMethod(outer, node);

		final JDefinedClass inner = loopClass(theid + "_inner", outer);
		comment_stamp(inner);
		idMethod(inner, node, theid + "_inner");

		return inner;
	}

	public JDefinedClass outerLoopClass(String id, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("Loop_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.OuterLoopClass.class);

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		final JMethod getter = classCacheMethod(parent, id, klass);
		comment_stamp(getter.body());

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(id, klass, JExpr.invoke(getter));

		return klass;
	}

	public JMethod sequenceMethod(JDefinedClass klass, ASTNode.ForLoop node)
	{
		final JMethod sequence = klass.method(JMod.PROTECTED, TypeList_t,
						      "getSequence");
		comment_stamp(sequence.body());

		final JavaExprGen javagen = createExprGen(node, null);
		final JExpression expr = do_cast(TypeList.I,
						 node.sequence.accept(javagen),
						 node.sequence.getType());
		final JVar range = sequence.body().decl(TypeList_t, "range", expr);

		sequence.body()._return(range);
		return sequence;
	}

	public JMethod createLoopMethod(JDefinedClass klass, JDefinedClass child_class,
					Type indextype)
	{
		final JMethod create_loop = klass.method(JMod.PROTECTED, child_class,
							 "createLoop");
		comment_stamp(create_loop.body());

		final JVar index = create_loop.param(Type_t, "index");
		final JExpression cast =
			JExpr.cast(convertTypeToJClass(indextype), index);
		create_loop.body()._return(JExpr._new(child_class)
					   .arg(cast));
		return create_loop;
	}

	public JDefinedClass loopClass(String id, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class(id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.OuterLoopClass.LoopClass.class);
		comment_stamp(klass);

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		return klass;
	}

	@Override
	public JDefinedClass visit(ASTNode.Conditional node, JDefinedClass parent)
	{
		log.info("visit((Conditional) %s, %s)", node, parent);
		String theid = dynamicID(node, node.id);
		List<ASTNode> children = node.children;
		boolean conditionalWithElseIf = false;
		for (ASTNode astNode : children) {
			if(astNode instanceof ElseIfBranch){
				conditionalWithElseIf = true;
				break;
			}
		}
		final JDefinedClass klass = conditionalClass(theid, parent, conditionalWithElseIf);
		comment_stamp(klass);
		idMethod(klass, node, theid);
		conditionMethod(klass, node);
		return klass;
	}

	JDefinedClass conditionalClass(String id, JDefinedClass parent, boolean conditionalWithElseIf)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("If_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.ConditionalClass.class);
		comment_stamp(klass);

		klass.metadata = new MetadataIfBranch(klass);
		log.info("%s.metadata/if has been set", klass);

		final JMethod getter = classCacheMethod(parent, id, klass);

		// hasElseIf method body
		final JMethod hasElseIf = klass.method(JMod.PUBLIC, this.model.BOOLEAN, "hasElseIf");
		hasElseIf.body()._return(JExpr.lit(conditionalWithElseIf));

		if (parent.metadata instanceof Metadata) {
			Metadata metadata = (Metadata) parent.metadata;
			metadata.registerContext(id, klass, JExpr.invoke(getter));
		} else {
			throw new RuntimeException("Parent Metadata for <if> tag is instance of not supported class "
					+ parent.metadata.getClass().getSimpleName());
			//TODO throw proper exception
		}

		return klass;
	}

	public JMethod conditionMethod(JDefinedClass klass, ASTNode.Conditional node)
	{
		final JMethod condition = klass.method(JMod.PUBLIC, Type_t, "getCondition");
		comment_stamp(condition.body());
		final JavaExprGen javagen = createExprGen(node, null);
		final JVar test = condition.body().decl(Type_t, "test",
						       node.condition.accept(javagen));
		condition.body()._return(test);
		return condition;
	}

	@Override
	public JDefinedClass visit(ASTNode.ElseBranch node, JDefinedClass parent)
	{
		log.info("visit((ElseBranch) %s, %s)", node, parent);
		final String theid = dynamicID(node, node.id);
		final JDefinedClass klass = elseBranchClass(theid, parent);
		comment_stamp(klass);
		idMethod(klass, node, theid);
		return klass;
	}

	public JDefinedClass elseBranchClass(String id, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("Else_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		if (parent.metadata instanceof MetadataIfBranch) {
			MetadataIfBranch metadata = (MetadataIfBranch) parent.metadata;
			metadata.elseBranch.registerContext(id, klass, JExpr._new(klass));
			klass._extends(jsignalml.codec.ConditionalClass.ElseBranchClass.class);
			comment_stamp(klass);
			klass.metadata = new Metadata(klass);
			log.info("%s.metadata has been set", klass);
		} else {
			throw new RuntimeException("Parent Metadata for <else> tag is instance of not supported class "
					+ parent.metadata.getClass().getSimpleName());
			//TODO throw proper exception
		}

		return klass;
	}

	public JDefinedClass visit(ASTNode.ElseIfBranch node, JDefinedClass parent)
	{
		log.info("visit((ElseIfBranch) %s, %s)", node, parent);
		final String theid = dynamicID(node, node.id);
		
		// lets check if there is any else-if inside this node
		List<ASTNode> children = node.children;
		boolean conditionalWithElseIf = false;
		for (ASTNode astNode : children) {
			if(astNode instanceof ElseIfBranch){
				conditionalWithElseIf = true;
				break;
			}
		}
		
		final JDefinedClass klass = elseIfBranchClass(theid, parent, conditionalWithElseIf);
		comment_stamp(klass);
		idMethod(klass, node, theid);
		conditionMethod(klass, node);
		return klass;
	}

	public JDefinedClass elseIfBranchClass(String id, JDefinedClass parent, boolean conditionalWithElseIf)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("ElseIf_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.ConditionalClass.ElseIfBranchClass.class);
		comment_stamp(klass);
		
		final JMethod hasElseIf = klass.method(JMod.PUBLIC, this.model.BOOLEAN, "hasElseIf");
		hasElseIf.body()._return(JExpr.lit(conditionalWithElseIf));
		
		klass.metadata = new MetadataIfBranch(klass);
		log.info("%s.metadata/else-if has been set", klass);
		
		final JMethod getter = classCacheMethod(parent, id, klass);
		
		if (parent.metadata instanceof MetadataIfBranch) {
			MetadataIfBranch metadata = (MetadataIfBranch) parent.metadata;
			metadata.elseIfBranch.registerContext(id, klass, JExpr.invoke(getter));
		} else {
			throw new RuntimeException("Parent Metadata for <else-if> tag is instance of not supported class "
					+ parent.metadata.getClass().getSimpleName());
			//TODO throw proper exception
		}
		
		return klass;
	}

	public JMethod conditionMethod(JDefinedClass klass, ASTNode.ElseIfBranch node)
	{
		final JMethod condition = klass.method(JMod.PUBLIC, Type_t, "getCondition");
		comment_stamp(condition.body());
		final JavaExprGen javagen = createExprGen(node, null);
		final JVar test = condition.body().decl(Type_t, "test",
						       node.condition.accept(javagen));
		condition.body()._return(test);
		return condition;
	}

	public JDefinedClass indexClass(JDefinedClass parent, String id, ASTNode.Itername node)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class(id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		final Type nodetype = nodeType(node);
		final JClass javatype = convertTypeToJClass(nodetype);
		klass._extends(Param_t.narrow(javatype));
		comment_stamp(klass);

		JMethod constructor = klass.constructor(JMod.NONE);
		JVar index = constructor.param(javatype, "index");
		comment_stamp(constructor.body());
		constructor.body().assign(JExpr._this().ref("cache"), index);

		idMethod(klass, node, id);

		loopClassConstructor(parent, id, nodetype, klass);

		createLoopMethod((JDefinedClass) parent.outer(), parent, nodetype);

		final JMethod impl = klass.method(JMod.PROTECTED, javatype, GET_PRIV);
		comment_stamp(impl.body());
		impl.body()._throw(JExpr._new(RuntimeException_t));

		if (isPrimGeneration())
			getMethod_p(klass, nodetype);

		return klass;
	}

	public JMethod loopClassConstructor(JDefinedClass klass,
					    String id, Type type,
					    JDefinedClass indexClass)
	{
		JMethod constructor = klass.constructor(JMod.NONE);
		JVar index = constructor.param(convertTypeToJClass(type), id);
		comment_stamp(constructor.body());
		JFieldVar stor = klass.field(JMod.FINAL, indexClass, "index");

		// work around bug in jcodemodel on using index instead of this.index
		constructor.body().assign(JExpr.refthis("index"),
					  JExpr._new(indexClass).arg(index));
		return constructor;
	}

	@Override
	public JDefinedClass visit(ASTNode.ChannelSet node, JDefinedClass parent)
	{
		log.info("visit((ChannelSet) %s, %s)", node, parent);
		final String theid = dynamicID(node, node.id);
		final JDefinedClass klass = channelSetClass(theid, parent);
		comment_stamp(klass);
		idMethod(klass, node, theid);
		return klass;
	}

	public JDefinedClass channelSetClass(String id, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("ChannelSet_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.ChannelSetClass.class);
		comment_stamp(klass);

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		final JMethod getter = classCacheMethod(parent, id, klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(id, klass, JExpr.invoke(getter));
		metadata.registerChannelSet(id, JExpr.invoke(getter));

		return klass;
	}

	@Override
	public JDefinedClass visit(ASTNode.Channel node, JDefinedClass parent)
	{
		log.info("visit((Channel) %s, %s)", node, parent);
		String theid = dynamicID(node, node.id);
		final JDefinedClass klass = channelClass(theid, parent);
		comment_stamp(klass);

		idMethod(klass, node, theid);
		underBufferMethod(klass);
		sampleFormatMethod(klass, node);
		mapSampleMethod(klass, node);
		getSampleMethod(klass, node);
		getSamplesMethod(klass, node);
		getSamplingFrequencyMethod(klass, node);
		getNumberOfSamplesMethod(klass, node);
		getChannelNameMethod(klass, node);
		getCalibrationGainMethod(klass, node);
		getCalibrationOffsetMethod(klass, node);
		getSampleUnitMethod(klass, node);

		return klass;
	}

	public JDefinedClass channelClass(String id, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("Channel_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.ChannelClass.class);
		comment_stamp(klass);

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		final JMethod getter = classCacheMethod(parent, id, klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(id, klass, JExpr.invoke(getter));
		metadata.registerChannel(id, JExpr.invoke(getter));

		return klass;
	}

	public JMethod underBufferMethod(JDefinedClass klass)
	{
		final JMethod method = klass.method(JMod.PROTECTED, MyBuffer_t, "_buffer");
		comment_stamp(method.body());
		method.body()._return(JExpr.invoke("buffer"));
		return method;
	}

	public JMethod sampleFormatMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, TypeString_t,
						    "getSampleFormat");
		final Type expected = node.format.getType();
		final JType expected_t = this.model.ref(expected.getClass());
		final JBlock body = method.body();
		comment_stamp(body);
		comment(body, "node.format.type=%s", typename(expected));

		final JavaExprGen javagen = createExprGen(node, null);
		final JVar value = body.decl(expected_t, "value",
					     node.format.accept(javagen));
		return_make_or_cast(body, TypeString.I, value, expected);
		return method;
	}

	public JMethod mapSampleMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, TypeInt_t, "mapSample");
		comment_stamp(method.body());
		final JVar sample = method.param(this.model.LONG, "sample");

		final JavaExprGen javagen = createExprGen(node, null);
		final JVar value = method.body().decl(Type_t, "value",
						      node.mapping.accept(javagen));
		return_make_or_cast(method.body(), TypeInt.I,
				    value.invoke(CALL).arg(JExpr._new(TypeInt_t).arg(sample)),
				    null);
		return method;
	}

	public JMethod getSampleMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final Expression fastSet = Processor.parse("1");
		final JMethod method = klass.method(JMod.PUBLIC, this.model.FLOAT, "getSample");
		comment_stamp(method.body());

		final JVar sample = method.param(this.model.LONG, "sample");

		final JavaExprGen javagen = createExprGen(node, null);
		final JBlock body = method.body();
		final JVar format_ = body.decl(TypeString_t, "format_",
					       JExpr._this().invoke("getSampleFormat"));
		final JVar format = body.decl(BitForm_t, "format",
					      BitForm_t.staticInvoke("get").arg(format_));

		JVar buffer = null;
		if(node.data != null){
			final JVar dataFileId = body.decl(Type_t, "dataFileId",
					node.data.accept(javagen));

			final JVar dataFilehandler = body.decl(FileClass_t, "fileHandler",
					JExpr.cast(FileClass_t, dataFileId));
			JVar filename = body.decl(File_t, "file",
					dataFilehandler.invoke("getCurrentFilename"));
			body._if(filename.eq(JExpr._null()))
					._then().invoke(dataFilehandler, "open").arg(JExpr._null());
			buffer = body.decl(ByteBuffer_t, "buffer",
					dataFilehandler.invoke("buffer").ref("source"));
		} else {
			buffer = body.decl(ByteBuffer_t, "buffer",
					JExpr.invoke("_buffer").ref("source"));
		}

		final JVar calibGain = body.decl(this.model.FLOAT, "calibGain",
				JExpr.invoke("getCalibrationGain").invoke("getValue")
				.invoke("floatValue"));
		final JVar calibOffs = body.decl(this.model.FLOAT, "calibOffs",
				JExpr.invoke("getCalibrationOffset").invoke("getValue")
				.invoke("floatValue"));
		final JVar sampleUnit = body.decl(this.model.FLOAT, "sampleUnit",
				JExpr.invoke("getSampleUnit").invoke("getValue")
				.invoke("floatValue"));

		if (isPrimGeneration() && node.fast.equals(fastSet)) {
			// Primitive types code variant

			final JExpression mapping_call = JExpr.cast(model.INT,
					JExpr.invoke("get_mapping").invoke(CALL_P).arg(sample));
			final JExpression input = format.invoke("read").arg(buffer)
					.arg(mapping_call);
			final JVar value = body.decl(this.model.FLOAT, "value", input);

			body._return(value.minus(calibOffs).mul(calibGain)
					.mul(sampleUnit));

		}
		else {
			final JVar mapping = body.decl(Type_t, "mapping",
					node.mapping.accept(javagen));
			final JExpression mapping_call = mapping.invoke(CALL)
					.arg(JExpr._new(TypeInt_t).arg(sample));
			final JVar input = body.decl(Type_t, "input",
					format.invoke("read").arg(buffer)
					.arg(JExpr.cast(TypeInt_t, mapping_call)));
			final JExpression conv = TypeFloat_I.invoke("make").arg(input);
			final JVar ret_value = body.decl(this.model.FLOAT, "ret_value",
					JExpr.cast(this.model.FLOAT, conv.ref("value")));
			body._return(ret_value.minus(calibOffs).mul(calibGain)
					.mul(sampleUnit));
		}
		return method;
	}

	public JMethod getSamplesMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final Expression fastSet = Processor.parse("1");
		final JMethod method = klass.method(JMod.PUBLIC, this.model.VOID, "getSamples");
		comment_stamp(method.body());
		final JVar dst = method.param(FloatBuffer_t, "dst");
		final JVar sample = method.param(this.model.LONG, "sample");

		final JavaExprGen javagen = createExprGen(node, null);
		final JBlock body = method.body();
		final JVar format_ = body.decl(TypeString_t, "format_",
					       JExpr._this().invoke("getSampleFormat"));
		final JVar format = body.decl(BitForm_t, "format",
					      BitForm_t.staticInvoke("get").arg(format_));

		JVar buffer = null;
		if(node.data != null){
			final JVar dataFileId = body.decl(Type_t, "dataFileId",
					node.data.accept(javagen));

			final JVar dataFilehandler = body.decl(FileClass_t, "fileHandler",
					JExpr.cast(FileClass_t, dataFileId));
			JVar filename = body.decl(File_t, "file",
					dataFilehandler.invoke("getCurrentFilename"));
			body._if(filename.eq(JExpr._null()))
					._then().invoke(dataFilehandler, "open").arg(JExpr._null());
			buffer = body.decl(ByteBuffer_t, "buffer",
					dataFilehandler.invoke("buffer").ref("source"));
		} else {
			buffer = body.decl(ByteBuffer_t, "buffer",
					JExpr.invoke("_buffer").ref("source"));
		}

		final JVar calibGain = body.decl(this.model.FLOAT, "calibGain",
				JExpr.invoke("getCalibrationGain").invoke("getValue")
				.invoke("floatValue"));
		final JVar calibOffs = body.decl(this.model.FLOAT, "calibOffs",
				JExpr.invoke("getCalibrationOffset").invoke("getValue")
				.invoke("floatValue"));
		final JVar sampleUnit = body.decl(this.model.FLOAT, "sampleUnit",
				JExpr.invoke("getSampleUnit").invoke("getValue")
				.invoke("floatValue"));

		if (isPrimGeneration() && node.fast.equals(fastSet)) {
			// Primitive types code variant
			final JVar count = body.decl(this.model.INT, "count",
					JExpr.invoke(dst, "remaining"));

			final JBlock _while = body._while(count.decr().gt(JExpr.lit(0))).body();
			final JExpression mapping_call = JExpr.cast(model.INT,
					JExpr.invoke(makeGetter(node.mapping.toString())).invoke(CALL_P)
					.arg(sample.incr()));
			final JExpression input = format.invoke("read").arg(buffer)
					.arg(mapping_call);
			final JVar value = _while.decl(this.model.FLOAT, "value", input);
			_while.add(dst.invoke("put").arg(value.minus(calibOffs).mul(calibGain)
					.mul(sampleUnit)));
		}
		else {
			final JVar mapping = body.decl(Type_t, "mapping",
					node.mapping.accept(javagen));

			final JBlock _while = body._while(JExpr.invoke(dst, "hasRemaining")).body();
			final JExpression mapping_call = mapping.invoke(CALL)
					.arg(JExpr._new(TypeInt_t).arg(sample.incr()));
			final JVar input = _while.decl(Type_t, "input",
					format.invoke("read").arg(buffer)
					.arg(JExpr.cast(TypeInt_t, mapping_call)));
			final JExpression conv = TypeFloat_I.invoke("make").arg(input);
			final JVar ret_value = _while.decl(this.model.FLOAT, "ret_value",
					JExpr.cast(this.model.FLOAT, conv.ref("value")));
			_while.add(dst.invoke("put")
					.arg(ret_value.minus(calibOffs).mul(calibGain)
					.mul(sampleUnit)));
		}
		return method;
	}

	public JMethod getSamplingFrequencyMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, this.model.DOUBLE,
					"getSamplingFrequency");
		comment_stamp(method.body());
		final JVar value = method.body().decl(Type_t, "value",
					JExpr.invoke("get_sampling_frequency").invoke("get"));
		final JVar cast = method.body().decl(TypeFloat_t, "cast",
					TypeFloat_I.invoke("make").arg(value));
		method.body()._return(cast.invoke("getValue"));
		return method;
	}

	public JMethod getNumberOfSamplesMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, this.model.LONG,
						    "getNumberOfSamples");
		comment_stamp(method.body());
		final JavaExprGen javagen = createExprGen(node, null);
		final JVar value = method.body().decl(Type_t, "value",
						      node.length.accept(javagen));
		final JVar cast = method.body().decl(TypeInt_t, "cast",
						     TypeInt_I.invoke("make").arg(value));
		method.body()._return(cast.invoke("safeLongValue"));
		return method;
	}

	public JMethod getSampleUnitMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, TypeFloat_t,
				"getSampleUnit");
		comment_stamp(method.body());
		final JVar value = method.body().decl(Type_t, "value",
				JExpr.invoke("get_sample_unit").invoke("get"));
		final JVar cast = method.body().decl(TypeFloat_t, "cast",
				TypeFloat_I.invoke("make").arg(value));
		method.body()._return(cast);
		return method;
	}

	public JMethod getChannelNameMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, String_t, "getChannelName");
		comment_stamp(method.body());

		final JVar value = method.body().decl(Type_t, "value",
				JExpr.invoke("get_channel_name").invoke("get"));
		final JVar val = method.body().decl(TypeString_t, "stringValue",
				JExpr.cast(TypeString_t, value));

		method.body()._return(val.invoke("getValue"));
		return method;
	}

	public JMethod getCalibrationGainMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, TypeFloat_t,
				"getCalibrationGain");
		comment_stamp(method.body());

		JInvocation ji = null;
		if (calibrGainPresent) {
			final JVar value = method.body().decl(Type_t, "value",
					JExpr.invoke("get_calibration_gain").invoke("get"));
			ji = TypeFloat_I.invoke("make").arg(value);
		}
		else {
			ji = JExpr._new(TypeFloat_t).arg("1");
		}
		final JVar cast = method.body().decl(TypeFloat_t, "cast", ji);
		method.body()._return(cast);

		return method;
	}

	public JMethod getCalibrationOffsetMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, TypeFloat_t,
				"getCalibrationOffset");
		comment_stamp(method.body());

		JInvocation ji = null;
		if (calibOffsPresent) {
			final JVar value = method.body().decl(Type_t, "value",
					JExpr.invoke("get_calibration_offset").invoke("get"));
			ji = TypeFloat_I.invoke("make").arg(value);
		}
		else {
			 ji = JExpr._new(TypeFloat_t).arg("0");
		}
		final JVar cast = method.body().decl(TypeFloat_t, "cast", ji);
		method.body()._return(cast);
		
		return method;
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
		new ASTNode.ExprParam(signalml, field_name, new TypeInt(), expr, null);

		final Expression expr2 = Processor.parse(field_name + "() + 1");
		new ASTNode.ExprParam(signalml, field_name+"2", new TypeInt(), expr2, null);

		ASTNode.FileHandle thefile = new ASTNode.FileHandle(signalml, "thefile", null);

		new ASTNode.BinaryParam(thefile, Processor.parse("'readTest'"), new TypeInt(),
					Expression.Const.make("<i4"), Expression.Const.make(25), null);
		new ASTNode.BinaryParam(thefile, Processor.parse("'readTestConv'"), new TypeInt(),
					Expression.Const.make("|S8"), Expression.Const.make(0), null);

		final NameCheck check = new NameCheck();
		signalml.accept(check, null);

		final JavaClassGen gen = new JavaClassGen();
		signalml.accept(gen, null);
		gen.write(System.out);
		gen.write(outputdir);
	}


	/* This static function goes here and not in Type, because it's an
	 * interface and cannot contain methods. It cannot go into Type
	 * either, in order to keep Type clean from Java implementation
	 * details.
	 */

	JClass convertTypeToJClass(Type type)
	{
		return this.model.ref(type != null ? type.getClass() : Type.class);
	}

	private static void comment(JGenerable where, String fmt, Object...args)
	{
		if (!_comments)
			return;

		final String out = format(fmt, args);
		if (where instanceof JBlock) {
			final JBlock body = (JBlock) where;
			body.directStatement("// " + out);
		} else if (where instanceof JDefinedClass) {
			final JDefinedClass klass = (JDefinedClass) where;
			klass.javadoc().append("\n" + out);
		} else {
			throw new RuntimeException("wtf?");
		}
	}

	/**
	 * Print a stack trace of generating method in generating code.
	 * The stacktrace is snipped to include JavaClassGen methods
	 * plus one before.
	 */
	private static void comment_stamp(JGenerable where)
	{
		if (!_comments)
			return;

		final StackTraceElement[] trace = new Throwable().getStackTrace();
		final String trigger = JavaClassGen.class.getName();
		boolean flag = false;
		for(int i=trace.length-1; i>=1; i--) {
			flag |= trace[i-1].getClassName().startsWith(trigger);
			if (flag || i==1) // print at least one
				comment(where, "%s", trace[i]);
		}
	}
}
