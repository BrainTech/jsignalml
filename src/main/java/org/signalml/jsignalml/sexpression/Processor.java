package org.signalml.jsignalml.sexpression;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.BasicConfigurator;
import org.signalml.jsignalml.Logger;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import org.antlr.runtime.RecognitionException;

public class Processor {

    public static void processFile(CallHelper state, String path)
	throws java.io.FileNotFoundException,
	       java.io.IOException,
	       SyntaxError
    {
	CommonTree ast = parseScript(new FileReader(path));
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
	throws java.io.IOException, SyntaxError
    {
	SExpressionParser parser =
	    new SExpressionParser(getTokenStream(reader));

	SExpressionParser.script_return result;
	try{
	    result = parser.script();
	}catch(RecognitionException e){
	    throw new SyntaxError(e);
	}catch(SyntaxError.RuntimeFlavour e){
	    throw e.unrunify();
	}
	reader.close();
	return (CommonTree) result.getTree();
    }

    public static CommonTree parseLine(String line)
	throws SyntaxError
    {
	SExpressionParser parser =
	    new SExpressionParser(getTokenStream(line));
	SExpressionParser.line_return result;

	try{
	    result = parser.line();
	}catch(RecognitionException e){
	    throw new SyntaxError(e);
	}catch(SyntaxError.RuntimeFlavour e){
	    throw e.unrunify();
	}

	CommonTree ast = (CommonTree) result.getTree();
	return ast;
    }

    public static Expression processLine(CommonTree ast)
	throws SyntaxError
    {
	STree tree = new STree(new CommonTreeNodeStream(ast));
	Expression expr;
	try{
	    expr = tree.line();
	}catch(RecognitionException e){
	    throw new SyntaxError(e);
	}catch(SyntaxError.RuntimeFlavour e){
	    throw e.unrunify();
	}
	    
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
	    } catch(SyntaxError e){
		log.exception("parsing line", e);
		continue;
	    }
	    System.out.format("tree: %s\n", ast.toStringTree());

	    Expression expr;
	    try{
		expr = processLine(ast);
	    }catch(SyntaxError e){
		log.exception("transform ast to Expression tree", e);
		continue;
	    }
	    System.out.format("expr: %s\n", expr);

	    try {
		Type value = expr.eval(state);
		System.out.format("----> %s\n",
              		     value == null ? "null" : value.repr());
	    } catch(ExpressionFault e) {
		log.exception("Expression tree evaluation", e);
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

    static Logger log = new Logger(Processor.class);

    public static void main(String ... args)
	throws java.io.IOException, 
	       java.io.FileNotFoundException,
	       SyntaxError
    {
	BasicConfigurator.configure();
	log.info("lexer grammar %s",
		 new SExpressionLexer(null).getGrammarFileName());
	log.info("parser grammar %s",
		 new SExpressionParser(null).getGrammarFileName());
	log.info("tree grammar %s",
		 new STree(null).getGrammarFileName());

	CallHelper state = new State();
	if(args.length == 0){
	    processInteractive(state, System.in);
	} else {
	    for(String path: args)
		processFile(state, path);
	}
    }
}
