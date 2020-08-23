import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/*
	Part of the internal testring suite in CSC172 Fall 2020
	This class is responsible for testing of a submission and pretty output of the results.
	Made by: Pavlo Pastaryev
	Email: ppastary@u.rochester.edu 
*/
public class TestRunner {
	// Runs a tester class by a given name. Note that if your tester is named LabXTester.java, input parameter tester should be LabXTester
	public static String runTester(String tester) {
		String out = "";
		// Compile the tester. Needed because of the changes in environment for each student
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		compiler.run(null, null, null, "tests/"+tester+".java");

		out+="---\nRunning " + tester + ".\n\n";
		try {
			// Dynamically load the compiled tester
			File file = new File("tests");
			URL url = file.toURI().toURL();
			URL[] urls = new URL[]{url};

			ClassLoader classLoader = new URLClassLoader(urls);
			Class c = classLoader.loadClass(tester);

			// Run JUnit environment tests
			JUnitCore jUnitCore = new JUnitCore();
			Result result = jUnitCore.runClasses(c);

			// Pretty output
			for (Failure failure: result.getFailures()) {
				out+=("    " + failure.getTestHeader() + " -> " + failure.getException().toString() + "\n");
			}
			if (result.getFailureCount() > 0) out+="\n";
			out +="Total test score: " + (result.getRunCount() - result.getFailureCount()) + "/" + result.getRunCount() + ".\n";
			out +="Total test(s) run time: " + String.format("%.3f", (result.getRunTime() / 100.0)) + " ms.\n---\n";
		} catch (ClassNotFoundException e) {
			out+=tester + " class not found. Check if it is present and/or compiled.\n---\n";
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return out;
	}

	public static void main(String[] args) {
		File testedDir = new File("tested");
		if (!testedDir.exists())
			testedDir.mkdir();

		System.out.println("\n[*] Testing " + args[1] + ".\n");

		// Run the given tester(s) (args[0]) on a given student (args[1])
		String results = "";
		for (String tester: args[0].split(","))
			results += runTester(tester);
		
		for (String s: results.split("\n"))
			System.out.println("  " + s);

		// Create a log file with (test_results.txt) test results for a student (args1) in his folder
		try {
			FileWriter writer = new FileWriter("tested/"+args[1]+"/test_results.txt");
			writer.write(results);
			writer.close();
		} catch (IOException e) {
			System.out.println("[*] Was unable to write results to file.");
		}
	}
}