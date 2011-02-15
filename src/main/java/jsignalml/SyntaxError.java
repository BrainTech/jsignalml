package jsignalml;

import org.antlr.runtime.RecognitionException;

public class SyntaxError extends RuntimeException {
	public SyntaxError(Exception cause) {
		super(cause);
	}

	public SyntaxError(String message) {
		super(message);
	}
}
