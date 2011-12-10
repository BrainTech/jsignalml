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

import jsignalml.logging.Logger;

/**
 * Class to hold an AST correspoding to the XML file
 */
public abstract class ASTNode {
	public static final Logger log = new Logger(ASTNode.class);

	final ASTNode parent;
	final Expression id;
	final String static_id;
	final List<ASTNode> children;

	public <T> T accept(ASTVisitor<T> v, T data) {
		log.debug("%s on %s, %s", v, this, data);
		T newdata = this._accept(v, data);

		// use a copy of the children list in case it changes
		for(ASTNode child: new LinkedList<ASTNode>(this.children)) {
			log.trace("recursing into child: %s >> %s", this, child);
			child.accept(v, newdata);
		}
		log.trace("recursion done: %s -> %s", this, newdata);
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

		this.static_id = _resolve_id(id);
	}

	private static String _resolve_id(Expression id) {
		if (!(id instanceof Expression.Const))
			return null;
		final Type ans = ((Expression.Const)id).value;
		if(!(ans instanceof TypeString))
			throw new ExpressionFault.TypeError();
		return ((TypeString)ans).value;
	}

	protected void _debug(String msg, Object...args) {
		String fmt = "[" + this.getClass().getSimpleName() + " " +
			(this.static_id != null ? this.static_id : this.id) + "] " + msg;
		log.debug(fmt, args);
	}

	protected ASTNode(ASTNode parent, String id) {
		this(parent, Expression.Const.make(id));
	}

	public ASTNode find(String id) {
		_debug("looking for %s", id);
		ASTNode ans = this.lookup(id);
		if (ans != null)
			_debug("found %s here", id);
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
			if (id.equals(child.static_id))
				return child;
		return null;
	}

	boolean isAFunction() {
		return false;
	}

	public FileHandle<? extends FileType> lookupFile() {
		if(parent != null)
			return parent.lookupFile();
		return null;
	}

	public static class Signalml extends ASTNode {
		public Signalml(String name)
		{
			super(Builtins.instance(), name);
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
		public final Expression mapping, format, length;

		public Channel(ASTNode parent, Expression id, 
			       Expression mapping, Expression format, Expression length)
		{
			super(parent, id);

			this.mapping = mapping;
			this.format = format;
			this.length = length;

			if (mapping == null)
				throw new SyntaxError("<channel> must have mapping attribute");
			if (format == null)
				throw new SyntaxError("<channel> must have format attribute");
			if (length == null)
				throw new SyntaxError("<channel> must have length attribute");

			final FileHandle<? extends FileType> handle = parent.lookupFile();
			if (handle == null)
				throw new SyntaxError("<channel> must live inside <file>");

			boolean found_channelset = false;
			for(ASTNode node = parent; node!=null; node=node.parent)
				if(node instanceof ChannelSet)
					found_channelset = true;
			if(!found_channelset)
				throw new SyntaxError("<channel> must live inside <file>");
		}

		@Override
		public String toString()
		{
			return format("ASTNode.Channel %s mapping=%s format=%s", id,
				      mapping, format);
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

		@Override
		boolean isAFunction() {
			return !this.args.isEmpty();
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
		public ExprParam(ASTNode parent, String id, Type type, Expression expr)
		{
			this(parent, Expression.Const.make(id), type, expr);
		}

		@Override
		public String toString()
		{
			return format("ExprParam %s expr=(%s)", id, expr);
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
			assert this.static_id != null;

			log.info("created BuiltinFunction %s.%s -> %s",
				 parent.id, this.static_id, Type.typename(type));
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
			return format("ASTNode.BuiltinFunction builtin.%s", static_id);
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
			return format("ASTNode.Assert %s expr=(%s)", id, expr);
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

		public FileHandle(ASTNode parent, Expression id, Expression filename) {
			super(parent, id);
			if (parent==null)
				throw new SyntaxError("<file> must have a parent");
			this.filename = filename;
		}
		public FileHandle(ASTNode parent, String id, Expression filename)
		{
			this(parent, Expression.Const.make(id), filename);
		}

		public static <V extends FileType>
		FileHandle<V> make(ASTNode parent, Expression filename) {
			return new FileHandle<V>(parent, (Expression)null, filename);
		}

		public static FileHandle make(ASTNode parent, Expression id,
					      Expression filename, String type)
		{
			if (type.equals("binary"))
				return new FileHandle<FileType.BinaryFile>(parent, id, filename);

			throw new IllegalArgumentException(format("unkown file type '%s'", type));
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

	public static class Positional extends ASTNode {
		public final Type type;

		public Positional(ASTNode parent, String id, Type type) {
			super(parent, id);
			if (parent==null)
				throw new SyntaxError("<arg> must have a parent");
			if (!(parent instanceof ASTNode.Param))
				throw new SyntaxError("<arg> must live inside <param>");
			if (this.static_id == null)
				throw new SyntaxError("<arg> must have a static string id");

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
				      static_id, Type.typename(type));
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
			return format("ASTNode.ForLoop seqeunce=(%s)", id, sequence);
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
			return format("ASTNode.Conditional %s test=(%s)", id, condition);
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
