package org.signalml.jsignalml.sexpression;

import static java.lang.String.format;


/**
 * An exception signyfing that an error occured during the execution
 * of an Expression.
 * This class is also used for errors in things that are not
 * expressions themselves, but are degenerate constants sometimes used
 * instead of expressions.
 */
public class ExpressionFault extends RuntimeException {
    ExpressionFault(){}
    ExpressionFault(Throwable cause){
	super(cause);
    }
    ExpressionFault(String message){
	super(message);
    }

    /**
     * Assignement is forbidden.
     */
    public static class AssignmentError extends ExpressionFault{}

    /**
     * Arguments passed to a function are wrong.
     */
    public static class ValueError extends ExpressionFault{
	public ValueError(String message){
	    super(message);
	}
    }

    /**
     * An object of wrong type was passed.
     */
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


    /**
     * Param with a given name was not found.
     */
    public static class NameError extends ExpressionFault {
	public final String name;
	public NameError(java.lang.String name){
	    this.name = name;
	}
    }

    /**
     * An out of bounds index.
     */
    public static class IndexError extends ExpressionFault {
	/* public final int index;
	   public IndexError(int index){
	       this.index = index;
	   }
	*/
    }

    /**
     * A wrong number of arguments was used.
     */
    public static class ArgMismatch extends ExpressionFault {
    }

    /**
     * Signifies an error detected by the environment in the course of
     * expression evalution.
     */
    public static class ExternalError extends ExpressionFault {
	public ExternalError(Throwable cause){
	    super(cause);
	}
    }
}
