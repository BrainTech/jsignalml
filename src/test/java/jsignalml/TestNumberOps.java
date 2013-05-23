package jsignalml;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestNumberOps {

	static Type eval(String line) throws Exception
	{
		final Expression expr = Processor.parse(line);
		final Frame state = new Frame(new ASTNode.Signalml("test"));
		final Type val = expr.accept(new EvalVisitor(state));
		return val;
	}

	static void equal(String line, int expected) throws Exception
	{
		assertEquals(new TypeInt(expected), eval(line));
	}
	static void equal(String line, double expected) throws Exception
	{
		assertEquals(new TypeFloat(expected), eval(line));
	}

	static void equal(String line, double expected, double delta)
		throws Exception
	{
		assertEquals(new TypeFloat(expected).getValue(),
			     ((TypeFloat)eval(line)).getValue(), delta);
	}

	static void equal(String line, String expected) throws Exception
	{
		assertEquals(new TypeString(expected), eval(line));
	}
	static void listEqual(String line, Object...elements) throws Exception
	{
		assertEquals(TypeList.make(elements), eval(line));
	}

	static void verifyIsTrue(String line, boolean expected) throws Exception
	{
		assertEquals(expected, eval(line).isTrue());
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
		equal("-1.", -1.);
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
	}

	@Test public void eval_pow_paren_with_negation_2() throws Exception
	{
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
		equal("-3//2", -2);
		equal("-3.//2.", -2);
	}
	
	@Test public void eval_ceildiv() throws Exception
	{
		equal("1///2", 1);
		equal("11///2", 6);
		equal("11.///2", 6);
		equal("1.///2", 1);
		equal("1.///2.", 1);
		equal("3.///2.", 2);
		equal("-3///2", -1);
		equal("-3.///2.", -1);
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

	@Test public void eval_modulo_negative_dividend_int() throws Exception
	{
		equal("-1 % 2", 1);
		equal("-21 % 13", 5);
	}
	@Test public void eval_modulo_negative_dividend_float() throws Exception
	{
		equal("-1. % 2.", 1.);
		equal("-21. % 13", 5.);
	}

	@Test public void eval_modulo_negative_divisor_int() throws Exception
	{
		equal("1 % -2", -1);
		equal("21 % -13", -5);
	}

	@Test public void eval_modulo_negative_divisor_float() throws Exception
	{
		equal("1. % -2.", -1.);
		equal("21. % -13", -5.);
	}

	@Test public void eval_modulo_negative_both_int() throws Exception
	{
		equal("-1 % -2", -1);
		equal("-21 % -13", -8);
	}

	@Test public void eval_modulo_negative_both_float() throws Exception
	{
		equal("-1. % -2.", -1.);
		equal("-21. % -13", -8.);
	}

	@Test public void eval_0_pow_0() throws Exception
	{
		// like in Python and Java
		equal("0**0", 1);
	}

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

	@Test public void eval_multiply_paren_add() throws Exception
	{
		equal("2 * ( 4 + 5 )", 18);
		equal("-3 * ( 4 + 5 )", -27);
	}

	@Test public void eval_pow_multiply() throws Exception
	{
		equal("2**2*3**2", 36);
		equal("3**2*2**2", 36);
		equal("-3**2*2**2", -36);
	}

	@Test public void eval_smaller_than() throws Exception
	{
		equal("2<2.", 0);
		equal("2<3", 1);
		equal("2<3.", 1);
		equal("2<2", 0);
		equal("2<1",0);
		equal("2<1.",0);
		equal("2<-1",0);
		equal("2<-1.",0);
		equal("-2<-1",1);
		equal("-2<-1.",1);
		equal("-2<3", 1);
		equal("-2<3.", 1);
	}

	@Test public void eval_greater_than() throws Exception
	{
		equal("2>2.", 0);
		equal("2>3", 0);
		equal("2>3.", 0);
		equal("2>2", 0);
		equal("2>1",1);
		equal("2>1.",1);
		equal("2>-1",1);
		equal("2>-1.",1);
		equal("-2>-1",0);
		equal("-2>-1.",0);
		equal("-2>1",0);
		equal("-2>1.",0);
		equal("2.>2.", 0);
		equal("2.>3", 0);
		equal("2.>3.", 0);
		equal("2.>2", 0);
		equal("2.>1",1);
		equal("2.>1.",1);
		equal("2.>-1",1);
		equal("2.>-1.",1);
		equal("-2.>-1",0);
		equal("-2.>-1.",0);
		equal("-2.>1",0);
		equal("-2.>1.",0);
	}

	@Test public void eval_equal_smaller_than() throws Exception
	{	equal("2<=2.", 1);
		equal("2<=3", 1);
		equal("2<=3.", 1);
		equal("2<=2", 1);
		equal("2<1",0);
		equal("2<1.",0);
		equal("2<-1",0);
		equal("2<-1.",0);
		equal("-2>-1",0);
		equal("-2>-1.",0);
		equal("-2<=3.", 1);
		equal("-2<=2", 1);
		equal("2.<=2.", 1);
		equal("2.<=3", 1);
		equal("2.<=3.", 1);
		equal("2.<=2", 1);
		equal("2.<1",0);
		equal("2.<1.",0);
		equal("2.<-1",0);
		equal("2.<-1.",0);
		equal("-2.>-1",0);
		equal("-2.>-1.",0);
		equal("-2.<=3.", 1);
		equal("-2.<=2", 1);
	}

	@Test public void eval_equal_greater_than() throws Exception
	{
		equal("2>=2.", 1);
		equal("2>=3", 0);
		equal("2>=3.", 0);
		equal("2>=2", 1);
		equal("2>=-1",1);
		equal("2>=-1.",1);
		equal("2>=1",1);
		equal("2>=1.",1);
		equal("-2>=-1",0);
		equal("-2>=-1.",0);
		equal("-2>=1",0);
		equal("-2>=1.",0);
		equal("2.>=2.", 1);
		equal("2.>=3", 0);
		equal("2.>=3.", 0);
		equal("2.>=2", 1);
		equal("2.>=-1",1);
		equal("2.>=-1.",1);
		equal("2.>=1",1);
		equal("2.>=1.",1);
		equal("-2.>=-1",0);
		equal("-2.>=-1.",0);
		equal("-2.>=1",0);
		equal("-2.>=1.",0);
	}

	@Test public void eval_equal_less_than() throws Exception
	{
		equal("2<=2.", 1);
		equal("2<=3", 1);
		equal("2<=3.", 1);
		equal("2<=2", 1);
		equal("2<=-1",0);
		equal("2<=-1.",0);
		equal("2<=1",0);
		equal("2<=1.",0);
		equal("-2<=-1",1);
		equal("-2<=-1.",1);
		equal("-2<=1",1);
		equal("-2<=1.",1);
		equal("2.<=2.", 1);
		equal("2.<=3", 1);
		equal("2.<=3.", 1);
		equal("2.<=2", 1);
		equal("2.<=-1",0);
		equal("2.<=-1.",0);
		equal("2.<=1",0);
		equal("2.<=1.",0);
		equal("-2.<=-1",1);
		equal("-2.<=-1.",1);
		equal("-2.<=1",1);
		equal("-2.<=1.",1);
	}

	@Test public void eval_equal_to() throws Exception
	{
		equal("2==2.", 1);
		equal("2==3", 0);
		equal("2==3.", 0);
		equal("2==2", 1);
		equal("2==-1",0);
		equal("2==-1.",0);
		equal("2==1",0);
		equal("2==1.",0);
		equal("-2==-1",0);
		equal("-2==-1.",0);
		equal("-2==1",0);
		equal("-2==1.",0);
		equal("2.==2.", 1);
		equal("2.==3", 0);
		equal("2.==3.", 0);
		equal("2.==2", 1);
		equal("2.==-1",0);
		equal("2.==-1.",0);
		equal("2.==1",0);
		equal("2.==1.",0);
		equal("-2.==-1",0);
		equal("-2.==-1.",0);
		equal("-2.==1",0);
		equal("-2.==1.",0);
	}

	@Test public void eval_un_equal_to() throws Exception
	{
		equal("2!=2.", 0);
		equal("2!=3", 1);
		equal("2!=3.", 1);
		equal("2!=2", 0);
		equal("2!=-1",1);
		equal("2!=-1.",1);
		equal("2!=1",1);
		equal("2!=1.",1);
		equal("-2!=-1",1);
		equal("-2!=-1.",1);
		equal("-2!=1",1);
		equal("-2!=1.",1);
		equal("2.!=2.", 0);
		equal("2.!=3", 1);
		equal("2.!=3.", 1);
		equal("2.!=2", 0);
		equal("2.!=-1",1);
		equal("2.!=-1.",1);
		equal("2.!=1",1);
		equal("2.!=1.",1);
		equal("-2.!=-1",1);
		equal("-2.!=-1.",1);
		equal("-2.!=1",1);
		equal("-2.!=1.",1);
	}

	@Test public void eval_or_1() throws Exception
	{
		equal("0 or 0", 0);
	}

	@Test public void eval_or_2() throws Exception
	{
		equal("0. or 0", 0);
	}

	@Test public void eval_or_3() throws Exception
	{
		equal("0 or 0.", 0.);
	}

	@Test public void eval_or_4() throws Exception
	{
		equal("0. or 0.", 0.);
	}

	@Test public void eval_or_5() throws Exception
	{
		equal("1 or 0", 1);
	}

	@Test public void eval_or_6() throws Exception
	{
		equal("0 or 1", 1);
	}

	@Test public void eval_or_7() throws Exception
	{
		equal("1 or 1", 1);
	}

	@Test public void eval_or_8() throws Exception
	{
		equal("1. or 1", 1.);
	}

	@Test public void eval_or_9() throws Exception
	{
		equal("1 or 1.", 1);
	}

	@Test public void eval_or_10() throws Exception
	{
		equal("1. or 1.", 1.);
	}

	@Test public void eval_or_11() throws Exception
	{
		equal("10 or 1.", 10);
	}

	@Test public void eval_or_12() throws Exception
	{
		equal("10. or 1.", 10.);
	}

	@Test public void eval_or_13() throws Exception
	{
		equal("10. or 1", 10.);
	}

	@Test public void eval_or_14() throws Exception
	{
		equal("10. or 10", 10.);
	}

	@Test public void eval_or_15() throws Exception
	{
		equal("-1 or 0", -1);
	}

	@Test public void eval_or_16() throws Exception
	{
		equal("0 or -1", -1);
	}

	@Test public void eval_or_17() throws Exception
	{
		equal("-1 or 1", -1);
	}

	@Test public void eval_or_18() throws Exception
	{
		equal("1 or -1", 1);
	}

	@Test public void eval_or_19() throws Exception
	{
		equal("-1. or 1", -1.);
	}

	@Test public void eval_or_20() throws Exception
	{
		equal("1 or -1.", 1);
	}

	@Test public void eval_or_21() throws Exception
	{
		equal("-1. or 1.", -1.);
	}

	@Test public void eval_or_22() throws Exception
	{

		equal("10 or -1.", 10);
	}

	@Test public void eval_or_23() throws Exception
	{
		equal("10. or -1.", 10.);
	}

	@Test public void eval_or_24() throws Exception
	{
		equal("-10. or 1", -10.);
	}

	@Test public void eval_or_25() throws Exception
	{
		equal("-10. or 10", -10.);
	}

	@Test public void eval_and_1() throws Exception
	{
		equal("0 and 0", 0);
	}

	@Test public void eval_and_2() throws Exception
	{
		equal("0. and 0", 0.);
	}

	@Test public void eval_and_3() throws Exception
	{
		equal("0 and 0.", 0);
	}

	@Test public void eval_and_4() throws Exception
	{
		equal("0. and 0.", 0.);
	}

	@Test public void eval_and_5() throws Exception
	{
		equal("1 and 0", 0);
	}

	@Test public void eval_and_6() throws Exception
	{
		equal("0 and 1", 0);
	}

	@Test public void eval_and_7() throws Exception
	{
		equal("1 and 1", 1);
	}

	@Test public void eval_and_8() throws Exception
	{
		equal("1. and 1", 1);
	}

	@Test public void eval_and_9() throws Exception
	{
		equal("1 and 1.", 1.);
	}

	@Test public void eval_and_10() throws Exception
	{
		equal("1. and 1.", 1.);
	}

	@Test public void eval_and_11() throws Exception
	{
		equal("10 and 1.", 1.);
	}

	@Test public void eval_and_12() throws Exception
	{
		equal("10. and 1.", 1.);
	}

	@Test public void eval_and_13() throws Exception
	{
		equal("10. and 1", 1);
	}

	@Test public void eval_and_14() throws Exception
	{
		equal("10. and 10", 10);
	}

	@Test public void eval_and_15() throws Exception
	{
		equal("-1 and 0", 0);
	}

	@Test public void eval_and_16() throws Exception
	{
		equal("0 and -1", 0);
	}

	@Test public void eval_and_17() throws Exception
	{
		equal("-1 and 1", 1);
	}

	@Test public void eval_and_18() throws Exception
	{
		equal("1 and -1", -1);
	}

	@Test public void eval_and_19() throws Exception
	{
		equal("-1. and 1", 1);
	}

	@Test public void eval_and_20() throws Exception
	{
		equal("1 and -1.", -1.);
	}

	@Test public void eval_and_21() throws Exception
	{
		equal("-1. and 1.", 1.);
	}

	@Test public void eval_and_22() throws Exception
	{
		equal("10 and -1.", -1.);
	}

	@Test public void eval_and_23() throws Exception
	{
		equal("10. and -1.", -1.);
	}

	@Test public void eval_and_24() throws Exception
	{
		equal("-10. and 1", 1);
	}

	@Test public void eval_and_25() throws Exception
	{
		equal("-10. and 10", 10);
	}

	@Test public void eval_not_1() throws Exception
	{
		equal("not 4",0);
	}

	@Test public void eval_not_2() throws Exception
	{
		equal("not -4.",0);
	}

	@Test public void eval_not_3() throws Exception
	{
		equal("not 0",1);
	}

	@Test public void eval_not_4() throws Exception
	{
		equal("not 4.",0);
	}

	@Test public void eval_not_5() throws Exception
	{
		equal("not -4",0);
	}

	@Test public void eval_not_6() throws Exception
	{
		equal("not (4-3)",0);
	}

	@Test public void eval_not_7() throws Exception
	{
		equal("not (4 - 2*2)",1);
	}

	@Test public void eval_not_8() throws Exception
	{
		equal("not (8 - 2**3)",1);
	}

	@Test public void eval_not_9() throws Exception
	{
		equal("not 4-3",0);
	}

	@Test public void eval_not_10() throws Exception
	{
		equal("not 4 - 2*2",1);
	}

	@Test public void eval_not_11() throws Exception
	{
		equal("not 8 - 2**3",1);
	}

	@Test public void eval_not_12() throws Exception
	{
		equal("not -1 +1",1);
	}

	@Test public void eval_ternary() throws Exception
	{
		equal(" 4 ? 2 : 1",2);
		equal(" 0 ? 2 : 1", 1);
	}

	@Test public void eval_is_true__int_1() throws Exception
	{
		verifyIsTrue("1", true);
	}

	@Test public void eval_is_true__int_minus_1() throws Exception
	{
		verifyIsTrue("-1", true);
	}

	@Test public void eval_is_true__int_4() throws Exception
	{
		verifyIsTrue("4", true);
	}

	@Test public void eval_is_true_int_0() throws Exception
	{
		verifyIsTrue("0", false);
	}

	@Test public void eval_is_true_float_1() throws Exception
	{
		verifyIsTrue("1.", true);
	}

	@Test public void eval_is_true_float_4() throws Exception
	{
		verifyIsTrue("4.", true);
	}

	@Test public void eval_is_true_float_0() throws Exception
	{
		verifyIsTrue("0.", false);
	}

	@Test public void eval_is_true_float_minus_1() throws Exception
	{
		verifyIsTrue("-1.", true);
	}

	@Test public void eval_sin_0() throws Exception
	{
	    equal("sin(0)", 0.0);
	}

	@Test public void val_sin_30() throws Exception
	{
		equal("sin(0.5236)", 0.5, .001);
	}

	@Test public void val_sin_45() throws Exception
	{
		equal("sin(0.7854)", 0.7071, .001);
	}

	@Test public void val_sin_neg45() throws Exception
	{
		equal("sin(-0.7854)", -0.7071, .001);
	}

	@Test public void eval_cos_0() throws Exception
	{
		equal("cos(0)", 1.0);
	}

	@Test public void eval_cos_30() throws Exception
	{
		equal("cos(0.5236)", 0.8660, .001);
	}

	@Test public void eval_cos_45() throws Exception
	{
	    equal("cos(0.7854)", 0.7071 , .001);
	}

	@Test public void eval_cos_neg45() throws Exception
	{
	    equal("cos(-0.7854)", 0.7071 , .001);
	}

	@Test public void eval_tan_0() throws Exception
	{
	    equal("tan(0)", 0.0);
	}

	@Test public void eval_tan_30() throws Exception
	{
		equal("tan(0.5236)", 0.5774, .001);
	}

	@Test public void eval_tan_45() throws Exception
	{
	    equal("tan(0.7854)", 1, .001);
	}

	@Test public void eval_tan_neg45() throws Exception
	{
		equal("tan(-0.7854)", -1, .001);
	}

	@Test public void eval_cot_0() throws Exception
	{
		equal("cot(0)", new TypeFloat(Double.POSITIVE_INFINITY).getValue());
	}

	@Test public void eval_cot_30() throws Exception
	{
		equal("cot(0.5236)", 1.7321, .001);
	}

	@Test public void eval_cot_45() throws Exception
	{
		equal("cot(0.7854)", 1, .001);
	}

	@Test public void eval_cot_neg45() throws Exception
	{
		equal("cot(-0.7854)", -1, .001);
	}

	@Test public void eval_exp_neg_float_3_5() throws Exception
	{
		equal("exp(-3.5)", 0.030197, .001);
	}

	@Test public void eval_exp_neg_int_3() throws Exception
	{
		equal("exp(-3)", 0.049787, .001);
	}

	@Test public void eval_exp_pos_float_3_5() throws Exception
	{
		equal("exp(3.5)", 33.1155, .001);
	}

	@Test public void eval_exp_pos_int_3() throws Exception
	{
		equal("exp(3)", 20.0855, .001);
	}

	@Test public void eval_exp_zero() throws Exception
	{
		equal("exp(0)", 1.0);
	}

	@Test public void eval_log_int_10() throws Exception
	{
		equal("log(10)", 2.30259, .001);
	}

	@Test public void eval_log_float_7_5() throws Exception
	{
		equal("log(7.5)", 2.01490, .001);
	}

	@Test public void eval_log10_int_10() throws Exception
	{
		equal("log10(10)", 1.0);
	}

	@Test public void eval_log10_float_7_5() throws Exception
	{
		equal("log10(7.5)", 0.875061, .001);
	}
}
