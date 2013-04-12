package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import main.Driver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import parsers.Parser;

/*
 * Parameterized test cases - the set of tests run over all input parameters
 */
@RunWith(value = Parameterized.class)
public class TestSuite {

	// Localized file path for a 'correct' comparison
	private String path_solution = "resources/examples/Solution.txt";
	
	// Localized file path for input
	private String path_test = "";
	
	// The parsers to be tested
	private Parser parser;
	
	/*
	 * The list of parameter to use for the tests
	 */
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "resources/examples/Example.txt"},
				{ "resources/examples/Example.csv"},
				{ "resources/examples/Example.psv"},
				{ "resources/examples/Example.tsv"},
				{ "resources/examples/Example.xls"},
				{ "resources/examples/Example.xlsx"}
				};
		return Arrays.asList(data);
	}

	/*
	 * Constructor for the run of tests
	 */
	public TestSuite(String path) {
		this.path_test = path;
		parser = new Driver(path_test).getParser();
	}
	
	/*
	 * Utility method that reads the content of a file
	 */
	private String readFileContents(String path) {
		String content = "";
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			content += line + System.getProperty("line.separator");
		}
		scanner.close();
		return content;
	}
	
	/*
	 * Compares the parsed text to the expected text
	 */
	@Test
	public void test_parser() {
		String solution = readFileContents(path_solution);
		assertTrue(parser.getConversion().equals(solution));
	}
	
	/*
	 * Checks for the existence of the output file
	 */
	@Test
	public void test_output_existence() {
		assertTrue(new File(parser.getOutputPath()).exists());
	}
	
	/*
	 * Checks the content of the output file
	 */
	@Test
	public void test_output_content() {
		String content = readFileContents(parser.getOutputPath());
		assertTrue(parser.getConversion().equals(content));
	}

}
