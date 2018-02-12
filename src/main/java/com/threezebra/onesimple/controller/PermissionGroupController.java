package com.threezebra.onesimple.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.threezebra.domain.EmpDetail;
import com.threezebra.domain.PermissionGroup;
import com.threezebra.service.EmployeeService;

@RestController
@RequestMapping("permission")
public class PermissionGroupController {
	@Autowired
	EmployeeService emplyoeeService;
	
	@RequestMapping(value = "/findGroupinEmployee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findGroupinEmployee() {
		 List<EmpDetail> employeelist=emplyoeeService.findAll();
		 List<String> permissionList=new ArrayList<>();
		 for(EmpDetail emp:employeelist) {
			 PermissionGroup group= emp.getPermissionGroup();
			 if(null!=group){
			 permissionList.add(group.getName());
			 }
		 }
		return new ResponseEntity<>(permissionList, HttpStatus.OK);
	}
	
	

}
