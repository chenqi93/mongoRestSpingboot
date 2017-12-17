package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.Unit;
import com.threezebra.repository.UnitRepository;

@Service
public class UnitService {
	@Autowired
	private ApplicationConfigurationProperties configurationProperties;
	@Autowired
	private UnitRepository unitRepository;

	public Unit save(Unit unit) {
		return unitRepository.save(unit);
	}

	public List<Unit> findAll() {
		return unitRepository.findAll();
	}

	public Unit findbyName(String unitName) {
		return unitRepository.findByName(unitName);
	}

	public Unit update(String name, BaseLocation baselocation) {
       
		Unit unitobj = unitRepository.findByName(name);
		if (unitobj!=null) {
			unitobj.setBaselocation(baselocation);
			unitobj.setName(name);
			unitRepository.save(unitobj);
			return unitobj;
			
		} else {
			unitobj = new Unit();
			unitobj.setId(System.nanoTime());
			unitobj.setName(name);
			unitobj.setBaselocation(baselocation);
			unitRepository.save(unitobj);
			return unitobj;
		  }
			
		}
	public Unit findbyId(long unitName) {
		return unitRepository.findById(unitName);
	}
}
