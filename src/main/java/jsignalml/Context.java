package jsignalml;

import java.util.Collection;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JClassAlreadyExistsException;

import org.apache.commons.lang.StringUtils;

public class Context {
	static final Logger log = new Logger(Context.class);

	JCodeModel model;
	Context parent;
	JDefinedClass klass;

	public Context(JCodeModel model, Context parent, String name)
		throws JClassAlreadyExistsException
	{
		log.info("created new Context '%s'", name);

		this.model = model;
		this.parent = parent;
		this.klass = model._class(name);
	}

	public JMethod find(String name)
	{
		final String prefixed = JavaGen.makeIdentifier(name);
		log.debug("find: looking for %s/%s", name, prefixed);

		Collection<JMethod> methods = this.klass.methods();
		for(JMethod method: methods)
			if (method.name().equals(prefixed))
				return method;
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
			if (jmethod.listParams().length != argtypes.length)
				throw new ExpressionFault.ArgMismatch();
			return this.klass.staticInvoke(name);
		} else {
			java.lang.reflect.Method method = Builtins.find(name);
			Class[] expectedtypes = method.getParameterTypes();
			for(int i=0; i<expectedtypes.length; i++){
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

			JClass klass = model.ref(Builtins.class);
			return klass.staticInvoke(prefixed);
		}
	}
}
