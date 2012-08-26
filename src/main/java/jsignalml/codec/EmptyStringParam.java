package jsignalml.codec;

import jsignalml.TypeString;

/**
 * This class is a dummy empty String parameter instance,
 * used for optional parameters which are not defined in signalml explicitly
 *
 * @author kago
 *
 */
public class EmptyStringParam extends Param<TypeString> {

	/**
	 * This is an empty id
	 */
	public String id() {
		throw new RuntimeException("should not be reached");
	}

	/**
	 * Returns the empty string as a parameter value
	 */
	protected TypeString _get() {
		return new TypeString();
	}

}
