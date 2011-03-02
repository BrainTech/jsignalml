package jsignalml;

import static java.lang.String.format;

public abstract class TypeObject extends Type {
	final ASTNode node;

	TypeObject(ASTNode node) {
		this.node = node;
	}

	@Override public Type access(String item)
	{
		throw new ExpressionFault.AttributeError(item);
	}

	@Override Type add(Type b) { throw new ExpressionFault.TypeError(); }
	@Override Type sub(Type b) { throw new ExpressionFault.TypeError(); }
	@Override Type mul(Type b) { throw new ExpressionFault.TypeError(); }
	@Override Type div(Type b) { throw new ExpressionFault.TypeError(); }
	@Override Type floordiv(Type b) { throw new ExpressionFault.TypeError(); }
	@Override Type mod(Type b) { throw new ExpressionFault.TypeError(); }

	@Override Type bin_and(Type b) { throw new ExpressionFault.TypeError(); }
	@Override Type bin_or(Type b) { throw new ExpressionFault.TypeError(); }
	@Override Type bin_xor(Type b) { throw new ExpressionFault.TypeError(); }

	@Override Type pow(Type b) { throw new ExpressionFault.TypeError(); }

	@Override Type pos() { throw new ExpressionFault.TypeError(); }
	@Override Type neg() { throw new ExpressionFault.TypeError(); }
	@Override Type bin_neg() { throw new ExpressionFault.TypeError(); }

	@Override public boolean isTrue(){ return true; }
	@Override public String repr(){ return this.toString(); }

	@Override public Type _binaryOpType(Type.BinaryOp op, TypeInt other)
	{
		throw new ExpressionFault.TypeError();
	}

	@Override public Object getValue(){ return this; }

	@Override
	public int hashCode() {
		return this.superHashCode();
	}

	@Override
	public java.lang.String toString() {
		return format("Node[%s]", this.node);
	}

	@Override public Type make(Type value){ throw new ExpressionFault.TypeError(); }

	@Override public int compareTo(Type other){
		if(this == other)
			return 0;
		if(this.getClass() == other.getClass())
			return 1;
		throw new ExpressionFault.TypeError();
	}
}
