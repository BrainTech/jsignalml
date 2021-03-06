package jsignalml;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

public class TestASTNode {
	static final Map<String,Type> map, map1;
	static {
		map = util.newTreeMap();
		map.put("a", new TypeInt(1));
		map.put("b", new TypeFloat(2.));

		map1 = util.newTreeMap();
		map1.put("c", new TypeString("ccc"));
	}

	static final ASTNode.Signalml signalml = new ASTNode.Signalml("root");
	Frame state = new Frame(null, signalml, map);
	//XXX there should be a test for nested namespaces (thus state1/map1)
	Frame state1 = state.localize(signalml, map1);

	@Test public void create_ExprParam() throws Exception
	{
		final int VAL = 11;
		final TypeInt intval = new TypeInt(VAL);
		ASTNode.ExprParam p = new ASTNode.ExprParam(signalml, "p", new TypeInt(1),
							new Expression.Const(intval));
		final Type val = p.expr.accept(new EvalVisitor(state));
		helpers.assertInstanceOf(val, TypeInt.class);
		assertEquals(val, intval);
	}

	static ASTNode.ExprParam makeExprParam(Type type, String line)
		throws Exception
	{
		Expression expr = Processor.parse(line);
		return new ASTNode.ExprParam(signalml, "generated", type, expr);
	}

	@Test public void eval_ExprParam() throws Exception
	{
		ASTNode.ExprParam p = makeExprParam(new TypeFloat(1), "a+b");
		final Type val = p.expr.accept(new EvalVisitor(state));
		helpers.assertInstanceOf(val, TypeFloat.class);
		assertEquals(val, new TypeFloat(3.));
	}
}
