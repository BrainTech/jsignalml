package org.signalml.jsignalml.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import java.util.List;
import java.io.InputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import org.signalml.jsignalml.CodecCore;
import org.signalml.jsignalml.XMLDocument;
import org.signalml.jsignalml.Machine;
import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.util;

public class TestCodecBuilding {
    static final XMLDocument doc;
    static{
	InputStream stream = new StringBufferInputStream(
	     "<signalml><file type='binary'><param id='only'>" +
	     "<arg name='one' type='int' />" +
	     "<expr>one + 1</expr>" +
	     "</param></file></signalml>");
	try{
	    doc = new XMLDocument(stream);
	}catch(IOException e){
	    throw new RuntimeException(e);
	}catch(SAXException e){
	    throw new RuntimeException(e);
	}
    }

    static void assert_arg_is(Machine.Positional arg, String name,
				  Class<? extends Type> type){
	assertEquals(arg.name, name);
	assertEquals(arg.type, type);
    }

    @Test public void test_do_arg() throws Exception{
	Element element = doc.getElement("//arg");
	List<Machine.Positional> list = util.newLinkedList();

	CodecCore.do_arg(element, list);

	assertEquals(list.size(), 1);

	Machine.Positional arg = list.get(0);
	assert_arg_is(arg, "one", Type.Int.class);
    }
}
