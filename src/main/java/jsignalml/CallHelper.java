package jsignalml;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CallHelper {
    public interface FileHandle<T extends FileType>
    {
	public T open(CallHelper state, File filename);
    }

    public Type call(String id, Type...args);

    public <T extends FileType> T getFile(FileHandle<T> handle);

    public void assign(String id, Expression expr);

    public CallHelper localize(Map<String,Type> locals);
}
