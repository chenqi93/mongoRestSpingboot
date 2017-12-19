package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.XternalDistributionGroup;
import com.threezebra.repository.XterlDistributionGroupRepository;

@Service
public class XterlDistributionGroupService {

	
	@Autowired
	XterlDistributionGroupRepository xterlDistributionGroupRepository;

	public XternalDistributionGroup save(XternalDistributionGroup XternalDistributionGroup) {
		return xterlDistributionGroupRepository.save(XternalDistributionGroup);
		
	}

	public List<XternalDistributionGroup> findAll() {
		 return xterlDistributionGroupRepository.findAll();
	}

	public XternalDistributionGroup findByName(String name) {
		// TODO Auto-generated method stub
		return xterlDistributionGroupRepository.findByName(name);
	}
}
