package jsignalml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import jsignalml.logging.Logger;

/**
 * This is a helper class which reads the float values from ascii files
 * at given offset (position in file) with whitespace delimiters
 *
 * @author kago
 *
 */
public class AsciiScanner {
	// the scanner instance
	private Scanner sc;

	// the file reference, required in case of 'reset'
	private File file;

	// current position at which the scanner returns the next value
	private int currentPosition = 0;

	private static final Logger log = new Logger(AsciiScanner.class);

	/**
	 * The constructor which takes the file reference
	 *
	 * @param source the file to which the searching applies
	 * @throws FileNotFoundException
	 */
	public AsciiScanner(File source) throws FileNotFoundException{
		log.debug("KAGO ASCII SCANNER new instance");
		this.file = source;
		sc = new Scanner(source);
		sc.useDelimiter("[\\s]+");
	}

	/**
	 * Returns the float value at given offset.
	 * If the offset is higher than current position, the number of values are skipped
	 * If the offset is lower than current position, the new instance of the underlying scanner is instantiated.
	 *
	 * @param offset - the position (using whitespace delimiters) of the desired value
	 *
	 * @return the float value found
	 *
	 * @throws ExpressionFault
	 */
	public float readFloat(int offset) throws ExpressionFault {
		try {
			if(offset == currentPosition){
				// the next position is the one we need
				currentPosition ++;
				return sc.nextFloat();
			} else if (offset > currentPosition){
				// skip a number of values until meet the desired position
				log.trace("Skipping " + (offset - currentPosition));
				while(offset > currentPosition){
					skip();
					currentPosition ++;
				}
				currentPosition ++;
				return sc.nextFloat();
			} else {
				// the scanner need to go back
				// the current position must be reset and new scanner created
				log.debug("Reset scanner");
				currentPosition = 0;
				sc.close();
				sc = new Scanner(file);
				sc.useDelimiter("[\\s]+");
				return readFloat(offset);
			}
		} catch (FileNotFoundException e) {
			 throw new ExpressionFault.ValueError("Invalid filename specified: " + e.getMessage());
		}
	}


	// skips next value (using whitespace characters as delimiters)
	private void skip(){
		sc.next();
	}

}
