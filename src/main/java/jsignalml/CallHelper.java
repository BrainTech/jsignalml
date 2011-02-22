package jsignalml;

import java.util.List;

public interface CallHelper {
	public Type call(String id, List<Type> args);
	public void assign(String id, Type value);
}
