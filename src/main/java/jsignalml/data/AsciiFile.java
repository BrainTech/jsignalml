package jsignalml.data;

import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class AsciiFile implements DataInfo {
	final BufferedReader reader;
	int lineno;

	public AsciiFile(File name)
		throws IOException, FileNotFoundException
	{
		this(new FileReader(name));
	}

	public AsciiFile(Reader reader)
		throws IOException, FileNotFoundException
	{
		this.reader = new BufferedReader(reader);
		this.lineno = 0;
	}

	@Override
	public float[] getNextSample()
		throws IOException
	{
		final String line;
		if ((line = this.reader.readLine()) != null)
			return this.parseLine(line);
		return null;
	}

	float[] parseLine(String line)
		throws IOException
	{
		String[] parts = line.split("\\s+");
		if (parts.length < 2)
			throw new IOException("syntax error");
		long pos = Integer.parseInt(parts[0]);
		if (pos != this.lineno++)
			throw new IOException("syntax error");

		float[] ans = new float[parts.length-1];
		for(int i=0; i<ans.length; i++)
			ans[i] = Float.parseFloat(parts[i+1]);
		return ans;
	}
}
