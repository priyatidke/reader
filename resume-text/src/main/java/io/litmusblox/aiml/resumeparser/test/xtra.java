package io.litmusblox.aiml.resumeparser.test;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class xtra {

	public static void main(String[] args) {

		String maindirpath = "/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/Resume";
		File maindir = new File(maindirpath);

		String type_txt = ".txt";// replace what ever type of file you need to
									// search
		String type_pdf = ".pdf";
		String type_doc = ".doc";
		String type_docx = ".docx";
		String type_rtf = ".rtf";

		List<String> fileNames = new ArrayList<>();

		if (maindir.exists() && maindir.isDirectory()) {
			File arr[] = maindir.listFiles();
			int n = arr.length;

			System.out.println("**********************************************");
			System.out.println("Files from main directory : " + maindir);
			System.out.println("**********************************************");

			for (File f : maindir.listFiles()) {

				if (f.isFile() && f.getName().endsWith(type_txt)) {

					System.out.println(f.getName());
					// System.out.println("No. Of TEXT
					// Files:"+fileNames.getSum());

				} else if (f.isFile() && f.getName().endsWith(type_pdf)) {
					fileNames.add(f.toString());

					System.out.println(f.getName());
					// System.out.println("No. Of PDF Files:"+fileNames.size());

				} else if (f.isFile() && f.getName().endsWith(type_doc)) {
					fileNames.add(f.toString());

					System.out.println(f.getName());
					// System.out.println("No. Of DOC Files:"+fileNames.size());

				} else if (f.isFile() && f.getName().endsWith(type_docx)) {
					fileNames.add(f.toString());

					System.out.println(f.getName());
					// System.out.println("No. Of DOCX
					// Files:"+fileNames.size());

				} else if (f.isFile() && f.getName().endsWith(type_rtf)) {
					fileNames.add(f.toString());

					System.out.println(f.getName());
					// System.out.println("No. Of RTF Files:"+fileNames.size());

				} else {
					System.out.println("file is not present");
				}

			}
			System.out.println("\nNo of entries in this directory : " + n);

		}

	}
}
