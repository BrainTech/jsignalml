package org.signalml.jsignalml.sexpression;

import org.signalml.jsignalml.Machine.MachineError;

public class ExpressionFault extends Exception {

    public static class TypeError extends ExpressionFault {
    }

    static class UnknownOperationError extends RuntimeException {
	public final Class<?> where;
	public final int opcode;
	public UnknownOperationError(Class<?> where, int opcode){
	    this.where = where;
	    this.opcode = opcode;
	}
    }

    public static class NameError extends ExpressionFault {
	public final String name;
	public NameError(java.lang.String name){
	    this.name = name;
	}
    }

    public static class IndexError extends ExpressionFault {
    }

    public static class ArgMismatch extends ExpressionFault {
    }

    public static class CodecError extends ExpressionFault {
	final MachineError me;
	public CodecError(MachineError me){
	    this.me = me;
	}
    }
}
