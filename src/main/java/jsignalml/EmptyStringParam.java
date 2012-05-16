package jsignalml;

import jsignalml.codec.Param;

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
		return ""; // this value is never used
	}

	/**
	 * Returns the empty string as a parameter value
	 */
	protected TypeString _get() {
		return new TypeString();
	}

}
