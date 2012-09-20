package jsignalml;

import java.math.BigInteger;

import static java.lang.String.format;

/**
 * Represents boolean type object in SignalML.
 *
 * @author Jakub.Halun@ericpol.com
 */
public class TypeBool extends TypeInt {

	/**
	 * Static reference
	 */
	public static final TypeBool I = new TypeBool();

	/**
	 * Constructs instance of class <code>TypeBool</code> based on integer
	 * value. Zero is <code>False</code>, any other value is <code>True</code>.
	 *
	 * @param value  integer value
	 */
	public TypeBool(long value) {
		super(value != 0);
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> based on float value.
	 * Zero is <code>False</code>, any other value is <code>True</code>.
	 *
	 * @param value  float value
	 */
	public TypeBool(double value) {
		this(value != 0.0);
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> based on a String.
	 * Empty is <code>False</code>, non-empty is <code>True</code>.
	 *
	 * @param value  float value
	 */
	public TypeBool(String value) {
		this(!value.isEmpty());
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> from Type.
	 *
	 * @param value the value
	 */
	public TypeBool(Type value) {
		this(value.isTrue());
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> based on parsing of
	 * string. "True" is <code>true</code>, "False" is <code>false</code>.
	 *
	 * @param repr  string value to parse
	 */
	public TypeBool parse(String repr) {
		if (repr.equals("True"))
			return True;
		if (repr.equals("False"))
			return False;
		throw new ExpressionFault.ValueError(format(
				    "'{0}' is not a valid boolean", repr));
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> based on boolean value.
	 *
	 * @param value
	 *            float value
	 */
	public TypeBool(boolean value) {
		super(value);
	}

	/**
	 * Constructs default boolean object, with <code>False</code> value.
	 */
	public TypeBool() {
		this(false);
	}

	/**
	 * Compares this <code>TypeBool</code> instance with another.
	 *
	 * @param other
	 *            the <code>TypeBool</code> instance to be compared
	 * @return zero if this object represents the same boolean value as the
	 *         argument; a positive value if this object represents true and the
	 *         argument represents false; and a negative value if this object
	 *         represents false and the argument represents true
	 * @throws ExpressionFault.TypeError
	 *             - if the argument is not <code>TypeBool</code> object
	 *
	 */
	@Override
	public int compareTo(Type other) {
		if (other instanceof TypeBool) {
			return this.compareTo((TypeBool) other);
		}
		throw new ExpressionFault.TypeError(other, this);
	}

	/**
	 * Compares this <code>TypeBool</code> instance with another.
	 *
	 * @param other
	 *            the <code>TypeBool</code> instance to be compared
	 * @return zero if this object represents the same boolean value as the
	 *         argument; a positive value if this object represents true and the
	 *         argument represents false; and a negative value if this object
	 *         represents false and the argument represents true
	 * @throws NullPointerException
	 *             - if the argument is null
	 */
	public int compareTo(TypeBool other) {
		return this.value.compareTo(other.value);
	}

	/**
	 * Makes <code>TypeBool</code> based on another <code>Type</code> instance.
	 *
	 * @param other
	 *            the other <code>Type</code> instance
	 * @return new instance of <code>TypeBool</code> class
	 * @throws ExpressionFault.TypeError
	 *             in case the other object is of different class than
	 *             <code>TypeBool</code>, <code>TypeString</code>,
	 *             <code>TypeFloat</code> or <code>TypeInt</code>.
	 */
	@Override
	public TypeBool make(Type other) {
		if (other instanceof TypeBool) {
			return (TypeBool) other;
		} else if (other instanceof TypeInt) {
			return new TypeBool((TypeInt) other);
		} else if (other instanceof TypeString) {
			return new TypeBool(((TypeString) other).getValue());
		} else if (other instanceof TypeFloat) {
			return new TypeBool((TypeFloat) other);
		} else if (other instanceof TypeBytes) {
			return new TypeBool((TypeBytes) other);
		} else {
			throw new ExpressionFault.TypeError(other, this);
		}
	}

	/**
	 * @see jsignalml.Type#_binaryOpType(jsignalml.Type.BinaryOp,
	 *      jsignalml.TypeInt)
	 */
	@Override
	public Type _binaryOpType(BinaryOp op, TypeInt other) {
		throw new ExpressionFault.TypeError(other, this);
	}

	/**
	 * Allows to obtain information about this instance type.
	 *
	 * @see jsignalml.Type#_binaryOpType(jsignalml.Type.BinaryOp,
	 *      jsignalml.TypeInt)
	 *
	 * @return instance for type check
	 */
	@Override
	public Type _binaryOpType(BinaryOp op, TypeBool other) {
		return this.binaryOp(op, other);
	}

	/**
	 * Returns value of <code>this</code> object
	 *
	 * @return <code>this</code> object value
	 */
	@Override
	public boolean isTrue() {
		return this.value.signum() != 0;
	}

	/**
	 * Returns <code>String</code> representation of <code>this</code> object
	 *
	 * @return <code>String</code> representation
	 */
	@Override
	public String repr() {
		return String.valueOf(value);
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return (this.getValue() != null) ? this.getValue().toString() : "null";
	}
}
