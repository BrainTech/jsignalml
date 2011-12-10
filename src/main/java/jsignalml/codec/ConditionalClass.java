package jsignalml.codec;

import jsignalml.Type;
import jsignalml.ContextVisitor;
import jsignalml.logging.Logger;

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

	public void createChannels() {
		if(this.getCondition().isTrue())
			this.createChannelsIf();
		else
			this.createChannelsElse();
	}

	/**
	 * Create params defined in the 'if' branch of this conditional.
	 */
	public abstract void createParamsIf();

	/**
	 * Create params defined in the 'else' branch of this conditional.
	 */
	public abstract void createParamsElse();

	/**
	 * Create channels defined in the 'if' branch of this conditional.
	 */
	public abstract void createChannelsIf();

	/**
	 * Create channels defined in the 'else' branch of this conditional.
	 */
	public abstract void createChannelsElse();

	public abstract class ElseBranchClass extends Context {
		@Override
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
