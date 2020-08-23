import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/*
	Part of the internal testring suite in CSC172 Fall 2020
	This class is responsible for unzipping given submissions.
	Made by: Pavlo Pastaryev
	Email: ppastary@u.rochester.edu 
*/
public class StageOne {
	// Unzip a file given by name to ./tested directory
	private static void unzip(String zipFile, LinkedList<String> tests) {
		System.out.print("  Unzipping " + zipFile + " ... ");
		try
	    {
	        int BUFFER = 2048;
	        File file = new File("submissions/" + zipFile);

	        ZipFile zip = new ZipFile(file);
	        String newPath = "tested/" + zipFile.split("_")[0];

	        new File(newPath).mkdir();
	        Enumeration zipFileEntries = zip.entries();

	        // Process each entry
	        while (zipFileEntries.hasMoreElements())
	        {
	            // grab a zip file entry
	            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
	            String currentEntry = entry.getName();

	            File destFile = new File(newPath, currentEntry);
	            File destinationParent = destFile.getParentFile();

	            // create the parent directory structure if needed
	            destinationParent.mkdirs();

	            if (!entry.isDirectory())
	            {
	                BufferedInputStream is = new BufferedInputStream(zip
	                .getInputStream(entry));
	                int currentByte;
	                // establish buffer for writing file
	                byte data[] = new byte[BUFFER];

	                // write the current file to disk
	                FileOutputStream fos = new FileOutputStream(destFile);
	                BufferedOutputStream dest = new BufferedOutputStream(fos,
	                BUFFER);

	                // read and write until last byte is encountered
	                while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
	                    dest.write(data, 0, currentByte);
	                }
	                dest.flush();
	                dest.close();
	                is.close();
	            }


	        }
	        tests.add(zipFile.split("_")[0]);
	        System.out.println("Finished.");
	    }
	    catch (Exception e) 
	    {
	        System.out.println("There was an error unzipping " + zipFile + ": " + e.getMessage());
	    }
	}

	public static void main(String[] args) {
		// arg[0] -- directory to get .zip files from. Default directory if arg[0] not given: ./submissions.
		String subm_dir = "submissions";
		if (args.length > 0)
			subm_dir = args[0];

		// Unzip each file in given directory
		LinkedList<String> testsToRun = new LinkedList<String>();
		System.out.println("[*] Starting to unzip submissions.");
		String[] submissions = new File(subm_dir).list();
		for (String submission : submissions) {
			if (submission.contains(".zip"))
				unzip(submission, testsToRun);
		}
		System.out.println("[*] Done\n");
 		
 		// Write a file with names of students. Used later.
 		try {
			FileWriter writer = new FileWriter("tests_to_run.txt");
			for (String t : testsToRun)
				writer.write(t+"\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}
}