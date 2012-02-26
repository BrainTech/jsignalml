package jsignalml;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.net.URLClassLoader;
import java.net.URL;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.io.FilenameUtils;

import jsignalml.codec.Signalml;

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
		FileOutputStream
			header_f = new FileOutputStream(output_prefix + ".hdr"),
			ascii_f = new FileOutputStream(output_prefix + ".ascii");
		PrintStream
			header = new PrintStream(header_f),
			ascii = new PrintStream(ascii_f);

		dumpHeader(source, header);
		dumpAscii(source, ascii);
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
