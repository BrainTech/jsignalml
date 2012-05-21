package jsignalml;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Test;

public class TestFileType {
	File filename = new File("target/test-classes/file1");

	@Test public void test_binary_file_bytes() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int8();
		assertThat(F.read(format, 0), equalTo((Type)new TypeInt('1')));
		assertThat(F.read(format, 1), equalTo((Type)new TypeInt('2')));
		assertThat(F.read(format, 2), equalTo((Type)new TypeInt('3')));
		assertThat(F.read(format, 3), equalTo((Type)new TypeInt('4')));
	}

	@Test public void test_binary_file_int() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int32.LE();
		int val = ((int)'1') + ((int)'2' << 8) +
			((int)'3' << 16) + ((int)'4' << 24);
		assertThat(F.read(format, 0), equalTo((Type)new TypeInt(val)));
	}

	@Test(expected=ExpressionFault.IndexError.class)
	public void test_binary_file_read_after() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int8();
		F.read(format, 4);
	}

	@Test(expected=ExpressionFault.IndexError.class)
	public void test_binary_file_read_before() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int8();
		F.read(format, -1);
	}

	@Test(expected=ExpressionFault.IndexError.class)
	public void test_binary_file_read_at_edge() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.Int.Int32.LE();
		F.read(format, 2);
	}

	@Test public void test_binary_file_read_string() throws Exception {
		FileType.BinaryFile F = new FileType.BinaryFile(filename);

		BitForm format = new BitForm.String(4);
		Type s = F.read(format, 0);
		assertThat(s, equalTo((Type)new TypeString("1234")));
	}
}
