package jsignalml;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.apache.log4j.BasicConfigurator;

import com.sun.codemodel.JDefinedClass;

// XXX, it's easier to import for now
import static jsignalml.CodecCore._extract_string;
import static jsignalml.CodecCore._extract;
import static jsignalml.CodecCore._attribute;

/**
 * Translate an XML DOM into a Codec in Java.
 */
public class CodecMaker {
	public static final Logger log = new Logger(CodecCore.class);
	final JavaGen generator;

	public static CodecMaker makeCodec(File filename)
		throws java.io.IOException, org.xml.sax.SAXException, SyntaxError
	{
		InputStream stream = new FileInputStream(filename);
		XMLDocument doc = new XMLDocument(stream);
		CodecMaker maker = new CodecMaker(filename);
		maker.parse_signalml(doc);
		return maker;
	}

	public CodecMaker(File filename)
	{
		this.generator = new JavaGen(filename.toString());
		// TODO: use inormation from header for name
	}

	public void parse_signalml(XMLDocument doc)
		throws SyntaxError
	{
		Element element;
		try {
			element = doc.getElement_re("/signalml");
		} catch (XMLDocument.NodeError e) {
			throw new IllegalArgumentException("no /signalml node");
		}
		this.do_signalml(this.generator.root, element);
	}

	public void do_signalml(Context context, Element element)
		throws SyntaxError
	{
		assert element.getNodeName().equals("signalml");

		final Iterable<Element> iter = XMLDocument.subNodes_re(element, "*");
		for (Element node: iter) {
			final String name = node.getNodeName();
			if (name.equals("file"))
				this.do_file(context, node);
			else if (name.equals("assert"))
				this.do_assert(context, node);
			else if (name.equals("param"))
				this.do_param(context, node);
			else
				log.warn("unknown element: %s", name);
		}
	}

	public void do_file(Context parent, Element element)
		throws SyntaxError
	{
		assert element.getNodeName().equals("file");

		final String type = _attribute(element, "type");
		final String name_ = _attribute(element, "name");
		final Expression name =
		        name_ == null ? null : Expression.Const.make(name_);

		JDefinedClass file_class = this.generator.root.klass._class("file1");
		Context context = new Context(file_class, parent, "file1");
		this.generator.openMethod(context);
		this.generator.fileConstructor(context);

		final Iterable<Element> iter = XMLDocument.subNodes_re(element, "*");
		for (Element node: iter) {
			if (node.getNodeName().equals("assert"))
				this.do_assert(context, node);
			else if (node.getNodeName().equals("param"))
				this.do_param(context, node);
			else if (node.getNodeName().equals("data"))
				this.do_data(context, node);
			else
				log.warn("unknown element: %s", node);
		}
	}

	public void do_param(Context parent, Element element)
		throws SyntaxError
	{
		assert element.getNodeName().equals("param");

		final String id = element.getAttribute("id");
		final String type_ = _attribute(element, "type");
		final Class<? extends Type> type =
		        Type.getType(type_==null? "auto" : type_);



		final Iterable<Element> iter = XMLDocument.subNodes_re(element, "./arg");
		for (Element node: iter)
			do_arg(node, args_);

		final Machine.Positional args[] = args_.toArray(new Machine.Positional[0]);

		final Expression expr    = _extract(element, "expr");
		final Expression format  = _extract(element, "format");
		final Expression offset  = _extract(element, "offset");
		final Expression pattern = _extract(element, "pattern");
		final Expression line    = _extract(element, "line");
		final Expression xpath   = _extract(element, "xpath");

		/* The code below is structed like it is to keep it simple and
		   reduce repetitions. */

		Machine.Param p = null;

		if (expr != null) {
			if (format == null || offset == null || pattern == null ||
			                line == null || xpath == null)
				p = new Machine.ExprParam(id, type, args, expr);
		} else if (format != null && offset != null) {
			if (expr == null && pattern == null && line == null && xpath == null) {
				Machine.FileHandle<? extends FileType.BinaryFile> bh
				= util.cast(handle);
				p = new Machine.BinaryParam(id, type, args, bh, format, offset);
			}
		} else if (pattern != null) {
			throw new UnsupportedOperationException();
		} else if (xpath != null) {
			throw new UnsupportedOperationException();
		} else {
			throw new IllegalArgumentException("not enough attributes");
		}

		if (p == null) {
			throw new IllegalArgumentException("too many attributes");
		}

		this.params.put(id, p);
	}

	public void do_assert(Context context, Element element)
	{
		assert element.getNodeName().equals("assert");

		log.warn("assert not implemented");
	}

	public void do_data(Context context, Element element)
	{
		assert element.getNodeName().equals("data");

		log.warn("data not implemented");
	}
}