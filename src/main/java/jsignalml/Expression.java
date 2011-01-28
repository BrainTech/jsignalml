package jsignalml;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableList;

import org.apache.commons.lang.StringUtils;

public abstract class Expression {
	/**
	 * Visitor pattern implementation.
	 *
	 * Visit all the nodes in the Expression starting at this, calling
	 * v.visit on all nodes. The traversal order is depth-first, like
	 * in normal expression evaluation.
	 */
	public abstract <T> T accept(ExpressionVisitor<T> v);

	public static class BinaryOp extends Expression {
		final Expression left, right;
		final Type.BinaryOp op;

		public BinaryOp(int opcode, Expression left, Expression right)
		{
			this.op = Type.BinaryOp.get(opcode);
			this.left = left;
			this.right = right;
		}

		@Override
		public <T> T accept(ExpressionVisitor<T> v){
			T left = this.left.accept(v);
			T right = this.right.accept(v);
			return v.visit(this, left, right);
		}

		@Override
		public String toString()
		{
			return format("%s %s %s", left, op.rep, right);
		}
	}

	public static class LogicalBinaryOp extends BinaryOp {
		public LogicalBinaryOp(int opcode, Expression left, Expression right)
		{
			super(opcode, left, right);
		}

		/**
		 * This one is special. We have to deviate from the normal
		 * pattern of visit left-visit right-perform op, because this
		 * would break lazy evaluation.
		 */
		@Override
		public <T> T accept(ExpressionVisitor<T> v){
			T left = this.left.accept(v);
			return v.visit(this, left);
		}


	}

	public static class UnaryOp extends Expression {
		final Type.UnaryOp op;
		final Expression sub;

		public UnaryOp(int opcode, Expression sub)
		{
			this.op = Type.UnaryOp.get(opcode);
			this.sub = sub;
		}

		@Override
		public String toString()
		{
			return format("%s %s", op.rep, sub);
		}

		@Override
		public <T> T accept(ExpressionVisitor<T> v){
			T sub = this.sub.accept(v);
			return v.visit(this, sub);
		}
	}

	public static class Call extends Expression {
		final String name;
		final /*immutable*/ List<Expression> args;

		Call(String name, List<? extends Expression> args) {
			this.name = name;
			this.args = unmodifiableList(new ArrayList(args));
		}

		Call(String name) {
			this(name, new ArrayList());
		}

		@Override
		public String toString()
		{
			return this.name + "(" + StringUtils.join(this.args, ", ") + ")";
		}

		public <T> T accept(ExpressionVisitor<T> v)
		{
			List<T> vals = util.newLinkedList();

			for (int i = 0; i < this.args.size(); i++)
				vals.add( this.args.get(i).accept(v) );

			return v.visit(this, vals);
		}
	}


	public static class List_ extends Expression {
		final /*immutable*/ List<Expression> args;

		List_(List<? extends Expression> args) {
			this.args = unmodifiableList(args!=null ? new ArrayList(args) : new ArrayList());
		}

		@Override
		public String toString() {
			return "[" + StringUtils.join(this.args, ", ") + "]";
		}

		public <T> T accept(ExpressionVisitor<T> v)
		{
			List<T> vals = util.newLinkedList();

			for (int i = 0; i < this.args.size(); i++)
				vals.add( this.args.get(i).accept(v) );

			return v.visit(this, vals);
		}
	}


	public static class Index extends Expression {
		final Expression item;
		final Expression index;

		Index(Expression item, Expression index) {
			this.item = item;
			this.index = index;
		}

		@Override
		public String toString()
		{
			return format("%s[%s]", item, index);
		}

		@Override
		public <T> T accept(ExpressionVisitor<T> v)
		{
			final T seq = this.item.accept(v);
			final T index = this.index.accept(v);
			return v.visit(this, seq, index);
		}
	}

	public static class Const extends Expression {
		public final Type value;

		public Const(Type value) {
			this.value = value;
		}

		@Override
		public String toString()
		{
			return this.value.repr();
		}

		public static Expression make(String str) {
			assert str != null;
			return new Const(new Type.String(str));
		}
		public static Expression make(int integer) {
			return new Const(new Type.Int(integer));
		}
		public static Expression make(double real) {
			return new Const(new Type.Float(real));
		}

		@Override
		public <T> T accept(ExpressionVisitor<T> v)
		{
			return v.visit(this);
		}
	}

	public static class Ternary extends Expression {
		public final Expression q, a, b;

		public Ternary(Expression q, Expression a, Expression b)
		{
			this.q = q;
			this.a = a;
			this.b = b;
		}

		@Override
		public String toString()
		{
			return format("if %s then %s else %s", q, a, b);
		}

		public <T> T accept(ExpressionVisitor<T> v)
		{
			final T cond = this.q.accept(v);
			return v.visit(this, cond);
		}
	}

	/*
	 * This is a helper expression node to be used in interactive
	 * expression script parsing and execution.
	 */
	public static class Assign extends Expression {
		public final String id;
		public final Expression value;

		public Assign(String id, Expression value) {
			this.id = id;
			this.value = value;
		}

		public <T> T accept(ExpressionVisitor<T> v)
		{
			return v.visit(this);
		}

		@Override
		public String toString()
		{
			return format("%s = %s", id, value);
		}
	}
}
