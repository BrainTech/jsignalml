package jsignalml;

import java.util.Collection;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;

import org.apache.commons.lang.StringUtils;

public abstract class Context {
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

	final Context parent;
	final String name;
	Context(Context parent, String name)
	{
		this.parent = parent;
		this.name = name;
	}

	/** Find the closest enclosing file.
	 * Throws ExpressionFault.TypeError if not inside a file.
	 */
	public ASTNode.FileHandle<? extends FileType> findEnclosingFile()
	{
		assert this.parent != null;
		return this.parent.findEnclosingFile();
	}

	public static class ClassContext extends Context {
		static final Logger log = new Logger(Context.class);

		final ASTNode.FileHandle<? extends FileType> filehandle;

		public ClassContext(Context parent, String name,
				    ASTNode.FileHandle<? extends FileType> filehandle)
		{
			log.info("created new Context '%s'", name);

			super(parent, name, filehandle);
			this.filehandle = filehandle;
		}

		public ASTNode.FileHandle<? extends FileType> findEnclosingFile()
		{
			if (this.filehandle != null)
				return this.filehandle;
			throw new ExpressionFault.TypeError();
		}

	}
}