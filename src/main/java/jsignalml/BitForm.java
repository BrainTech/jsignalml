package jsignalml;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public abstract class BitForm {
	protected static final Logger log = new Logger(BitForm.class);

	public static class BadBitForm extends Exception {
		public BadBitForm(java.lang.String description) {
			super("bad BitFormat description '" + description + "'");
		}
	}

	public static BitForm get(java.lang.String description)
		throws BadBitForm
	{
		if (description.equals("<i1") ||
		                description.equals(">i1") ||
		                description.equals("|i1"))
			return new Int.Int8();
		if (description.equals("<i2"))
			return new Int.Int16.LE();
		if (description.equals("<i4"))
			return new Int.Int32.LE();
		if (description.equals("<i8"))
			return new Int.Int64.LE();
		if (description.equals("<u2"))
			return new Int.Unsigned16.LE();
		if (description.equals("<u4"))
			return new Int.Unsigned16.LE();
		if (description.equals("<u8"))
			return new Int.Unsigned64.LE();
		if (description.equals("<f4"))
			return new Float.Float32.LE();
		if (description.startsWith("|S"))
			return new String(Integer.parseInt(description.substring(2)));
		throw new BadBitForm(description);
	}

	public static BitForm get(Type.String description)
		throws BadBitForm
	{
		return get(description.value);
	}

	public abstract Type read(ByteBuffer buffer, int offset);
	public abstract Type read2(ByteBuffer buffer, Type.Int offset);
	public java.lang.String toString()
	{
		return this.getClass().getName().replace("$", ".") + "()";
	}

	public static abstract class Int extends BitForm {
		@Override
		public abstract Type.Int read(ByteBuffer buffer, int offset);

		@Override
		public abstract Type.Int read2(ByteBuffer buffer, Type.Int offset);

		public static class Int8 extends Int {
			protected static final Logger log = new Logger(Int8.class);
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
			public Type.Int read2(ByteBuffer buffer, Type.Int offset)
			{
				byte data;
				int offset_ = offset.safeIntValue();

				log.debug("read2(%s, %s)", buffer, offset_);

				try {
					data = buffer.get(offset_);
				} catch (IndexOutOfBoundsException e) {
					throw new ExpressionFault.IndexError(offset_, buffer.limit());
				}
				return new Type.Int(data);
			}

		}

		public static abstract class Int16 extends Int {
			public static class LE extends Int16 {
				protected static final Logger log = new Logger(Int16.LE.class);
				@Override
				public Type.Int read(ByteBuffer buffer, int byteoffset) {
					int data;
					try {
						data = buffer.getShort(byteoffset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(byteoffset, buffer.limit());
					}
					return new Type.Int(data);
				}
				@Override
				public Type.Int read2(ByteBuffer buffer, Type.Int byteoffset) {
					int data;
					int offset = byteoffset.safeIntValue();

					log.debug("read2(%s, %s)", buffer, byteoffset);

					try {
						data = buffer.getShort(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return new Type.Int(data);
				}
			}
		}

		public static abstract class Int32 extends Int {
			public static class LE extends Int32 {
				protected static final Logger log = new Logger(Int32.LE.class);
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
				public Type.Int read2(ByteBuffer buffer, Type.Int byteoffset) {
					int data;
					int offset = byteoffset.safeIntValue();

					log.debug("read2(%s, %s)", buffer, byteoffset);

					try {
						data = buffer.getInt(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return new Type.Int(data);
				}
			}
		}

		public static abstract class Int64 extends Int {
			public static class LE extends Int64 {
				protected static final Logger log = new Logger(Int64.LE.class);
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
				public Type.Int read2(ByteBuffer buffer, Type.Int byteoffset) {
					long data;
					int offset = byteoffset.safeIntValue();

					log.debug("read2(%s, %s)", buffer, byteoffset);

					try {
						data = buffer.getLong(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return new Type.Int(data);
				}
			}
		}


		public static abstract class Unsigned16 extends Int {
			public static class LE extends Unsigned16 {
				protected static final Logger log = new Logger(Unsigned16.LE.class);
				@Override
				public Type.Int read(ByteBuffer buffer, int byteoffset) {
					short data;
					try {
						data = buffer.getShort(byteoffset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(byteoffset, buffer.limit());
					}
					return new Type.Int(data);
				}
				@Override
				public Type.Int read2(ByteBuffer buffer, Type.Int byteoffset) {
					short data;
					int offset = byteoffset.safeIntValue();

					log.debug("read2(%s, %s)", buffer, byteoffset);

					try {
						data = buffer.getShort(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return Type.Int.makeFromUnsignedReadAsSigned(data);
				}
			}
		}

		public static abstract class Unsigned32 extends Int {
			public static class LE extends Unsigned32 {
				protected static final Logger log = new Logger(Unsigned32.LE.class);
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
				public Type.Int read2(ByteBuffer buffer, Type.Int byteoffset) {
					int data;
					int offset = byteoffset.safeIntValue();

					log.debug("read2(%s, %s)", buffer, byteoffset);

					try {
						data = buffer.getInt(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return Type.Int.makeFromUnsignedReadAsSigned(data);
				}
			}
		}

		public static abstract class Unsigned64 extends Int {
			public static class LE extends Unsigned64 {
				protected static final Logger log = new Logger(Unsigned64.LE.class);
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
				public Type.Int read2(ByteBuffer buffer, Type.Int byteoffset) {
					long data;
					int offset = byteoffset.safeIntValue();

					log.debug("read2(%s, %s)", buffer, byteoffset);

					try {
						data = buffer.getLong(offset);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset, buffer.limit());
					}
					return Type.Int.makeFromUnsignedReadAsSigned(data);
				}
			}
		}

	}

	public static abstract class Float extends BitForm {
		public abstract static class Float32 extends Float {
			public static class LE extends Float32 {
				protected static final Logger log = new Logger(Float32.LE.class);
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
				public Type.Float read2(ByteBuffer buffer, Type.Int offset)
				{
					float data;
					int offset_ = offset.safeIntValue();

					log.debug("read2(%s, %s)", buffer, offset_);

					try {
						data = buffer.getFloat(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return new Type.Float(data);
				}
			}
		}

	}

	public static class String extends BitForm {
		protected static final Logger log = new Logger(String.class);

		final int size;
		public String(int size) {
			this.size = size;
		}

		@Override
		public Type.String read(ByteBuffer buffer, int byteoffset) {
			log.info("BitForm.String.read: buffer=%s byteoffset=%d",
			         buffer, byteoffset);
			buffer = buffer.asReadOnlyBuffer(); // XXX: don't use limit?
			buffer.limit(byteoffset + this.size).position(byteoffset);

			byte[] data = new byte[this.size];
			buffer.get(data);
			return new Type.String(new java.lang.String(data));
		}

		@Override
		public Type.String read2(ByteBuffer buffer, Type.Int byteoffset) {
			int offset = byteoffset.safeIntValue();
 			log.info("BitForm.String.read: buffer=%s byteoffset=%d",
			         buffer, offset);
			buffer = buffer.asReadOnlyBuffer(); // XXX: don't use limit?
			buffer.limit(offset + this.size).position(offset);

			log.debug("read2(%s, %s)", buffer, byteoffset);

			byte[] data = new byte[this.size];
			buffer.get(data);
			return new Type.String(new java.lang.String(data));
		}

		public java.lang.String toString()
		{
			return this.getClass().getName().replace("$", ".")
				+ "(" + this.size + ")";
		}

	}
}
