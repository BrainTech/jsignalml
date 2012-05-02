package jsignalml;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jsignalml.codec.Signalml.FileClass;
import jsignalml.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.util.FastMath;

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

	private static Builtins instance;
	public static Builtins instance() {
		if (instance==null)
			instance = new Builtins();
		return instance;
	}

	public static final BigInteger DOUBLE_MAX = new BigDecimal(Double.MAX_VALUE).toBigInteger();
	public static final BigInteger DOUBLE_MIN = new BigDecimal(1 - Double.MAX_VALUE).toBigInteger();

	/**
	 * Factorial function
	 */
	public static class factorial extends TypeObject {

		@Override
		public long call(long val) {
			long ret = 1;
			while(val > 0) {
				ret *= val;
				val--;
			}
			return ret;
		}

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

	/**
	 * Strip function
	 */
	public static class strip extends TypeObject {

		@Override
		public String call(String val)
		{
			return StringUtils.strip(val);
		}

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

	/**
	 * Trim function
	 */
	public static class trim extends TypeObject {

		@Override
		public String call(String val) {
			return StringUtils.trim(val);
		}

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

	/**
	 * Fetch
	 */
	public static class fetch extends TypeObject {
		public static TypeString call(TypeString text, TypeString pattern, TypeInt group)
		{
			Pattern pat = Pattern.compile(pattern.getValue());
			Matcher matcher = pat.matcher(text.getValue());

			if (matcher.find()) {
				return new TypeString(matcher.group(group.getValue().intValue()));
			}

			return null;
		}

		@Override
		public TypeString call(List<Type> args)
		{
			if(args.size() != 3)
				throw new ExpressionFault.ArgMismatch(3, args.size());
			
			TypeString retValue = call((TypeString)args.get(0), 
					(TypeString)args.get(1), (TypeInt)args.get(2));
			if(retValue != null){
				return retValue;
			}
			
			throw new ExpressionFault.NoMatchFoundError(((TypeString)args.get(0)).getValue(), 
					((TypeString)args.get(1)).getValue());
		}
	}	
	
	private static TypeObject _fetch = new fetch();
	public static TypeObject fetch(){ return _fetch; }


	/**
	 * Bool
	 */
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

	/**
	 * Str
	 */
	public static class str extends TypeObject {
		public static TypeString call(Type x)
		{
			return new TypeString().make(x);
		}

		@Override
		public TypeString call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());
			return call(args.get(0));
		}
	}

	private static TypeObject _str = new str();
	public static TypeObject str(){ return _str; }

	/**
	 * Get line for pattern
	 */
	public static class get_line_for_pattern extends TypeObject {
		public static TypeInt call(FileClass file, TypeString pattern, TypeInt group)
		{
			return file.textBuffer().get_line_matching_pattern(pattern, group);
		}
		public static TypeInt call(FileClass file, TypeString pattern)
		{
			return call(file, pattern, new TypeInt(0));
		}

		@Override
		public TypeInt call(List<Type> args)
		{
			if (args.size() < 2) {
				throw new ExpressionFault.ArgMismatch(2, args.size());
			} else if (args.size() == 2) {
				TypeInt retValue = call((FileClass) args.get(0), 
						(TypeString) args.get(1));
				if(retValue != null) return retValue;
			} else if (args.size() == 3) {
				TypeInt retValue = call((FileClass) args.get(0), 
						(TypeString) args.get(1), (TypeInt) args.get(2));
				if(retValue != null) return retValue;
			}
			throw new ExpressionFault.NoMatchFoundError(((TypeString) args.get(0)).getValue(), 
					((TypeString) args.get(1)).getValue());
		}
	}

	private static TypeObject _get_line_for_pattern = new get_line_for_pattern();
	public static TypeObject get_line_for_pattern(){ return _get_line_for_pattern; }

	/**
	 * Get name of the file specified by its handler
	 */
	public static class get_filename extends TypeObject {
		public static TypeString call(FileClass file)
		{
			return new TypeString(file.getCurrentFilename().getName());
		}

		@Override
		public TypeString call(List<Type> args)
		{
			if (args.size() == 1) {
				return call((FileClass) args.get(0));
			} else {
				throw new ExpressionFault.ArgMismatch(1, args.size());
			}
		}
	}

	private static TypeObject _get_filename = new get_filename();
	public static TypeObject get_filename(){ return _get_filename; }

	/**
	 * Len
	 */
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

	/**
	 * Range
	 */
	public static class range extends TypeObject {
		public static TypeList call(TypeInt start, TypeInt stop, TypeInt step)
		{
			ArrayList<TypeInt> list = util.newArrayList();
			while(start.compareTo(stop) < 0) {
				list.add(start);
				start = start.add(step);
			}
			return new TypeList(list);
		}

		@Override
		public TypeList call(List<Type> args)
		{
			if(args.size() == 1)
				return call(new TypeInt(0), (TypeInt)args.get(0), new TypeInt(1));
			else if(args.size() == 2)
				return call((TypeInt)args.get(0), (TypeInt)args.get(1), new TypeInt(1));
			else if(args.size() == 3)
				return call((TypeInt)args.get(0), (TypeInt)args.get(1), (TypeInt)args.get(2));
			else
				throw new ExpressionFault.ArgMismatch(3, args.size());
		}
	}

	private static TypeObject _range = new range();
	public static TypeObject range(){ return _range; }

	/**
	 * Get
	 */
	public static class get extends TypeObject {
		public static Type call(TypeMap map, Type key, Type default_)
		{
			if (default_ == null)
				return map.index(key);
			else
				return map.index(key, default_);
		}

		@Override
		public Type call(List<Type> args)
		{
			if(args.size() == 2)
				return call((TypeMap)args.get(0), args.get(1), null);
			else if(args.size() == 3)
				return call((TypeMap)args.get(0), args.get(1), args.get(2));
			else
				throw new ExpressionFault.ArgMismatch(3, args.size());
		}
	}

	private static TypeObject _get = new get();
	public static TypeObject get(){ return _get; }

	/**
	 * Sine function
	 */
	public static class sin extends TypeObject {

		@Override
		public double call(double val) {
			return FastMath.sin(val);
		}

		public static TypeFloat call(TypeFloat x)
		{
			Double val = x.getValue();
			Double ret = FastMath.sin(val);
			return new TypeFloat(ret);
		}

		public static TypeFloat call(TypeInt x)
		{
			BigInteger val = x.getValue();

			// Check for overflow
			if (DOUBLE_MAX.compareTo(val) < 0 || DOUBLE_MIN.compareTo(val) > 0)
				throw new ExpressionFault.ValueError("overflow");

			Double ret = FastMath.sin(val.doubleValue());
			return new TypeFloat(ret);
		}

		@Override
		public TypeFloat call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());

			Type arg0 = args.get(0);
			if (arg0 instanceof TypeInt) {
				return call((TypeInt)arg0);
			} else if (arg0 instanceof TypeFloat) {
				return call((TypeFloat)arg0);
			} else {
				throw new ExpressionFault.ValueError("argument must be a number");
			}
		}
	}

	private static TypeObject _sin = new sin();
	public static TypeObject sin(){ return _sin; }

	/**
	 * Cosine function
	 */
	public static class cos extends TypeObject {

		@Override
		public double call(double val) {
			return FastMath.cos(val);
		}

		public static TypeFloat call(TypeFloat x) {
			Double val = x.getValue();
			Double ret = FastMath.cos(val);
			return new TypeFloat(ret);
		}

		public static TypeFloat call(TypeInt x) {
			BigInteger val = x.getValue();
			// Check for overflow
			if (DOUBLE_MAX.compareTo(val) < 0 || DOUBLE_MIN.compareTo(val) > 0)
				throw new ExpressionFault.ValueError("overflow");

			Double ret = FastMath.cos(val.doubleValue());
			return new TypeFloat(ret);
		}

		@Override
		public TypeFloat call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());

			Type arg0 = args.get(0);
			if (arg0 instanceof TypeInt) {
				return call((TypeInt)arg0);
			} else if (arg0 instanceof TypeFloat) {
				return call((TypeFloat)arg0);
			} else {
				throw new ExpressionFault.ValueError("argument must be a number");
			}
		}
	}

	private static TypeObject _cos = new cos();
	public static TypeObject cos(){ return _cos; }

	/**
	 * Tangent function
	 */
	public static class tan extends TypeObject {

		@Override
		public double call(double val) {
			return FastMath.tan(val);
		}

		public static TypeFloat call(TypeFloat x) {
			Double val = x.getValue();
			Double ret = FastMath.tan(val);
			return new TypeFloat(ret);
		}

		public static TypeFloat call(TypeInt x) {
			BigInteger val = x.getValue();
			// Check for overflow
			if (DOUBLE_MAX.compareTo(val) < 0 || DOUBLE_MIN.compareTo(val) > 0)
				throw new ExpressionFault.ValueError("overflow");

			Double ret = FastMath.tan(val.doubleValue());
			return new TypeFloat(ret);
		}

		@Override
		public TypeFloat call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());

			Type arg0 = args.get(0);
			if (arg0 instanceof TypeInt) {
				return call((TypeInt)arg0);
			} else if (arg0 instanceof TypeFloat) {
				return call((TypeFloat)arg0);
			} else {
				throw new ExpressionFault.ValueError("argument must be a number");
			}
		}
	}

	private static TypeObject _tan = new tan();
	public static TypeObject tan(){ return _tan; }

	/**
	 * Cotangent function
	 */
	public static class cot extends TypeObject {

		@Override
		public double call(double val) {
			return 1/FastMath.tan(val);
		}

		public static TypeFloat call(TypeFloat x) {
			Double val = x.getValue();
			Double ret = 1/FastMath.tan(val);
			return new TypeFloat(ret);
		}

		public static TypeFloat call(TypeInt x) {
			BigInteger val = x.getValue();
			// Check for overflow
			if (DOUBLE_MAX.compareTo(val) < 0 || DOUBLE_MIN.compareTo(val) > 0)
				throw new ExpressionFault.ValueError("overflow");

			Double ret = 1/FastMath.tan(val.doubleValue());
			return new TypeFloat(ret);
		}

		@Override
		public TypeFloat call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());

			Type arg0 = args.get(0);
			if (arg0 instanceof TypeInt) {
				return call((TypeInt)arg0);
			} else if (arg0 instanceof TypeFloat) {
				return call((TypeFloat)arg0);
			} else {
				throw new ExpressionFault.ValueError("argument must be a number");
			}
		}
	}

	private static TypeObject _cot = new cot();
	public static TypeObject cot(){ return _cot; }

	/**
	 * Exponential function
	 */
	public static class exp extends TypeObject {

		@Override
		public double call(double val) {
			return FastMath.exp(val);
		}

		public static TypeFloat call(TypeFloat x) {
			Double val = x.getValue();
			Double ret = FastMath.exp(val);
			return new TypeFloat(ret);
		}

		public static TypeFloat call(TypeInt x) {
			BigInteger val = x.getValue();
			// Check for overflow
			if (DOUBLE_MAX.compareTo(val) < 0 || DOUBLE_MIN.compareTo(val) > 0)
				throw new ExpressionFault.ValueError("overflow");

			Double ret = FastMath.exp(val.doubleValue());
			return new TypeFloat(ret);
		}

		@Override
		public TypeFloat call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());

			Type arg0 = args.get(0);
			if (arg0 instanceof TypeInt) {
				return call((TypeInt)arg0);
			} else if (arg0 instanceof TypeFloat) {
				return call((TypeFloat)arg0);
			} else {
				throw new ExpressionFault.ValueError("argument must be a number");
			}
		}
	}

	private static TypeObject _exp = new exp();
	public static TypeObject exp(){ return _exp; }

	/**
	 * Natural logarithm
	 */
	public static class log extends TypeObject {

		@Override
		public double call(double val) {
			return FastMath.log(val);
		}

		public static TypeFloat call(TypeFloat x) {
			Double val = x.getValue();

			if (val <= 0)
				throw new ExpressionFault.ValueError("argument must be positive");

			Double ret = FastMath.log(val);
			return new TypeFloat(ret);
		}

		public static TypeFloat call(TypeInt x) {
			BigInteger val = x.getValue();
			// Check for overflow
			if (DOUBLE_MAX.compareTo(val) < 0 || DOUBLE_MIN.compareTo(val) > 0)
				throw new ExpressionFault.ValueError("overflow");

			double doubleVal = val.doubleValue();
			if(doubleVal <= 0)
				throw new ExpressionFault.ValueError("argument must be positive");

			Double ret = FastMath.log(doubleVal);
			return new TypeFloat(ret);
		}


		@Override
		public TypeFloat call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());

			Type arg0 = args.get(0);
			if (arg0 instanceof TypeInt) {
				return call((TypeInt)arg0);
			} else if (arg0 instanceof TypeFloat) {
				return call((TypeFloat)arg0);
			} else {
				throw new ExpressionFault.ValueError("argument must be a number");
			}
		}
	}

	private static TypeObject _log = new log();
	public static TypeObject log(){ return _log; }

	/**
	 * Common logarithm
	 */
	public static class log10 extends TypeObject {

		@Override
		public double call(double val) {
			return FastMath.log10(val);
		}

		public static TypeFloat call(TypeFloat x) {
			Double val = x.getValue();
			if (val <= 0)
				throw new ExpressionFault.ValueError("argument must be positive");

			Double ret = FastMath.log10(val);
			return new TypeFloat(ret);
		}

		public static TypeFloat call(TypeInt x) {
			BigInteger val = x.getValue();
			// Check for overflow
			if (DOUBLE_MAX.compareTo(val) < 0 || DOUBLE_MIN.compareTo(val) > 0)
				throw new ExpressionFault.ValueError("overflow");

			double doubleVal = val.doubleValue();
			if(doubleVal <= 0)
				throw new ExpressionFault.ValueError("argument must be positive");

			Double ret = FastMath.log10(doubleVal);
			return new TypeFloat(ret);
		}

		@Override
		public TypeFloat call(List<Type> args)
		{
			if(args.size() != 1)
				throw new ExpressionFault.ArgMismatch(1, args.size());

			Type arg0 = args.get(0);
			if (arg0 instanceof TypeInt) {
				return call((TypeInt)arg0);
			} else if (arg0 instanceof TypeFloat) {
				return call((TypeFloat)arg0);
			} else {
				throw new ExpressionFault.ValueError("argument must be a number");
			}
		}
	}

	private static TypeObject _log10 = new log10();
	public static TypeObject log10(){ return _log10; }

	/**
	 * @see jsignalml.ASTNode#lookup(java.lang.String)
	 */
	public ASTNode.BuiltinFunction lookup(String name)
	{
		log.debug("looking for %s", name);

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
		} else if (name.equals("fetch")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeString(), _fetch);
			function.arg("text", new TypeString());
			function.arg("pattern", new TypeString());
			function.arg("group", new TypeInt());
			return function;
		} else if (name.equals("bool")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeInt(), _bool);
			function.arg("s", null);
			return function;
		} else if (name.equals("str")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeString(), _str);
			function.arg("x", null);
			return function;
		} else if (name.equals("len")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeInt(), _len);
			function.arg("sequence", null);
			return function;
		} else if (name.equals("get_line_for_pattern")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeInt(), _get_line_for_pattern);
			function.arg("file_handler", new TypeObject() {});
			function.arg("pattern", new TypeString());
			function.arg("group", new TypeInt());
			return function;
		} else if (name.equals("get_filename")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeString(), _get_filename);
			function.arg("file_handler", new TypeObject() {});
			return function;
		} else if (name.equals("range")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeList(), _range);
			function.arg("start", new TypeInt());
			function.arg("stop", new TypeInt());
			function.arg("step", new TypeInt());
			return function;
		} else if (name.equals("get")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, null, _get);
			function.arg("map", new TypeMap());
			function.arg("key", null);
			function.arg("default", null);
			return function;
		} else if (name.equals("sin")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeFloat(), _sin);
			function.arg("x", null);
			return function;
		} else if (name.equals("cos")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeFloat(), _cos);
			function.arg("x", null);
			return function;
		} else if (name.equals("tan")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeFloat(), _tan);
			function.arg("x", null);
			return function;
		} else if (name.equals("cot")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeFloat(), _cot);
			function.arg("x", null);
			return function;
		} else if (name.equals("exp")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeFloat(), _exp);
			function.arg("x", null);
			return function;
		} else if (name.equals("log")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeFloat(), _log);
			function.arg("x", null);
			return function;
		} else if (name.equals("log10")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new TypeFloat(), _log10);
			function.arg("x", null);
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
