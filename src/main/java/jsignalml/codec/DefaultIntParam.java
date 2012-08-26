package jsignalml.codec;

import jsignalml.TypeInt;

/**
 * This class forms a default int parameter,
 * used for optional parameters which are not defined in signalml explicitly
 *
 * @author kago
 *
 */
public class DefaultIntParam extends Param<TypeInt> {

	// default value
	private int value;

	public DefaultIntParam(int value){
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
	protected TypeInt _get() {
		return new TypeInt(value);
	}
}
