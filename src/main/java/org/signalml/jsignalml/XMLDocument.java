package org.signalml.jsignalml;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
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
    static final DocumentBuilder docbuilder;
    static final XPathFactory xfactory;
    static {
	final DocumentBuilderFactory builderFactory
	    = DocumentBuilderFactory.newInstance();
	builderFactory.setNamespaceAware(true);
	try{
	    docbuilder = builderFactory.newDocumentBuilder();
	}catch(javax.xml.parsers.ParserConfigurationException e){
	    throw new RuntimeException(e);
	}

	xfactory = XPathFactory.newInstance();
	log.info("builderFactory is %s", builderFactory.getClass());
    }

    final Document document;

    public XMLDocument(InputStream stream)
	throws java.io.IOException,
	       org.xml.sax.SAXException
    {
	this.document = docbuilder.parse(stream);
    }

    public XMLDocument(String filename)
	throws java.io.IOException,
	       org.xml.sax.SAXException
    {
	this(new FileInputStream(filename));
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
