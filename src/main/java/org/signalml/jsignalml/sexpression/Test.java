package org.signalml.jsignalml.sexpression;

import java.io.*;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class Test {
    public static void main(String[] args) throws Exception {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	while(true){
	    String line = in.readLine();

	    ANTLRStringStream input = new ANTLRStringStream(line);
	    SExpressionLexer lexer = new SExpressionLexer(input);
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    SExpressionParser parser = new SExpressionParser(tokens);
	    SExpressionParser.singleexpr_return r = parser.singleexpr();

	    System.out.println(r.tree.toStringTree());
	    System.out.print("--------------------\ntree: ");

	    CommonTreeNodeStream nodes = new CommonTreeNodeStream(r.getTree());
	    STree tree = new STree(nodes);
	    Expression expr = tree.expr();
	    System.out.println(expr);
	    System.out.println("--------------------");
	}
    }
}
