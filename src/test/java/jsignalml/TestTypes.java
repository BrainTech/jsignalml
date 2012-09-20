package jsignalml;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import static jsignalml.TestNumberOps.eval;

public class TestTypes {

	public static void assertIsType(String line, Class<? extends Type> theClass)
		throws Exception
	{
		helpers.assertInstanceOf(eval(line), theClass);
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

	@Test(expectedExceptions=IllegalArgumentException.class)
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

	@Test public void test_boolean_make_from_string() {
		assertEquals(new TypeBool(true), new TypeBool("TRUE"));
		assertEquals(new TypeBool(true), new TypeBool("TrUe"));
		assertEquals(new TypeBool(true), new TypeBool("true"));
		assertEquals(new TypeBool(true), new TypeBool("FALSE"));
		assertEquals(new TypeBool(true), new TypeBool("False"));
		assertEquals(new TypeBool(true), new TypeBool("false"));
		assertEquals(new TypeBool(true), new TypeBool("x"));
		assertEquals(new TypeBool(true), new TypeBool("0"));
		assertEquals(new TypeBool(true), new TypeBool("1"));
	}

	@Test public void test_boolean_make_from_typestring() {
		assertEquals(new TypeBool(true), new TypeBool(new TypeString("TRUE")));
		assertEquals(new TypeBool(true), new TypeBool(new TypeString("TrUe")));
		assertEquals(new TypeBool(true), new TypeBool(new TypeString("true")));
		assertEquals(new TypeBool(true), new TypeBool(new TypeString("FALSE")));
		assertEquals(new TypeBool(true), new TypeBool(new TypeString("False")));
		assertEquals(new TypeBool(true), new TypeBool(new TypeString("false")));
		assertEquals(new TypeBool(true), new TypeBool(new TypeString("x")));
		assertEquals(new TypeBool(true), new TypeBool(new TypeString("0")));
		assertEquals(new TypeBool(true), new TypeBool(new TypeString("1")));
	}

	@Test public void test_boolean_parse() {
		assertEquals(new TypeBool(true), TypeBool.I.parse("True"));
		assertEquals(new TypeBool(false), TypeBool.I.parse("False"));
		// Add checks that everything else throws
	}

	@Test public void test_boolean_make_from_float() {
		assertEquals(new TypeBool(true), new TypeBool(new TypeFloat(1000)));
		assertEquals(new TypeBool(true), new TypeBool(new TypeFloat(1000.01)));
		assertEquals(new TypeBool(true), new TypeBool(new TypeFloat(-1.4)));
		assertEquals(new TypeBool(false), new TypeBool(new TypeFloat(0)));
		assertEquals(new TypeBool(false), new TypeBool(0.0F));
		assertEquals(new TypeBool(true), new TypeBool(1.0F));
	}

	@Test public void test_boolean_make_from_int() {
		assertEquals(new TypeBool(true), new TypeBool(new TypeInt(1000)));
		assertEquals(new TypeBool(true), new TypeBool(new TypeInt(-1600)));
		assertEquals(new TypeBool(true), new TypeBool(new TypeInt(1)));
		assertEquals(new TypeBool(false), new TypeBool(new TypeInt(0)));
		assertEquals(new TypeBool(true), new TypeBool(1L));
		assertEquals(new TypeBool(false), new TypeBool(0L));
	}

	@Test public void test_edf_nr_of_channels() {
                TypeString input = new TypeString("17  ");
                TypeInt value = TypeInt.I.make(input);
		assertEquals(value.safeIntValue(), 17);
	}

	@Test public void test_edf_channel_label() {
                TypeString input = new TypeString(" Sa02");
		TypeString output = Builtins.trim().call(input);
		assertEquals(output.getValue(), "Sa02");
	}
}
