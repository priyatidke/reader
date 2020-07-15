package io.litmusblox.aiml.resumeparser.reader;

import java.io.File;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.JAXBException;

import io.litmusblox.aiml.resumeparser.domain.KeywordResult;
import io.litmusblox.aiml.resumeparser.domain.MatchedKeywords;
import io.litmusblox.aiml.resumeparser.domain.ResumeAsText;
import io.litmusblox.aiml.resumeparser.repository.ExcelFileKewordRepository;
import io.litmusblox.aiml.resumeparser.repository.ExcelFileKewordRepositoryImpl;
import io.litmusblox.aiml.resumeparser.repository.KeywordRepository;
import io.litmusblox.aiml.resumeparser.repository.KeywordRepositoryImpl;
import io.litmusblox.aiml.resumeparser.util.XMLWriter;

public class ResumeKeywordsAnalyser {

	private XMLWriter xmlWrite= new  XMLWriter();
	private PDFResumeReader pdfResumeReader = new PDFResumeReader();
	private TextResumeReader textResumeReader = new TextResumeReader();
	private DocResumeReader docResumeReader= new DocResumeReader();
	private RTFResumeReader rtfResumeReader= new RTFResumeReader();
	private KeywordRepository keywordRepository= new KeywordRepositoryImpl();
	private ExcelFileKewordRepository excelFileKewordRepository= new ExcelFileKewordRepositoryImpl();

	public static void main(String[] args) throws IOException {
		String maindirpath = "/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/Resume";
		String resultFile = "/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/ResumeResult.xml";
		ResumeKeywordsAnalyser resumeKeywordsAnalyser = new ResumeKeywordsAnalyser();
		List<KeywordResult> result=resumeKeywordsAnalyser.getResumeKewordResult(maindirpath);
		resumeKeywordsAnalyser.writeResult(resultFile, result);
	}


	
	public void writeResult(String fileLocation,List<KeywordResult> result ){
		try {
			this.xmlWrite.writeToFile(fileLocation, result);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<File> getFiles(String folderPath) {
		File maindir = new File(folderPath);
		if (maindir.exists() && maindir.isDirectory()) {
			return Arrays.asList(maindir.listFiles());
		}
		return null;

	}

	public Set<String> getKeywords() {
		return this.keywordRepository.getKeywords();

	}
	
	public Set<String> getExcelKeywords(String excelPath) {
		return this.excelFileKewordRepository.getKeywordsFromExcel(excelPath);

	}

	public ResumeAsText getWordsFromFile(File resume) {
		String type_txt = ".txt";
		String type_pdf = ".pdf";
		String type_doc = ".doc";
		String type_docx = ".docx";
		String type_rtf = ".rtf";

		if (resume.isFile() && resume.getName().endsWith(type_txt)) {
			System.out.println("TEXT  FILE ===>" + resume.getName());
			return this.textResumeReader.getWordFromFile(resume);

		} else if (resume.isFile() && resume.getName().endsWith(type_pdf)) {
			System.out.println("PDF  FILE ===>" + resume.getName());
			return this.pdfResumeReader.getWordFromFile(resume);
		} else if (resume.isFile() && ( resume.getName().endsWith(type_doc) || resume.getName().endsWith(type_docx))) {
			System.out.println("DOC/DOCX  FILE ===>" + resume.getName());

			return this.docResumeReader.getWordFromFile(resume);

		}  else if (resume.isFile() && resume.getName().endsWith(type_rtf)) {
			System.out.println("RTF  FILE ===>" + resume.getName());

			return this.rtfResumeReader.getWordFromFile(resume);
		} else {
			System.out.println("file is not present");
			ResumeAsText resumeAsText= new  ResumeAsText("DuMMY TExt","DUMMY text",new ArrayList<String>());
			return resumeAsText;
		}


	}

	public List< KeywordResult> getResumeKewordResult(String directoryPath) {
		List< KeywordResult> fileKeywordResult = new ArrayList< KeywordResult>();
		List<File> filesList = this.getFiles(directoryPath);
		Map<String, ResumeAsText> fileWords = new HashMap<String, ResumeAsText>();
		for (File resume : filesList) {
			ResumeAsText resumeAsText = this.getWordsFromFile(resume);
			fileWords.put(resume.getName(), resumeAsText);
		}

		Set<String> keywords = this.getKeywords();
		for (Entry<String, ResumeAsText> entry : fileWords.entrySet()) {
			if (null != entry.getValue()) {
				for (String keyword : keywords) {
					KeywordResult keywordResult = new KeywordResult();
					keywordResult.setKeyword(keyword);
					keywordResult.setFileName(entry.getKey());
					Integer count = 0;
					for (String word : entry.getValue().getWords()) {
						if (word.equals(keyword)) {
							count++;
						}

					}
					keywordResult.setCount(count);
					fileKeywordResult.add( keywordResult);
				}
			}

		}
		printFileKeywordResult(fileKeywordResult);
		return fileKeywordResult;
	}

	public List< MatchedKeywords> getMatchedResumeKewordResult(String directoryPath, String excelPath) {
		List< MatchedKeywords> matchedKeywordsList = new ArrayList< MatchedKeywords>();
		List<File> filesList = this.getFiles(directoryPath);
		Map<String, ResumeAsText> fileWords = new HashMap<String, ResumeAsText>();
		for (File resume : filesList) {
			ResumeAsText resumeAsText = this.getWordsFromFile(resume);
			fileWords.put(resume.getName(), resumeAsText);
			//System.out.println(resumeAsText.getResumeName()+"----");
			//System.out.println(resumeAsText.getResumeName()+"----"+resumeAsText.getResumeContent().length()+"----"+resumeAsText.getWords().size());
		}

		Set<String> keywords = this.getExcelKeywords(excelPath);
		System.out.println(keywords);
		for (Entry<String, ResumeAsText> entry : fileWords.entrySet()) {
			MatchedKeywords matchedKeywords = new MatchedKeywords();
			matchedKeywords.setResumeName(entry.getKey());
			matchedKeywords.setResumeAsText(entry.getValue());

            List<String> machedKewordList= new ArrayList<>();
			if (null != entry.getValue()) {
				for (String keyword : keywords) {
					if(null !=  entry.getValue().getWords() ){
					for (String word : entry.getValue().getWords()) {
						if (word.equalsIgnoreCase(keyword)) {
							machedKewordList.add(keyword);
						}

					}
					}
				}
			}
			String result= StringUtils.join(machedKewordList, ",");
			matchedKeywords.setMatchedKeywordList(result);
			matchedKeywordsList.add(matchedKeywords);
		}
		return matchedKeywordsList;
		
	}

	
	
	private void printFileKeywordResult(List<KeywordResult> fileKeywordResult) {
		for ( KeywordResult result : fileKeywordResult) {
			if(result.getCount()!=0)
			System.out.println("File Result --> " + result);
		}

		// TODO Auto-generated method stub

	}

	// 1. Read Folder -- > file List
	// for loop
	// 2. File By File words -- > List of words
	// 3. Get all the keyword -- > Map Store
	// file by filec-- forloop
	// filer Map Words with Database
	// Get final Result

}
