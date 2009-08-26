package org.signalml.jsignalml.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.InputStream;
import org.w3c.dom.Node;

import org.signalml.jsignalml.XMLDocument;

public class TestXMLDocument {
    XMLDocument doc;
    @Before public void init()
	throws java.io.IOException,
	       org.xml.sax.SAXException
    {
	InputStream stream =
	    getClass().getResourceAsStream("/xml_doc_1.xml" );
	doc = new XMLDocument(stream);
    }

    @Test public void xpath_node_access() throws Exception
    {
	assertEquals(doc.getNode("/root").getNodeName(), "root");
	assertEquals(doc.getNode("/root/node1/subnode").getNodeName(),
		     "subnode");
	assertEquals(doc.getNode("//node2").getNodeName(),
		     "node2");
    }

    @Test(expected=XMLDocument.NoNodeError.class)
    public void xpath_invalid_node() throws Exception
    {
	doc.getNode("/no_such_node");
    }

    @Test public void xpath_iterate() throws Exception
    {
	int i = 0;
	for(Node node: doc.getNodes("//subnode"))
	    i++;
	assertEquals(i, 4);
    }
}
