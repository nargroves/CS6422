package main;

import parsers.Parser;
import parsers.Parser_Delimited;
import parsers.Parser_Excel;
import parsers.Parser_JSON;
import parsers.Parser_Text;
import parsers.Parser_XML;
import enums.TYPE;

/*
 * A class for choosing a file to convert into a MySQL table
 */
public class Driver {

	// The type of file being parsed
	private TYPE type;

	// The input file path
	private String path;

	// The file extension of the path
	private String extension = "";
	
	// The parser being used for the file
	private Parser parser = null;

	/*
	 * Constructor based on an input file path
	 */
	public Driver(String path) {
		this.path = path;
		validate();
		parse();
	}

	/*
	 * Validate the input file to determine that it falls into a correct format
	 */
	private void validate() {
		extension = path.substring(path.lastIndexOf("."));
		if (extension.equalsIgnoreCase(".csv") || extension.equalsIgnoreCase(".psv") || extension.equalsIgnoreCase(".tsv")) {
			type = TYPE.DELIMITED;
		} else if (extension.equalsIgnoreCase(".xls") || extension.equalsIgnoreCase(".xlsx")) {
			type = TYPE.EXCEL;
		} else if (extension.equalsIgnoreCase(".json")) {
			type = TYPE.JSON;
		} else if (extension.equalsIgnoreCase(".xml")) {
			type = TYPE.XML;
		} else if (extension.equalsIgnoreCase(".txt")) {
			type = TYPE.TEXT;
		} else {
			type = TYPE.UNKNOWN;
		}
	}

	/*
	 * Initialize the specific parser for the input file
	 */
	private void parse() {
		switch (type) {
		case DELIMITED:
			setParser(new Parser_Delimited(path));
			break;
		case XML:
			setParser(new Parser_XML(path));
			break;
		case JSON:
			setParser(new Parser_JSON(path));
			break;
		case EXCEL:
			setParser(new Parser_Excel(path));
			break;
		case TEXT:
			setParser(new Parser_Text(path));
			break;
		case UNKNOWN:
			// An error must have occurred
			System.err.println("The following file failed validation:\n" + path);
			System.exit(-1);
			break;
		default:
			// An error must have occurred
			System.err.println("The following file failed validation:\n" + path);
			System.exit(-1);
			break;
		}
	}

	/*
	 * Get the parser for the file
	 */
	public Parser getParser() {
		return parser;
	}

	/*
	 * Set the parser for the file
	 */
	public void setParser(Parser parser) {
		this.parser = parser;
	}

}
