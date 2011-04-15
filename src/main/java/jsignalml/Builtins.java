package jsignalml;

import org.apache.commons.lang.StringUtils;
import java.math.BigInteger;
import java.util.List;

public class Builtins extends ASTNode {
	static final Logger log = new Logger(Builtins.class);

	/**
	 * The builtin methods are declared as static. To write only necessary
	 * the methods which are actually used, the list of used methods is kept
	 * in a Builtins instance (as children), and then written by the proper
	 * visitor.
	 */

	private Builtins() {
		super(null, "builtins");
	}

	public static final Builtins instance = new Builtins();

	public static class factorial extends TypeObject {
		public static TypeInt call(TypeInt x)
		{
			BigInteger val = x.getValue();
			if(val.compareTo(BigInteger.ZERO) < 0)
				throw new ExpressionFault.ValueError("argument cannot be negative");

			BigInteger ret = BigInteger.valueOf(1);
			while(val.compareTo(BigInteger.ZERO) > 0) {
				ret = ret.multiply(val);
				val = val.subtract(BigInteger.ONE);
			}

			return new TypeInt(ret);
		}

		@Override
		public TypeInt call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());
			return call((TypeInt)args.get(0));
		}
	}

	private static TypeObject _factorial = new factorial();
	public static TypeObject factorial(){ return _factorial; }

	public static class strip extends TypeObject {
		public static TypeString call(TypeString x)
		{
			String val = x.getValue();
			return new TypeString(StringUtils.strip(val));
		}

		@Override
		public TypeString call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());
			return call((TypeString)args.get(0));
		}
	}

	private static TypeObject _strip = new strip();
	public static TypeObject strip(){ return _strip; }

	public Type visit(ASTNode.BuiltinFunction p, Type dummy) {
		assert dummy == null;

		throw new RuntimeException();
	}

	public static class trim extends TypeObject {
		public static TypeString call(TypeString x)
		{
			String val = x.getValue();
			return new TypeString(StringUtils.trim(val));
		}

		@Override
		public TypeString call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());
			return call((TypeString)args.get(0));
		}
	}

	private static TypeObject _trim = new trim();
	public static TypeObject trim(){ return _trim; }

	public static class bool extends TypeObject {
		public static TypeInt call(Type x)
		{
			return new TypeInt(x.isTrue());
		}

		@Override
		public TypeInt call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());
			return call(args.get(0));
		}
	}

	private static TypeObject _bool = new bool();
	public static TypeObject bool(){ return _bool; }

	public static class len extends TypeObject {
		public static TypeInt call(Type x)
		{
			return x.len();
		}

		@Override
		public TypeInt call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());
			return call(args.get(0));
		}
	}

	private static TypeObject _len = new len();
	public static TypeObject len(){ return _len; }

	public ASTNode.BuiltinFunction lookup(String name)
	{
		log.debug("find: looking for %s", name);

		ASTNode owner = this.children.get(0);

		if (name.equals("factorial")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeInt(), _bool);
			function.arg("n", new TypeInt());
			return function;
		} else if (name.equals("strip")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeString(), _strip);
			function.arg("s", new TypeString());
			return function;
		} else if (name.equals("trim")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeString(), _trim);
			function.arg("s", new TypeString());
			return function;
		} else if (name.equals("bool")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeString(), _bool);
			function.arg("s", new TypeString());
			return function;
		} else if (name.equals("len")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeString(), _len);
			function.arg("s", new TypeString());
			return function;
		}

		return null;
	}

	@Override
	public <T> T _accept(ASTVisitor<T> v, T data)
	{
		return v.visit(this, data);
	}
}
