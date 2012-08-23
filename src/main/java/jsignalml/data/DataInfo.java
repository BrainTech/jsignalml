package jsignalml.data;

import java.io.IOException;

public interface DataInfo {
	/**
	 * Get one sample. Array can be overriden on next query.
	 */
	float[] getNextSample() throws IOException;

}
