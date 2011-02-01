package jsignalml;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import java.util.TreeMap;

public class Test_FileHandle {
	static final Frame empty_state = new Frame(null);

	static final String file1 = "target/test-classes/file1";
	static final Expression file1expr = Expression.Const.make(file1);
	final ASTNode.Signalml codec = new ASTNode.Signalml("Test");

	@Test public void open_filetype() throws Exception {
		ASTNode.FileHandle<? extends FileType> h =
			ASTNode.FileHandle.make(codec, file1expr, "binary");
		// TODO: write eval visitor for ASTNodes
		// FileType F = h.open(empty_state, null);
		// assertThat(F, instanceOf(FileType.BinaryFile.class));
	}
}
