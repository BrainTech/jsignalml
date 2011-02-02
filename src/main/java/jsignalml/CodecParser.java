package jsignalml;

import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.apache.log4j.BasicConfigurator;

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
		throws java.io.IOException, org.xml.sax.SAXException, SyntaxError
	{
		final InputStream stream = new FileInputStream(filename);
		final XMLDocument doc = new XMLDocument(stream);
		final CodecParser parser = new CodecParser(filename);
		final ASTNode codec = parser.parse_signalml(doc);
		return codec;
	}

	public ASTNode dispatch(ASTNode parent, Element element) throws SyntaxError
	{
		final String name = element.getNodeName();
		if (name.equals("signalml"))
			return this.do_signalml(parent, element);
		if (name.equals("param"))
			return this.do_param(parent, element);
		if (name.equals("assert"))
			return this.do_assert(parent, element);
		if (name.equals("file"))
			return this.do_file(parent, element);
		if (name.equals("data"))
			return this.do_data(parent, element);
		if (name.equals("expr") || name.equals("format") || name.equals("offset")
		    || name.equals("pattern") || name.equals("line") || name.equals("xpath"))
			return null; /* handled directly */
		log.warn("unknown element: %s", element);
		return null;
	}

	public ASTNode walk(ASTNode parent, Element element) throws SyntaxError
	{
		final ASTNode node = this.dispatch(parent, element);

		if (node != null) {
			final Iterable<Element> iter = XMLDocument.subNodes_re(element, "*");
			for (Element child: iter)
				this.walk(node, child);
		}

		return node;
	}

	public ASTNode parse_signalml(XMLDocument doc) throws SyntaxError
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

	public ASTNode.Signalml do_signalml(ASTNode parent, Element element) throws SyntaxError
	{
		assert element.getNodeName().equals("signalml");

		String name = _extract_string(element, "header/name");
		if (name == null)
			name = util.basename_noext(this.filename);

		return new ASTNode.Signalml(name);
	}

	public ASTNode.Param do_param(ASTNode parent, Element element) throws SyntaxError
	{
		assert element.getNodeName().equals("param");

		final String id = element.getAttribute("id");
		final String type_ = _attribute(element, "type");
		final Type type = Type.getType(type_);

		final Expression expr    = _extract(element, "expr");
		final Expression format  = _extract(element, "format");
		final Expression offset  = _extract(element, "offset");
		final Expression pattern = _extract(element, "pattern");
		final Expression line    = _extract(element, "line");
		final Expression xpath   = _extract(element, "xpath");

		/* The code below is structed like it is to keep it simple and
		   reduce repetitions. */

		ASTNode.Param p = null;

		if (expr != null) {
			if (format == null || offset == null || pattern == null ||
			                line == null || xpath == null)
				p = new ASTNode.ExprParam(parent, id, type, null, expr);
		} else if (format != null && offset != null) {
			if (expr == null && pattern == null && line == null && xpath == null) {
				p = new ASTNode.BinaryParam(parent, id, type, format, offset);
				if (((ASTNode.BinaryParam)p).handle == null)
					throw new SyntaxError("binary <param> must live inside <file>");

			}
		} else if (pattern != null) {
			throw new UnsupportedOperationException();
		} else if (xpath != null) {
			throw new UnsupportedOperationException();
		} else {
			throw new IllegalArgumentException("not enough attributes");
		}

		if (p == null)
			throw new IllegalArgumentException("too many attributes");

		return p;
	}

        static public ASTNode.Positional do_arg(ASTNode parent, Element element)
		throws SyntaxError
        {
                assert element.getNodeName().equals("arg");

                final String name = element.getAttribute("name");
                final String type_ = element.getAttribute("type");
		final Type type = Type.getType(type_);

                final ASTNode.Positional arg = new ASTNode.Positional(parent, name, type);

		if (!(parent instanceof ASTNode.Param))
			throw new SyntaxError("<arg> can only be a direct child of <param>");
		final ASTNode.Param param = (ASTNode.Param)parent;
                param.args.add(arg);
		return arg;
        }

	public ASTNode.Assert do_assert(ASTNode parent, Element element) throws SyntaxError
	{
		assert element.getNodeName().equals("assert");

		final String id = element.getAttribute("id");
		final Expression expr = _extract(element, "expr");
		if (expr == null)
			throw new SyntaxError("<assert> needs an <expr> child");

		return new ASTNode.Assert(parent, id, expr);
	}

	public ASTNode.FileHandle do_file(ASTNode parent, Element element) throws SyntaxError
	{
		assert element.getNodeName().equals("file");

		final String type = _attribute(element, "type");
		final String name_ = _attribute(element, "name");
		final Expression name =
		        name_ == null ? null : Expression.Const.make(name_);

		if (type == null)
			throw new SyntaxError("<file> needs a type attribute");

		final ASTNode.FileHandle handle = ASTNode.FileHandle.make(parent, name, type);
		return handle;
	}

	public ASTNode.DataHandle do_data(ASTNode parent, Element element)
	{
		assert element.getNodeName().equals("data");

		final String name = _attribute(element, "name");
		final String mapping = element.getAttribute("mapping");
		final String format = element.getAttribute("format");

		log.warn("data not implemented");

		return new ASTNode.DataHandle(parent, name, mapping, format);
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
		throws SyntaxError
	{
		String expr = _extract_string(where, xpath);
		if (expr==null)
			return null;
		try {
			return Processor.parse(expr);
		} catch (SyntaxError e) {
			log.error("failed to parse: '%s'", expr);
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

	public static void main(String...args) throws Exception
	{
		BasicConfigurator.configure();

		ASTNode codec = makeCodec(new File(args[0]));
		System.out.print(ASTDumper.dump(codec));

		if (args.length <= 1)
			return;

		String[] files = new String[args.length-1];
		System.arraycopy(args, 1, files, 0, files.length);
	}
}
