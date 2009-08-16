package org.signalml.jsignalml.sexpression;

public abstract class ExpressionFault extends Exception {

    static class TypeError extends ExpressionFault {
    }

    static class UnknownOperationError extends RuntimeException {
	public final Class<?> where;
	public final int opcode;
	public UnknownOperationError(Class<?> where, int opcode){
	    this.where = where;
	    this.opcode = opcode;
	}
    }

    static class NameError extends ExpressionFault {
	public final String name;
	public NameError(java.lang.String name){
	    this.name = name;
	}
    }

    static class IndexError extends ExpressionFault {
    }
}
