package jsignalml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jsignalml.ExpressionFault.ExternalError;
import jsignalml.logging.Logger;

public class TextBuffer {
	protected static final Logger log = new Logger(TextBuffer.class);
	private List<String> linesCache = new LinkedList<String>();
	private int currentLinesCounter = 0;

	private static final int REGEXP_GROUP_NUMBER = 1;

	final private BufferedReader source;

	final java.io.File filename;

	public TextBuffer(File name) throws java.io.FileNotFoundException, java.io.IOException {
		log.info("opening text file %s", name);
		this.filename = name;

		FileReader fileReader = new FileReader(name);
		this.source = new BufferedReader(fileReader);
	}

	public TypeInt read(TypeInt line, TypeString pattern, TypeInt groupNumber, TypeInt t){
		String s = read(line.value.intValue(), pattern.value, groupNumber.value.intValue());
		return new TypeInt(s);
	}

	public TypeFloat read(TypeInt line, TypeString pattern, TypeInt groupNumber, TypeFloat t){
		String s = read(line.value.intValue(), pattern.value, groupNumber.value.intValue());
		return new TypeFloat(Double.parseDouble(s));
	}

	public TypeString read(TypeInt line, TypeString pattern, TypeInt groupNumber, TypeString t){
		String s = read(line.value.intValue(), pattern.value, groupNumber.value.intValue());
		return new TypeString(s);
	}

	public TypeInt get_line_matching_pattern(TypeString pattern, TypeInt groupNumber){
		Pattern pat = Pattern.compile(pattern.value);
		log.debug("Searching for match [" + pattern.value + "] in all the lines");
		int line = 1;
		try {
			while (true) { // either some match will be found or exception will
							// be thrown
				String textLine;
				textLine = getLine(line, true);
				Matcher matcher = pat.matcher(textLine);
				if (matcher.find()
						&& (groupNumber.value.intValue() < 1
								|| matcher.group(groupNumber.value.intValue()) != null)) {
					return new TypeInt(line);
				}
				line++;

			}
		} catch (IOException e) {
			throw new ExpressionFault.ValueError("Unable to read data from file: " + e.getMessage());
		}
	}

	private String read(int line, String pattern, int groupNumber) {
		Pattern pat = Pattern.compile(pattern);

		try {

			if(line < 0){
				log.debug("Searching for match [" + pattern + "] in all the lines");
				line = 1;
				while (true) { // either some match will be found or exception will be thrown
					String found = null;
					
					String textLine = getLine(line, true);
					Matcher matcher = pat.matcher(textLine);
					if (matcher.find()) {
						found = matcher.group(groupNumber);
						log.debug("Found match: '" + found + "'");
						
						return found;
					}
					
					line ++;
					
				}
				
			} else {
				log.debug("Searching for match [" + pattern + "] in line " + line);
				String textLine = getLine(line, /*false*/true);
				//if (textLine == null) return "";

				String found = null;
				
				Matcher matcher = pat.matcher(textLine);

				if (matcher.find()) {
					found = matcher.group((groupNumber < 0) ? REGEXP_GROUP_NUMBER : groupNumber);
					log.debug("Found match: '" + found + "'");
				} //else return "";
																																															
				if (found == null) {
					throw new ExternalError("No match found for pattern " + pattern + " in line "
							+ line + " [" + textLine + "]");
				}

				return found;
			}
			

		} catch (IOException e) {
			throw new ExpressionFault.ValueError("Unable to read data from file: " + e.getMessage());
		}
	}

	/**
	 * Tries to fetch the given line from the cache. If not found, the cache is
	 * filled with the lines from file until desired line is met and returned;
	 * 
	 * @param lineNumber
	 * @return
	 * @throws IOException
	 */
	private String getLine(int lineNumber, boolean throwExceptionOnEof) throws IOException {
		log.debug("Looking into line: %d, currentLinesCounter: %d", 
				lineNumber, currentLinesCounter);
		if (currentLinesCounter >= lineNumber) {
			log.debug("Line found within cached lines, returning [" 
					+ linesCache.get(lineNumber-1) + "]");
			
			return linesCache.get(lineNumber-1);
		} else {
			log.debug("Line not found found within cached lines, reading next lines");
			while (currentLinesCounter < lineNumber) {
				log.debug("Reading line number: " + (currentLinesCounter+1));
				String line = source.readLine();
				if(line == null){
					if (throwExceptionOnEof) throw new ExternalError("End of file reached, expected line/data not found in file");
					else return null;
				}
				linesCache.add(line);
				currentLinesCounter++;
			}
			
			log.debug("Returning [" 
					+ linesCache.get(lineNumber-1) + "]");
			return linesCache.get(lineNumber-1);
		}
	}

	public File getFilename() {
		return this.filename;
	}

	public void close() {
		// TODO
	}

	public static TextBuffer open(File filename) {
		try {
			return new TextBuffer(filename);
		} catch (FileNotFoundException e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		} catch (IOException e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		}
	}
}
