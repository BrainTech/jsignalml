package org.signalml.jsignalml.sexpression;

import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public abstract class Type {
    public abstract Object getValue();

    public abstract Type add(Type other) throws ExpressionFault.TypeError;
    public Type subtract(Type other){ return null; }

    public Type multiply(Type other){ return null; }
    public Type floordiv(Type other){ return null; }
    public Type truediv(Type other){ return null; }
    public Type modulo(Type other){ return null; }

    public Type binary_and(Type other){ return null; }
    public Type binary_or(Type other){ return null; }
    public Type binary_xor(Type other){ return null; }

    public Type power(Type other){ return null; }

    public Type index(Type sub)
	throws ExpressionFault.TypeError,
	       ExpressionFault.IndexError
    {
	    throw new ExpressionFault.TypeError();
    }

    public abstract boolean isTrue();
    public abstract java.lang.String repr();

    public Type logical_not(){
	if(this.isTrue())
	    return new Int(1);
	else
	    return new Int(0);
    }

    public static class Int extends Type {
	final int value;
	public Int(int value){
	    this.value = value;
	}
	public Int(java.lang.String text){
	    this(new Integer(text));
	}
	
	public Integer getValue(){
	    return this.value;
	}

	public boolean isTrue(){
	    return this.value != 0;
	}

	public java.lang.String repr(){
	    return java.lang.String.valueOf(this.value);
	}

	public Type add(Type other)
	    throws ExpressionFault.TypeError
	{
	    try {
		Int right = (Int)other;
		return new Int(this.value + right.value);
	    } catch(ClassCastException e){}

	    try {
		Float right = (Float)other;
		return new Float(this.value + right.value);
	    } catch(ClassCastException e){}

	    throw new ExpressionFault.TypeError();
	}
    }

    public static class Float extends Type {
	final double value;
	public Float(double value){
	    this.value = value;
	}
	public Float(java.lang.String text){
	    this(new Double(text));
	}
	
	public Double getValue(){
	    return this.value;
	}

	public boolean isTrue(){
	    return this.value != 0;
	}

	public java.lang.String repr(){
	    return java.lang.String.valueOf(this.value);
	}

	public Float add(Type other)
	    throws ExpressionFault.TypeError
	{
	    try {
		Int right = (Int)other;
		return new Float(this.value + right.value);
	    } catch(ClassCastException e){}

	    try {
		Float right = (Float)other;
		return new Float(this.value + right.value);
	    } catch(ClassCastException e){}

	    throw new ExpressionFault.TypeError();
	}
    }

    public static class String extends Type {
	final java.lang.String value;
	public String(java.lang.String value){
	    this.value = value;
	}
	public String(char value){
	    this.value = "" + value;
	}
	
	public java.lang.String getValue(){
	    return this.value;
	}

	public boolean isTrue(){
	    return this.value.length() > 0;
	}

	public java.lang.String repr(){
	    // FIXME: add quotes
	    return this.value;
	}

	public String add(Type other)
	    throws ExpressionFault.TypeError
	{
	    try {
		String right = (String)other;
		return new String(this.value + right.value);
	    } catch(ClassCastException e){}

	    throw new ExpressionFault.TypeError();
	}

	public Type index(Type sub)
	    throws ExpressionFault.TypeError,
		   ExpressionFault.IndexError
	{
	    try{
		Int ind = (Int) sub;
		char c = this.value.charAt(ind.value);
		return new String(c);
	    } catch(ClassCastException e){
	    } catch(IndexOutOfBoundsException e){
		throw new ExpressionFault.IndexError();
	    }

	    throw new ExpressionFault.TypeError();
	}

	/** Synchronize with SExpression.g:ESC_SEQ ! */
	public static final Map<Pattern, java.lang.String> escapePatterns;
	static {
	    escapePatterns = new HashMap<Pattern, java.lang.String>();
	    escapePatterns.put(Pattern.compile(Pattern.quote("\\t")),  "\t");
	    escapePatterns.put(Pattern.compile(Pattern.quote("\\n")),  "\n");
	    escapePatterns.put(Pattern.compile(Pattern.quote("\\r")),  "\r");
	    escapePatterns.put(Pattern.compile(Pattern.quote("\\\"")), "\"");
	    escapePatterns.put(Pattern.compile(Pattern.quote("\\'")),  "'");
	}

	public static String fromQuoted(java.lang.String quoted)
	{
	    return new String(unquote(quoted));
	}

	public static java.lang.String unquote(final java.lang.String quoted)
	{
            java.lang.String quoteless = quoted.substring(1, quoted.length()-1);

	    for(Map.Entry<Pattern,java.lang.String> entry: escapePatterns.entrySet())
		quoteless = 
		    entry.getKey().matcher(quoteless).replaceAll(entry.getValue());

	    return quoteless;
	}
    }
}
