package jsignalml;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static jsignalml.Processor.parse;

public class TestParsing {
	@Test public void parse_atoms() throws Exception
	{
		parse("1");
		parse("\"a string\"");
		parse("'b string'");
		parse("1.11");
	}

	@Test public void parse_atoms_with_newlines_at_end() throws Exception
	{
		parse("1\n");
		parse("\"a string\"\n");
		parse("'b string'\n");
		parse("1.11\n");
	}

	@Test public void parse_atoms_with_newlines_in_front() throws Exception
	{
		parse("\n1");
		parse("\n\"a string\"");
		parse("\n'b string'");
		parse("\n1.11");
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

	@Test(expected= SyntaxError.class)
	public void parse_bad_octal() throws Exception
	{
		parse("0o8");
		parse("0oa");
	}

	@Test(expected= SyntaxError.class)
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

	@Test public void parse_add_with_no_spaces() throws Exception
	{
		parse("1+1");
	}

	@Test public void parse_additions_with_spaces() throws Exception
	{
		parse("1 + -1");
		parse("1 + + 1");
		parse("+1 + +1");
		parse("- 1 + + 1");
		parse(" - - 1 + + 1 ");
	}

	@Test public void parse_additions_with_newlines() throws Exception
	{
		parse("1 +\n-1");
		parse("1 +\n+ 1");
		parse("+1 +\n+1");
		parse("- 1 +\n+ 1");
		parse(" -\n- 1\n+\n+\n1 ");
	}

	@Test public void parse_additions_without_spaces() throws Exception
	{
		parse("1+-1");
		parse("1++1");
		parse("+1++1");
		parse("-1++1");
		parse("--1++1");
	}

	@Test(expected=SyntaxError.class)
	public void parse_multiple_expressions_invalid_1() throws Exception
	{
		parse("1;2");
	}

	@Test(expected=SyntaxError.class)
	public void parse_multiple_expressions_invalid_2() throws Exception
	{
		parse("a+b;");
	}

	@Test(expected=SyntaxError.class)
	public void parse_multiple_expressions_invalid_3() throws Exception
	{
		parse("a+b;\n");
	}

	@Test(expected=SyntaxError.class)
	public void parse_multiple_expressions_invalid_4() throws Exception
	{
		parse("a+b;\nc");
	}

	@Test public void parse_empty_list() throws Exception
	{
		parse("[]");
		parse("[ ]");
	}

	@Test public void parse_int_list() throws Exception
	{
		parse("[1]");
		parse("[1,2]");
		parse("[1,-2]");
		parse("[-1,-2]");
		parse("[1, -2, 3, 4, 5, 6, 9, 10, 12092020]");
	}

	@Test public void parse_index_basic() throws Exception
	{
		parse("a[0]");
		parse("a[b]");
	}

	@Test public void parse_index_list_and_subexpr() throws Exception
	{
		parse("[0,1,2][0]");
		parse("(a)[0]");
		parse("(a)[-1]");
	}

	@Test public void parse_example_from_EDF() throws Exception
	{
		parse("data_format(0,0)[-1]");
	}
}
