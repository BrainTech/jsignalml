package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestFrame {
	static final Map<String,Type> map, map1;
	static {
		map = util.newTreeMap();
		map.put("a", new Type.Int(1));
		map.put("b", new Type.Float(2.));

		map1 = util.newTreeMap();
		map1.put("c", new Type.String("ccc"));
	}

	final Frame state = new Frame(null).localize(map);
	final Frame state1 = state.localize(map1);

	@Test public void test_lookup() throws Exception
	{
		List<Type> args = util.newLinkedList();
		assertEquals(map.get("a"), state.lookup("a", args));
		assertEquals(map.get("b"), state.lookup("b", args));
	}

	@Test(expected=ExpressionFault.NameError.class)
	public void test_lookup_bad_id() throws Exception
	{
		List<Type> args = util.newLinkedList();
		state.lookup("c", args);
	}

	@Test(expected=ExpressionFault.ArgMismatch.class)
	public void test_lookup_with_args() throws Exception
	{
		List<Type> args = util.newLinkedList();
		args.add((Type)(new Type.Int(666)));
		state.lookup("a", args);
	}

	@Test public void test_parent_lookup() throws Exception
	{
		List<Type> args = util.newLinkedList();
		assertEquals(map.get("a"), state1.lookup("a", args));
		assertEquals(map.get("b"), state1.lookup("b", args));
		assertEquals(map1.get("c"), state1.lookup("c", args));
	}

	@Test(expected=ExpressionFault.ArgMismatch.class)
	public void test_parent_lookup_with_args() throws Exception
	{
		List<Type> args = util.newLinkedList();
		args.add(new Type.Int(666));
		state.lookup("a", args);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void test_add_new_local()
	{
		Map<String,Type> mapcopy = new TreeMap<String,Type>(map);
		new Frame(null).localize(mapcopy).env.put("xxx", new Type.Int(666));
	}
}
