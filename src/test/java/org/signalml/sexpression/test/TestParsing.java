package org.signalml.jsignalml.sexpression.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.signalml.jsignalml.sexpression.Processor;
import org.signalml.jsignalml.sexpression.Expression;

public class TestParsing {
    void parse(String line) throws Exception
    {
	Processor.processLine(Processor.parseLine(line));
    }

    @Test public void parse_atoms() throws Exception
    {
	parse("1");
	parse("\"a string\"");
	parse("'b string'");
	parse("1.11");
    }

    @Test public void parse_hexadecimal() throws Exception
    {
	parse("0xFF");
	parse("0x0");
	parse("0x01");
	parse("0x0aAbBcD");
    }

    @Test public void parse_octal() throws Exception
    {
	parse("0o01");
	parse("0o0");
	parse("0o77777");
	parse("0o00000");
    }

    @Test(expected= org.antlr.runtime.RecognitionException.class)
    public void parse_bad_octal() throws Exception
    {
	parse("0o8");
	parse("0oa");
    }

    @Test(expected= org.antlr.runtime.RecognitionException.class)
    public void parse_bad_hexadecimal() throws Exception
    {
	parse("0xfggg");
	parse("0oH");
    }

    @Test public void parse_negative_int() throws Exception
    {
	parse("-1");
    }

    @Test public void parse_negated_negative_int() throws Exception
    {
	parse("-------1");
    }

    @Test public void parse_positive_int() throws Exception
    {
	parse("+1");
    }

    @Test public void parse_positive_based_int() throws Exception
    {
	parse("+0x03");
	parse("+0o02");
	parse("+0b01");
    }

    @Test public void parse_additions() throws Exception
    {
	parse("1+1");
	parse("1+-1");
	parse("1++1");
	parse("+1++1");
	parse("-1++1");
	parse("--1++1");
    }
}
