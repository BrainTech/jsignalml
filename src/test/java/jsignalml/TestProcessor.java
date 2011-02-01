package jsignalml;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestProcessor {
	Type eval(Frame state, String line)
		throws Exception
	{
		Expression expr = Processor.parse(line);
		final ASTNode.ExprParam param =
			new ASTNode.ExprParam(null, "expr", null,
					      new ASTNode.Positional[0], expr);
		Type value = expr.accept(new EvalVisitor(state, null));
		return value;
	}

	void assertLeqR(String line, Type expected) throws Exception
	{
		Frame state = new Frame(null);
		Type result = eval(state, line);
		assertTrue(expected.equals(result));
	}

	void assertLeqR(String line, int expected) throws Exception
	{ assertLeqR(line, new Type.Int(expected)); }
	void assertLeqR(String line, double expected) throws Exception
	{ assertLeqR(line, new Type.Float(expected)); }
	void assertLeqR(String line, String expected) throws Exception
	{ assertLeqR(line, new Type.String(expected)); }

	@Test public void simpleAdd() throws Exception
	{
		assertLeqR("1", 1);
	}
}
