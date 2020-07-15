package io.litmusblox.aiml.resumeparser.repository;

public interface ResultWriter {
	void storeResult(String filename, String resultCommaseperated, String resumeContent);

}
