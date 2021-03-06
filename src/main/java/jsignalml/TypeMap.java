package jsignalml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TypeMap extends Type /* implements Map<Type, Type>  */ {
	public static final TypeMap I = new TypeMap();

	public final HashMap<Type, Type> map;

	public TypeMap(Map<? extends Type, ? extends Type> map) {
		this.map = new HashMap<Type, Type>(map);
		// make a copy to be independent from the external map
	}

	public TypeMap(List<Map.Entry<Type, Type>> list) {
		this.map = new HashMap<Type, Type>(list.size());
		for(Map.Entry<Type, Type> entry: list)
			this.map.put(entry.getKey(), entry.getValue());
	}

	public TypeMap(Type...items) {
		if(items.length % 2 != 0)
			throw new ExpressionFault.ArgMismatch();
		this.map = new HashMap<Type, Type>(items.length / 2);
		for(int i=0; i<items.length; i+=2)
			this.map.put(items[i], items[i+1]);
	}

	@Override
	public TypeMap make(Type other) {
		if (other instanceof TypeMap)
			return new TypeMap(((TypeMap)other).map);
		throw new ExpressionFault.TypeError(other, this);
	}

	public static TypeMap make(Object... items) {
		Type typed[] = new Type[items.length];
		for (int i=0; i<items.length; i++) {
			Object item = items[i];
			if (item instanceof Integer) {
				typed[i] = new TypeInt((Integer) item);;
			} else if (item instanceof Double) {
				typed[i] = new TypeFloat((Double) item);
			} else if (item instanceof Float) {
				typed[i] = new TypeFloat((Float) item);
			} else if (item instanceof String) {
				typed[i] = new TypeString((String) item);
			} else if (item instanceof Boolean) {
				typed[i] = new TypeBool((Boolean) item);
			} else if (item instanceof TypeBytes.ByteSequence) {
				typed[i] = new TypeBytes((TypeBytes.ByteSequence) item);
			} else {
				throw new RuntimeException();
			}
		}
		return new TypeMap(typed);
	}

	@Override
	public HashMap<Type, Type> getValue() {
		return this.map;
	}

	public String repr() {
		StringBuilder repr = new StringBuilder("{");
		boolean first = true;
		for (Map.Entry<Type, Type> entry: this.map.entrySet()) {
			if (first)
				first = false;
			else
				repr.append(", ");
			repr.append(entry.getKey().repr());
			repr.append(":");
			repr.append(entry.getValue().repr());
		}
		repr.append("}");
		return repr.toString();
	}

	public boolean isTrue() {
		return !this.map.isEmpty();
	}

	public Type binaryOp(BinaryOp op, TypeInt other)
		throws ExpressionFault.Unsupported
	{
		throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
	}

	public Type binaryOp(BinaryOp op, TypeFloat other)
		throws ExpressionFault.Unsupported
	{
		throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
	}

	public Type binaryOp(BinaryOp op, TypeList other)
		throws ExpressionFault.Unsupported
	{
		throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
	}

	@Override
	public Type binaryOp(BinaryOp op, TypeMap other)
		throws ExpressionFault.TypeError, ExpressionFault.Unsupported
	{
		final boolean value;
		switch (op) {
		case EQ:
			value = this.map.entrySet().equals(other.map.entrySet());
			break;
		case NE:
			value = !this.map.entrySet().equals(other.map.entrySet());
			break;
		case LT:
			value = other.map.entrySet().containsAll(this.map.entrySet())
				&& !this.map.entrySet().containsAll(other.map.entrySet());
			break;
		case GT:
			value = this.map.entrySet().containsAll(other.map.entrySet())
				&& !other.map.entrySet().containsAll(this.map.entrySet());
			break;
		case LE:
			value = other.map.entrySet().containsAll(this.map.entrySet());
			break;
		case GE:
			value = this.map.entrySet().containsAll(other.map.entrySet());
			break;

		case LOG_AND:
		case LOG_OR:
			throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
		default:
			throw new ExpressionFault.TypeError(this.getClass(), other.getClass());
		}

		return new TypeInt(value);
	}

	public Iterator<Map.Entry<Type, Type>> iterator() {
		return this.map.entrySet().iterator();
	}

	public int compareTo(Type other){
		if(other instanceof TypeMap)
			return this.compareTo((TypeMap)other);
		throw new ExpressionFault.TypeError(other, this);
	}

	/* No strict ordering exists for Maps. Therefore operations are
	 * performed directly in the binaryOp function.
	 */
	public int compareTo(TypeMap other){
		throw new RuntimeException();
	}

	public TypeInt len() {
		return new TypeInt(this.map.size());
	}

	public Type index(Type key){
		Type ans = this.map.get(key);
		if (ans == null)
			throw new ExpressionFault.KeyError(key);
		return ans;
	}

	public Type index(Type key, Type default_){
		Type ans = this.map.get(key);
		return ans != null ? ans : default_;
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeInt other)
	{
		throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeMap other)
	{
		/* {} should be safe and efficient for all ops */
		return this.binaryOp(op, new TypeMap());
	}

	public Type add(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "add");
	}

	public Type sub(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "sub");
	}

	public Type mul(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "mul");
	}
	public TypeList mul(TypeInt other){
		throw new ExpressionFault.Unsupported(this.getClass(), "mul");
	}

	public TypeFloat div(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "div");
	}

	public TypeInt floordiv(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "floordiv");
	}

	public TypeInt ceildiv(Type other){
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
	public String toString() {
		String result = "TypeMap [";
		Set<Map.Entry<Type, Type>> entries = this.map.entrySet();
		int i = 0;
		for(Map.Entry<Type, Type> entry: entries) {
			Type key = entry.getKey();
			Type val = entry.getValue();
			if (i > 0) result += ", ";
			result += key + " = " + val;
			i ++;
		}
		result += "]";
		return result;
	}
}
