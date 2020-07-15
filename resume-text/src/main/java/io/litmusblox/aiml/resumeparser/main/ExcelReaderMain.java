package io.litmusblox.aiml.resumeparser.main;

import java.io.File;
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
import io.litmusblox.aiml.resumeparser.reader.DocResumeReader;
import io.litmusblox.aiml.resumeparser.reader.PDFResumeReader;
import io.litmusblox.aiml.resumeparser.reader.RTFResumeReader;
import io.litmusblox.aiml.resumeparser.reader.ResumeKeywordsAnalyser;
import io.litmusblox.aiml.resumeparser.reader.TextResumeReader;
import io.litmusblox.aiml.resumeparser.repository.KeywordRepository;
import io.litmusblox.aiml.resumeparser.repository.KeywordRepositoryImpl;
import io.litmusblox.aiml.resumeparser.repository.ResultWriter;
import io.litmusblox.aiml.resumeparser.repository.ResultWriterImpl;
import io.litmusblox.aiml.resumeparser.util.XMLWriter;

public class ExcelReaderMain {

	private XMLWriter xmlWrite= new  XMLWriter();
	private PDFResumeReader pdfResumeReader = new PDFResumeReader();
	private TextResumeReader textResumeReader = new TextResumeReader();
	private DocResumeReader docResumeReader= new DocResumeReader();
	private RTFResumeReader rtfResumeReader= new RTFResumeReader();
	private KeywordRepository keywordRepository = new KeywordRepositoryImpl();
    private ResultWriter resultWriter= new ResultWriterImpl();
	
	public static void main(String[] args) throws IOException {
		//String maindirpath = "/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/Manuf_Resume/Manuf Resumes- Khresst";
		//String excelPath= "/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/Manuf_Keywords/Manuf_KeywordsInput.xlsx";
	    //String maindirpath = "/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/Sales/Sales Resume- 100";
		//String excelPath= "/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/Sales/sales_Keywords.xlsx";
		String maindirpath = "/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/IT_Resumes/ITSOMEfile";
		String excelPath= "/Users/priyavivekbhandarkar/Desktop/LITMUSBLOX/IT_Resumes/IT_keywords (1).xlsx";
				
		
		ExcelReaderMain excelReaderMain= new ExcelReaderMain();
		ResumeKeywordsAnalyser resumeKeywordsAnalyser = new ResumeKeywordsAnalyser();
		List<MatchedKeywords> result=resumeKeywordsAnalyser.getMatchedResumeKewordResult(maindirpath, excelPath);
		
		excelReaderMain.saveResultInDatabase(result);
	}

    public void saveResultInDatabase(List<MatchedKeywords> result){
    	for(MatchedKeywords matchedKeywords:result ){
    		this.resultWriter.storeResult(matchedKeywords.getResumeName(), matchedKeywords.getMatchedKeywordList(), matchedKeywords.getResumeAsText().getResumeContent());
    	}
    	
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
		}

		return null;

	}

	public List< KeywordResult> getResumeKewordResult(String directoryPath) {
		List< KeywordResult> fileKeywordResult = new ArrayList< KeywordResult>();
		List<File> filesList = this.getFiles(directoryPath);
		Map<String, ResumeAsText> fileWords = new HashMap<String, ResumeAsText>();
		for (File resume : filesList) {
			ResumeAsText word = this.getWordsFromFile(resume);
			fileWords.put(resume.getName(), word);
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

