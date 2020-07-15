package io.litmusblox.aiml.resumeparser.reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;

import io.litmusblox.aiml.resumeparser.domain.ResumeAsText;

public class RTFResumeReader implements ResumeReader {

	@Override
	public ResumeAsText getWordFromFile(File resume) {
		ResumeAsText resumeAsText= new ResumeAsText();
		resumeAsText.setResumeName(resume.getName());
		// read rtf from file
		JEditorPane p = new JEditorPane();
		p.setContentType("text/rtf");
		EditorKit rtfKit = p.getEditorKitForContentType("text/rtf");
		try {
			rtfKit.read(new FileReader(resume), p.getDocument(), 0);
		} catch (IOException | BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rtfKit = null;

		// convert to text
		EditorKit txtKit = p.getEditorKitForContentType("text/plain");
		Writer writer = new StringWriter();
		try {
			txtKit.write(writer, p.getDocument(), 0, p.getDocument().getLength());
		} catch (IOException | BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String documentText = writer.toString();
		
		resumeAsText.setResumeContent(documentText);
		resumeAsText.setWords(Arrays.asList(documentText.split(" ")));
		return resumeAsText;
	}



}
