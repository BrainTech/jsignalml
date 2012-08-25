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

	public static void assertEquals(float[] left, float[] right,
					float precision, float delta) {
		if (left.length != right.length)
			throw new AssertionError("arrays have different length ("
						 + left.length + "!="
						 + right.length + ")");
		for(int i=0; i<left.length; i++)
			if (Math.abs((left[i]-right[i])/right[i]) > precision &&
			    Math.abs(left[i]-right[i]) > delta)
				throw new AssertionError("arrays differ at pos "
							 + i + " (" +
							 left[i] + "!=" +
							 right[i] + ")" +
							 format(left) + " vs " +
							 format(right)
							 );
	}

	public static void assertEquals(double left, double right,
					float precision, float delta) {
		if (Math.abs((left-right)/right) > precision &&
		    Math.abs(left-right) > delta)
			throw new AssertionError("numbers differ (" +
						 left + "!=" +
						 right + ")");
	}

	public static String format(float[] array) {
		StringBuilder buf = new StringBuilder("[");
		for(float f: array)
			buf.append(" " + f + ",");
		return buf.substring(0, buf.length()-1) + "]";
	}

	public static void assertEquals(float[] left, float... right) {
		assertEquals(left, right, 0.000001f, 0.000001f);
	}

	public static void assertEquals(double left, double right) {
		assertEquals(left, right, 0.000001f, 0.000001f);
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

	/**
	 * Creates a temporary file with prefix in name.
	 * Will be deleted on JVM exit unless jsignalml.test.keep
	 * is set.
	 */
	public static File temporaryFile(String prefix)
		throws java.io.IOException
	{
		File file = File.createTempFile(prefix, null);
		if (!keep_tmp_files)
			file.deleteOnExit();
		return file;
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
