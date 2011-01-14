package jsignalml;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
}
