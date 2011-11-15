package jsignalml.codec;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;

import jsignalml.MyBuffer;
import jsignalml.ExpressionFault;
import jsignalml.Type;
import jsignalml.ContextVisitor;
import jsignalml.Channel;
import jsignalml.ChannelSet;
import jsignalml.util;

public abstract class Signalml extends Context implements jsignalml.Source {
	protected File default_filename;

	final ArrayList<ChannelSet> channel_set_list = util.newArrayList();

	@Override
	public <T> T _accept(ContextVisitor<T> v, String name, T data)
	{
		return v.visit(this, name, data);
	}

	protected void registerChannelSet(ChannelSet set)
	{
		this.channel_set_list.add(set);
		log.info("registered channel set %s (total %d)", set,
			 this.getNumberOfChannelSets());
	}

	public int getNumberOfChannelSets() throws ExpressionFault {
		return this.channel_set_list.size();
	}

	public ChannelSet get_set() {
		assert(!this.channel_set_list.isEmpty());
		return this.channel_set_list.get(0);
	}

	public abstract class FileClass extends Context {
		MyBuffer buffer;

		public void open(File filename)
		{
			if ((filename == null) && (default_filename != null)) {
				filename = default_filename;
				default_filename = null;
			}
			if (filename == null)
				throw new ExpressionFault.ValueError("filename must be specified");
			this.buffer = MyBuffer.open(filename);
		}

		public MyBuffer buffer()
		{
			if (this.buffer == null)
				this.open(null);
			return this.buffer;
		}

		@Override
		public <T> T _accept(ContextVisitor<T> v, String name, T data)
		{
			return v.visit(this, name, data);
		}
	}
}
