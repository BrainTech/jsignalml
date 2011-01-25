package jsignalml;

import java.lang.reflect.Method;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JInvocation;

import org.apache.commons.lang.StringUtils;

public class Builtins extends Context {
	static final Logger log = new Logger(Builtins.class);

	public Builtins() {
		super(null, "builtins");
	}

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

	public ASTNode.BuiltinFunction lookup(String name)
	{
		final String prefixed = JavaGen.makeIdentifier(name);
		log.debug("find: looking for %s/%s", name, prefixed);

		if (name.equals("factorial"))
			return new ASTNode.BuiltinFunction(name, Type.Int, Type.Int);

		return null;
	}

	public ASTNode.BuiltinFunction find(String name)
	{
		return lookup(name);
	}
}
