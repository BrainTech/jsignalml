package jsignalml;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sun.codemodel.JFormatter;

import jline.ConsoleReader;

import org.apache.log4j.BasicConfigurator;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.runtime.RecognitionException;

import com.sun.codemodel.JClassAlreadyExistsException;

public class Processor {
	static Logger log = new Logger(Processor.class);

	public static CommonTree processFile(Frame frame, String path)
		throws java.io.FileNotFoundException,
		       java.io.IOException,
		       SyntaxError
	{
		CommonTree ast = parseScript(new FileReader(path));
		// TODO:  this.processNonInteractive();
		return ast;
	}

	public static CommonTokenStream getTokenStream(java.io.Reader reader)
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

	public static CommonTree parseScript(java.io.Reader reader)
		throws java.io.IOException
	{
		SExpressionParser parser =
		        new SExpressionParser(getTokenStream(reader));

		SExpressionParser.script_return result;
		try {
			result = parser.script();
		} catch (RecognitionException e) {
			throw new SyntaxError(e);
		}
		reader.close();
		return (CommonTree) result.getTree();
	}

	public static CommonTree parseLine(String line)
	{
		SExpressionParser parser =
		        new SExpressionParser(getTokenStream(line));
		SExpressionParser.line_return result;

		try {
			result = parser.line();
		} catch (RecognitionException e) {
			throw new SyntaxError(e);
		}

		CommonTree ast = (CommonTree) result.getTree();
		return ast;
	}

	public static CommonTree parseSingleexpr(String line)
		throws SyntaxError
	{
		SExpressionParser parser =
		        new SExpressionParser(getTokenStream(line));
		SExpressionParser.singleexpr_return result;

		try {
			result = parser.singleexpr();
		} catch (RecognitionException e) {
			throw new SyntaxError(e);
		}

		CommonTree ast = (CommonTree) result.getTree();
		return ast;
	}

	public static Expression processLine(CommonTree ast)
	{
		final STree tree = new STree(new CommonTreeNodeStream(ast));
		final Expression expr;
		try {
			expr = tree.line();
		} catch (RecognitionException e) {
			throw new SyntaxError(e);
		}

		return expr;
	}

	public static void processInteractive(Frame frame)
		throws java.io.IOException
	{
		ConsoleReader console  = new ConsoleReader();

		while (true) {
			String line = console.readLine("expr> ");
			if(line == null) {
				// EOF
				System.out.println(); // We've already written the prompt,
				                      // so we should print a newline.
				break;
			}
			line = line.trim();
			if (line.isEmpty())
				continue;

			final CommonTree ast;
			try {
				ast = parseLine(line);
			} catch (SyntaxError e) {
				log.exception("parsing line", e);
				continue;
			}
			System.out.format("tree: %s\n", ast.toStringTree());

			final Expression expr;
			try {
				expr = processLine(ast);
			} catch (SyntaxError e) {
				log.exception("transform ast to Expression tree", e);
				continue;
			}
			System.out.format("expr: %s\n", expr);

			final ASTNode.ExprParam param =
				new ASTNode.ExprParam(frame.node, "expr", null, expr);

			try {
				final Type type = expr.accept(new TypeVisitor(frame));
				System.out.format("type: %s\n", Type.typename(type) );
			} catch (ExpressionFault e) {
				log.exception("type checking", e);
			}

			try {
				final Type value = expr.accept(new EvalVisitor(frame));
				System.out.format("----> %s\n",
				                  value == null ? "null" : value.repr());
			} catch (ExpressionFault e) {
				log.exception("evaluation", e);
			}

			final PrintWriter pw = new PrintWriter(System.out);
			final JFormatter code = new JFormatter( pw );
			final DynamicJavaNameResolver res = new DynamicJavaNameResolver(frame);
			final JavaExprGen javagen = res.createExprGen();
			try {
				code.p("code: ").g(expr.accept(javagen)).nl();
				pw.flush();
			} catch (ExpressionFault e) {
				log.exception("code generation:", e);
			}
		}
	}

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

		final ASTNode.Signalml root = new ASTNode.Signalml("root");
		final Frame frame = new Frame(root);
		if (args.length == 0) {
			processInteractive(frame);
		} else {
			for (String path: args)
				processFile(frame, path);
		}
	}

	public static Expression parse(String line)
		throws SyntaxError
	{
		try {
			return Processor.processLine(Processor.parseSingleexpr(line));
		} catch(SyntaxError e) {
			log.error("failed to parse '%s'", line);
			throw e;
		}
	}
}
