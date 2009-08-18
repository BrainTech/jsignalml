package org.signalml.jsignalml;

import java.io.File;
import java.io.InputStream;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.signalml.jsignalml.Logger;

public class XMLDocument
{
    public class NoNodeError extends Exception {
	public final String xpath;
	public NoNodeError(String xpath){
	    this.xpath = xpath;
	}	    
    }

    static final Logger log = new Logger(XMLDocument.class);
    static final XPathFactory xfactory;
    static final DocumentBuilderFactory builderFactory;
    static {
	builderFactory = DocumentBuilderFactory.newInstance();
	builderFactory.setNamespaceAware(true);
	xfactory = XPathFactory.newInstance();
	log.info("builderFactory is %s", builderFactory.getClass());
    }

    final Document document;

    public XMLDocument(String filename)
	throws java.io.IOException,
	       org.xml.sax.SAXException,
	       javax.xml.parsers.ParserConfigurationException
    {
	final DocumentBuilder docbuilder
	    = builderFactory.newDocumentBuilder();
	// parse the XML as a W3C Document
	File file = new File(filename);
	this.document = docbuilder.parse(file);
    }
    public XMLDocument(InputStream stream)
	throws java.io.IOException,
	       org.xml.sax.SAXException,
	       javax.xml.parsers.ParserConfigurationException
    {
	final DocumentBuilder docbuilder
	    = builderFactory.newDocumentBuilder();
	// parse the XML as a W3C Document
	this.document = docbuilder.parse(stream);
    }

    public Node getNode(String xpath)
	throws javax.xml.xpath.XPathExpressionException,
	       NoNodeError
    {
	XPath getter = xfactory.newXPath();
	Object node = getter.evaluate(xpath, this.document,
				      XPathConstants.NODE);
	if(node == null)
	    throw new NoNodeError(xpath);
	return (Node) node;
    }

    public Iterable<Node> getNodes(String xpath)
	throws javax.xml.xpath.XPathExpressionException
    {
	XPath getter = xfactory.newXPath();
	Object nodes = getter.evaluate(xpath, this.document,
				       XPathConstants.NODESET);
	return new NodeIterable((NodeList) nodes);
    }
}
