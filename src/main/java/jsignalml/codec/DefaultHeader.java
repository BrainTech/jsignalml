package jsignalml.codec;

import jsignalml.TypeString;
import jsignalml.ContextVisitor;

public class DefaultHeader extends Header {

	@Override
	public String id() {
		return "default_header";
	}

	public static class DefaultCodecId extends CodecId {

		@Override
		protected TypeString _get() {
			return null;
		}

		@Override
		public String id() {
			return "default_codec_id";
		}

	}

	public static class DefaultFormatId extends FormatId {

		@Override
		protected TypeString _get() {
			return null;
		}

		@Override
		public String id() {
			return "default_format_id";
		}

	}

	@Override
	public CodecId get_codec_id() {
		return new DefaultCodecId();
	}

	@Override
	public FormatId get_format_id() {
		return new DefaultFormatId();
	}

	@Override
	public <T> T _accept(ContextVisitor<T> v, String name, T data) {
		return v.visit(this, name, data);
	}

	@Override
	public void createParams() {
	}
}
