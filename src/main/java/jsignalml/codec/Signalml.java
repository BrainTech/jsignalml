package jsignalml.codec;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

import jsignalml.MyBuffer;
import jsignalml.ExpressionFault;
import jsignalml.Type;
import jsignalml.util;

public abstract class Signalml extends Context implements jsignalml.Source {
	protected File default_filename;

	public abstract class FileClass extends Context {
		MyBuffer buffer;

		public void open(File filename)
		{
			if ((filename == null) && (default_filename != null)) {
				filename = default_filename;
				default_filename = null;
			}
			if (filename == null)
				throw new ExpressionFault.ValueError("filename must be specified");
			this.buffer = MyBuffer.open(filename);
		}

		public MyBuffer buffer()
		{
			if (this.buffer == null)
				this.open(null);
			return this.buffer;
		}
	}

	public abstract class LoopClass extends Context {
		final public IndexClass index;
		public LoopClass(Type index) {
			this.index = new IndexClass(index);
		}

		public class IndexClass extends Param<Type> {
			IndexClass(Type index) {
				this.cache = index;
			}
			protected Type _get() {
				throw new RuntimeException();
			}
		}
	}
}
