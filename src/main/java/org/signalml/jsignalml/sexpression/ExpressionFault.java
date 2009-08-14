package org.signalml.jsignalml.sexpression;

public abstract class ExpressionFault extends Exception {

    static class TypeError extends ExpressionFault {
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
