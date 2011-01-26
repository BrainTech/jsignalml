package jsignalml;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import static java.lang.String.format;

/**
 * Class to hold an AST correspoding to the XML file
 */
public abstract class ASTNode {
	public static final Logger log = new Logger(Machine.class);

	final Context context;
	final String id;
	final List<ASTNode> children;

	public void accept(ASTVisitor v) {
		this._accept(v);
		for(ASTNode child: this.children)
			child.accept(v);
	}

	public abstract void _accept(ASTVisitor v);

	private ASTNode(Context context, String id) {
		this.context = context;
		this.id = id;
		this.children = util.newLinkedList();
	}


	public static class Signalml extends ASTNode {
		public Signalml(String name) {
			super(new Context.ClassContext(new Builtins(), name), name);
		}
		
		public void _accept(ASTVisitor v)
		{
			v.visit(this);
		}
	}

	public abstract static class Param extends ASTNode {
		public final Type type;
		public final Positional[] args;

		public Param(Context parent, String id, Type type, Positional args[]) {
			super(new Context.ClassContext(parent, id), id);
			this.type = type;
			this.args = args;
		}
	}

	public abstract static class ReadParam extends Param {
		public ReadParam(Context context, String id, Type type,
		                 Positional args[])
		{
			super(context, id, type, args);
		}
	}

	public static class BinaryParam extends ReadParam {
		final FileHandle<? extends FileType.BinaryFile> handle;
		final Expression format, offset;

		public BinaryParam(Context context, String id, Type type,
		                   Positional args[],
		                   FileHandle<? extends FileType.BinaryFile> handle,
		                   Expression format, Expression offset)
		{
			super(context, id, type, args);
			this.handle = handle;
			this.format = format;
			this.offset = offset;
		}

		public String toString()
		{
			return format("BinaryParam on %s format: %s offset: %s",
			              this.handle, this.format, this.offset);
		}

		public void _accept(ASTVisitor v)
		{
			v.visit(this);
		}
	}

	public static class ExprParam extends Param {
		final Expression expr;

		public ExprParam(Context context, String id, Type type,
		                 Positional args[],
		                 Expression expr)
		{
			super(context, id, type, args);
			this.expr = expr;
		}

		public String toString()
		{
			return format("ExprParam expression: %s", this.expr);
		}

		public void _accept(ASTVisitor v)
		{
			v.visit(this);
		}
	}

	/**
	 * A holder for information about a file yet to be opened.
	 * Two filenames can be provided: at construction time, and
	 * as an argument to open(). At least one is needed, and
	 * the second one has priority. If both are null, a
	 * ExpressionFault.ValueError is thrown.
	 */
	public static class FileHandle<T extends FileType> extends ASTNode
	{
		public final Expression filename; // may be null
		public final List<DataHandle> datas = util.newLinkedList();

		public FileHandle(Context parent, String id, Expression filename) {
			super(new Context.ClassContext(parent, id), id);
			this.filename = filename;
		}

		public static <V extends FileType>
		FileHandle<V> make(Context parent, Expression filename) {
			return new FileHandle<V>(parent, null, filename);
		}

		public static FileHandle make(Context parent, Expression filename, String type)
		{
			if (type.equals("binary"))
				return new FileHandle<FileType.BinaryFile>(parent, null, filename);

			throw new IllegalArgumentException(format("unkown file type '%s'", type));
		}

		void addData(DataHandle data)
		{
			this.datas.add(data);
		}

		public String toString()
		{
			return format("FileHandle: filename=%s", filename);
		}

		public void _accept(ASTVisitor v)
		{
			v.visit(this);
		}
	}

	public static class DataHandle extends ASTNode {
		public final String mapping, format;

		public DataHandle(Context context, String id, 
				  FileHandle<?> handle, String mapping, String format)
		{
			super(context, id);
			this.mapping = mapping;
			this.format = format;

			handle.addData(this);
		}

		public String toString()
		{
			return format("DataHandle: mapping=%s format=%s", mapping, format);
		}

		public void _accept(ASTVisitor v)
		{
			v.visit(this);
		}
	}

	public static class BuiltinFunction extends Param {
		public BuiltinFunction(String id, Type type,
				       Positional...args) {
			super(null, id, type, args);
		}

		public void _accept(ASTVisitor v)
		{
			v.visit(this);
		}
	}

	public static class Positional extends ASTNode {
		public final Type type;

		public Positional(Context context, String id, Type type) {
			super(context, id);
			this.type = type;
		}

		public static Positional make(Context context, String name, String type) {
			return new Positional(context, name, Type.getType(type));
		}

		public void _accept(ASTVisitor v)
		{
			v.visit(this);
		}
	}
}
