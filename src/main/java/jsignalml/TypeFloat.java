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

public class TypeFloat extends Type {

	public final double value;
	public TypeFloat(double value) {
		this.value = value;
	}
	public TypeFloat(java.lang.String text) {
		this(new Double(text));
	}
	public TypeFloat(BigInteger value) {
		this.value = value.doubleValue();
	}
	public TypeFloat(TypeInt value) {
		this(value.value);
	}

	public TypeFloat() {
		this(0.0);
	}

	@Override
	public TypeFloat make(Type value) {
		if (value instanceof TypeFloat)
			return (TypeFloat)value;
		if (value instanceof TypeInt)
			return new TypeFloat(((TypeInt)value).getValue());
		if (value instanceof TypeString)
			return new TypeFloat(((TypeString)value).getValue());
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
		if(other instanceof TypeInt)
			return this.compareTo((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.compareTo((TypeFloat)other);
		throw new ExpressionFault.TypeError();
	}
	public int compareTo(TypeInt other){
		return -other.compareTo(this);
	}
	public int compareTo(TypeFloat other){
		return java.lang.Double.compare(this.value,
						other.value);
	}

	public Type add(Type other){
		if(other instanceof TypeInt)
			return this.add((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.add((TypeFloat)other);
		throw new ExpressionFault.TypeError();
	}
	public TypeFloat add(TypeInt other){
		return new TypeFloat(this.value + other.value.doubleValue());
	}
	public TypeFloat add(TypeFloat other){
		return new TypeFloat(this.value + other.value);
	}

	public Type sub(Type other){
		if(other instanceof TypeInt)
			return this.sub((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.sub((TypeFloat)other);
		throw new ExpressionFault.TypeError();
	}
	public TypeFloat sub(TypeInt other){
		return new TypeFloat(this.value - other.value.doubleValue());
	}
	public TypeFloat sub(TypeFloat other){
		return new TypeFloat(this.value - other.value);
	}

	public Type mul(Type other){
		if(other instanceof TypeInt)
			return this.mul((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.mul((TypeFloat)other);
		throw new ExpressionFault.TypeError();
	}
	public TypeFloat mul(TypeInt other){
		return new TypeFloat(this.value * other.value.doubleValue());
	}
	public TypeFloat mul(TypeFloat other){
		return new TypeFloat(this.value * other.value);
	}

	public TypeFloat div(Type other){
		if(other instanceof TypeInt)
			return this.div((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.div((TypeFloat)other);
		throw new ExpressionFault.TypeError();
	}
	public TypeFloat div(TypeInt other){
		return new TypeFloat(this.value / other.value.doubleValue());
	}
	public TypeFloat div(TypeFloat other){
		return new TypeFloat(this.value / other.value);
	}

	public TypeInt floordiv(Type other){
		if(other instanceof TypeInt)
			return this.floordiv((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.floordiv((TypeFloat)other);
		throw new ExpressionFault.TypeError();
	}
	public TypeInt floordiv(TypeInt other){
		return new TypeInt(Math.round(Math.floor(this.value / other.value.doubleValue())));
	}
	public TypeInt floordiv(TypeFloat other){
		return new TypeInt(Math.round(Math.floor(this.value / other.value)));
	}

	public TypeFloat mod(Type other){
		if(other instanceof TypeInt)
			return this.mod((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.mod((TypeFloat)other);
		throw new ExpressionFault.TypeError();
	}
	public TypeFloat mod(TypeFloat other){
		double value = this.value % other.value;
		if ((other.value > 0 && value < 0) ||
		    (other.value < 0 && value > 0))
			value += other.value;
		return new TypeFloat(value);
	}
	public TypeFloat mod(TypeInt other){
		return this.mod(new TypeFloat(other));
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
		if(other instanceof TypeInt)
			return this.pow((TypeInt)other);
		if(other instanceof TypeFloat)
			return this.pow((TypeFloat)other);
		throw new ExpressionFault.TypeError();
	}
	public TypeFloat pow(TypeInt other){
		return new TypeFloat(Math.pow(this.value, other.value.doubleValue()));
	}
	public TypeFloat pow(TypeFloat other){
		return new TypeFloat(Math.pow(this.value, other.value));
	}

	public Type index(Type i){
		throw new ExpressionFault.TypeError();
	}

	public TypeFloat pos() { return this; }
	public TypeFloat neg() { return new TypeFloat(-this.value); }
	public TypeInt bin_neg() {
		throw new ExpressionFault.TypeError();
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeInt other)
	{
		/* 1 should be safe for all ops */
		return this.binaryOp(op, new TypeInt(1));
	}

	@Override
	public Type _binaryOpType(BinaryOp op, TypeFloat other)
	{
		/* 1 should be safe for all ops */
		return this.binaryOp(op, new TypeFloat(1.0));
	}


	@Override
	public Type unaryOp(UnaryOp op)
		throws ExpressionFault.TypeError
	{
		switch (op) {
		case POS:
			return this;
		case NEG:
			return new TypeFloat(-this.value);
		default:
			throw new ExpressionFault.TypeError();
		}
	}
}
