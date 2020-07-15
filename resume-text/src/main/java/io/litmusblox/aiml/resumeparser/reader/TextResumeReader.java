package io.litmusblox.aiml.resumeparser.reader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.litmusblox.aiml.resumeparser.domain.ResumeAsText;

public class TextResumeReader implements ResumeReader {

	@Override
	public ResumeAsText getWordFromFile(File resume) {
		ResumeAsText resumeAsText= new ResumeAsText();
		resumeAsText.setResumeName(resume.getName());
		
		try {
			String resumeContent= TextResumeReader.readFileAsString(resume.getAbsolutePath());
			resumeAsText.setResumeContent(resumeContent);
			resumeAsText.setWords(Arrays.asList(resumeContent.split(" ")));
			return resumeAsText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String readFileAsString(String fileName) throws Exception {
		return new String(Files.readAllBytes(Paths.get(fileName)));

	}

	//===================Unnecessary code  starts here  =====================

	public void main(String[] args) throws Exception {
		String data = readFileAsString("/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/Resume/readme.txt");
		System.out.println("Text : " + data);
		String words[] = data.split(" ");

		// Scanner sc = new Scanner(data);
		ArrayList<String> arr = new ArrayList<String>(Arrays.asList(words));
		for (int x = 0; x < arr.size(); x++) {
			System.out.println("Word :" + arr.get(x));

		}
	}
	//===================Unnecessary code  Ends here  =====================


}
