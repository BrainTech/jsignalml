package org.signalml.jsignalml.sexpression;

import java.util.List;

//import org.antlr.runtime.tree.CommonTree;

public abstract class Expression {
    //    final CommonTree expr;

    //    public Expression(CommonTree expr){
    //	this.expr = expr;
    //    }

    public abstract Type eval(CallHelper state)
	throws ExpressionFault;

    static final String getTokenName(int op){
	return SExpressionParser.tokenNames[op];
    }

    static class BinaryOp extends Expression {
	final Expression left, right;
	final int op;

	public BinaryOp(int op, Expression left, Expression right){
	    this.op = op;
	    this.left = left;
	    this.right = right;
	}

	public Type eval(CallHelper state)
	    throws ExpressionFault
	{
	    Type left = this.left.eval(state);
	    Type right = this.right.eval(state);

	    switch(this.op){
	    case SExpressionParser.ADD:      return left.add(right);
	    case SExpressionParser.SUBTRACT: return left.subtract(right);

	    case SExpressionParser.MULTIPLY: return left.multiply(right);
	    case SExpressionParser.FLOORDIV: return left.floordiv(right);
	    case SExpressionParser.TRUEDIV:  return left.truediv(right);
	    case SExpressionParser.MODULO:   return left.modulo(right);

	    case SExpressionParser.BINARY_AND: return left.binary_and(right);
	    case SExpressionParser.BINARY_OR:  return left.binary_or(right);
	    case SExpressionParser.BINARY_XOR: return left.binary_xor(right);

	    case SExpressionParser.POWER:    return left.power(right);
	    default:
		assert false;
	    }

	    return null;
	}

	public String toString()
	{
	    return this.left.toString() + " " + getTokenName(this.op)
		+ " " + this.right;
	}
    }

    static class LogicalBinaryOp extends BinaryOp{
	public LogicalBinaryOp(int op, Expression left, Expression right){
	    super(op, left, right);
	}

	public Type eval(CallHelper state)
	    throws ExpressionFault
	{
	    Type left = this.left.eval(state);
	    switch(this.op){
	    case SExpressionParser.LOGICAL_AND:{
		if(!left.isTrue())
		    return left;
	    }
	    case SExpressionParser.LOGICAL_OR:{
		if(left.isTrue())
		    return left;
	    }
	    default:
		assert false;
	    }
	
	    Type right = this.right.eval(state);
	    return right;
	}
    }

    static class UnaryOp extends Expression {
	final int op;
	final Expression sub;
	
	public UnaryOp(int op, Expression sub){
	    this.op = op;
	    this.sub = sub;
	}
	
	public Type eval(CallHelper state)
	    throws ExpressionFault
	{
	    Type sub = this.sub.eval(state);
	    switch(this.op){
	    case SExpressionParser.LOGICAL_NOT: return sub.logical_not();
	    default:
		assert false;
	    }
	    
	    return null;
	}

	public String toString()
	{
	    return getTokenName(this.op) + " " + this.sub;
	}
    }

    static class Call extends Expression {
	final String name;
	final List<Expression> args;
	
	Call(String name, List<Expression> args){
	    this.name = name;
	    this.args = args;
	}
	
	public Type eval(CallHelper state)
	    throws ExpressionFault
	{
	    return state.call(this.name, this.args);
	}

	public String toString()
	{
	    String r = this.name + "(";

	    boolean first = true;
	    for(Expression arg: this.args){
		if(!first)
		    r += ", ";
		else
		    first = false;
		r += arg;
	    }
	    r += ")";
	    return r;
	}
    }

    static class Const extends Expression {
	public final Type value;

	public Const(Type value){
	    this.value = value;
	}

	public String toString()
	{
	    return this.value.repr();
	}

	public Type eval(CallHelper dummy)
	{
	    return this.value;
	}
    }
}
