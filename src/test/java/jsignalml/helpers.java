package jsignalml;

import java.io.File;
import java.io.FilenameFilter;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

public class helpers {
	public static void assertInstanceOf(Object obj, Class<?> klass) {
		if (!klass.isInstance(obj))
			throw new AssertionError("got type " +
						 obj.getClass().getName() +
						 ", but expected " +
						 klass.getName());
	}

	public static final boolean keep_tmp_files =
		System.getProperties()
		.getProperty("jsignalml.test.keep", "")
		.length() > 0;

	/**
	 * Creates a temporary directory with prefix in name.
	 * prefix is currently ignored. Will be deleted on JVM exit
	 * unless jsignalml.test.keep is set.
	 */
	public static File temporaryDir(String prefix)
		throws java.io.IOException
	{
		File dir = Files.createTempDir();
		if (!keep_tmp_files)
			FileUtils.forceDeleteOnExit(dir);
		return dir;
	}

	public static final String FILE_SEP =
		System.getProperty("file.separator");


	public static final FilenameFilter xml_file_filter =
		new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		};
	public static final FilenameFilter java_file_filter =
		new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		};
}
