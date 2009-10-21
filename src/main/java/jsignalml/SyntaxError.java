package jsignalml;

import org.antlr.runtime.RecognitionException;

public class SyntaxError extends Exception {
    public SyntaxError(RecognitionException cause){
	super(cause);
    }

    static class RuntimeFlavour extends RuntimeException {
	final RecognitionException cause;

	RuntimeFlavour(RecognitionException cause){
	    this.cause = cause;
	}

	SyntaxError unrunify(){
	    return new SyntaxError(this.cause);
	}
    }
}
