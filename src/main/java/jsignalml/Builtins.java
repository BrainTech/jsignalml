package jsignalml;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JInvocation;

public class Builtins {
	public static JavaType.Int factorial(JavaType.Int x)
	{
		JavaType.Int ret = new JavaType.Int(1);
		if(x.compareTo(JavaType.Int.ZERO) < 0)
			throw new ExpressionFault.ValueError("argument cannot be negative");

		while(x.compareTo(JavaType.Int.ZERO) > 0) {
			ret = ret.mul(x);
			x = x.sub(JavaType.Int.ONE);
		}

		return ret;
	}

	public static JInvocation find(JCodeModel model, String name,
				       Class<? extends JavaType>...argtypes)
	{
		java.lang.reflect.Method method;
		try {
			method = Builtins.class.getMethod(name, argtypes);
		} catch(NoSuchMethodException e) {
			return null;
		} catch(SecurityException e) {
			return null;
		}

		JClass klass = model.ref(Builtins.class);
		return klass.staticInvoke(name);
	}
}
