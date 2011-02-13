package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;

public class TestProcessor {
	Type eval(Frame state, String line)
		throws Exception
	{
		Expression expr = Processor.parse(line);
		final ASTNode.ExprParam param =
			new ASTNode.ExprParam(null, "expr", null, expr);
		Type value = expr.accept(new EvalVisitor(state, null));
		return value;
	}

	void assertLeqR(String line, Type expected) throws Exception
	{
		Frame state = new Frame(null);
		Type result = eval(state, line);
		assertEquals(expected, result);
	}

	void assertLeqR(String line, BigInteger expected) throws Exception
	{
		Frame state = new Frame(null);
		BigInteger result = ((TypeInt)eval(state, line)).getValue();
		assertEquals(expected, result);
	}

	void assertLeqR(String line, int expected) throws Exception
	{ assertLeqR(line, new TypeInt(expected)); }
	void assertLeqR(String line, double expected) throws Exception
	{ assertLeqR(line, new TypeFloat(expected)); }
	void assertLeqR(String line, String expected) throws Exception
	{ assertLeqR(line, new TypeString(expected)); }

	@Test public void simpleAdd() throws Exception
	{
		assertLeqR("1", 1);
	}

	@Test public void pow_overflow() throws Exception
	{
		assertLeqR("2**1024", BigInteger.valueOf(2).pow(1024));
	}
}
