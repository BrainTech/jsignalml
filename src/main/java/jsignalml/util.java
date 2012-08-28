package jsignalml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.beust.jcommander.IStringConverter;

public abstract class util {
	public static <T> LinkedList<T> newLinkedList() {
		return new LinkedList<T>();
	}

	public static <T> LinkedList<T> newLinkedList(Collection<? extends T> c) {
		return new LinkedList<T>(c);
	}

	public static <T> LinkedList<T> newLinkedList(Iterator<? extends T> it) {
		LinkedList<T> list = new LinkedList<T>();
		while(it.hasNext())
			list.add(it.next());
		return list;
	}

	public static <T> ArrayList<T> newArrayList() {
		return new ArrayList<T>();
	}

	public static <T> ArrayList<T> newArrayList(int size) {
		return new ArrayList<T>(size);
	}

	public static <K,V> HashMap<K,V> newHashMap() {
		return new HashMap<K,V>();
	}

	public static <K,V> LinkedHashMap<K,V> newLinkedHashMap() {
		return new LinkedHashMap<K,V>();
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

	public static String basename_noext(File file) {
		String basename = StringUtils.split(file.getName(), ".")[0];
		return basename.replace("[^a-zA-Z0-9_]", "_");
		// TODO: tests needed
	}

	public class FileConverter implements IStringConverter<File> {
		@Override
		public File convert(String value) {
			return new File(value);
		}
	}
}
