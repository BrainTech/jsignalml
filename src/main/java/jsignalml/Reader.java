package jsignalml;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Reader extends Frame implements CallHelper {
    public static final Logger log = new Logger(Reader.class);

    final CodecyThing codec;

    /* HashMap because TreeMap would require comparable. */
    final Map<FileHandle<FileType>, FileType> files =
	new HashMap<FileHandle<FileType>, FileType>();

    final LinkedList<File> filehints = new LinkedList<File>();

    public Reader(CodecyThing codec, String...filenames){
	super(null);
	this.codec = codec;
	for(String filename: filenames)
	    this.filehints.add(new File(filename));
    }

    @Override
    public <T extends FileType> T getFile(FileHandle<T> handle)
    {
	log.info("request for %s", handle);
	T file = (T) this.files.get(handle);
	if(file != null)
	    return file;

	log.info("attempting to open %s", handle);
	file = handle.open(this, this.filehints.poll());
	this.files.put((FileHandle<FileType>) handle, (FileType) file);
			//XXX WTF?
	return file;
    }

    @Override
    public Type frame_call(String id, Type...args)
    {
	Machine.Param p = codec.getParam(id);
	Type v = p.eval(this, args);
	return v;
    }
}
