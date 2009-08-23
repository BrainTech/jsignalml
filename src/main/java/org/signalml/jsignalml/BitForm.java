package org.signalml.jsignalml;

public abstract class BitForm {
    public static class BadBitForm extends Exception {
	public final String description;
	public BadBitForm(String description){
	    this.description = description;
	}
    }

    public static BitForm get(String description)
	throws BadBitForm
    {
	if(description.equals("<i1") ||
	   description.equals(">i1") ||
	   description.equals("|i1"))
	    return new Byte();
	if(description.equals("<i2"))
	    return new Int32.LE();
	throw new BadBitForm(description);
    }

    public static class Byte extends BitForm {};
    public static abstract class Int32 extends BitForm {
	public static class LE extends Int32 {}
    }
}
