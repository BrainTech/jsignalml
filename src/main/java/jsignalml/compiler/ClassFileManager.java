package jsignalml.compiler;

import javax.tools.JavaFileManager;
import javax.tools.StandardJavaFileManager;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.security.SecureClassLoader;

import jsignalml.logging.Logger;

public class ClassFileManager
	extends ForwardingJavaFileManager<StandardJavaFileManager> {
	public static final Logger log = new Logger(ClassFileManager.class);

	/**
	 * Instance of JavaClassObject that will store the compiled bytecode
	 * of our class
	 */
	private JavaClassObject jclassObject;

	/**
	 * Will initialize the manager with the specified standard java file manager
	 *
	 * @param standardManger
	 */
	public ClassFileManager(StandardJavaFileManager standardManager) {
		super(standardManager);
	}

	/**
	 * A loader extending SecureClassLoader which uses the byte code
	 * created by the compiler and stored in the JavaClassObject, and
	 * returns the Class for it.
	 */
	class StringClassLoader extends SecureClassLoader {
		@Override
		protected Class<?> findClass(String name)
			throws ClassNotFoundException
		{
			log.debug("loading class: %s", name);
			assert jclassObject != null;

			byte[] b = jclassObject.getBytes();
			return super.defineClass(name,
						 jclassObject.getBytes(),
						 0, b.length);
		}
	}

	@Override
	public ClassLoader getClassLoader(Location location) {
		log.info("providing class loader for %s", location);
		return new StringClassLoader();
	}

	/**
	 * Gives the compiler an instance of the JavaClassObject
	 * so that the compiler can write the byte code into it.
	 */
	@Override
	public JavaFileObject getJavaFileForOutput(Location location,
		String className, JavaFileObject.Kind kind, FileObject sibling)
		throws IOException
	{
		jclassObject = new JavaClassObject(className, kind);
		return jclassObject;
	}
}
