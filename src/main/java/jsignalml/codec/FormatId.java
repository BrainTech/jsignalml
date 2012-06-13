package jsignalml.codec;

import jsignalml.EmptyStringParam;
import jsignalml.TypeString;

public abstract class FormatId extends Param<TypeString> {

	public final Param<TypeString> name = new EmptyStringParam();
	public final Param<TypeString> version = new EmptyStringParam();
	public final Param<TypeString> provider = new EmptyStringParam();
}
