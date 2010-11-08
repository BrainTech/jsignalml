package org.signalml.jsignalml.files;

public abstract class Mapping {
	public abstract int map(int channel, int sample);

	static public class Multiplex {
		public final int number_of_channels;
		public Multiplex(int number_of_channels) {
			this.number_of_channels = number_of_channels;
		}

		public int map(int channel, int sample) {
			return this.number_of_channels * sample + channel;
		}
	}

	static public class EDF {
		final int samples_per_frame[];
		final int frame_size;
		final int channel_offset[];

		public EDF(int samples_per_frame[], int frame_size,
		           int channel_offset[])
		{
			this.samples_per_frame = samples_per_frame;
			for (int samples: samples_per_frame)
				assert samples > 0;

			this.frame_size = frame_size;
			this.channel_offset = channel_offset;
		}

		public int map(int channel, int sample) {
			return sample/samples_per_frame[channel] * frame_size
			       + channel_offset[channel] +
			       sample % samples_per_frame[channel];
		}
	}
	/*
	static public class Custom {
	public final Expression expr;
	public final CallHelper state;
	public Custom(Function expr, CallHelper state){
	    ???
	}
	}
	*/
}
