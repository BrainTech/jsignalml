package org.signalml.jsignalml;

import org.signalml.jsignalml.sexpression.Type;
import java.nio.ByteBuffer;

public abstract class BitForm {
    public static class BadBitForm extends Exception {
	public BadBitForm(String description){
	    super("bad BitFormat description '" + description + "'");
	}
    }

    public static BitForm get(String description)
	throws BadBitForm
    {
	if(description.equals("<i1") ||
	   description.equals(">i1") ||
	   description.equals("|i1"))
	    return new Int8();
	if(description.equals("<i2"))
	    return new Int32.LE();
	throw new BadBitForm(description);
    }

    public abstract Type read(ByteBuffer buffer, int offset);

    public static class Int8 extends BitForm {
	@Override
	public Type.Int read(ByteBuffer buffer, int offset){
	    byte data = buffer.get(offset);
	    return new Type.Int(data);
	}
    }

    public static abstract class Int32 extends BitForm {
	public static class LE extends Int32 {
	    @Override
	    public Type.Int read(ByteBuffer buffer, int byteoffset){
		int data = buffer.getInt(byteoffset);
		return new Type.Int(data);
	    }
	}
    }
}
