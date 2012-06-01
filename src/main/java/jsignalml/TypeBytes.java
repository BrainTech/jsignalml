package jsignalml;

import java.util.Map;
import java.util.regex.Pattern;

import com.sun.codemodel.JExpression;
import com.sun.codemodel.JExpressionImpl;
import com.sun.codemodel.JFormatter;

/**
 * Represents bytes object type in SignalML. Object of this type stores
 * sequences of ASCII characters (0-255).
 *
 * @author Jakub.Halun@ericpol.com
 */
public class TypeBytes extends Type {

	/**
	 * Static reference
	 */
	public static final TypeBytes I = new TypeBytes();

	/**
	 * Constructs new instance of <code>TypeBytes</code> class with specified
	 * <code>ByteSequence</code> value.
	 *
	 * @param value
	 *            specified <code>ByteSequence</code> value
	 */
	public TypeBytes(ByteSequence value) {
		this.value = value;
	}

	/**
	 *
	 * Constructs new instance of <code>TypeBytes</code> with value based on
	 * specified <code>char</code> array
	 *
	 * @param chars
	 *            array of <code>char</code>
	 */
	public TypeBytes(char[] chars) {
		this.value = new ByteSequence(chars);
	}

	/**
	 *
	 * Constructs new instance of <code>TypeBytes</code> with value based on
	 * specified <code>String</code>
	 *
	 * @param s
	 *            specified <code>String</code>
	 */
	public TypeBytes(String s) {
		this.value = new ByteSequence(s);
	}

	/**
	 * Constructs new instance of <code>TypeBytes</code> with empty byte
	 * sequence.
	 *
	 */
	public TypeBytes() {
		value = new ByteSequence();
	}

	/**
	 * Value
	 */
	private final ByteSequence value;

	/**
	 * Compares this <code>TypeBytes</code> instance with another based on their
	 * <code>String<code> representation.
	 *
	 * @see String#compareTo(String)
	 *
	 * @param other
	 *            the <code>TypeBytes</code> instance to be compared
	 * @throws ExpressionFault.TypeError
	 *             - if the argument is not <code>TypeBytes</code> object
	 *
	 */
	@Override
	public int compareTo(Type other) {
		if (other instanceof TypeBytes) {
			return this.compareTo((TypeBytes) other);
		}
		throw new ExpressionFault.TypeError(other, this);
	}

	/**
	 * Compares this <code>TypeBytes</code> instance with another based on their
	 * <code>String<code> representation.
	 *
	 * @see String#compareTo(String)
	 *
	 * @param other
	 *            the <code>TypeBytes</code> instance to be compared
	 * @throws NullPointerException
	 *             - if the argument is null
	 */
	public int compareTo(TypeBytes other) {
		return this.value.compareTo(other.value);
	}

	/**
	 * Makes <code>TypeBytes</code> based on another <code>Type</code> instance.
	 *
	 * @param other
	 *            the other <code>Type</code> instance
	 * @return new instance of <code>TypeBytes</code> class
	 * @throws ExpressionFault.TypeError
	 *             in case the other object is of different class than
	 *             <code>TypeBool</code>, <code>TypeString</code>,
	 *             <code>TypeFloat</code> or <code>TypeInt</code>
	 *             <code>TypeBytes</code>,.
	 */
	@Override
	public Type make(Type value) {
		return new TypeBytes(value.getValue().toString());
	}

	/**
	 * Returns value of <code>this</code> object
	 *
	 * @return <code>this</code> object value
	 */
	@Override
	public ByteSequence getValue() {
		return value;
	}

	/**
	 *
	 * @see jsignalml.Type#_binaryOpType(jsignalml.Type.BinaryOp,
	 *      jsignalml.TypeInt)
	 */
	@Override
	public Type _binaryOpType(BinaryOp op, TypeInt other) {
		return this.binaryOp(op, new TypeInt(0));
	}

	@Override
	public boolean isTrue() {
		return this.value.length() > 0;
	}

	/**
	 *
	 * @see jsignalml.Type#repr()
	 */
	@Override
	public String repr() {
		return "B\"" + value + "\"";
	}

	/**
	 *
	 * @see jsignalml.Type#add(jsignalml.Type)
	 */
	@Override
	public Type add(Type b) {
		return new TypeBytes(value.toString() + b.toString());
	}

	/**
	 *
	 * @see jsignalml.Type#sub(jsignalml.Type)
	 */
	@Override
	public Type sub(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "sub");
	}

	/**
	 *
	 * @see jsignalml.Type#mul(jsignalml.Type)
	 */
	@Override
	public Type mul(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "mul");
	}

	/**
	 *
	 * @see jsignalml.Type#div(jsignalml.Type)
	 */
	@Override
	public Type div(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "div");
	}

	/**
	 *
	 * @see jsignalml.Type#floordiv(jsignalml.Type)
	 */
	@Override
	public Type floordiv(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "floordiv");
	}

	/**
	 *
	 * @see jsignalml.Type#mod(jsignalml.Type)
	 */
	@Override
	public Type mod(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "mod");
	}

	/**
	 *
	 * @see jsignalml.Type#bin_and(jsignalml.Type)
	 */
	@Override
	public Type bin_and(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_and");
	}

	/**
	 *
	 * @see jsignalml.Type#bin_or(jsignalml.Type)
	 */
	@Override
	public Type bin_or(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_or");
	}

	/**
	 *
	 * @see jsignalml.Type#bin_xor(jsignalml.Type)
	 */
	@Override
	public Type bin_xor(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_xor");
	}

	/**
	 *
	 * @see jsignalml.Type#pow(jsignalml.Type)
	 */
	@Override
	public Type pow(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "pow");
	}

	/**
	 *
	 * @see jsignalml.Type#pos()
	 */
	@Override
	public Type pos() {
		throw new ExpressionFault.Unsupported(this.getClass(), "pos");
	}

	/**
	 *
	 * @see jsignalml.Type#neg()
	 */
	@Override
	public Type neg() {
		throw new ExpressionFault.Unsupported(this.getClass(), "neg");
	}

	/**
	 *
	 * @see jsignalml.Type#bin_neg()
	 */
	@Override
	public Type bin_neg() {
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_neg");
	}

	/**
	 * Synchronize with SExpression.g:ESC_SEQ !
	 */
	public static final Map<Pattern, java.lang.String> escapePatterns;
	static {
		escapePatterns = util.newHashMap();
		escapePatterns.put(Pattern.compile(Pattern.quote("\\t")), "\t");
		escapePatterns.put(Pattern.compile(Pattern.quote("\\n")), "\n");
		escapePatterns.put(Pattern.compile(Pattern.quote("\\r")), "\r");
		escapePatterns.put(Pattern.compile(Pattern.quote("\\\"")), "\"");
		escapePatterns.put(Pattern.compile(Pattern.quote("\\'")), "'");
	}

	/**
	 *
	 * Prepares <code>TypeBytes</code> instance based on <i>bytes</i> literal.
	 *
	 * @param quoted
	 *            <i>bytes</i> literal
	 * @return new <code>TypeBytes</code> instance
	 */
	public static TypeBytes fromQuoted(java.lang.String quoted) {
		return new TypeBytes(unquote(quoted));
	}

	/**
	 * Removes quotes and the starting character B from the string representing
	 * <i>bytes</i> literal.
	 *
	 * @param quoted
	 *            string representing <i>bytes</i> literal
	 * @return string with removed quotes and starting character
	 */
	public static String unquote(final String quoted) {
		java.lang.String quoteless = quoted.substring(2, quoted.length() - 1);

		for (Map.Entry<Pattern, String> entry : escapePatterns.entrySet())
			quoteless = entry.getKey().matcher(quoteless)
					.replaceAll(entry.getValue());

		return quoteless;
	}

	public static class ByteSequence implements Comparable<ByteSequence> {

		/**
		 *
		 */
		private final char[] sequence;

		/**
		 *
		 * Creates new instance of <code>ByteSequence</code> with empty
		 * sequence.
		 *
		 */
		public ByteSequence() {
			sequence = new char[0];
		}

		/**
		 *
		 * Creates new instance of <code>ByteSequence</code> from specified char
		 * array.
		 *
		 * @param chars
		 *            specified char array
		 */
		public ByteSequence(char[] chars) {
			for (char ch : chars) {
				if (ch > 255) {
					StringBuilder sb = new StringBuilder(chars.length + 128);
					sb.append("Unreasonable value. Cannot use character ")
							.append(ch).append(" (#").append((int) ch)
							.append(") in byte sequence.\n")
							.append("Incorrect char sequence is: ")
							.append(String.copyValueOf(chars));
					throw new ExpressionFault.ValueError(sb.toString());
				}
			}
			this.sequence = chars;
		}

		/**
		 *
		 * Creates new instance of <code>ByteSequence</code> from specified
		 * string
		 *
		 * @param s
		 *            specified string
		 */
		public ByteSequence(String s) {
			this(s.toCharArray());
		}

		/**
		 * Returns length of the sequence
		 *
		 * @return length of the sequence
		 */
		public int length() {
			return sequence.length;
		}

		/**
		 *
		 * Creates code model expression from <code>ByteSequence</code> literal.
		 *
		 * @param s
		 *            <code>ByteSequence</code> literal
		 * @return code model expression
		 */
		public static JExpression lit(ByteSequence s) {
			return new JBytesLiteral(s);
		}

		/**
		 *
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return String.copyValueOf(sequence);
		}

		/**
		 *
		 * Compares two byte sequences based on their <code>String</code>
		 * representation.
		 *
		 * @param o
		 *            another <code>ByteSequence</code> object.
		 * @return result of the comparison
		 * @see String#compareTo(String)
		 */
		@Override
		public int compareTo(ByteSequence o) {
			return this.toString().compareTo(o.toString());
		}
	}

	/**
	 *
	 * Represents bytes literal in code model.
	 *
	 */
	public static class JBytesLiteral extends JExpressionImpl {

		/**
		 *
		 * Creates new literal based on <code>ByteSequence</code> value.
		 *
		 * @param what
		 *            <code>ByteSequence</code> value
		 */
		JBytesLiteral(ByteSequence what) {
			str = what.toString();
		}

		/**
		 *
		 * @see com.sun.codemodel.JGenerable#generate(com.sun.codemodel.JFormatter)
		 */
		@Override
		public void generate(JFormatter jformatter) {
			jformatter.p(str);

		}

		/**
		 * Object value
		 */
		public final String str;

	}

}
