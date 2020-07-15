package io.litmusblox.aiml.resumeparser.domain;

import java.util.List;

public class ResumeAsText {

	private String resumeName;
	private String resumeContent;
	private List<String> words;
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getResumeContent() {
		return resumeContent;
	}
	public void setResumeContent(String resumeContent) {
		this.resumeContent = resumeContent;
	}
	public List<String> getWords() {
		return words;
	}
	public void setWords(List<String> words) {
		this.words = words;
	}
	public ResumeAsText(String resumeName, String resumeContent, List<String> words) {
		super();
		this.resumeName = resumeName;
		this.resumeContent = resumeContent;
		this.words = words;
	}
	public ResumeAsText() {
		super();
	}
	
}
