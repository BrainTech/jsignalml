package jsignalml;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;

public class TypeList extends Type implements Iterable<Type> {
	public static final TypeList I = new TypeList();

	public final java.util.List<Type> value;

	public TypeList(java.util.List<? extends Type> value) {
		this.value = unmodifiableList(new ArrayList<Type>(value));
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
		throw new ExpressionFault.TypeError(other, this);
	}

	public static TypeList make(Object... items) {
		java.util.List<Type> list = util.newLinkedList();
		for (Object item: items) {
			if (item instanceof Integer) {
				list.add(new TypeInt((Integer) item));
			} else if (item instanceof Double) {
				list.add(new TypeFloat((Double) item));
			} else if (item instanceof Float) {
				list.add(new TypeFloat((Float) item));
			} else if (item instanceof String) {
				list.add(new TypeString((String) item));
			} else if (item instanceof Boolean) {
				list.add(new TypeBool((Boolean) item));
			} else if (item instanceof TypeBytes.ByteSequence) {
				list.add(new TypeBytes((TypeBytes.ByteSequence) item));
			} else {
				throw new RuntimeException("bad type " +
							   item.getClass());
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
		return !this.value.isEmpty();
	}

	@Override
	public Type binaryOp(BinaryOp op, TypeFloat other)
		throws ExpressionFault.Unsupported
	{
		throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
	}

	@Override
	public Type binaryOp(BinaryOp op, TypeList other)
		throws ExpressionFault.TypeError, ExpressionFault.Unsupported
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
			throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
		default:
			throw new ExpressionFault.TypeError(this, other);
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
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeList add(TypeList other){
		ArrayList<Type> result = new ArrayList<Type>(this.value);
		result.addAll(other.value);
		return new TypeList(result);
	}

	public Type sub(Type other){
		throw new ExpressionFault.Unsupported(this.getClass(), "sub");
	}

	public Type mul(Type other){
		if(other instanceof TypeInt)
			return this.mul((TypeInt)other);
		throw new ExpressionFault.TypeError(other, this);
	}
	public TypeList mul(TypeInt other){
		ArrayList<Type> result = new ArrayList<Type>(this.value);
		for(long count=other.value.longValue(); count > 1; count--)
			result.addAll(this.value);
		return new TypeList(result);
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
		if(other instanceof TypeList)
			return this.compareTo((TypeList)other);
		throw new ExpressionFault.TypeError(other, this);
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
		throw new ExpressionFault.TypeError(i, new TypeInt());
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

		for(int i=start_; step_>0 ? i<stop_ : i>stop_; i+=step_) {
			if (i < 0 || i >= this.value.size())
				throw new ExpressionFault.IndexError(i, len);
			ans.add(this.value.get(i));
		}

		return new TypeList(ans);
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
		String result = "TypeList [";
		int i = 0;
		for (Type type : this.value) {
			if (i > 0) result += ", ";
			result += (type != null) ? type.toString() : "null";
			i ++;
		}
		result += "]";
		return result;
	}
}
