package org.signalml.jsignalml;

import java.io.*;
import org.apache.log4j.BasicConfigurator;

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
    }
}
