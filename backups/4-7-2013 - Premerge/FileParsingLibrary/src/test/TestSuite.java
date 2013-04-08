package test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

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
	private String path_solution = "resources/Solution.txt";
	
	// Localized file path for input
	private String path_test = "";
	
	/*
	 * The list of parameter to use for the tests
	 */
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "resources/Example.txt"},
				{ "resources/Example.csv"},
				{ "resources/Example.psv"},
				{ "resources/Example.tsv"},
				{ "resources/Example.xls"},
				{ "resources/Example.xlsx"},
				{ "resources/Example.json"},
				{ "resources/Example.xml"}
				};
		return Arrays.asList(data);
	}

	/*
	 * Constructor for the run of tests
	 */
	public TestSuite(String path) {
		this.path_test = path;
	}

	/*
	 * A single run that compares the parsed text to the expected text
	 */
	@Test
	public void test() {
		Parser parser_test = new Driver(path_test).getParser();
		Parser parser_solution = new Driver(path_solution).getParser();
		System.out.println("File:\n" + parser_test.getFile().getAbsolutePath());
		//System.out.println("\n\nOriginal Text:\n" + parser_test.getText() + "\nConverted Text:\n" + parser_test.getConversion() + "\nSolution Text:\n" + parser_solution.getConversion());
		assertTrue(parser_test.getConversion().equals(parser_solution.getConversion()));
	}

}
