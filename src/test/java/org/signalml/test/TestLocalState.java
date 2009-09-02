package org.signalml.jsignalml.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.ExpressionFault;
import org.signalml.jsignalml.LocalState;
import org.signalml.jsignalml.util;

public class TestLocalState {
    static final Map<String,Type> map, map1;
    static {
	map = util.newTreeMap();
	map.put("a", new Type.Int(1));
	map.put("b", new Type.Float(2.));

	map1 = util.newTreeMap();
	map1.put("c", new Type.String("ccc"));
    }

    final LocalState state = new LocalState(null, map);
    final LocalState state1 = new LocalState(state, map1);

    @Test public void test_call() throws Exception {
	assertTrue(state.call("a").equals(map.get("a")));
	assertTrue(state.call("b").equals(map.get("b")));
    }

    @Test(expected=ExpressionFault.NameError.class)
    public void test_call_bad_id() throws Exception {
	state.call("c");
    }

    @Test(expected=ExpressionFault.ArgMismatch.class)
    public void test_call_with_args() throws Exception {
	state.call("a", new Type.Int(666));
    }

    @Test public void test_parent_call() throws Exception {
	assertTrue(state1.call("a").equals(map.get("a")));
	assertTrue(state1.call("b").equals(map.get("b")));
	assertTrue(state1.call("c").equals(map1.get("c")));
    }

    @Test(expected=ExpressionFault.ArgMismatch.class)
    public void test_parent_call_with_args() throws Exception {
	state.call("a", new Type.Int(666));
    }

    @Test(expected=UnsupportedOperationException.class)
    public void test_add_new_local() {
	Map<String,Type> mapcopy = new TreeMap<String,Type>(map);
	new LocalState(null, mapcopy).locals.put("xxx", new Type.Int(666));
    }
}
