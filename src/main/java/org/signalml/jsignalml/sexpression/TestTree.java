package org.signalml.jsignalml.sexpression;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class TestTree {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        SExpressionLexer lexer = new SExpressionLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SExpressionParser parser = new SExpressionParser(tokens);
        SExpressionParser.multiline_return r = parser.multiline();
	System.out.println(r.tree.toStringTree());
    }
}
