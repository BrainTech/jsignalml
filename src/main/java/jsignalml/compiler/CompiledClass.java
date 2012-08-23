package jsignalml.compiler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;

import jsignalml.logging.Logger;

public class CompiledClass {
	public static final Logger log = new Logger(CompiledClass.class);

	/**
	 * Show this many diagnostic lines in the exception trace.
	 */
	public static int exception_error_limit = 6;

	public final String fullName;
	public final CharSequence src;

	private Class<?> klass = null;
	protected DiagnosticCollector<JavaFileObject> diagnostics =
		new DiagnosticCollector<JavaFileObject>();

	public CompiledClass(String fullName, CharSequence src) {
		log.info("compiled class %s: %d chars", fullName, src.length());
		this.fullName = fullName;
		this.src = src;
	}

	/**
	 * Get compilation diagnostics. Only makes sense after compilation,
	 * i.e. after theClass() method has been called.
	 */
	public List<Diagnostic<? extends JavaFileObject>> getDiagnostics()
	{
		return this.diagnostics.getDiagnostics();
	}

	synchronized Class<?> theClass()
		throws ClassNotFoundException
	{
		if (this.klass != null)
			return this.klass;

		// We get an instance of JavaCompiler. Then
		// we create a file manager
		// (our custom implementation of it)
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		log.debug("retrieved system compiler: %s", compiler);
		JavaFileManager fileManager = new
			ClassFileManager(compiler
				 .getStandardFileManager(null, null, null));

		// Dynamic compiling requires specifying
		// a list of "files" to compile. In our case
		// this is a list containing one "file" which is in our case
		// our own implementation (see details below)
		List<JavaFileObject> jfiles =
			StringJavaFile.createList(this.fullName, this.src);

		// We specify a task to the compiler. Compiler should use our file
		// manager and our list of "files".
		// Then we run the compilation with call()
		log.debug("executing compilation for class %s", this.fullName);
		JavaCompiler.CompilationTask task =
			compiler.getTask(null, fileManager, this.diagnostics,
					 null, null, jfiles);
		if (!task.call())
			throw compilationFailure();

		// Creating an instance of our compiled class and
		// running its toString() method
		this.klass = fileManager.getClassLoader(null)
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
			if (i++ < exception_error_limit)
				reason.append("\n" + pos + dia.getMessage(null));
			log.warn(pos + dia.getMessage(null));
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

	public Object newInstance(Object... parameters)
		throws ClassNotFoundException,
		       NoSuchMethodException,
		       InstantiationException,
		       IllegalAccessException,
		       IllegalArgumentException,
		       InvocationTargetException
	{
		Constructor cons = this.getConstructor(parameters);
		return cons.newInstance(parameters);
	}
}
