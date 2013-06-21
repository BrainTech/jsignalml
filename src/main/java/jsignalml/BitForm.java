package jsignalml;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import jsignalml.logging.Logger;

public abstract class BitForm {
	protected static final Logger log = new Logger(BitForm.class);

	public static class PrimitiveConversion {

		static long makeFromUnsignedReadAsSigned(byte value) {
			log.info("converting byte %s", value);
			if (value >= 0)
				return value;
			return value + Byte.MIN_VALUE * -2;
		}

		static long makeFromUnsignedReadAsSigned(short value) {
			log.info("converting short %s", value);
			if (value >= 0)
				return value;

			return value + Short.MIN_VALUE * -2;
		}

		static long makeFromUnsignedReadAsSigned(int value) {
			log.info("converting int %s", value);
			if (value >= 0)
				return value;

			return value + Long.valueOf(Integer.MIN_VALUE) * -2L;
		}

		static long makeFromUnsignedReadAsSigned(long value) {
			log.info("converting long %s", value);
			if (value >= 0)
				return value;
			else
				// Value beyond range of long
				throw new ExpressionFault.ValueError("overflow");
		}
	}

	public static class BadBitForm extends Exception {
		private static final long serialVersionUID = 1444830363920758159L;

		public BadBitForm(java.lang.String description) {
			super("bad BitFormat description '" + description + "'");
		}
	}

	public static BitForm get(java.lang.String description) throws BadBitForm {
		if (description.equals("<i1") || description.equals(">i1")
				|| description.equals("|i1"))
			return new Int.Int8();
		if (description.equals("<i2"))
			return new Int.Int16.LE();
		if (description.equals("<i4"))
			return new Int.Int32.LE();
		if (description.equals("<i8"))
			return new Int.Int64.LE();
		if (description.equals("<u2"))
			return new Int.Unsigned.Unsigned16.LE();
		if (description.equals("<u4"))
			return new Int.Unsigned.Unsigned32.LE();
		if (description.equals("<u8"))
			return new Int.Unsigned.Unsigned64.LE();

		if (description.equals("<u1") || description.equals(">u1")
				|| description.equals("|u1"))
			return new Int.Unsigned.Unsigned8();
		if (description.equals(">i2"))
			return new Int.Int16.BE();
		if (description.equals(">i4"))
			return new Int.Int32.BE();
		if (description.equals(">i8"))
			return new Int.Int64.BE();
		if (description.equals(">u2"))
			return new Int.Unsigned.Unsigned16.BE();
		if (description.equals(">u4"))
			return new Int.Unsigned.Unsigned32.BE();
		if (description.equals(">u8"))
			return new Int.Unsigned.Unsigned64.BE();

		if (description.equals("<f4"))
			return new Float.Float32.LE();

		if (description.equals(">f4"))
			return new Float.Float32.BE();

		if (description.equals("<f8"))
			return new Float.Float64.LE();

		if (description.equals(">f8"))
			return new Float.Float64.BE();

		if (description.startsWith("|S"))
			return new String(Integer.parseInt(description.substring(2)));
		throw new BadBitForm(description);
	}

	public static BitForm get(TypeString description)
			throws ExpressionFault.ValueError {
		try {
			return get(description.value);
		} catch (BadBitForm e) {
			throw new ExpressionFault(e);
		}
	}

	public abstract Type read(ByteBuffer buffer, TypeInt offset);

	public abstract double read(ByteBuffer buffer, int offset);

	public abstract Type readType();

	public java.lang.String toString() {
		return this.getClass().getName().replace("$", ".") + "()";
	}

	protected int correctOffset(ByteBuffer buffer, int offset_) {
		return offset_ >= 0 ? offset_ : buffer.limit() + offset_;
	}

	public static abstract class Int extends BitForm {

		public TypeInt readType() {
			return TypeInt.I;
		}

		@Override
		public double read(ByteBuffer buffer, int offset_) {
			return read_(buffer, correctOffset(buffer, offset_));
		}

		@Override
		public TypeInt read(ByteBuffer buffer, TypeInt offset) {
			return new TypeInt(read(buffer, offset.safeIntValue()));
		}

		protected abstract long read_(ByteBuffer buffer, int offset_);

		public static class Int8 extends Int {
			protected static final Logger log = new Logger(Int8.class);

			protected long read_(ByteBuffer buffer, int offset_) {
				byte data;
				try {
					data = buffer.get(offset_);
				} catch (IndexOutOfBoundsException e) {
					throw new ExpressionFault.IndexError(offset_,
							buffer.limit());
				}
				return data;
			}
		}

		public static abstract class Int16 extends Int {
			public static class LE extends Int16 {
				protected static final Logger log = new Logger(Int16.LE.class);

				protected long read_(ByteBuffer buffer, int offset_) {
					int data;
					buffer.order(ByteOrder.LITTLE_ENDIAN);
					try {
						data = buffer.getShort(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}

			public static class BE extends Int16 {
				protected static final Logger log = new Logger(Int16.BE.class);

				protected long read_(ByteBuffer buffer, int offset_) {
					int data;
					buffer.order(ByteOrder.BIG_ENDIAN);
					try {
						data = buffer.getShort(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}
		}

		public static abstract class Int32 extends Int {
			public static class LE extends Int32 {
				protected static final Logger log = new Logger(Int32.LE.class);

				@Override
				protected long read_(ByteBuffer buffer, int offset_) {
					int data;
					buffer.order(ByteOrder.LITTLE_ENDIAN);
					try {
						data = buffer.getInt(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}

			public static class BE extends Int32 {
				protected static final Logger log = new Logger(Int32.BE.class);

				@Override
				protected long read_(ByteBuffer buffer, int offset_) {
					int data;
					buffer.order(ByteOrder.BIG_ENDIAN);
					try {
						data = buffer.getInt(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}
		}

		public static abstract class Int64 extends Int {
			public static class LE extends Int64 {
				protected static final Logger log = new Logger(Int64.LE.class);

				@Override
				protected long read_(ByteBuffer buffer, int offset_) {
					long data;
					buffer.order(ByteOrder.LITTLE_ENDIAN);
					try {
						data = buffer.getLong(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}

			public static class BE extends Int64 {
				protected static final Logger log = new Logger(Int64.BE.class);

				@Override
				protected long read_(ByteBuffer buffer, int offset_) {
					long data;
					buffer.order(ByteOrder.BIG_ENDIAN);
					try {
						data = buffer.getLong(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}
		}

		public abstract static class Unsigned extends Int {

			@Override
			public double read(ByteBuffer buffer, int offset_) {
				return PrimitiveConversion.makeFromUnsignedReadAsSigned(read_(
						buffer, correctOffset(buffer, offset_)));
			}

			@Override
			public TypeInt read(ByteBuffer buffer, TypeInt offset) {
				return TypeInt.makeFromUnsignedReadAsSigned(read_(buffer,
						correctOffset(buffer, offset.safeIntValue())));
			}

			public static class Unsigned8 extends Unsigned {
				protected static final Logger log = new Logger(Unsigned8.class);

				@Override
				protected long read_(ByteBuffer buffer, int offset_) {
					byte data;
					try {
						data = buffer.get(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}

			public static abstract class Unsigned16 extends Unsigned {
				public static class LE extends Unsigned16 {
					protected static final Logger log = new Logger(
							Unsigned16.LE.class);

					@Override
					protected long read_(ByteBuffer buffer, int offset_) {
						short data;
						buffer.order(ByteOrder.LITTLE_ENDIAN);
						try {
							data = buffer.getShort(offset_);
						} catch (IndexOutOfBoundsException e) {
							throw new ExpressionFault.IndexError(offset_,
									buffer.limit());
						}
						return data;
					}
				}

				public static class BE extends Unsigned16 {
					protected static final Logger log = new Logger(
							Unsigned16.BE.class);

					@Override
					protected long read_(ByteBuffer buffer, int offset_) {
						short data;
						buffer.order(ByteOrder.BIG_ENDIAN);
						try {
							data = buffer.getShort(offset_);
						} catch (IndexOutOfBoundsException e) {
							throw new ExpressionFault.IndexError(offset_,
									buffer.limit());
						}
						return data;
					}
				}
			}

			public static abstract class Unsigned32 extends Unsigned {
				public static class LE extends Unsigned32 {
					protected static final Logger log = new Logger(
							Unsigned32.LE.class);

					@Override
					protected long read_(ByteBuffer buffer, int offset_) {
						int data;
						buffer.order(ByteOrder.LITTLE_ENDIAN);
						try {
							data = buffer.getInt(offset_);
						} catch (IndexOutOfBoundsException e) {
							throw new ExpressionFault.IndexError(offset_,
									buffer.limit());
						}
						return data;
					}
				}

				public static class BE extends Unsigned32 {
					protected static final Logger log = new Logger(
							Unsigned32.BE.class);

					@Override
					protected long read_(ByteBuffer buffer, int offset_) {
						int data;
						buffer.order(ByteOrder.BIG_ENDIAN);
						try {
							data = buffer.getInt(offset_);
						} catch (IndexOutOfBoundsException e) {
							throw new ExpressionFault.IndexError(offset_,
									buffer.limit());
						}
						return data;
					}
				}
			}

			public static abstract class Unsigned64 extends Unsigned {
				public static class LE extends Unsigned64 {
					protected static final Logger log = new Logger(
							Unsigned64.LE.class);

					@Override
					protected long read_(ByteBuffer buffer, int offset_) {
						long data;
						buffer.order(ByteOrder.LITTLE_ENDIAN);
						try {
							data = buffer.getLong(offset_);
						} catch (IndexOutOfBoundsException e) {
							throw new ExpressionFault.IndexError(offset_,
									buffer.limit());
						}
						return data;
					}
				}

				public static class BE extends Unsigned64 {
					protected static final Logger log = new Logger(
							Unsigned64.BE.class);

					@Override
					protected long read_(ByteBuffer buffer, int offset_) {
						long data;
						buffer.order(ByteOrder.BIG_ENDIAN);
						try {
							data = buffer.getLong(offset_);
						} catch (IndexOutOfBoundsException e) {
							throw new ExpressionFault.IndexError(offset_,
									buffer.limit());
						}
						return data;
					}
				}
			}
		}
	}

	public static abstract class Float extends BitForm {
		public TypeFloat readType() {
			return TypeFloat.I;
		}

		@Override
		public double read(ByteBuffer buffer, int offset_) {
			return read_(buffer, correctOffset(buffer, offset_));
		}

		@Override
		public TypeFloat read(ByteBuffer buffer, TypeInt offset) {
			return new TypeFloat(read(buffer, offset.safeIntValue()));
		}

		protected abstract double read_(ByteBuffer buffer, int offset_);

		public abstract static class Float32 extends Float {

			public static class LE extends Float32 {
				protected static final Logger log = new Logger(Float32.LE.class);

				@Override
				protected double read_(ByteBuffer buffer, int offset_) {
					float data;
					try {
						buffer.order(ByteOrder.LITTLE_ENDIAN);
						data = buffer.getFloat(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}

			public static class BE extends Float32 {
				protected static final Logger log = new Logger(Float32.BE.class);

				@Override
				protected double read_(ByteBuffer buffer, int offset_) {
					float data;
					try {
						buffer.order(ByteOrder.BIG_ENDIAN);
						data = buffer.getFloat(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}
		}

		public abstract static class Float64 extends Float {
			public static class LE extends Float64 {
				protected static final Logger log = new Logger(Float64.LE.class);

				@Override
				protected double read_(ByteBuffer buffer, int offset_) {
					float data;
					try {
						buffer.order(ByteOrder.LITTLE_ENDIAN);
						data = (float) buffer.getDouble(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}

			public static class BE extends Float64 {
				protected static final Logger log = new Logger(Float64.BE.class);

				@Override
				protected double read_(ByteBuffer buffer, int offset_) {
					float data;
					try {
						buffer.order(ByteOrder.BIG_ENDIAN);
						data = (float) buffer.getDouble(offset_);
					} catch (IndexOutOfBoundsException e) {
						throw new ExpressionFault.IndexError(offset_,
								buffer.limit());
					}
					return data;
				}
			}
		}
	}

	public static class String extends BitForm {
		protected static final Logger log = new Logger(String.class);

		public TypeString readType() {
			return TypeString.I;
		}

		final int size;

		public String(int size) {
			this.size = size;
		}

		@Override
		public double read(ByteBuffer buffer, int offset_) {
			throw new ExpressionFault.ValueError(
					"Unsupported format for Primitive Types read");
		}

		@Override
		public TypeString read(ByteBuffer buffer, TypeInt offset) {
			int offset_ = correctOffset(buffer, offset.safeIntValue());
			log.info("BitForm.String.read: buffer=%s offset=%d", buffer,
					offset_);
			buffer = buffer.asReadOnlyBuffer(); // XXX: don't use limit?
			try {
				buffer.limit(offset_ + this.size).position(offset_);
			} catch (IllegalArgumentException e) {
				throw new ExpressionFault(e);
			}

			byte[] data = new byte[this.size];
			buffer.get(data);
			return new TypeString(new java.lang.String(data));
		}

		public java.lang.String toString() {
			return this.getClass().getName().replace("$", ".") + "("
					+ this.size + ")";
		}
	}

	public static class Bytes extends BitForm {
		protected static final Logger log = new Logger(Bytes.class);

		public TypeBytes readType() {
			return TypeBytes.I;
		}

		final int size;

		public Bytes(int size) {
			this.size = size;
		}

		@Override
		public double read(ByteBuffer buffer, int offset_) {
			throw new ExpressionFault.ValueError(
					"Unsupported format for Primitive Types read");
		}

		@Override
		public TypeBytes read(ByteBuffer buffer, TypeInt offset) {
			int offset_ = correctOffset(buffer, offset.safeIntValue());
			log.info("BitForm.Bytes.read: buffer=%s offset=%d", buffer, offset_);
			buffer = buffer.asReadOnlyBuffer();
			try {
				buffer.limit(offset_ + this.size).position(offset_);
			} catch (IllegalArgumentException e) {
				throw new ExpressionFault(e);
			}

			byte[] data = new byte[this.size];
			buffer.get(data);
			return new TypeBytes(new java.lang.String(data));
		}

		public java.lang.String toString() {
			return this.getClass().getName().replace("$", ".") + "("
					+ this.size + ")";
		}
	}
}
