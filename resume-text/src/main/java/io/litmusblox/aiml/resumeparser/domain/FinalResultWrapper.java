package io.litmusblox.aiml.resumeparser.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FinalResultWrapper {

	private List<KeywordResult> result;

	public List<KeywordResult> getResult() {
		return result;
	}

	public void setResult(List<KeywordResult> result) {
		this.result= new ArrayList<KeywordResult>();
		for(KeywordResult keywordResult : result){
			if(keywordResult.getCount() !=0)
				this.result.add(keywordResult);
		}
		
	}
	
}
