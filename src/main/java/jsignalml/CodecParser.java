package jsignalml;

import static java.lang.String.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jsignalml.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Translate an XML DOM into an ASTNode tree.
 */
public class CodecParser {
	public static final Logger log = new Logger(CodecParser.class);

	final File filename;
	CodecParser(File filename)
	{
		this.filename = filename;
	}

	public static ASTNode makeCodec(File filename)
		throws java.io.IOException, org.xml.sax.SAXException
	{
		final InputStream stream = new FileInputStream(filename);
		return makeCodec(stream, filename);
	}

	public static ASTNode makeCodec(InputStream stream, File filename)
		throws java.io.IOException, org.xml.sax.SAXException
	{
		final XMLDocument doc = new XMLDocument(stream);
		final CodecParser parser = new CodecParser(filename);
		final ASTNode codec = parser.parse_signalml(doc);
		return codec;
	}

	public ASTNode dispatch(ASTNode parent, Element element)
	{
		log.debug("dispatch: %s id='%s'", element, element.getAttribute("id"));
		final String name = element.getNodeName();
		if (name.equals("signalml"))
			return this.do_signalml(parent, element);
		if (name.equals("channelset"))
			return this.do_channelset(parent, element);
		if (name.equals("channel"))
			return this.do_channel(parent, element);
		if (name.equals("param"))
			return this.do_param(parent, element);
		if (name.equals("arg"))
			return this.do_arg(parent, element);
		if (name.equals("assert"))
			return this.do_assert(parent, element);
		if (name.equals("for-each"))
			return this.do_forloop(parent, element);
		if (name.equals("if"))
			return this.do_conditional(parent, element, false);
		if (name.equals("else"))
			return this.do_else(parent, element);
		if (name.equals("else-if"))
			return this.do_conditional(parent, element, true);
		if (name.equals("file"))
			return this.do_file(parent, element);
		if (name.equals("expr") || name.equals("format") || name.equals("offset")
		    || name.equals("pattern") || name.equals("line") || name.equals("xpath")
		    || name.equals("group"))
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
			name = util.basename_noext(this.filename);

		return new ASTNode.Signalml(name);
	}

	public ASTNode.Param do_param(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("param");

		final Expression id = _identifier(element);
		final String type_ = _attribute(element, "type");
		final Type type = Type.getType(type_);
		final String fast_ = _attribute(element, "fast");
		final Expression fast = _null_or_parse(fast_);

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
				p = new ASTNode.ExprParam(parent, id, type, expr, fast);
		} else if (format != null && offset != null) {
			if (expr == null && pattern == null && line == null && xpath == null)
				p = new ASTNode.BinaryParam(parent, id, type, format, offset,
						fast);
		} else if (pattern != null && line != null && format != null && group != null) {
			if (offset == null && xpath == null && expr == null)
				p = new ASTNode.TextParam(parent, id, type, format, line, pattern, group);
		} else if (xpath != null) {
			throw new UnsupportedOperationException();
		} else {
			throw new SyntaxError("not enough attributes: " + element);
		}

		if (p == null)
			throw new SyntaxError("too many attributes: " + element);

		return p;
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
		final Expression mapping = _null_or_parse(_attribute(element, "mapping"));
		final Expression format = _null_or_parse(_attribute(element, "format"));
		final Expression length = _null_or_parse(_attribute(element, "length"));
		final Expression fast = _null_or_parse(_attribute(element, "fast"));

		final ASTNode.Channel node = new ASTNode.Channel(parent, id, mapping, format,
				length, fast);
		return node;
	}

	public ASTNode.FileHandle do_file(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("file");

		final Expression id = _identifier(element);
		final String type = _attribute(element, "type");
		final String filename_ = _attribute(element, "filename");
		final Expression filename =
		        filename_ == null ? null : Expression.Const.make(filename_);

		if (type == null)
			throw new SyntaxError("<file> needs a type attribute");

		final ASTNode.FileHandle handle = ASTNode.FileHandle.make(parent, id, filename, type);
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

		final String sequence = _attribute(element, "sequence");
		final Expression expr = _null_or_parse(sequence);
		return new ASTNode.ForLoop(parent, id, var, type, expr);
	}

	public ASTNode.Conditional do_conditional(ASTNode parent, Element element,
						  boolean elsebranch)
	{
		assert element.getNodeName().equals(elsebranch ? "else-if" : "if");

		final Expression id = _identifier(element);
		final String condition = _attribute(element, "test");

		final Expression expr = _null_or_parse(condition);
		return new ASTNode.Conditional(parent, id, expr);
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
		return _null_or_parse(expr);
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


	public static void main(String...args) throws Exception
	{
		BasicConfigurator.configure();

		final ASTNode codec = makeCodec(new File(args[0]));
		log.info("-- codec is parsed --");
		System.out.print(ASTDumper.dump(codec));

		final NameCheck check = new NameCheck();
		codec.accept(check, null);
		log.info("-- name checking is done --");

		final ASTTypeVisitor typer = new ASTTypeVisitor();
		codec.accept(typer, null);
		log.info("-- type checking is done --");

		final JavaClassGen gen = new JavaClassGen(typer.getTypeResolver());
		codec.accept(gen, null);
		log.info("-- java has been generated --");
		gen.write(System.out);

		if (args.length <= 1)
			return;

		final File outputdir = new File(args[1]);
		gen.write(outputdir);
		log.info("-- java has been written --");

		// String[] files = new String[args.length-1];
		// System.arraycopy(args, 1, files, 0, files.length);
	}
}
