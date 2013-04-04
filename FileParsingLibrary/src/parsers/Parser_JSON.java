package parsers;

/*
 * A class for parsing input files into a standard csv format for later conversion to a MySQL table
 */
public class Parser_JSON extends Parser {

	/*
	 * Constructor for an JSON-based file-parser
	 */
	public Parser_JSON(String path) {
		super(path);
	}
	
	/*
	 * Parses the input file into a MySQL table
	 */
	protected void parse() {
		// TODO - parse the file
	}

}
