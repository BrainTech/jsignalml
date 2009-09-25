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
	assertEquals("root", doc.getNode("/root").getNodeName());
	assertEquals("subnode",
		     doc.getNode("/root/node1/subnode").getNodeName());
	assertEquals("node2", doc.getNode("//node2").getNodeName());
    }

    @Test public void xpath_element_access() throws Exception
    {
	assertEquals("root", doc.getElement("/root").getNodeName());
	assertEquals("subnode",
		     doc.getElement("/root/node1/subnode").getNodeName());
	assertEquals("node2", doc.getElement("//node2").getNodeName());
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
	assertEquals(4, i);
    }

    @Test public void xpath_from_non_root() throws Exception
    {
	assertEquals("node2",
	     doc.subNode(doc.getNode("/root/node2"), ".").getNodeName());
    }

    @Test public void xpath_from_non_root2() throws Exception
    {
	Node supernode = doc.getNode("/root/node1");
	int i = 0;
	for(Node node: doc.subNodes(supernode, "*")){
	    i++;
	    assertEquals("subnode", node.getNodeName());
	}
	assertEquals(4, i);
    }

    @Test public void xpath_extract_text_nonnull() throws Exception
    {
	assertEquals("example text",
		     doc.getNode("/root/node2").getTextContent());
    }
}
