package jsignalml;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class TestTypes {
	Type eval(String line) throws Exception
	{
		CallHelper state = new Processor.State();
		Expression expr = Processor.parse(line);
		Type val = expr.eval(state);
		return val;
	}

	void equal(String line, int expected) throws Exception
	{
		assertTrue(eval(line).equals(new Type.Int(expected)));
	}
	void equal(String line, double expected) throws Exception
	{
		assertTrue(eval(line).equals(new Type.Float(expected)));
	}
	void equal(String line, String expected) throws Exception
	{
		assertTrue(eval(line).equals(new Type.String(expected)));
	}

	void verifyIsTrue(String line, boolean expected) throws Exception
	{
		assertEquals(eval(line).isTrue(), expected);
	}

	void assertIsType(String line, Class<? extends Type> theClass) throws Exception
	{
		//	assertTrue(eval(line) instanceof Type.Int);
		assertThat(eval(line), instanceOf(theClass));
	}

	@Test public void eval_atom_is_int() throws Exception
	{
		assertIsType("1", Type.Int.class);
		assertIsType("0", Type.Int.class);
		assertIsType("-1", Type.Int.class);
		assertIsType("0000000", Type.Int.class);
	}

	@Test public void eval_atom_is_float_1() throws Exception
	{
		assertIsType("0.", Type.Float.class);
	}

	@Test public void eval_atom_is_float_2() throws Exception
	{
		assertIsType("1.", Type.Float.class);
	}

	@Test public void eval_atom_is_float_3() throws Exception
	{
		assertIsType("-1.", Type.Float.class);
	}

	@Test public void eval_atom_is_string_1() throws Exception
	{
		assertIsType("'aaa'", Type.String.class);
	}

	@Test public void eval_atom_is_string_2() throws Exception
	{
		assertIsType("\"aaa\"", Type.String.class);
	}

	@Test public void get_types_from_string()
	{
		assertEquals(Type.getType("int"), Type.Int.class);
		assertEquals(Type.getType("float"), Type.Float.class);
		assertEquals(Type.getType("str"), Type.String.class);
		assertEquals(Type.getType("list"), Type.List.class);
	}

	@Test(expected=IllegalArgumentException.class)
	public void get_types_from_string_invalid()
	{
		assertEquals(Type.getType("xxx"), Type.List.class);
	}

	@Test public void test_int_make_with_spaces()
	{
		assertEquals(new Type.Int("1"), new Type.Int(1));
		assertEquals(new Type.Int("  1"), new Type.Int(1));
		assertEquals(new Type.Int("1  "), new Type.Int(1));
		assertEquals(new Type.Int("0       "), new Type.Int(0));
		assertEquals(new Type.Int("+2"), new Type.Int(2));
		assertEquals(new Type.Int("+ 3 "), new Type.Int(3));
	}
}
