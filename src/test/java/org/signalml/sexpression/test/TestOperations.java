package org.signalml.jsignalml.sexpression.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.Processor;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.CallHelper;

public class TestOperations {
    Type eval(String line) throws Exception
    {
	CallHelper state = new Processor.State();
	Expression expr =
	    Processor.processLine(Processor.parseLine(line));
	Type val = expr.eval(state);
	return val;
    }

    void equal(String line, int expected) throws Exception
    {
	assertTrue(eval(line).equals(new Type.Int(expected)));
    }
    void equal(String line, double expected) throws Exception
    {
	assertTrue(eval(line).equals(new Type.Float(expected)));
    }
    void equal(String line, String expected) throws Exception
    {
	assertTrue(eval(line).equals(new Type.String(expected)));
    }

    @Test public void check_int_atom_values() throws Exception
    {
	equal("1", 1);
	equal("0", 0);
	equal("00000", 0);
	equal("-1", -1);
    }

    @Test public void check_float_atom_values() throws Exception
    {
	equal("0.01", 0.01);
	equal("0e3", 0.0);
	equal("00000.", 0.0);
	equal("-11.", -11.);
    }

    @Test public void check_string_atom_values() throws Exception
    {
	equal("'a'", "a");
	equal("'a string with spaces'", "a string with spaces");
	equal("'\"'", "\"");
    }

    @Test public void check_string_with_escapes() throws Exception
    {
	equal("'\\t'", "\t");
	equal("'\\n'", "\n");
	equal("'\\r'", "\r");
    }

}
