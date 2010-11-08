package jsignalml;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

class DOMIterator <T extends Node> implements Iterator<T> {
	final NodeList source;
	int index;

	DOMIterator(NodeList source) {
		this.source = source;
		this.index = 0;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public boolean hasNext() {
		return this.index < this.source.getLength();
	}

	public T next() {
		Node item = this.source.item(this.index);
		if (item == null)
			throw new NoSuchElementException();
		this.index ++;
		return (T) item;
	}

	public static <V extends Node>
	Iterator<V> newIterator(NodeList source) {
		return new DOMIterator<V>(source);
	}
}

public class DOMIterable<T extends Node> implements Iterable<T> {
	final NodeList source;

	public DOMIterable(NodeList source) {
		this.source = source;
	}

	public Iterator<T> iterator() {
		return DOMIterator.newIterator(this.source);
	}
}
