package io.litmusblox.aiml.resumeparser.repository;

import java.util.Set;

public interface ExcelFileKewordRepository {

	Set<String> getKeywordsFromExcel(String excelPath);
}
