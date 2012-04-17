package jsignalml;

import static java.lang.String.format;


/**
 * An exception signyfing that an error occured during the execution
 * of an Expression.
 * This class is also used for errors in things that are not
 * expressions themselves, but are degenerate constants sometimes used
 * instead of expressions.
 */
public class ExpressionFault extends RuntimeException {
	public ExpressionFault() {}
	public ExpressionFault(Throwable cause) {
		super(cause);
	}
	public ExpressionFault(String message) {
		super(message);
	}

	/**
	 * Assignement is forbidden.
	 */
	public static class AssignmentError extends ExpressionFault {}

	/**
	 * Arguments passed to a function are wrong.
	 */
	public static class ValueError extends ExpressionFault {
		public ValueError(String message) {
			super(message);
		}
	}

	/**
	 * An object of wrong type was passed.
	 */
	public static class TypeError extends ExpressionFault {
		public final Class<? extends Type> from, to;
		public TypeError(Class<? extends Type> from, Class<? extends Type> to) {
			this.from = from;
			this.to = to;
		}

		public TypeError(Type from, Type to) {
			this(from.getClass(), to.getClass());
		}

		@Deprecated public TypeError() {
			this.from = this.to = Type.class;
		}

		public String toString() {
			return format("%s(%s => %s)", getClass(), from, to);
		}
	}

	/**
	 * An object of wrong type was passed.
	 */
	public static class Unsupported extends ExpressionFault {
		public final Class<? extends Type> type;
		public final String operation;

		public Unsupported(Class<? extends Type> type, String operation) {
			this.type = type;
			this.operation = operation;
		}

		public String toString() {
			return format("Type %s does't support %s",
				      type, operation);
		}
	}

	/**
	 * Param with a given name was not found.
	 */
	public static class NameError extends ExpressionFault {
		public final String name;
		public NameError(java.lang.String name) {
			this.name = name;
		}

		public String getMessage() {
			return format("name '%s' not found", name);
		}
	}

	/**
	 * Attribute with a given name was not found.
	 */
	public static class AttributeError extends ExpressionFault {
		public final String object, name;
		public AttributeError(String object, String name) {
			this.object = object;
			this.name = name;
		}
		public AttributeError(String name) {
			this(null, name);
		}

		public String getMessage() {
			if (object != null)
				return format("%s has no param %s", object, name);
			else
				return format("attribute '%s' not found", name);
		}
	}

	/**
	 * An out of bounds index.
	 */
	public static class IndexError extends ExpressionFault {
		public final long index, limit;
		public IndexError(long index, long limit) {
			this.index = index;
			this.limit = limit;
		}

		public String getMessage() {
			return format("index %d bad, limit=%d", index, limit);
		}
	}

	public static class KeyError extends ExpressionFault {
		public final Type key;
		public KeyError(Type key) {
			this.key = key;
		}

		public String getMessage() {
			return format("key %s not found", this.key.repr());
		}
	}

	/**
	 * A wrong number of arguments was used.
	 */
	public static class ArgMismatch extends ExpressionFault {
		final int expected, present;
		@Deprecated public ArgMismatch() {
			this(-1, -1);
		}

		public ArgMismatch(int expected, int present) {
			this.expected = expected;
			this.present = present;
		}

		public String getMessage() {
			return format("wrong number of arguments, %d expected, %d actually present",
				      expected, present);
		}
	}

	/**
	 * No match for a given pattern was found.
	 */
	public static class NoMatchFoundError extends ExpressionFault {
		public final String text, pattern;
		public NoMatchFoundError(java.lang.String text, 
				java.lang.String pattern) {
			this.text = text;
			this.pattern = pattern;
		}

		public String getMessage() {
			return format("pattern '%s' not found in the text line '%s'", pattern, text);
		}
	}

	
	/**
	 * Signifies an error detected by the environment in the course of
	 * expression evalution.
	 */
	public static class ExternalError extends ExpressionFault {
		public ExternalError(Throwable cause) {
			super(cause);
		}
		public ExternalError(String message) {
			super(message);
		}
	}
}
