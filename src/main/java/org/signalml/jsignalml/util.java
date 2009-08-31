package org.signalml.jsignalml;

import java.util.LinkedList;
import java.util.HashMap;

public class util {
    public static <T> LinkedList<T> newLinkedList(){
	return new LinkedList<T>();
    }

    public static <K,V> HashMap<K,V> newHashMap(){
	return new HashMap<K,V>();
    }
}
