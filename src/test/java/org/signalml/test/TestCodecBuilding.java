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
import org.signalml.jsignalml.Machine.Param;
import org.signalml.jsignalml.Machine.FileHandle;
import org.signalml.jsignalml.FileType;
import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.util;

public class TestCodecBuilding {
    static final XMLDocument doc;
    static{
	InputStream stream = new StringBufferInputStream(
	     "<signalml><file type='binary'>" +
	     "  <assert id='an_assert'>" +
	     "    <expr>1 + 1</expr>" +
	     "  </assert>" +
	     "  <param id='only' type='int'>" +
	     "    <arg name='one' type='int' />" +
	     "    <arg name='two' type='float' />" +
	     "    <arg name='three' type='str' />" +
	     "    <arg name='four' type='list' />" +
	     "    <expr>2 + 2</expr>" +
	     "  </param>" +
	     "</file></signalml>");
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

    @Test public void test_do_two_args() throws Exception{
	List<Machine.Positional> list = util.newLinkedList();

	Iterable<Element> nodes = doc.getNodes("//arg");
	for(Element element: nodes)
	    CodecCore.do_arg(element, list);

	assertEquals(list.size(), 4);

	assert_arg_is(list.get(0), "one", Type.Int.class);
	assert_arg_is(list.get(1), "two", Type.Float.class);
	assert_arg_is(list.get(2), "three", Type.String.class);
	assert_arg_is(list.get(3), "four", Type.List.class);
    }

    @Test public void test_do_assert() throws Exception {
	Element element = doc.getElement("//assert");

	throw new UnsupportedOperationException("yet, even no interface to test");
    }

    @Test public void test_do_param() throws Exception {
	Element element = doc.getElement("//param");

	CodecCore core = new CodecCore();
	Machine.FileHandle<FileType.BinaryFile> handle = FileHandle.make(null);

	core.do_param(element, handle);

	Param p = core.params.get("only");
	assertEquals( p.id, "only");
	assertEquals( p.type, Type.Int.class);
	assertEquals( p.args.length, 4);
	assertThat( p, instanceOf(Machine.ExprParam.class) );
    }

    @Test public void test_do_expr() throws Exception {
	Element element = doc.getElement("//expr");

	Expression expr = CodecCore.do_expr(element);
	assertThat( expr, instanceOf(Expression.BinaryOp.class) );
    }
}
