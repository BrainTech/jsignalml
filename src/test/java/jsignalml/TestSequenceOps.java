package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;

import static jsignalml.TestNumberOps.eval;
import static jsignalml.TestNumberOps.equal;
import static jsignalml.TestNumberOps.listEqual;

public class TestSequenceOps {

	@Test public void eval_index_string() throws Exception
	{
		equal("'string'[0]", "s");
		equal("'string'[4]", "n");
	}

	@Test public void eval_index_list() throws Exception
	{
		equal("[0,1,2][0]", 0);
		equal("[0,1,2][1]", 1);
		equal("[0,1,2][2]", 2);
	}

	@Test public void eval_simple_list() throws Exception
	{
		listEqual("[1, 2]", 1, 2);
	}

	@Test public void eval_index_string_from_end() throws Exception
	{
		equal("'string'[-1]", "g");
		equal("'string'[-4]", "r");
	}

	@Test public void eval_index_list_from_end() throws Exception
	{
		equal("[0,1,2][-1]", 2);
		equal("[0,1,2][-2]", 1);
		equal("[0,1,2][-3]", 0);
	}

	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_invalid_1() throws Exception
	{
		eval("[0, 1][2]");
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_invalid_2() throws Exception
	{
		eval("[0][1]");
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_invalid_3() throws Exception
	{
		eval("[0][-2]");
	}

	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_str_invalid_1() throws Exception
	{
		eval("'ab'[2]");
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_str_invalid_2() throws Exception
	{
		eval("'a'[1]");
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void eval_index_str_invalid_3() throws Exception
	{
		eval("'a'[-2]");
	}

	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_int_indexed() throws Exception
	{
		eval("1[0]");
	}

	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_float_indexed() throws Exception
	{
		eval("1.[0]");
	}

	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_list_indexed_string() throws Exception
	{
		eval("[1]['a']");
	}
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_string_indexed_string() throws Exception
	{
		eval("'a'['a']");
	}
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_list_indexed_float() throws Exception
	{
		eval("[1][1.]");
	}
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_string_indexed_float() throws Exception
	{
		eval("'a'[1.]");
	}
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_list_indexed_list() throws Exception
	{
		eval("[1][[1]]");
	}
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_string_indexed_list() throws Exception
	{
		eval("'a'[[1]]");
	}

	@Test public void list_plus_list() throws Exception
	{
		listEqual("[0,1,2] + [-1]", 0, 1, 2, -1);
		listEqual("[0,1,2] + []", 0, 1, 2);
	}
	@Test public void string_plus_string() throws Exception
	{
		equal("'abc' + 'd'", "abcd");
		equal("'abc' + ''", "abc");
	}
	@Test public void string_slice() throws Exception
	{
		equal("'abc'[1:]", "bc");
		equal("'abc'[-1:]", "c");		
		equal("'abcdef'[-1:]", "f");
		equal("'abcdef'[:-1]", "abcde");
		equal("'abcdef'[1:-1]", "bcde");
		equal("'abcdef'[-2:-1]", "e");
		equal("'abcdef'[0:]", "abcdef");
		equal("'abcdef'[1:4]", "bcd");
	}
	@Test public void string_slice_three() throws Exception
	{
		equal("'abcdef'[::2]", "ace");
		equal("'abcde'[1::2]", "bd");
		equal("'abcdef'[::-1]", "fedcba");
	}
	@Test public void string_slice_three_arguments_1() throws Exception
	{
		equal("'abcdef'[1::3]", "be");
	}
	@Test public void string_slice_three_arguments_2() throws Exception
	{
		equal("'abcdef'[::-2]", "fdb");
	}
	@Test public void string_slice_three_arguments_3() throws Exception
	{
		equal("'abcdef'[1::-2]", "b");
	}
	@Test public void string_slice_three_arguments_4() throws Exception
	{		
		equal("'abcdef'[-2::-2]","eca");
	}
	@Test public void list_slice() throws Exception
	{
		listEqual("[1,2,3][1:]", 2, 3);
		listEqual("[1,2,3][-1:]", 3);		
		listEqual("[1,2,3,4,5,6][-1:]", 6);
		listEqual("[1,2,3,4,5,6][:-1]", 1, 2, 3, 4, 5);
		listEqual("[1,2,3,4,5,6][1:-1]", 2, 3, 4, 5);
		listEqual("[1,2,3,4,5,6][-2:-1]", 5);
		listEqual("[1,2,3,4,5,6][0:]", 1, 2, 3, 4, 5, 6);
		listEqual("[1,2,3,4,5,6][1:4]", 2, 3, 4);
	}
	@Test public void list_slice_three() throws Exception
	{
		listEqual("[1,2,3,4,5,6][::2]", 1, 3, 5);
		listEqual("[1,2,3,4,5][1::2]", 2,4);
		listEqual("[1,2,3,4,5,6][::-1]",6,5,4,3,2,1);
	}
	@Test public void list_slice_three_arguments_1() throws Exception
	{
		listEqual("[1,2,3,4,5,6][1::3]", 2, 5);
	}
	@Test public void list_slice_three_arguments_2() throws Exception
	{
		listEqual("[1,2,3,4,5,6][::-2]", 6,4,2);
	}
	@Test public void list_slice_three_arguments_3() throws Exception
	{
		listEqual("[1,2,3,4,5,6][1::-2]", 2);
	}
	@Test public void list_slice_three_arguments_4() throws Exception
	{		
		listEqual("[1,2,3,4,5,6][-2::-2]",5,3,1);
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void string_slice_outside_of_range() throws Exception
	{
		eval("'abcEF'[1:14]");
	}
	@Test(expected=ExpressionFault.IndexError.class)
	public void list_slice_outside_of_range() throws Exception
	{
		eval("[1,2,3,4,5][1:14]");
	}
	
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_list_plus_int() throws Exception
	{
		eval("[1] + 2");
	}
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_list_plus_float() throws Exception
	{
		eval("[1] + 2.");
	}
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_list_plus_string() throws Exception
	{
		eval("[1] + 'a'");
	}

	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_string_plus_int() throws Exception
	{
		eval("'1' + 2");
	}
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_string_plus_float() throws Exception
	{
		eval("'1' + 2.");
	}
	@Test(expected=ExpressionFault.TypeError.class)
	public void eval_string_plus_list() throws Exception
	{
		eval("'1' + [1]");
	}
	@Test public void eval_string() throws Exception
	{
		equal("'string'=='string'", 1);
		equal("'abecdef'", "abecdef");
	}
	@Test public void n_eval_string() throws Exception
	{
		equal("'string'!='string'", 0);
		equal("'abecdef'!='abecde'",1);
	}
	@Test public void eval_list() throws Exception
	{
		equal("[1,2,3,4,5]==[1,2,3,4,5]", 1);
		equal("[1,2,3,4,5]==[1,2,3,4]",0);
	}

	@Test public void n_eval_list() throws Exception
	{
		equal("[1,2,3]!=[1,2,3]", 0);
		equal("[1,2,3]!=[1,2,3,4]",1);
	}
	@Test public void greater_string() throws Exception
	{
		equal("'string'>'strinG'", 1);
		equal("'strin'>'strinG'", 0);
		equal("'a'>'b'",0);
		equal("'ą'>'a'",1);
	}
	@Test(expected=java.lang.AssertionError.class)
	public void greater_string_invalid() throws Exception
	{
		equal("'strin'>'strinG'",1);
	}
	@Test public void lesser_string() throws Exception
	{
		equal("'string'<'strinG'", 0);
		equal("'strin'<'strinG'", 1);
		equal("'a'<'b'",1);
		equal("'ą'<'a'",0);
	}
	@Test(expected=java.lang.AssertionError.class)
	public void lesser_string_invalid() throws Exception
	{
		equal("'strin'<'strinG'",0);
	}
	@Test public void greater_equal_string() throws Exception
	{
		equal("'string'>='strinG'", 1);
		equal("'strin'>='strinG'", 0);
		equal("'a'>='b'",0);
		equal("'ą'>='a'",1);
		equal("'a'>='a'",1);
		equal("'b'>='a'",1);
	}
	@Test(expected=java.lang.AssertionError.class)
	public void greater_equal_string_invalid() throws Exception
	{
		equal("'strin'>='strinG'",1);
		equal("'a'>='ala'",1);
		equal("'a'>='a'",0);
	}
	@Test public void lesser_equal_string() throws Exception
	{
		equal("'string'<='strinG'", 0);
		equal("'strin'<='strinG'", 1);
		equal("'a'<='b'",1);
		equal("'ą'<='a'",0);
		equal("'a'<='a'",1);
		equal("'b'<='a'",0);
	}
	@Test(expected=java.lang.AssertionError.class)
	public void lesser_equal_string_invalid() throws Exception
	{
		equal("'strin'<='strinG'",0);
		equal("'a'<='ala'",0);
		equal("'a'<='a'",0);
	}
	@Test public void greater_list() throws Exception
	{
		equal("[1,2,3,4,6]>[1,2,3,4,5]", 1);
		equal("[1,2,3,4]>[1,2,3,4,5]", 0);
		equal("[1]>[2]",0);
	}
	@Test(expected=java.lang.AssertionError.class)
	public void greater_list_invalid() throws Exception
	{
		equal("[1,2,3,4]>[1,2,3,4,5]",1);
	}
	@Test public void lesser_list() throws Exception
	{
		equal("[1,2,3,4,6]<[1,2,3,4,5]", 0);
		equal("[1,2,3,4]<[1,2,3,4,5]", 1);
		equal("[1]<[2]",1);
	}
	@Test(expected=java.lang.AssertionError.class)
	public void lesser_list_invalid() throws Exception
	{
		equal("[1,2,3,4]<[1,2,3,4,5]",0);
	}
	@Test public void greater_equal_list() throws Exception
	{
		equal("[1,2,3,4,6]>=[1,2,3,4,5]", 1);
		equal("[1,2,3,4]>=[1,2,3,4,5]", 0);
		equal("[1]>=[2]",0);
		equal("[1]>=[1]",1);
		equal("[2]>=[1]",1);
	}
	@Test(expected=java.lang.AssertionError.class)
	public void greater_equal_list_invalid() throws Exception
	{
		equal("[1,2,3,4]>=[1,2,3,4,5]",1);
		equal("[1]>=[1,2,3]",1);
		equal("[1]>=[1]",0);
	}
	@Test public void lesser_equal_list() throws Exception
	{
		equal("[1,2,3,4,6]<=[1,2,3,4,5]", 0);
		equal("[1,2,3,4]<=[1,2,3,4,5]", 1);
		equal("[1]<=[2]",1);
		equal("[1]<=[1]",1);
		equal("[2]<=[1]",0);
	}
	@Test(expected=java.lang.AssertionError.class)
	public void lesser_equal_list_invalid() throws Exception
	{
		equal("[1,2,3,4]<=[1,2,3,4,5]",0);
		equal("[1]<=[1,2,3]",0);
		equal("[1]<=[1]",0);
	}
	@Test public void multiplication_list() throws Exception
	{
		listEqual("[1,2]*3",1,2,1,2,1,2);
		listEqual("[1]*4",1,1,1,1);
	}
	@Test public void multiplication_string() throws Exception
	{
		equal("'a'*5","'aaaaa'");
		equal("'asia'*3","'asiaasiaasia'");
	}
	@Test public void multiplication_list_commutativity() throws Exception
	{
		listEqual("3*[1,2]",1,2,1,2);
		listEqual("3*[3]",3,3,3);
	}
	@Test public void multiplication_string_commutativity() throws Exception
	{
		equal("3*'asia'","'asiaasiaasia'");
		equal("5*'a'","'aaaaa'");
	}
	@Test public void addition_list() throws Exception
	{
		listEqual("[1,2,3]+[4,5]",1,2,3,4,5);
		listEqual("[4,5]+[1,2,3]",4,5,1,2,3);
	}
	@Test public void addition_string() throws Exception
	{
		equal("'gugu'+'bubu'","'gugububu'");
		equal("'bubu'+'gugu'","'bubugugu'");
	}
	@Test public void and_string() throws Exception
	{
		equal("'gugu' and 'bubu'","'bubu'");
	}
	@Test public void and_string_1() throws Exception
	{
		equal("'' and ''","''");
	}
	@Test public void and_string_2() throws Exception
	{
		equal("'' and 'bubu'","''");
	}
	@Test public void and_string_3() throws Exception
	{
		equal("'bubu' and ''","''");
	}
	@Test public void or_string() throws Exception
	{
		equal("'gugu' or 'bubu'","'gugu'");
	}
	@Test public void or_string_1() throws Exception
	{
		equal("'gugu' or ''","'gugu'");
	}
	@Test public void or_string_2() throws Exception
	{
		equal("'' or 'bubu'","'bubu'");
	}
	@Test public void or_string_3() throws Exception
	{
		equal("'' or ' '","' '");
	}
	@Test public void or_string_4() throws Exception
	{
		equal("'' or ''","''");
	}
	@Test public void and_list() throws Exception
	{
		listEqual("[1,2,3] and [2,3,4]",2,3,4);
	}
	@Test public void and_list_1() throws Exception
	{
		equal("[] and []","[]");
	}
	@Test public void and_list_2() throws Exception
	{
		equal("[] and [1,2,3]","[]");
	}
	@Test public void and_list_3() throws Exception
	{
		equal("[1,2,3] and []","[]");
	}
	@Test public void or_list() throws Exception
	{
		equal("[1,2,3,4] or [5,6,7]","[1,2,3,4]");
	}
	@Test public void or_list_1() throws Exception
	{
		equal("[1,2,3,4] or []","[1,2,3,4]");
	}
	@Test public void or_list_2() throws Exception
	{
		listEqual("[] or [1,2,3]",1,2,3);
	}
	@Test public void or_list_3() throws Exception
	{
		equal("[] or []","[]");
	}
	@Test public void or_list_4() throws Exception
	{
		equal("[] or [1]","[1]");
	}
}
