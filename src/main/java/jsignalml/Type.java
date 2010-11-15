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
		ADD("+", SExpressionParser.ADD),
		SUB("-", SExpressionParser.SUBTRACT),
		MUL("*", SExpressionParser.MULTIPLY),
		DIV("/", SExpressionParser.TRUEDIV),
		FLOORDIV("//", SExpressionParser.FLOORDIV),
		MOD("%", SExpressionParser.MODULO),
		BIN_AND("&", SExpressionParser.BINARY_AND),
		BIN_OR("|", SExpressionParser.BINARY_OR),
		BIN_XOR("^", SExpressionParser.BINARY_XOR),
		POW("**", SExpressionParser.POWER),
		EQ("==", SExpressionParser.EQUALS),
		NE("!=", SExpressionParser.NOTEQUALS),
		LT("<", SExpressionParser.LESSTHAN),
		GT(">", SExpressionParser.MORETHAN),
		LE("<=", SExpressionParser.LESSEQUALS),
		GE(">=", SExpressionParser.MOREEQUALS),

		LOG_AND("and", SExpressionParser.LOGICAL_AND),
		LOG_OR("or", SExpressionParser.LOGICAL_OR);

		public final java.lang.String rep;

		BinaryOp(java.lang.String rep, int opcode) {
			this.rep = rep;
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
		LOG_NOT("not", SExpressionParser.LOGICAL_NOT),
		POS("+", SExpressionParser.UNARY_ADD),
		NEG("-", SExpressionParser.UNARY_SUBTRACT);

		public final java.lang.String rep;
		public static /*final*/ Map<Integer, UnaryOp> opTable = util.newTreeMap();

		UnaryOp(java.lang.String rep, int opcode) {
			this.rep = rep;
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
		try {
			return this.binaryOp(op, (Int) other);
		} catch (ClassCastException e) {}
		try {
			return this.binaryOp(op, (Float) other);
		} catch (ClassCastException e) {}
		try {
			return this.binaryOp(op, (String) other);
		} catch (ClassCastException e) {}

		throw new RuntimeException();
	}

	public abstract Type binaryOp(BinaryOp op, Int other);
	public abstract Type binaryOp(BinaryOp op, Float other);
	public abstract Type binaryOp(BinaryOp op, String other);

	public Type unaryOp(UnaryOp op)
	{
		throw new ExpressionFault.TypeError();
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
			this(_convert(text));
		}

		static int _convert(java.lang.String text) {
			text = text.trim();
			if (text.startsWith("+"))
				text = text.substring(1).trim();
			return Integer.parseInt(text);
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
		public Int make(Type value) {
			if (value instanceof Int)
				return (Int)value;
			if (value instanceof String)
				return new Int(((String)value).getValue());
			throw new UnsupportedOperationException();
		}

		public Integer getValue() {
			return this.value;
		}

		public boolean isTrue() {
			return this.value != 0;
		}

		public java.lang.String repr() {
			return java.lang.String.valueOf(this.value);
		}

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
				return new Int(this.value % other.value);
				// XXX: fix for negative values in modulo
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
				return new Float(this.value % other.value);
				// XXX: fix for negative values in modulo
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

		public Float make(Type value) {
			if (value instanceof Float)
				return (Float)value;
			if (value instanceof Int)
				return new Float(((Int)value).getValue());
			if (value instanceof String)
				return new Float(((String)value).getValue());
			throw new UnsupportedOperationException();
		}

		public Double getValue() {
			return this.value;
		}

		public boolean isTrue() {
			return this.value != 0;
		}

		public java.lang.String repr() {
			return java.lang.String.valueOf(this.value);
		}

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
				return new Float(this.value % other.value);
				// XXX: fix for negative values in modulo
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
				return new Float(this.value % other.value);
				// XXX: fix for negative values in modulo
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

		public Type binaryOp(BinaryOp op, String other)
		throws ExpressionFault.TypeError
		{
			throw new ExpressionFault.TypeError();
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

		public String make(Type value) {
			return value.str();
		}

		public java.lang.String getValue() {
			return this.value;
		}

		public boolean isTrue() {
			return this.value.length() > 0;
		}

		public java.lang.String repr() {
			// FIXME: add quotes
			return this.value;
		}

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

		public Type binaryOp(BinaryOp op, Float other)
		throws ExpressionFault.TypeError
		{
			throw new ExpressionFault.TypeError();
		}

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
		                                    java.util.List<?> list)
		{
			StringBuilder s = new StringBuilder();

			// our list is array-based, so access is cheap
			for (int i = 0; i < list.size(); i++) {
				if (i > 0)
					s.append(sep);
				s.append(list.get(i).toString());
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

		public List make(Type value) {
			throw new UnsupportedOperationException();
		}

		public /*immutable*/ java.util.List<Type> getValue() {
			return this.value;
		}

		public java.lang.String repr() {
			return "[" + String.join(", ", this.value) + "]";
		}

		public boolean isTrue() {
			return this.value.size() > 0;
		}

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

		public Type binaryOp(BinaryOp op, Float other)
		throws ExpressionFault.TypeError
		{
			throw new ExpressionFault.TypeError();
		}

		public Type binaryOp(BinaryOp op, String other)
		throws ExpressionFault.TypeError
		{
			switch (op) {
			case ADD:
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
