package jsignalml;

import static java.lang.String.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import jsignalml.xml.XMLDocument;
import jsignalml.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

/**
 * Translate an XML DOM into an ASTNode tree.
 */
public class CodecParser {
	private static final String XPATH_TAG_NAME = "xpath";
	private static final String XPATH_EVALUATION_TYPE_ATTRIBUTE_NAME = "xpath-evaluation-type";
	private static final String XPATH_ATTRIBUTE_NAME_ATTRIBUTE_NAME = "xpath-attribute-name";
	public static final Logger log = new Logger(CodecParser.class);

	final String name_hint;
	public final ASTNode codec;

	String format_id = null;
	String codec_id = null;

	public CodecParser(String name_hint, XMLDocument xml)
	{
		assert name_hint != null;
		this.name_hint = name_hint;
		this.codec = this.parse_signalml(xml);
	}

	public CodecParser(File filename)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		this(util.basename_noext(filename),
		     new XMLDocument(filename));
	}

	public ASTNode dispatch(ASTNode parent, Element element)
	{
		log.debug("dispatch: %s id='%s'", element, element.getAttribute("id"));
		final String name = element.getNodeName();
		if (name.equals("signalml"))
			return this.do_signalml(parent, element);
		else if (name.equals("channelset"))
			return this.do_channelset(parent, element);
		else if (name.equals("channel"))
			return this.do_channel(parent, element);
		else if (name.equals("param"))
			return this.do_param(parent, element);
		else if (name.equals("arg"))
			return CodecParser.do_arg(parent, element);
		else if (name.equals("assert"))
			return this.do_assert(parent, element);
		else if (name.equals("for-each"))
			return this.do_forloop(parent, element);
		else if (name.equals("if"))
			return this.do_conditional(parent, element);
			//return this.do_conditional(parent, element, false);
		else if (name.equals("else"))
			return this.do_else(parent, element);
		else if (name.equals("else-if"))
			return this.do_elseIf(parent, element);
			//return this.do_conditional(parent, element, true);
		else if (name.equals("file"))
			return this.do_file(parent, element);
		else if (name.equals("header"))
			return this.do_header(parent, element);
		else if (name.equals("format_id"))
			return this.do_format_id(parent, element);
		else if (name.equals("codec_id"))
			return this.do_codec_id(parent, element);
		else if (name.equals("expr") || // expression
			 name.equals("format") || name.equals("offset") || // binary
			 name.equals("pattern") || name.equals("line") || // text
			 name.equals("xpath") || name.equals("group") || // xml
			 name.equals("version") || name.equals("name") || // header
			 name.equals("provider") || name.equals("info"))
			return null; /* handled directly */
		log.warn("unknown element: %s", element);
		return null;
	}

	public ASTNode walk(ASTNode parent, Element element)
	{
		final ASTNode node = this.dispatch(parent, element);

		if (node != null) {
			final Iterable<Element> iter = XMLDocument.subNodes_re(element, "*");
			for (Element child: iter)
				this.walk(node, child);
		}

		return node;
	}

	public ASTNode parse_signalml(XMLDocument doc)
	{
		final Element element;
		try {
			element = doc.getElement_re("/signalml");
		} catch (XMLDocument.NodeError e) {
			throw new IllegalArgumentException("no /signalml node");
		}
		return this.walk(null, element);
	}

	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

	public ASTNode.Signalml do_signalml(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("signalml");

		String name = _extract_string(element, "header/name");
		if (name == null)
			name = this.name_hint;

		return new ASTNode.Signalml(name);
	}

	public ASTNode.Header do_header(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("header");

		final Expression id = Expression.Const.make("header");
		return new ASTNode.Header(parent, id);
	}

	void checkNotEmpty(String where, String field, String value) {
		if (value.isEmpty())
			throw new SyntaxError("element <" + where + "/" + field
					      + "> cannot be empty");
	}

	public ASTNode.FormatID do_format_id(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("format_id");
		final Expression id = Expression.Const.make("format_id");

		String name = _extract_string(element, "name");
		String provider = _extract_string(element, "provider");
		String version = _extract_string(element, "version");
		String info = _extract_string(element, "info");

		if (name == null)
			name = this.name_hint;
		checkNotEmpty("header/format_id", "name", name);
		if (provider == null)
			provider = "unknown";
		checkNotEmpty("header/format_id", "provider", provider);
		if (version == null)
			version = ""; // on purpose: not everything is versioned
		if (info == null)
			info = "";

		ASTNode.FormatID node = new ASTNode.FormatID(parent, id, name,
							     provider, version,
							     info);
		final EvalVisitor valuator = EvalVisitor.create(node);
		this.format_id = valuator.quickEval(TypeString.I, node.name);
		return node;
	}

	public ASTNode.CodecID do_codec_id(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("codec_id");
		final Expression id = Expression.Const.make("codec_id");

		String provider = _extract_string(element, "provider");
		String version  = _extract_string(element, "version");

		if (provider == null)
			provider = "unknown";
		checkNotEmpty("header/codec_id", "provider", provider);
		if (version == null)
			version = "0";
		checkNotEmpty("header/codec_id", "version", version);

		return new ASTNode.CodecID(parent, id, provider, version);
	}

	public ASTNode.Param do_param(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("param");

		final Expression id = _identifier(element);
		final String type_ = _attribute(element, "type");
		final Type type = Type.getType(type_);
		//final String fast_ = _attribute(element, "fast");
		//final Expression fast = _parse_attribute(fast_);

		final Expression expr    = _extract(element, "expr");
		final Expression format  = _extract(element, "format");
		final Expression offset  = _extract(element, "offset");
		final Expression pattern = _extract(element, "pattern");
		final Expression line    = _extract(element, "line");
		final Expression group    = _extract(element, "group");
		final Expression xpath   = _extract(element, "xpath");

		/* The code below is structed like it is to keep it simple and
		   reduce repetitions. */

		ASTNode.Param p = null;

		if (expr != null) {
			if (format == null && offset == null && pattern == null &&
			                line == null && xpath == null)
				p = new ASTNode.ExprParam(parent, id, type, expr);
		} else if (format != null && offset != null) {
			if (expr == null && pattern == null && line == null && xpath == null)
				p = new ASTNode.BinaryParam(parent, id, type, format, offset);
		} else if (pattern != null && group != null) {
			if (offset == null && xpath == null && expr == null && format == null)
				p = new ASTNode.TextParam(parent, id, type, line, pattern, group);
		} else if (xpath != null) {
			if ((expr == null) && (pattern == null) && (line == null) && (offset == null)){
				String xpathEvaluationType = getXpathEvaluationTypeFromParamElement(element);
				String xpathAttributeName = getXpathAttributeNameFromParamElement(element);
				p = new ASTNode.XmlParam(parent, id, type, xpath, xpathEvaluationType, xpathAttributeName);
			}
		} else {
			throw new SyntaxError("not enough attributes: " + element);
		}

		if (p == null)
			throw new SyntaxError("too many attributes: " + element);

		return p;
	}



	private String getXpathEvaluationTypeFromParamElement(Element paramElement) {
		String xpathTypeAttrValue = null;
		if (paramElement.hasChildNodes()) {
			NodeList nl = paramElement.getChildNodes();
			Node node;
			for (int i = 0; i < nl.getLength(); i++) {
				node = nl.item(i);
				String nodeLocalName = node.getLocalName();
				if ((nodeLocalName != null) && (nodeLocalName.equals(XPATH_TAG_NAME))) {
					xpathTypeAttrValue = node.getAttributes()
							.getNamedItem(XPATH_EVALUATION_TYPE_ATTRIBUTE_NAME).getFirstChild()
							.getNodeValue();
				}
			}
		}
		return xpathTypeAttrValue;
	}

	private String getXpathAttributeNameFromParamElement(Element paramElement) {
		String xpathTypeAttrValue = null;
		if (paramElement.hasChildNodes()) {
			NodeList nl = paramElement.getChildNodes();
			Node node;
			for (int i = 0; i < nl.getLength(); i++) {
				node = nl.item(i);
				String nodeLocalName = node.getLocalName();
				if ((nodeLocalName != null) && (nodeLocalName.equals(XPATH_TAG_NAME))) {
					NamedNodeMap nnm = node.getAttributes();
					Node attributeNameNode = nnm.getNamedItem(XPATH_ATTRIBUTE_NAME_ATTRIBUTE_NAME);
					if (attributeNameNode != null){
						xpathTypeAttrValue = attributeNameNode.getFirstChild().getNodeValue();
					}
				}
			}
		}
		return xpathTypeAttrValue;
	}

        static public ASTNode.Positional do_arg(ASTNode parent, Element element)
        {
                assert element.getNodeName().equals("arg");

                final String name = _attribute(element, "name");
                final String type_ = _attribute(element, "type");
		final Type type;
		try {
			type = Type.getType(type_);
		} catch(IllegalArgumentException e){
			throw new SyntaxError(format("%s: unkown type='%s'", element, type_));
		}

                final ASTNode.Positional arg = new ASTNode.Positional(parent, name, type);

		return arg;
        }

	public ASTNode.Assert do_assert(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("assert");

		final Expression id = _identifier(element);
		final Expression expr = _extract(element, "expr");
		if (expr == null)
			throw new SyntaxError("<assert> needs an <expr> child");

		return new ASTNode.Assert(parent, id, expr);
	}

	public ASTNode.ChannelSet do_channelset(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("channelset");

		final Expression id = _identifier(element);

		final ASTNode.ChannelSet node = new ASTNode.ChannelSet(parent, id);
		return node;
	}

	public ASTNode.Channel do_channel(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("channel");

		final Expression id = _identifier(element);
		final Expression mapping = _parse_attribute(element, "mapping");
		final Expression format = _parse_attribute(element, "format");
		final Expression length = _parse_attribute(element, "length");
		//final Expression fast = _parse_attribute(element, "fast");
		final Expression data = _parse_attribute(element, "data");

		final ASTNode.Channel node = new ASTNode.Channel(parent, id, mapping, format,
				length, data);
		return node;
	}

	public ASTNode.FileHandle<?> do_file(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("file");

		final Expression id = _identifier(element);
		final String type = _attribute(element, "type");
		final Expression filename = _parse_attribute(element, "filename");

		if (type == null)
			throw new SyntaxError("<file> needs a type attribute");

		final ASTNode.FileHandle<?> handle = ASTNode.FileHandle.make(parent, id, filename, type);
		return handle;
	}

	public ASTNode.ForLoop do_forloop(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("for-each");

		final Expression id = _identifier(element);
		final String var = _attribute(element, "var");

		final String type_ = _attribute(element, "type");
		final Type type;
		try {
			type = Type.getType(type_);
		} catch(IllegalArgumentException e){
			throw new SyntaxError(format("%s: unkown type='%s'", element, type_));
		}

		final Expression expr = _parse_attribute(element, "sequence");
		return new ASTNode.ForLoop(parent, id, var, type, expr);
	}

	public ASTNode.Conditional do_conditional(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("if");

		final Expression id = _identifier(element);
		final Expression expr = _parse_attribute(element, "test");
		return new ASTNode.Conditional(parent, id, expr);
	}

	public ASTNode.ElseIfBranch do_elseIf(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("else-if");

		final Expression id = _identifier(element);
		final Expression expr = _parse_attribute(element, "test");
		return new ASTNode.ElseIfBranch(parent, id, expr);
	}

	public ASTNode.ElseBranch do_else(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("else");

		final Expression id = _identifier(element);
		return new ASTNode.ElseBranch(parent, id);
	}

	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

	static String _extract_string(Node where, String xpath)
	{
		Node node = XMLDocument._subNode_re(where, xpath);
		if (node == null)
			return null;
		else
			return node.getTextContent();
	}

	/**
	 * Get the expression pointed to by xpath (starting from where).
	 * Returns Expression or null if not found.
	 */
	static Expression _extract(Node where, String xpath)
	{
		String expr = _extract_string(where, xpath);
		try {
			return _null_or_parse(expr);
		} catch (SyntaxError e) {
			log.error("failed to parse %s/%s: %s:",
				  where, xpath, expr, e);
			throw e;
		}
	}

	static Expression _null_or_parse(String expression)
		throws SyntaxError
	{
		if (expression==null)
			return null;
		try {
			return Processor.parse(expression);
		} catch (SyntaxError e) {
			log.error("failed to parse: '%s'", expression);
			throw e;
		}
	}

	static String _attribute(Element element, String attr)
	{
		String value = element.getAttribute(attr);
		if ("".equals(value))
			return null;
		else
			return value;
	}

	static Expression _parse_attribute(Element element, String attr)
	{
		String value = _attribute(element, attr);
		try {
			return _null_or_parse(value);
		} catch(SyntaxError e) {
			log.error("failed to parse expression '%s' from %s/%s",
				  value, element, attr);
			throw e;
		}
	}

	static Expression _identifier(Element element)
	{
		final String id = _attribute(element, "id");
		final String name = _attribute(element, "name");
		if (id != null && name != null)
			throw new SyntaxError("both id and name set!");
		if (id != null)
			return Expression.Const.make(id);
		else
			return _null_or_parse(name);
	}

	public static JavaClassGen generateFromFile(String name_hint,
						    XMLDocument xml,
						    String package_name,
						    Boolean comments)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		ASTNode codec = new CodecParser(name_hint, xml).codec;
		log.info("-- codec is parsed --");
		System.out.print(ASTDumper.dump(codec));

		final NameCheck check = new NameCheck();
		codec.accept(check, null);
		log.info("-- name checking is done --");

		final ASTTypeVisitor typer = new ASTTypeVisitor();
		codec.accept(typer, null);
		log.info("-- type checking is done --");

		final JavaClassGen gen =
			new JavaClassGen(typer.getTypeResolver(), package_name);
		if (comments != null)
			gen.setComments(comments);
		codec.accept(gen, null);
		log.info("-- java has been generated --");

		return gen;
	}

	public static JavaClassGen generateFromFile(File file, String package_name,
						    Boolean comments)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		final String hint = util.basename_noext(file);
		final XMLDocument xml = new XMLDocument(file);
		return generateFromFile(hint, xml, package_name, comments);
	}

	public static JavaClassGen generateFromFile(File file, String package_name)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		return generateFromFile(file, package_name, null);
	}

	public static JavaClassGen generateFromResource(String codec_name,
							String package_name,
							Boolean comments)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		String resource = format("specs/%s.xml", codec_name);
		InputStream stream = CodecParser.class.getResourceAsStream(resource);
		XMLDocument xml = new XMLDocument(stream);
		return generateFromFile(codec_name, xml, package_name, comments);
	}

	public static JavaClassGen generateFromResource(String codec_name,
							String package_name)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		return generateFromResource(codec_name, package_name, null);
	}

	static class Options {
		@Parameter(names="--debug",
			   description="Dump generated code on stdout")
		public boolean debug = false;

		@Parameter(names="--package",
			   description="Put generated classes in this package")
		public String package_name = "";

		@Parameter(names="--comments", arity=1,
			   description="Add comments about code origin")
		public boolean comments = JavaClassGen.comments;

		@Parameter(names="--help",
			   description="Show help and exit")
		public boolean help;

		@Parameter(names={"--output", "-o"},
			   description="Output dir")
		public String outputdir = null;

		@Parameter(names="--resource",
			   description="XML files should be retrieved as resources")
		public boolean resource = false;

		@Parameter(required=true,
			   description="XML codec file")
		public List<String> xml;
	}

	public static void main(String...args) throws Exception
	{
		BasicConfigurator.configure();

		Options opts = new Options();
		JCommander optparser = new JCommander(opts, args);
		if (opts.help) {
			optparser.usage();
			return;
		}

		assert opts.xml != null;
		for(String file: opts.xml) {
			final JavaClassGen gen;
			if (opts.resource)
				gen = generateFromResource(file,
							   opts.package_name,
							   opts.comments);
			else
				gen = generateFromFile(new File(file),
						       opts.package_name,
						       opts.comments);

			if (opts.debug)
				gen.write(System.out);

			if (opts.outputdir == null)
				continue;

			gen.write(new File(opts.outputdir));
			log.info("-- java has been written --");
		}
	}
}
