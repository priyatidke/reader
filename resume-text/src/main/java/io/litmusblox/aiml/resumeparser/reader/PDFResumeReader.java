package io.litmusblox.aiml.resumeparser.reader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import io.litmusblox.aiml.resumeparser.domain.ResumeAsText;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDFResumeReader implements ResumeReader {
	//===================Unnecessary code  starts here  =====================


	public static void main(String[] args) throws IOException {

		try (PDDocument document = PDDocument
				.load(new File("/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/Resume/Priya-Tidke-2019-Resume2.pdf"))) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);
				System.out.println("\nText:" + pdfFileInText);
				String lines[] = pdfFileInText.split(" ");

				ArrayList<String> arr = new ArrayList<String>(Arrays.asList(lines));
				// for(int x=0;x<arr.size();x++){
				// System.out.println("Data :"+arr.get(x));
				// split by whitespace
				for (String line : lines) {
					System.out.println("Word :" + line);
				}

			}

		}

	}
	//===================Unnecessary code  ends here  =====================

	@Override
	public ResumeAsText getWordFromFile(File resume) {
		ResumeAsText resumeAsText= new ResumeAsText();
		resumeAsText.setResumeName(resume.getName());
		List<String> lines = null;
		try (PDDocument document = PDDocument.load(resume)) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);
				resumeAsText.setResumeContent(pdfFileInText);
				//System.out.println("\nText:" + pdfFileInText);
				lines = Arrays.asList(pdfFileInText.split(" "));
				resumeAsText.setWords(lines);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resumeAsText;
	}
}
// https://www.mkyong.com/java/pdfbox-how-to-read-pdf-file-in-java/
