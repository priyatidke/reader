package io.litmusblox.aiml.resumeparser.reader;

import java.io.File;
import java.util.List;

import io.litmusblox.aiml.resumeparser.domain.ResumeAsText;

public interface ResumeReader {
	
	public ResumeAsText getWordFromFile(File resume);

}
