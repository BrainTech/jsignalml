package jsignalml;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import static java.util.Collections.unmodifiableList;

interface JavaType {
	JavaType add(JavaType b);
	JavaType sub(JavaType b);
	JavaType mul(JavaType b);
	JavaType div(JavaType b);
	JavaType floordiv(JavaType b);
	JavaType mod(JavaType b);

	/*
	JavaType bin_and(JavaType b);
	JavaType bin_or(JavaType b);
	JavaType bin_xor(JavaType b);

	JavaType pow(JavaType b);
	*/

	JavaType cmp(JavaType b);

	JavaType bool();
	JavaType pos();
	JavaType neg();

	class Int extends BigInteger implements JavaType {
		static Int False = new Int(0);
		static Int True = new Int(1);

		public Int(String repr){
			super(repr);
		}
		Int(BigInteger value){
			super(value.toByteArray());
		}

		static byte[] _long_to_arr8(long value){
			byte[] arr = new byte[8];
			for(int i=0; i<arr.length; i++)
				arr[i] = (byte)(value >> (7-i));
			return arr;
		}

		Int(long value){
			super(_long_to_arr8(value));
		}
		Int(double value){
			super(_long_to_arr8((long)value));
		}

		public JavaType add(JavaType other){
			if(other instanceof Int)
				return this.add((Int)other);
			if(other instanceof Float)
				return this.add((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int add(Int other){
			return new Int(this.add(other));
		}
		public Float add(Float other){
			return new Float(this.doubleValue() + other.value);
		}

		public JavaType sub(JavaType other){
			if(other instanceof Int)
				return this.sub((Int)other);
			if(other instanceof Float)
				return this.sub((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int sub(Int other){
			return new Int(this.sub(other));
		}
		public Float sub(Float other){
			return new Float(this.doubleValue() - other.value);
		}

		public JavaType mul(JavaType other){
			if(other instanceof Int)
				return this.mul((Int)other);
			if(other instanceof Float)
				return this.mul((Float)other);
			if(other instanceof Str)
				return this.mul((Str)other);
			if(other instanceof List)
				return this.mul((List)other);
			throw new ExpressionFault.TypeError();
		}
		public Int mul(Int other){
			return new Int(this.multiply(other));
		}
		public Float mul(Float other){
			return new Float(this.doubleValue() * other.value);
		}
		public Str mul(Str other){
			return other.mul(this);
		}
		public List mul(List other){
			return other.mul(this);
		}

		public Float div(JavaType other){
			if(other instanceof Int)
				return this.div((Int)other);
			if(other instanceof Float)
				return this.div((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float div(Int other){
			return new Float(this.doubleValue() / other.doubleValue());
		}
		public Float div(Float other){
			return new Float(this.doubleValue() / other.value);
		}

		public Int floordiv(JavaType other){
			if(other instanceof Int)
				return this.floordiv((Int)other);
			if(other instanceof Float)
				return this.floordiv((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int floordiv(Int other){
			return new Int(this.divide(other));
		}
		public Int floordiv(Float other){
			return new Int(this.doubleValue() / other.value);
		}

		public JavaType mod(JavaType other){
			if(other instanceof Int)
				return this.mod((Int)other);
			if(other instanceof Float)
				return this.mod((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int mod(Int other){
			return new Int(this.mod(other));
		}
		public Float mod(Float other){
			return new Float(this).mod(other);
		}

		public Int cmp(JavaType other){
			if(other instanceof Int)
				return this.cmp((Int)other);
			if(other instanceof Float)
				return this.cmp((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int cmp(Int other){
			return new Int(this.compareTo(other));
		}
		public Int cmp(Float other){
			int value = java.lang.Double.compare(this.doubleValue(),
							     other.value);
			return new Int(value);
		}

		public Int bool(){
			return this.equals(BigInteger.ZERO) ? Int.False : Int.True;
		}

		public Int pos() { return this; }
		public Int neg() { return new Int(this.negate()); }
	}

	class Float implements JavaType {
		final double value;
		Float(String repr){
			this.value = java.lang.Double.parseDouble(repr);
		}
		Float(double value){
			this.value = value;
		}
		Float(Int value){
			this.value = value.doubleValue();
		}


		public JavaType add(JavaType other){
			if(other instanceof Int)
				return this.add((Int)other);
			if(other instanceof Float)
				return this.add((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float add(Int other){
			return new Float(this.value + other.doubleValue());
		}
		public Float add(Float other){
			return new Float(this.value + other.value);
		}

		public JavaType sub(JavaType other){
			if(other instanceof Int)
				return this.sub((Int)other);
			if(other instanceof Float)
				return this.sub((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float sub(Int other){
			return new Float(this.value - other.doubleValue());
		}
		public Float sub(Float other){
			return new Float(this.value - other.value);
		}

		public JavaType mul(JavaType other){
			if(other instanceof Int)
				return this.mul((Int)other);
			if(other instanceof Float)
				return this.mul((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float mul(Int other){
			return new Float(this.value * other.doubleValue());
		}
		public Float mul(Float other){
			return new Float(this.value * other.value);
		}

		public Float div(JavaType other){
			if(other instanceof Int)
				return this.div((Int)other);
			if(other instanceof Float)
				return this.div((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float div(Int other){
			return new Float(this.value / other.doubleValue());
		}
		public Float div(Float other){
			return new Float(this.value / other.value);
		}

		public Int floordiv(JavaType other){
			if(other instanceof Int)
				return this.floordiv((Int)other);
			if(other instanceof Float)
				return this.floordiv((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int floordiv(Int other){
			return new Int(this.value / other.doubleValue());
		}
		public Int floordiv(Float other){
			return new Int(this.value / other.value);
		}

		public Float mod(JavaType other){
			if(other instanceof Int)
				return this.mod((Int)other);
			if(other instanceof Float)
				return this.mod((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Float mod(Float other){
			double value = this.value % other.value;
			if (value < 0)
				value += other.value;
			return new Float(value);
		}
		public Float mod(Int other){
			return this.mod(new Float(other));
		}

		public Int cmp(JavaType other){
			if(other instanceof Int)
				return this.cmp((Int)other);
			if(other instanceof Float)
				return this.cmp((Float)other);
			throw new ExpressionFault.TypeError();
		}
		public Int cmp(Int other){
			int value = java.lang.Double.compare(this.value,
							     other.doubleValue());
			return new Int(value);
		}
		public Int cmp(Float other){
			int value = java.lang.Double.compare(this.value,
							     other.value);
			return new Int(value);
		}

		public Int bool(){
			return this.value == 0.0 ? Int.False : Int.True;
		}

		public Float pos() { return this; }
		public Float neg() { return new Float(-this.value); }
	}

	class Str implements JavaType {
		java.lang.String value;
		Str(java.lang.String value){
			this.value = value;
		}

		public JavaType add(JavaType other){
			if(other instanceof Str)
				return this.add((Str)other);
			throw new ExpressionFault.TypeError();
		}
		public Str add(Str other){
			return new Str(this.value + other.value);
		}

		public JavaType sub(JavaType other){
			throw new ExpressionFault.TypeError();
		}

		public JavaType mul(JavaType other){
			if(other instanceof Int)
				return this.mul((Int)other);
			throw new ExpressionFault.TypeError();
		}
		public Str mul(Int other){
			StringBuilder result = new StringBuilder();
			for(long count=other.longValue(); count > 0; count--)
				result.append(this.value);
			return new Str(result.toString());
		}

		public Float div(JavaType other){
			throw new ExpressionFault.TypeError();
		}

		public Int floordiv(JavaType other){
			throw new ExpressionFault.TypeError();
		}

		public JavaType mod(JavaType other){
			throw new ExpressionFault.TypeError();
		}

		public Int cmp(JavaType other){
			if(other instanceof Str)
				return this.cmp((Str)other);
			throw new ExpressionFault.TypeError();
		}
		public Int cmp(Str other){
			return new Int(this.value.compareTo(other.value));
		}

		public Int bool(){
			return this.value.isEmpty() ? Int.False : Int.True;
		}

		public Int pos() {
			throw new ExpressionFault.TypeError();
		}
		public Int neg() {
			throw new ExpressionFault.TypeError();
		}
	}
	class List implements JavaType {
		final java.util.List<JavaType> value;

		public List(Collection<? extends JavaType> items){
			this.value = unmodifiableList(new ArrayList<JavaType>(items));
		}

		public JavaType add(JavaType other){
			if(other instanceof List)
				return this.add((List)other);
			throw new ExpressionFault.TypeError();
		}
		public List add(List other){
			ArrayList<JavaType> result = new ArrayList<JavaType>(this.value);
			result.addAll(other.value);
			return new List(result);
		}

		public JavaType sub(JavaType other){
			throw new ExpressionFault.TypeError();
		}

		public JavaType mul(JavaType other){
			if(other instanceof Int)
				return this.mul((Int)other);
			throw new ExpressionFault.TypeError();
		}
		public List mul(Int other){
			ArrayList<JavaType> result = new ArrayList<JavaType>(this.value);
			for(long count=other.longValue(); count > 1; count--)
				result.addAll(this.value);
			return new List(result);
		}

		public Float div(JavaType other){
			throw new ExpressionFault.TypeError();
		}

		public Int floordiv(JavaType other){
			throw new ExpressionFault.TypeError();
		}

		public JavaType mod(JavaType other){
			throw new ExpressionFault.TypeError();
		}

		public Int cmp(JavaType other){
			if(other instanceof List)
				return this.cmp((List)other);
			throw new ExpressionFault.TypeError();
		}
		public Int cmp(List other){
			Iterator<JavaType> itother = other.value.iterator();

			for(JavaType item: this.value){
				if(!itother.hasNext())
					return new Int(1);
				JavaType itemother = itother.next();
				Int cmp = (Int) item.cmp(itemother);
				if(cmp.compareTo(Int.False) != 0)
					return cmp;
			}

			return new Int(0);
		}

		public Int bool(){
			return this.value.isEmpty() ? Int.False : Int.True;
		}

		public Int pos() {
			throw new ExpressionFault.TypeError();
		}
		public Int neg() {
			throw new ExpressionFault.TypeError();
		}
	}
}