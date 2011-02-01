package jsignalml;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import java.util.Map;
import java.util.TreeMap;

public class TestASTNode {
	static final Map<String,Type> map, map1;
	static {
		map = util.newTreeMap();
		map.put("a", new Type.Int(1));
		map.put("b", new Type.Float(2.));

		map1 = util.newTreeMap();
		map1.put("c", new Type.String("ccc"));
	}

	static final ASTNode.Signalml signalml = new ASTNode.Signalml("root");
	Frame state = new Frame(null).localize(map);
	//XXX there should be a test for nested namespaces (thus state1/map1)
	Frame state1 = state.localize(map1);

	@Test public void create_ExprParam() throws Exception
	{
		final int VAL = 11;
		final Type.Int intval = new Type.Int(VAL);
		ASTNode.ExprParam p = new ASTNode.ExprParam(signalml, "p", new Type.Int(1),
							new ASTNode.Positional[0],
							new Expression.Const(intval));
		final Type val = p.expr.accept(new EvalVisitor(state, p));
		assertThat(val, instanceOf(Type.Int.class));
		assertEquals(val, intval);
	}

	static ASTNode.ExprParam makeExprParam(Type type, String line)
		throws Exception
	{
		Expression expr = Processor.parse(line);
		return new ASTNode.ExprParam(signalml, "generated", type,
					     new ASTNode.Positional[0], expr);
	}

	@Test public void eval_ExprParam() throws Exception
	{
		ASTNode.ExprParam p = makeExprParam(new Type.Float(1), "a+b");
		final Type val = p.expr.accept(new EvalVisitor(state, p));
		assertThat(val, instanceOf(Type.Float.class));
		assertEquals(val, new Type.Float(3.));
	}
}
