package jsignalml;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.ByteOrder;

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

		if (description.equals("<u1") ||
		                description.equals(">u1") ||
		                description.equals("|u1"))
			return new Int.Unsigned8();
		if (description.equals(">i2"))
			return new Int.Int16.BE();
		if (description.equals(">i4"))
			return new Int.Int32.BE();
		if (description.equals(">i8"))
			return new Int.Int64.BE();
		if (description.equals(">u2"))
			return new Int.Unsigned16.BE();
		if (description.equals(">u4"))
			return new Int.Unsigned16.BE();
		if (description.equals(">u8"))
			return new Int.Unsigned64.BE();

		if (description.equals("<f4"))
			return new Float.Float32.LE();

		if (description.startsWith("|S"))
			return new String(Integer.parseInt(description.substring(2)));
		throw new BadBitForm(description);
	}

	public static BitForm get(TypeString description)
		throws ExpressionFault.ValueError
	{
		try {
			return get(description.value);
		} catch(BadBitForm e) {
			throw new ExpressionFault(e);
		}
	}

	public abstract Type read(ByteBuffer buffer, TypeInt offset);

	public abstract Type readType();

	public java.lang.String toString()
	{
		return this.getClass().getName().replace("$", ".") + "()";
	}

	public static abstract class Int extends BitForm {
		@Override
		public abstract TypeInt read(ByteBuffer buffer, TypeInt offset);

		public TypeInt readType()
		{
			return TypeInt.I;
		}

		public static class Int8 extends Int {
			protected static final Logger log = new Logger(Int8.class);
			@Override
			public TypeInt read(ByteBuffer buffer, TypeInt offset) {
				int offset_ = offset.safeIntValue();
				byte data;
				try {
					data = buffer.get(offset_);
				} catch (IndexOutOfBoundsException e) {
					throw new ExpressionFault.IndexError(offset_, buffer.limit());
				}
				return new TypeInt(data);
			}
		}

		public static abstract class Int16 extends Int {
			public static class LE extends Int16 {
				protected static final Logger log = new Logger(Int16.LE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					int data;
					buffer.order(ByteOrder.LITTLE_ENDIAN);
					try {
						data = buffer.getShort(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return new TypeInt(data);
				}
			}

			public static class BE extends Int16 {
				protected static final Logger log = new Logger(Int16.BE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					int data;
					buffer.order(ByteOrder.BIG_ENDIAN);
					try {
						data = buffer.getShort(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return new TypeInt(data);
				}
			}
		}

		public static abstract class Int32 extends Int {
			public static class LE extends Int32 {
				protected static final Logger log = new Logger(Int32.LE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					int data;
					buffer.order(ByteOrder.LITTLE_ENDIAN);
					try {
						data = buffer.getInt(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return new TypeInt(data);
				}
			}

			public static class BE extends Int32 {
				protected static final Logger log = new Logger(Int32.BE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					int data;
					buffer.order(ByteOrder.BIG_ENDIAN);
					try {
						data = buffer.getInt(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return new TypeInt(data);
				}
			}
		}

		public static abstract class Int64 extends Int {
			public static class LE extends Int64 {
				protected static final Logger log = new Logger(Int64.LE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					long data;
					buffer.order(ByteOrder.LITTLE_ENDIAN);
					try {
						data = buffer.getLong(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return new TypeInt(data);
				}
			}

			public static class BE extends Int64 {
				protected static final Logger log = new Logger(Int64.BE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					long data;
					buffer.order(ByteOrder.BIG_ENDIAN);
					try {
						data = buffer.getLong(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return new TypeInt(data);
				}
			}
		}


		public static class Unsigned8 extends Int {
			protected static final Logger log = new Logger(Unsigned8.class);
			//@Override
			public TypeInt read(ByteBuffer buffer, TypeInt offset) {
				int offset_ = offset.safeIntValue();
				byte data;
				try {
					data = buffer.get(offset_);
				} catch (IndexOutOfBoundsException e) {
					throw new ExpressionFault.IndexError(offset_, buffer.limit());
				}
				return TypeInt.makeFromUnsignedReadAsSigned(data);
			}
		}

		public static abstract class Unsigned16 extends Int {
			public static class LE extends Unsigned16 {
				protected static final Logger log = new Logger(Unsigned16.LE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					short data;
					buffer.order(ByteOrder.LITTLE_ENDIAN);
					try {
						data = buffer.getShort(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return TypeInt.makeFromUnsignedReadAsSigned(data);
				}
			}

			public static class BE extends Unsigned16 {
				protected static final Logger log = new Logger(Unsigned16.BE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					short data;
					buffer.order(ByteOrder.BIG_ENDIAN);
					try {
						data = buffer.getShort(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return TypeInt.makeFromUnsignedReadAsSigned(data);
				}
			}
		}

		public static abstract class Unsigned32 extends Int {
			public static class LE extends Unsigned32 {
				protected static final Logger log = new Logger(Unsigned32.LE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					int data;
					buffer.order(ByteOrder.LITTLE_ENDIAN);
					try {
						data = buffer.getInt(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return TypeInt.makeFromUnsignedReadAsSigned(data);
				}
			}

			public static class BE extends Unsigned32 {
				protected static final Logger log = new Logger(Unsigned32.BE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					int data;
					buffer.order(ByteOrder.BIG_ENDIAN);
					try {
						data = buffer.getInt(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return TypeInt.makeFromUnsignedReadAsSigned(data);
				}
			}
		}

		public static abstract class Unsigned64 extends Int {
			public static class LE extends Unsigned64 {
				protected static final Logger log = new Logger(Unsigned64.LE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					long data;
					buffer.order(ByteOrder.LITTLE_ENDIAN);
					try {
						data = buffer.getLong(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return TypeInt.makeFromUnsignedReadAsSigned(data);
				}
			}

			public static class BE extends Unsigned64 {
				protected static final Logger log = new Logger(Unsigned64.BE.class);
				@Override
				public TypeInt read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					long data;
					buffer.order(ByteOrder.BIG_ENDIAN);
					try {
						data = buffer.getLong(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return TypeInt.makeFromUnsignedReadAsSigned(data);
				}
			}
		}

	}

	public static abstract class Float extends BitForm {
		public TypeFloat readType()
		{
			return TypeFloat.I;
		}

		public abstract static class Float32 extends Float {
			public static class LE extends Float32 {
				protected static final Logger log = new Logger(Float32.LE.class);
				@Override
				public TypeFloat read(ByteBuffer buffer, TypeInt offset) {
					int offset_ = offset.safeIntValue();
					float data;
					try {
						data = buffer.getFloat(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_, buffer.limit());
					}
					return new TypeFloat(data);
				}
			}
		}
	}

	public static class String extends BitForm {
		protected static final Logger log = new Logger(String.class);

		public TypeString readType()
		{
			return TypeString.I;
		}

		final int size;
		public String(int size) {
			this.size = size;
		}

		@Override
		public TypeString read(ByteBuffer buffer, TypeInt offset) {
			int offset_ = offset.safeIntValue();
			log.info("BitForm.String.read: buffer=%s offset=%d",
			         buffer, offset_);
			buffer = buffer.asReadOnlyBuffer(); // XXX: don't use limit?
			try {
				buffer.limit(offset_ + this.size).position(offset_);
			} catch(IllegalArgumentException e) {
				throw new ExpressionFault(e);
			}

			byte[] data = new byte[this.size];
			buffer.get(data);
			return new TypeString(new java.lang.String(data));
		}

		public java.lang.String toString()
		{
			return this.getClass().getName().replace("$", ".")
				+ "(" + this.size + ")";
		}
	}
}
