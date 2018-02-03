package com.threezebra.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.Department;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.JobTitle;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;

@Service
public class ExcelWriterService {
	Logger log = LoggerFactory.getLogger(ExcelWriterService.class);

	@Autowired
	EmployeeService employeeService;
	@Autowired
	UnitService unitService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	UserTypeService userTypeService;
	@Autowired
	JobRoleService jobRoleService;
	@Autowired
	JobTitleService jobTitleService;

	public byte[] generateExcel() throws IOException {
	
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet1 = workbook.createSheet("Security Configuration Manager Data");
		XSSFSheet sheet2 = workbook.createSheet("Department Wise Job Title");
		// List<EmpDetail> employees = employeeService.findAll();

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.ORANGE.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		font.setBold(true);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		// create header row
		Row header = ((org.apache.poi.ss.usermodel.Sheet) sheet1).createRow(0);
		header.createCell(0).setCellValue("Unit");
		header.getCell(0).setCellStyle(style);
		header.createCell(1).setCellValue("Department");
		header.getCell(1).setCellStyle(style);
		header.createCell(2).setCellValue("UserType");
		header.getCell(2).setCellStyle(style);
		header.createCell(3).setCellValue("JobRole");
		header.getCell(3).setCellStyle(style);
		int rowCountjobrole = 1;
		int rowCountjobtitle = 1;
		List<Unit> unitlist = unitService.findAll();
		for (Unit unit : unitlist) {
			List<Department> departmentList = departmentService.findByUnit(unit);
			List<UserType> usertypelst = userTypeService.findByUnit(unit);
			for (UserType usertype : usertypelst) {
				List<JobRole> listJobRole = jobRoleService.findByUserType(usertype);
				for (Department department : departmentList) {
					for (JobRole jobRole : listJobRole) {
							Row unitRow = ((org.apache.poi.ss.usermodel.Sheet) sheet1).createRow(rowCountjobrole++);
							unitRow.createCell(0).setCellValue(unit.getName());
							unitRow.createCell(1).setCellValue(department.getName());
							unitRow.createCell(2).setCellValue(usertype.getName());			
							unitRow.createCell(3).setCellValue(jobRole.getName());
						

					}
				}
			}
		}
		Row header2 = ((org.apache.poi.ss.usermodel.Sheet) sheet2).createRow(0);
		header2.createCell(0).setCellValue("Unit");
		header2.getCell(0).setCellStyle(style);
		header2.createCell(1).setCellValue("Department");
		header2.getCell(1).setCellStyle(style);
		header2.createCell(2).setCellValue("JobTitle");
		header2.getCell(2).setCellStyle(style);
		for (Unit unit : unitlist) {
			List<Department> departmentList = departmentService.findByUnit(unit);
			for (Department department : departmentList) {
				List<JobTitle> jobTitlelist = jobTitleService.findByDepartment(department);
				 for (JobTitle jobTitle : jobTitlelist) {
						Row unitRow1 = ((org.apache.poi.ss.usermodel.Sheet) sheet2).createRow(rowCountjobtitle++);
						unitRow1.createCell(0).setCellValue(unit.getName());
						unitRow1.createCell(1).setCellValue(department.getName());	
						unitRow1.createCell(2).setCellValue(jobTitle.getName());
					}
			}
		}
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
		} finally {
			bos.close();
		}
		byte[] bytes = bos.toByteArray();
		return bytes;

	}
}
