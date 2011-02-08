package jsignalml;

import java.util.regex.Pattern;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Iterator;
import java.math.BigInteger;
import static java.util.Collections.unmodifiableList;
import static java.lang.String.format;

import org.apache.commons.lang.StringUtils;

public abstract class Type implements Comparable<Type> {
	static final Logger log = new Logger(Type.class);

	static final Map<java.lang.String, Type> typeNames = util.newHashMap();

	private static void registerType(java.lang.String type, Type theClass) {
		Type oldClass = typeNames.put(type, theClass);
		log.info("type registered: %s->%s", type, theClass.getClass().getName());
		assert oldClass == null;
	}
	public static Type getType(java.lang.String type) {
		if (type == null) // null means "auto"
			return null;

		final Type thetype = typeNames.get(type);
		if (thetype != null)
			return thetype;

		throw new IllegalArgumentException(format("unkown type '%s'", type));
	}

	/* This goes here, and not within BinaryOp because of initialization
	   order: enum instances are initialized before static variables of
	   the enum class. This is a result of the order or declarations
	   in code, which cannot be reversed, because enum ids must be located
	   directly at the begging of enum class definition.
	*/
	static final Map<Integer, BinaryOp> binOpTable = util.newTreeMap();

	public enum BinaryOp {
		ADD("+", "add", SExpressionParser.ADD),
		SUB("-", "sub", SExpressionParser.SUBTRACT),
		MUL("*", "mul", SExpressionParser.MULTIPLY),
		DIV("/", "div", SExpressionParser.TRUEDIV),
		FLOORDIV("//", "floordiv", SExpressionParser.FLOORDIV),
		MOD("%", "mod", SExpressionParser.MODULO),
		BIN_AND("&", "bin_and", SExpressionParser.BINARY_AND),
		BIN_OR("|", "bin_or", SExpressionParser.BINARY_OR),
		BIN_XOR("^", "bin_xor", SExpressionParser.BINARY_XOR),
		POW("**", "pow", SExpressionParser.POWER),
		EQ("==", "cmp", SExpressionParser.EQUALS),
		NE("!=", "cmp", SExpressionParser.NOTEQUALS),
		LT("<", "cmp", SExpressionParser.LESSTHAN),
		GT(">", "cmp", SExpressionParser.MORETHAN),
		LE("<=", "cmp", SExpressionParser.LESSEQUALS),
		GE(">=", "cmp", SExpressionParser.MOREEQUALS),

		LOG_AND("and", "and", SExpressionParser.LOGICAL_AND),
		LOG_OR("or", "or", SExpressionParser.LOGICAL_OR);

		public final java.lang.String rep;
		public final java.lang.String javaMethod;

		BinaryOp(java.lang.String rep, java.lang.String javaMethod, int opcode) {
			this.rep = rep;
			this.javaMethod = javaMethod;
			binOpTable.put(opcode, this);
			log.info("BinaryOp registered: %s opcode=%d", rep, opcode);
		}

		public static BinaryOp get(int opcode)
		{
			BinaryOp op = binOpTable.get(opcode);
			assert op != null: "opcode=" + opcode;
			return op;
		}
	}

	static final Map<Integer, UnaryOp> unOpTable = util.newTreeMap();

	public enum UnaryOp {
		LOG_NOT("not", "not", SExpressionParser.LOGICAL_NOT),
		POS("+", "pos", SExpressionParser.UNARY_ADD),
		NEG("-", "neg", SExpressionParser.UNARY_SUBTRACT);

		public final java.lang.String rep;
		public final java.lang.String javaMethod;
		public static /*final*/ Map<Integer, UnaryOp> opTable = util.newTreeMap();

		UnaryOp(java.lang.String rep, java.lang.String javaMethod, int opcode) {
			this.rep = rep;
			this.javaMethod = javaMethod;
			unOpTable.put(opcode, this);
		}

		public static UnaryOp get(int opcode)
		{
			Type.UnaryOp op = unOpTable.get(opcode);
			assert op != null: "opcode=" + opcode;
			return op;
		}
	}

	public abstract Type make(Type value);

	public abstract Object getValue();

	public java.lang.String toString() {
		return super.toString() + "=" + this.getValue();
	}

	public Type binaryOp(BinaryOp op, Type other)
	{
		if (other instanceof Type.Int)
			return this.binaryOp(op, (Type.Int) other);
		if (other instanceof Type.Float)
			return this.binaryOp(op, (Type.Float) other);
		if (other instanceof Type.String)
			return this.binaryOp(op, (Type.String) other);
		if (other instanceof Type.List)
			return this.binaryOp(op, (Type.List) other);
		throw new RuntimeException("unknown type in expression");
	}

	public Type binaryOp(BinaryOp op, Int other)
	{
		switch (op) {
		case ADD: return this.add(other);
		case SUB: return this.sub(other);
		case MUL: return this.mul(other);
		case DIV: return this.div(other);
		case FLOORDIV: return this.floordiv(other);
		case MOD: return this.mod(other);
		case BIN_AND: return this.bin_and(other);
		case BIN_OR: return this.bin_or(other);
		case BIN_XOR: return this.bin_xor(other);
		case POW: return this.pow(other);
		case EQ: return new Int(this.compareTo(other) == 0);
		case NE: return new Int(this.compareTo(other) != 0);
		case LT: return new Int(this.compareTo(other) < 0);
		case GT: return new Int(this.compareTo(other) > 0);
		case LE: return new Int(this.compareTo(other) <= 0);
		case GE: return new Int(this.compareTo(other) >= 0);
		case LOG_AND:
		case LOG_OR:
			throw new RuntimeException();
		default:
			throw new ExpressionFault.TypeError(this.getClass(), other.getClass());
		}
	}

	public Type binaryOp(BinaryOp op, Float other)
	{
		switch (op) {
		case ADD: return this.add(other);
		case SUB: return this.sub(other);
		case MUL: return this.mul(other);
		case DIV: return this.div(other);
		case FLOORDIV: return this.floordiv(other);
		case MOD: return this.mod(other);
		case BIN_AND: return this.bin_and(other);
		case BIN_OR: return this.bin_or(other);
		case BIN_XOR: return this.bin_xor(other);
		case POW: return this.pow(other);
		case EQ: return new Int(this.compareTo(other) == 0);
		case NE: return new Int(this.compareTo(other) != 0);
		case LT: return new Int(this.compareTo(other) < 0);
		case GT: return new Int(this.compareTo(other) > 0);
		case LE: return new Int(this.compareTo(other) <= 0);
		case GE: return new Int(this.compareTo(other) >= 0);
		case LOG_AND:
		case LOG_OR:
			throw new RuntimeException();
		default:
			throw new ExpressionFault.TypeError(this.getClass(), other.getClass());
		}
	}

	public Type binaryOp(BinaryOp op, String other)
	{
		throw new ExpressionFault.TypeError(this.getClass(), other.getClass());
	}
	public Type binaryOp(BinaryOp op, List other)
	{
		throw new ExpressionFault.TypeError(this.getClass(), other.getClass());
	}

	/**
	 * Return the superset type of the possible results of expression. The
	 * type is "encoded" as an object being an instance (of a subclass) of
	 * Type.  An actual object, not Class<? extends Type> is returned,
	 * because Java doesn't have class methods. In case the expression is
	 * known to be invalid, an exception is thrown. null is used to specify
	 * that more than one basic type can be returned.
	 *
	 * This evaluation is is not supposed to be strict. Sometimes null
	 * might be returned, even if a strict analysis would be able to return
	 * a more precise answer. Likewise, not all errors have to be detected.
	 *
	 * The helper functions _binaryOpType(op, other) are called only for
	 * not-null arguments, and for non-logical operations.
	 * Apart from _binaryOpType(..., Type.Int), the function are implemented
	 * (unless overridden) to signal an invalid expression: this is the most
	 * common case. Int operations are different: all types interact with
	 * Ints.
	 */
	public Type binaryOpType(BinaryOp op, Type other)
	{
		if (other == null)
			return null;
		if (op == BinaryOp.LOG_AND || op == BinaryOp.LOG_OR){
			if (this.getClass().equals(other.getClass()))
				return this;
			else
				return null;
		}

		if (other instanceof Type.Int)
			return this._binaryOpType(op, (Type.Int) other);
		if (other instanceof Type.Float)
			return this._binaryOpType(op, (Type.Float) other);
		if (other instanceof Type.String)
			return this._binaryOpType(op, (Type.String) other);
		if (other instanceof Type.List)
			return this._binaryOpType(op, (Type.List) other);
		throw new RuntimeException("unknown type in expression");
	}

	public abstract Type _binaryOpType(BinaryOp op, Type.Int other);
	public Type _binaryOpType(BinaryOp op, Type.Float other)
	{
		throw new ExpressionFault.TypeError();
	}
	public Type _binaryOpType(BinaryOp op, Type.String other)
	{
		throw new ExpressionFault.TypeError();
	}
	public Type _binaryOpType(BinaryOp op, Type.List other)
	{
		throw new ExpressionFault.TypeError();
	}


	public Type unaryOp(UnaryOp op)
	{
		throw new ExpressionFault.TypeError();
	}

	public Type unaryOpType(UnaryOp op)
	{
		switch(op){
		case LOG_NOT:
			return new Type.Int();
		case POS:
		case NEG:
			if (this.getClass().equals(Type.Int.class) ||
			    this.getClass().equals(Type.Float.class))
				return this;
			else
				throw new ExpressionFault.TypeError();
		default:
			throw new RuntimeException();
		}
	}

	public Type index(Type sub)
	{
		throw new ExpressionFault.TypeError();
	}

	public abstract boolean isTrue();
	public abstract java.lang.String repr();
	public String str() {
		return new String(this.repr());
	}

	@Override
	public boolean equals(Object other)
	{
		try {
			return this.binaryOp(Type.BinaryOp.EQ, (Type)other).isTrue();
		} catch (ExpressionFault.TypeError e) {
			/* object of incompatible types are different by definition */
			return false;
		} catch (ClassCastException e) {
			return super.equals(other);
		}
	}

	@Override
	public int hashCode() {
		return this.getValue().hashCode();
	}

	public Type logical_not() {
		if (this.isTrue())
			return new Int(0);
		else
			return new Int(1);
	}

	abstract Type add(Type b);
	abstract Type sub(Type b);
	abstract Type mul(Type b);
	abstract Type div(Type b);
	abstract Type floordiv(Type b);
	abstract Type mod(Type b);

	abstract Type bin_and(Type b);
	abstract Type bin_or(Type b);
	abstract Type bin_xor(Type b);

	abstract Type pow(Type b);

	abstract Type pos();
	abstract Type neg();
	abstract Type bin_neg();



	static {
		registerType("int", new Int());
	}
	public static class Int extends Type {
		public static final Int ZERO = new Int(BigInteger.ZERO);

		public final BigInteger value;

		public static final Int False = new Int(0);
		public static final Int True = new Int(1);

		public Int(BigInteger value){
			this.value = value;
		}
		public Int(long value) {
			this.value = BigInteger.valueOf(value);
		}
		public Int(double value) {
			this((long)value);
			if(value > Integer.MAX_VALUE || value < Integer.MIN_VALUE)
				throw new ExpressionFault.ValueError("overflow");
		}
		public Int(java.lang.String text) {

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
				throw new SyntaxError.RuntimeFlavour(e);
			}
		}
		public Int(boolean value) {
			this(value?1:0);
		}
		public Int() {
			this(0);
		}

		@Override
		public Int make(Type other) {
			if (other instanceof Int)
				return (Int)other;
			if (other instanceof Float)
				return new Int(((Float)other).value);
			if (other instanceof String)
				return new Int(((String)other).value);
			throw new ExpressionFault.TypeError();
		}

		static Int makeFromUnsignedReadAsSigned(long value) {
			Int ivalue = new Int(value);
			log.info("converting long %s", value);
			if (ivalue.compareTo(ZERO) >= 0)
				return ivalue;
			// TODO: add tests!!!
			return new Int(ivalue.value.add(BigInteger.valueOf(Long.MIN_VALUE)
							.multiply(BigInteger.valueOf(-2))));
		}

		static Int makeFromUnsignedReadAsSigned(int value) {
			Int ivalue = new Int(value);
			log.info("converting int %s", value);
			if (ivalue.compareTo(ZERO) >= 0)
				return ivalue;
			// TODO: add tests!!!
			return new Int(ivalue.value.add(BigInteger.valueOf(Integer.MIN_VALUE)
							.multiply(BigInteger.valueOf(-2))));
		}

		static Int makeFromUnsignedReadAsSigned(short value) {
			Int ivalue = new Int(value);
			log.info("converting short %s", value);
			if (ivalue.compareTo(ZERO) >= 0)
				return ivalue;
			// TODO: add tests!!!
			return new Int(ivalue.value.add(BigInteger.valueOf(Short.MIN_VALUE)
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
		public Type binaryOp(BinaryOp op, String other)
		{
			switch (op) {
			case MUL:
				return other.binaryOp(op, this);
			default:
				throw new ExpressionFault.TypeError();
			}
		}

		@Override
		public Type binaryOp(BinaryOp op, List other)
		{
			switch (op) {
			case MUL:
				return other.binaryOp(op, this);
			default:
				throw new ExpressionFault.TypeError();
			}
		}

		@Override
		public Type _binaryOpType(BinaryOp op, Type.Int other)
		{
			/* 1 should be safe for all ops */
			return this.binaryOp(op, new Type.Int(1));
		}

		@Override
		public Type _binaryOpType(BinaryOp op, Type.Float other)
		{
			/* 1 should be safe for all ops */
			return this.binaryOp(op, new Type.Float(1.0));
		}

		@Override
		public Type _binaryOpType(BinaryOp op, Type.String other)
		{
			return other.binaryOpType(op, this);
		}

		@Override
		public Type _binaryOpType(BinaryOp op, Type.List other)
		{
			return other.binaryOpType(op, this);
		}


		@Override
		public Type unaryOp(UnaryOp op)
			throws ExpressionFault.TypeError
		{
			switch (op) {
			case POS:
				return this;
			case NEG:
				return new Int(this.value.negate());
			default:
				throw new ExpressionFault.TypeError();
			}
		}

		public Type add(Type other){
			if(other instanceof Int)
				return this.add((Int)other);
			if(other instanceof Float)
				return this.add((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int add(Int other){
			return new Int(this.value.add(other.value));
		}
		public Float add(Float other){
			return new Float(this.value.doubleValue() + other.value);
		}

		public Type sub(Type other){
			if(other instanceof Int)
				return this.sub((Int)other);
			if(other instanceof Float)
				return this.sub((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int sub(Int other){
			return new Int(this.value.subtract(other.value));
		}
		public Int sub(BigInteger other){
			return new Int(this.value.subtract(other));
		}
		public Float sub(Float other){
			return new Float(this.value.doubleValue() - other.value);
		}

		public Type mul(Type other){
			if(other instanceof Int)
				return this.mul((Int)other);
			if(other instanceof Float)
				return this.mul((Float)other);
			if(other instanceof String)
				return this.mul((String)other);
			if(other instanceof List)
				return this.mul((List)other);
			throw new ExpressionFault.TypeError();
		}
		public Int mul(Int other){
			return new Int(this.value.multiply(other.value));
		}
		public Float mul(Float other){
			return new Float(this.value.doubleValue() * other.value);
		}
		public String mul(String other){
			return other.mul(this);
		}
		public List mul(List other){
			return other.mul(this);
		}

		public Float div(Type other){
			if(other instanceof Int)
				return this.div((Int)other);
			if(other instanceof Float)
				return this.div((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float div(Int other){
			return new Float(this.value.doubleValue() / other.value.doubleValue());
		}
		public Float div(Float other){
			return new Float(this.value.doubleValue() / other.value);
		}

		public Int floordiv(Type other){
			if(other instanceof Int)
				return this.floordiv((Int)other);
			if(other instanceof Float)
				return this.floordiv((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int floordiv(Int other){
			return make(this.sub(this.mod(other)).div(other));
		}
		public Int floordiv(Float other){
			return new Int(this.value.doubleValue() / other.value);
		}

		public Type mod(Type other){
			if(other instanceof Int)
				return this.mod((Int)other);
			if(other instanceof Float)
				return this.mod((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int mod(Int other){
			if(other.compareTo(ZERO) < 0)
				// BigInteger doesn't allow negative factor.
				// Nevertheless, -x mod -y == x mod y.
				return new Int(this.value.negate()
					       .mod(other.value.negate()).negate());
			return new Int(this.value.mod(other.value));
		}
		public Float mod(Float other){
			return new Float(this.value.doubleValue()).mod(other);
		}

		public Type bin_and(Type other){
			if(other instanceof Int)
				return this.bin_and((Int)other);
			throw new ExpressionFault.TypeError();
		}
		public Int bin_and(Int other){
			return new Int(this.value.and(other.value));
		}

		public Type bin_or(Type other){
			if(other instanceof Int)
				return this.bin_or((Int)other);
			throw new ExpressionFault.TypeError();
		}
		public Int bin_or(Int other){
			return new Int(this.value.or(other.value));
		}

		public Type bin_xor(Type other){
			if(other instanceof Int)
				return this.bin_xor((Int)other);
			throw new ExpressionFault.TypeError();
		}
		public Int bin_xor(Int other){
			return new Int(this.value.xor(other.value));
		}

		public Type pow(Type other){
			if(other instanceof Int)
				return this.pow((Int)other);
			if(other instanceof Float)
				return this.pow((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int pow(Int other){
			return new Int(this.value.pow(other.value.intValue()));
		}
		public Float pow(Float other){
			return new Float(Math.pow(this.value.doubleValue(), other.value));
		}

		public int compareTo(Type other){
			if(other instanceof Int)
				return this.compareTo((Int)other);
			if(other instanceof Float)
				return this.compareTo((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public int compareTo(Int other){
			return this.value.compareTo(other.value);
		}
		public int compareTo(Float other){
			return java.lang.Double.compare(this.value.doubleValue(),
							other.value);
		}

		public Type index(Type i){
			throw new ExpressionFault.TypeError();
		}

		public Int pos() { return this; }
		public Int neg() {
			return new Int(this.value.negate());
		}
		public Int bin_neg() {
			return new Int(this.value.not());
		}

		public int safeIntValue() {
			if (this.value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0
			    || this.value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0)
				throw new ExpressionFault.ValueError
					("cannot cast to int without changing value.");
			return this.value.intValue();
		}
	}

	static {
		registerType("float", new Float());
	}
	public static class Float extends Type {

		public final double value;
		public Float(double value) {
			this.value = value;
		}
		public Float(java.lang.String text) {
			this(new Double(text));
		}
		public Float(BigInteger value) {
			this.value = value.doubleValue();
		}
		public Float(Int value) {
			this(value.value);
		}

		public Float() {
			this(0.0);
		}

		@Override
		public Float make(Type value) {
			if (value instanceof Float)
				return (Float)value;
			if (value instanceof Int)
				return new Float(((Int)value).getValue());
			if (value instanceof String)
				return new Float(((String)value).getValue());
			throw new ExpressionFault.TypeError();
		}

		@Override
		public Double getValue() {
			return this.value;
		}

		@Override
		public boolean isTrue() {
			return this.value != 0;
		}

		@Override
		public java.lang.String repr() {
			return java.lang.String.valueOf(this.value);
		}

		public int compareTo(Type other){
			if(other instanceof Int)
				return this.compareTo((Int)other);
			if(other instanceof Float)
				return this.compareTo((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public int compareTo(Int other){
			return -other.compareTo(this);
		}
		public int compareTo(Float other){
			return java.lang.Double.compare(this.value,
							other.value);
		}

		public Type add(Type other){
			if(other instanceof Int)
				return this.add((Int)other);
			if(other instanceof Float)
				return this.add((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float add(Int other){
			return new Float(this.value + other.value.doubleValue());
		}
		public Float add(Float other){
			return new Float(this.value + other.value);
		}

		public Type sub(Type other){
			if(other instanceof Int)
				return this.sub((Int)other);
			if(other instanceof Float)
				return this.sub((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float sub(Int other){
			return new Float(this.value - other.value.doubleValue());
		}
		public Float sub(Float other){
			return new Float(this.value - other.value);
		}

		public Type mul(Type other){
			if(other instanceof Int)
				return this.mul((Int)other);
			if(other instanceof Float)
				return this.mul((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float mul(Int other){
			return new Float(this.value * other.value.doubleValue());
		}
		public Float mul(Float other){
			return new Float(this.value * other.value);
		}

		public Float div(Type other){
			if(other instanceof Int)
				return this.div((Int)other);
			if(other instanceof Float)
				return this.div((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float div(Int other){
			return new Float(this.value / other.value.doubleValue());
		}
		public Float div(Float other){
			return new Float(this.value / other.value);
		}

		public Int floordiv(Type other){
			if(other instanceof Int)
				return this.floordiv((Int)other);
			if(other instanceof Float)
				return this.floordiv((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int floordiv(Int other){
			return new Int(Math.round(Math.floor(this.value / other.value.doubleValue())));
		}
		public Int floordiv(Float other){
			return new Int(Math.round(Math.floor(this.value / other.value)));
		}

		public Float mod(Type other){
			if(other instanceof Int)
				return this.mod((Int)other);
			if(other instanceof Float)
				return this.mod((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float mod(Float other){
			double value = this.value % other.value;
			if ((other.value > 0 && value < 0) ||
			    (other.value < 0 && value > 0))
				value += other.value;
			return new Float(value);
		}
		public Float mod(Int other){
			return this.mod(new Float(other));
		}

		public Type bin_and(Type other){
			throw new ExpressionFault.TypeError();
		}
		public Type bin_or(Type other){
			throw new ExpressionFault.TypeError();
		}
		public Type bin_xor(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Type pow(Type other){
			if(other instanceof Int)
				return this.pow((Int)other);
			if(other instanceof Float)
				return this.pow((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float pow(Int other){
			return new Float(Math.pow(this.value, other.value.doubleValue()));
		}
		public Float pow(Float other){
			return new Float(Math.pow(this.value, other.value));
		}

		public Type index(Type i){
			throw new ExpressionFault.TypeError();
		}

		public Int bool(){
			return this.value == 0.0 ? Int.False : Int.True;
		}

		public Float pos() { return this; }
		public Float neg() { return new Float(-this.value); }
		public Int bin_neg() {
			throw new ExpressionFault.TypeError();
		}

		@Override
		public Type _binaryOpType(BinaryOp op, Type.Int other)
		{
			/* 1 should be safe for all ops */
			return this.binaryOp(op, new Type.Int(1));
		}

		@Override
		public Type _binaryOpType(BinaryOp op, Type.Float other)
		{
			/* 1 should be safe for all ops */
			return this.binaryOp(op, new Type.Float(1.0));
		}


		@Override
		public Type unaryOp(UnaryOp op)
			throws ExpressionFault.TypeError
		{
			switch (op) {
			case POS:
				return this;
			case NEG:
				return new Float(-this.value);
			default:
				throw new ExpressionFault.TypeError();
			}
		}
	}

	static {
		registerType("str", new String());
	}
	public static class String extends Type {
		public final java.lang.String value;
		public String(java.lang.String value) {
			this.value = value;
		}
		public String(char value) {
			this.value = "" + value;
		}

		public String() {
			this("");
		}

		@Override
		public String make(Type value) {
			return new String(value.toString());
		}

		@Override
		public java.lang.String getValue() {
			return this.value;
		}

		@Override
		public boolean isTrue() {
			return this.value.length() > 0;
		}

		@Override
		public java.lang.String repr() {
			// FIXME: add quotes
			return "\"" + this.value + "\"";
		}

		@Override
		public Type binaryOp(BinaryOp op, String other)
			throws ExpressionFault.TypeError
		{
			switch (op) {
			case ADD: return this.add(other);

			case EQ: return new Int(this.compareTo(other) == 0);
			case NE: return new Int(this.compareTo(other) != 0);
			case LT: return new Int(this.compareTo(other) < 0);
			case GT: return new Int(this.compareTo(other) > 0);
			case LE: return new Int(this.compareTo(other) <= 0);
			case GE: return new Int(this.compareTo(other) >= 0);

			case LOG_AND:
			case LOG_OR:
				throw new RuntimeException();
			default:
				throw new ExpressionFault.TypeError(this.getClass(), other.getClass());
			}
		}

		@Override
		public Type _binaryOpType(BinaryOp op, Type.Int other)
		{
			/* 0 should be safe and efficient for all ops */
			return this.binaryOp(op, new Type.Int(0));
		}

		@Override
		public Type _binaryOpType(BinaryOp op, Type.String other)
		{
			/* "" should be safe and efficient for all ops */
			return this.binaryOp(op, new Type.String());
		}

		/** Synchronize with SExpression.g:ESC_SEQ ! */
		public static final Map<Pattern, java.lang.String> escapePatterns;
		static {
			escapePatterns = util.newHashMap();
			escapePatterns.put(Pattern.compile(Pattern.quote("\\t")),  "\t");
			escapePatterns.put(Pattern.compile(Pattern.quote("\\n")),  "\n");
			escapePatterns.put(Pattern.compile(Pattern.quote("\\r")),  "\r");
			escapePatterns.put(Pattern.compile(Pattern.quote("\\\"")), "\"");
			escapePatterns.put(Pattern.compile(Pattern.quote("\\'")),  "'");
		}

		public static String fromQuoted(java.lang.String quoted)
		{
			return new String(unquote(quoted));
		}

		public static java.lang.String unquote(final java.lang.String quoted)
		{
			java.lang.String quoteless = quoted.substring(1, quoted.length()-1);

			for (Map.Entry<Pattern,java.lang.String> entry: escapePatterns.entrySet())
				quoteless =
				        entry.getKey().matcher(quoteless).replaceAll(entry.getValue());

			return quoteless;
		}

		public Type add(Type other){
			if(other instanceof String)
				return this.add((String)other);
			throw new ExpressionFault.TypeError();
		}
		public String add(String other){
			return new String(this.value + other.value);
		}

		public Type sub(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Type mul(Type other){
			if(other instanceof Int)
				return this.mul((Int)other);
			throw new ExpressionFault.TypeError();
		}
		public String mul(Int other){
			StringBuilder result = new StringBuilder();
			for(long count=other.value.longValue(); count > 0; count--)
				result.append(this.value);
			return new String(result.toString());
		}

		public Float div(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Int floordiv(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Type mod(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Type bin_and(Type other){
			throw new ExpressionFault.TypeError();
		}
		public Type bin_or(Type other){
			throw new ExpressionFault.TypeError();
		}
		public Type bin_xor(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Type pow(Type other){
			throw new ExpressionFault.TypeError();
		}

		public int compareTo(Type other){
			if(other instanceof String)
				return this.compareTo((String)other);
			throw new ExpressionFault.TypeError();
		}
		public int compareTo(String other){
			return this.value.compareTo(other.value);
		}

		public Type index(Type i){
			if(i instanceof Int)
				return this.index((Int)i);
			throw new ExpressionFault.TypeError();
		}
		public Type index(Int i){
			int offset = i.safeIntValue();
			if (offset < 0)
				offset += this.value.length();
			try {
				log.debug("index=%s", i);
				char c = this.value.charAt(offset);
				return new String(c);
			} catch (IndexOutOfBoundsException e) {
				throw new ExpressionFault.IndexError(
				        offset, this.value.length());
			}
		}

		public Int pos() {
			throw new ExpressionFault.TypeError();
		}
		public Int neg() {
			throw new ExpressionFault.TypeError();
		}
		public Int bin_neg() {
			throw new ExpressionFault.TypeError();
		}

	}

	static {
		registerType("list", new List());
	}
	public static class List extends Type implements Iterable<Type> {
		public final java.util.List<Type> value;

		public List(java.util.List<? extends Type> value) {
			this.value = unmodifiableList(new ArrayList(value));
		}

		public List(Type...items) {
			this.value = unmodifiableList(Arrays.asList(items));
		}

		@Override
		public List make(Type other) {
			if (other instanceof String)
				return null; // TODO
			if (other instanceof List)
				return (List)other;
			throw new ExpressionFault.TypeError();
		}

		public static List make(Object... items) {
			java.util.List<Type> list = util.newLinkedList();
			for (Object item: items) {
				if (item instanceof java.lang.Integer) {
					list.add(new Type.Int((java.lang.Integer)item));
				} else if (item instanceof java.lang.Double) {
					list.add(new Type.Float((java.lang.Double)item));
				} else if (item instanceof java.lang.Float) {
					list.add(new Type.Float((java.lang.Float)item));
				} else if (item instanceof java.lang.String) {
					list.add(new Type.String((java.lang.String)item));
				} else {
					throw new RuntimeException();
				}
			}
			return new List(list);
		}

		@Override
		public /*immutable*/ java.util.List<Type> getValue() {
			return this.value;
		}

		@Override
		public java.lang.String repr() {
			return "[" + StringUtils.join(this.value, ", ") + "]";
		}

		@Override
		public boolean isTrue() {
			return this.value.size() > 0;
		}

		@Override
		public Type binaryOp(BinaryOp op, Int other)
			throws ExpressionFault.TypeError
		{
			switch (op) {
			case MUL:
				// TODO
			default:
				throw new ExpressionFault.TypeError();
			}
		}

		@Override
		public Type binaryOp(BinaryOp op, Float other)
			throws ExpressionFault.TypeError
		{
			throw new ExpressionFault.TypeError();
		}

		@Override
		public Type binaryOp(BinaryOp op, List other)
			throws ExpressionFault.TypeError
		{
			switch (op) {
			case ADD: return this.add(other);

			case EQ: return new Int(this.compareTo(other) == 0);
			case NE: return new Int(this.compareTo(other) != 0);
			case LT: return new Int(this.compareTo(other) < 0);
			case GT: return new Int(this.compareTo(other) > 0);
			case LE: return new Int(this.compareTo(other) <= 0);
			case GE: return new Int(this.compareTo(other) >= 0);

			case LOG_AND:
			case LOG_OR:
				throw new RuntimeException();
			default:
				throw new ExpressionFault.TypeError(this.getClass(), other.getClass());
			}
		}

		@Override
		public Iterator<Type> iterator() {
			return value.iterator();
		}


		@Override
		public Type _binaryOpType(BinaryOp op, Type.Int other)
		{
			/* 0 should be safe and efficient for all ops */
			return this.binaryOp(op, new Type.Int(0));
		}

		@Override
		public Type _binaryOpType(BinaryOp op, Type.List other)
		{
			/* [] should be safe and efficient for all ops */
			return this.binaryOp(op, new Type.List());
		}

		public Type add(Type other){
			if(other instanceof List)
				return this.add((List)other);
			throw new ExpressionFault.TypeError();
		}
		public List add(List other){
			ArrayList<Type> result = new ArrayList<Type>(this.value);
			result.addAll(other.value);
			return new List(result);
		}

		public Type sub(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Type mul(Type other){
			if(other instanceof Int)
				return this.mul((Int)other);
			throw new ExpressionFault.TypeError();
		}
		public List mul(Int other){
			ArrayList<Type> result = new ArrayList<Type>(this.value);
			for(long count=other.value.longValue(); count > 1; count--)
				result.addAll(this.value);
			return new List(result);
		}

		public Float div(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Int floordiv(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Type mod(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Type bin_and(Type other){
			throw new ExpressionFault.TypeError();
		}
		public Type bin_or(Type other){
			throw new ExpressionFault.TypeError();
		}
		public Type bin_xor(Type other){
			throw new ExpressionFault.TypeError();
		}

		public Type pow(Type other){
			throw new ExpressionFault.TypeError();
		}

		public int compareTo(Type other){
			if(other instanceof List)
				return this.compareTo((List)other);
			throw new ExpressionFault.TypeError();
		}
		public int compareTo(List other){
			Iterator<Type> itother = other.value.iterator();

			for(Type item: this.value){
				if(!itother.hasNext())
					return 1;
				Type itemother = itother.next();
				int cmp = item.compareTo(itemother);
				if(cmp != 0)
					return cmp;
			}

			if(itother.hasNext())
				return -1;
			return 0;			
		}

		public Type index(Type i){
			if(i instanceof Int)
				return this.index((Int)i);
			throw new ExpressionFault.TypeError();
		}
		public Type index(Int i){
			int offset = i.safeIntValue();
			if (offset < 0)
				offset += this.value.size();
			try {
				return this.value.get(offset);
			} catch (IndexOutOfBoundsException e) {
				throw new ExpressionFault.IndexError(
				        offset, this.value.size());
			}
		}

		public Int bool(){
			return this.value.isEmpty() ? Int.False : Int.True;
		}

		public Int pos() {
			throw new ExpressionFault.TypeError();
		}
		public Int neg() {
			throw new ExpressionFault.TypeError();
		}
		public Int bin_neg() {
			throw new ExpressionFault.TypeError();
		}
	}
}
