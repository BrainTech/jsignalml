package jsignalml;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class TestFrame {
	static final Map<String,Type> map, map1;
	static {
		map = util.newTreeMap();
		map.put("a", new TypeInt(1));
		map.put("b", new TypeFloat(2.));

		map1 = util.newTreeMap();
		map1.put("c", new TypeString("ccc"));
	}

	ASTNode node = new ASTNode.Signalml("test");
	final Frame state = new Frame(null, node, map);
	final Frame state1 = state.localize(node, map1);

	@Test public void test_call() throws Exception
	{
		assertEquals(map.get("a"), state.lookup("a"));
		assertEquals(map.get("b"), state.lookup("b"));
	}

	@Test(expectedExceptions=ExpressionFault.Unsupported.class)
	public void test_call_with_args() throws Exception
	{
		List<Type> args = Arrays.asList((Type)new TypeInt(666));
		state.lookup("a").call(args);
	}

	@Test public void test_local_lookup() throws Exception
	{
		assertEquals(map1.get("c"), state1.lookup("c"));
	}

	@Test(expectedExceptions=ExpressionFault.NameError.class)
	public void test_parent_lookup() throws Exception
	{
		state1.lookup("a");
	}

	@Test public void test_parent_call() throws Exception
	{
		assertEquals(map1.get("c"), state1.lookup("c"));
	}

	@Test(expectedExceptions=ExpressionFault.Unsupported.class)
	public void test_parent_call_with_args() throws Exception
	{
		List<Type> args = Arrays.asList((Type)new TypeInt(667));
		state1.lookup("c").call(args);
	}

	@Test(expectedExceptions=ExpressionFault.Unsupported.class)
	public void test_parent_call_without_args() throws Exception
	{
		List<Type> noargs = util.newLinkedList();
		state1.lookup("c").call(noargs);
	}
}
