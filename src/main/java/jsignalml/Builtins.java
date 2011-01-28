package jsignalml;

import java.lang.reflect.Method;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JInvocation;

import org.apache.commons.lang.StringUtils;

public class Builtins extends ASTNode {
	static final Logger log = new Logger(Builtins.class);

	private static Builtins _builtins = new Builtins();
	public static Builtins instance(){ return _builtins; };

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
		log.debug("find: looking for %s", name);

		if (name.equals("factorial")) {
			ASTNode.Positional arg = new ASTNode.Positional(this, "n", new Type.Int());
			return new ASTNode.BuiltinFunction(name, new Type.Int(), arg);
		}

		return null;
	}

	public void _accept(ASTVisitor v)
	{
		v.visit(this);
	}
}
