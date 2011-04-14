package jsignalml;

import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang.NotImplementedException;

public class TypeMap extends Type /* implements Map<Type, Type>  */ {
	public final HashMap<Type, Type> map;

	public TypeMap(Map<? extends Type, ? extends Type> map) {
		this.map = new HashMap(map);
		// make a copy to be independent from the external map
	}

	public TypeMap(List<Map.Entry<Type, Type>> list) {
		this.map = new HashMap(list.size());
		for(Map.Entry<Type, Type> entry: list)
			this.map.put(entry.getKey(), entry.getValue());
	}

	public TypeMap(Type...items) {
		if(items.length % 2 != 0)
			throw new ExpressionFault.ArgMismatch();
		this.map = new HashMap(items.length / 2);
		for(int i=0; i<items.length; i+=2)
			this.map.put(items[i], items[i+1]);
	}

	@Override
	public TypeMap make(Type other) {
		if (other instanceof TypeMap)
			return new TypeMap(((TypeMap)other).map);
		throw new ExpressionFault.TypeError();
	}

	public static TypeMap make(Object... items) {
		Type typed[] = new Type[items.length];
		for (int i=0; i<items.length; i++) {
			Object item = items[i];
			if (item instanceof java.lang.Integer) {
				typed[i] = new TypeInt((java.lang.Integer)item);;
			} else if (item instanceof java.lang.Double) {
				typed[i] = new TypeFloat((java.lang.Double)item);
			} else if (item instanceof java.lang.Float) {
				typed[i] = new TypeFloat((java.lang.Float)item);
			} else if (item instanceof java.lang.String) {
				typed[i] = new TypeString((java.lang.String)item);
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

	public java.lang.String repr() {
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
		throws ExpressionFault.TypeError
	{
		throw new ExpressionFault.TypeError();
	}

	public Type binaryOp(BinaryOp op, TypeFloat other)
		throws ExpressionFault.TypeError
	{
		throw new ExpressionFault.TypeError();
	}

	public Type binaryOp(BinaryOp op, TypeList other)
		throws ExpressionFault.TypeError
	{
		throw new ExpressionFault.TypeError();
	}

	@Override
	public Type binaryOp(BinaryOp op, TypeMap other)
		throws ExpressionFault.TypeError
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
			throw new RuntimeException();
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
		throw new ExpressionFault.TypeError();
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

	@Override
	public Type _binaryOpType(BinaryOp op, TypeInt other)
	{
		throw new ExpressionFault.TypeError();
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeMap other)
	{
		/* {} should be safe and efficient for all ops */
		return this.binaryOp(op, new TypeMap());
	}

	public Type add(Type other){
		throw new ExpressionFault.TypeError();
	}

	public Type sub(Type other){
		throw new ExpressionFault.TypeError();
	}

	public Type mul(Type other){
		throw new ExpressionFault.TypeError();
	}
	public TypeList mul(TypeInt other){
		throw new ExpressionFault.TypeError();
	}

	public TypeFloat div(Type other){
		throw new ExpressionFault.TypeError();
	}

	public TypeInt floordiv(Type other){
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

	public TypeInt bool(){
		return this.isTrue() ? TypeInt.False : TypeInt.True;
	}

	public TypeInt pos() {
		throw new ExpressionFault.TypeError();
	}
	public TypeInt neg() {
		throw new ExpressionFault.TypeError();
	}
	public TypeInt bin_neg() {
		throw new ExpressionFault.TypeError();
	}
}
