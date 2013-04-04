package parsers;

/*
 * A class for parsing input files into a standard csv format for later conversion to a MySQL table
 */
public class Parser_Delimited extends Parser {

	/*
	 * The type of delimiter
	 */
	private enum DELIMITER {
		CSV, PSV, TSV;
	}

	private DELIMITER delimiter;
	private String token = null;

	/*
	 * Constructor for an Delimited-based file-parser
	 */
	public Parser_Delimited(String path) {
		super(path);
	}

	/*
	 * Parses the input file into a usable delimited table
	 */
	protected void parse() {
		// Use regex to determine if file is delimited
		String temp = super.getText();

		int numTotalDelimiter = determineDelimiter();

		// Determine the number of delimiter per line by using the total number
		// of delimiters divided by the line number
		int perLine = numTotalDelimiter / super.getLineNumber();

		// Make sure the number of delimiters on every line is the same
		boolean numDelimitersPerLineEqual = validate(temp, perLine);

		if (numDelimitersPerLineEqual == true) {
			super.setConversion(super.getText().replace(token, ","));
		} else {
			// An error must have occurred
			System.err.println("The following file failed validation:\n" + super.getPath());
			System.exit(-1);
		}
	}

	/*
	 * Determines what character is being used as a delimiter
	 * Returns the number of uses of that delimiter
	 */
	private int determineDelimiter() {
		String temp = super.getText();
		int numTotalDelimiter = 0;
		if (temp.contains(",")) {
			int delimiterCount = temp.split(",").length - 1;
			if (delimiterCount > numTotalDelimiter) {
				numTotalDelimiter = delimiterCount;
				delimiter = DELIMITER.CSV;
				token = ",";
			}
		}
		if (temp.contains("|")) {
			int delimiterCount = temp.split("\\|").length - 1;
			if (delimiterCount > numTotalDelimiter) {
				numTotalDelimiter = delimiterCount;
				delimiter = DELIMITER.PSV;
				token = "|";
			}
		}
		if (temp.contains("\t")) {
			int delimiterCount = temp.split("\t").length - 1;
			if (delimiterCount > numTotalDelimiter) {
				numTotalDelimiter = delimiterCount;
				delimiter = DELIMITER.TSV;
				token = "\t";
			}
		}
		return numTotalDelimiter;
	}

	/*
	 * Checks to make sure every line of the string has the same number of
	 * delimiters
	 */
	private boolean validate(String temp, int perLine) {
		// Iterate over each line and determine if the same number of delimiter
		// appears in each line
		boolean numDelimitersPerLineEqual = true;
		for (String line : temp.split("\n")) {
			if ((delimiter == DELIMITER.CSV && line.split(",").length - 1 != perLine)
					|| (delimiter == DELIMITER.PSV && line.split("\\|").length - 1 != perLine)
					|| (delimiter == DELIMITER.TSV && line.split("\t").length - 1 != perLine)) {
				numDelimitersPerLineEqual = false;
			}
		}
		return numDelimitersPerLineEqual;
	}

}
