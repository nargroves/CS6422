package parsers;


/*
 * A class for parsing input files into a standard csv format for later conversion to a MySQL table
 */
public class Parser_Text extends Parser {

	private Parser parser;
	
	/*
	 * Constructor for an Text-based file-parser
	 */
	public Parser_Text(String path) {
		super(path);
	}

	@Override
	protected void parse() {
		parser = new Parser_Delimited(this.getPath());
		this.setText(parser.getText());
		this.setConversion(parser.getConversion());
	}

}
