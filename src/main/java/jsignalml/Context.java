package jsignalml;

import java.util.Collection;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;

import org.apache.commons.lang.StringUtils;

public class Context {
	static final Logger log = new Logger(Context.class);

	Context parent;
	JDefinedClass klass;

	public Context(JDefinedClass klass, Context parent, String name)
		throws JClassAlreadyExistsException
	{
		log.info("created new Context '%s'", name);

		this.parent = parent;
		this.klass = klass;
	}

	public JMethod find(String name)
	{
		final String getter = JavaGen.makeGetter(name);
		log.debug("find: looking for %s/%s", name, getter);

		Collection<JMethod> methods = this.klass.methods();
		for(JMethod method: methods){
			log.debug("looking at %s", method.name());
			if (method.name().equals(getter))
				return method;
		}
		if(parent != null)
			return parent.find(name);
		else
			return null;
	}

	public JInvocation find(String name, Type...argtypes)
	{
		String[] argnames = new String[argtypes.length];
		for(int i=0; i<argtypes.length; i++) {
			Type t = argtypes[i];
			argnames[i] = t == null ? "*" : t.getClass().getSimpleName();
		}

		final String prefixed = JavaGen.makeIdentifier(name);
		log.debug("looking for %s/%s(%s) in '%s'", name, prefixed,
			  StringUtils.join(argnames, ", "), this.klass.name());
		JMethod jmethod = this.find(name);

		if (jmethod != null) {
			JType[] expectedtypes = jmethod.listParamTypes();
			if (expectedtypes.length != argtypes.length)
				throw new ExpressionFault.ArgMismatch();
			for (int i=0; i<expectedtypes.length; i++) {
				if (argtypes[i] == null)
					continue;
				Class<? extends Type> expected =
					Type.getType(expectedtypes[i].name());
				if (!argtypes[i].equals(expected))
					throw new ExpressionFault.TypeError(expected,
								    argtypes[i].getClass());
			}

			return JExpr._this().invoke(jmethod);
		} else {
			java.lang.reflect.Method method = Builtins.find(name);
			Class[] expectedtypes = method.getParameterTypes();
			if (expectedtypes.length != argtypes.length)
				throw new ExpressionFault.ArgMismatch();
			for (int i=0; i<expectedtypes.length; i++) {
				Class<? extends JavaType> expected =
					expectedtypes[i].asSubclass(JavaType.class);
				Class<? extends JavaType> argtype =
					JavaGen.convertType(argtypes[i]);
				if (argtype == null)
					continue;
				if (!argtype.equals(expected))
					throw new ExpressionFault.TypeError(
					    JavaGen.unconvertType(expected).getClass(),
					    argtypes[i].getClass());
			}

			JClass klass = this.klass.owner().ref(Builtins.class);
			return klass.staticInvoke(prefixed);
		}
	}
}
