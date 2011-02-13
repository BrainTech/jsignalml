package jsignalml;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import static java.lang.String.format;

public abstract class FileType {
	static final Logger log = new Logger(FileType.class);

	public static class BinaryFile extends FileType {
		final MyBuffer buffer;

		public BinaryFile(File filename)
		throws IOException, FileNotFoundException
		{
			log.info("opening buffer for %s", filename);
			buffer = new MyBuffer(filename);
		}

		public Type read(BitForm format, TypeInt offset)
		{
			return this.buffer.read(format, offset);
		}
		public Type read(BitForm format, int offset)
		{
			return this.read(format, new TypeInt(offset));
		}
	}

	public static <T extends FileType>
	T open(File filename)
	throws IOException, FileNotFoundException
	{
		// if(klass TODO
		return (T) new BinaryFile(filename);
	}
}
