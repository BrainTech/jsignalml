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
import org.signalml.jsignalml.LocalState;
import org.signalml.jsignalml.FileType;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.Type;

public class TestMachine_BinaryParam {
    static final LocalState empty_state
	= new LocalState(null, new TreeMap<String,Type>());

    /*
    static final Map<String,Type> map;
    static {
	map = new TreeMap<String,Type>();
	map.put("_offset", new Type.Int(1));
	map.put("_format", new Type.String("<i8"));
    }

    static final CallHelper state = new LocalState(null, map);
    */

    static final String file1 = "target/test-classes/file1";
    static final Expression file1expr =
	new Expression.Const(new Type.String(file1));

    Machine.FileHandle<FileType.BinaryFile> handle;

    @Before
    public void init() throws Exception {
	handle = Machine.FileHandle.make(FileType.BinaryFile.class, file1expr);
    }

    static final Expression const_offset =
	new Expression.Const(new Type.Int(1));
    static final Expression const_format =
	new Expression.Const(new Type.String("<i1"));

    @Test public void read_binary_file_param() throws Exception {
	Param p = new BinaryParam("p", Type.Int.class, new Positional[0],
				  handle, const_format, const_offset);

	assertThat(p.eval(empty_state), equalTo((Type)new Type.Int('2')));
    }
}
