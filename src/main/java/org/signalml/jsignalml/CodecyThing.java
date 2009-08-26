package org.signalml.jsignalml;

public interface CodecyThing {
    public Machine.Param getParam(String id)
	throws Machine.MachineError.ParamNotFound;
}
