package jsignalml;

public class helpers {
	public static void assertInstanceOf(Object obj, Class<?> klass) {
		if (!klass.isInstance(obj))
			throw new AssertionError("got type " +
						 obj.getClass().getName() +
						 ", but expected " +
						 klass.getName());
	}
}
