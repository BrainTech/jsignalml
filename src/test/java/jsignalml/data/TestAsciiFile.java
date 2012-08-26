package jsignalml.data;

import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.annotations.Test;
import static org.testng.Assert.assertNull;

import jsignalml.helpers;

public class TestAsciiFile {
	@Test
	public void test_reading()
		throws IOException
	{
		AsciiFile file = new AsciiFile(new InputStreamReader(
			  getClass().getResourceAsStream("dump1.ascii")));
		float[] sample = file.getNextSample();
		helpers.assertEquals(sample, 11f, 12f, 13f, 14f, 15f, 16f, 17f);
		sample = file.getNextSample();
		helpers.assertEquals(sample, 11.1f, 12.1f, 13.1f, 14.1f, 15.1f, 16.1f, 17.1f);
		sample = file.getNextSample();
		assertNull(sample);
	}

	@Test
	public void test_compressed()
		throws IOException
	{
		AsciiFile file = AsciiFile.compressed(
			  getClass().getResourceAsStream("dump1.ascii.xz"));
		float[] sample = file.getNextSample();
		helpers.assertEquals(sample, 11f, 12f, 13f, 14f, 15f, 16f, 17f);
		sample = file.getNextSample();
		helpers.assertEquals(sample, 11.1f, 12.1f, 13.1f, 14.1f, 15.1f, 16.1f, 17.1f);
		sample = file.getNextSample();
		assertNull(sample);
	}
}
