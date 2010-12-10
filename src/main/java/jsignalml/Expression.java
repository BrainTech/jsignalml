package jsignalml;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableList;

public abstract class Expression {
	public abstract Type eval(CallHelper state);
	public abstract String toJava();

	public static class BinaryOp extends Expression {
		final Expression left, right;
		final Type.BinaryOp op;

		public BinaryOp(int opcode, Expression left, Expression right)
		{
			this.op = Type.BinaryOp.get(opcode);
			this.left = left;
			this.right = right;
		}

		public Type eval(CallHelper state)
		{
			Type left = this.left.eval(state);
			Type right = this.right.eval(state);

			return left.binaryOp(this.op, right);
		}

		public String toString()
		{
			return format("%s %s %s", left, op.rep, right);
		}
		
		public String toJava()
		{
			String left = this.left.toJava();
			String right = this.right.toJava();
			if (op.javaMethod == "cmp") 
			{
				return format("(%s.cmp(%s) %s 0 ? JavaType.Int.True : JavaType.Int.False)",
					      left, right, op.rep);
			} else {
				return format("%s.%s(%s)", left, op.javaMethod, right);
			}
		}
	}

	public static class LogicalBinaryOp extends BinaryOp {
		public LogicalBinaryOp(int opcode, Expression left, Expression right)
		{
			super(opcode, left, right);
		}

		public Type eval(CallHelper state)
		{
			Type left = this.left.eval(state);
			switch (this.op) {
			case LOG_AND:
				if (!left.isTrue())
					return left;
				break;
			case LOG_OR:
				if (left.isTrue())
					return left;
				break;
			default:
				throw new RuntimeException();
			}

			Type right = this.right.eval(state);
			return right;
		}

		public String toJava()
		{
			String left = this.left.toJava();
			String right = this.right.toJava();
			switch (this.op) {
			case LOG_AND:
				return format("( %s ? %s : %s )", left, right, left);
			case LOG_OR:
				return format("( %s ? %s : %s )", left, left, right);
			default:
				throw new RuntimeException();
			}
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

		public Type eval(CallHelper state)
		{
			Type sub = this.sub.eval(state);
			if (this.op == Type.UnaryOp.LOG_NOT)
				return sub.logical_not();
			else
				return sub.unaryOp(this.op);
		}

		public String toString()
		{
			return format("%s %s", op.rep, sub);
		}

		public String toJava()
		{
			return format("%s.%s()", sub.toJava(), op.javaMethod);
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

		public Type eval(CallHelper state)
		{
			Type vals[] = new Type[this.args.size()];
			for (int i = 0; i < vals.length; i++)
				vals[i] = this.args.get(i).eval(state);

			return state.call(this.name, vals);
		}

		public String toString()
		{
			return this.name + "(" + Type.String.join(", ", this.args) + ")";
		}

		public String toJava()
		{
			if (this.args.size() == 0)
				return JavaGen.makeIdentifier(this.name);

			String code = JavaGen.makeIdentifier(this.name) + "(";
			boolean first = true;
			for (Expression arg: this.args) {
				if (!first)
					code += ",";
				code += arg.toJava();
			}
			return code + ")";
		}
	}


	public static class List_ extends Expression {
		final /*immutable*/ List<Expression> args;

		List_(List<? extends Expression> args) {
			this.args = unmodifiableList(new ArrayList(args));
		}

		public Type eval(CallHelper state)
		{
			ArrayList<Type> vals = new ArrayList<Type>(this.args.size());
			for (int i = 0; i < this.args.size(); i++)
				vals.add(this.args.get(i).eval(state));

			return new Type.List(vals);
		}

		public String toString() {
			return "[" + Type.String.join(", ", this.args) + "]";
		}

		public String toJava()
		{
			String code = "new JavaType.List(";
			for (int i = 0; i < this.args.size() -1 ; i++)
				code = code.concat(format("%s,", this.args.get(i).toJava()));
			code = code.concat(format("%s)", this.args.get(this.args.size() - 1).toJava()));

			return code;
		}
	}


	public static class Index extends Expression {
		final Expression item;
		final Expression index;

		Index(Expression item, Expression index) {
			this.item = item;
			this.index = index;
		}

		public Type eval(CallHelper state)
		{
			Type vitem = this.item.eval(state);
			Type vindex = this.index.eval(state);
			return vitem.index(vindex);
		}

		public String toString()
		{
			return format("%s[ %s ]", item, index);
		}

		public String toJava()
		{
			String item = this.item.toJava();
			String index = this.index.toJava();
			return format("%s.index( %s )", item, index);
		}
	}

	public static class Const extends Expression {
		public final Type value;

		public Const(Type value) {
			this.value = value;
		}

		public String toString()
		{
			return this.value.repr();
		}

		public Type eval(CallHelper dummy)
		{
			return this.value;
		}

		public String toJava()
		{
			if (this.value instanceof Type.Int)
				return format("new JavaType.Int(%s)", this.value.repr());
			if (this.value instanceof Type.Float)
				return format("new JavaType.Float(%s)", this.value.repr());
			if (this.value instanceof Type.String)
				return format("new JavaType.Str(%s)", this.value.repr());
			if (this.value instanceof Type.List)
			{
				String code = "new JavaType.List(";
				java.util.List<Type> items = ((Type.List)this.value).getValue();

				for (int i = 0; i < items.size() -1 ; i++)
					code = code.concat(format("%s,", items.get(i)));
				code = code.concat(format("%s)", items.get(items.size() - 1)));

				return code;
			}
			throw new RuntimeException();
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
	}

	public static class Ternary extends Expression {
		public final Expression q, a, b;

		public Ternary(Expression q, Expression a, Expression b)
		{
			this.q = q;
			this.a = a;
			this.b = b;
		}

		public Type eval(CallHelper state)
		{
			Type qvalue = this.q.eval(state);

			Expression which = qvalue.isTrue() ? this.a : this.b;
			Type value = which.eval(state);
			return value;
		}

		public String toString()
		{
			return format("if %s then %s else %s", q, a, b);
		}

		public String toJava()
		{
			return format("( %s ? %s : %s )", q.toJava(), a.toJava(), b.toJava());
		}
	}

	/*
	 * This is a helper expression node to be used in interactive
	 * expression script parsing and execution.
	 */
	public static class Assign extends Expression {
		public final String id;
		public final List<Argument> args;
		public final Expression value;

		// TODO: find a better home for this class
		public static class Argument {
			public final Type type;
			public final String name;
			public Argument(Type type, String name) {
				this.type = type;
				this.name = name;
			}
		}

		// TODO: non-empty argument list
		public Assign(String id, List<Argument> args, Expression value) {
			this.id = id;
			this.args = args;
			this.value = value;

			assert args.size() == 0;
		}

		public Assign(String id, Expression value) {
			this(id, new LinkedList<Argument>(), value);
		}

		public Type eval(CallHelper state)
		{
			state.assign(this.id, this.value);
			return null;
		}

		public String toJava()
		{
			throw new RuntimeException();
		}
	}
}
