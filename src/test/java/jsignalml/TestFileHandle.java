package jsignalml;

import org.junit.Test;

public class TestFileHandle {
	static final String file1 = "target/test-classes/file1";
	static final Expression file1expr = Expression.Const.make(file1);
	final ASTNode.Signalml codec = new ASTNode.Signalml("Test");

	@Test public void open_filetype() throws Exception {
		ASTNode.FileHandle<? extends FileType> h =
			ASTNode.FileHandle.make(codec, Expression.Const.make(file1),
						file1expr, "binary");
		// TODO: write eval visitor for ASTNodes
		// FileType F = h.open(empty_state, null);
		// assertThat(F, instanceOf(FileType.BinaryFile.class));
	}
}
