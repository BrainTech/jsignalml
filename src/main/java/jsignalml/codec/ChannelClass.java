package jsignalml.codec;

import jsignalml.Type;
import jsignalml.TypeInt;
import jsignalml.TypeFloat;
import jsignalml.TypeString;
import jsignalml.BitForm;
import jsignalml.ExpressionFault;
import jsignalml.MyBuffer;
import jsignalml.ContextVisitor;

import java.nio.FloatBuffer;
import java.nio.BufferUnderflowException;

import java.lang.Double;

public abstract class ChannelClass extends Context implements jsignalml.Channel {

	public float getSample(long sample)
	{
              TypeString format = this.getSampleFormat();
              TypeInt offset = this.mapSample(sample);
              BitForm theformat = BitForm.get(format);
              Type input = theformat.read(_buffer().source, offset);
              return new TypeFloat().make(input).getValue().floatValue();
	}

        protected abstract MyBuffer _buffer();
        protected abstract TypeInt mapSample(long sample);
        protected abstract TypeString getSampleFormat();

	public void getSamples(FloatBuffer dst, long sample) throws BufferUnderflowException
	{
		while(dst.hasRemaining())
			dst.put(this.getSample(sample));
	}

	@Override
	public <T> T _accept(ContextVisitor<T> v, String name, T data)
	{
		return v.visit(this, name, data);
	}
}
