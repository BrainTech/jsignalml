package org.signalml.jsignalml.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.File;

import org.signalml.jsignalml.FileType;
import org.signalml.jsignalml.BitForm;
import org.signalml.jsignalml.sexpression.Type;

public class TestFileType {
    @Test public void test_binary_file_bytes() throws Exception {
	File filename = new File("target/test-classes/file1");
	FileType.BinaryFile F = new FileType.BinaryFile(filename);

	BitForm format = new BitForm.Int8();
	assertThat(F.read(format, 0), equalTo((Type)new Type.Int('1')));
	assertThat(F.read(format, 1), equalTo((Type)new Type.Int('2')));
	assertThat(F.read(format, 2), equalTo((Type)new Type.Int('3')));
	assertThat(F.read(format, 3), equalTo((Type)new Type.Int('4')));
    }

    @Test public void test_binary_file_int() throws Exception {
	File filename = new File("target/test-classes/file1");
	FileType.BinaryFile F = new FileType.BinaryFile(filename);

	BitForm format = new BitForm.Int32.LE();
	int val = ((int)'4') + ((int)'3' << 8) +
	    ((int)'2' << 16) + ((int)'1' << 24);
	assertThat(F.read(format, 0), equalTo((Type)new Type.Int(val)));
    }
}
