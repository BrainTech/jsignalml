package jsignalml;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.File;

import org.apache.commons.lang.StringUtils;

public class util {
	public static <T> LinkedList<T> newLinkedList() {
		return new LinkedList<T>();
	}

	public static <K,V> HashMap<K,V> newHashMap() {
		return new HashMap<K,V>();
	}

	public static <K,V> TreeMap<K,V> newTreeMap() {
		return new TreeMap<K,V>();
	}

	public static <T> TreeSet<T> newTreeSet() {
		return new TreeSet<T>();
	}

	public static <T> T cast(Object object) {
		return (T) object;
	}

	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE)
			//			throw new IllegalArgumentException
			throw new ExpressionFault.ValueError
				(l + " cannot be cast to int without changing its value.");
		return (int) l;
	}

	public static String basename_noext(File file) {
		String basename = StringUtils.split(file.getName(), ".")[0];
		return basename.replace("[^a-zA-Z0-9_]", "_");
		// TODO: tests needed
	}
}
