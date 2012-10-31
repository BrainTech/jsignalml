package jsignalml.compiler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.io.File;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;

import org.apache.log4j.BasicConfigurator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import jsignalml.util;
import jsignalml.logging.Logger;

public class CompiledClass<T> {
	public static final Logger log = new Logger(CompiledClass.class);

	/**
	 * Show this many diagnostic lines in the exception trace.
	 */
	public static int exception_error_limit = 6;

	public final String fullName;
	public final CharSequence src;

	private Class<T> klass = null;
	protected DiagnosticCollector<JavaFileObject> diagnostics =
		new DiagnosticCollector<JavaFileObject>();

	public CompiledClass(String fullName, CharSequence src) {
		log.info("compiled class %s: %d chars", fullName, src.length());
		this.fullName = fullName;
		this.src = src;
	}


	/**
	 * Convenience wrapper to not to have to write the cast twice.
	 */
	public static <T> CompiledClass<T> newCompiledClass(String fullName,
							    CharSequence src)
	{
		return new CompiledClass<T>(fullName, src);
	}

	/**
	 * Get compilation diagnostics. Only makes sense after compilation,
	 * i.e. after theClass() method has been called.
	 */
	public List<Diagnostic<? extends JavaFileObject>> getDiagnostics()
	{
		return this.diagnostics.getDiagnostics();
	}

	public synchronized Class<T> theClass()
		throws ClassNotFoundException
	{
		if (this.klass != null)
			return this.klass;

		// We get an instance of JavaCompiler. Then
		// we create a file manager
		// (our custom implementation of it)
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		log.debug("retrieved system compiler: %s", compiler);
		JavaFileManager fileManager = new ClassFileManager(
		       compiler.getStandardFileManager(this.diagnostics, null, null));

		// Dynamic compiling requires specifying
		// a list of "files" to compile. In our case
		// this is a list containing one "file" which is in our case
		// our own implementation (see details below)
		List<JavaFileObject> jfiles =
			StringJavaFile.createList(this.fullName, this.src);

		String classpath = System.getProperty("java.class.path");
		List<String> optionList = util.newArrayList();
		// set compiler's classpath to be same as the runtime's
		optionList.add("-classpath");
		optionList.add(classpath);
		log.debug("added classpath %s", classpath);

		// We specify a task to the compiler. Compiler should use our file
		// manager and our list of "files".
		// Then we run the compilation with call()
		log.debug("executing compilation for class %s", this.fullName);
		JavaCompiler.CompilationTask task =
			compiler.getTask(null, fileManager, this.diagnostics,
					 optionList, null, jfiles);
		if (!task.call())
			throw compilationFailure();

		// Creating an instance of our compiled class and
		// running its toString() method
		this.klass = (Class<T>) fileManager.getClassLoader(null)
			.loadClass(fullName);

		return this.klass;
	}

	protected ClassNotFoundException compilationFailure() {
		StringBuilder reason = new StringBuilder(
			 "compilation failed for class " + this.fullName);
		int i = 0;
		for(Diagnostic<? extends JavaFileObject> dia:
			    this.getDiagnostics()) {
			final String pos;
			if(dia.getPosition() != Diagnostic.NOPOS)
				pos = String.format("line %d: ", dia.getLineNumber());
			else
				pos = "";
			String msg = dia.getMessage(null).replace("\n", "; ");
			if (i++ < exception_error_limit)
				reason.append("\n" + pos + msg);
			log.warn(pos + msg);
		}
		// reason.append("\n" + this.src);
		log.error("compilation failed for class %s", this.fullName);
		return new ClassNotFoundException(reason.toString());
	}

	public Constructor getConstructor(Object... parameters)
		throws ClassNotFoundException,
		       NoSuchMethodException
	{
		Class<?> types[] = new Class<?>[parameters.length];
		for(int i=0; i<parameters.length; i++)
			types[i] = parameters[i].getClass();
		return this.theClass().getConstructor(types);
	}

	public T newInstance(Object... parameters)
		throws ClassNotFoundException,
		       NoSuchMethodException,
		       InstantiationException,
		       IllegalAccessException,
		       IllegalArgumentException,
		       InvocationTargetException
	{
		Constructor cons = this.getConstructor(parameters);
		return (T) cons.newInstance(parameters);
	}

	public static <T> CompiledClass<T> fromFile(File path)
		throws java.io.IOException,
		       java.io.FileNotFoundException,
		       ClassNotFoundException,
		       NoSuchMethodException,
		       InstantiationException,
		       IllegalAccessException,
		       IllegalArgumentException,
		       InvocationTargetException
	{
		String class_name = FilenameUtils.getBaseName(path.toString());
		String text = FileUtils.readFileToString(path);
		CompiledClass<T> klass = new CompiledClass<T>(class_name, text);
		log.info("created CompiledClass from %s", path);
		return klass;
	}

	public static void main(String... args)
		throws java.io.IOException,
		       java.io.FileNotFoundException,
		       ClassNotFoundException,
		       NoSuchMethodException,
		       InstantiationException,
		       IllegalAccessException,
		       IllegalArgumentException,
		       InvocationTargetException
	{
		BasicConfigurator.configure();

		String path = args[0];
		Object instance = fromFile(new File(path)).newInstance();
	}
}
