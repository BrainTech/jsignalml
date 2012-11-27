package jsignalml.compiler;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.LinkedList;
import java.util.jar.JarFile;

import javax.tools.JavaFileManager;
import javax.tools.StandardJavaFileManager;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;

import org.apache.commons.lang.StringUtils;

import jsignalml.logging.Logger;
import jsignalml.util;

public class ClassFileManager
	extends ForwardingJavaFileManager<StandardJavaFileManager> {
	public static final Logger log = new Logger(ClassFileManager.class);

	/**
	 * Instance of JavaClassObject that will store the compiled bytecode
	 * of our class
	 */
	protected Map<String, JavaClassObject> objects = util.newHashMap();

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
			JavaClassObject object = objects.get(name);
			if (object == null)
				throw new ClassNotFoundException(name);

			byte[] b = object.getBytes();
			return super.defineClass(name, b, 0, b.length);
		}
	}

	@Override
	public ClassLoader getClassLoader(Location location) {
		log.info("providing class loader for location %s", location);
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
		log.trace("storing %s %s in location %s",
			  kind, className, location);
		JavaClassObject object = new JavaClassObject(className, kind);
		this.objects.put(className, object);
		return object;
	}

	public static class ClassPathWrapper {
		LinkedList<String> items = util.newLinkedList();

		public ClassPathWrapper(String classpath) {
			String[] items = StringUtils.split(classpath, ":");
			for(String item: items)
				this.addItem(item);
		}

		public ClassPathWrapper() {
			this(System.getProperty("java.class.path"));
		}

		public void addItem(String item) {
			if (this.items.contains(item))
				return;

			this.items.add(item);
			final JarFile jf;
			try {
				jf = new JarFile(item);
			} catch(IOException e) {
				log.info("failed to load jar %s, not analyzing for classpaths", item);
				return;
			}

			final String extra;
			try {
				extra = jf.getManifest().getMainAttributes().getValue("Class-Path");
			} catch(IOException e) {
				log.info("failed to load manifest from jar %s, not analyzing for classpaths", item);
				return;
			}

			String[] items = StringUtils.split(extra, " ");
			if (items == null)
				return;
			for (String item2: items)
				this.addItem(item2);
		}

		public String classpath() {
			return StringUtils.join(this.items, ":");
		}
	}
}
