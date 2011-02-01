package jsignalml;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.Map;
import java.util.TreeMap;

public class Test_BinaryParam {
	static final Frame reader = new Frame(null);
	/* parent=null means that no other parameters can be called,
	   but this should be OK in this particuar case.
	   If ever Frame is modified to actually require the parent,
	   a NullPointerException should occur and this test corrected.
	*/

	static final String file1 = "target/test-classes/file1";
	static final Expression file1expr = Expression.Const.make(file1);

	static final Expression const_offset = Expression.Const.make(1);
	static final Expression const_format = Expression.Const.make("<i1");

	final ASTNode.Signalml codec = new ASTNode.Signalml("Test");
	ASTNode.FileHandle<FileType.BinaryFile> handle;

	@Before	public void init() throws Exception
	{
		handle = ASTNode.FileHandle.make(codec, file1expr);
	}

	@Test public void read_binary_file_param() throws Exception
	{
		ASTNode.Param p = new ASTNode.BinaryParam(handle, "p", new Type.Int(),
							  const_format, const_offset);
		// TODO: write eval visitor for ASTNodes
		// assertThat(p.eval(reader), equalTo((Type)new Type.Int('2')));
	}
}
