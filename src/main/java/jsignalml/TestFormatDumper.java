package jsignalml;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.io.FilenameUtils;

import jsignalml.codec.Signalml;

/**
 * Dumps data from a data file, using a Signalml codec.
 * <p>
 * Sample values are written on stdout, as space seperated values, one sample
 * (<i>n</i> channels), per output line:
 * <pre>
 *    ch1-sample1 ch2-sample1 ch3-sample1 ... chn-sample1
 *    ch1-sample2 ch2-sample2 ch3-sample2 ... chn-sample2
 *    ...
 *    ch1-samplem ch2-samplem ch3-samplem ... chn-samplem
 * </pre>
 * <p>
 * Metadata are written to <tt>&lt;filename-minus-extension&gt;.hdr</tt> in
 * current directory. The format is
 * <ul>
 *   <li><tt>field = value</tt>
 * </ul>
 * or
 * <ul>
 *   <li><tt>field = value1, value2, value3, ...</tt>
 * </ul>
 * for per-channel values.
 * <p>
 * The following fields should be present:
 * <ul>
 *   <li><tt>number_of_samples</tt>
 *   <li><tt>number_of_channels</tt>
 *   <li><tt>sampling_frequency</tt>
 *   <li><tt>channel_labels</tt>
 * </ul>
 * <p>
 * If the signal is scaled by the codec <tt>scaled = 1</tt> is added.
 * If the signal is not scaled, the following fields are added:
 * <ul>
 *   <li><tt>scaled = 0</tt>
 *   <li><tt>scaling_gain</tt>
 *   <li><tt>scaling_offset</tt>
 * </ul>
 * 
 * To generate data in the "test format", the following invocation can be used:
 * <pre>
 *   java jsignalml.TestFormatDumper &lt;codec-name&gt; &lt;data-file&gt;.&lt;ext&gt; |
 *          xz -9v &gt; &lt;data-file&gt;.ascii.xz
 * </pre>
 * This should generate <tt>.hdr</tt> and <tt>.ascii.xz</tt> files in current
 * directory.
 */
public class TestFormatDumper {
	static Source makeSource(String klass_name, String file)
		throws Exception
	{
		File file_ = new File(file);

		Class<?> klass = Class.forName(klass_name);
		Class<? extends Signalml> source_klass =
			klass.asSubclass(Signalml.class);
		Signalml source = source_klass.newInstance();

		source.open(file_);
		source.createParams();
		source.createChannels();

		return source;
	}

	static void dumpTestFormat(Source source, String output_prefix)
		throws Exception
	{
		FileOutputStream header_f =
			new FileOutputStream(output_prefix + ".hdr");
		PrintStream header = new PrintStream(header_f);

		dumpHeader(source, header);
		dumpAscii(source, System.out);
	}

	static void dumpHeader(Source source, PrintStream out)
		throws Exception
	{
		ChannelSet set = source.get_set();
		int channels = set.getNumberOfChannels();

		out.format("%s = %d\n", "number_of_channels",
			   channels);
		out.format("%s = %d\n", "number_of_samples",
			   set.getNumberOfSamples());
		out.format("%s = %f\n", "sampling_frequency",
			   set.getSamplingFrequency());

		{
			String labels[] = new String[channels];
			for(int i=0; i<channels; i++) {
				Channel channel = set.getChannel(i);
				String name = channel.getChannelName();
				labels[i] = name;
			}
			out.format("%s = %s\n", "channel_labels",
				   StringUtils.join(labels, ", "));
		}

		out.format("%s = %d\n", "scaled", 1);
	}

	static void dumpAscii(Source source, PrintStream out)
		throws Exception
	{
		ChannelSet set = source.get_set();
		int channels = set.getNumberOfChannels();
		long samples = set.getNumberOfSamples();

		for(long sample=0; sample<samples; sample++) {
			float[] Sample = set.getSample(sample);
			for(int i=0; i<channels; i++)
				out.format("%f%c", Sample[i],
					   i < channels-1 ? ' ' : '\n');
		}
	}

	public static void main(String... args)
		throws Exception
	{
		final String klass_name = args[0];
		final String file_name = args[1];

		Source source = makeSource(klass_name, file_name);
		// String prefix = FilenameUtils.removeExtension(file_name);
		String prefix = FilenameUtils.getBaseName(file_name);
		dumpTestFormat(source, prefix);
	}
}
