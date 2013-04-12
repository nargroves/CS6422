package main;

import javax.swing.UIManager;

import querier.QueryTool;

public class MainActivity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		QueryTool qt = new QueryTool();
		qt.setUpTool();
		
	}

}
