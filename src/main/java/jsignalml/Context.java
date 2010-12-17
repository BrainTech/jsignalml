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

	public JInvocation find(String name, Class<? extends JavaType>...argtypes)
	{
		final String prefixed = JavaGen.makeIdentifier(name);
		log.debug("looking for %s/%s(%s) in '%s'", name, prefixed,
			  StringUtils.join(argtypes, ", "), this.klass.name());

		Collection<JMethod> methods = this.klass.methods();
		for(JMethod method: methods) {
			if (!method.name().equals(prefixed))
				continue;
			JType expected_types[] = method.listParamTypes();
			if (expected_types.length != argtypes.length)
				throw new ExpressionFault.ArgMismatch();
			for(int i = 0; i<argtypes.length; i++){
				if (!expected_types[i].equals(this.model.ref(argtypes[i])))
					throw new ExpressionFault.ArgMismatch();
			}
			return this.klass.staticInvoke(name);
		}

		if(parent != null)
			return parent.find(name, argtypes);
		else
			return Builtins.find(this.model, name, argtypes);
	}
}
