package org.signalml.jsignalml;

public interface CodecyThing {
    public static class CodecError extends Exception {
	public static class KeyError extends CodecError {
	    final public String key;
	    public KeyError(String key){
		this.key = key;
	    }
	}
    }

    public Machine.Param getParam(String id) throws CodecError.KeyError;
}
