package org.signalml.jsignalml.sexpression;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class Processor {
    public static void processFile(CallHelper state, String path)
	throws java.io.FileNotFoundException,
	       java.io.IOException,
	       org.antlr.runtime.RecognitionException
    {
	CommonTree ast =
	    parseScript(new FileReader(path));
	// TODO:  this.processNonInteractive();
    }

    public static CommonTokenStream getTokenStream(Reader reader)
	throws java.io.IOException
    {
	SExpressionLexer lexer
	    = new SExpressionLexer(new ANTLRReaderStream(reader));
	return new CommonTokenStream(lexer);
    }

    public static CommonTokenStream getTokenStream(String line)
    {
	SExpressionLexer lexer
	    = new SExpressionLexer(new ANTLRStringStream(line));
	return new CommonTokenStream(lexer);
    }

    public static CommonTree parseScript(Reader reader)
	throws org.antlr.runtime.RecognitionException,
	       java.io.IOException
    {
	SExpressionParser parser =
	    new SExpressionParser(getTokenStream(reader));
	SExpressionParser.script_return result =
	    parser.script();
	reader.close();
	return (CommonTree) result.getTree();
    }

    public static CommonTree parseLine(String line)
	throws org.antlr.runtime.RecognitionException
    {
	SExpressionParser parser =
	    new SExpressionParser(getTokenStream(line));
	SExpressionParser.line_return result = parser.line();

	CommonTree ast = (CommonTree) result.getTree();
	if(ast==null)
	    throw new RecognitionException();

	return ast;
    }

    public static Expression processLine(CommonTree ast)
	throws org.antlr.runtime.RecognitionException
    {
	STree tree = new STree(new CommonTreeNodeStream(ast));
	Expression expr = tree.line();
	if(expr == null)
	    throw new org.antlr.runtime.RecognitionException();
	return expr;
    }

    public static void processInteractive(CallHelper state,
					  InputStream in)
    {
	Scanner scanner = new Scanner(in);

	while(true){
	    System.out.print("expr> ");
	    String line;
	    try {
		line = scanner.nextLine().trim();
	    } catch(NoSuchElementException e){
		// EOF
		break;
	    }
	    if("q".equals(line))
		break;

	    CommonTree ast;
	    try {
		ast = parseLine(line);
	    } catch(RecognitionException e){
		System.out.println(e);
		continue;
	    }
	    System.out.format("tree: %s\n", ast.toStringTree());

	    Expression expr;
	    try{
		expr = processLine(ast);
	    }catch(RecognitionException e){
		System.out.println(e);
		continue;
	    }
	    System.out.format("expr: %s\n", expr);

	    try {
		Type value = expr.eval(state);
		System.out.format("----> %s\n",
              		     value == null ? "null" : value.repr());
	    } catch(ExpressionFault e) {
		    System.out.println(e);
	    }
	}
    }

    /////////////////////////////////////////////////////////
    public static class State implements CallHelper {
	Map<String, Expression> vars =
	    new TreeMap<String, Expression>();
	public void assign(String id, Expression expr)
	{
	    this.vars.put(id, expr);
	}

	public Type call(String id, List<Type> args)
	    throws ExpressionFault
	{
	    Expression expr = vars.get(id);
	    if(expr==null)
		throw new ExpressionFault.NameError(id);
	    else
		return expr.eval(this);
	}
    }

    public static void main(String ... args)
	throws java.io.IOException, 
	       java.io.FileNotFoundException,
	       org.antlr.runtime.RecognitionException
    {
	CallHelper state = new State();
	if(args.length == 0){
	    processInteractive(state, System.in);
	} else {
	    for(String path: args)
		processFile(state, path);
	}
    }
}
