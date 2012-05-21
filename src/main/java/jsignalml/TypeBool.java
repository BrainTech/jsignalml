package jsignalml;

import java.math.BigInteger;

/**
 * Represents boolean type object in SignalML.
 *
 * @author Jakub.Halun@ericpol.com
 */
public class TypeBool extends Type {

	/**
	 * Static reference
	 */
	public static final TypeBool I = new TypeBool();

	/**
	 * Value
	 */
	private final Boolean value;

	/**
	 * Constructs instance of class <code>TypeBool</code> based on integer
	 * value. Zero is <code>False</code>, any other value is <code>True</code>.
	 *
	 * @param longValue
	 *            integer value
	 */
	public TypeBool(long longValue) {
		if (longValue == 0) {
			value = false;
		} else {
			value = true;
		}
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> based on integer
	 * value. Zero is <code>False</code>, any other value is <code>True</code>.
	 *
	 * @param intValue
	 *            integer value
	 */
	public TypeBool(TypeInt intValue) {
		if (intValue.getValue().equals(BigInteger.ZERO)) {
			value = false;
		} else {
			value = true;
		}
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> based on float value.
	 * Zero is <code>False</code>, any other value is <code>True</code>.
	 *
	 * @param doubleVal
	 *            float value
	 */
	public TypeBool(double doubleVal) {
		if (doubleVal == 0) {
			value = false;
		} else {
			value = true;
		}
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> based on float value.
	 * Zero is <code>False</code>, any other value is <code>True</code>.
	 *
	 * @param floatVal
	 *            float value
	 */
	public TypeBool(TypeFloat floatVal) {
		this(floatVal.getValue());
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> directly from a given
	 * boolean value.
	 *
	 * @param value
	 *            boolean value
	 */
	public TypeBool(boolean value) {
		this.value = value;
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> based on parsing of
	 * string. Every string which matches "true" (regardless of lower case/upper
	 * case letters) gives <code>True</code>, any other gives <code>False</code>
	 * value.
	 *
	 * @param strValue
	 *            string value for parsing
	 */
	public TypeBool(String strValue) {
		this.value = Boolean.valueOf(strValue);
	}

	/**
	 * Constructs instance of class <code>TypeBool</code> based on parsing of
	 * string. Every string which matches "true" (regardless of lower case/upper
	 * case letters) gives <code>True</code>, any other gives <code>False</code>
	 * value.
	 *
	 * @param strValue
	 *            string value for parsing
	 */
	public TypeBool(TypeString strValue) {
		this(strValue.getValue());
	}

	/**
	 * Constructs default boolean object, with <code>False</code> value.
	 */
	public TypeBool() {
		value = false;
	}

	/**
	 * Compares this <code>TypeBool</code> instance with another.
	 * <code>this</code> object.
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
	 * <code>this</code> object.
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
	 * @return the other object reference if the type of the object is
	 *         <code>TypeBool</code>; new instance of <code>TypeBool</code>
	 *         value based on other object if the other is <code>TypeInt</code>,
	 *         <code>TypeFloat</code> or <code>TypeString</code>.
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
		}
		throw new ExpressionFault.TypeError(other, this);
	}

	/**
	 * Returns value of <code>this</code> object
	 *
	 * @return <code>this</code> object value
	 */
	@Override
	public Boolean getValue() {
		return value;
	}

	/**
	 * @see jsignalml.Type#_binaryOpType(jsignalml.Type.BinaryOp, jsignalml.TypeInt)
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
		return value;
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
	 * @see jsignalml.Type#add(jsignalml.Type)
	 */
	@Override
	public Type add(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "add");
	}

	/**
	 * @see jsignalml.Type#sub(jsignalml.Type)
	 */
	@Override
	public Type sub(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "sub");
	}

	/**
	 * @see jsignalml.Type#mul(jsignalml.Type)
	 */
	@Override
	public Type mul(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "mul");
	}

	/**
	 * @see jsignalml.Type#div(jsignalml.Type)
	 */
	@Override
	public Type div(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "div");
	}

	/**
	 * @see jsignalml.Type#floordiv(jsignalml.Type)
	 */
	@Override
	public Type floordiv(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "floordiv");
	}

	/**
	 * @see jsignalml.Type#mod(jsignalml.Type)
	 */
	@Override
	public Type mod(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "mod");
	}

	/**
	 * Performs AND operation on <code>this</code> object and other instance of
	 * <code>TypeBool</code> class.
	 *
	 * @param other
	 *            other instance of <code>TypeBool</code> class
	 * @return AND operation result
	 * @throws ExpressionFault.TypeError
	 *             if other instance is not of class <code>TypeBool</code>
	 */
	@Override
	public Type bin_and(Type other) {
		if (other instanceof TypeBool) {
			return this.bin_and((TypeBool) other);
		}
		throw new ExpressionFault.TypeError(other, this);
	}

	/**
	 * Performs AND operation on <code>this</code> object and other instance of
	 * <code>TypeBool</code> class.
	 *
	 * @param other
	 *            other instance of <code>TypeBool</code> class
	 * @return AND operation result
	 */
	public Type bin_and(TypeBool other) {
		return new TypeBool(this.value && other.value);
	}

	/**
	 * Performs OR operation on <code>this</code> object and other instance of
	 * <code>TypeBool</code> class.
	 *
	 * @param other
	 *            other instance of <code>TypeBool</code> class
	 * @return OR operation result
	 * @throws ExpressionFault.TypeError
	 *             if other instance is not of class <code>TypeBool</code>
	 */
	public Type bin_or(Type other) {
		if (other instanceof TypeBool) {
			return this.bin_or((TypeBool) other);
		}
		throw new ExpressionFault.TypeError(other, this);
	}

	/**
	 * Performs OR operation on <code>this</code> object and other instance of
	 * <code>TypeBool</code> class.
	 *
	 * @param other
	 *            other instance of <code>TypeBool</code> class
	 * @return OR operation result
	 */
	public Type bin_or(TypeBool other) {
		return new TypeBool(this.value || other.value);
	}

	/**
	 * Performs XOR operation on <code>this</code> object and other instance of
	 * <code>TypeBool</code> class.
	 *
	 * @param other
	 *            other instance of <code>TypeBool</code> class
	 * @return XOR operation result
	 * @throws ExpressionFault.TypeError
	 *             if other instance is not of class <code>TypeBool</code>
	 */
	@Override
	public Type bin_xor(Type other) {
		if (other instanceof TypeBool) {
			return this.bin_xor((TypeBool) other);
		}
		throw new ExpressionFault.TypeError(other, this);
	}

	/**
	 * Performs XOR operation on <code>this</code> object and other instance of
	 * <code>TypeBool</code> class.
	 *
	 * @param other
	 *            other instance of <code>TypeBool</code> class
	 * @return XOR operation result
	 */
	public Type bin_xor(TypeBool other) {
		return new TypeBool(this.value ^ other.value);
	}

	/**
	 * @see jsignalml.Type#pow(jsignalml.Type)
	 */
	@Override
	public Type pow(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "pow");
	}

	/**
	 * @see jsignalml.Type#pos()
	 */
	@Override
	public Type pos() {
		throw new ExpressionFault.Unsupported(this.getClass(), "pos");
	}

	/**
	 * Return new <code>TypeBool</code> instance with a reversed value of
	 * <code>this</code>.
	 *
	 * @return <code>TypeBool</code> instance with a reversed value.
	 */
	@Override
	public Type neg() {
		return new TypeBool(!value.booleanValue());
	}

	/**
	 * Return new <code>TypeBool</code> instance with a reversed value of
	 * <code>this</code>.
	 *
	 * @return <code>TypeBool</code> instance with a reversed value.
	 */
	@Override
	public Type bin_neg() {
		return new TypeBool(!value.booleanValue());
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return (this.getValue() != null) ? this.getValue().toString() : "null";
	}
}
