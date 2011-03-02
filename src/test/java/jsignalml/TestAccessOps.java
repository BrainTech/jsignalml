package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;

import static jsignalml.TestNumberOps.eval;
import static jsignalml.TestNumberOps.equal;

public class TestAccessOps {

	@Test(expected=ExpressionFault.AttributeError.class)
	public void access_nonexistent() throws Exception
	{
		eval("(1).aaaaaaaaaaaaaaaa");
	}
}
