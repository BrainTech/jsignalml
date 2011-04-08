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
			this.createIfParams();
		else
			this.createElseParams();
	}

	/**
	 * Create params defined in the 'if' branch of this conditional.
	 */
	public abstract void createIfParams();

	/**
	 * Create params defined in the 'else' branch of this conditional.
	 */
	public abstract void createElseParams();

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
