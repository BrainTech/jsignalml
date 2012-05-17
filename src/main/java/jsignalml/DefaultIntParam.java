package jsignalml;

import jsignalml.codec.Param;

/**
 * This class is a default int parameter instance,
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
		return "" + value; // this value is never used
	}

	/**
	 * Returns the default value for the parameter
	 */
	protected TypeInt _get() {
		return new TypeInt(value);
	}
}