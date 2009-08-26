package org.signalml.jsignalml.sexpression;

import org.signalml.jsignalml.Logger;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public abstract class Type {
    static final Logger log = new Logger(Type.class);

    /* This goes here, and not within BinaryOp because of initialization
       order: enum instances are initialized before static variables of
       the enum class. This is a result of the order or declarations
       in code, which cannot be reversed, because enum ids must be located
       directly at the begging of enum class definition.
    */
    static final Map<Integer, BinaryOp> binOpTable
	= new TreeMap<Integer, BinaryOp>();

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

	BinaryOp(java.lang.String rep, int opcode){
	    this.rep = rep;
	    binOpTable.put(opcode, this);
	    log.info("BinaryOp registered: %s opcode=%d", rep, opcode);
	}

	public static BinaryOp get(int opcode)
	    throws ExpressionFault.UnknownOperationError
	{
	    BinaryOp op = binOpTable.get(opcode);
	    if(op != null)
		return op;

	    throw new ExpressionFault.UnknownOperationError(BinaryOp.class, opcode);
	}
    }

    static final Map<Integer, UnaryOp> unOpTable
	= new TreeMap<Integer, UnaryOp>();

    public enum UnaryOp {
	LOG_NOT("not", SExpressionParser.LOGICAL_NOT);

	public final java.lang.String rep;
	public static /*final*/ Map<Integer, UnaryOp> opTable
	    = new TreeMap<Integer, Type.UnaryOp>();

	UnaryOp(java.lang.String rep, int opcode){
	    this.rep = rep;
	    unOpTable.put(opcode, this);
	}

	public static UnaryOp get(int opcode)
	    throws ExpressionFault.UnknownOperationError
	{
	    Type.UnaryOp op = unOpTable.get(opcode);
	    if(op != null)
		return op;
	    throw new ExpressionFault.UnknownOperationError(UnaryOp.class, opcode);
	}
    }

    public abstract Object getValue();

    public java.lang.String toString(){
	return super.toString() + "=" + this.getValue();
    }

    public Type binaryOp(BinaryOp op, Type other)
	throws ExpressionFault.TypeError
    {
	try {
	    return this.binaryOp(op, (Int) other);
	} catch(ClassCastException e){}
	try {
	    return this.binaryOp(op, (Float) other);
	} catch(ClassCastException e){}
	try {
	    return this.binaryOp(op, (String) other);
	} catch(ClassCastException e){}

	assert false;
	return null;
    }

    public abstract Type binaryOp(BinaryOp op, Int other)
	throws ExpressionFault.TypeError;
    public abstract Type binaryOp(BinaryOp op, Float other)
	throws ExpressionFault.TypeError;
    public abstract Type binaryOp(BinaryOp op, String other)
	throws ExpressionFault.TypeError;

    public Type index(Type sub)
	throws ExpressionFault.TypeError,
	       ExpressionFault.IndexError
    {
	throw new ExpressionFault.TypeError();
    }

    public abstract boolean isTrue();
    public abstract java.lang.String repr();

    @Override
    public boolean equals(Object other)
    {
	try{
	    return this.binaryOp(Type.BinaryOp.EQ, (Type)other).isTrue();
	} catch(ExpressionFault.TypeError e){
	    /* object of incompatible types are different by definition */
	    return false;
	} catch(ClassCastException e){
	    return super.equals(other);
	}
    }

    @Override
    public int hashCode(){
	return this.getValue().hashCode();
    }

    public Type logical_not(){
	if(this.isTrue())
	    return new Int(1);
	else
	    return new Int(0);
    }

    public <T extends Type> T castTo(Class<T> theClass)
	throws ExpressionFault.TypeError
    {
	if(theClass.isInstance(this))
	    return (T)this;
	else
	    // TODO
	    throw new ExpressionFault.TypeError(this.getClass(), theClass);
    }

    public static class Int extends Type {
	public final int value;
	public Int(int value){
	    this.value = value;
	}
	public Int(java.lang.String text){
	    this(new Integer(text.startsWith("+") ?
			     text.substring(1) :
			     text));
	}
	public Int(long value){
	    this((int)value);
	}
	public Int(boolean value){
	    this(value?1:0);
	}

	public Integer getValue(){
	    return this.value;
	}

	public boolean isTrue(){
	    return this.value != 0;
	}

	public java.lang.String repr(){
	    return java.lang.String.valueOf(this.value);
	}

	public Type binaryOp(BinaryOp op, Int other)
	    throws ExpressionFault.TypeError
	{
	    switch(op){
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
	    default:
		throw new ExpressionFault.TypeError();
	    }
	}

	public Type binaryOp(BinaryOp op, Float other)
	    throws ExpressionFault.TypeError
	{
	    switch(op){
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
	    switch(op){
	    case MUL:
		return other.binaryOp(op, this);
	    default:
		throw new ExpressionFault.TypeError();
	    }
	}
    }

    public static class Float extends Type {
	public final double value;
	public Float(double value){
	    this.value = value;
	}
	public Float(java.lang.String text){
	    this(new Double(text));
	}

	public Double getValue(){
	    return this.value;
	}

	public boolean isTrue(){
	    return this.value != 0;
	}

	public java.lang.String repr(){
	    return java.lang.String.valueOf(this.value);
	}

	public Type binaryOp(BinaryOp op, Int other)
	    throws ExpressionFault.TypeError
	{
	    switch(op){
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
	    switch(op){
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
    }

    public static class String extends Type {
	public final java.lang.String value;
	public String(java.lang.String value){
	    this.value = value;
	}
	public String(char value){
	    this.value = "" + value;
	}

	public java.lang.String getValue(){
	    return this.value;
	}

	public boolean isTrue(){
	    return this.value.length() > 0;
	}

	public java.lang.String repr(){
	    // FIXME: add quotes
	    return this.value;
	}

	public Type binaryOp(BinaryOp op, Int other)
	    throws ExpressionFault.TypeError
	{
	    switch(op){
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
	    switch(op){
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

	public Type index(Type sub)
	    throws ExpressionFault.TypeError,
		   ExpressionFault.IndexError
	{
	    try{
		Int ind = (Int) sub;
		char c = this.value.charAt(ind.value);
		return new String(c);
	    } catch(ClassCastException e){
	    } catch(IndexOutOfBoundsException e){
		throw new ExpressionFault.IndexError();
	    }

	    throw new ExpressionFault.TypeError();
	}

	/** Synchronize with SExpression.g:ESC_SEQ ! */
	public static final Map<Pattern, java.lang.String> escapePatterns;
	static {
	    escapePatterns = new HashMap<Pattern, java.lang.String>();
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

	    for(Map.Entry<Pattern,java.lang.String> entry: escapePatterns.entrySet())
		quoteless =
		    entry.getKey().matcher(quoteless).replaceAll(entry.getValue());

	    return quoteless;
	}
    }
}
