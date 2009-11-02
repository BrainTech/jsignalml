package jsignalml;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * Translate an XML DOM into a Codec.
 */
public class CodecCore implements CodecyThing {
    public static final Logger log = new Logger(CodecCore.class);

    final Map<String,Machine.Param> params = util.newHashMap();
    final List<Machine.FileHandle<?>> file_handles = util.newLinkedList();

    public void addParam(Machine.Param p)
    {
	assert p != null;

	if(this.params.get(p.id) != null)
	    throw new IllegalArgumentException("duplicate id");

	this.params.put(p.id, p);
    }

    public Machine.Param getParam(String id)
    {
	Machine.Param p = this.params.get(id);
	if(p!=null)
	    return p;
	throw new ExpressionFault.NameError(id);
    }

    public void parse_signalml(XMLDocument doc)
	throws SyntaxError
    {
	Element element;
	try{
	    element = doc.getElement_re("/signalml");
	}catch(XMLDocument.NodeError e){
	    throw new IllegalArgumentException("no /signalml node");
	}
	this.do_signalml(element);
    }

    public void do_signalml(Element element)
	throws SyntaxError
    {
	assert element.getNodeName().equals("signalml");

	final Iterable<Element> iter = XMLDocument.subNodes_re(element, "*");
	for(Element node: iter){
	    final String name = node.getNodeName();
	    if(name.equals("file"))
		this.do_file(node);
	    else if(name.equals("assert"))
		this.do_assert(node);
	    else
		log.warn("unknown element: %s", name);
	}
    }

    public void do_file(Element element)
	throws SyntaxError
    {
	assert element.getNodeName().equals("file");

	final String type = _attribute(element, "type");
	final String name_ = _attribute(element, "name");
	final Expression name =
	    name_ == null ? null : Expression.Const.make(name_);

	final Machine.FileHandle handle = Machine.FileHandle.make(name, type);

	final Iterable<Element> iter = XMLDocument.subNodes_re(element, "*");
	for(Element node: iter) {
	    if(node.getNodeName().equals("assert"))
		this.do_assert(node);
	    else if(node.getNodeName().equals("param"))
		this.do_param(node, handle);
	    else if(node.getNodeName().equals("data"))
		this.do_data(node, handle);
	    else
		log.warn("unknown element: %s", node);
	}

	this.file_handles.add(handle);
    }

    public void do_assert(Element element)
    {
	assert element.getNodeName().equals("file");

	log.warn("assert not implemented");
    }

    public void do_param(Element element, Machine.FileHandle<?> handle)
	throws SyntaxError
    {
	assert element.getNodeName().equals("param");

	final String id = element.getAttribute("id");
	final String type_ = _attribute(element, "type");
	final Class<? extends Type> type =
	    Type.getType(type_==null? "auto" : type_);

	final List<Machine.Positional> args_ = util.newLinkedList();

	final Iterable<Element> iter = XMLDocument.subNodes_re(element, "./arg");
	for(Element node: iter)
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

	if(expr != null){
	    if(format == null || offset == null || pattern == null ||
	       line == null || xpath == null)
		p = new Machine.ExprParam(id, type, args, expr);
	} else if(format != null && offset != null){
	    if(expr == null && pattern == null && line == null && xpath == null){
		Machine.FileHandle<? extends FileType.BinaryFile> bh
		    = util.cast(handle);
		p = new Machine.BinaryParam(id, type, args, bh, format, offset);
	    }
	} else if(pattern != null){
	    throw new UnsupportedOperationException();
	} else if(xpath != null){
	    throw new UnsupportedOperationException();
	} else {
	    throw new IllegalArgumentException("not enough attributes");
	}

	if(p == null){
	    throw new IllegalArgumentException("too many attributes");
	}

	this.params.put(id, p);
    }

    static public void do_arg(Element element, List<? super Machine.Positional> list)
    {
	assert element.getNodeName().equals("arg");

	final String type = element.getAttribute("type");
	final String name = element.getAttribute("name");

	final Machine.Positional arg = Machine.Positional.make(name, type);
	list.add(arg);
    }

    public static Expression do_expr(Element element)
	throws SyntaxError
    {
	assert element.getNodeName().equals("expr");

	String code = element.getTextContent();
	Expression expr = Processor.parse(code);
	return expr;
    }

    public void do_data(Element element, Machine.FileHandle<?> handle)
    {
	assert element.getNodeName().equals("data");

	log.warn("data not implemented");
	String mapping = element.getAttribute("mapping");
	String format = element.getAttribute("format");

	new Machine.DataHandle(handle, mapping, format);
    }

    static String _extract_string(Node where, String xpath)
    {
	Node node = XMLDocument._subNode_re(where, xpath);
	if(node == null)
	    return null;
	else
	    return node.getTextContent();
    }

    /**
     * Get the expression pointed to by xpath (starting from where).
     * Returns null if not found or and Expression.
     */
    static Expression _extract(Node where, String xpath)
	throws SyntaxError
    {
	String expr = _extract_string(where, xpath);
	if(expr==null)
	    return null;
	try{
	    return Processor.parse(expr);
	}catch(SyntaxError e){
	    log.error("failed to parse: '%s'", expr);
	    throw e;
	}
    }

    static String _attribute(Element element, String attr)
    {
	String value = element.getAttribute(attr);
	if("".equals(value))
	    return null;
	else
	    return value;
    }
}
