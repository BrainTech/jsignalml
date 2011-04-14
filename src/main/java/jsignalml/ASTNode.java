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
	final Expression id;
	final List<ASTNode> children;

	public <T> T accept(ASTVisitor<T> v, T data) {
		log.info("%s accept(%s, %s)", this, v, data);
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

	protected ASTNode(ASTNode parent, Expression id) {
		this.parent = parent;
		this.id = id;
		this.children = util.newLinkedList();
		if (parent != null)
			parent.children.add(this);
	}

	protected ASTNode(ASTNode parent, String id) {
		this(parent, Expression.Const.make(id));
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

		@Override
		public String toString()
		{
			return format("ASTNode.Signalml %s", this.id);
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

	public static class ChannelSet extends ASTNode {
		public ChannelSet(ASTNode parent, Expression id)
		{
			super(parent, id);
		}

		@Override
		public String toString()
		{
			return format("ASTNode.ChannelSet %s", id);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}

	}

	public static class Channel extends ASTNode {
		public Channel(ASTNode parent, Expression id)
		{
			super(parent, id);
		}

		@Override
		public String toString()
		{
			return format("ASTNode.Channel %s", id);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}

	}

	public abstract static class Param extends ASTNode {
		public final Type type;
		public final List<Positional> args = util.newLinkedList();

		public Param(ASTNode parent, Expression id, Type type)
		{
			super(parent, id);
			if (parent==null)
				throw new SyntaxError("<param> must have a parent");
			this.type = type;
		}
	}

	public abstract static class ReadParam extends Param {
		final FileHandle<? extends FileType> handle;

		public ReadParam(ASTNode parent, Expression id, Type type)
		{
			super(parent, id, type);

			this.handle = parent.lookupFile();
			if (this.handle == null)
				throw new SyntaxError("<param> must live inside <file>");
		}
	}

	public static class BinaryParam extends ReadParam {
		final Expression format, offset;

		public BinaryParam(ASTNode parent, Expression id, Type type,
		                   Expression format, Expression offset)
			throws SyntaxError
		{
			super(parent, id, type);
			this.format = format;
			this.offset = offset;

			// TODO: test file type
		}

		@Override
		public String toString()
		{
			return format("ASTNode.BinaryParam %s on %s format=%s offset=%s",
			              id, handle, format, offset);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class ExprParam extends Param {
		final Expression expr;

		public ExprParam(ASTNode parent, Expression id, Type type, Expression expr)
		{
			super(parent, id, type);
			this.expr = expr;
		}

		@Override
		public String toString()
		{
			return format("ExprParam %s expr=%s", id, expr);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class BuiltinFunction extends Param {
		final TypeObject function;
		public BuiltinFunction(ASTNode parent, Expression id, Type type,
				       TypeObject function) {
			super(parent, id, type);
			this.function = function;
			log.info("created BuiltinFunction %s.%s -> %s",
				 parent.id, this.id, type.getClass().getSimpleName());
		}

		public BuiltinFunction(ASTNode parent, String id, Type type,
				       TypeObject function) {
			this(parent, Expression.Const.make(id), type, function);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}

		Positional arg(String name, Type type)
		{
			return new ASTNode.Positional(this, "n", new TypeInt());
		}

		@Override
		public String toString()
		{
			return format("ASTNode.BuiltinFunction builtin.%s", id);
		}
	}

	public static class Assert extends ASTNode {
		final Expression expr;

		public Assert(ASTNode parent, Expression id, Expression expr)
		{
			super(parent, id);
			if (parent==null)
				throw new SyntaxError("<assert> must have a parent");
			if (expr==null)
				throw new SyntaxError("<assert> needs an <expr> child");
			this.expr = expr;
		}

		@Override
		public String toString()
		{
			return format("ASTNode.Assert %s expr=%s", id, expr);
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

		public FileHandle(ASTNode parent, Expression id, Expression filename) {
			super(parent, id);
			if (parent==null)
				throw new SyntaxError("<file> must have a parent");
			this.filename = filename;
		}

		public static <V extends FileType>
		FileHandle<V> make(ASTNode parent, Expression filename) {
			return new FileHandle<V>(parent, null, filename);
		}

		public static FileHandle make(ASTNode parent, Expression id,
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

		@Override
		public String toString()
		{
			return format("ASTNode.FileHandle %s filename=%s", id, filename);
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
		public final Expression mapping, format;

		public DataHandle(ASTNode parent, Expression id,
				  Expression mapping, Expression format)
		{
			super(parent, id);
			if (parent==null)
				throw new SyntaxError("<data> must have a parent");
			this.mapping = mapping;
			this.format = format;

			final FileHandle<? extends FileType> handle = parent.lookupFile();
			if (handle == null)
				throw new SyntaxError("<data> must live inside <file>");
			handle.addData(this);
		}

		@Override
		public String toString()
		{
			return format("ASTNode.DataHandle id mapping=%s format=%s",
				      id, mapping, format);
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
			if (parent==null)
				throw new SyntaxError("<arg> must have a parent");
			if (!(parent instanceof ASTNode.Param))
				throw new SyntaxError("<arg> must live inside <param>");

			this.type = type;
			((Param)parent).args.add(this);
		}

		public static Positional make(Param parent, String name, String type) {
			return new Positional(parent, name, Type.getType(type));
		}

		@Override
		public String toString()
		{
			return format("ASTNode.Positional %s:%s",
				      id, type.getClass().getSimpleName());
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class Itername extends ASTNode {
		public final Type type;

		public Itername(ASTNode parent, String id, Type type) {
			super(parent, id);
			assert parent instanceof ASTNode.ForLoop;

			this.type = type;
		}

		@Override
		public String toString()
		{
			return format("ASTNode.Itername %s:%s",
				      id, Type.typename(type));
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class ForLoop extends ASTNode {
		public final Expression sequence;
		public final Itername itername;

		public ForLoop(ASTNode parent, Expression id, String itername,
			       Expression sequence) {
			super(parent, id);
			if (parent==null)
				throw new SyntaxError("<for-each> must have a parent");
			this.sequence = sequence;
			if (sequence==null)
				throw new SyntaxError("<for-each> must have sequence attribute'");
			this.itername = new Itername(this, itername, null);
		}

		@Override
		public String toString()
		{
			return format("ASTNode.ForLoop seqeunce=%s", id, sequence);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class Conditional extends ASTNode {
		public final Expression condition;
		public ElseBranch elsebranch = null;

		public Conditional(ASTNode parent, Expression id, Expression condition) {
			super(parent, id);
			if (parent==null)
				throw new SyntaxError("<if> must have a parent");
			if (condition==null)
				throw new SyntaxError("<if> must have test attribute'");
			this.condition = condition;
		}

		@Override
		public String toString()
		{
			return format("ASTNode.Conditional test=%s", id, condition);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}

	public static class ElseBranch extends ASTNode {
		public ElseBranch(ASTNode parent, Expression id) {
			super(parent, id);
			if (parent==null)
				throw new SyntaxError("<else> must have a parent");
			if (!(parent instanceof Conditional))
				throw new SyntaxError("<else> can only be used with <if>");
			Conditional ifparent = (Conditional) parent;
			if (ifparent.elsebranch != null)
				throw new SyntaxError("cannot have more than one <else>");
			ifparent.elsebranch = this;
		}

		@Override
		public String toString()
		{
			return format("ASTNode.ElseBranch %s", id);
		}

		@Override
		public <T> T _accept(ASTVisitor<T> v, T data)
		{
			return v.visit(this, data);
		}
	}
}
