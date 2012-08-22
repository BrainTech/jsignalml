package jsignalml.compiler;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JPackage;

import java.io.OutputStream;
import java.io.Writer;
import java.io.StringWriter;
import java.io.IOException;
import org.apache.commons.lang.NotImplementedException;

public class MemoryWriter extends CodeWriter {
	/**
         *  Called by CodeModel at the end of the process.
	 */
	@Override
	public void close()
		throws IOException
	{
		this.writer.close();
	}

	/**
	 *  Called by CodeModel to store the specified file.
	 */
	@Override
	public OutputStream openBinary(JPackage pkg, String fileName) {
		throw new NotImplementedException();
	}

	protected StringWriter writer = new StringWriter();

	/**
	 *  Called by CodeModel to store the specified file.
	 */
	@Override
	public Writer openSource(JPackage pkg, String fileName) {
		assert this.writer.getBuffer().length() == 0;
		return this.writer;
	}

	public CharSequence getCode() {
		StringBuffer buffer = this.writer.getBuffer();
		assert buffer.length() > 0;
		return buffer;
	}
}
