package jsignalml;

import java.io.*;
import java.util.Map;
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

	for(Map.Entry<String,Machine.Param> entry: core.params.entrySet()){
	    String id = entry.getKey();
	    Machine.Param p = entry.getValue();
	    System.out.format("param %s => %s\n", id, p);
	}

	for(Machine.FileHandle<?> handle: core.file_handles){
	    System.out.format("file %s\n", handle);
	    for(Machine.DataHandle data: handle.datas)
		System.out.format("    data %s\n", data);
	}
    }
}
