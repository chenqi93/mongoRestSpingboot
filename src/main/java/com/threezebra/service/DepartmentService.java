package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.Department;
import com.threezebra.domain.Unit;
import com.threezebra.repository.DepartmentRepository;

@Service("departmentService")
public class DepartmentService {
	@Autowired
	private ApplicationConfigurationProperties configurationProperties;
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department save(Department department) {
		return departmentRepository.save(department);
	}

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	

	public void deleteDepartment(Department dept) {
		departmentRepository.delete(dept);
	}

	public Department update(String name,List<Unit> unitlist) {

		Department department = departmentRepository.findByName(name);
		if (null != department) {
			List<Unit> unitlst=department.getUnit();
			
			
			department.setUnit(unitlist);
			department.setName(name);
			departmentRepository.save(department);
			return department;
		} else {
			department = new Department();
			department.setId(System.nanoTime());
			department.setName(name);
			department.setUnit(unitlist);
			departmentRepository.save(department);
			return department;
		}
	}

	public List<Department> findByUnit(Unit unit) {
		
		return departmentRepository.findByUnit(unit);
	}

	public void deleteReferredDepartmentUnit(Department department, Unit unit) {
		List<Unit> unitlist = department.getUnit();
		if (null != unitlist) {
			for (Unit unitobj : unitlist) {
				if (unitobj.getName().equals(unit.getName())) {
					unitlist.remove(unit);
				}
			}
			department.setUnit(unitlist);

		}
	}

	public Department findbyName(String department) {
		return departmentRepository.findByName(department);
	}
	
	public Department findById(long id) {
		return departmentRepository.findById(id);
	}
}
