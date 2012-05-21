package jsignalml;

import java.nio.BufferUnderflowException;
import java.nio.FloatBuffer;

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

	double getSamplingFrequency() throws ExpressionFault;
	long getNumberOfSamples() throws ExpressionFault;
	String getChannelName() throws ExpressionFault;
	String getChannelTypeName() throws ExpressionFault;

}
