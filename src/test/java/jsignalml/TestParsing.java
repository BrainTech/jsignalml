package jsignalml;

import java.util.Collection;
import java.util.Arrays;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static jsignalml.Processor.parse;

public class TestParsing {

	@Test(dataProvider="valid")
	public void parsingWorks(final String expr) {
		parse(expr);
	}

	@Test(expectedExceptions=SyntaxError.class, dataProvider="invalid")
	public void parsingFails(final String expr) {
		parse(expr);
	}

	@DataProvider
	public static Object[][] valid() {
		return valid;
	}

	@DataProvider
	public static Object[][] invalid() {
		return invalid;
	}

	public static final Object[][] valid = new Object[][] {
		{"1"},
		{"\"a string\""},
		{"'b string'"},
		{"1.11"},

		{"1\n"},
		{"\"a string\"\n"},
		{"'b string'\n"},
		{"1.11\n"},

		{"\n1"},
		{"\n\"a string\""},
		{"\n'b string'"},
		{"\n1.11"},

		{"0xFF"},
		{"0x0"},
		{"0x01"},
		{"0x0aAbBcD"},

		{"0o01"},
		{"0o0"},
		{"0o77777"},
		{"0o00000"},

		{"-1"},

		{"-------1"},

		{"+1"},

		{"+0x03"},
		{"+0o02"},
		{"+0b01"},

		{"1+1"},

		{"1 + -1"},
		{"1 + + 1"},
		{"+1 + +1"},
		{"- 1 + + 1"},
		{" - - 1 + + 1 "},

		{"1 +\n-1"},
		{"1 +\n+ 1"},
		{"+1 +\n+1"},
		{"- 1 +\n+ 1"},
		{" -\n- 1\n+\n+\n1 "},

		{"1+-1"},
		{"1++1"},
		{"+1++1"},
		{"-1++1"},
		{"--1++1"},

		{"[]"},
		{"[ ]"},

		{"[1]"},
		{"[1,2]"},
		{"[1,-2]"},
		{"[-1,-2]"},
		{"[1, -2, 3, 4, 5, 6, 9, 10, 12092020]"},

		{"{1:2}"},
		{"{1: 2}"},
		{" { 1 : 2 } "},

		{"{{{}:{}}:{}}"},
		{"{{}: {}}"},
		{"[{}, {[]:[]}]"},

		{"{}"},
		{"{ }"},

		{"a[0]"},
		{"a[b]"},

		{"[0,1,2][0]"},
		{"(a)[0]"},
		{"(a)[-1]"},

		// example from EDF
		{"data_format(0,0)[-1]"},

		// oo notation
		{"a.b"},

		// oo notation chained
		{"a.b.c"},

		// oo notation call
		{"a.b()"},

		// oo notation chained call last
		{"a.b.c()"},

		// oo notation chained call first
		{"a().b.c"},

		// oo notation chained call all
		{"a().b().c()"},
	};

	public static final Object[][] invalid = new Object[][] {
		{"0o8"},
		{"0oa"},

		{"0xfggg"},
		{"0oH"},

		{"1;2"},
		{"a+b;"},
		{"a+b;\n"},
		{"a+b;\nc"},
		{"'a"},
		{"\"a"},
		{"\"a\\\""},
		{"(1"},
		{"1)"},
		{") + 3"},
		{"[1"},
		{"1]"},
		{"] + 3"},

		// oo notation bad
		{"a..b.c"},
	};
}
