package jsignalml.codec;

import static java.lang.String.format;

import java.util.List;

import jsignalml.ContextVisitor;
import jsignalml.Type;
import jsignalml.TypeInt;
import jsignalml.TypeList;
import jsignalml.util;
import jsignalml.logging.Logger;

public abstract class OuterLoopClass extends Param<TypeList> {
	static final Logger log = new Logger(OuterLoopClass.class);

	abstract protected TypeList getSequence();
	abstract protected LoopClass createLoop(Type index);

	protected TypeList _get() {
		TypeList range = this.getSequence();

		List value = util.newArrayList();
		for(Type var: range) {
			LoopClass item = this.createLoop(var);
			value.add(item);

			/* A parameter inside loop can refer to the loop (or some stuff inside).
			 * This will cause a deadlock, so
			 * - we require the calls to only go to earlier loop iterations
			 * - set the variable temporarily
			 * It would be better to make OuterLoopClass not a Param
			 * wrapping a list, but have it deliver loop iterations directly.
			 */
			this.cache = new TypeList(value);

			item.createParams();
		}
		return new TypeList(value);
	}

	@Override
	public <T> T _accept(ContextVisitor<T> v, String name, T data)
	{
		T newdata = v.visit(this, name, data);

		TypeList items = this.get();
		for(int i=0; i<items.len().safeIntValue(); i++) {
			Type item = items.index(new TypeInt(i));
			LoopClass loopitem = (LoopClass) item;
			String itemname = format("%s[%d]", name, i);
			loopitem.accept(v, itemname, newdata);
		}

		return newdata;
	}

	public void createLoopChannels()
	{
		TypeList items = this.get();
		log.debug("createParams() [%s items]", items.len());
	}

	public abstract class LoopClass extends Context {
		@Override
		public <T> T _accept(ContextVisitor<T> v, String name, T data)
		{
			return v.visit(this, name, data);
		}
	}
}
