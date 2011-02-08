package jsignalml.codec;

import java.io.File;
import java.util.List;
import java.util.LinkedList;

import jsignalml.MyBuffer;
import jsignalml.ExpressionFault;
import jsignalml.Type;
import jsignalml.util;

public abstract class Signalml implements jsignalml.Source {
	protected File default_filename;

	public abstract class FileClass {
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

	public abstract class LoopClass {
		final protected Type index;
		public LoopClass(Type index) {
			this.index = index;
		}
	}
}