package parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import enums.TYPE;

/*
 * A class for parsing input files into a standard csv format for later conversion to a MySQL table
 */
public abstract class Parser {

	// Localized file paths
	private String path;

	// The actual files
	private File file;

	// The texts contained within the files
	private String text = "";
	
	// The number of lines of the document
	private int lineNumber = 0;
	
	// The converted final text
	private String conversion = "";
	
	// The type of file being parsed
	private TYPE type;

	/*
	 * Constructor requiring a path of the input file
	 */
	public Parser(String path) {
		this.path = path;
		this.file = new File(path);
		read();
		parse();
	}
	
	/*
	 * Parse the input file
	 */
	protected abstract void parse();

	/*
	 * Displays the file name and the contents to output
	 * Also initializes the text contents of each file
	 */
	private void read() {
		// Read in the file contents
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// If no file found, exit execution
			System.err.println("File not found:");
			System.err.println(file.getAbsolutePath());
			System.exit(-1);
		}
		// Go line-by-line and read the file
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			setLineNumber(getLineNumber() + 1);
			// Adds a newline character to the end of the string
			text += line + System.getProperty("line.separator");
		}
		scanner.close();
	}

	/*
	 * Getters and setters
	 */

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

}
