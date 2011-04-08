package jsignalml.codec;

import jsignalml.Type;
import jsignalml.TypeList;
import jsignalml.TypeInt;
import jsignalml.ContextVisitor;
import jsignalml.Logger;
import jsignalml.util;

import java.util.List;
import java.util.ArrayList;

import static java.lang.String.format;

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

	public abstract class LoopClass extends Context {
		final public IndexClass index;
		public LoopClass(Type index) {
			this.index = new IndexClass(index);
		}

		@Override
		public <T> T _accept(ContextVisitor<T> v, String name, T data)
		{
			return v.visit(this, name, data);
		}

		public class IndexClass extends Param<Type> {
			IndexClass(Type index) {
				this.cache = index;
			}
			protected Type _get() {
				throw new RuntimeException();
			}

			@Override
			public <T> T _accept(ContextVisitor<T> v, String name, T data)
			{
				return v.visit(this, name, data);
			}
		}
	}
}