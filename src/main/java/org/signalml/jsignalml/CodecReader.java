package org.signalml.jsignalml;

import java.io.*;
import java.util.Map;
import org.apache.log4j.BasicConfigurator;

import org.signalml.jsignalml.Machine.Param;

public class CodecReader
{
    public static void main(String...args)
	throws Exception
    {
	BasicConfigurator.configure();

	InputStream stream = new FileInputStream(args[0]);
	XMLDocument doc = new XMLDocument(stream);
	CodecCore core = new CodecCore();
	core.parse_signalml(doc);

	for(Map.Entry<String,Param> entry: core.params.entrySet()){
	    String id = entry.getKey();
	    Param p = entry.getValue();
	    System.out.format("param %s => %s\n", id, p);
	}
    }
}
