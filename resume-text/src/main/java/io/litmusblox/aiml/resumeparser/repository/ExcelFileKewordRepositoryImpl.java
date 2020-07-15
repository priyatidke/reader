package io.litmusblox.aiml.resumeparser.repository;

import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ExcelFileKewordRepositoryImpl implements ExcelFileKewordRepository {
    Integer columnIndex=0; //excel column no.
	
	@Override
	public Set<String> getKeywordsFromExcel(String excelPath) {
		List<String> keyword= new ArrayList<String> ();
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		try
        {
            FileInputStream file = new FileInputStream(new File(excelPath));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);//sheet number
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    if(cell.getColumnIndex()==columnIndex){
                    switch (cell.getCellType()) 
                    {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "t");
                            keyword.add(cell.getStringCellValue());
                            break;
                    }
                    }
                }
                System.out.println("");
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
		Set<String> hSet = new HashSet<String>(keyword); 
        hSet.addAll(keyword); 
		return hSet;
	}

}
