import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.BufferUnderflowException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.log4j.BasicConfigurator;

import jsignalml.MyBuffer;
import jsignalml.Type;
import jsignalml.BitForm;
import jsignalml.ChannelSet;
import jsignalml.Channel;
import jsignalml.ExpressionFault;

public class EdfReader implements jsignalml.Source, jsignalml.ChannelSet {
	MyBuffer buffer = null;

	public void open(File filename) throws FileNotFoundException, IOException {
		buffer = new MyBuffer(filename);

		int numch = get_number_of_channels();
		channels = new EdfChannel[numch];
		for(int k=0; k<numch; k++) {
			channels[k] = new EdfChannel(k);
		}
	}

	public void close() {
		buffer.close();
	}

	long _get_version() {
		Type.String str = (Type.String) buffer.read(new BitForm.STR(8), 0);
		return new Type.Int().make(str).getValue();
	}

	long version = null;
	public long get_version() {
		if (version == null)
			version = _get_version();
		return version;
	}

	long _get_number_of_channels() {
		Type.String str = (Type.String) buffer.read(new BitForm.STR(4), 252);
		return new Type.Int().make(str).getValue();
	}

	long number_of_channels = null;
	public long get_number_of_channels() {
		if (number_of_channels == null)
			number_of_channels = _get_number_of_channels();
		return number_of_channels;
	}

	public ChannelSet get_set() {
		return this;
	}

	EdfChannel[] channels = null;
	public EdfChannel get_channel(long i) {
		return channels[i];
	}

	public String getFormatID() {
		return "EDF";
	}

	public File getCurrentFilename() {
		return buffer.getFilename();
	}

	public String getFormatDescription() {
		return null;
	}

	public float[] getSample (long sample) {
	        float[] data = new float[get_number_of_channels()];
		int i = 0;
		for(Channel channel: this)
			data[i++] = channel.getSample(sample);
		return data;
	}

	public boolean has_calibration() {
		return true;
	}

	public boolean has_uniform_sampling_frequency() {
		return false;
	}

	public long get_number_of_samples() {
		throw new ExpressionFault("has_uniform_sampling_frequency is false");
	}

	public double get_sampling_frequency() {
		throw new ExpressionFault("has_uniform_sampling_frequency is false");
	}

	public Iterator<Channel> iterator() {
		return Arrays.asList((Channel[])channels).iterator();
	}

	double duration_of_data_record = null;
	public double get_duration_of_data_record() {
		if (duration_of_data_record == null)
			duration_of_data_record = _get_duration_of_data_record();
		return duration_of_data_record;
	}
	double _get_duration_of_data_record() {
		long offset = 244;
		Type.String str = (Type.String) buffer.read(new BitForm.STR(8), offset);
		return new Type.Float().make(str).getValue();
	}

	long frame_size = null;
	public long get_frame_size() {
		if (frame_size == null)
			frame_size = _get_frame_size();
		return frame_size;
	}
	long _get_frame_size() {
		// different from the prototype in EDF.xml
		return get_channel(get_number_of_channels()-1).get_channel_offset()
			+ get_channel(get_number_of_channels()-1).get_samples_per_frame()
			* get_datatype_width();
	}

	long number_of_data_records = null;
	public long get_number_of_data_records() {
		if (number_of_data_records == null)
			number_of_data_records = _get_number_of_data_records();
		return number_of_data_records;
	}
	long _get_number_of_data_records() {
		long offset = 236;
		Type.String str = (Type.String) buffer.read(new BitForm.STR(8), offset);
		return new Type.Int().make(str).getValue();
	}

	long datatype_width = null;
	public long get_datatype_width() {
		if (datatype_width == null)
			datatype_width = _get_datatype_width();
		return datatype_width;
	}
	public long _get_datatype_width() {
		return 2;
	}

	/*
	 * *********************** EdfChannel ****************************
	 */
	class EdfChannel implements Channel {
		final long channel;
		EdfChannel(long channel) {
			this.channel = channel;
		}

		double sampling_frequency = null;
		public double get_sampling_frequency() {
			if (sampling_frequency == null)
				sampling_frequency = _get_sampling_frequency();
			return sampling_frequency;
		}

		double _get_sampling_frequency() {
			return get_samples_per_frame() / get_duration_of_data_record();
		}

		long samples_per_frame = null;
		public long get_samples_per_frame() {
			if (samples_per_frame == null)
				samples_per_frame = _get_samples_per_frame();
			return samples_per_frame;
		}
		long _get_samples_per_frame() {
			long number_of_channels = get_number_of_channels();
			long offset = 256 + 216*number_of_channels + 8*channel;
			Type.String str = (Type.String) buffer.read(new BitForm.STR(8), offset);
			return new Type.Int().make(str).getValue();
		}

		public float getSample(long sample) {
		        return sample / get_samples_per_frame() * get_frame_size() 
				+ get_channel_offset() +
				sample % get_samples_per_frame() * get_datatype_width();
		}

		public void getSamples(FloatBuffer dst, long sample) throws BufferUnderflowException {
			long wanted = dst.limit() - dst.position();
			long available = get_number_of_samples() - sample;
			assert wanted >= 0;
			if(available < 0)
				throw new ExpressionFault.IndexError(sample, get_number_of_samples());
			if(wanted > available)
				throw new BufferUnderflowException();
			while(dst.hasRemaining())
				dst.put(getSample(sample++));
		}

		public long get_number_of_samples() throws ExpressionFault {
			return get_samples_per_frame() * get_number_of_data_records();
		}

		String channel_name = null;
		public String get_channel_name() throws ExpressionFault {
			if (channel_name == null)
				channel_name = _get_channel_name();
			return channel_name;
		}
		String _get_channel_name() {
			long offset = 256 + 16*channel;
			Type.String str = (Type.String) buffer.read(new BitForm.STR(8), offset);
			return str.getValue();
		}

		long channel_offset = null;
		public long get_channel_offset() {
			if (channel_offset == null)
				channel_offset = _get_channel_offset();
			return channel_offset;
		}
		long _get_channel_offset() {
			if (channel == 0)
				return 0;
			return get_channel(channel-1).get_channel_offset() +
				get_channel(channel-1).get_samples_per_frame() *
				get_datatype_width();
		}
	}

	public static void main(String...args) throws FileNotFoundException, IOException {
		BasicConfigurator.configure();
		EdfReader reader = new EdfReader();
		reader.open(new File(args[0]));
		System.out.println("number_of_channels " + reader.get_number_of_channels());
		for(Channel ch: reader.get_set())
			System.out.println("number_of_samples " + ch.get_number_of_samples());
	}
}
