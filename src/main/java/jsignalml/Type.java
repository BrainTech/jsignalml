package jsignalml;

import java.util.regex.Pattern;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.util.Collections.unmodifiableList;
import static java.lang.String.format;

public abstract class Type {
	static final Logger log = new Logger(Type.class);

	static final Map<java.lang.String, Class<? extends Type>>
		typeNames = util.newHashMap();

	private static void registerType(java.lang.String type,
	                                 Class<? extends Type> theClass) {
		Class<? extends Type> oldClass = typeNames.put(type, theClass);
		log.info("type registered: %s->%s", type, theClass.getName());
		assert oldClass == null;
	}
	public static Class<? extends Type> getType(java.lang.String type) {
		Class<? extends Type> theClass = typeNames.get(type);
		if (theClass != null)
			return theClass;

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

	public abstract Type binaryOp(BinaryOp op, Int other);
	public abstract Type binaryOp(BinaryOp op, Float other);
	public Type binaryOp(BinaryOp op, String other)
	{
		throw new ExpressionFault.TypeError();
	}
	public Type binaryOp(BinaryOp op, List other)
	{
		throw new ExpressionFault.TypeError();
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

	static {
		registerType("int", Int.class);
	}
	public static class Int extends Type {
		public final int value;
		public Int(int value) {
			this.value = value;
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
				this.value = Integer.parseInt(text, base);
			} catch (NumberFormatException e) {
				throw new SyntaxError.RuntimeFlavour(e);
			}
		}

		public Int(long value) {
			this((int)value);
		}
		public Int(boolean value) {
			this(value?1:0);
		}
		public Int() {
			this(0);
		}

		@Override
		public Int make(Type value) {
			if (value instanceof Int)
				return (Int)value;
			if (value instanceof String)
				return new Int(((String)value).getValue());
			throw new UnsupportedOperationException();
		}

		@Override
		public Integer getValue() {
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

		@Override
		public Type binaryOp(BinaryOp op, Int other)
		{
			switch (op) {
			case ADD:
				return new Int(this.value + other.value);
			case SUB:
				return new Int(this.value - other.value);
			case MUL:
				return new Int(this.value * other.value);
			case DIV:
				return new Float((double)this.value / other.value);
			case FLOORDIV:
				return new Int(this.value / other.value);
			case MOD:
				int value = this.value % other.value;
				if ((other.value > 0 && value < 0) ||
				    (other.value < 0 && value > 0))
					value += other.value;
				return new Int(value);
			case BIN_AND:
				return new Int(this.value & other.value);
			case BIN_OR:
				return new Int(this.value | other.value);
			case BIN_XOR:
				return new Int(this.value ^ other.value);
			case POW:
				return new Int(Math.round(Math.pow(this.value, other.value)));
			case EQ:
				return new Int(this.value == other.value);
			case NE:
				return new Int(this.value != other.value);
			case LT:
				return new Int(this.value < other.value);
			case GT:
				return new Int(this.value > other.value);
			case LE:
				return new Int(this.value <= other.value);
			case GE:
				return new Int(this.value >= other.value);
			case LOG_AND:
			case LOG_OR:
				throw new RuntimeException();
			default:
				throw new ExpressionFault.TypeError(this.getClass(), other.getClass());
			}
		}

		@Override
		public Type binaryOp(BinaryOp op, Float other)
		{
			switch (op) {
			case ADD:
				return new Float(this.value + other.value);
			case SUB:
				return new Float(this.value - other.value);
			case MUL:
				return new Float(this.value * other.value);
			case DIV:
				return new Float(this.value / other.value);
			case FLOORDIV:
				return new Int(Math.round(Math.floor(this.value / other.value)));
			case MOD:
				double value = this.value % other.value;
				if ((other.value > 0 && value < 0) ||
				    (other.value < 0 && value > 0))
					value += other.value;
				return new Float(value);
			case POW:
				return new Float(Math.pow(this.value, other.value));

			case EQ:
				return new Int(this.value == other.value);
			case NE:
				return new Int(this.value != other.value);
			case LT:
				return new Int(this.value < other.value);
			case GT:
				return new Int(this.value > other.value);
			case LE:
				return new Int(this.value <= other.value);
			case GE:
				return new Int(this.value >= other.value);

			case LOG_AND:
			case LOG_OR:
				throw new RuntimeException();

			case BIN_AND:
			case BIN_OR:
			case BIN_XOR:
			default:
				throw new ExpressionFault.TypeError();
			}
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
				return new Int(-this.value);
			default:
				throw new ExpressionFault.TypeError();
			}
		}
	}

	static {
		registerType("float", Float.class);
	}
	public static class Float extends Type {

		public final double value;
		public Float(double value) {
			this.value = value;
		}
		public Float(java.lang.String text) {
			this(new Double(text));
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
			throw new UnsupportedOperationException();
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

		@Override
		public Type binaryOp(BinaryOp op, Int other)
			throws ExpressionFault.TypeError
		{
			switch (op) {
			case ADD:
				return new Float(this.value + other.value);
			case SUB:
				return new Float(this.value - other.value);
			case MUL:
				return new Float(this.value * other.value);
			case DIV:
				return new Float(this.value / other.value);
			case FLOORDIV:
				return new Int(Math.round(Math.floor(this.value / other.value)));
			case MOD:
				double value = this.value % other.value;
				if ((other.value > 0 && value < 0) ||
				    (other.value < 0 && value > 0))
					value += other.value;
				return new Float(value);
			case POW:
				return new Float(Math.pow(this.value, other.value));
			case EQ:
				return new Int(this.value == other.value);
			case NE:
				return new Int(this.value != other.value);
			case LT:
				return new Int(this.value < other.value);
			case GT:
				return new Int(this.value > other.value);
			case LE:
				return new Int(this.value <= other.value);
			case GE:
				return new Int(this.value >= other.value);

			case LOG_AND:
			case LOG_OR:
				throw new RuntimeException();

			case BIN_AND:
			case BIN_OR:
			case BIN_XOR:
			default:
				throw new ExpressionFault.TypeError();
			}
		}

		@Override
		public Type binaryOp(BinaryOp op, Float other)
			throws ExpressionFault.TypeError
		{
			switch (op) {
			case ADD:
				return new Float(this.value + other.value);
			case SUB:
				return new Float(this.value - other.value);
			case MUL:
				return new Float(this.value * other.value);
			case DIV:
				return new Float(this.value / other.value);
			case FLOORDIV:
				return new Int(Math.round(Math.floor(this.value / other.value)));
			case MOD:
				double value = this.value % other.value;
				if ((other.value > 0 && value < 0) ||
				    (other.value < 0 && value > 0))
					value += other.value;
				return new Float(value);
			case POW:
				return new Float(Math.pow(this.value, other.value));

			case EQ:
				return new Int(this.value == other.value);
			case NE:
				return new Int(this.value != other.value);
			case LT:
				return new Int(this.value < other.value);
			case GT:
				return new Int(this.value > other.value);
			case LE:
				return new Int(this.value <= other.value);
			case GE:
				return new Int(this.value >= other.value);

			case LOG_AND:
			case LOG_OR:
				throw new RuntimeException();

			case BIN_AND:
			case BIN_OR:
			case BIN_XOR:
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
		registerType("str", String.class);
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
			return value.str();
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
		public Type binaryOp(BinaryOp op, String other)
			throws ExpressionFault.TypeError
		{
			switch (op) {
			case ADD:
				return new String(this.value + other.value);

			case EQ:
				return new Int(this.value.equals(other.value));
			case NE:
				return new Int(!this.value.equals(other.value));

			case LT: /* should those be implemented ? */
			case GT:
			case LE:
			case GE:

			default:
				throw new ExpressionFault.TypeError();
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

		@Override
		public Type index(Type sub)
			throws ExpressionFault.TypeError,
			       ExpressionFault.IndexError
		{
			if (!(sub instanceof Int))
				throw new ExpressionFault.TypeError();

			Int ind = (Int) sub;
			int offset = ind.value;
			if (offset < 0)
				offset += this.value.length();
			try {
				char c = this.value.charAt(offset);
				return new String(c);
			} catch (IndexOutOfBoundsException e) {
				throw new ExpressionFault.IndexError(
				        ind.value, this.value.length());
			}
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

		public static java.lang.String join(java.lang.String sep,
		                                    java.util.List<? extends Type> list)
		{
			StringBuilder s = new StringBuilder();

			// our list is array-based, so access is cheap
			for (int i = 0; i < list.size(); i++) {
				if (i > 0)
					s.append(sep);
				s.append(list.get(i).repr());
			}

			// TODO: add interface shared by Expression and Type
			// with repr(), and call repr() on items() ?

			return s.toString();
		}
	}

	static {
		registerType("list", List.class);
	}
	public static class List extends Type {
		public final java.util.List<Type> value;

		public List(java.util.List<? extends Type> value) {
			this.value = unmodifiableList(new ArrayList(value));
		}

		public List() {
			this(new ArrayList());
		}

		@Override
		public List make(Type value) {
			throw new UnsupportedOperationException();
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
			return "[" + String.join(", ", this.value) + "]";
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
			case ADD:
				// TODO
			case EQ:
				return new Int(this.compareTo(other) == 0);
			case NE:
				return new Int(this.compareTo(other) != 0);

			case LT:
				return new Int(this.compareTo(other) < 0);
			case GT:
				return new Int(this.compareTo(other) > 0);
			case LE:
				return new Int(this.compareTo(other) <= 0);
			case GE:
				return new Int(this.compareTo(other) >= 0);

			default:
				throw new ExpressionFault.TypeError();
			}
		}

		public int compareTo(List other)
			throws ExpressionFault.TypeError
		{
			int size1 = this.value.size();
			int size2 = other.value.size();

			for(int i=0; i < size1 && i < size2; i++) {
				Type a = this.value.get(i);
				Type b = other.value.get(i);
				if (a.binaryOp(Type.BinaryOp.LT, b).isTrue())
					return -1;
				if (b.binaryOp(Type.BinaryOp.LT, a).isTrue())
					return 1;
			}
			return size1 - size2;
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


		@Override
		public Type index(Type sub)
			throws ExpressionFault.TypeError,
			       ExpressionFault.IndexError
		{
			if (!(sub instanceof Int))
				throw new ExpressionFault.TypeError();

			Int ind = (Int) sub;
			int offset = ind.value;
			if (offset < 0)
				offset += this.value.size();
			try {
				return this.value.get(offset);
			} catch (IndexOutOfBoundsException e) {
				throw new ExpressionFault.IndexError(
				        ind.value, this.value.size());
			}
		}
	}

	static {
		registerType("auto", Type.class);
	}
}
