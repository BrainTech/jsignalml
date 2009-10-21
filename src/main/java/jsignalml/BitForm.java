package jsignalml;

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
	    byte data;
	    try{
		data = buffer.get(offset);
	    }catch(IndexOutOfBoundsException e){
		throw new ExpressionFault.IndexError();
	    }
	    return new Type.Int(data);
	}
    }

    public static abstract class Int32 extends BitForm {
	public static class LE extends Int32 {
	    @Override
	    public Type.Int read(ByteBuffer buffer, int byteoffset){
		int data;
		try{
		    data = buffer.getInt(byteoffset);
		}catch(IndexOutOfBoundsException e){
		    throw new ExpressionFault.IndexError();
		}
		return new Type.Int(data);
	    }
	}
    }
}
