package jsignalml.compiler;

import java.net.URI;
import java.util.List;
import javax.tools.SimpleJavaFileObject;
import javax.tools.JavaFileObject;

import jsignalml.util;

public class StringJavaFile extends SimpleJavaFileObject {
	public static URI stringURI(String className, Kind kind) {
		String uri = "string:///" +
			className.replace('.', '/') + kind.extension;
		return URI.create(uri);
	}

	/**
	 * CharSequence representing the source code to be compiled
	 */
	private final CharSequence content;

	/**
	 * This constructor will store the source code in the
	 * internal "content" variable and register it as a
	 * source code, using a URI containing the class full name
	 *
	 * @param className
	 *            name of the public class in the source code
	 * @param content
	 *            source code to compile
	 */
	public StringJavaFile(String className, CharSequence content) {
		super(stringURI(className, Kind.SOURCE), Kind.SOURCE);
		this.content = content;
	}

	/**
	 * Return the CharSequence to be compiled.
	 */
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return this.content;
	}

	public static List<JavaFileObject> createList(String name, CharSequence src) {
		List<JavaFileObject> jfiles = util.newArrayList();
		StringJavaFile f = new StringJavaFile(name, src);
		jfiles.add(f);
		return jfiles;
	}
}
