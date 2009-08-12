package org.signalml.jsignalml;

import java.io.File;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLDocument
{
    final Document document;
    final XPathFactory xfactory;

    public XMLDocument(String filename)
	throws java.io.IOException,
	       org.xml.sax.SAXException,
	       javax.xml.parsers.ParserConfigurationException
    {
	// parse the XML as a W3C Document
	DocumentBuilderFactory builderFactory = 
	    DocumentBuilderFactory.newInstance();
	builderFactory.setNamespaceAware(true);
	DocumentBuilder builder =
	    builderFactory.newDocumentBuilder();
	File file = new File(filename);
	this.document = builder.parse(file);
	this.xfactory = XPathFactory.newInstance(); 
    }

    Node getNode(String xpath)
	throws javax.xml.xpath.XPathExpressionException
    {
	XPath getter = this.xfactory.newXPath();
	Object node = getter.evaluate(xpath, this.document,
				      XPathConstants.NODE);
	return (Node) node;
    }

    Iterable<Node> getNodes(String xpath)
	throws javax.xml.xpath.XPathExpressionException
    {
	XPath getter = this.xfactory.newXPath();
	Object nodes = getter.evaluate(xpath, this.document,
				       XPathConstants.NODESET);
	return new NodeIterable((NodeList) nodes);
    }
}
