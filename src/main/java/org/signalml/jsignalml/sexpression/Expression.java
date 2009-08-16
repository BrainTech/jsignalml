package org.signalml.jsignalml.sexpression;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public abstract class Expression {
    public abstract Type eval(CallHelper state)
	throws ExpressionFault;

    static class BinaryOp extends Expression {
	final Expression left, right;
	final Type.BinaryOp op;

	public BinaryOp(int opcode, Expression left, Expression right)
	    throws  ExpressionFault.UnknownOperationError
	{
	    this.op = Type.BinaryOp.get(opcode);
	    this.left = left;
	    this.right = right;
	}

	public Type eval(CallHelper state)
	    throws ExpressionFault
	{
	    Type left = this.left.eval(state);
	    Type right = this.right.eval(state);

	    return left.binaryOp(this.op, right);
	}

	public String toString()
	{
	    return String.format("%s %s %s",
				 left, op.rep, right);
	}
    }

    static class LogicalBinaryOp extends BinaryOp{
	public LogicalBinaryOp(int opcode, Expression left, Expression right)
	    throws ExpressionFault.UnknownOperationError
	{
	    super(opcode, left, right);
	}

	public Type eval(CallHelper state)
	    throws ExpressionFault
	{
	    Type left = this.left.eval(state);
	    switch(this.op){
	    case LOG_AND:{
		if(!left.isTrue())
		    return left;
	    }
	    case LOG_OR:{
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
	final Type.UnaryOp op;
	final Expression sub;
	
	public UnaryOp(int opcode, Expression sub)
	    throws  ExpressionFault.UnknownOperationError
	{
	    this.op = Type.UnaryOp.get(opcode);
	    this.sub = sub;
	}
	
	public Type eval(CallHelper state)
	    throws ExpressionFault
	{
	    Type sub = this.sub.eval(state);
	    switch(this.op){
	    case LOG_NOT: return sub.logical_not();
	    default:
		assert false;
	    }
	    
	    return null;
	}

	public String toString()
	{
	    return String.format("%s %s", op.rep, sub);
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
	    List<Type> vals = new LinkedList<Type>();
	    for(Expression arg: this.args){
		Type val = arg.eval(state);
		vals.add(val);
	    }
	    
	    return state.call(this.name, vals);
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

    static class Index extends Expression {
	final Expression item;
	final Expression index;

	Index(Expression item, Expression index){
	    this.item = item;
	    this.index = index;
	}
	
	public Type eval(CallHelper state)
	    throws ExpressionFault
	{
	    Type vitem = this.item.eval(state);
	    Type vindex = this.index.eval(state);
	    return vitem.index(vindex);
	}

	public String toString()
	{
	    return String.format("%s[ %s ]", item, index);
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

    static class Ternary extends Expression {
	public final Expression q, a, b;

	public Ternary(Expression q, Expression a, Expression b)
	{
	    this.q = q;
	    this.a = a;
	    this.b = b;
	}

	public Type eval(CallHelper state)
	    throws ExpressionFault
	{
	    Type qvalue = this.q.eval(state);
	    
	    Expression which = qvalue.isTrue() ? this.a : this.b;
	    Type value = which.eval(state);
	    return value;
	}

	public String toString()
	{
	    return String.format("if %s then %s else %s", q, a, b);
	}
    }

    /*
     * This is a helper expression node to be used in interactive 
     * expression script parsing and execution.
     */
    static class Assign extends Expression {
	public final String id;
	public final List<Argument> args;
	public final Expression value;

	// TODO: find a better home for this class
	public static class Argument {
	    public final Type type;
	    public final String name;
	    public Argument(Type type, String name){
		this.type = type;
		this.name = name;
	    }
	}

	// TODO: non-empty argument list
	public Assign(String id, List<Argument> args, Expression value){
	    this.id = id;
	    this.args = args;
	    this.value = value;

	    assert args.size() == 0;
	}

	public Assign(String id, Expression value){
	    this(id, new LinkedList<Argument>(), value);
	}

	public Type eval(CallHelper state)
	{
	    state.assign(this.id, this.value);
	    return null;
	}
    }
}
