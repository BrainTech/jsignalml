package org.signalml.jsignalml;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

class NodeIterator implements Iterator<Node> {
    final NodeList source;
    int index;

    NodeIterator(NodeList source){
	this.source = source;
	this.index = 0;
    }

    public void remove(){
	throw new UnsupportedOperationException();
    }

    public boolean hasNext(){
	return this.index < this.source.getLength();
    }

    public Node next(){
	Node item = this.source.item(this.index);
	if(item == null)
	    throw new NoSuchElementException();
	this.index ++;
	return item;
    }
}

public class NodeIterable implements Iterable<Node> {
    final NodeList source;

    public NodeIterable(NodeList source){
	this.source = source;
    }

    public Iterator<Node> iterator(){
	return new NodeIterator(this.source);
    }
}
