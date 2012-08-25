package jsignalml.data;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Arrays;

import jsignalml.Source;
import jsignalml.ChannelSet;
import jsignalml.logging.Logger;
import jsignalml.TypeInt;
import jsignalml.util;
import jsignalml.helpers;

import org.apache.commons.io.FileUtils;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CodecSampleCase {
	public static final Logger log = new Logger(CodecSampleCase.class);

	public final Source source;
	public final File main_file;

	public final HdrInfo hdr;
	public final DataInfo data;

	public CodecSampleCase(Source source, File main_file,
			       HdrInfo hdr, DataInfo data)
		throws IOException
	{
		// if(!dir.exists())
		// 	throw new IOException("directory '" + dir + "' does not exist");
		this.source = source;
		this.main_file = main_file;

		this.hdr = hdr;
		this.data = data;
	}

	public String toString() {
		return getClass().getSimpleName() + " for " +
			source.getClass().getName() + " from " + main_file;
	}

	@Test
	public void test_open()
		throws FileNotFoundException, IOException
	{
		this.source.open(this.main_file);
	}

	@Test(dependsOnMethods={"test_open"})
	public void test_format_description() {
		assertNotNull(this.source.getFormatID());
		assertNotNull(this.source.getFormatDescription());

		assertNotNull(this.source.getFormatName());
		assertNotNull(this.source.getFormatProvider());
		assertNotNull(this.source.getFormatVersion());
		assertNotNull(this.source.getCodecProvider());
		assertNotNull(this.source.getCodecVersion());
	}

	@Test(dependsOnMethods={"test_open"})
	public void test_current_filename() {
		assertEquals(this.source.getCurrentFilename(),
			     this.main_file);
	}

	ChannelSet channel_set = null;

	@Test(dependsOnMethods={"test_open"})
	public void test_get_set() {
		ChannelSet set = this.source.get_set();
		assertNotNull(set);
		this.channel_set = set;
	}

	@Test(dependsOnMethods={"test_get_set"})
	public void test_sampling_frequency() {
		double expected = (Double) hdr.get("sampling_frequency").getValue();
		double current = channel_set.getSamplingFrequency();
		helpers.assertEquals(current, expected);
	}

	@Test(dependsOnMethods={"test_get_set"})
	public void test_number_of_channels() {
		int expected = ((TypeInt)hdr.get("number_of_channels")).safeIntValue();
		int current = channel_set.getNumberOfChannels();
		assertEquals(current, expected);
	}

	@Test(dependsOnMethods={"test_get_set"})
	public void test_all_samples_from_set()
		throws Exception
	{
		float[] expected, current;
		long i = 0;
		while((expected = data.getNextSample()) != null) {
			current = channel_set.getSample(i);
			try {
				helpers.assertEquals(current, expected);
			} catch(AssertionError e) {
				throw new AssertionError("sample " + i +
							 " differs: " + e);
			}
			i++;
		}
	}

	/**
	 * Create testcases for all test files underneath a given dir.
	 * All found .hdr files are assumed to be valid test cases.
	 */
	public static Collection<Object> find(Source source, File dir,
					      String extension)
		throws java.io.FileNotFoundException,
		       java.io.IOException
	{
		try {
			return _find(source, dir, extension);
		} catch(Exception e) {
			return Arrays.asList((Object)
				new FailedTestCase(e, source, dir, extension));
		}
	}

	public static Collection<Object> _find(Source source, File dir,
					      String extension)
		throws java.io.FileNotFoundException,
		       java.io.IOException
	{
		log.debug("looking for %s with ext .%s in %s",
			  source, extension, dir);
		Iterator<File> hdrs
			= FileUtils.iterateFiles(dir, new String[] {"hdr"}, true);

		LinkedList<Object> list = util.newLinkedList();

		while(hdrs.hasNext()) {
			File hdr_file = hdrs.next();
			Object testcase;
			try {
				testcase = createTestcase(source, hdr_file,
							  extension);
			} catch(final Exception e) {
				log.exception("failed to create testcase", e);
				testcase = new FailedTestCase(e,
					     source, dir, extension, hdr_file);
			}
			list.add(testcase);
		}

		return list;
	}

	public static class FailedTestCase {
		final Exception e;
		final Object[] args;
		public FailedTestCase(Exception e, Object... args) {
			this.e = e;
			this.args = args;
		}

		public String toString() {
			StringBuffer buf =
				new StringBuffer(this.getClass().getName() + "[");
			for(Object obj: this.args)
				buf.append(obj.toString() + ",");
			buf.append("]");
			return buf.toString();
		}

		@Test
		public void failed_to_create_testcase()
			throws Exception
		{
			throw this.e;
		}
	}

	static CodecSampleCase createTestcase(Source source, File hdr_file,
					      String extension)
		throws java.io.FileNotFoundException,
		       java.io.IOException
	{
			final String hdr_path = hdr_file.toString();
			assert hdr_path.endsWith(".hdr");
			final HdrInfo hdr = new HdrFile(hdr_file);

			final String basename =
				hdr_path.substring(0, hdr_path.length()-4);

			File ascii_path = new File(basename + ".ascii");
			final DataInfo data;
			if (ascii_path.exists()) {
				data = new AsciiFile(ascii_path);
			} else {
				ascii_path = new File(basename + ".ascii.xz");
				data = AsciiFile.compressed(ascii_path);
			}

			final File main_path = new File(basename + "." + extension);

			return new CodecSampleCase(source, main_path, hdr, data);
	}
}
