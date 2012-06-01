package jsignalml;

import java.nio.BufferUnderflowException;
import java.nio.FloatBuffer;

public interface Channel {
	/*
	 * Returns a single sample value.
	 *
	 * @param sample: the number of sample to return
	 */
	float getSample(long sample);

	/*
	 * Copy samples into a buffer.
	 *
	 * The indices between position and limit are filled.  Samples
	 * are copied from the source starting at sample.
	 *
	 * @param dst: the Buffer to fill with samples
	 * @param sample: the number of the first sample to copy
	 *
	 * @throws BufferUnderflowException if not enough samples are
	 * available to fill the requested area.
	 */
	void getSamples(FloatBuffer dst, long sample) throws BufferUnderflowException;

	/**
	 * Returns the sampling frequency for the channel.
	 */
	double getSamplingFrequency() throws ExpressionFault;

	/**
	 * Returns the number of channels.
	 */
	long getNumberOfSamples() throws ExpressionFault;

	/**
	 * Returns the channel name.
	 */
	String getChannelName() throws ExpressionFault;

	/**
	 * Returns the channel type string.
	 */
	String getChannelType() throws ExpressionFault;

}
