package jsignalml;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JClassAlreadyExistsException;

public class Context {
	JCodeModel model;
	Context parent;
	JDefinedClass klass;

	public Context(JCodeModel model, Context parent, String name)
		throws JClassAlreadyExistsException
	{
		this.model = model;
		this.parent = parent;
		this.klass = model._class(name);
	}

	public JInvocation find(String name, Class<? extends JavaType>...argtypes)
	{
		JType args[] = new JType[argtypes.length];
		for(int i=0; i<args.length; i++)
			args[i] = this.model.ref(argtypes[i]);

		JMethod method = klass.getMethod(name, args);
		if(method != null)
			return this.klass.staticInvoke(name);

		if(parent != null)
			return parent.find(name, argtypes);
		else
			return Builtins.find(this.model, name, argtypes);
	}
}
