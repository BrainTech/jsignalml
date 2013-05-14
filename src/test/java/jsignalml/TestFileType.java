package jsignalml;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

public class TestFileType {
	File filename = new File("target/test-classes/file1");

	@Test public void test_binary_file_bytes() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int8();
		assertEquals(F.read(format, 0), new TypeInt('1'));
		assertEquals(F.read(format, 1), new TypeInt('2'));
		assertEquals(F.read(format, 2), new TypeInt('3'));
		assertEquals(F.read(format, 3), new TypeInt('4'));
	}

	@Test public void test_binary_file_int() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int32.LE();
		int val = ((int)'1') + ((int)'2' << 8) +
			((int)'3' << 16) + ((int)'4' << 24);
		assertEquals(F.read(format, 0), new TypeInt(val));
	}

	@Test(expectedExceptions=ExpressionFault.IndexError.class)
	public void test_binary_file_read_after() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int8();
		F.read(format, 4);
	}

	@Test public void test_binary_file_read_from_end() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int8();
		assertEquals(new TypeInt('4'), F.read(format, -1));
	}

	@Test(expectedExceptions=ExpressionFault.IndexError.class)
	public void test_binary_file_read_at_edge() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int32.LE();
		F.read(format, 2);
	}

	@Test public void test_binary_file_read_string() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.String(4);
		Type s = F.read(format, 0);
		assertEquals(s, new TypeString("1234"));
	}
}
