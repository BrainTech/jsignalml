package jsignalml.codec;

import jsignalml.Type;
import jsignalml.ContextVisitor;
import jsignalml.Logger;

public abstract class ConditionalClass extends Context {
	static final Logger log = new Logger(ConditionalClass.class);

	abstract public Type getCondition();

	@Override
	public <T> T _accept(ContextVisitor<T> v, String name, T data)
	{
		return v.visit(this, name, data);
	}

	public void createParams() {
		if(this.getCondition().isTrue())
			this.createParamsIf();
		else
			this.createParamsElse();
	}

	/**
	 * Create params defined in the 'if' branch of this conditional.
	 */
	public abstract void createParamsIf();

	/**
	 * Create params defined in the 'else' branch of this conditional.
	 */
	public abstract void createParamsElse();

	public abstract class ElseBranchClass extends Context {
		//@Override
		public void register(String name, Context child) {
			ConditionalClass.this.register(name, child);
		}

		@Override
		public <T> T _accept(ContextVisitor<T> v, String name, T data)
		{
			return v.visit(this, name, data);
		}
	}
}
