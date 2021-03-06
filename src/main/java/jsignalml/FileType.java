package jsignalml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jsignalml.logging.Logger;

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

	public static class TextFile extends FileType {
		final TextBuffer buffer;

		public TextFile(File filename)
		throws IOException, FileNotFoundException
		{
			log.info("opening buffer for %s", filename);
			buffer = new TextBuffer(filename);
		}

//		public Type read(BitForm format, TypeInt offset)
//		{
//			return this.buffer.read(format, offset);
//		}
//		public Type read(BitForm format, int offset)
//		{
//			return this.read(format, new TypeInt(offset));
//		}
	}

	public static class XmlFile extends FileType {
		final XMLBuffer buffer;

		public XmlFile(File filename){
			log.info("opening buffer for %s", filename);
			buffer = new XMLBuffer(filename);
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
