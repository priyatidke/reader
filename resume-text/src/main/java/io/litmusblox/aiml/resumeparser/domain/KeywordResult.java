package io.litmusblox.aiml.resumeparser.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KeywordResult {
	private String fileName;
	private String Keyword;
	private Integer count;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getKeyword() {
		return Keyword;
	}
	public void setKeyword(String keyword) {
		Keyword = keyword;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "KeywordResult [fileName=" + fileName + ", Keyword=" + Keyword + ", count=" + count + "]";
	}


}
