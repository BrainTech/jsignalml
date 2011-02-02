package jsignalml;

import java.lang.reflect.Method;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JInvocation;

import org.apache.commons.lang.StringUtils;

public class Builtins extends ASTNode {
	static final Logger log = new Logger(Builtins.class);

	/**
	 * The builtin methods are declared as static. To write only necessary
	 * the methods which are actually used, the list of used methods is kept
	 * in a Builtins instance (as children), and then written by the proper
	 * visitor.
	 */

	public Builtins() {
		super(null, "builtins");
	}

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

	public ASTNode.BuiltinFunction lookup(String name)
	{
		log.debug("find: looking for %s", name);

		assert this.children.size() == 1;
		ASTNode owner = this.children.get(0);

		if (name.equals("factorial")) {
			ASTNode.BuiltinFunction function =
				new ASTNode.BuiltinFunction(owner, name, new Type.Int());
			function.arg("n", new Type.Int());
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
