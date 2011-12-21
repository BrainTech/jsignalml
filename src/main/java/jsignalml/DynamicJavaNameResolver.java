package jsignalml;

import java.util.List;

import com.sun.codemodel.JVar;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;

public class DynamicJavaNameResolver implements JavaExprGen.JavaNameResolver {
	final JavaClassGen gen = new JavaClassGen();
	final Frame frame;

	DynamicJavaNameResolver(Frame frame)
	{
		this.frame = frame;
	}

	@Override
	public JExpression lookup(String id)
	{
		final Type val = this.frame.locals.get(id);
		final List<JVar> locals = util.newLinkedList();
		if (val != null) {
			JDefinedClass klass = gen.model.anonymousClass(Object.class);
			JClass t = this.gen.convertTypeToJClass(val);
			JVar var = klass.field(JMod.FINAL, t, id);
			locals.add(var);
		}

		final JavaExprGen.JavaNameResolver res =
			this.gen.createResolver(this.frame.node, locals, JavaClassGen.GET);
		return res.lookup(id);
	}

	public JavaExprGen createExprGen()
	{
		return new JavaExprGen(gen.model, this);
	}
}
