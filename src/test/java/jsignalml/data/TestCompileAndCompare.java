package jsignalml.data;

import java.util.Properties;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.io.File;

import jsignalml.Source;
import jsignalml.JavaClassGen;
import jsignalml.CodecParser;
import jsignalml.compiler.CompiledClass;
import jsignalml.compiler.MemoryWriter;
import jsignalml.util;

import jsignalml.logging.Logger;
import org.apache.log4j.BasicConfigurator;

import org.testng.annotations.Factory;
import org.testng.annotations.DataProvider;

public class TestCompileAndCompare {
	public static final Logger log = new Logger(TestCompileAndCompare.class);

	@Factory(dataProvider="formats_and_codecs")
	public static Object[] generate_testcase(String format, String codec)
	{
		log.info("looking for %s/%s in %s", format, codec, codec_basedir);
		if (codec_basedir == null)
			return new Object[][] {};
		assert codec_basedir.exists();

		try {
			return _generate_testcase(format, codec).toArray();
		} catch(Exception e) {
			return new Object[] {
			     new CodecSampleCase.FailedTestCase(e, format, codec)
			};
		}
	}

	static Collection<Object> _generate_testcase(String format, String codec)
		throws Exception
	{
		final File codec_path = new File("specs", codec + ".xml");
		final JavaClassGen gen = CodecParser.generateFromFile(codec_path);
		final String name = gen.getClassName();
		final CharSequence code = gen.getSourceCode();

		CompiledClass klass = new CompiledClass(name, code);

		String ext = getTestcaseExt(format);
		File dirs[] = getTestcaseDirs(format);
		Collection<Object> coll = util.newArrayList();
		for(File dir: dirs) {
			Source inst = (Source) klass.newInstance();
			coll.addAll(CodecSampleCase.find(inst, dir, ext));
		}

		return coll;
	}

	public static final Properties config = new Properties();
	static {
		try {
			config.load(TestCompileAndCompare.class
				    .getResourceAsStream("testcase.properties"));
		} catch(java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static final String SEPARATOR = ",\\s*";

	@DataProvider
	public static Object[][] formats_and_codecs() {
		List<Object[]> list = util.newArrayList();
		for(String name: config.stringPropertyNames()) {
			if (name.endsWith("-codecs")) {
				String value = (String) config.get(name);
				String format = name.substring(0, name.length()-7);
				String[] codecs = value.split(SEPARATOR);
				for (String codec: codecs)
					list.add(new Object[]{ format, codec });
			}
		}
		return list.toArray(new Object[][]{});
	}

	public static final File codec_basedir;
	static {
		String base = System.getProperty("jsignalml.test.base");
		if (base == null)
			codec_basedir = null;
		else
			codec_basedir = new File(base);
	}

	public static File[] getTestcaseDirs(String format_id) {
		assert codec_basedir != null;

		String value = (String) config.get(format_id + "-dirs");
		String dirs[] = value.split(SEPARATOR);
		File[] abs = new File[dirs.length];
		for(int i=0; i<abs.length; i++)
			abs[i] = new File(codec_basedir, dirs[i]);
		return abs;
	}

	public static String getTestcaseExt(String format_id) {
		return (String) config.get(format_id + "-ext");
	}

	public static void main(String... args)
		throws Exception
	{
		BasicConfigurator.configure();

		log.info("looking for test cases");

		Object[][] pairs = formats_and_codecs();
		for(Object[] pair: pairs) {
			Object[] cases = generate_testcase((String)pair[0],
							   (String)pair[1]);
			for(Object test: cases)
				log.info("test: %s", test);
		}
	}
}
