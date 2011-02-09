package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import static jsignalml.TestNumberOps.eval;
import static jsignalml.TestNumberOps.equal;

public class TestTypes {

	public static void assertIsType(String line, Class<? extends Type> theClass)
		throws Exception
	{
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
		assertEquals(new Type.Int(),  Type.getType("int"));
		assertEquals(new Type.Float(), Type.getType("float"));
		assertEquals(new Type.String(), Type.getType("str"));
		assertEquals(new Type.List(), Type.getType("list"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void get_types_from_string_invalid()
	{
		assertEquals(Type.getType("xxx"), Type.List.class);
	}

	@Test public void test_int_make_with_spaces()
	{
		assertEquals(new Type.Int(1), new Type.Int("1"));
		assertEquals(new Type.Int(1), new Type.Int("  1"));
		assertEquals(new Type.Int(1), new Type.Int("1  "));
		assertEquals(new Type.Int(0), new Type.Int("0       "));
		assertEquals(new Type.Int(2), new Type.Int("+2"));
		assertEquals(new Type.Int(3), new Type.Int("+ 3 "));
	}
}
