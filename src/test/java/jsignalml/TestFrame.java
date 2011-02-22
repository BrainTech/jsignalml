package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;

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
	final Frame state = new Frame(node, map);
	final Frame state1 = state.localize(node, map1);

	@Test public void test_call() throws Exception
	{
		List<Type> args = util.newLinkedList();
		assertEquals(map.get("a"), state.call("a", args));
		assertEquals(map.get("b"), state.call("b", args));
	}

	@Test public void test_call_with_args() throws Exception
	{
		List<Type> args = Arrays.asList((Type)new TypeInt(666));
		assertEquals(null, state.call("a", args));
	}

	@Test public void test_parent_call() throws Exception
	{
		List<Type> args = util.newLinkedList();
		assertEquals(map.get("a"), state1.call("a", args));
		assertEquals(map.get("b"), state1.call("b", args));
		assertEquals(map1.get("c"), state1.call("c", args));
	}

	@Test public void test_parent_call_with_args() throws Exception
	{
		List<Type> args = Arrays.asList((Type)new TypeInt(667));
		assertEquals(null, state1.call("a", args));
	}

}
