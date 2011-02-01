package jsignalml;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableList;

/**
 * Class to hold an AST correspoding to the XML file
 */
public abstract class ASTNode {
	public static final Logger log = new Logger(ASTNode.class);

	final ASTNode parent;
	final String id;
	final List<ASTNode> children;

	public <T> T accept(ASTVisitor<T> v, T data) {
		log.info("%s.accept(%s, %s)", this, v, data);
		T newdata = this._accept(v, data);
		for(ASTNode child: this.children)
			child.accept(v, newdata);
		return newdata;
	}

	/**
	 * This function must exist in all non-abstract children (even if
	 * identical), to allow overloaded v.visit method resolution based on
	 * this.
	 */
	public abstract <T> T _accept(ASTVisitor<T> v, T data);

	protected ASTNode(ASTNode parent, String id) {
		this.parent = parent;
		this.id = id;
		this.children = util.newLinkedList();
		if (parent != null)
			parent.children.add(this);
	}

	public ASTNode find(String id) {
		log.debug("[%s] looking for %s", this.id, id);
		ASTNode ans = this.lookup(id);
		if (ans != null)
			log.info("[%s] found %s here", this.id, id);
		if (ans == null && this.parent != null)
			ans = this.parent.find(id);
		if (ans == null)
			throw new ExpressionFault.NameError(id);
		return ans;
	}

	/** Look for child with id=id.
	 */
	public ASTNode lookup(String id) {
		for(ASTNode child: this.children)
			if (child.id.equals(id))
				return child;
		return null;
	}

	public static class Signalml extends ASTNode {
		public Signalml(String name) {
			super(null, name);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}

		@Override
		public ASTNode lookup(String id) {
			ASTNode node = super.lookup(id);
			if (node == null)
				node = Builtins.instance().lookup(id);
			return node;
		}
	}

	public abstract static class Param extends ASTNode {
		public final Type type;
		public final List<Positional> args;

		public Param(ASTNode parent, String id, Type type, Positional args[]) {
			super(parent, id);
			this.type = type;
			if(args == null)
				this.args = util.newLinkedList();
			else
				this.args = unmodifiableList(Arrays.asList(args));
		}
	}

	public abstract static class ReadParam extends Param {
		public ReadParam(ASTNode parent, String id, Type type,
		                 Positional args[])
		{
			super(parent, id, type, args);
		}
	}

	public static class BinaryParam extends ReadParam {
		final FileHandle<? extends FileType.BinaryFile> handle;
		final Expression format, offset;

		public BinaryParam(ASTNode parent, String id, Type type,
		                   Expression format, Expression offset)
		{
			super(parent, id, type, new Positional[0]);
			// XXX: this.handle = parent.currentFile() ?
			this.handle = null;
			this.format = format;
			this.offset = offset;
		}

		public String toString()
		{
			return format("BinaryParam on %s format: %s offset: %s",
			              this.handle, this.format, this.offset);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class ExprParam extends Param {
		final Expression expr;

		public ExprParam(ASTNode parent, String id, Type type,
		                 Positional args[], Expression expr)
		{
			super(parent, id, type, args);
			this.expr = expr;
		}

		public String toString()
		{
			return format("ExprParam expression: %s", this.expr);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class Assert extends ASTNode {
		final Expression expr;

		public Assert(ASTNode parent, String id, Expression expr)
		{
			super(parent, id);
			this.expr = expr;
		}

		public String toString()
		{
			return format("Assert expression: %s", this.expr);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class BuiltinFunction extends Param {
		public BuiltinFunction(String id, Type type,
				       Positional...args) {
			super(null, id, type, args);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
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

		public FileHandle(ASTNode parent, String id, Expression filename) {
			super(parent, id);
			this.filename = filename;
		}

		public static <V extends FileType>
		FileHandle<V> make(ASTNode parent, Expression filename) {
			return new FileHandle<V>(parent, null, filename);
		}

		public static FileHandle make(ASTNode parent, Expression filename, String type)
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

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class DataHandle extends ASTNode {
		public final String mapping, format;

		public DataHandle(ASTNode parent, String id, String mapping, String format)
		{
			super(parent, id);
			this.mapping = mapping;
			this.format = format;

			// TODO: handle.addData(this);
		}

		public String toString()
		{
			return format("DataHandle: mapping=%s format=%s", mapping, format);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class Positional extends ASTNode {
		public final Type type;

		public Positional(ASTNode parent, String id, Type type) {
			super(parent, id);
			this.type = type;
		}

		public static Positional make(ASTNode parent, String name, String type) {
			return new Positional(parent, name, Type.getType(type));
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}
}
