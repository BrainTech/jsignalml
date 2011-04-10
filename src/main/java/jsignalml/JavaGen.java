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
	static String makeParamClass(String name)
	{
		return PREFIX + "_param_" + name;
	}

	final JCodeModel model = new JCodeModel();

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
				klass.method(JMod.PUBLIC, JavaGen.this.model.VOID, method_name);
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
				klass.method(JMod.PUBLIC, JavaGen.this.model.VOID,
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
		assert klass != null;
		final JDefinedClass nested = paramClass(klass, node.type, node.id);
		exprFunction(nested, node, node.type, node.expr);
		getterMethod(klass, node.id, nested);
		return nested;
	}

	@Override
	public JDefinedClass visit(ASTNode.BinaryParam node, JDefinedClass klass)
	{
		assert klass != null;
		JDefinedClass nested = paramClass(klass, node.type, node.id);
		readParamFunction(nested, node, node.type, node.format, node.offset);
		getterMethod(klass, node.id, nested);
		return nested;
	}

	JDefinedClass paramClass(JDefinedClass parent, Type type, String id)
	{
		final JClass typeref = convertTypeToJClass(type);
		final JClass param_class = this.model.ref(jsignalml.codec.Param.class).narrow(typeref);
		final JDefinedClass nested;
		try {
			nested = parent._class(makeParamClass(id));
		} catch(JClassAlreadyExistsException e) {
			throw new SyntaxError(format("duplicate name: '%s'", id));
		}
		nested._extends(param_class);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerParam(id, JExpr._new(nested));

		return nested;
	}


	public JMethod readParamFunction(JDefinedClass klass, ASTNode node,
					 Type type, Expression format, Expression offset)
	{
		assert klass != null;

		final JMethod readfunc = readFunction(klass, node, type);

		final JavaGenVisitor javagen =
			new JavaGenVisitor(this.model, createResolver(node));
		final JClass javatype = convertTypeToJClass(type);
		final JMethod impl = klass.method(JMod.PROTECTED, javatype, "_get");

		final JBlock body = impl.body();
		final JVar format_ = body.decl(this.model.ref(TypeString.class), "format",
					       format.accept(javagen));
		final JVar offset_ = body.decl(this.model.ref(TypeInt.class), "offset",
					       offset.accept(javagen));
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

	public JavaGenVisitor.JavaNameResolver createResolver(final ASTNode start)
	{
		return new JavaGenVisitor.JavaNameResolver() {
			@Override
			public JInvocation lookup(String id)
			{
				final ASTNode target = start.find(id);
				if (target instanceof ASTNode.BuiltinFunction) {
					final JClass klass = JavaGen.this.model.ref(Builtins.class);
					final JInvocation impl_inv = klass.staticInvoke(id);
					return impl_inv;
				} else {
					return JExpr.invoke(makeGetter(id));
				}
			}
		};
	}

	public JMethod exprFunction(JDefinedClass klass, ASTNode node,
				    Type type, Expression expr)
	{
		final JClass javatype = convertTypeToJClass(type);
		final JMethod impl = klass.method(JMod.PROTECTED, javatype, "_get");
		final JavaGenVisitor javagen =
			new JavaGenVisitor(this.model, createResolver(node));
		JExpression value = expr.accept(javagen);
		if (type != null)
			value = JExpr._new(javatype).invoke("make").arg(value);
		impl.body()._return(value);
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
		final JVar var = body.decl(javatype, "var",
					   JExpr._new(javatype).invoke("make").arg(input));
		body._return(var);
		return impl;
	}

	@Override
	public JDefinedClass visit(ASTNode.FileHandle node, JDefinedClass parent)
	{
		final JDefinedClass klass = this.fileClass(node, parent);
		getterMethod(parent, node.id, klass);
		return klass;
	}

	public JDefinedClass fileClass(ASTNode.FileHandle node, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("File_" + node.id);
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
		metadata.registerContext(node.id, klass);

		return klass;
	}

	public JMethod getterMethod(JDefinedClass klass, String ident,
				     JDefinedClass nested)
	{
		final String prefixed = makeIdentifier(ident);
		final JMethod getter = klass.method(JMod.PUBLIC, jsignalml.Type.class,
						    makeGetter(ident));
		getter.body()._return(JExpr.invoke("access").arg(ident));

		return getter;
	}

	@Override
	public JDefinedClass visit(ASTNode.Itername node, JDefinedClass klass)
	{
		iternameGetter(node, klass);
		return klass;
	}

	JMethod iternameGetter(ASTNode.Itername node, JDefinedClass klass)
	{
		JClass type = this.model.ref(jsignalml.codec.OuterLoopClass.LoopClass.IndexClass.class);
		final JMethod getter = klass.method(JMod.PUBLIC, type,
						    makeGetter(node.id));
		getter.body()._return(JExpr._this().ref("index"));

		Metadata metadata = (Metadata) klass.metadata;
		metadata.registerParam(node.id, JExpr._this().ref("index"));

		return getter;
	}

	@Override
	public JDefinedClass visit(ASTNode.ForLoop node, JDefinedClass parent)
	{
		final JDefinedClass outer = outerLoopClass(node, parent);
		sequenceMethod(outer, node);
		final JDefinedClass inner = loopClass(node, outer);
		createLoopMethod(outer, node, inner);
		getterMethod(parent, node.id, outer);
		return inner;
	}

	public JDefinedClass outerLoopClass(ASTNode.ForLoop node, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("Loop_" + node.id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}

		klass._extends(jsignalml.codec.OuterLoopClass.class);

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerParam(node.id, JExpr._new(klass));

		return klass;
	}

	public JMethod sequenceMethod(JDefinedClass klass, ASTNode.ForLoop node)
	{
		final JType list_type = this.model.ref(TypeList.class);
		final JMethod sequence = klass.method(JMod.PROTECTED, list_type, "getSequence");
		final JavaGenVisitor javagen =
			new JavaGenVisitor(this.model, createResolver(node));
		final JVar range = sequence.body().decl(list_type, "range",
							node.sequence.accept(javagen));
		sequence.body()._return(range);
		return sequence;
	}

	public JMethod createLoopMethod(JDefinedClass klass, ASTNode.ForLoop node,
					JDefinedClass child_class)
	{
		final JMethod create_loop = klass.method(JMod.PROTECTED, child_class, "createLoop");
		final JVar index = create_loop.param(Type.class, "index");
		create_loop.body()._return(JExpr._new(child_class).arg(index));
		return create_loop;
	}

	public JDefinedClass loopClass(ASTNode.ForLoop node, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("LoopItem_" + node.id);
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
		final JDefinedClass klass = conditionalClass(node, parent);
		conditionMethod(klass, node);
		getterMethod(parent, node.id, klass);
		return klass;
	}

	JDefinedClass conditionalClass(ASTNode.Conditional node, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("If_" + node.id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}

		klass._extends(jsignalml.codec.ConditionalClass.class);

		klass.metadata = new MetadataIfBranch(klass);
		log.info("%s.metadata/if has been set", klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(node.id, klass);

		return klass;
	}

	public JMethod conditionMethod(JDefinedClass klass, ASTNode.Conditional node)
	{
		final JType type = this.model.ref(Type.class);
		final JMethod condition = klass.method(JMod.PUBLIC, type, "getCondition");
		final JavaGenVisitor javagen =
			new JavaGenVisitor(this.model, createResolver(node));
		final JVar test = condition.body().decl(type, "test",
						       node.condition.accept(javagen));
		condition.body()._return(test);
		return condition;
	}

	@Override
	public JDefinedClass visit(ASTNode.ElseBranch node, JDefinedClass parent)
	{
		final JDefinedClass klass = elseBranchClass(node, parent);
		return klass;
	}

	public JDefinedClass elseBranchClass(ASTNode.ElseBranch node, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("Else_" + node.id);
		} catch(JClassAlreadyExistsException e) {
			throw new RuntimeException("WTF?");
		}

		klass._extends(jsignalml.codec.ConditionalClass.ElseBranchClass.class);

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		MetadataIfBranch metadata = (MetadataIfBranch) parent.metadata;
		metadata.registerElseParam(node.id, JExpr._new(klass));

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

		new ASTNode.BinaryParam(thefile, "readTest", new TypeInt(),
					Expression.Const.make("<i4"), Expression.Const.make(25));
		new ASTNode.BinaryParam(thefile, "readTestConv", new TypeInt(),
					Expression.Const.make("|S8"), Expression.Const.make(0));

		final NameCheck check = new NameCheck();
		signalml.accept(check, null);

		final JavaGen gen = new JavaGen();
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
