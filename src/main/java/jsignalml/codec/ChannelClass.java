package jsignalml.codec;

import java.nio.BufferUnderflowException;
import java.nio.FloatBuffer;

import jsignalml.BitForm;
import jsignalml.ContextVisitor;
import jsignalml.MyBuffer;
import jsignalml.Type;
import jsignalml.TypeFloat;
import jsignalml.TypeInt;
import jsignalml.TypeString;

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
        public abstract TypeInt mapSample(long sample);
        public abstract TypeString getSampleFormat();

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
