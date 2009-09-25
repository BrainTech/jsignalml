package org.signalml.jsignalml.sexpression.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import org.signalml.jsignalml.CallHelper;
import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.Processor;
import org.signalml.jsignalml.sexpression.Expression;

public class TestTypes {
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

    void verifyIsTrue(String line, boolean expected) throws Exception
    {
	assertEquals(eval(line).isTrue(), expected);
    }

    void assertIsType(String line, Class<? extends Type> theClass) throws Exception
    {
	//	assertTrue(eval(line) instanceof Type.Int);
	assertThat(eval(line), instanceOf(theClass));
    }

    @Test public void eval_atom_is_int() throws Exception
    {
        assertIsType("1", Type.Int.class);
        assertIsType("0", Type.Int.class);
        assertIsType("-1", Type.Int.class);
    }
 
    @Test public void eval_atom_is_int_float_1() throws Exception
    {
        assertIsType("0.", Type.Int.class);
    }

    @Test public void eval_atom_is_int_float_2() throws Exception
    {
        assertIsType("1.", Type.Int.class);
    }    

    @Test public void eval_atom_is_int_float_3() throws Exception
    {
	assertIsType("-1.", Type.Int.class);
    }

    @Test public void eval_atom_is_int_string_1() throws Exception
    {   
        assertIsType("'aaa'", Type.Int.class);
    }

    @Test public void eval_atom_is_int_string_2() throws Exception
    {
        assertIsType("\"aaa\"", Type.Int.class);
    }

    @Test public void eval_atom_is_str() throws Exception
    {
        assertIsType("'aaa'", Type.String.class);
        assertIsType("\"aaa\"", Type.String.class);
    }


    @Test public void eval_atom_is_str_int() throws Exception
    {
        assertIsType("1", Type.String.class);
    }

    @Test public void eval_atom_is_str_float() throws Exception
    {
        assertIsType("1.", Type.String.class);
    }

    @Test public void eval_atom_is_str_float() throws Exception
    {
	assertIsType("1.", Type.String.class);
    }
}
