package org.signalml.jsignalml;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.signalml.jsignalml.Machine.Param;
import org.signalml.jsignalml.sexpression.Processor;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.SyntaxError;

/**
 * Translate an XML DOM into a Codec.
 */
public class CodecCore {
    public static final Logger log = new Logger(Reader.class);

    public final Map<String,Param> params = new HashMap<String, Param>();

    void do_signalml(XMLDocument doc)
	throws SyntaxError
    {
	final Iterable<Element> iter = doc.getNodes_re("/signalml");
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

    void do_file(Element element)
	throws SyntaxError
    {
	assert element.getNodeName().equals("file");

	final String type = element.getAttribute("type");
	final String name_str = element.getAttribute("name");
	final Expression name = Processor.parse(name_str);
	final Machine.FileHandle handle = Machine.FileHandle.make(name, type);

	final Iterable<Element> iter = XMLDocument.subNodes_re(element, ".");
	for(Element node: iter) {
	    if(name.equals("assert"))
		this.do_assert(node);
	    else if(name.equals("param"))
		this.do_param(node, handle);
	    else if(name.equals("data"))
		this.do_data(node, handle);
	    else
		log.warn("unknown element: %s", name);
	}
    }

    void do_assert(Element element)
    {
	assert element.getNodeName().equals("file");

	log.warn("assert not implemented");
    }

    void do_param(Element element, Machine.FileHandle<?> handle)
    {
	assert element.getNodeName().equals("param");

	final String id = element.getAttribute("id");
	final String type = element.getAttribute("type");

	final List<Machine.Positional> args = util.newLinkedList();

	final Iterable<Element> iter = XMLDocument.subNodes_re(element, "./arg");
	for(Element node: iter) {
	    do_arg(node, args);
	}
    }

    static public void do_arg(Element element, List<? super Machine.Positional> list)
    {
	assert element.getNodeName().equals("arg");

	final String type = element.getAttribute("type");
	final String name = element.getAttribute("name");

	final Machine.Positional arg = Machine.Positional.make(name, type);
	list.add(arg);
    }

    void do_data(Element element, Machine.FileHandle<?> handle)
    {
	assert element.getNodeName().equals("data");

	log.warn("data not implemented");
    }
}
