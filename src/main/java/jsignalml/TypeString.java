package jsignalml;

import java.util.Map;
import java.util.regex.Pattern;

public class TypeString extends Type {
	public static final TypeString I = new TypeString();

	public final java.lang.String value;
	public TypeString(java.lang.String value) {
		this.value = value;
	}
	public TypeString(char value) {
		this.value = "" + value;
	}

	public TypeString() {
		this("");
	}

	@Override
	public TypeString make(Type value) {
		return new TypeString(value.getValue().toString());
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
	public Type binaryOp(BinaryOp op, TypeString other)
		throws ExpressionFault.TypeError
	{
		switch (op) {
		case ADD: return this.add(other);

		case EQ: return new TypeInt(this.compareTo(other) == 0);
		case NE: return new TypeInt(this.compareTo(other) != 0);
		case LT: return new TypeInt(this.compareTo(other) < 0);
		case GT: return new TypeInt(this.compareTo(other) > 0);
		case LE: return new TypeInt(this.compareTo(other) <= 0);
		case GE: return new TypeInt(this.compareTo(other) >= 0);

		case LOG_AND:
		case LOG_OR:
			throw new RuntimeException();
		default:
			throw new ExpressionFault.TypeError(this.getClass(), other.getClass());
		}
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeInt other)
	{
		/* 0 should be safe and efficient for all ops */
		return this.binaryOp(op, new TypeInt(0));
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeString other)
	{
		/* "" should be safe and efficient for all ops */
		return this.binaryOp(op, new TypeString());
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

	public static TypeString fromQuoted(java.lang.String quoted)
	{
		return new TypeString(unquote(quoted));
	}

	public static java.lang.String unquote(final java.lang.String quoted)
	{
		java.lang.String quoteless = quoted.substring(1, quoted.length()-1);

		for (Map.Entry<Pattern,String> entry: escapePatterns.entrySet())
			quoteless =
			        entry.getKey().matcher(quoteless).replaceAll(entry.getValue());

		return quoteless;
	}

	public Type add(Type other){
		if(other instanceof TypeString)
			return this.add((TypeString)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeString add(TypeString other){
		return new TypeString(this.value + other.value);
	}

	public Type sub(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "sub");
	}

	public Type mul(Type other){
		if(other instanceof TypeInt)
			return this.mul((TypeInt)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeString mul(TypeInt other){
		StringBuilder result = new StringBuilder();
		for(long count=other.value.longValue(); count > 0; count--)
			result.append(this.value);
		return new TypeString(result.toString());
	}

	public TypeFloat div(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "div");
	}

	public TypeInt floordiv(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "floordiv");
	}

	public Type mod(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "mod");
	}

	public Type bin_and(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_and");
	}
	public Type bin_or(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_or");
	}
	public Type bin_xor(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_xor");
	}

	public Type pow(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "pow");
	}

	public int compareTo(Type other){
		if(other instanceof TypeString)
			return this.compareTo((TypeString)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public int compareTo(TypeString other){
		return this.value.compareTo(other.value);
	}

	public TypeInt len() {
		return new TypeInt(this.value.length());
	}

	public Type index(Type i){
		if(i instanceof TypeInt)
			return this.index((TypeInt)i);
		throw new ExpressionFault.TypeError(i, new TypeInt());
	}
	public Type index(TypeInt i){
		int offset = i.safeIntValue();
		if (offset < 0)
			offset += this.value.length();
		try {
			log.debug("index=%s", i);
			char c = this.value.charAt(offset);
			return new TypeString(c);
		} catch (IndexOutOfBoundsException e) {
			throw new ExpressionFault.IndexError(
			        offset, this.value.length());
		}
	}

	public Type slice(Type start, Type stop, Type step){
		if( (start == null || start instanceof TypeInt) &&
		    (stop == null || stop instanceof TypeInt) &&
		    (step == null || step instanceof TypeInt) )
			return this.slice((TypeInt)start, (TypeInt)stop, (TypeInt)step);
		throw new ExpressionFault.TypeError();
	}
	public Type slice(final TypeInt start, final TypeInt stop, final TypeInt step){
		final int len = this.value.length();
		final int step_ = step != null ? step.safeIntValue() : 1;

		if (step_ == 0)
			throw new ExpressionFault.ValueError("slice step cannot be 0");

		final int default_start = step_ > 0 ? 0 : -1;
		final int default_stop = step_ > 0 ? len : -1;

		int start_ = start != null ? start.safeIntValue() : default_start;
		if (start_ < 0)
			start_ += len;

		int stop_;
		if (stop != null) {
			stop_ = stop.safeIntValue();
			if (stop_ < 0)
				stop_ += len;
		} else {
			stop_ = default_stop;
		}

		//final int newsize = (stop_ - start_) / step_;
		StringBuilder ans = new StringBuilder();
		int i = -1; // value will not be ever used, I think
		try {
			for(i=start_; step_>0 ? i<stop_ : i>stop_ ; i+=step_)
				ans.append(this.value.charAt(i));
		} catch (IndexOutOfBoundsException e) {
			log.exception("this.value = %s, stop=%s, start=%s", e, this.value,
				      stop_, start_);
			throw new ExpressionFault.IndexError(i, len);
		}
		return new TypeString(ans.toString());
	}

	public TypeInt pos() {
		throw new ExpressionFault.Unsupported(this.getClass(), "pos");
	}
	public TypeInt neg() {
		throw new ExpressionFault.Unsupported(this.getClass(), "neg");
	}
	public TypeInt bin_neg() {
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_neg");
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
