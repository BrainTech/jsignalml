package jsignalml.data;

import java.util.Properties;
import java.util.Collection;
import java.util.List;
import java.io.File;
import java.io.FileReader;

import jsignalml.Source;
import jsignalml.JavaClassGen;
import jsignalml.CodecParser;
import jsignalml.compiler.CompiledClass;
import jsignalml.compiler.MemoryWriter;
import jsignalml.util;

import org.testng.annotations.Factory;
import org.testng.annotations.DataProvider;

public class TestCompileAndCompare {

	@Factory(dataProvider="formats_and_codecs")
	public Object[] generate_testcase(String format, String codec)
		throws Exception
	{
		if (codec_basedir == null)
			return new Object[][] {};
		assert codec_basedir.exists();

		final File codec_path = new File("specs", codec + ".xml");
		final JavaClassGen gen = CodecParser.generateFromFile(codec_path);
		final String name = gen.getClassName();
		final CharSequence code = gen.getSourceCode();

		CompiledClass klass = new CompiledClass(name, code);

		String ext = getTestcaseExt(format);
		File dirs[] = getTestcaseDirs(format);
		Collection<CodecTestCase> coll = util.newArrayList();
		for(File dir: dirs) {
			Source inst = (Source) klass.newInstance();
			coll.addAll(CodecTestCase.find(inst, dir, ext));
		}

		return coll.toArray();
	}

	public static final String codec_testcases =
		"target/test-classes/testcase.properties";
	public static final Properties config = new Properties();
	static {
		try {
			config.load(new FileReader(codec_testcases));
		} catch(Exception e) {
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
}
