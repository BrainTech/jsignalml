package jsignalml.data;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Set;
import java.util.LinkedHashSet;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import jsignalml.Type;
import jsignalml.TypeInt;
import jsignalml.TypeFloat;
import jsignalml.TypeList;
import jsignalml.helpers;

public class TestHdrFile {
	HdrInfo info = null;

	@Test
	public void test_HdrFile()
		throws IOException, FileNotFoundException
	{
		InputStream stream = getClass().getResourceAsStream("snokac.metainfo");
		this.info = new HdrFile("snokac.metainfo", stream);
	}

	@Test(dependsOnMethods={"test_HdrFile"})
	public void test_number_of_samples()
	{
		assert this.info != null;

		Type number_of_samples = info.get("number_of_samples");
		assertEquals(number_of_samples, TypeList.make(300750));
	}

	@Test(dependsOnMethods={"test_HdrFile"})
	public void test_number_of_channels()
	{
		assert this.info != null;

		Type number_of_channels = info.get("number_of_channels");
		assertEquals(number_of_channels, new TypeInt(8));
	}

	@Test(dependsOnMethods={"test_HdrFile"})
	public void test_sampling_frequency()
	{
		assert this.info != null;

		Type sampling_frequency = info.get("sampling_frequency");
		assertEquals(sampling_frequency, TypeList.make(250));
		helpers.assertInstanceOf(sampling_frequency, TypeList.class);
	}

	@Test(dependsOnMethods={"test_HdrFile"})
	public void test_channel_labels()
	{
		assert this.info != null;

		Type channel_labels = info.get("channel_labels");
		assertEquals(channel_labels,
			     TypeList.make("Fp1", "Fp2", "F7", "F3",
					   "Fz", "F4", "F8", "T3"));
		helpers.assertInstanceOf(channel_labels, TypeList.class);
	}

	static final String[] fields = {
		"number_of_samples",
		"number_of_channels",
		"channel_labels",
		"sampling_frequency",
		"scaled"
	};

	@Test(dependsOnMethods={"test_HdrFile"})
	public void test_listing()
	{
		assert this.info != null;

		Set<String> keys = this.info.keySet();
		assertEquals(keys, new LinkedHashSet(Arrays.asList(fields)));
	}
}
