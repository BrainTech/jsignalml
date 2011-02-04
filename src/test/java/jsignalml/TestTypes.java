package jsignalml;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class TestTypes {
	Type eval(String line) throws Exception
	{
		Frame state = new Frame(null);
		Expression expr = Processor.parse(line);
		final ASTNode.ExprParam param =
			new ASTNode.ExprParam(null, "expr", null, expr);
		Type val = expr.accept(new EvalVisitor(state, param));
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
		assertEquals(Type.getType("int"), new Type.Int());
		assertEquals(Type.getType("float"), new Type.Float());
		assertEquals(Type.getType("str"), new Type.String());
		assertEquals(Type.getType("list"), new Type.List());
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
