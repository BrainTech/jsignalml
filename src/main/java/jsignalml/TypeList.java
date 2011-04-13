package jsignalml;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Iterator;
import static java.util.Collections.unmodifiableList;
import static java.lang.String.format;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.NotImplementedException;

public class TypeList extends Type implements Iterable<Type> {
	public final java.util.List<Type> value;

	public TypeList(java.util.List<? extends Type> value) {
		this.value = unmodifiableList(new ArrayList(value));
	}

	public TypeList(Type...items) {
		this.value = unmodifiableList(Arrays.asList(items));
	}

	@Override
	public TypeList make(Type other) {
		if (other instanceof TypeString)
			throw new NotImplementedException();
		if (other instanceof TypeList)
			return (TypeList)other;
		throw new ExpressionFault.TypeError();
	}

	public static TypeList make(Object... items) {
		java.util.List<Type> list = util.newLinkedList();
		for (Object item: items) {
			if (item instanceof java.lang.Integer) {
				list.add(new TypeInt((java.lang.Integer)item));
			} else if (item instanceof java.lang.Double) {
				list.add(new TypeFloat((java.lang.Double)item));
			} else if (item instanceof java.lang.Float) {
				list.add(new TypeFloat((java.lang.Float)item));
			} else if (item instanceof java.lang.String) {
				list.add(new TypeString((java.lang.String)item));
			} else {
				throw new RuntimeException();
			}
		}
		return new TypeList(list);
	}

	@Override
	public /*immutable*/ java.util.List<Type> getValue() {
		return this.value;
	}

	@Override
	public java.lang.String repr() {
		List<String> list = util.newArrayList(this.value.size());
		for(Type item: this.value)
			list.add(item.repr());
		return "[" + StringUtils.join(list, ", ") + "]";
	}

	@Override
	public boolean isTrue() {
		return this.value.isEmpty(); // XXX: add test and fix
	}

	@Override
	public Type binaryOp(BinaryOp op, TypeInt other)
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
	public Type binaryOp(BinaryOp op, TypeFloat other)
		throws ExpressionFault.TypeError
	{
		throw new ExpressionFault.TypeError();
	}

	@Override
	public Type binaryOp(BinaryOp op, TypeList other)
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
	public Iterator<Type> iterator() {
		return this.value.iterator();
	}


	@Override
	public Type _binaryOpType(BinaryOp op, TypeInt other)
	{
		/* 0 should be safe and efficient for all ops */
		return this.binaryOp(op, new TypeInt(0));
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeList other)
	{
		/* [] should be safe and efficient for all ops */
		return this.binaryOp(op, new TypeList());
	}

	public Type add(Type other){
		if(other instanceof TypeList)
			return this.add((TypeList)other);
		throw new ExpressionFault.TypeError();
	}
	public TypeList add(TypeList other){
		ArrayList<Type> result = new ArrayList<Type>(this.value);
		result.addAll(other.value);
		return new TypeList(result);
	}

	public Type sub(Type other){
		throw new ExpressionFault.TypeError();
	}

	public Type mul(Type other){
		if(other instanceof TypeInt)
			return this.mul((TypeInt)other);
		throw new ExpressionFault.TypeError();
	}
	public TypeList mul(TypeInt other){
		ArrayList<Type> result = new ArrayList<Type>(this.value);
		for(long count=other.value.longValue(); count > 1; count--)
			result.addAll(this.value);
		return new TypeList(result);
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

	public int compareTo(Type other){
		if(other instanceof TypeList)
			return this.compareTo((TypeList)other);
		throw new ExpressionFault.TypeError();
	}
	public int compareTo(TypeList other){
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

	public TypeInt len() {
		return new TypeInt(this.value.size());
	}

	public Type index(Type i){
		if(i instanceof TypeInt)
			return this.index((TypeInt)i);
		throw new ExpressionFault.TypeError();
	}
	public Type index(TypeInt i){
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

	public Type slice(Type start, Type stop, Type step){
		if( (start == null || start instanceof TypeInt) &&
		    (stop == null || stop instanceof TypeInt) &&
		    (step == null || step instanceof TypeInt) )
			return this.slice((TypeInt)start, (TypeInt)stop, (TypeInt)step);
		throw new ExpressionFault.TypeError();
	}
	public Type slice(final TypeInt start, final TypeInt stop, final TypeInt step){
		final int len = this.value.size();
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

		final int newsize = (stop_ - start_) / step_;
		List<Type> ans = util.newArrayList(newsize);
		int i = -1; // value will not be ever used, I think
		try {
			int j;
			for(i=start_, j=0; j<newsize; i+=step_, j++)
				ans.add(this.value.get(i));
		} catch (IndexOutOfBoundsException e) {
			log.exception("this.value = %s, stop=%s, start=%s", e, this.value,
				      stop_, start_);
			throw new ExpressionFault.IndexError(i, len);
		}
		return new TypeList(ans);
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
