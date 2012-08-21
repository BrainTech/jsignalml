package jsignalml;

import static jsignalml.TestNumberOps.eval;

import org.testng.annotations.Test;

public class TestAccessOps {

	@Test(expectedExceptions=ExpressionFault.ConstAttributeError.class)
	public void access_nonexistent() throws Exception
	{
		eval("(1).aaaaaaaaaaaaaaaa");
	}
}
