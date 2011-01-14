package jsignalml;

import org.antlr.runtime.RecognitionException;

public class SyntaxError extends Exception {
	public SyntaxError(Exception cause) {
		super(cause);
	}

	static class RuntimeFlavour extends RuntimeException {
		final Exception cause;

		RuntimeFlavour(RecognitionException cause) {
			this.cause = cause;
		}

		RuntimeFlavour(NumberFormatException cause) {
			this.cause = cause;
		}

		SyntaxError unrunify() {
			return new SyntaxError(this.cause);
		}
	}
}
