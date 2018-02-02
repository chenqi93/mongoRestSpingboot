package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.CallSheet;
import com.threezebra.repository.CallSheetRepository;

@Service
public class CallSheetService {
	
	@Autowired
	CallSheetRepository callSheetRepository;

	public CallSheet save(CallSheet callSheet) {
		callSheetRepository.save(callSheet);
		return callSheet;
	}

	public void deleteAll() {
	  callSheetRepository.deleteAll();
		
	}

	public List<CallSheet> findAll() {
		return callSheetRepository.findAll();
	}

}
