package io.litmusblox.aiml.resumeparser.reader;

//https://stackoverflow.com/questions/19358643/java-reading-doc-file-using-poi
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import io.litmusblox.aiml.resumeparser.domain.ResumeAsText;

public class DocResumeReader implements ResumeReader {
	//===================Unnecessary code  starts here  =====================
	public static void main(String[] args) {

		// Alternate between the two to check what works.
		// String FilePath = "D:\\Users\\username\\Desktop\\Doc1.docx";
		String FilePath = "D:\\Users\\username\\Desktop\\Bob.doc";
		FileInputStream fis;

		if (FilePath.substring(FilePath.length() - 1).equals("x")) { // is a
																		// docx
			try {
				fis = new FileInputStream(new File(FilePath));
				XWPFDocument doc = new XWPFDocument(fis);
				XWPFWordExtractor extract = new XWPFWordExtractor(doc);
				System.out.println(extract.getText());
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else { // is not a docx
			try {
				fis = new FileInputStream(new File(FilePath));
				HWPFDocument doc = new HWPFDocument(fis);
				WordExtractor extractor = new WordExtractor(doc);
				System.out.println(extractor.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//===================Unnecessary code  ends here  =====================


	@Override
	public ResumeAsText getWordFromFile(File resume) {
		ResumeAsText resumeAsText= new ResumeAsText();
		resumeAsText.setResumeName(resume.getName());
		FileInputStream fis;

		//Docx File
		if (resume.getAbsolutePath().substring(resume.getAbsolutePath().length() - 1).equals("x")) { 
			try {
				fis = new FileInputStream(resume);
				XWPFDocument doc = new XWPFDocument(fis);
				XWPFWordExtractor extract = new XWPFWordExtractor(doc);
				resumeAsText.setWords(Arrays.asList(extract.getText().split(" ")));
				resumeAsText.setResumeContent(extract.getText());
				
				//return Arrays.asList(extract.getText().split(" "));
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else { // is not a docx
			try {
				fis = new FileInputStream(resume);
				HWPFDocument doc = new HWPFDocument(fis);
				WordExtractor extractor = new WordExtractor(doc);
				resumeAsText.setWords(Arrays.asList(extractor.getText().split(" ")));
				resumeAsText.setResumeContent(extractor.getText());
				//return Arrays.asList(extractor.getText().split(" "));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resumeAsText;
	}
}
