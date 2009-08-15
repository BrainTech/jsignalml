package org.signalml.jsignalml.sexpression.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.signalml.jsignalml.sexpression.Processor;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.CallHelper;
import org.signalml.jsignalml.sexpression.Type;

public class TestProcessor {
    Type eval(CallHelper state, String line)
	throws Exception
    {
	Expression expr =
	    Processor.processLine(Processor.parseLine(line));
	Type value = expr.eval(state);
	return value;
    }

    void assertLeqR(String line, Type expected) throws Exception
    {
	Processor.State state = new Processor.State();
	Type result = eval(state, line);
	assertEquals(expected, result);
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
