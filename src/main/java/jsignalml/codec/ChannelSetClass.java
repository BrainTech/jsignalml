package jsignalml.codec;

import java.util.ArrayList;
import java.util.Iterator;

import jsignalml.Channel;
import jsignalml.ContextVisitor;
import jsignalml.ExpressionFault;
import jsignalml.util;
import jsignalml.logging.Logger;

public abstract class ChannelSetClass extends Context implements jsignalml.ChannelSet {
	static final Logger log = new Logger(ChannelSetClass.class);

	final ArrayList<Channel> channel_list = util.newArrayList();

	@Override
	public <T> T _accept(ContextVisitor<T> v, String name, T data)
	{
		return v.visit(this, name, data);
	}

	protected void registerChannel(Channel channel)
	{
		this.channel_list.add(channel);
		log.info("registered channel %s (total %d)", channel,
			 this.getNumberOfChannels());
	}

	public int getNumberOfChannels() throws ExpressionFault {
		return this.channel_list.size();
	}

	public Channel getChannel(int channel) {
		return this.channel_list.get(channel);
	}

	/*
	 * Return the sampling frequency, if it is the same in all channels.
	 *
	 * @throws ExpressionFault if has_uniform_sampling_frequency() is false
	 */
	public double getSamplingFrequency() throws ExpressionFault
	{
		assert !this.channel_list.isEmpty();

		double ans = 0.0; // value will not be used
		int i = 0;
		for(Channel channel: this.channel_list) {
			double ans2 = channel.getSamplingFrequency();
			if(i++ == 0)
				ans = ans2;
			else
				if (ans2 != ans)
					throw new ExpressionFault.ValueError
						("non-uniform sampling frequency");
		}
		return ans;
	}

	public boolean hasUniformSamplingFrequency() throws ExpressionFault
	{
		assert !this.channel_list.isEmpty();

		double ans = 0.0; // value will not be used
		int i = 0;
		for(Channel channel: this.channel_list) {
			double ans2 = channel.getSamplingFrequency();
			if(i++ == 0)
				ans = ans2;
			else
				if (ans2 != ans)
					return false;
		}
		return true;
	}

	/*
	 * Return the number of samples, if it is the same in all channels.
	 *
	 * @throws ExpressionFault if has_uniform_sampling_frequency() is false
	 */
	public long getNumberOfSamples() throws ExpressionFault
	{
		assert !this.channel_list.isEmpty();

		long ans = 0; // value will not be used
		int i = 0;
		for(Channel channel: this.channel_list) {
			long ans2 = channel.getNumberOfSamples();
			if(i++ == 0)
				ans = ans2;
			else
				if (ans2 != ans)
					throw new ExpressionFault.ValueError
						("non-uniform number of samples");
		}
		log.info("getNumberOfSamples -> ", ans);
		return ans;
	}

	/*
	 * Return the maximum number of samples in any channel.
	 *
	 * @throws ExpressionFault if an error occurs
	 */
	public long getMaxNumberOfSamples() throws ExpressionFault
	{
		assert !this.channel_list.isEmpty();

		long ans = 0; // value will not be used
		for(Channel channel: this.channel_list) {
			long ans2 = channel.getNumberOfSamples();
			if (ans2 > ans)
				ans = ans2;
		}
		log.info("getMaxNumberOfSamples -> ", ans);
		return ans;
	}


	public boolean has_calibration() throws ExpressionFault { return true; };

	/*
	 * Return values across channels for a single time offset.
	 */
	public float[] getSample(long sample)
	{
		float ans[] = new float[this.channel_list.size()];

		int i = 0;
		for(Channel channel: this.channel_list)
			ans[i++] = channel.getSample(sample);

		return ans;
	}

	public Iterator<Channel> iterator()
	{
		return channel_list.iterator();
	}
}
