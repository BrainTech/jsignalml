package jsignalml.codec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import jsignalml.AsciiScanner;
import jsignalml.ChannelSet;
import jsignalml.ContextVisitor;
import jsignalml.ExpressionFault;
import jsignalml.MyBuffer;
import jsignalml.TextBuffer;
import jsignalml.TypeInt;
import jsignalml.XMLBuffer;
import jsignalml.util;

public abstract class Signalml extends Context implements jsignalml.Source {
	protected File default_filename;
	protected File main_filename;

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
		assert this.getNumberOfChannelSets() == 1;
	}

	public int getNumberOfChannelSets() throws ExpressionFault {
		return this.channel_set_list.size();
	}

	public ChannelSet get_set() {
		return this.get_set(0);
	}

	public ChannelSet get_set(int id) {
		this.maybeInitParams();
		assert(!this.channel_set_list.isEmpty());
		assert(id < getNumberOfChannelSets());
		return this.channel_set_list.get(id);
	}

	public abstract class FileClass extends Context {
		MyBuffer buffer;
		TextBuffer textBuffer;
		XMLBuffer xmlBuffer;
		private AsciiScanner scanner;
		protected boolean isBinary = true; // this is the default type
		final int file_index;

		@Override
		public TypeInt len() {
			return isBinary ? new TypeInt(buffer().getLimit()) : super.len();
		}
		
		public boolean isBinary(){
			return isBinary;
		}

		protected FileClass(int file_index) {
			this.file_index = file_index;
		}

		protected File currentFilename;

		public File getCurrentFilename(){
			return currentFilename;
		}

		public AsciiScanner getScanner(){
			return scanner;
		}

		public void open(File filename)
		{
			if ((filename == null) && (currentFilename != null)) {
				filename = currentFilename;
				default_filename = null;
			} else if ((filename == null) && (default_filename != null)) {
				filename = default_filename;
				//default_filename = null;
			}
			if (filename == null)
				throw new ExpressionFault.ValueError("filename must be specified");

			if(!isBinary) {
				try {
					scanner = new AsciiScanner(filename);
				} catch (FileNotFoundException e) {
					throw new ExpressionFault.ValueError("Invalid filename specified: " + e.getMessage());
				}
			}
			this.buffer = MyBuffer.open(filename);
			this.textBuffer = TextBuffer.open(filename);
			if (filename.getName().endsWith(".xml")) {
				this.xmlBuffer = new XMLBuffer(filename);
			}
			this.currentFilename = filename;

			if (this.file_index == 0)
				main_filename = filename;
		}

		public TextBuffer textBuffer()
		{
			if (this.textBuffer == null)
				this.open(null);
			return this.textBuffer;
		}

		public MyBuffer buffer()
		{
			if (this.buffer == null)
				this.open(null);
			return this.buffer;
		}

		public XMLBuffer xmlBuffer(){
			if (this.xmlBuffer == null)
				this.open(null);
			return this.xmlBuffer;
		}

		@Override
		public <T> T _accept(ContextVisitor<T> v, String name, T data)
		{
			return v.visit(this, name, data);
		}
	}

	public static boolean isPrimGeneration() {
		return _prim;
	}

	public static void setPrimGeneration(boolean prim) {
		_prim = prim;
	}

	private static boolean _prim =
			System.getProperties().getProperty("jsignalml.primitive", "").length() > 0
				&& System.getProperties().getProperty("jsignalml.primitive").equals("1");

	public EmptyStringParam get_channel_name(){
		return new EmptyStringParam();
	}

	public EmptyStringParam get_unit(){
		return new EmptyStringParam();
	}

	public EmptyStringParam get_channel_type(){
		return new EmptyStringParam();
	}

	public DefaultIntParam get_calibration_offset(){
		return new DefaultIntParam(0);
	}

	public DefaultIntParam get_calibration_gain(){
		return new DefaultIntParam(1);
	}

	public Header get_header() {
		return new DefaultHeader();
	}

	public File getCurrentFilename() {
		return main_filename;
	}

	public void open(File filename) {
		this.default_filename = filename;
		this.maybeInitParams();
	}

	public void close() {
		// right now nothing
	}

	public void maybeInitParams() {
		if (this.channel_set_list.isEmpty())
			this.createParams();
	}
}
