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
		//if (description.equals("<i2"))
		//	return new Int.Int16.LE();
		if (description.equals("<i4"))
			return new Int.Int32.LE();
		if (description.equals("<i8"))
			return new Int.Int64.LE();
		if (description.equals("<u8"))
			return new Int.Unsigned64.LE();
		if (description.equals("<f4"))
			return new Float.Float32.LE();
		if (description.startsWith("|S"))
			return new Str(Integer.parseInt(description.substring(2)));
		throw new BadBitForm(description);
	}

	public static BitForm get(JavaType.Str description)
		throws BadBitForm
	{
		return get(description.value);
	}

	public abstract Type read(ByteBuffer buffer, int offset);
	public abstract JavaType read2(ByteBuffer buffer, JavaType.Int offset);
	public String toString()
	{
		return this.getClass().getName().replace("$", ".") + "()";
	}

	public static abstract class Int extends BitForm {
		@Override
		public abstract Type.Int read(ByteBuffer buffer, int offset);

		@Override
		public abstract JavaType.Int read2(ByteBuffer buffer, JavaType.Int offset);

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
			public JavaType.Int read2(ByteBuffer buffer, JavaType.Int offset)
			{
				byte data;
				int offset_ = offset.safeIntValue();
				try {
					data = buffer.get(offset_);
				} catch (IndexOutOfBoundsException e) {
					throw new ExpressionFault.IndexError(offset_, buffer.limit());
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
				public JavaType.Int read2(ByteBuffer buffer, JavaType.Int byteoffset) {
					int data;
					int offset = byteoffset.safeIntValue();
					try {
						data = buffer.getInt(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return new JavaType.Int(data);
				}
			}
		}

		public static abstract class Int64 extends Int {
			public static class LE extends Int64 {
				@Override
				public Type.Int read(ByteBuffer buffer, int byteoffset) {
					long data;
					try {
						data = buffer.getLong(byteoffset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(byteoffset, buffer.limit());
					}
					return new Type.Int(data);
				}
				@Override
				public JavaType.Int read2(ByteBuffer buffer, JavaType.Int byteoffset) {
					long data;
					int offset = byteoffset.safeIntValue();

					log.debug("read2(%s, %s)", buffer, byteoffset);

					try {
						data = buffer.getLong(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return new JavaType.Int(data);
				}
			}
		}


		public static abstract class Unsigned64 extends Int {
			public static class LE extends Unsigned64 {
				@Override
				public Type.Int read(ByteBuffer buffer, int byteoffset) {
					long data;
					try {
						data = buffer.getLong(byteoffset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(byteoffset, buffer.limit());
					}
					return new Type.Int(data);
				}
				@Override
				public JavaType.Int read2(ByteBuffer buffer, JavaType.Int byteoffset) {
					long data;
					int offset = byteoffset.safeIntValue();

					log.debug("read2(%s, %s)", buffer, byteoffset);

					try {
						data = buffer.getLong(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return JavaType.Int.makeFromUnsignedReadAsSigned(data);
				}
			}
		}

	}

	public static abstract class Float extends BitForm {
		public abstract static class Float32 extends Float {
			public static class LE extends Float32 {
				@Override
				public Type.Float read(ByteBuffer buffer, int offset) {
					float data;
					try {
						data = buffer.getFloat(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return new Type.Float(data);
				}

				@Override
				public JavaType.Float read2(ByteBuffer buffer, JavaType.Int offset)
				{
					float data;
					int offset_ = offset.safeIntValue();
					try {
						data = buffer.getFloat(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return new JavaType.Float(data);
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
			buffer = buffer.asReadOnlyBuffer(); // XXX: don't use limit?
			buffer.limit(byteoffset + this.size).position(byteoffset);

			byte[] data = new byte[this.size];
			buffer.get(data);
			return new Type.String(new String(data));
		}

		@Override
		public JavaType.Str read2(ByteBuffer buffer, JavaType.Int byteoffset) {
			int offset = byteoffset.safeIntValue();
 			log.info("BitForm.Str.read: buffer=%s byteoffset=%d",
			         buffer, offset);
			buffer = buffer.asReadOnlyBuffer(); // XXX: don't use limit?
			buffer.limit(offset + this.size).position(offset);

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
