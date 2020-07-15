package io.litmusblox.aiml.resumeparser.domain;

public class MatchedKeywords {

	String resumeName;
	String MatchedKeywordList;
	ResumeAsText resumeAsText;
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getMatchedKeywordList() {
		return MatchedKeywordList;
	}
	public void setMatchedKeywordList(String matchedKeywordList) {
		MatchedKeywordList = matchedKeywordList;
	}
	public ResumeAsText getResumeAsText() {
		return resumeAsText;
	}
	public void setResumeAsText(ResumeAsText resumeAsText) {
		this.resumeAsText = resumeAsText;
	}
	public MatchedKeywords(String resumeName, String matchedKeywordList, ResumeAsText resumeAsText) {
		super();
		this.resumeName = resumeName;
		MatchedKeywordList = matchedKeywordList;
		this.resumeAsText = resumeAsText;
	}
	public MatchedKeywords() {
		super();
	}

	
	
}
