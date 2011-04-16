package jsignalml;

import static java.lang.String.format;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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

	private int numbered_expression_number = 0;
	private String makeGeneratedID(String part)
	{
		String ans = PREFIX + part + "_" + numbered_expression_number++;
		log.info("generated id %s", ans);
		return ans;
	}

	final JCodeModel model = new JCodeModel();

	private String dynamicID(Expression id)
	{
		if (id != null) {
			Type ans = null;
			try {
				ans = EvalVisitor.evaluate(id);
				assert ans != null;
			} catch(ExpressionFault.NameError e) {
				// pass
			}

			if (ans != null) {
				if(!(ans instanceof TypeString))
					throw new ExpressionFault.TypeError();
				return ((TypeString)ans).value;
			}
		}

		// expression cannot be evaluated statically
		return this.makeGeneratedID("gen_id");
	}

	@Override
	public JDefinedClass visit(ASTNode.Signalml node, JDefinedClass dummy)
	{
		log.info("visit((Signalml) %s, %s)", node, dummy);
		assert dummy == null;

		final JDefinedClass klass;
		try {
			klass = this.model._class(dynamicID(node.id));
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}
		klass._extends(jsignalml.codec.Signalml.class);

		klass.metadata = new Metadata(klass);
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
		final JBlock create_params;

		Metadata(JDefinedClass klass, String method_name)
		{
			final JMethod register_params =
				klass.method(JMod.PUBLIC, JavaClassGen.this.model.VOID, method_name);
			this.create_params = register_params.body();
		}

		Metadata(JDefinedClass klass)
		{
			this(klass, "createParams");
		}

		void registerParam(String name, JExpression param_obj)
		{
			log.info("register %s", name);
			this.create_params.add(JExpr.invoke("register").arg(name).arg(param_obj));
		}

		void registerContext(String name, JDefinedClass context_class)
		{
			log.info("register context %s=>%s", name, context_class);
			final JVar obj = this.create_params.decl(context_class, "obj",
								 JExpr._new(context_class));
			registerParam(name, obj);
			this.create_params.add(obj.invoke("createParams"));
		}
	}

	/**
	 * Metadata which adds parameters to createIfParams function, for use with an if.
	 */
	class MetadataIfBranch extends Metadata {
		final JBlock else_params;

		MetadataIfBranch(JDefinedClass klass)
		{
			super(klass, "createIfParams");
			final JMethod else_params =
				klass.method(JMod.PUBLIC, JavaClassGen.this.model.VOID,
					     "createElseParams");
			this.else_params = else_params.body();

		}

		void registerElseParam(String name, JExpression param_obj)
		{
			log.info("register for else %s", name);
			this.else_params.add(JExpr.invoke("register").arg(name).arg(param_obj));
		}
	}

	public JMethod mainMethod(JDefinedClass klass)
	{
		final JMethod main = klass.method(JMod.STATIC | JMod.PUBLIC,
						  this.model.VOID, "main");
		final JVar args = main.varParam(String.class, "args");

		final JBlock body = main.body();

		body.add(this.model.ref(BasicConfigurator.class).staticInvoke("configure"));

		final JVar reader = body.decl(klass, "reader", JExpr._new(klass));
		final JExpression file = JExpr._new(this.model.ref(File.class))
			.arg(args.component(JExpr.lit(0)));
		body.add(reader.invoke("open").arg(file));

		body.add(reader.invoke("createParams"));

		final JExpression dumper_dump = this.model.ref(jsignalml.ContextDumper.class)
			.staticInvoke("dump").arg(reader);
		body.add(this.model.ref(System.class).staticRef("out")
			 .invoke("print").arg(dumper_dump));

		return main;
	}

	public JMethod codecOpenMethod(JDefinedClass klass)
	{
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
		log.info("visit((ExprParam) %s, %s)", node, klass);
		assert klass != null;
		final JDefinedClass nested = paramClass(klass, node);
		if(node.args.isEmpty())
			getExprMethod(nested, node);
		else
			callExprMethod(nested, node);
		getterMethod(klass, dynamicID(node.id), node.type, nested);
		return nested;
	}

	@Override
	public JDefinedClass visit(ASTNode.BinaryParam node, JDefinedClass klass)
	{
		log.info("visit((BinaryParam) %s, %s)", node, klass);
		assert klass != null;
		JDefinedClass nested = paramClass(klass, node);
		readParamFunction(nested, node);
		getterMethod(klass, dynamicID(node.id), node.type, nested);
		return nested;
	}

	JDefinedClass paramClass(JDefinedClass parent, ASTNode.Param node)
	{
		final JClass typeref = convertTypeToJClass(node.type);

		final Class<? extends jsignalml.codec.Context> klass_type;
		if(node.args.isEmpty())
			klass_type = jsignalml.codec.Param.class;
		else
			klass_type = jsignalml.codec.FunctionParam.class;
		final JClass param_class = this.model.ref(klass_type).narrow(typeref);
		final JDefinedClass nested;
		final String theid = dynamicID(node.id);
		try {
			nested = parent._class(makeParamClass(theid));
		} catch(JClassAlreadyExistsException e) {
			throw new SyntaxError(format("duplicate name: '%s'", theid));
		}
		nested._extends(param_class);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerParam(theid, JExpr._new(nested));

		return nested;
	}

	public JMethod readParamFunction(JDefinedClass klass, ASTNode.BinaryParam node)
	{
		assert klass != null;

		final JMethod readfunc = readFunction(klass, node, node.type);

		final JavaExprGen javagen =
			new JavaExprGen(this.model, createResolver(node, null));
		final JClass javatype = convertTypeToJClass(node.type);
		final JMethod impl = klass.method(JMod.PROTECTED, javatype, "_get");

		final JBlock body = impl.body();
		final JVar format_ = body.decl(this.model.ref(TypeString.class), "format",
					       node.format.accept(javagen));
		final JVar offset_ = body.decl(this.model.ref(Type.class), "offset",
					       node.offset.accept(javagen));
		final JExpression offset_int = JExpr._new(this.model.ref(TypeInt.class))
			.invoke("make").arg(offset_);
		final JClass bitform_class = this.model.ref(BitForm.class);
		final JVar theformat = body.decl(bitform_class, "theformat",
						 bitform_class.staticInvoke("get").arg(format_));
		impl.body()._return(JExpr._this().invoke(readfunc).arg(theformat).arg(offset_int));
		return impl;
	}

	public JavaExprGen.JavaNameResolver createResolver(final ASTNode start,
							   final List<JVar> locals)
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
				if (target instanceof ASTNode.BuiltinFunction) {
					final JClass builtins =
						JavaClassGen.this.model.ref(Builtins.class);
					return builtins.staticInvoke(id);
				} else {
					return JExpr.invoke(makeGetter(id));
				}
			}
		};
	}

	public JMethod getExprMethod(JDefinedClass klass, ASTNode.ExprParam node)
	{
		final JClass javatype = convertTypeToJClass(node.type);
		final JMethod impl = klass.method(JMod.PROTECTED, javatype, "_get");
		final JavaExprGen javagen =
			new JavaExprGen(this.model, createResolver(node, null));
		JExpression value = node.expr.accept(javagen);
		make_or_return(impl.body(), node.type, value);
		return impl;
	}

	public JMethod callExprMethod(JDefinedClass klass, ASTNode.ExprParam node)
	{
		final JClass javatype = convertTypeToJClass(node.type);
		final JMethod impl = klass.method(JMod.PUBLIC, javatype, "call");

		List<JVar> locals = util.newLinkedList();
		for (ASTNode.Positional arg: node.args) {
			JVar var = impl.param(convertTypeToJClass(arg.type),
					      dynamicID(arg.id));
			locals.add(var);
		}

		final JClass type_list = this.model.ref(List.class).narrow(Type.class);
		final JMethod cast = klass.method(JMod.PUBLIC, javatype, "call");
		final JVar cast_args = cast.param(type_list, "args");
		final JBlock cast_body = cast.body();
		final JClass ef_am = this.model.ref(ExpressionFault.ArgMismatch.class);
		cast_body._if(cast_args.invoke("size").ne(JExpr.lit(locals.size())))
			._then()
			._throw(JExpr._new(ef_am)
				.arg(cast_args.invoke("size"))
				.arg(JExpr.lit(locals.size())));

		final JInvocation subcall = JExpr._this().invoke("call");
		int i = 0;
		for (JVar arg: locals) {
			final JExpression arg_i = cast_args.invoke("get").arg(JExpr.lit(i++));
			subcall.arg(JExpr.cast(arg.type(), arg_i));
		}
		cast_body._return(subcall);
		
		final JavaExprGen javagen =
			new JavaExprGen(this.model, createResolver(node, locals));
		JExpression value = node.expr.accept(javagen);
		make_or_return(impl.body(), node.type, value);
		return impl;
	}

	public JMethod readFunction(JDefinedClass klass, ASTNode node, Type type)
	{
		final JClass javatype = this.convertTypeToJClass(type);

		final JMethod impl = klass.method(JMod.NONE, javatype, "_get_impl");
		final JVar bitform_param = impl.param(BitForm.class, "bitform");
		final JVar offset_param = impl.param(TypeInt.class, "offset");
		final JBlock body = impl.body();
		body.directStatement(format("// type=%s", type));

		final JClass javatype2 = this.model.ref(Type.class);
		final JExpression expr = bitform_param.invoke("read")
			.arg(JExpr.ref(JExpr.invoke("buffer"), "source"))
			.arg(offset_param);
		final JVar input = body.decl(javatype2, "input", expr);
		make_or_return(body, type, input);
		return impl;
	}

	@Override
	public JDefinedClass visit(ASTNode.FileHandle node, JDefinedClass parent)
	{
		log.info("visit((FileHandle) %s, %s)", node, parent);
		final String theid = dynamicID(node.id);
		final JDefinedClass klass = this.fileClass(node, theid, parent);
		getterMethod(parent, theid, null, klass);
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


		/* add methods
		   - T access(String name) { return super.access(name); }
		   - void register(String name, Context child) { super.register(name, child); }
		   because otherwise methods abstract super classes cannot be called
		   from nested classes.
		*/
		{
			final JMethod get_child =
				klass.method(JMod.PUBLIC, jsignalml.Type.class, "access");
			final JVar name = get_child.param(String.class, "name");
			get_child.body()._return(JExpr._super().invoke("access").arg(name));
		}

		{
			final JMethod register =
				klass.method(JMod.PUBLIC, this.model.VOID, "register");
			final JVar name = register.param(String.class, "name");
			final JVar object = register.param(jsignalml.codec.Context.class, "child");
			register.body().add(JExpr._super()
					    .invoke("register").arg(name).arg(object));
		}

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(id, klass);

		return klass;
	}

	/**
	 * If type is not null, make sure that value is of this type, by
	 * invoking type.make(value). Otherwise just make body return value.
	 */
	public void make_or_return(JBlock body, Type type, JExpression value)
	{
		final JClass javatype = convertTypeToJClass(type);
		if (type != null) {
			// conversion cast requiered
			final JVar var =
				body.decl(javatype, "var",
					  JExpr._new(javatype).invoke("make").arg(value));
			body._return(var);
		} else {
			// no need to cast
			body._return(value);
		}
	}

	public JMethod getterMethod(JDefinedClass klass, String ident, Type type,
				     JDefinedClass nested)
	{
		final JClass typeref = convertTypeToJClass(type);
		final String prefixed = makeIdentifier(ident);
		final JMethod getter = klass.method(JMod.PUBLIC, typeref,
						    makeGetter(ident));
		final JBlock body = getter.body();
		final JVar value = body.decl(convertTypeToJClass(null), "value",
					     JExpr.invoke("access").arg(ident));
		make_or_return(body, type, value);
		return getter;
	}

	@Override
	public JDefinedClass visit(ASTNode.Itername node, JDefinedClass klass)
	{
		log.info("visit((Itername) %s, %s)", node, klass);
		iternameGetter(dynamicID(node.id), klass);
		return klass;
	}

	JMethod iternameGetter(String id, JDefinedClass klass)
	{
		JClass type = this.model.ref(Type.class);
		final JMethod getter = klass.method(JMod.PUBLIC, type,
						    makeGetter(id));
		getter.body()._return(JExpr._this().ref("index").invoke("get"));

		Metadata metadata = (Metadata) klass.metadata;
		metadata.registerParam(id, JExpr._this().ref("index"));

		return getter;
	}

	@Override
	public JDefinedClass visit(ASTNode.ForLoop node, JDefinedClass parent)
	{
		log.info("visit((ForLoop) %s, %s)", node, parent);
		final String theid = dynamicID(node.id);
		final JDefinedClass outer = outerLoopClass(theid, parent);
		sequenceMethod(outer, node);
		final JDefinedClass inner = loopClass(theid, outer);
		createLoopMethod(outer, inner);
		getterMethod(parent, theid, null, outer);
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

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerParam(id, JExpr._new(klass));

		return klass;
	}

	public JMethod sequenceMethod(JDefinedClass klass, ASTNode.ForLoop node)
	{
		final JType list_type = this.model.ref(TypeList.class);
		final JMethod sequence = klass.method(JMod.PROTECTED, list_type, "getSequence");
		final JavaExprGen javagen =
			new JavaExprGen(this.model, createResolver(node, null));
		final JVar range = sequence.body().decl(this.model.ref(Type.class), "range",
							node.sequence.accept(javagen));
		make_or_return(sequence.body(), new TypeList(), range);
		return sequence;
	}

	public JMethod createLoopMethod(JDefinedClass klass, JDefinedClass child_class)
	{
		final JMethod create_loop = klass.method(JMod.PROTECTED, child_class,
							 "createLoop");
		final JVar index = create_loop.param(Type.class, "index");
		create_loop.body()._return(JExpr._new(child_class).arg(index));
		return create_loop;
	}

	public JDefinedClass loopClass(String id, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("LoopItem_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}

		klass._extends(jsignalml.codec.OuterLoopClass.LoopClass.class);

		JMethod constructor = klass.constructor(JMod.NONE);
		JVar index = constructor.param(Type.class, "index");
		constructor.body().add(JExpr.invoke("super").arg(index));

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		return klass;
	}

	@Override
	public JDefinedClass visit(ASTNode.Conditional node, JDefinedClass parent)
	{
		log.info("visit((Conditional) %s, %s)", node, parent);
		String theid = dynamicID(node.id);
		final JDefinedClass klass = conditionalClass(theid, parent);
		conditionMethod(klass, node);
		getterMethod(parent, theid, null, klass);
		return klass;
	}

	JDefinedClass conditionalClass(String id, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("If_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}

		klass._extends(jsignalml.codec.ConditionalClass.class);

		klass.metadata = new MetadataIfBranch(klass);
		log.info("%s.metadata/if has been set", klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(id, klass);

		return klass;
	}

	public JMethod conditionMethod(JDefinedClass klass, ASTNode.Conditional node)
	{
		final JType type = this.model.ref(Type.class);
		final JMethod condition = klass.method(JMod.PUBLIC, type, "getCondition");
		final JavaExprGen javagen =
			new JavaExprGen(this.model, createResolver(node, null));
		final JVar test = condition.body().decl(type, "test",
						       node.condition.accept(javagen));
		condition.body()._return(test);
		return condition;
	}

	@Override
	public JDefinedClass visit(ASTNode.ElseBranch node, JDefinedClass parent)
	{
		log.info("visit((ElseBranch) %s, %s)", node, parent);
		final JDefinedClass klass = elseBranchClass(dynamicID(node.id), parent);
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

		klass._extends(jsignalml.codec.ConditionalClass.ElseBranchClass.class);

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		MetadataIfBranch metadata = (MetadataIfBranch) parent.metadata;
		metadata.registerElseParam(id, JExpr._new(klass));

		return klass;
	}

	@Override
	public JDefinedClass visit(ASTNode.ChannelSet node, JDefinedClass parent)
	{
		log.info("visit((ChannelSet) %s, %s)", node, parent);
		final JDefinedClass klass = channelSetClass(dynamicID(node.id), parent);
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

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(id, klass);

		return klass;
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
		new ASTNode.ExprParam(signalml, field_name, new TypeInt(), expr);

		final Expression expr2 = Processor.parse(field_name + "() + 1");
		new ASTNode.ExprParam(signalml, field_name+"2", new TypeInt(), expr2);

		ASTNode.FileHandle thefile = new ASTNode.FileHandle(signalml, "thefile", null);

		new ASTNode.BinaryParam(thefile, Processor.parse("'readTest'"), new TypeInt(),
					Expression.Const.make("<i4"), Expression.Const.make(25));
		new ASTNode.BinaryParam(thefile, Processor.parse("'readTestConv'"), new TypeInt(),
					Expression.Const.make("|S8"), Expression.Const.make(0));

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
}
