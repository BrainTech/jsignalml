package jsignalml.codec;

import java.io.File;

import jsignalml.MyBuffer;
import jsignalml.ExpressionFault;

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
}