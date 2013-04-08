package parsers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * A class for parsing input files into a standard delimited format for later conversion to a MySQL table
 */
public class Parser_Excel extends Parser {

	/*
	 * Constructor for an Excel-based file-parser
	 */
	public Parser_Excel(String path) {
		super(path);
	}

	/*
	 * Parses the input file
	 */
	protected void parse() {
		// The input stream from path
		InputStream input = null;
		try {
			input = new FileInputStream(this.getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// The Workbook is created
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(input);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// // For multiple table support
		// for (int i = 0; i < wb.getNumberOfSheets(); i++) {
		// System.out.println(wb.getSheetAt(i).getSheetName());
		// convert(wb.getSheetAt(i));
		// }
		// For a single sheet
		convert(wb.getSheetAt(0));
	}

	/*
	 * Convert the excel file to a delimited format
	 */
	private void convert(Sheet sheet) {
		String text = "";
		Row row = null;
		// Iterate over all the rows and columns to find all cell values
		for (int i = 0; i < sheet.getLastRowNum()+1; i++) {
			row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				text += row.getCell(j).getStringCellValue();
				if (j < row.getLastCellNum()-1) {
					text += "~";
				}
			}
			text += "\r\n";
		}
		this.setConversion(text);
	}

}
