package org.signalml.jsignalml;

import org.signalml.jsignalml.sexpression.Type;
import org.signalml.jsignalml.sexpression.Expression;
import org.signalml.jsignalml.sexpression.ExpressionFault;
import org.signalml.jsignalml.Logger;

import java.util.Map;
import java.util.TreeMap;

public class Machine {
    public static final Logger log = new Logger(Machine.class);

    public static class MachineError extends Exception {
	public static class ArgMismatch extends MachineError {}
	public static class CastError extends MachineError {}
	public static class BadBitForm extends MachineError {}
    }

    public static class Positional {
	public final String id;
	public final Class<? extends Type> type;

	public Positional(String id, Class<? extends Type> type){
	    this.id = id;
	    this.type = type;
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
	    throws MachineError, ExpressionFault
	{
	    if(args.length != this.args.length)
		throw new MachineError.ArgMismatch();

	    // XXX: is this the right map class?
	    final Map<String,Type> locals = new TreeMap<String,Type>();
	    for(int i=0; i<this.args.length; i++){
		Type cast = args[i].castTo(this.args[i].type);
		locals.put(this.args[i].id, cast);
	    }

	    return locals;
	}

	public abstract Type eval(CallHelper state, Type...args)
	    throws ExpressionFault, MachineError;
    }

    public abstract static class ReadParam extends Param {
	final FileHandle handle;

	public ReadParam(String id, Class<? extends Type> type,
			 Positional args[], FileHandle handle)
	{
	    super(id, type, args);
	    this.handle = handle;
	}

	@Override
	public Type eval(CallHelper state, Type...args)
	    throws MachineError, ExpressionFault
	{
	    Map<String,Type> locals = this.mapArgs(args);
	    Type val = this.read(state);
	    return val.castTo(this.type);
	}

	public abstract Type read(CallHelper state)
	    throws ExpressionFault, MachineError;
    }

    public static class BinaryParam extends ReadParam {
	final Expression format, offset;

	public BinaryParam(String id, Class<? extends Type> type,
			   Positional args[], FileHandle handle,
			   Expression format, Expression offset)
	{
	    super(id, type, args, handle);
	    this.format = format;
	    this.offset = offset;
	}

	@Override
	public Type read(CallHelper state)
	    throws ExpressionFault, MachineError
	{
	    Type format = this.format.eval(state);
	    Type offset = this.offset.eval(state);

	    BitForm bitf;
	    try{
		bitf = BitForm.get(format.castTo(Type.String.class).value);
	    }catch(BitForm.BadBitForm e){
		throw new MachineError.BadBitForm();
	    }
	    int offs = offset.castTo(Type.Int.class).value;

	    FileType file = state.getFile(this.handle);
	    log.debug("reading %s as %s @ %s from %s",
		      this.id, bitf, offs, file);
	    return file.read(bitf, offs);
	}
    }


    public static class FileHandle {
	public final String filename; // may be null
	public FileHandle(String type, String filename){
	    this.filename = filename;
	    // TODO
	}

	public FileType open(CallHelper state, String filename){
	    return null;
	}
    }
}
