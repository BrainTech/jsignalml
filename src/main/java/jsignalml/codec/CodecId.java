package jsignalml.codec;

import jsignalml.TypeString;

public abstract class CodecId extends Param<TypeString> {
	public final Param<TypeString> version = new DefaultStringParam("0");
	public final Param<TypeString> provider = new DefaultStringParam("unknown");
}
