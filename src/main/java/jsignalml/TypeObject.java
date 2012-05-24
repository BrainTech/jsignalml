package jsignalml;

public abstract class TypeObject extends Type {

	@Override public Type access(String item)
	{
		throw new ExpressionFault.AttributeError(item);
	}

	@Override public Type add(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "add");
	}

	@Override public Type sub(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "sub");
	}

	@Override public Type mul(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "mul");
	}

	@Override public Type div(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "div");
	}

	@Override public Type floordiv(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "floordiv");
	}

	@Override public Type mod(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "mod");
	}

	@Override public Type bin_and(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_and");
	}

	@Override public Type bin_or(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_or");
	}

	@Override public Type bin_xor(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_xor");
	}

	@Override public Type pow(Type b) {
		throw new ExpressionFault.Unsupported(this.getClass(), "pow");
	}

	@Override public Type pos() {
		throw new ExpressionFault.Unsupported(this.getClass(), "pos");
	}

	@Override public Type neg() {
		throw new ExpressionFault.Unsupported(this.getClass(), "neg");
	}

	@Override public Type bin_neg() {
		throw new ExpressionFault.Unsupported(this.getClass(), "bin_neg");
	}

	@Override public boolean isTrue(){ return true; }
	@Override public String repr(){ return this.toString(); }

	@Override public Type _binaryOpType(Type.BinaryOp op, TypeInt other)
	{
		throw new ExpressionFault.Unsupported(this.getClass(), op.toString());
	}

	@Override public Object getValue(){ return this; }

	@Override public String toString() {
		return "TypeObject " + super.toString();
	}

	@Override
	public int hashCode() {
		return this.superHashCode();
	}

	@Override public Type make(Type value){
		throw new ExpressionFault.Unsupported(this.getClass(), "make");
	}

	@Override public int compareTo(Type other){
		if(this == other)
			return 0;
		if(this.getClass() == other.getClass())
			return 1;
		throw new ExpressionFault.TypeError(other, this);
	}
}
