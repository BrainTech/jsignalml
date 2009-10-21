package jsignalml;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class util {
    public static <T> LinkedList<T> newLinkedList(){
	return new LinkedList<T>();
    }

    public static <K,V> HashMap<K,V> newHashMap(){
	return new HashMap<K,V>();
    }

    public static <K,V> TreeMap<K,V> newTreeMap(){
	return new TreeMap<K,V>();
    }

    public static <T> TreeSet<T> newTreeSet(){
	return new TreeSet<T>();
    }

    public static <T> T cast(Object object){
	return (T) object;
    }
}
