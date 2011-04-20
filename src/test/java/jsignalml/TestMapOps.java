
package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;

import static jsignalml.TestNumberOps.eval;
import static jsignalml.TestNumberOps.equal;
import static jsignalml.TestNumberOps.verifyIsTrue;

public class TestMapOps {
	static void mapEqual(String line, Object...elements) throws Exception
	{
		final TypeMap expected = TypeMap.make(elements);
		assertEquals(expected, eval(line));
	}

	@Test public void simple_mapping() throws Exception
	{
		equal("{1:2}[1]", 2);
	}

	@Test public void mapping_mapping() throws Exception
	{
		assertEquals(eval("{{}:{}}[{}]"), new TypeMap());
	}

	@Test public void mapping_veracity() throws Exception
	{
		verifyIsTrue("{}", false);
		verifyIsTrue("{{}:{}}", true);
		verifyIsTrue("{1:2}", true);
		verifyIsTrue("{'a':'b', []:[]}", true);
	}

	@Test(expected=ExpressionFault.KeyError.class)
	public void eval_keyerror() throws Exception
	{
		eval("{1:2}[0]");
	}

	@Test(expected=ExpressionFault.KeyError.class)
	public void eval_keyerror_string() throws Exception
	{
		eval("{1:2}['a']");
	}

	@Test(expected=ExpressionFault.KeyError.class)
	public void eval_keyerror_list() throws Exception
	{
		eval("{1:2}[[]]");
	}

	@Test(expected=ExpressionFault.KeyError.class)
	public void eval_keyerror_map() throws Exception
	{
		eval("{1:2}[{}]");
	}

	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_keyerror_ops_add() throws Exception
	{
		eval("{1:2} + {}");
		eval("{1:2} + {2:3}");
	}

	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_keyerror_ops_mul() throws Exception
	{
		eval("{1:2} * {}");
	}

	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_keyerror_ops_div() throws Exception
	{
		eval("{1:2} / {}");
	}

	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_keyerror_ops_bin_and() throws Exception
	{
		eval("{1:2} & {}");
	}

	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_keyerror_ops_unary_pos() throws Exception
	{
		eval("+{1:2}");
	}
	@Test public void maps_bool() throws Exception
	{
		equal("bool({})", 0);
		equal("bool({1:2})", 1);
	}
	@Test public void maps_len() throws Exception
	{
		equal("len({})", 0);
		equal("len({1:2,3:4})", 2);
	}
	@Test public void or_map() throws Exception
	{
		mapEqual("{1:2} or {3:4}", 1, 2);
		mapEqual("{} or {3:4}", 3, 4);
		mapEqual("{3:4} or {}", 3, 4);
		mapEqual("{}  or {}");
	}
	@Test public void and_map() throws Exception
	{
		mapEqual("{1:2} and {3:4}", 3, 4);
		mapEqual("{} and {3:4}");
		mapEqual("{3:4} and {}");
		mapEqual("{} and {}");
	}
	@Test public void equal_map() throws Exception
	{
		equal("{1:2} == {2:3}", 0);
		equal("{1:2} == {1:3}", 0);		
		equal("{1:2} == {2:2}", 0);		
		equal("{1:2} == {1:2}", 1);
		equal("{1:2} == {}", 0);
		equal("{} == {}", 1);
	}
	@Test public void not_equal_map() throws Exception
	{
		equal("{1:2} != {2:3}", 1);
		equal("{1:2} != {1:3}", 1);		
		equal("{1:2} != {2:2}", 1);		
		equal("{1:2} != {1:2}", 0);
		equal("{1:2} != {}", 1);
		equal("{} != {}", 0);
	}
	@Test public void lesser_map() throws Exception
	{
		equal("{1:2} < {2:3}", 1);
		equal("{1:2} < {1:3}", 1);		
		equal("{1:2} < {2:2}", 1);		
		equal("{1:2} < {1:2}", 0);
		equal("{2:3} < {1:2}", 0);
		equal("{1:3} < {1:2}", 0);
		equal("{1:1} < {1:2}", 1);
		equal("{1:2} < {}", 0);
		equal("{} < {}", 0);
	}
	@Test public void greater_map() throws Exception
	{
		equal("{1:2} > {2:3}", 0);
		equal("{1:2} > {1:3}", 0);		
		equal("{1:2} > {2:2}", 0);		
		equal("{1:2} > {1:2}", 0);
		equal("{2:3} > {1:2}", 1);
		equal("{1:3} > {1:2}", 1);
		equal("{1:1} > {1:2}", 0);
		equal("{1:2} > {}", 1);
		equal("{} > {}", 0);
	}
	@Test public void lesser_equal_map() throws Exception
	{
		equal("{1:2} <= {2:3}", 1);
		equal("{1:2} <= {1:3}", 1);		
		equal("{1:2} <= {2:2}", 1);		
		equal("{1:2} <= {1:2}", 1);
		equal("{2:3} <= {1:2}", 0);
		equal("{1:3} <= {1:2}", 0);
		equal("{1:1} <= {1:2}", 1);
		equal("{1:2} <= {}", 0);
		equal("{} <= {}", 1);
	}
	@Test public void greater_equal_map() throws Exception
	{
		equal("{1:2} >= {2:3}", 0);
		equal("{1:2} >= {1:3}", 0);		
		equal("{1:2} >= {2:2}", 0);		
		equal("{1:2} >= {1:2}", 1);
		equal("{2:3} >= {1:2}", 1);
		equal("{1:3} >= {1:2}", 1);
		equal("{1:1} >= {1:2}", 0);
		equal("{1:2} >= {}", 1);
		equal("{} >= {}", 1);
	}

}
