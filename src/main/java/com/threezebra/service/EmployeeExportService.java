package com.threezebra.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.EmpDetail;
import com.threezebra.exception.ThreeZebraException;
@Service
public class EmployeeExportService {
	Logger log = LoggerFactory.getLogger(ExcelWriterService.class);

	@Autowired
	EmployeeService employeeService;
	
	public byte[] generateExcel() throws ThreeZebraException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet1 = workbook.createSheet("Employee Data sheet");
		
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.ORANGE.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		font.setBold(true);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
	 
	    Row header = ((org.apache.poi.ss.usermodel.Sheet) sheet1).createRow(0);
		header.createCell(0).setCellValue("Empployee Id");
		header.getCell(0).setCellStyle(style);
		header.createCell(1).setCellValue("firstName");
		header.getCell(1).setCellStyle(style);
		header.createCell(2).setCellValue("lastName");
		header.getCell(2).setCellStyle(style);
		header.createCell(3).setCellValue("SpecialRole");
		header.getCell(3).setCellStyle(style);
		header.createCell(4).setCellValue("Unit");
		header.getCell(4).setCellStyle(style);
		header.createCell(5).setCellValue("Department");
		header.getCell(5).setCellStyle(style);
		header.createCell(6).setCellValue("UserType");
		header.getCell(6).setCellStyle(style);
		header.createCell(7).setCellValue("Job Role");
		header.getCell(7).setCellStyle(style);
		header.createCell(8).setCellValue("JobTitle");
		header.getCell(8).setCellStyle(style);
		header.createCell(9).setCellValue("WorkEmail");
		header.getCell(9).setCellStyle(style);
		header.createCell(10).setCellValue("personalEmail");
		header.getCell(10).setCellStyle(style);
		header.createCell(11).setCellValue("PhoneNumber");
		header.getCell(11).setCellStyle(style);
		header.createCell(12).setCellValue("Distribution Group");
		header.getCell(12).setCellStyle(style);
		header.createCell(13).setCellValue("permission Group");
		header.getCell(13).setCellStyle(style);
		header.createCell(14).setCellValue("isActive");
		header.getCell(14).setCellStyle(style);
		header.createCell(15).setCellValue("Base Location");
		header.getCell(15).setCellStyle(style);
		header.createCell(16).setCellValue("Access Start date");
		header.getCell(16).setCellStyle(style);
		header.createCell(17).setCellValue("Permitted Devices");
		header.getCell(17).setCellStyle(style);
				
		int rowCount = 1;
		
		List<EmpDetail> employeeList = employeeService.findAll();
	
		for (EmpDetail employee : employeeList) {
			Row empRow = ((org.apache.poi.ss.usermodel.Sheet) sheet1).createRow(rowCount++);
			empRow.createCell(0).setCellValue(employee.getId());
			empRow.createCell(1).setCellValue(employee.getFirstName());
			empRow.createCell(2).setCellValue(employee.getLastName());	
			if(null!=employee.getSpecialRole()) {
			empRow.createCell(3).setCellValue(employee.getSpecialRole().getName());	
			 }
			empRow.createCell(4).setCellValue(employee.getUnit().getName());
			empRow.createCell(5).setCellValue(employee.getDepartment().getName());
			empRow.createCell(6).setCellValue(employee.getUserType().getName());
			empRow.createCell(7).setCellValue(employee.getJobRole().getName());
			empRow.createCell(8).setCellValue(employee.getJobTitle().getName());
			empRow.createCell(9).setCellValue(employee.getWorkEmail());
			empRow.createCell(10).setCellValue(employee.getPersonalEmail());
			empRow.createCell(11).setCellValue(employee.getPersonalPhoneNum());
			List<DistributionGroup> distrogroup=employee.getDistributionGroup();
			StringBuilder distributiongroup=new StringBuilder();
			if(null!=distrogroup) {
			for(DistributionGroup distGroup:distrogroup) {
				distributiongroup.append(distGroup.getName()).append("|");
			
			}}
			
			empRow.createCell(12).setCellValue(distributiongroup.toString());
			empRow.createCell(13).setCellValue(employee.getPermissionGroup().getName());
			empRow.createCell(14).setCellValue(employee.getIsActive());
			empRow.createCell(15).setCellValue(employee.getBaseLocation().getName());
			empRow.createCell(16).setCellValue(employee.getAccessStartDate());
			empRow.createCell(17).setCellValue(employee.getPermittedNumDevices());
		}
		
		try {
			workbook.write(bos);
		    } 
		catch(IOException ex) {
			throw new ThreeZebraException(ex);
		}
		finally {
			try {
				bos.close();
			} catch (IOException ex) {
				throw new ThreeZebraException(ex);
			}
		 }
		byte[] bytes = bos.toByteArray();
		return bytes;
		
		}
		
}
