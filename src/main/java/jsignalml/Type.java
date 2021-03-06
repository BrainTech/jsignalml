package jsignalml;

import static java.lang.String.format;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jsignalml.logging.Logger;

public abstract class Type implements Comparable<Type> {
	static final Logger log = new Logger(Type.class);

	static final Map<java.lang.String, Type> typeNames = util.newHashMap();

	private static void registerType(java.lang.String type, Type theClass) {
		Type oldClass = typeNames.put(type, theClass);
		log.trace("type registered: %s->%s", type, theClass.getClass().getName());
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

	public Type get() {
		return this;
	}

	/* This goes here, and not within BinaryOp because of initialization
	   order: enum instances are initialized before static variables of
	   the enum class. This is a result of the order or declarations
	   in code, which cannot be reversed, because enum ids must be located
	   directly at the begging of enum class definition.
	*/
	static final Map<Integer, BinaryOp> binOpTable = util.newTreeMap();

	public static final int ATOMIC = 0;
	public static final int COMPARISON = 6;
	public static final int LOGICAL = 7;
	public static final int TERNARY = 8;

	public enum BinaryOp {
		ADD("+", "add", SExpressionParser.ADD, 5),
		SUB("-", "sub", SExpressionParser.SUBTRACT, 5),
		MUL("*", "mul", SExpressionParser.MULTIPLY, 4),
		DIV("/", "div", SExpressionParser.TRUEDIV, 4),
		FLOORDIV("//", "floordiv", SExpressionParser.FLOORDIV, 4),
		CEILDIV("///", "ceildiv", SExpressionParser.CEILDIV, 4),
		MOD("%", "mod", SExpressionParser.MODULO, 4),
		BIN_AND("&", "bin_and", SExpressionParser.BINARY_AND, 3),
		BIN_OR("|", "bin_or", SExpressionParser.BINARY_OR, 3),
		BIN_XOR("^", "bin_xor", SExpressionParser.BINARY_XOR, 3),
		POW("**", "pow", SExpressionParser.POWER, 2),
		EQ("==", "cmp", SExpressionParser.EQUALS, COMPARISON),
		NE("!=", "cmp", SExpressionParser.NOTEQUALS, COMPARISON),
		LT("<", "cmp", SExpressionParser.LESSTHAN, COMPARISON),
		GT(">", "cmp", SExpressionParser.MORETHAN, COMPARISON),
		LE("<=", "cmp", SExpressionParser.LESSEQUALS, COMPARISON),
		GE(">=", "cmp", SExpressionParser.MOREEQUALS, COMPARISON),

		LOG_AND("and", "and", SExpressionParser.LOGICAL_AND, LOGICAL),
		LOG_OR("or", "or", SExpressionParser.LOGICAL_OR, LOGICAL);

		public final java.lang.String rep;
		public final java.lang.String javaMethod;
		public final int priority;

		BinaryOp(java.lang.String rep, java.lang.String javaMethod, int opcode, int priority) {
			this.rep = rep;
			this.javaMethod = javaMethod;
			this.priority = priority;
			binOpTable.put(opcode, this);
			log.info("BinaryOp registered: %s opcode=%d priority=%d",
				 rep, opcode, priority);
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

	public Type parse(String repr) {
		throw new ExpressionFault.ValueError(format(
				    "don't know how to convert {0} to {1}",
				    repr, this.getClass().getSimpleName()));
	}

	public abstract Object getValue();

	@Override
	public java.lang.String toString() {
		return super.toString() + "=" + this.getValue();
	}

	public Type binaryOp(BinaryOp op, Type other)
	{
		assert other != null;
		if (other instanceof TypeInt)
			return this.binaryOp(op, (TypeInt) other);
		else if (other instanceof TypeFloat)
			return this.binaryOp(op, (TypeFloat) other);
		else if (other instanceof TypeString)
			return this.binaryOp(op, (TypeString) other);
		else if (other instanceof TypeList)
			return this.binaryOp(op, (TypeList) other);
		else if (other instanceof TypeMap)
			return this.binaryOp(op, (TypeMap) other);
		else if (other instanceof TypeBool)
			return this.binaryOp(op, (TypeBool) other);
		else if (other instanceof TypeBytes)
			return this.binaryOp(op, (TypeBytes) other);
		else if (other instanceof TypeObject)
			return this.binaryOp(op, (TypeBytes) other);
		else
			throw new RuntimeException("unknown type in expression: " + other.getClass());
	}

	protected Type _compareOp(BinaryOp op, Type other)
	{
		assert other != null;

		switch (op) {
		case EQ: return this.compareTo(other) == 0 ? TypeInt.True : TypeInt.False;
		case NE: return this.compareTo(other) != 0 ? TypeInt.True : TypeInt.False;
		case LT: return this.compareTo(other) < 0 ? TypeInt.True : TypeInt.False;
		case GT: return this.compareTo(other) > 0 ? TypeInt.True : TypeInt.False;
		case LE: return this.compareTo(other) <= 0 ? TypeInt.True : TypeInt.False;
		case GE: return this.compareTo(other) >= 0 ? TypeInt.True : TypeInt.False;
		case LOG_AND:
		case LOG_OR:
			throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
		default:
			throw new ExpressionFault.TypeError(this, other);
		}
	}

	public Type binaryOp(BinaryOp op, TypeInt other)
	{
		switch (op) {
		case ADD: return this.add(other);
		case SUB: return this.sub(other);
		case MUL: return this.mul(other);
		case DIV: return this.div(other);
		case FLOORDIV: return this.floordiv(other);
		case CEILDIV: return this.ceildiv(other);
		case MOD: return this.mod(other);
		case BIN_AND: return this.bin_and(other);
		case BIN_OR: return this.bin_or(other);
		case BIN_XOR: return this.bin_xor(other);
		case POW: return this.pow(other);
		default: return this._compareOp(op, other);
		}
	}

	public Type binaryOp(BinaryOp op, TypeFloat other)
	{
		switch (op) {
		case ADD: return this.add(other);
		case SUB: return this.sub(other);
		case MUL: return this.mul(other);
		case DIV: return this.div(other);
		case FLOORDIV: return this.floordiv(other);
		case CEILDIV: return this.ceildiv(other);
		case MOD: return this.mod(other);
		case BIN_AND: return this.bin_and(other);
		case BIN_OR: return this.bin_or(other);
		case BIN_XOR: return this.bin_xor(other);
		case POW: return this.pow(other);
		default: return this._compareOp(op, other);
		}
	}

	public Type binaryOp(BinaryOp op, TypeBool other)
	{
		switch (op) {
		case ADD: return this.add(other);
		case SUB: return this.sub(other);
		case MUL: return this.mul(other);
		case DIV: return this.div(other);
		case FLOORDIV: return this.floordiv(other);
		case CEILDIV: return this.ceildiv(other);
		case MOD: return this.mod(other);
		case BIN_AND: return this.bin_and(other);
		case BIN_OR: return this.bin_or(other);
		case BIN_XOR: return this.bin_xor(other);
		case POW: return this.pow(other);
		default: return this._compareOp(op, other);
		}
	}

	public Type binaryOp(BinaryOp op, TypeBytes other)
	{
		return this._compareOp(op, other);
	}

	public Type binaryOp(BinaryOp op, TypeObject other)
	{
		return this._compareOp(op, other);
	}

	public Type binaryOp(BinaryOp op, TypeString other)
	{
		return this._compareOp(op, other);
	}

	public Type binaryOp(BinaryOp op, TypeList other)
	{
		return this._compareOp(op, other);
	}

	public Type binaryOp(BinaryOp op, TypeMap other)
	{
		return this._compareOp(op, other);
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
	 * Apart from _binaryOpType(..., TypeInt), the function are implemented
	 * (unless overridden) to signal an invalid expression: this is the most
	 * common case. Int operations are different: all types interact with
	 * Ints.
	 */
	public Type binaryOpType(BinaryOp op, Type other)
	{
		if (op.priority == COMPARISON)
			return TypeBool.I;
		if (other == null)
			return null;
		if (op == BinaryOp.LOG_AND || op == BinaryOp.LOG_OR){
			if (this.getClass().equals(other.getClass()))
				return this;
			else
				return null;
		}

		if (other instanceof TypeInt)
			return this._binaryOpType(op, (TypeInt) other);
		else if (other instanceof TypeFloat)
			return this._binaryOpType(op, (TypeFloat) other);
		else if (other instanceof TypeString)
			return this._binaryOpType(op, (TypeString) other);
		else if (other instanceof TypeList)
			return this._binaryOpType(op, (TypeList) other);
		else if (other instanceof TypeMap)
			return this._binaryOpType(op, (TypeMap) other);
		else if (other instanceof TypeBool)
			return this._binaryOpType(op, (TypeBool) other);
		else if (other instanceof TypeBytes)
			return this._binaryOpType(op, (TypeBytes) other);
		else if (other instanceof TypeObject)
			return this._binaryOpType(op, (TypeObject) other);
		else
			throw new RuntimeException("unknown type in expression: " + other);
	}

	/*
	 * Return the superset type of the possible results of expression. The
	 * type is "encoded" in the same way as in binaryOpType(). This function
	 * will only be called if left is null.
	 */
	public Type binaryOpTypeRight(BinaryOp op)
	{
		if (op.priority == COMPARISON)
			return TypeInt.I;
		return null;
	}

	public abstract Type _binaryOpType(BinaryOp op, TypeInt other);

	public Type _binaryOpType(BinaryOp op, TypeFloat other)
	{
		throw new ExpressionFault.TypeError(other, this);
	}

	public Type _binaryOpType(BinaryOp op, TypeString other)
	{
		throw new ExpressionFault.TypeError(other, this);
	}

	public Type _binaryOpType(BinaryOp op, TypeList other)
	{
		throw new ExpressionFault.TypeError(other, this);
	}

	public Type _binaryOpType(BinaryOp op, TypeMap other)
	{
		throw new ExpressionFault.TypeError(other, this);
	}

	public Type _binaryOpType(BinaryOp op, TypeBool other) {
		throw new ExpressionFault.TypeError(other, this);
	}

	public Type _binaryOpType(BinaryOp op, TypeBytes other) {
		throw new ExpressionFault.TypeError(other, this);
	}

	public Type _binaryOpType(BinaryOp op, TypeObject other) {
		throw new ExpressionFault.TypeError(other, this);
	}

	public Type unaryOp(UnaryOp op)
	{
		return unsupported("unary " + op);
	}

	public Type unaryOpType(UnaryOp op)
	{
		switch(op){
		case LOG_NOT:
			if (this instanceof TypeBool)
				return this;
			else
				return new TypeInt();
		case POS:
		case NEG:
			if (this instanceof TypeInt ||
			    this instanceof TypeFloat ||
			    this instanceof TypeBool)
				return this;
			else
				return unsupported("unary " + op);
		default:
			throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
		}
	}

	Type unsupported(String operation)
	{
		throw new ExpressionFault.Unsupported(this.getClass(), operation);
	}

	public Type callType(List<Type> args)
	{
		return unsupported("call");
	}
	public Type call(List<Type> args)
	{
		return unsupported("call");
	}
	public Type call(Type... args)
	{
		return this.call(Arrays.asList(args));
	}

	public String call(String arg)
	{
		throw new ExpressionFault.Unsupported(this.getClass(), "call");
	}

	public long call(long arg)
	{
		throw new ExpressionFault.Unsupported(this.getClass(), "call");
	}

	public double call(double arg)
	{
		throw new ExpressionFault.Unsupported(this.getClass(), "call");
	}

	public Type index(Type sub)
	{
		return unsupported("indexing");
	}

	public Type slice(Type start, Type stop, Type step)
	{
		return unsupported("slicing");
	}

	public Type access(String item)
	{
		throw new ExpressionFault.AttributeError(item);
	}

	public abstract boolean isTrue();
	public abstract java.lang.String repr();

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

	protected int superHashCode() {
		return super.hashCode();
	}

	public Type logical_not() {
		if (this.isTrue())
			return TypeInt.False;
		else
			return TypeInt.True;
	}

	public abstract Type add(Type b);
	public abstract Type sub(Type b);
	public abstract Type mul(Type b);
	public abstract Type div(Type b);
	public abstract Type floordiv(Type b);
	public abstract Type ceildiv(Type b);
	public abstract Type mod(Type b);

	public abstract Type bin_and(Type b);
	public abstract Type bin_or(Type b);
	public abstract Type bin_xor(Type b);

	public abstract Type pow(Type b);

	public abstract Type pos();
	public abstract Type neg();
	public abstract Type bin_neg();

	public TypeInt len() {
		throw new ExpressionFault.ValueError("not a sequence");
	}

	static {
		registerType("int", new TypeInt());
		registerType("float", new TypeFloat());
		registerType("str", new TypeString());
		registerType("list", new TypeList());
		registerType("map", new TypeMap());
		registerType("bool", new TypeBool());
		registerType("bytes", new TypeBytes());
	}

	public static String typename(Type type) {
		return type == null ? "unknown"
			: type.getClass().getSimpleName();
	}
}
