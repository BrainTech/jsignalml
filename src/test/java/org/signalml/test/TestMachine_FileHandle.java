package org.signalml.jsignalml.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import java.util.TreeMap;

import org.signalml.jsignalml.LocalState;
import org.signalml.jsignalml.FileType;
import org.signalml.jsignalml.Machine.FileHandle;
import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.Expression;

public class TestMachine_FileHandle {
    static final LocalState empty_state
	= new LocalState(null, new TreeMap<String,Type>());

    static final String file1 = "target/test-classes/file1";
    static final Expression file1expr =
	new Expression.Const(new Type.String(file1));

    @Test public void open_filetype() throws Exception {
	FileHandle<? extends FileType> h =
	    FileHandle.<FileType.BinaryFile>make(file1expr);
	FileType F = h.open(empty_state, null);
	assertThat(F, instanceOf(FileType.BinaryFile.class));
    }
}
