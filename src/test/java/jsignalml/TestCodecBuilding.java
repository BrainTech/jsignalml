package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

public class TestCodecBuilding {
	static final XMLDocument doc;
	static{
		InputStream stream = new StringBufferInputStream(
			"<signalml><file id='file' type='binary'>" +
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

	static void assert_arg_is(ASTNode.Positional arg, String name,
				  Class<? extends Type> type)
	{
		assertEquals(name, arg.id);
		assertEquals(type, arg.type.getClass());
	}

	@Test public void test_do_arg() throws Exception
	{
		Element element = doc.getElement("//arg");
		final ASTNode.Param p =
			new ASTNode.ExprParam(new ASTNode.Signalml("test"),
					      "expr", null, null);

		CodecParser.do_arg(p, element);

		assertEquals(1, p.args.size());

		ASTNode.Positional arg = p.args.get(0);
		assert_arg_is(arg, "one", TypeInt.class);
	}

	@Test public void test_do_two_args() throws Exception
	{
		final ASTNode.Param p =
			new ASTNode.ExprParam(new ASTNode.Signalml("test"),
					      "expr", null, null);

		Iterable<Element> nodes = doc.getNodes("//arg");
		for(Element element: nodes)
			CodecParser.do_arg(p, element);

		assertEquals(4, p.args.size());

		assert_arg_is(p.args.get(0), "one", TypeInt.class);
		assert_arg_is(p.args.get(1), "two", TypeFloat.class);
		assert_arg_is(p.args.get(2), "three", TypeString.class);
		assert_arg_is(p.args.get(3), "four", TypeList.class);
	}

	@Test public void test_do_assert() throws Exception
	{
		Element element = doc.getElement("//assert");

		throw new UnsupportedOperationException("yet, even no interface to test");
	}

	@Test public void test_do_param() throws Exception
	{
		Element element = doc.getElement("//file");

		CodecParser parser = new CodecParser(new File("dummy.xml"));
		ASTNode.Signalml root = new ASTNode.Signalml("root");

		parser.walk(root, element);

		ASTNode.Param p = (ASTNode.Param) root.find("file").find("only");

		assertEquals("only", p.id);
		assertThat( p.type, instanceOf(TypeInt.class) );
		assertEquals(4, p.args.size());
		assertThat( p, instanceOf(ASTNode.ExprParam.class) );
	}
}
