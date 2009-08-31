package org.signalml.jsignalml.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import java.util.Map;
import java.util.TreeMap;

import org.signalml.jsignalml.Machine;
import org.signalml.jsignalml.Machine.Param;
import org.signalml.jsignalml.LocalState;
import org.signalml.jsignalml.CallHelper;
import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.ExpressionFault;
import org.signalml.jsignalml.sexpression.Processor;

public class TestMachine {
    static final Map<String,Type> map, map1;
    static {
	map = new TreeMap<String,Type>();
	map.put("a", new Type.Int(1));
	map.put("b", new Type.Float(2.));

	map1 = new TreeMap<String,Type>();
	map1.put("c", new Type.String("ccc"));
    }

    CallHelper state = new LocalState(null, map);
    CallHelper state1 = new LocalState(state, map1);

    @Test public void create_ExprParam() throws Exception {
	final int VAL = 11;
	final Type.Int intval = new Type.Int(VAL);
	Param p = new Machine.ExprParam("p", Type.Int.class,
					new Machine.Positional[0],
					new Expression.Const(intval));
	Type val = p.eval(state);
	assertThat(val, instanceOf(Type.Int.class));
	assertEquals(val, intval);
    }

    static Machine.ExprParam makeExprParam(Class<? extends Type> type, String line)
	throws Exception
    {
	Expression expr = Processor.parse(line);
	return new Machine.ExprParam("generated", type,
				     new Machine.Positional[0], expr);
    }

    @Test public void eval_ExprParam() throws Exception {
	Param p = makeExprParam(Type.Float.class, "a+b");
	Type val = p.eval(state);
	assertThat(val, instanceOf(Type.Float.class));
	assertEquals(val, new Type.Float(3.));
    }
}
