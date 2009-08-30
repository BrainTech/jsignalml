package org.signalml.jsignalml.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.Map;
import java.util.TreeMap;

import org.signalml.jsignalml.Machine;
import org.signalml.jsignalml.Machine.Param;
import org.signalml.jsignalml.Machine.BinaryParam;
import org.signalml.jsignalml.Machine.Positional;
import org.signalml.jsignalml.Reader;
import org.signalml.jsignalml.FileType;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.Type;

public class TestMachine_BinaryParam {
    static final Reader reader = new Reader(null);
    /* codec=null means that no other parameters can be called,
       but this should be OK in this particuar case.
       If ever Reader is modified to actually require the codec,
       a NullPointerException should occur and this test corrected.
    */

    static final String file1 = "target/test-classes/file1";
    static final Expression file1expr =
	new Expression.Const(new Type.String(file1));

    static final Expression const_offset =
	new Expression.Const(new Type.Int(1));
    static final Expression const_format =
	new Expression.Const(new Type.String("<i1"));

    Machine.FileHandle<FileType.BinaryFile> handle;

    @Before
    public void init() throws Exception {
	handle = Machine.FileHandle.make(file1expr);
    }

    @Test public void read_binary_file_param() throws Exception {
	Param p = new BinaryParam("p", Type.Int.class, new Positional[0],
				  handle, const_format, const_offset);

	assertThat(p.eval(reader), equalTo((Type)new Type.Int('2')));
    }
}
