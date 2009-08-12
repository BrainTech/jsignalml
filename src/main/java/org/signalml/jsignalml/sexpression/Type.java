package org.signalml.jsignalml.sexpression;

public abstract class Type {
    public abstract Object getValue();

    public Type add(Type other){ return null; }
    public Type subtract(Type other){ return null; }

    public Type multiply(Type other){ return null; }
    public Type floordiv(Type other){ return null; }
    public Type truediv(Type other){ return null; }
    public Type modulo(Type other){ return null; }

    public Type binary_and(Type other){ return null; }
    public Type binary_or(Type other){ return null; }
    public Type binary_xor(Type other){ return null; }

    public Type power(Type other){ return null; }

    public abstract boolean isTrue();
    public abstract java.lang.String repr();

    public Type logical_not(){
	if(this.isTrue())
	    return new Int(1);
	else
	    return new Int(0);
    }

    static class Int extends Type {
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
    }

    static class Float extends Type {
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
    }

    static class String extends Type {
	final java.lang.String value;
	public String(java.lang.String value){
	    this.value = value;
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
    }
}
