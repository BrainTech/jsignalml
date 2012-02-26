package jsignalml;

public interface ASTTypeResolver {
	/**
	 * Tell what type the node is. In case of ParamNodes this is the result
	 * of the expression or the type of data read based upon the format.
	 *
	 * @return Type or null iff not known
	 */
	Type getType(ASTNode node);
}
