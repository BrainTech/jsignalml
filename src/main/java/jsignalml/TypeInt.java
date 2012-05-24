package jsignalml;

import java.math.BigInteger;

public class TypeInt extends Type {
	public static final TypeInt I = new TypeInt();
	public static final TypeInt ZERO = new TypeInt(BigInteger.ZERO);

	public final BigInteger value;

	public static final TypeInt False = new TypeInt(0);
	public static final TypeInt True = new TypeInt(1);

	public TypeInt(BigInteger value){
		this.value = value;
	}

	public TypeInt(long value) {
		this.value = BigInteger.valueOf(value);
	}

	public TypeInt(double value) {
		this((long)value);
		if(value > Long.MAX_VALUE || value < Long.MIN_VALUE)
			throw new ExpressionFault.ValueError("overflow");
	}

	public TypeInt(java.lang.String text) {

		int base = 10;
		text = text.trim();
		if (text.startsWith("+"))
			text = text.substring(1).trim();
		if (text.startsWith("0x")){
			text = text.substring(2);
			base = 16;
		} else if (text.startsWith("0o")){
			text = text.substring(2);
			base = 8;
		} else if (text.startsWith("0b")){
			text = text.substring(2);
			base = 2;
		}

		try {
			this.value = new BigInteger(text, base);
		} catch (NumberFormatException e) {
			throw new SyntaxError(e);
		}
	}

	public TypeInt(boolean value) {
		this(value?1:0);
	}

	public TypeInt() {
		this(0);
	}

	public TypeInt(TypeBool value) {
		this(value.getValue());
	}

	@Override
	public TypeInt make(Type other) {
		if (other instanceof TypeInt)
			return (TypeInt)other;
		else if (other instanceof TypeFloat)
			return new TypeInt(((TypeFloat)other).value);
		else if (other instanceof TypeString)
			return new TypeInt(((TypeString)other).value);
		else if (other instanceof TypeBool)
			return new TypeInt(((TypeBool) other).getValue());
		else
			throw new ExpressionFault.TypeError(other, this);
	}

	static TypeInt makeFromUnsignedReadAsSigned(long value) {
		TypeInt ivalue = new TypeInt(value);
		log.info("converting long %s", value);
		if (ivalue.compareTo(ZERO) >= 0)
			return ivalue;
		// TODO: add tests!!!
		return new TypeInt(ivalue.value.add(BigInteger.valueOf(Long.MIN_VALUE)
						.multiply(BigInteger.valueOf(-2))));
	}

	static TypeInt makeFromUnsignedReadAsSigned(int value) {
		TypeInt ivalue = new TypeInt(value);
		log.info("converting int %s", value);
		if (ivalue.compareTo(ZERO) >= 0)
			return ivalue;
		// TODO: add tests!!!
		return new TypeInt(ivalue.value.add(BigInteger.valueOf(Integer.MIN_VALUE)
						.multiply(BigInteger.valueOf(-2))));
	}

	static TypeInt makeFromUnsignedReadAsSigned(short value) {
		TypeInt ivalue = new TypeInt(value);
		log.info("converting short %s", value);
		if (ivalue.compareTo(ZERO) >= 0)
			return ivalue;
		// TODO: add tests!!!
		return new TypeInt(ivalue.value.add(BigInteger.valueOf(Short.MIN_VALUE)
						.multiply(BigInteger.valueOf(-2))));
	}

	static TypeInt makeFromUnsignedReadAsSigned(byte value) {
		TypeInt ivalue = new TypeInt(value);
		log.info("converting byte %s", value);
		if (ivalue.compareTo(ZERO) >= 0)
			return ivalue;
		// TODO: add tests!!!
		return new TypeInt(ivalue.value.add(BigInteger.valueOf(Byte.MIN_VALUE)
						.multiply(BigInteger.valueOf(-2))));
	}

	@Override
	public BigInteger getValue() {
		return this.value;
	}

	@Override
	public boolean isTrue() {
		return this.compareTo(ZERO) != 0;
	}

	@Override
	public java.lang.String repr() {
		return java.lang.String.valueOf(this.value);
	}


	@Override
	public Type binaryOp(BinaryOp op, TypeString other)
	{
		switch (op) {
		case MUL:
			return other.binaryOp(op, this);
		default:
			throw new ExpressionFault.TypeError(other, this);
		}
	}

	@Override
	public Type binaryOp(BinaryOp op, TypeList other)
	{
		switch (op) {
		case MUL:
			return other.binaryOp(op, this);
		default:
			throw new ExpressionFault.TypeError(other, this);
		}
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeInt other)
	{
		/* 1 should be safe for all ops */
		return this.binaryOp(op, new TypeInt(1));
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeFloat other)
	{
		/* 1 should be safe for all ops */
		return this.binaryOp(op, new TypeFloat(1.0));
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeString other)
	{
		return other.binaryOpType(op, this);
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeList other)
	{
		return other.binaryOpType(op, this);
	}


	@Override
	public Type unaryOp(UnaryOp op)
		throws ExpressionFault.Unsupported
	{
		switch (op) {
		case POS:
			return this;
		case NEG:
			return new TypeInt(this.value.negate());
		default:
			throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
		}
	}

	public Type add(Type other){
		if(other instanceof TypeInt)
			return this.add((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.add((TypeFloat)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeInt add(TypeInt other){
		return new TypeInt(this.value.add(other.value));
	}
	public TypeFloat add(TypeFloat other){
		return new TypeFloat(this.value.doubleValue() + other.value);
	}

	public Type sub(Type other){
		if(other instanceof TypeInt)
			return this.sub((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.sub((TypeFloat)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeInt sub(TypeInt other){
		return new TypeInt(this.value.subtract(other.value));
	}
	public TypeInt sub(BigInteger other){
		return new TypeInt(this.value.subtract(other));
	}
	public TypeFloat sub(TypeFloat other){
		return new TypeFloat(this.value.doubleValue() - other.value);
	}

	public Type mul(Type other){
		if(other instanceof TypeInt)
			return this.mul((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.mul((TypeFloat)other);
		if(other instanceof TypeString)
			return this.mul((TypeString)other);
		if(other instanceof TypeList)
			return this.mul((TypeList)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeInt mul(TypeInt other){
		return new TypeInt(this.value.multiply(other.value));
	}
	public TypeFloat mul(TypeFloat other){
		return new TypeFloat(this.value.doubleValue() * other.value);
	}
	public TypeString mul(TypeString other){
		return other.mul(this);
	}
	public TypeList mul(TypeList other){
		return other.mul(this);
	}

	public TypeFloat div(Type other){
		if(other instanceof TypeInt)
			return this.div((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.div((TypeFloat)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeFloat div(TypeInt other){
		return new TypeFloat(this.value.doubleValue() / other.value.doubleValue());
	}
	public TypeFloat div(TypeFloat other){
		return new TypeFloat(this.value.doubleValue() / other.value);
	}

	public TypeInt floordiv(Type other){
		if(other instanceof TypeInt)
			return this.floordiv((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.floordiv((TypeFloat)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeInt floordiv(TypeInt other){
		return make(this.sub(this.mod(other)).div(other));
	}
	public TypeInt floordiv(TypeFloat other){
		return new TypeInt(this.value.doubleValue() / other.value);
	}

	public Type mod(Type other){
		if(other instanceof TypeInt)
			return this.mod((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.mod((TypeFloat)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeInt mod(TypeInt other){
		if(other.compareTo(ZERO) < 0)
			// BigInteger doesn't allow negative factor.
			// Nevertheless, -x mod -y == x mod y.
			return new TypeInt(this.value.negate()
				       .mod(other.value.negate()).negate());
		return new TypeInt(this.value.mod(other.value));
	}
	public TypeFloat mod(TypeFloat other){
		return new TypeFloat(this.value.doubleValue()).mod(other);
	}

	public Type bin_and(Type other){
		if(other instanceof TypeInt)
			return this.bin_and((TypeInt)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeInt bin_and(TypeInt other){
		return new TypeInt(this.value.and(other.value));
	}

	public Type bin_or(Type other){
		if(other instanceof TypeInt)
			return this.bin_or((TypeInt)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeInt bin_or(TypeInt other){
		return new TypeInt(this.value.or(other.value));
	}

	public Type bin_xor(Type other){
		if(other instanceof TypeInt)
			return this.bin_xor((TypeInt)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeInt bin_xor(TypeInt other){
		return new TypeInt(this.value.xor(other.value));
	}

	public Type pow(Type other){
		if(other instanceof TypeInt)
			return this.pow((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.pow((TypeFloat)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeInt pow(TypeInt other){
		return new TypeInt(this.value.pow(other.value.intValue()));
	}
	public TypeFloat pow(TypeFloat other){
		return new TypeFloat(Math.pow(this.value.doubleValue(), other.value));
	}

	public int compareTo(Type other){
		if(other instanceof TypeInt)
			return this.compareTo((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.compareTo((TypeFloat)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public int compareTo(TypeInt other){
		return this.value.compareTo(other.value);
	}
	public int compareTo(TypeFloat other){
		return java.lang.Double.compare(this.value.doubleValue(),
						other.value);
	}

	public Type index(Type i){
		throw new ExpressionFault.TypeError(i, this);
	}

	public TypeInt pos() { return this; }
	public TypeInt neg() {
		return new TypeInt(this.value.negate());
	}
	public TypeInt bin_neg() {
		return new TypeInt(this.value.not());
	}

	public int safeIntValue() {
		if (this.value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0
		    || this.value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0)
			throw new ExpressionFault.ValueError
				("cannot cast to int without changing value.");
		return this.value.intValue();
	}

	public long safeLongValue() {
		if (this.value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0
		    || this.value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0)
			throw new ExpressionFault.ValueError
				("cannot cast to long without changing value.");
		return this.value.longValue();
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof TypeInt ? value.equals(((TypeInt)obj).value) : false;
	}

	@Override
	public Type access(String item) {
		return this.get();
	}

	@Override
	public String toString() {
		return (this.getValue() != null) ? this.getValue().toString() : "null";
	}

}
