package com.threezebra.onesimple.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.EmpDetail;
import com.threezebra.service.DistributionGroupService;
import com.threezebra.service.EmployeeService;

@RestController
public class DistributionListController {

	@Autowired
	EmployeeService empDetailService;
	@Autowired
	DistributionGroupService distributionGroupService;

	@RequestMapping(value = "/getMasterDistributionList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, List<EmpDetail>>> getMasterDistributionList() {
		List<DistributionGroup> distroList = distributionGroupService.findAll();
		List<EmpDetail> EmpDetaillist = empDetailService.findAll();
		List<EmpDetail> empdistronlistwise = new ArrayList<>();
		List<String> distrodeptName=new ArrayList<>();
		List<String> distroName=new ArrayList<>();
		Map<String, List<EmpDetail>> empDistroMap = new HashMap<>();
		for (DistributionGroup distributionGroup : distroList) {
			if (distributionGroup.getDefaultvalue().equals("true")) {
				distroName.add(distributionGroup.getName());
				distrodeptName.add(distributionGroup.getDepartment().getName());
			}
		   }
		
		
		for(EmpDetail empDetail:EmpDetaillist) {
		for(int i=0;i<distrodeptName.size();i++) {
		   if(distrodeptName.get(i).equals(empDetail.getDepartment().getName())) {
			   empdistronlistwise.add(empDetail);
		      }
		   empDistroMap.put(distroName.get(i), empdistronlistwise);
		    }
		 }
			
		
	
		return new ResponseEntity<>(empDistroMap, HttpStatus.OK);
	}

}
