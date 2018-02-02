package com.threezebra.onesimple.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.threezebra.onesimple.dto.FileInfo;
import com.threezebra.service.EmployeeExportService;
import com.threezebra.service.ExcelReaderService;
import com.threezebra.service.ExcelWriterService;

@RestController
public class DownloadManagerController {
	@Autowired
	ExcelWriterService excelWriterService;
	@Autowired
	ExcelReaderService excelReaderService;
	@Autowired
	EmployeeExportService employeeExportService;
	private static String UPLOADED_FOLDER = "D://temp//";
	// @Autowired
	// ServletContext context;
	Logger logger = LoggerFactory.getLogger("DownloadManagerController.class");

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET, produces = "application/excel")
	public ResponseEntity<byte[]> downloadExcel() {

		ResponseEntity<byte[]> response = null;
		try {
			String filename = "Security Configuration Manager data.xlsx";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/excel"));
			headers.setContentDispositionFormData(filename, filename);
			byte[] data = excelWriterService.generateExcel();
			response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value = "/downloadEmployeeList", method = RequestMethod.GET, produces = "application/excel")
	public ResponseEntity<byte[]> downloadEmployees() {

		ResponseEntity<byte[]> response = null;
		try {
			String filename = "Employee  Data.xlsx";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/excel"));
			headers.setContentDispositionFormData(filename, filename);
			byte[] data = employeeExportService.generateExcel();
			response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "/uploadExcel", headers=("content-type=multipart/*"), method = RequestMethod.POST)
	 public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile) {
		
		
	  FileInfo fileInfo = new FileInfo();
	  HttpHeaders headers = new HttpHeaders();
	  if (!inputFile.isEmpty()) {
	   try {
	    String originalFilename = inputFile.getOriginalFilename();
	    File destinationFile = new File(UPLOADED_FOLDER+  File.separator + originalFilename);
	    inputFile.transferTo(destinationFile);
	    fileInfo.setFileName(destinationFile.getPath());
	    fileInfo.setFileSize(inputFile.getSize());
	    headers.add("File Uploaded Successfully - ", originalFilename);
	    excelReaderService.read();
	    return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
	   } catch (Exception e) {    
	    return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
	   }
	  }else{
	   return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
	  }
	 }

}
