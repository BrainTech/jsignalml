package jsignalml;

import static java.lang.String.format;
import static jsignalml.Type.typename;
import static jsignalml.codec.Signalml.isPrimGeneration;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.List;

import jsignalml.ASTNode.ElseIfBranch;
import jsignalml.codec.Signalml.FileClass;
import jsignalml.logging.Logger;
import jsignalml.compiler.MemoryWriter;

import org.apache.log4j.BasicConfigurator;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
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
import com.sun.codemodel.CodeWriter;
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
	final JClass Boolean_t = this.model.ref(Boolean.class);
	final JClass ByteSequence_t = this.model.ref(TypeBytes.ByteSequence.class);
	final JClass Type_t = this.model.ref(Type.class);
	final JClass TypeInt_t = this.model.ref(TypeInt.class);
	final JClass TypeFloat_t = this.model.ref(TypeFloat.class);
	final JClass TypeString_t = this.model.ref(TypeString.class);
	final JClass TypeList_t = this.model.ref(TypeList.class);
	final JClass TypeMap_t = this.model.ref(TypeMap.class);
	final JClass TypeBool_t = this.model.ref(TypeBool.class);
	final JClass TypeBytes_t = this.model.ref(TypeBytes.class);
	final JClass List_of_Type_t = this.model.ref(List.class).narrow(Type.class);

	final JFieldRef TypeInt_I = TypeInt_t.staticRef("I");
	final JFieldRef TypeFloat_I = TypeFloat_t.staticRef("I");
	final JFieldRef TypeString_I = TypeString_t.staticRef("I");
	final JFieldRef TypeList_I = TypeList_t.staticRef("I");
	final JFieldRef TypeMap_I = TypeMap_t.staticRef("I");
	final JFieldRef TypeBool_I = TypeBool_t.staticRef("I");
	final JFieldRef TypeBytes_I = TypeBytes_t.staticRef("I");

	final JClass ArgMismatch_t = this.model.ref(ExpressionFault.ArgMismatch.class);
	final JClass ContextDumper_t = this.model.ref(ContextDumper.class);
	final JClass ChannelSet_t = this.model.ref(ChannelSet.class);
	final JClass Channel_t = this.model.ref(Channel.class);
	final JClass MyBuffer_t = this.model.ref(MyBuffer.class);
	final JClass TextBuffer_t = this.model.ref(TextBuffer.class);
	final JClass XmlBuffer_t = this.model.ref(XmlBuffer.class);
	final JClass BitForm_t = this.model.ref(BitForm.class);
	final JClass Builtins_t = this.model.ref(Builtins.class);
	final JClass RuntimeException_t = this.model.ref(RuntimeException.class);
	final JClass Param_t = this.model.ref(jsignalml.codec.Param.class);
	final JClass FunctionParam_t = this.model.ref(jsignalml.codec.FunctionParam.class);

	final JClass Header_t = this.model.ref(jsignalml.codec.Header.class);
	final JClass CodecId_t = this.model.ref(jsignalml.codec.CodecId.class);
	final JClass FormatId_t = this.model.ref(jsignalml.codec.FormatId.class);

	final JClass BasicConfigurator_t = this.model.ref(BasicConfigurator.class);
	final JClass File_t = this.model.ref(File.class);
	final JClass String_t = this.model.ref(String.class);
	final JClass Logger_t = this.model.ref(Logger.class);
	final JClass System_t = this.model.ref(System.class);
	final JClass FloatBuffer_t = this.model.ref(FloatBuffer.class);
	final JClass ByteBuffer_t = this.model.ref(ByteBuffer.class);
	final JClass FileClass_t = this.model.ref(FileClass.class);

	String class_name = null; // this should be set when Signalml class is created.
	JFieldVar log_var = null; // this should be set when Signalml class is created.
	int number_of_files = 0;  // this should be increased for each File class.
	final ASTTypeResolver typeresolver;

	public String getClassName() {
		return this.class_name;
	}

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

		if (this.class_name != null)
			throw new SyntaxError("unexpected duplicate <signalml> node");

		final JDefinedClass klass;
		String theid = dynamicID(node, node.id);

		if (!isJavaIdentifier(theid)) {
			String theidold = theid;
			theid = theid.replaceAll("([^A-Za-z0-9_])", "_");
			log.info("visit(node id %s -> %s)", theidold, theid);
			assert isJavaIdentifier(theid);
		}
		this.class_name = theid;

		try {
			klass = this.model._class(theid);
		} catch(JClassAlreadyExistsException e) {
			throw new SyntaxError(format("duplicate name: '%s'", theid));
		}
		klass._extends(jsignalml.codec.Signalml.class);
		comment_stamp(klass);

		assert this.log_var == null;
		this.log_var = klass.field(JMod.STATIC|JMod.FINAL, Logger_t, "log",
					   JExpr._new(Logger_t).arg(klass.dotclass()));

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		idMethod(klass, node, theid);
		this.mainMethod(klass);
		this.getFormatIDMethod(klass);
		this.headerFieldMethod(klass, "getFormatName", "format_id", "name");
		this.headerFieldMethod(klass, "getFormatProvider", "format_id", "provider");
		this.headerFieldMethod(klass, "getFormatVersion", "format_id", "version");
		this.headerFieldMethod(klass, "getFormatDescription", "format_id", "description");
		this.headerFieldMethod(klass, "getCodecProvider", "codec_id", "provider");
		this.headerFieldMethod(klass, "getCodecVersion", "codec_id", "version");

		// Private variable for channel objects counter
		klass.field(4, model.INT, "channelCounter", JExpr.lit(0));

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

		final JVar argc = body.decl(this.model.INT, "argc", args.ref("length"));
		final JConditional _if = body._if(argc.lt(JExpr.lit(1)));
		final JBlock _ifBlock = _if._then();
		final JExpression syntax = JExpr.lit("Syntax:\n\t" + klass.name()
				+ " inputFile channelNr1 channelNr2 ...");
		_ifBlock.add(System_t.staticRef("out").invoke("println").arg(syntax));
		_ifBlock._return();

		body.add(BasicConfigurator_t.staticInvoke("configure"));

		final JVar reader = body.decl(klass, "reader", JExpr._new(klass));
		final JExpression file = JExpr._new(File_t).arg(args.component(JExpr.lit(0)));
		body.add(reader.invoke("open").arg(file));

		body.add(reader.invoke("createParams"));
		body.add(reader.invoke("createChannels"));

		comment(body, "%s", "System.out.print(ContextDumper.dump(reader));");
		//final JExpression dumper_dump =
		//	ContextDumper_t.staticInvoke("dump").arg(reader);
		//body.add(System_t.staticRef("out").invoke("print").arg(dumper_dump));

		final JVar nrOfChannelSets = body.decl(this.model.INT, "nrOfChannelSets",
			reader.invoke("getNumberOfChannelSets"));
		{
			final JForLoop for_set_ = body._for();
			final JVar k = for_set_.init(this.model.INT, "k", JExpr.lit(0));
			for_set_.test(k.lt(nrOfChannelSets));
			for_set_.update(k.incr());
			final JBlock forsetbody = for_set_.body();

			final JVar channelSet = forsetbody.decl(ChannelSet_t, "channelSet", reader.invoke("get_set").arg(k));
			final JVar nrOfChannels = forsetbody.decl(this.model.INT, "nrOfChannels",
					channelSet.invoke("getNumberOfChannels"));
			final JVar nrOfChannelsToShow = forsetbody.decl(this.model.INT, "nrOfChannelsToShow",
					nrOfChannels);
			final JExpression inputFileName = JExpr.lit("Input file for " + klass.name() + " codec: ")
					.plus(args.component(JExpr.lit(0)));
			final JExpression inputFileChannels = JExpr.lit("Input file has ")
					.plus(nrOfChannels).plus(JExpr.lit(" channels"));
			forsetbody.add(System_t.staticRef("out").invoke("println").arg(inputFileName));
			forsetbody.add(System_t.staticRef("out").invoke("println").arg(inputFileChannels));
			final JConditional _ifArgc = forsetbody._if(argc.gt(JExpr.lit(1)));
			final JBlock _ifBlockArgc = _ifArgc._then();
			_ifBlockArgc.assign(nrOfChannelsToShow, argc);

			{
				final JForLoop for_ =  forsetbody._for();
				final JVar j = for_.init(this.model.INT, "j", JExpr.lit(1));
				for_.test(j.lte(nrOfChannelsToShow));
				for_.update(j.incr());
				final JBlock forbody = for_.body();

				final JInvocation channel_num =
					Integer_t.staticInvoke("decode").arg(args.component(j)).invoke("intValue");
				final JVar channelNr = forbody.decl(this.model.INT, "channelNr", j.minus(JExpr.lit(1)));
				final JConditional _ifChannelsArgc = forbody._if(argc.gt(JExpr.lit(1)));
				final JBlock _ifBlockChannelsArgc = _ifChannelsArgc._then();
				_ifBlockChannelsArgc.assign(channelNr, channel_num);

				final JVar channel = forbody.decl(Channel_t, "channel",
						channelSet.invoke("getChannel").arg(channelNr));
				final JVar nrOfSamples = forbody.decl(this.model.INT, "nrOfSamples",
						JExpr.cast(this.model.INT, channel.invoke("getNumberOfSamples")));
				final JVar nrOfSamplesToShow = forbody.decl(this.model.INT, "nrOfSamplesToShow",
						this.model.ref(Math.class).staticInvoke("min").arg(nrOfSamples).arg(JExpr.lit(10)));
				final JVar channelName = forbody.decl(String_t, "channelName",
						channel.invoke("getChannelName"));
				final JVar channelType = forbody.decl(String_t, "channelType",
						channel.invoke("getChannelType"));
				final JExpression channelInfo = JExpr.lit("Channel #").plus(channelNr)
						.plus(JExpr.lit("[").plus(channelType).plus(JExpr.lit(" ").plus(channelName)))
						.plus(JExpr.lit("] has ").plus(nrOfSamples).plus(JExpr.lit(" samples:")));
				forbody.add(System_t.staticRef("out").invoke("println").arg(channelInfo));

				/*
				 * long count = channelSet.getNumberOfSamples();
				 * FloatBuffer buffer = FloatBuffer.allocate(((int) count));
				 * channelSet.getChannel(channelNr).getSamples(buffer, 0);
				 */
				//final JVar count = forbody.decl(this.model.LONG, "count",
				//		channelSet.invoke("getNumberOfSamples"));
				//final JInvocation buffer_init = FloatBuffer_t
				//	.staticInvoke("allocate")
				//	.arg(JExpr.cast(this.model.INT, count));
				//final JVar buffer = forbody.decl(FloatBuffer_t, "buffer", buffer_init);
				//forbody.add(channelSet.invoke("getChannel").arg(channelNr)
				//	    .invoke("getSamples").arg(buffer).arg(JExpr.lit(0)));

				final JForLoop forSamples =  forbody._for();
				final JVar i = forSamples.init(this.model.INT, "sampleNr", JExpr.lit(0));
				forSamples.test(i.lt(nrOfSamplesToShow));
				forSamples.update(i.incr());
				final JBlock forSamplesBody = forSamples.body();

				final JExpression sampleUnitValue = forSamplesBody.decl(this.model.FLOAT, "sampleUnitValue",
						channel.invoke("getSample").arg(JExpr.ref("sampleNr")));
				final JExpression sampleInfo = JExpr.lit("\tSample #").plus(JExpr.ref("sampleNr"))
						.plus(JExpr.lit(" ---> ").plus(sampleUnitValue));
				forSamplesBody.add(System_t.staticRef("out").invoke("println").arg(sampleInfo));
			}
		}

		return main;
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

	public JMethod headerFieldMethod(JDefinedClass klass,
					 String method_name,
					 String header_section,
					 String field_name)
	{
		final JMethod method = klass.method(JMod.PUBLIC, String_t,
						    method_name);
		comment_stamp(method.body());
		final JBlock body = method.body();

		final JVar value = body.decl(String_t, "value",
				    body.invoke(makeGetter("header"))
					.invoke(makeGetter(header_section))
					.ref(field_name).invoke("get")
					.invoke("toString"));
		// XXX: this implementation is horrible. There's no reason
		// not to export individual header fields.
		body._return(value);

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

	@Override
	public JDefinedClass visit(ASTNode.XmlParam node, JDefinedClass klass)
	{
		log.info("visit((XmlParam) %s, %s)", node, klass);
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

	@Override
	public JDefinedClass visit(ASTNode.FormatID node, JDefinedClass klass)
	{
		log.info("visit((FormatId) %s, %s)", node, klass);
		assert klass != null;
		final String theid = dynamicID(node, node.id);
		JDefinedClass nested = formatIdClass(klass, theid, node);
		idMethod(nested, node, theid);
		readParamFunction(nested, node);
		return nested;
	}

	JDefinedClass formatIdClass(JDefinedClass parent, String theid, ASTNode.FormatID node)
	{
		final Type nodetype = this.nodeType(node);

		final JDefinedClass nested;
		try {
			String parentParamClass = makeParamClass(theid);
			comment(parent, "parent paramClass=%s", parentParamClass);
			nested = parent._class(parentParamClass);
		} catch(JClassAlreadyExistsException e) {
			throw new SyntaxError(format("duplicate name: '%s'", theid));
		}
		nested._extends(FormatId_t);
		comment_stamp(nested);
		comment(nested, "node.type=%s", typename(node.type));
		comment(nested, "--> nodetype=%s", typename(nodetype));

		final JMethod getter = classCacheMethod(parent, theid, nested);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerParam(theid, nested, JExpr.invoke(getter));

		return nested;
	}

	@Override
	public JDefinedClass visit(ASTNode.CodecID node, JDefinedClass klass)
	{
		log.info("visit((FormatId) %s, %s)", node, klass);
		assert klass != null;
		final String theid = dynamicID(node, node.id);
		JDefinedClass nested = codecIdClass(klass, theid, node);
		idMethod(nested, node, theid);
		readParamFunction(nested, node);
		return nested;
	}

	JDefinedClass codecIdClass(JDefinedClass parent, String theid, ASTNode.CodecID node)
	{
		final Type nodetype = this.nodeType(node);

		final JDefinedClass nested;
		try {
			String parentParamClass = makeParamClass(theid);
			comment(parent, "parent paramClass=%s", parentParamClass);
			nested = parent._class(parentParamClass);
		} catch(JClassAlreadyExistsException e) {
			throw new SyntaxError(format("duplicate name: '%s'", theid));
		}
		nested._extends(CodecId_t);
		comment_stamp(nested);
		comment(nested, "node.type=%s", typename(node.type));
		comment(nested, "--> nodetype=%s", typename(nodetype));

		final JMethod getter = classCacheMethod(parent, theid, nested);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerParam(theid, nested, JExpr.invoke(getter));

		return nested;
	}

	//

	/**
	 * isJavaIdentifier
	 * Method returns true if given string is a legal Java identifier.
	 *
	 * @param string
	 * @return
	 */
	public static boolean isJavaIdentifier(String string) {
		if (string == null || string.isEmpty() || !Character.isJavaIdentifierStart (string.charAt (0))) {
			return false;
		}
		for (int j = 1; j < string.length(); j ++) {
			if (!Character.isJavaIdentifierPart (string.charAt (j))) {
				return false;
			}
		}
		return true;
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
				/*if (! isJavaIdentifier (value)) {
					String valueOld = value;
					value = value.replaceAll("([^A-Za-z0-9_])", "_");
					log.info("idMethod(node id %s -> %s, class %s)", valueOld, value, klass);
					assert isJavaIdentifier (value);
				} else {
					log.info("idMethod(node id %s, class %s)", value, klass);
				}*/
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
		comment(body, "line=(%s)", node.line);
		comment(body, "line.type=%s", typename(node.line.type));
		comment(body, "pattern=(%s)", node.pattern);
		comment(body, "pattern.type=%s", typename(node.pattern.type));
		comment(body, "group=(%s)", node.group);
		comment(body, "group.type=%s", typename(node.group.type));

		final JVar line_ = body.decl(TypeInt_t, "line", JExpr.cast(TypeInt_t, node.line.accept(javagen)));
		final JVar pattern_ = body.decl(TypeString_t, "pattern", node.pattern.accept(javagen));
		final JVar group_ = body.decl(TypeInt_t, "group", JExpr.cast(TypeInt_t, node.group.accept(javagen)));
		final JVar textBuf = body.decl(TextBuffer_t, "textBuf", JExpr.invoke("textBuffer"));
		final JVar _t = body.decl(nodetype_t, "_t", JExpr._null());
		final JVar value = body.decl(nodetype_t, "value",
				textBuf.invoke("read").arg(line_).arg(pattern_).arg(group_).arg(_t));
		body._return(value);

		if (isPrimGeneration())
			getMethod_p(klass, nodetype);

		return impl;
	}

	public JMethod readParamFunction(JDefinedClass klass, ASTNode.XmlParam node) {
		assert klass != null;

		final JavaExprGen javagen = createExprGen(node, null);
		final Type nodetype = nodeType(node);
		final JClass nodetype_t = convertTypeToJClass(nodetype);
		final JMethod impl = klass.method(JMod.PROTECTED, nodetype_t, GET_PRIV);
		final JBlock body = impl.body();
		comment_stamp(body);

		comment(body, "node.type=%s", typename(node.type));
		comment(body, "--> nodetype=%s", typename(nodetype));
		comment(body, "xpathPattern=(%s)", node.xpathPattern);
		comment(body, "xpathEvaluationType=(%s)", node.xpathEvaluationType);
		comment(body, "xpathAttributeName=(%s)", node.xpathAttributeName);

		final JVar xpathPattern_ = body.decl(TypeString_t, "xpathPattern",
				node.xpathPattern.accept(javagen));

		final JVar xmlBuf = body.decl(XmlBuffer_t, "xmlBuf",
				JExpr.invoke("xmlBuffer"));
		final JVar _t = body.decl(nodetype_t, "_t", JExpr._null());
		final JVar value;
		if (node.xpathAttributeName != null){
			value = body.decl(nodetype_t, "value", xmlBuf.invoke("read")
					.arg(xpathPattern_).arg(node.xpathEvaluationType).arg(node.xpathAttributeName).arg(_t));
		} else {
			value = body.decl(nodetype_t, "value", xmlBuf.invoke("read")
					.arg(xpathPattern_).arg(node.xpathEvaluationType).arg(_t));
		}
		body._return(value);

		if (isPrimGeneration())
			getMethod_p(klass, nodetype);
		return impl;
	}

	public JMethod readParamFunction(JDefinedClass klass, ASTNode.FormatID node) {
		assert klass != null;

		final JavaExprGen javagen = createExprGen(node, null);
		final Type nodetype = new TypeString();
		final JClass nodetype_t = convertTypeToJClass(nodetype);
		final JMethod impl = klass.method(JMod.PROTECTED, nodetype_t, GET_PRIV);
		final JBlock body = impl.body();
		comment_stamp(body);

		comment(body, "node.type=%s", typename(node.type));
		comment(body, "--> nodetype=%s", typename(nodetype));
		comment(body, "name=(%s)", node.name);
		comment(body, "name.type=%s", typename(node.name.type));
		comment(body, "provider=(%s)", node.provider);
		comment(body, "provider.type=%s", typename(node.provider.type));
		comment(body, "version=(%s)", node.version);
		comment(body, "version.type=%s", typename(node.version.type));

		JFieldVar name_ = klass.field(1, Type_t, "name", node.name.accept(javagen));
		JFieldVar provider_ = klass.field(1, Type_t, "provider", node.provider.accept(javagen));
		JFieldVar version_ = klass.field(1, Type_t, "version", node.version.accept(javagen));
		JFieldVar description_ = klass.field(1, Type_t, "description",
			node.description != null ? node.description.accept(javagen) :
						   JExpr._null());

		final JVar value;
		value = body.decl(TypeString_t, "value", JExpr._new(TypeString_t)
				.arg(name_.invoke("toString").plus(JExpr.lit(":")).plus(provider_)
						.plus(JExpr.lit(":")).plus(version_)));
		body._return(value);

		return impl;
	}

	public JMethod readParamFunction(JDefinedClass klass, ASTNode.CodecID node) {
		assert klass != null;

		final JavaExprGen javagen = createExprGen(node, null);
		final Type nodetype = new TypeString();
		final JClass nodetype_t = convertTypeToJClass(nodetype);
		final JMethod impl = klass.method(JMod.PROTECTED, nodetype_t, GET_PRIV);
		final JBlock body = impl.body();
		comment_stamp(body);

		comment(body, "node.type=%s", typename(node.type));
		comment(body, "--> nodetype=%s", typename(nodetype));
		comment(body, "provider=(%s)", node.provider);
		comment(body, "provider.type=%s", typename(node.provider.type));
		comment(body, "version=(%s)", node.version);
		comment(body, "version.type=%s", typename(node.version.type));

		JFieldVar provider_ = klass.field(1, Type_t, "provider", node.provider.accept(javagen));
		JFieldVar version_ = klass.field(1, Type_t, "version", node.version.accept(javagen));

		final JVar value;
		value = body.decl(TypeString_t, "value", JExpr._new(TypeString_t)
				.arg(provider_.invoke("toString").plus(JExpr.lit(":")).plus(version_)));
		body._return(value);

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
		} else if (wanted instanceof TypeBool) {
			get = get.invoke("getValue");
			type = Boolean_t;
		} else if (wanted instanceof TypeBytes) {
			get = get.invoke("getValue");
			type = ByteSequence_t;
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
		final JType javatype = gen.convertTypeToJClass_p(node, nodetype);
		final JMethod impl = klass.method(JMod.PUBLIC, javatype, CALL_P);
		final JBlock body = impl.body();
		comment_stamp(body);

		for (ASTNode.Positional arg: node.args) {
			JVar var = impl.param(gen.convertTypeToJClass_p(node, arg.type),
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
	public JDefinedClass visit(ASTNode.Header node, JDefinedClass parent)
	{
		log.info("visit((Header) %s, %s)", node, parent);

		final String theid = dynamicID(node, node.id);
		final JDefinedClass klass = this.headerClass(node, theid, parent);
		idMethod(klass, node, theid);
		return klass;
	}

	public JDefinedClass headerClass(ASTNode.Header node, String id,
		       JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class(id);
		} catch(JClassAlreadyExistsException e) {
			throw new SyntaxError(format("duplicate name: '%s'", id));
		}
		klass._extends(jsignalml.codec.Header.class);
		comment_stamp(klass);

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		final JMethod getter = classCacheMethod(parent, id, klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(id, klass, JExpr.invoke(getter));

		return klass;
	}

	@Override
	public JDefinedClass visit(ASTNode.FileHandle<?> node, JDefinedClass parent)
	{
		log.info("visit((FileHandle) %s, %s)", node, parent);

		final String theid = dynamicID(node, node.id);
		final JDefinedClass klass = this.fileClass(node, theid, parent,
							   ++this.number_of_files-1);
		idMethod(klass, node, theid);
		return klass;
	}

	public JDefinedClass fileClass(ASTNode.FileHandle<?> node, String id,
				       JDefinedClass parent, int file_index)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("File_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new SyntaxError(format("duplicate name: '%s'", id));
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
			body.directStatement(format("super(%d);", file_index));
			// XXX: how to call super properly in codemodel?

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

			if(!node.isBinary){
				body.assign(JExpr.ref("isBinary"),
						JExpr.FALSE);
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
			throw new SyntaxError(format("duplicate name: '%s'", id));
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
			throw new SyntaxError(format("duplicate name: '%s'", id));
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
			throw new SyntaxError(format("duplicate name: '%s'", id));
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
			throw new SyntaxError(format("duplicate name: '%s'", id));
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
			throw new SyntaxError(format("duplicate name: '%s'", id));
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
			throw new SyntaxError(format("duplicate name: '%s'", id));
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
		/*JFieldVar stor = */klass.field(JMod.FINAL, indexClass, "index");

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
			throw new SyntaxError(format("duplicate name: '%s'", id));
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
		applyLinearTransformationMethod(klass);
		getSamplingFrequencyMethod(klass, node);
		getNumberOfSamplesMethod(klass, node);
		getChannelNameMethod(klass, node);
		getChannelTypeMethod(klass, node);
		getCalibrationGainMethod(klass, node);
		getCalibrationOffsetMethod(klass, node);

		return klass;
	}

	public JDefinedClass channelClass(String id, JDefinedClass parent)
	{
		final JDefinedClass klass;
		try {
			klass = parent._class("Channel_" + id);
		} catch(JClassAlreadyExistsException e) {
			throw new SyntaxError(format("duplicate name: '%s'", id));
		}
		klass._extends(jsignalml.codec.ChannelClass.class);
		comment_stamp(klass);

		klass.metadata = new Metadata(klass);
		log.info("%s.metadata has been set", klass);

		final JMethod getter = classCacheMethod(parent, id, klass);

		Metadata metadata = (Metadata) parent.metadata;
		metadata.registerContext(id, klass, JExpr.invoke(getter));
		metadata.registerChannel(id, JExpr.invoke(getter));

		// Private variable for channel number
		klass.field(4, model.INT, "channelNum", JExpr.ref("channelCounter").incr());

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
		final JMethod method = klass.method(JMod.PUBLIC, this.model.FLOAT, "getSample");
		comment_stamp(method.body());

		final JVar sample = method.param(this.model.LONG, "sample");

		final JavaExprGen javagen = createExprGen(node, null);
		final JBlock body = method.body();
		final JVar format_ = body.decl(TypeString_t, "format_",
					       JExpr._this().invoke("getSampleFormat"));
		final JVar format = body.decl(BitForm_t, "format",
					      BitForm_t.staticInvoke("get").arg(format_));

		JVar dataFilehandler = null;
		if (node.data != null) {
			JExpression dataFileIdValue = node.data.accept(javagen);
			final JVar dataFileId = body.decl(Type_t, "dataFileId", dataFileIdValue);
			dataFilehandler = body.decl(FileClass_t, "fileHandler", JExpr.cast(FileClass_t, dataFileId));
		}

		JVar buffer = null;
		if (node.data != null){
			buffer = fillBufferFromDataFile(dataFilehandler, javagen, body);
		} else {
			buffer = body.decl(ByteBuffer_t, "buffer", JExpr.invoke("_buffer").ref("source"));
		}

		if (isPrimGeneration()) {
			// Primitive types code variant

			final JExpression mapping_call = JExpr.cast(model.INT,
					JExpr.invoke("get_mapping").invoke(CALL_P).arg(sample));

			final JConditional _if = body._if(JExpr.invoke(dataFilehandler, "isBinary"));
			final JBlock _ifBlock = _if._then();
			final JExpression input = format.invoke("read").arg(buffer)
					.arg(mapping_call);
			final JVar rawValue = _ifBlock.decl(this.model.FLOAT, "rawValue", input);
			_ifBlock._return(JExpr.invoke("applyLinearTransformation").arg(rawValue));

			final JBlock _elseBlock = _if._else();
			final JVar rawValueElse = _elseBlock.decl(this.model.FLOAT, "rawValue",
					JExpr.invoke(dataFilehandler, "getScanner").invoke("readFloat").arg(mapping_call));
			_elseBlock._return(JExpr.invoke("applyLinearTransformation").arg(rawValueElse));

		} else {
			final JVar mapping = body.decl(Type_t, "mapping",
					node.mapping.accept(javagen));
			final JExpression mapping_call = mapping.invoke(CALL)
					.arg(JExpr._new(TypeInt_t).arg(sample));

			final JConditional _if = body._if(JExpr.invoke(dataFilehandler, "isBinary"));

			final JBlock _ifBlock = _if._then();
			final JVar input = _ifBlock.decl(Type_t, "input",
					format.invoke("read").arg(buffer)
					.arg(JExpr.cast(TypeInt_t, mapping_call)));
			final JExpression conv = TypeFloat_I.invoke("make").arg(input);
			final JVar rawValue = _ifBlock.decl(this.model.FLOAT, "rawValue",
					JExpr.cast(this.model.FLOAT, conv.ref("value")));
			_ifBlock._return(JExpr.invoke("applyLinearTransformation").arg(rawValue));

			final JBlock _elseBlock = _if._else();
			final JVar rawValueElse = _elseBlock.decl(
					this.model.FLOAT,"rawValue",
					JExpr.invoke(dataFilehandler, "getScanner")
							.invoke("readFloat")
							.arg(((JExpression) JExpr.cast(TypeInt_t, mapping_call)).ref("value")
									.invoke("intValue")));

			_elseBlock._return(JExpr.invoke("applyLinearTransformation").arg(rawValueElse));
		}
		return method;
	}

	public JMethod getSamplesMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		//final Expression fastSet = Processor.parse("1");
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

		JVar dataFilehandler = null;
		if (node.data != null) {
			JExpression dataFileIdValue = node.data.accept(javagen);
			final JVar dataFileId = body.decl(Type_t, "dataFileId", dataFileIdValue);
			dataFilehandler = body.decl(FileClass_t, "fileHandler", JExpr.cast(FileClass_t, dataFileId));
		}

		JVar buffer = null;
		if(node.data != null){
			buffer = fillBufferFromDataFile(dataFilehandler, javagen, body);
		} else {
			buffer = body.decl(ByteBuffer_t, "buffer",
					JExpr.invoke("_buffer").ref("source"));
		}

		if (isPrimGeneration()) {
			// Primitive types code variant
			final JVar count = body.decl(this.model.INT, "count",
					JExpr.invoke(dst, "remaining"));
			final JExpression mapping_call = JExpr.cast(model.INT,
					JExpr.invoke(makeGetter(node.mapping.toString())).invoke(CALL_P)
					.arg(sample.incr()));

			final JConditional _if = body._if(JExpr.invoke(dataFilehandler, "isBinary"));

			final JBlock _ifBlock = _if._then();
			final JBlock _while = _ifBlock._while(count.decr().gt(JExpr.lit(0))).body();
			final JExpression input = format.invoke("read").arg(buffer)
					.arg(mapping_call);
			final JVar rawValue = _while.decl(this.model.FLOAT, "rawValue", input);
			_while.add(dst.invoke("put").arg(JExpr.invoke("applyLinearTransformation").arg(rawValue)));

			final JBlock _elseBlock = _if._else();
			final JBlock _whileElse = _elseBlock._while(count.decr().gt(JExpr.lit(0))).body();
			final JVar rawValueElse = _whileElse.decl(this.model.FLOAT, "rawValue",
					JExpr.invoke(dataFilehandler, "getScanner").invoke("readFloat").arg(mapping_call));
			_whileElse.add(dst.invoke("put").arg(JExpr.invoke("applyLinearTransformation").arg(rawValueElse)));

		} else {
			final JVar mapping = body.decl(Type_t, "mapping",
					node.mapping.accept(javagen));
			final JExpression mapping_call = mapping.invoke(CALL)
					.arg(JExpr._new(TypeInt_t).arg(sample.incr()));

			final JConditional _if = body._if(JExpr.invoke(dataFilehandler, "isBinary"));

			final JBlock _ifBlock = _if._then();

			final JBlock _while = _ifBlock._while(JExpr.invoke(dst, "hasRemaining")).body();
			final JVar input = _while.decl(Type_t, "input",
					format.invoke("read").arg(buffer)
					.arg(JExpr.cast(TypeInt_t, mapping_call)));
			final JExpression conv = TypeFloat_I.invoke("make").arg(input);
			final JVar rawValue = _while.decl(this.model.FLOAT, "rawValue",
					JExpr.cast(this.model.FLOAT, conv.ref("value")));
			_while.add(dst.invoke("put").arg(JExpr.invoke("applyLinearTransformation").arg(rawValue)));

			final JBlock _elseBlock = _if._else();
			final JBlock _whileElse = _elseBlock._while(JExpr.invoke(dst, "hasRemaining")).body();
			final JVar rawValueElse = _whileElse.decl(
					this.model.FLOAT,"rawValue",
					JExpr.invoke(dataFilehandler, "getScanner")
							.invoke("readFloat")
							.arg(((JExpression) JExpr.cast(TypeInt_t, mapping_call)).ref("value")
									.invoke("intValue")));
			_whileElse.add(dst.invoke("put").arg(JExpr.invoke("applyLinearTransformation").arg(rawValueElse)));
		}
		return method;
	}

	private JMethod applyLinearTransformationMethod(JDefinedClass klass){
		final JMethod method = klass.method(JMod.PRIVATE, this.model.FLOAT, "applyLinearTransformation");
		final JVar rawValue = method.param(this.model.FLOAT, "rawValue");
		comment_stamp(method.body());
		final JBlock body = method.body();
		final JVar calibGain = body.decl(this.model.FLOAT, "calibGain",
				JExpr.invoke("getCalibrationGain").invoke("getValue")
				.invoke("floatValue"));
		final JVar calibOffs = body.decl(this.model.FLOAT, "calibOffs",
				JExpr.invoke("getCalibrationOffset").invoke("getValue")
				.invoke("floatValue"));

		body._return(rawValue.minus(calibOffs).mul(calibGain));
		return method;
	}

	private JVar fillBufferFromDataFile(final JVar dataFilehandler, final JavaExprGen javagen,
			final JBlock body) {
		JVar buffer;

		JVar filename = body.decl(File_t, "file",
				JExpr.invoke(dataFilehandler, "getCurrentFilename"));
		body._if(filename.eq(JExpr._null()))
				._then().invoke(dataFilehandler, "open").arg(JExpr._null());
		buffer = body.decl(ByteBuffer_t, "buffer",
				JExpr.invoke(dataFilehandler, "buffer").ref("source"));
		return buffer;
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

	public JMethod getChannelNameMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, String_t, "getChannelName");
		comment_stamp(method.body());

		JInvocation ji = JExpr.invoke("get_channel_name").invoke("get");
		final JBlock body = method.body();

		final JVar value = body.decl(Type_t, "value", ji);
		final JInvocation jiji = body.decl(TypeString_t, "stringValue",
				JExpr.cast(TypeString_t, value)).invoke("getValue");
		final JVar strValue = body.decl(String_t, "strValue", jiji);

		final JConditional _if = body._if(strValue.invoke("equals").arg(JExpr.lit("")));
		final JBlock _ifBlock = _if._then();
		_ifBlock._return(JExpr.lit("L").plus(JExpr.ref("channelNum")));

		method.body()._return(strValue);

		return method;
	}

	public JMethod getUnit(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, String_t, "getUnit");
		comment_stamp(method.body());

		JInvocation jInvo = JExpr.invoke("get_unit").invoke("get");
		final JVar value = method.body().decl(Type_t, "value", jInvo);
		final JInvocation jInvoStr = method.body().decl(TypeString_t, "stringValue",
				JExpr.cast(TypeString_t, value)).invoke("getValue");
		method.body()._return(jInvoStr);

		return method;
	}

	public JMethod getChannelTypeMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, String_t, "getChannelType");
		comment_stamp(method.body());

		JInvocation ji = JExpr.invoke("get_channel_type").invoke("get");

		final JVar value = method.body().decl(Type_t, "value", ji);
		final JInvocation jiji = method.body().decl(TypeString_t, "stringValue",
				JExpr.cast(TypeString_t, value)).invoke("getValue");
		method.body()._return(jiji);

		return method;
	}

	public JMethod getCalibrationGainMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, TypeFloat_t,
				"getCalibrationGain");
		comment_stamp(method.body());

		final JVar value = method.body().decl(Type_t, "value",
					JExpr.invoke("get_calibration_gain").invoke("get"));
		JInvocation	ji = TypeFloat_I.invoke("make").arg(value);

		final JVar cast = method.body().decl(TypeFloat_t, "cast", ji);
		method.body()._return(cast);

		return method;
	}

	public JMethod getCalibrationOffsetMethod(JDefinedClass klass, ASTNode.Channel node)
	{
		final JMethod method = klass.method(JMod.PUBLIC, TypeFloat_t,
				"getCalibrationOffset");
		comment_stamp(method.body());

		final JVar value = method.body().decl(Type_t, "value",
					JExpr.invoke("get_calibration_offset").invoke("get"));
		JInvocation	ji = TypeFloat_I.invoke("make").arg(value);

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
	public void write(CodeWriter writer)
		throws java.io.IOException
	{
		this.model.build(writer);
	}
	public void write(File outputdir)
		throws java.io.IOException
	{
		this.model.build(new FileCodeWriter(outputdir));
	}

	public CharSequence getSourceCode()
		throws java.io.IOException
	{
		final MemoryWriter writer = new MemoryWriter();
		this.write(writer);
		return writer.getCode();
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

		ASTNode.FileHandle<?> thefile = new ASTNode.FileHandle(signalml, "thefile", null, true);

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
			throw new SyntaxError("Comment not applicable for object of class " +
					where.getClass().getCanonicalName());
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
