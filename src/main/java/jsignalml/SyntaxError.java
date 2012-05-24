package jsignalml;


public class SyntaxError extends RuntimeException {
	private static final long serialVersionUID = -3646909653347624066L;

	public SyntaxError(Exception cause) {
		super(cause);
	}

	public SyntaxError(String message) {
		super(message);
	}
}
