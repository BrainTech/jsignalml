package jsignalml.codec;

import jsignalml.ContextVisitor;
import jsignalml.DefaultHeader.DefaultCodecId;
import jsignalml.DefaultHeader.DefaultFormatId;

public abstract class Header extends Context {

	public String id() {
		return "header";
	}

	/**
	 * Prepares default codec id for codecs without this parameter
	 *
	 * @return empty codec id
	 */
	public CodecId get_codec_id() {
		return new DefaultCodecId();
	}

	/**
	 * Prepares default format id for codecs without this parameter
	 *
	 * @return empty format id
	 */
	public FormatId get_format_id() {
		return new DefaultFormatId();
	}

	@Override
	public <T> T _accept(ContextVisitor<T> v, String name, T data) {
		return v.visit(this, name, data);
	}
}
