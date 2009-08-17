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

    @Test public void eval_int_pow() throws Exception
    {
	equal("2**3", 8);
	equal("-2**3", -8);
	equal("-2**3", -8);
	equal("2**10", 1024);
    }	

    @Test public void eval_float_int_pow() throws Exception
    {
	equal("2.**3", 8.);
	equal("-2.**3", -8.);
	equal("-2.**3", -8.);
	equal("2.**10", 1024.);
    }	

    @Test public void eval_float_float_pow() throws Exception
    {
	equal("1.**11.", 1.);
	equal("2.**3.", 8.);
	equal("-2.**3.", -8.);
	equal("-2.**3.", -8.);
	equal("2.**10.", 1024.);
    }

    // tests adapted from test_Expression.py
    @Test public void eval_pow_pow() throws Exception
    {
	equal("2**3**2", 512);
	equal("(2**3)**2", 64);
    }

    @Test public void eval_pow_paren() throws Exception
    {
	equal("2**(3 * 2)", 64);
	equal("11**(1 + 1)", 121);
	equal("-11**(1 + 2)", -1331);
    }

    // What should this really give???
    @Test public void eval_pow_paren_with_negation() throws Exception
    {
	equal("-11**(1 + 1)", -121);
	equal("-11**2**2", -14641);
    }

    @Test public void eval_add_add() throws Exception
    {
	equal("11 + 7 + 5", 23);
	equal("11 + 7 + 5.", 23.);
	equal("-11 + 7 + 5.", 1.);
	equal("-11 + 7 + -5", -9);
	equal("-11 + 7 + -5.", -9.);
    }

    @Test public void eval_floordiv() throws Exception
    {
	equal("1//2", 0);
	equal("11//2", 5);
	equal("11.//2", 5);
	equal("1.//2", 0);
	equal("1.//2.", 0);
	equal("3.//2.", 1);
	equal("-3.//2.", -2);
    }

    @Test public void eval_truediv() throws Exception
    {
	equal("1/2", 0.5);
	equal("11/2", 5.5);
	equal("11./2", 5.5);
	equal("1./2", 0.5);
	equal("1./2.", 0.5);
	equal("3./2.", 1.5);
	equal("-3./2.", -1.5);
    }

    @Test public void eval_modulo() throws Exception
    {
	equal("1 % 2", 1);
	equal("21 % 13", 8);

	equal("1. % 2.", 1.);
	equal("21. % 13", 8.);
    }

    @Test public void eval_modulo_negative() throws Exception
    {
	equal("-1 % 2", 1);
	equal("-21 % 13", 5);

	equal("-1. % 2.", 1.);
	equal("-21. % 13", 5.);
    }

    @Test public void eval_0_pow_0() throws Exception
    {
	// like in Python and Java
	equal("0**0", 1);

    @Test public void eval_binary_and() throws Exception
    {
	equal("1 & 2", 0);
	equal("4 & 5", 4);
    }

    @Test public void eval_binary_or() throws Exception
    {
	equal("1 | 2", 3);
	equal("4 | 5", 5);
    }

    @Test public void eval_binary_xor() throws Exception
    {
	equal("1 ^ 2", 3);
	equal("4 ^ 5", 1);
    }
}
