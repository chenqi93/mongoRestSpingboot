package com.threezebra.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExcelReaderService {
	Logger logger = LoggerFactory.getLogger("ExcelReaderService.class");
	private static final String FILE_NAME = "D:\\temp\\ExcelTest.xlsx";

	public void read() {

		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.rowIterator();
			int rowcount = 0;
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				if (rowcount >=1) {
					String unitname = currentRow.getCell(0).getStringCellValue();
					String department = currentRow.getCell(1).getStringCellValue();
					String userType = currentRow.getCell(2).getStringCellValue();
					String jobRole=currentRow.getCell(4).getStringCellValue();
					Set<String> unitSet=new HashSet<>();
					unitSet.add(unitname);
					
					logger.info("%%%%----" + unitname + "---%%%%%");
					logger.info("%%%%----" + department + "---%%%%%");
					logger.info("%%%%----" + userType + "---%%%%%");
					logger.info("%%%%----" + jobRole + "---%%%%%");
				}
				rowcount++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
