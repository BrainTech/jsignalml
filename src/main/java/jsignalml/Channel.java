package jsignalml;

import java.nio.FloatBuffer;
import java.nio.BufferUnderflowException;

public interface Channel {
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
	float getSample(long sample);

	void getSamples(FloatBuffer dst, long sample) throws BufferUnderflowException;
	/* void getSamples(DoubleBuffer dst, long sample); */

	double get_sampling_frequency();

	long get_number_of_samples() throws ExpressionFault;
	String get_channel_name() throws ExpressionFault;
}
