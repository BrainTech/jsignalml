package jsignalml;

import static jsignalml.TestNumberOps.eval;

import org.junit.Test;

public class TestAccessOps {

	@Test(expected=ExpressionFault.ConstAttributeError.class)
	public void access_nonexistent() throws Exception
	{
		eval("(1).aaaaaaaaaaaaaaaa");
	}
}
