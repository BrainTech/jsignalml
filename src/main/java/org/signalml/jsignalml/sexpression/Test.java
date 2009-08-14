package org.signalml.jsignalml.sexpression;

import java.io.*;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class Test {
    static class State implements CallHelper {
	public Type call(java.lang.String id, Object ... args)
	    throws ExpressionFault
	{
	    throw new ExpressionFault.NameError(id);
	}
    }

    public static void main(String[] args) throws Exception {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	CallHelper state = new State();
	while(true){
	    String line = in.readLine();

	    ANTLRStringStream input = new ANTLRStringStream(line);
	    SExpressionLexer lexer = new SExpressionLexer(input);
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    SExpressionParser parser = new SExpressionParser(tokens);
	    SExpressionParser.singleexpr_return r = parser.singleexpr();
	    if(r == null)
		continue;

	    System.out.format("ast: %s\n", r.tree.toStringTree());
	    System.out.print("--------------------\ntree: ");
	    CommonTreeNodeStream nodes = new CommonTreeNodeStream(r.getTree());
	    STree tree = new STree(nodes);
	    Expression expr = tree.expr();
	    System.out.println(tree==null ? "(null)" : expr);
	    if(tree == null)
		continue;

	    System.out.print("--------------------\nvalue: ");

	    try{
		Type val = expr.eval(state);
		System.out.println(val == null ? "null" : val.repr());
	    }catch(ExpressionFault e){
		System.out.println(e);
	    }

	    System.out.println("--------------------");
	    
	}
    }
}
