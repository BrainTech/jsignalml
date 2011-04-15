package jsignalml.codec;

import jsignalml.Type;
import jsignalml.TypeInt;
import jsignalml.TypeFloat;
import jsignalml.TypeString;
import jsignalml.BitForm;
import jsignalml.ExpressionFault;
import jsignalml.MyBuffer;

import java.nio.FloatBuffer;
import java.nio.BufferUnderflowException;

import java.lang.Double;

public abstract class ChannelClass extends Context implements jsignalml.Channel {

	public float getSample(long sample)
	{
              TypeInt offset = this.map_sample(sample);
              TypeString format = this.sample_format();
              BitForm theformat = BitForm.get(format);
              Type input = theformat.read(_buffer().source, offset);
              return new TypeFloat().make(input).getValue().floatValue();
	}

        protected abstract TypeInt map_sample(long sample);
        protected abstract TypeString sample_format();
        protected abstract MyBuffer _buffer();

	public void getSamples(FloatBuffer dst, long sample) throws BufferUnderflowException
	{
		while(dst.hasRemaining())
			dst.put(this.getSample(sample));
	}
}
