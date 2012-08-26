package jsignalml.codec;

import jsignalml.TypeString;

/**
 * This class forms a default string parameter,
 * used for optional parameters which are not defined in signalml explicitly.
 *
 * @author kago
 *
 */
public class DefaultStringParam extends Param<TypeString> {

	// default value
	private String value;

	public DefaultStringParam(String value){
		this.value = value;
	}

	/**
	 * The default id
	 */
	public String id() {
		throw new RuntimeException("should not be reached");
	}

	/**
	 * Returns the default value for the parameter
	 */
	protected TypeString _get() {
		return new TypeString(value);
	}
}
