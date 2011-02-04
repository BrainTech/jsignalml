package jsignalml;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.LinkedList;
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

		// use a copy of the children list in case it changes
		for(ASTNode child: new LinkedList<ASTNode>(this.children))
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
			// child.id might be null
			if (id.equals(child.id))
				return child;
		return null;
	}

	public FileHandle<? extends FileType> lookupFile() {
		if(parent != null)
			return parent.lookupFile();
		return null;
	}

	public static class Signalml extends ASTNode {
		public Signalml(String name)
		{
			super(new Builtins(), name);
		}

		/**
		 * Contrary to normal behaviour, parent is visited here to give
		 * it a chance to perform its duties.
		 */
		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public abstract static class Param extends ASTNode {
		public final Type type;
		public final List<Positional> args;

		public Param(ASTNode parent, String id, Type type)
		{
			super(parent, id);
			this.type = type;
			this.args = util.newLinkedList();
		}
	}

	public abstract static class ReadParam extends Param {
		public ReadParam(ASTNode parent, String id, Type type)
		{
			super(parent, id, type);
		}
	}

	public static class BinaryParam extends ReadParam {
		final FileHandle<? extends FileType.BinaryFile> handle;
		final Expression format, offset;

		public BinaryParam(ASTNode parent, String id, Type type,
		                   Expression format, Expression offset)
			throws SyntaxError
		{
			super(parent, id, type);
			this.format = format;
			this.offset = offset;

			// handle is checked to be not null in CodecParser,
			// here it can be null for testing purposes
			if (this.parent != null)
				this.handle = (FileHandle<? extends FileType.BinaryFile>)parent.lookupFile();
			else
				this.handle = null;
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

		public ExprParam(ASTNode parent, String id, Type type, Expression expr)
		{
			super(parent, id, type);
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

	public static class BuiltinFunction extends Param {
		public BuiltinFunction(ASTNode parent, String id, Type type) {
			super(parent, id, type);
			log.info("created BuiltinFunction %s.%s -> %s",
				 parent.id, this.id, type.getClass().getSimpleName());
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}

		Positional arg(String name, Type type)
		{
			return new ASTNode.Positional(this, "n", new Type.Int());
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

		public static FileHandle make(ASTNode parent, String id,
					      Expression filename, String type)
		{
			if (type.equals("binary"))
				return new FileHandle<FileType.BinaryFile>(parent, id, filename);

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

		@Override
		public FileHandle<? extends FileType> lookupFile() {
			return this;
		}
	}

	public static class DataHandle extends ASTNode {
		public final String mapping, format;

		public DataHandle(ASTNode parent, String id, String mapping, String format)
		{
			super(parent, id);
			this.mapping = mapping;
			this.format = format;

			if (parent != null) {
				final FileHandle<? extends FileType> handle
					= parent.lookupFile();
				if( handle != null )
					handle.addData(this);
			}
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
			assert parent != null;
			assert parent instanceof ASTNode.Param;

			this.type = type;
			if( parent != null )
				((Param)parent).args.add(this);
		}

		public static Positional make(Param parent, String name, String type) {
			return new Positional(parent, name, Type.getType(type));
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class ForLoop extends ASTNode {
		public final Expression expr;
		public String itername;

		public ForLoop(ASTNode parent, String id, String itername, Expression expr) {
			super(parent, id);
			this.expr = expr;
			this.itername = itername;
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

}
