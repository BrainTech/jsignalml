package jsignalml;

import java.util.List;

public interface CallHelper {
	public Type lookup(String name);
	public void assign(String id, Type value);
}
