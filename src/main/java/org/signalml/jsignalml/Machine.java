package org.signalml.jsignalml;

import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.ExpressionFault;
import org.signalml.jsignalml.Logger;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import static java.lang.String.format;

/**
 * Class to hold a group of parameters.
 */
public class Machine {
    public static final Logger log = new Logger(Machine.class);

    public static class Positional {
	public final String name;
	public final Class<? extends Type> type;

	public Positional(String name, Class<? extends Type> type){
	    this.name = name;
	    this.type = type;
	}

	public static Positional make(String name, String type){
	    return new Positional(name, Type.getType(type));
	}
    }

    public abstract static class Param {
	public final String id;
	public final Class<? extends Type> type;
	public final Positional[] args;

	public Param(String id, Class<? extends Type> type, Positional args[]){
	    this.id = id;
	    this.type = type;
	    this.args = args;
	}

	Map<String, Type> mapArgs(Type...args)
	{
	    if(args.length != this.args.length)
		throw new ExpressionFault.ArgMismatch();

	    // XXX: is this the right map class?
	    final Map<String,Type> locals = util.newTreeMap();
	    for(int i=0; i<this.args.length; i++){
		Type cast = args[i].castTo(this.args[i].type);
		locals.put(this.args[i].name, cast);
	    }

	    return locals;
	}

	public Type eval(CallHelper state, Type...args)
	{
	    Map<String,Type> locals = this.mapArgs(args);
	    CallHelper frame = state.localize(locals);
	    Type val = this.read(frame);
	    return val.castTo(this.type);
	}

	public abstract Type read(CallHelper state);
    }

    public abstract static class ReadParam extends Param {
	public ReadParam(String id, Class<? extends Type> type,
			 Positional args[])
	{
	    super(id, type, args);
	}
    }

    public static
    class BinaryParam extends ReadParam {
	final FileHandle<? extends FileType.BinaryFile> handle;
	final Expression format, offset;

	public BinaryParam(String id, Class<? extends Type> type,
			   Positional args[],
			   FileHandle<? extends FileType.BinaryFile> handle,
			   Expression format, Expression offset)
	{
	    super(id, type, args);
	    this.handle = handle;
	    this.format = format;
	    this.offset = offset;
	}

	public String toString()
	{
	    return format("BinaryParam on %s format: %s offset: %s",
			  this.handle, this.format, this.offset);
	}

	@Override
        public Type read(CallHelper state)
	{
	    Type format = this.format.eval(state);
	    Type offset = this.offset.eval(state);

	    BitForm bitf;
	    try{
		bitf = BitForm.get(format.castTo(Type.String.class).value);
	    }catch(BitForm.BadBitForm e){
		throw new ExpressionFault.ExternalError(e);
	    }
	    int offs = offset.castTo(Type.Int.class).value;
	    FileType.BinaryFile file = state.getFile(this.handle);

	    log.debug("reading %s as %s @ %s from %s",
		      this.id, bitf, offs, file);
	    return file.read(bitf, offs);
	}
    }

    public static class ExprParam extends Param {
	final Expression expr;

	public ExprParam(String id, Class<? extends Type> type,
			 Positional args[],
			 Expression expr)
	{
	    super(id, type, args);
	    this.expr = expr;
	}

	public String toString()
	{
	    return format("ExprParam expression: %s", this.expr);
	}

	@Override
	public Type read(CallHelper state)
	{
	    return this.expr.eval(state);
	}
    }

    /**
     * A holder for information about a file yet to be opened.
     * Two filenames can be provided: at construction time, and
     * as an argument to open(). At least one is needed, and
     * the second one has priority. If both are null, a
     * ExpressionFault.ValueError is thrown.
     */
    public static class FileHandle<T extends FileType>
	implements CallHelper.FileHandle<T>
    {
	public final Expression filename; // may be null
	public final List<DataHandle> datas = util.newLinkedList();

	public FileHandle(Expression filename){
	    this.filename = filename;
	}

	public static <V extends FileType>
	FileHandle<V> make(Expression filename){
	    return new FileHandle<V>(filename);
	}

	public static FileHandle make(Expression filename, String type)
	{
	    if(type.equals("binary"))
		return new FileHandle<FileType.BinaryFile>(filename);

	    throw new IllegalArgumentException(format("unkown file type '%s'", type));
	}

	public T open(CallHelper state, File hint)
	{
	    File thename;
	    if(hint != null){
		thename = hint;
	    } else if(this.filename != null){
		Type tmp = this.filename.eval(state);
		String str = tmp.castTo(Type.String.class).getValue();
		thename = new File(str);
	    } else {
		throw new ExpressionFault.ValueError("both filenames are null");
	    }

	    log.info("opening file '%s'", thename);
	    try{
		return FileType.open(thename);
	    }catch(IOException e){
		throw new ExpressionFault.ExternalError(e);
	    }
	}

	void addData(DataHandle data)
	{
	    this.datas.add(data);
	}
    }

    public static class DataHandle {
	public final String mapping, format;

	public DataHandle(FileHandle<?> handle, String mapping, String format)
	{
	    this.mapping = mapping;
	    this.format = format;

	    handle.addData(this);
	}
    }
}
