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
		assertIsType("1", TypeInt.class);
		assertIsType("0", TypeInt.class);
		assertIsType("-1", TypeInt.class);
		assertIsType("0000000", TypeInt.class);
	}

	@Test public void eval_atom_is_float_1() throws Exception
	{
		assertIsType("0.", TypeFloat.class);
	}

	@Test public void eval_atom_is_float_2() throws Exception
	{
		assertIsType("1.", TypeFloat.class);
	}

	@Test public void eval_atom_is_float_3() throws Exception
	{
		assertIsType("-1.", TypeFloat.class);
	}

	@Test public void eval_atom_is_string_1() throws Exception
	{
		assertIsType("'aaa'", TypeString.class);
	}

	@Test public void eval_atom_is_string_2() throws Exception
	{
		assertIsType("\"aaa\"", TypeString.class);
	}

	@Test public void get_types_from_string()
	{
		assertEquals(new TypeInt(),  Type.getType("int"));
		assertEquals(new TypeFloat(), Type.getType("float"));
		assertEquals(new TypeString(), Type.getType("str"));
		assertEquals(new TypeList(), Type.getType("list"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void get_types_from_string_invalid()
	{
		assertEquals(Type.getType("xxx"), TypeList.class);
	}

	@Test public void test_int_make_with_spaces()
	{
		assertEquals(new TypeInt(1), new TypeInt("1"));
		assertEquals(new TypeInt(1), new TypeInt("  1"));
		assertEquals(new TypeInt(1), new TypeInt("1  "));
		assertEquals(new TypeInt(0), new TypeInt("0       "));
		assertEquals(new TypeInt(2), new TypeInt("+2"));
		assertEquals(new TypeInt(3), new TypeInt("+ 3 "));
	}
}
