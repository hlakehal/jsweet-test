package tests;

import org.jsweet.JSweetCommandLineLauncher;

public class Main {

	public static void main(String[] args) {

		args = new String[] {
				"-i",
				"src-test",
				"-m",
				"es2015",
				"--targetVersion",
				"ES6",
				"--tsOnly"
		};
		
		JSweetCommandLineLauncher.main(args);
		
		
	}
	
	
}
