package jsignalml;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableList;

import org.apache.commons.lang.StringUtils;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JOp;

public abstract class Expression {
	public abstract T accept(ExpressionVisitor<T> v);

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
		public T accept(ExpressionVisitor<T> v){
			T left = this.left.accept(v);
			T right = this.right.accept(v);
			return v.visitBinaryOp(this, left, right);
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

		@Override
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


		@Override
		public Type type(Context context)
		{
			Type a = this.left.type(context);
			Type b = this.right.type(context);
			return a.binaryOpType(this.op, b);
		}

		@Override
		public JExpression toJava(Context context)
		{
			JExpression left = this.left.toJava(context);
			JExpression right = this.right.toJava(context);
			switch (this.op) {
			case LOG_AND:
				return JOp.cond(left, right, left);
			case LOG_OR:
				return JOp.cond(left, left, right);
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

		@Override
		public Type eval(CallHelper state)
		{
			Type sub = this.sub.eval(state);
			if (this.op == Type.UnaryOp.LOG_NOT)
				return sub.logical_not();
			else
				return sub.unaryOp(this.op);
		}

		@Override
		public Type type(Context context)
		{
			Type a = this.sub.type(context);
			return a.unaryOpType(this.op);
		}

		@Override
		public String toString()
		{
			return format("%s %s", op.rep, sub);
		}

		@Override
		public JExpression toJava(Context context)
		{
			JExpression sub = this.sub.toJava(context);
			return JExpr.invoke(sub, op.javaMethod);
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
		public Type eval(CallHelper state)
		{
			Type vals[] = new Type[this.args.size()];
			for (int i = 0; i < vals.length; i++)
				vals[i] = this.args.get(i).eval(state);

			return state.call(this.name, vals);
		}

		@Override
		public Type type(Context context)
		{
			// TODO
			return null;
		}

		@Override
		public String toString()
		{
			return this.name + "(" + StringUtils.join(this.args, ", ") + ")";
		}

		@Override
		public JExpression toJava(Context context)
		{
			Type types[] = new Type[this.args.size()];
			for(int i=0; i<types.length; i++)
				types[i] = this.args.get(i).type(context);

			JInvocation inv = context.find(name, types);
			if (inv == null)
				throw new ExpressionFault.NameError(name);
			for (Expression arg: this.args)
				inv.arg(arg.toJava(context));
			return inv;
		}
	}


	public static class List_ extends Expression {
		final /*immutable*/ List<Expression> args;

		List_(List<? extends Expression> args) {
			this.args = unmodifiableList(args!=null ? new ArrayList(args) : new ArrayList());
		}

		@Override
		public Type eval(CallHelper state)
		{
			ArrayList<Type> vals = new ArrayList<Type>(this.args.size());
			for (int i = 0; i < this.args.size(); i++)
				vals.add(this.args.get(i).eval(state));

			return new Type.List(vals);
		}

		@Override
		public Type type(Context context)
		{
			return new Type.List();
		}

		/**
		 * If all elements are of the same type, return this type, otherwise null.
		 */
		public Type elementType(Context context)
		{
			Type type = null;
			for (Expression el: this.args) {
				Type t = el.type(context);
				if (t == null)
					return null;
				if (type != null && !t.getClass().equals(type.getClass()))
					return null;
				type = t;
			}
			return type;
		}


		@Override
		public String toString() {
			return "[" + StringUtils.join(this.args, ", ") + "]";
		}

		@Override
		public JExpression toJava(Context context)
		{
			JInvocation list = JExpr._new(context.klass.owner().ref(JavaType.List.class));
			for (Expression expr: this.args)
				list.arg(expr.toJava(context));
			return list;

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
		public Type eval(CallHelper state)
		{
			Type vitem = this.item.eval(state);
			Type vindex = this.index.eval(state);
			return vitem.index(vindex);
		}

		@Override
		public Type type(Context context)
		{
			final Type indextype = this.index.type(context);
			if (indextype != null && !(indextype instanceof Type.Int))
				throw new ExpressionFault.TypeError();

			final Type listtype = this.item.type(context);
			if (listtype != null && !(listtype instanceof Type.List))
				throw new ExpressionFault.TypeError();

			if (this.item instanceof List_)
				return ((List_)this.item).elementType(context);

			return null;
		}

		@Override
		public String toString()
		{
			return format("%s[%s]", item, index);
		}

		@Override
		public JExpression toJava(Context context)
		{
			JExpression item = this.item.toJava(context);
			JExpression index = this.index.toJava(context);
			return JExpr.invoke(item, "index").arg(index);
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

		@Override
		public Type eval(CallHelper dummy)
		{
			return this.value;
		}

		@Override
		public Type type(Context context)
		{
			return this.value;
		}

		@Override
		public JExpression toJava(Context context)
		{
			Class<? extends JavaType> type;
			JExpression repr;

			if (this.value instanceof Type.Int) {
				type = JavaType.Int.class;
				// TODO: check if representation is not outside range
				repr = JExpr.lit(((Type.Int)this.value).getValue());
			} else if (this.value instanceof Type.Float) {
				type = JavaType.Float.class;
				repr = JExpr.lit(((Type.Float)this.value).getValue());
			} else if (this.value instanceof Type.String) {
				type = JavaType.Str.class;
				repr = JExpr.lit(((Type.String)this.value).getValue());
			} else {
				throw new RuntimeException();
			}

			return JExpr._new(context.klass.owner().ref(type)).arg(repr);
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

		@Override
		public Type eval(CallHelper state)
		{
			Type qvalue = this.q.eval(state);

			Expression which = qvalue.isTrue() ? this.a : this.b;
			Type value = which.eval(state);
			return value;
		}

		@Override
		public String toString()
		{
			return format("if %s then %s else %s", q, a, b);
		}

		@Override
		public Type type(Context context)
		{
			Type a = this.a.type(context);
			Type b = this.a.type(context);
			if (a.getClass().equals(b.getClass()))
				return a;
			else
				return null;
		}

		@Override
		public JExpression toJava(Context context)
		{
			return JOp.cond(q.toJava(context),
					a.toJava(context),
					b.toJava(context));
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

		@Override
		public Type eval(CallHelper state)
		{
			state.assign(this.id, this.value);
			return null;
		}

		@Override
		public Type type(Context context)
		{
			throw new RuntimeException();
		}

		@Override
		public JExpression toJava(Context context)
		{
			throw new RuntimeException();
		}
	}
}
