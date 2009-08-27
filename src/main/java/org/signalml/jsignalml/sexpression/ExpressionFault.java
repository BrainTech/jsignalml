package org.signalml.jsignalml.sexpression;

import org.signalml.jsignalml.Machine.MachineError;
import static java.lang.String.format;

public class ExpressionFault extends Exception {

    public static class TypeError extends ExpressionFault {
	public final Class<? extends Type> from, to;
	public TypeError(Class<? extends Type> from, Class<? extends Type> to){
	    this.from = from;
	    this.to = to;
	}

	@Deprecated public TypeError(){
	    this.from = this.to = Type.class;
	}

	public String toString(){
	    return format("%s(%s => %s)", getClass(), from, to);
	}
    }

    static class UnknownOperationError extends RuntimeException {
	public final Class<?> where;
	public final int opcode;
	public UnknownOperationError(Class<?> where, int opcode){
	    this.where = where;
	    this.opcode = opcode;
	}

	public String toString(){
	    return format("%s opcode=%d", where, opcode);
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
