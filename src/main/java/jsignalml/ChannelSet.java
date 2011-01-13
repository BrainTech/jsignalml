package jsignalml;

/* Svarog will require and accept only one ChannelSet for now, but the
 * introduction of this concept now will make things easier later on.
 */
public interface ChannelSet extends Iterable<Channel> {
	long get_number_of_channels() throws ExpressionFault;
	Channel get_channel(Type.Int channel);

	/*
	 * Return the sampling frequency, if it is the same in all channels.
	 *
	 * @throws ExpressionFault if has_uniform_sampling_frequency() is false
	 */
	double get_sampling_frequency()
		throws ExpressionFault;
	boolean has_uniform_sampling_frequency()
		throws ExpressionFault;

	/*
	 * Return the number of samples, if it is the same in all channels.
	 *
	 * @throws ExpressionFault if has_uniform_sampling_frequency() is false
	 */
	long get_number_of_samples()
		throws ExpressionFault;

	boolean has_calibration() throws ExpressionFault;

	/*
	 * Return values across channels for a single time offset.
	 */
	float[] getSample(Type.Int sample);

	/* Fill dst with samples. Will be implemented in the abstract
	 * base class by calling lower level methods.
	 *
	 * @param chn: Either the channel number of -1 to mean all
	 * channels.
	 *
	 */
	/*		void fillChannelSamples(float[][] dst, long dst_offset,
					int chn, Type.Int offset, long count)
			throws ExpressionFault;
	*/
}