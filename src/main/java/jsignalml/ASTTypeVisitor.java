package jsignalml;

import javax.lang.model.type.NullType;

public class ASTTypeVisitor extends ASTVisitor<NullType> {
	public static final Logger log = new Logger(ASTTypeVisitor.class);

	// All Expression fields defined in subclasses of ASTNode should be
	// checked.

	@Override
	public NullType visit(ASTNode.ExprParam node, NullType parent)
	{
		log.debug("checking %s", node);
		assert node.expr != null;
		node.expr.accept(TypeVisitor.create(node));
		return null;
	}

	@Override
	public NullType visit(ASTNode.BinaryParam node, NullType parent)
	{
		log.debug("checking %s", node);
		TypeVisitor checker = TypeVisitor.create(node);
		assert node.format != null;
		assert node.offset != null;
		node.format.accept(checker);
		node.offset.accept(checker);
		return null;
	}

	@Override
	public NullType visit(ASTNode.Assert node, NullType parent)
	{
		log.debug("checking %s", node);
		assert node.expr != null;
		node.expr.accept(TypeVisitor.create(node));
		return null;
	}

	@Override
	public NullType visit(ASTNode.FileHandle node, NullType parent)
	{
		log.debug("checking %s", node);
		if (node.filename != null)
			node.filename.accept(TypeVisitor.create(node));
		return null;
	}
}
