package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;

import static jsignalml.TestNumberOps.eval;
import static jsignalml.TestNumberOps.equal;
import static jsignalml.TestNumberOps.listEqual;

public class TestSequenceOps {

	@Test public void eval_index_string() throws Exception
	{
		equal("'string'[0]", "s");
		equal("'string'[4]", "n");
	}

	@Test public void eval_index_list() throws Exception
	{
		equal("[0,1,2][0]", 0);
		equal("[0,1,2][1]", 1);
		equal("[0,1,2][2]", 2);
	}

	@Test public void eval_simple_list() throws Exception
	{
		listEqual("[1, 2]", 1, 2);
	}

	@Test public void eval_index_string_from_end() throws Exception
	{
		equal("'string'[-1]", "g");
		equal("'string'[-4]", "r");
	}

	@Test public void eval_index_list_from_end() throws Exception
	{
		equal("[0,1,2][-1]", 2);
		equal("[0,1,2][-2]", 1);
		equal("[0,1,2][-3]", 0);
	}

	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_invalid_1() throws Exception
	{
		eval("[0, 1][2]");
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_invalid_2() throws Exception
	{
		eval("[0][1]");
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_invalid_3() throws Exception
	{
		eval("[0][-2]");
	}

	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_str_invalid_1() throws Exception
	{
		eval("'ab'[2]");
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_str_invalid_2() throws Exception
	{
		eval("'a'[1]");
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_str_invalid_3() throws Exception
	{
		eval("'a'[-2]");
	}
}
