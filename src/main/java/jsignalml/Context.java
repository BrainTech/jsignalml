package jsignalml;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

public abstract class Context {
	static final Logger log = new Logger(Context.class);

	/** Look for a name amongst immediate children. */
	public abstract ASTNode lookup(String id);

	/** Look for a name in this context or delegate to parent.
	 *
	 *  Throws ExpressionFault.NameError if the name cannot be resolved.
	 */
	public ASTNode find(String id)
	{
		ASTNode ans = this.lookup(id);
		if (ans == null)
			ans = this.parent.find(id);
		if (ans == null)
			throw new ExpressionFault.NameError(id);
		return ans;
	}

	/** Find the closest enclosing file.
	 * Throws ExpressionFault.TypeError if not inside a file.
	 */
	public ASTNode.FileHandle<? extends FileType> findEnclosingFile()
	{
		if (this.filehandle != null)
			return this.filehandle;
		if (this.parent != null)
			return this.parent.findEnclosingFile();
		throw new ExpressionFault.TypeError();
	}

	final Context parent;
	final String name;
	final ASTNode.FileHandle<? extends FileType> filehandle;

	Context(Context parent, String name, ASTNode.FileHandle<? extends FileType> filehandle)
	{
		this.parent = parent;
		this.name = name;
		this.filehandle = filehandle;

		log.info("created new Context '%s'", name);
	}
}