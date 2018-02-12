package com.threezebra.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.Department;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;

@Service
public class ExcelReaderService {
	Logger logger = LoggerFactory.getLogger("ExcelReaderService.class");
	private static final String FILE_NAME = "D:\\temp\\";
    @Autowired
  private JobTitleService jobTitleService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private BaseLocationService baseLocationService;
    @Autowired
    private UserTypeService userTypeService;
    @Autowired
    private JobRoleService jobRoleService;
	public void read(String filename) {

		try {
			FileInputStream excelFile = new FileInputStream(FILE_NAME+File.separator +filename);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.rowIterator();
			int rowcount = 0;
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				if (rowcount >=1) {
					
					String baselocation = currentRow.getCell(0).getStringCellValue();
					String unit = currentRow.getCell(1).getStringCellValue();
					String department = currentRow.getCell(2).getStringCellValue();
					String userType = currentRow.getCell(3).getStringCellValue();
					String userTypeDesc=currentRow.getCell(4).getStringCellValue();
					String jobRole = currentRow.getCell(5).getStringCellValue();
					BaseLocation blocation=baseLocationService.findByName(baselocation);
					logger.info("%%%%----" + unit + "---%%%%%");
					Unit unitObj=unitService.update(unit,blocation);
					List<Unit> unitlist=new ArrayList<>();
					Department departmentObj=departmentService.findbyName(department);
					if(null==departmentObj) {
						logger.info("%%%%----" + baselocation + "---%%%%%");
					     unitlist.add(unitObj);
						departmentService.save(department, unitlist);
					}
					else {
						logger.info("%%%%----" + baselocation + "---%%%%%");
						departmentService.update(departmentObj, department, unitObj);
					}
					UserType userTypeObj=userTypeService.findByUserType(userType);
					if(null==userTypeObj) {
						userTypeObj=userTypeService.save(userType, userTypeDesc, unitlist);
						logger.info("%%%%----" + userTypeObj.getName() + "---%%%%%");
					}
					else {
						userTypeService.addUnit(userTypeObj, unitObj);
						logger.info("%%%%----" + userTypeObj.getName() + "---%%%%%");
					}
					JobRole jobRoleObj=jobRoleService.findByName(jobRole);
					if(null==jobRoleObj) {
						jobRoleObj=jobRoleService.save(jobRole,userTypeObj,unitlist,"TRUE");
					}
					else {
						logger.info("%%%%----" + jobRoleObj.getName()+"%%%%"+jobRoleObj.getCheckFlag()+ "---%%%%%");
						jobRoleObj=jobRoleService.update(jobRoleObj, unitObj, userTypeObj);
					}
					
					
					
				   
			
				
					
				
				}
				rowcount++;

			}
			
			Sheet datatypeSheet1 = workbook.getSheetAt(1);
			Iterator<Row> iterator1 = datatypeSheet1.rowIterator();
			int rowcount1 = 0;
			while (iterator1.hasNext()) {

				Row currentRow = iterator1.next();
				if (rowcount1 >=1) {
					String unitname = currentRow.getCell(0).getStringCellValue();
					String department = currentRow.getCell(1).getStringCellValue();
					String jobTitle = currentRow.getCell(2).getStringCellValue();
					Department departmentObj=departmentService.findbyName(department);
					jobTitleService.save(jobTitle,departmentObj);
					
				    logger.info("%%%%----" + unitname + "---%%%%%");
					logger.info("%%%%----" + department + "---%%%%%");
					logger.info("%%%%----" + jobTitle + "---%%%%%");
				
				}
				rowcount1++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
