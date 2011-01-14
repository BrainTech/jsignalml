package jsignalml;

import java.lang.reflect.Method;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JInvocation;

import org.apache.commons.lang.StringUtils;

public class Builtins {
	static final Logger log = new Logger(Builtins.class);

	public static JavaType.Int _jsignalml_factorial(JavaType.Int x)
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

	public static Method find(String name)
	{
		final String prefixed = JavaGen.makeIdentifier(name);
		log.debug("find: looking for %s/%s", name, prefixed);

		Method methods[];
		try {
			methods = Builtins.class.getMethods();
		} catch(SecurityException e) {
			log.exception("Builtins.getMethods()", e);
			return null;
		}

		for(Method method: methods) {
			if(method.getName().equals(prefixed))
				return method;
		}

		throw new ExpressionFault.NameError(name);
	}
}
