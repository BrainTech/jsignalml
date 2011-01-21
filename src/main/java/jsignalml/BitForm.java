package jsignalml;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public abstract class BitForm {
	protected static final Logger log = new Logger(BitForm.class);

	public static class BadBitForm extends Exception {
		public BadBitForm(String description) {
			super("bad BitFormat description '" + description + "'");
		}
	}

	public static BitForm get(String description)
		throws BadBitForm
	{
		if (description.equals("<i1") ||
		                description.equals(">i1") ||
		                description.equals("|i1"))
			return new Int.Int8();
		if (description.equals("<i2"))
			return new Int.Int32.LE();
		if (description.startsWith("|S"))
			return new Str(Integer.parseInt(description.substring(2)));
		throw new BadBitForm(description);
	}

	public abstract Type read(ByteBuffer buffer, int offset);
	public abstract JavaType read2(ByteBuffer buffer, int offset);
	public String toString()
	{
		return this.getClass().getName().replace("$", ".") + "()";
	}

	public static abstract class Int extends BitForm {
		@Override
		public abstract Type.Int read(ByteBuffer buffer, int offset);

		@Override
		public abstract JavaType.Int read2(ByteBuffer buffer, int offset);

		public static class Int8 extends Int {
			@Override
			public Type.Int read(ByteBuffer buffer, int offset) {
				byte data;
				try {
					data = buffer.get(offset);
				} catch (IndexOutOfBoundsException e) {
					throw new ExpressionFault.IndexError(offset, buffer.limit());
				}
				return new Type.Int(data);
			}

			@Override
			public JavaType.Int read2(ByteBuffer buffer, int offset)
			{
				byte data;
				try {
					data = buffer.get(offset);
				} catch (IndexOutOfBoundsException e) {
					throw new ExpressionFault.IndexError(offset, buffer.limit());
				}
				return new JavaType.Int(data);
			}

		}

		public static abstract class Int32 extends Int {
			public static class LE extends Int32 {
				@Override
				public Type.Int read(ByteBuffer buffer, int byteoffset) {
					int data;
					try {
						data = buffer.getInt(byteoffset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(byteoffset, buffer.limit());
					}
					return new Type.Int(data);
				}
				@Override
				public JavaType.Int read2(ByteBuffer buffer, int byteoffset) {
					int data;
					try {
						data = buffer.getInt(byteoffset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(byteoffset, buffer.limit());
					}
					return new JavaType.Int(data);
				}
			}
		}
	}

	public static class Str extends BitForm {
		final int size;
		public Str(int size) {
			this.size = size;
		}

		@Override
		public Type.String read(ByteBuffer buffer, int byteoffset) {
			log.info("BitForm.Str.read: buffer=%s byteoffset=%d",
			         buffer, byteoffset);
			buffer.limit(byteoffset + this.size).position(byteoffset);

			byte[] data = new byte[this.size];
			buffer.get(data);
			return new Type.String(new String(data));
		}

		@Override
		public JavaType.Str read2(ByteBuffer buffer, int byteoffset) {
			log.info("BitForm.Str.read: buffer=%s byteoffset=%d",
			         buffer, byteoffset);
			buffer.limit(byteoffset + this.size).position(byteoffset);

			byte[] data = new byte[this.size];
			buffer.get(data);
			return new JavaType.Str(new String(data));
		}

		public String toString()
		{
			return this.getClass().getName().replace("$", ".")
				+ "(" + this.size + ")";
		}

	}
}
