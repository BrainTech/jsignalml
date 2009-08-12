package org.signalml.jsignalml;

import org.w3c.dom.Node;

public class Codec
{
    public Codec(String filename)
	throws java.io.IOException,
	       org.xml.sax.SAXException,
	       javax.xml.parsers.ParserConfigurationException,
	       javax.xml.xpath.XPathExpressionException
    {
	XMLDocument document = new XMLDocument(filename);

	Node url = document.getNode("/signalml/header/url");
	System.out.println(url);

	for(Node file:document.getNodes("/signalml/file")){
	    System.out.println(file);
	}
    }
}
 