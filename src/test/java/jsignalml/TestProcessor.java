package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;
import static jsignalml.TestNumberOps.eval;

public class TestProcessor {
	void assertLeqR(String line, Type expected) throws Exception
	{
		Type result = eval(line);
		assertEquals(expected, result);
	}

	void assertLeqR(String line, BigInteger expected) throws Exception
	{
		BigInteger result = ((TypeInt)eval(line)).getValue();
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
