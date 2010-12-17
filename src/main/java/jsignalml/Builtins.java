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

	public static JInvocation find(JCodeModel model, String name,
				       Class<? extends JavaType>...argtypes)
	{
		final String prefixed = JavaGen.makeIdentifier(name);
		log.debug("looking for %s/%s(%s)", name, prefixed, StringUtils.join(argtypes, ", "));

		Method methods[];
		try {
			methods = Builtins.class.getMethods();
		} catch(SecurityException e) {
			log.exception("Builtins.getMethods()", e);
			return null;
		}

		for(Method method: methods) {
			if(!method.getName().equals(prefixed))
				continue;

			if(method.getParameterTypes().length != argtypes.length)
				throw new ExpressionFault.ArgMismatch();
			// TODO: check argument types if possible

			JClass klass = model.ref(Builtins.class);
			return klass.staticInvoke(prefixed);
		}
		return null;
	}
}
