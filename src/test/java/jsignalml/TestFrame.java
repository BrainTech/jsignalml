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

	final Frame state = new Frame(null).localize(map);
	final Frame state1 = state.localize(map1);

	@Test public void test_lookup() throws Exception
	{
		List<Type> args = util.newLinkedList();
		assertEquals(map.get("a"), state.lookup("a", args));
		assertEquals(map.get("b"), state.lookup("b", args));
	}

	@Test public void test_lookup_with_args() throws Exception
	{
		List<Type> args = Arrays.asList((Type)new TypeInt(666));
		assertEquals(null, state.lookup("a", args));
	}

	@Test public void test_parent_lookup() throws Exception
	{
		List<Type> args = util.newLinkedList();
		assertEquals(map.get("a"), state1.lookup("a", args));
		assertEquals(map.get("b"), state1.lookup("b", args));
		assertEquals(map1.get("c"), state1.lookup("c", args));
	}

	@Test public void test_parent_lookup_with_args() throws Exception
	{
		List<Type> args = Arrays.asList((Type)new TypeInt(667));
		assertEquals(null, state1.lookup("a", args));
	}

}
