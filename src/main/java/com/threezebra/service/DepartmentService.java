package com.threezebra.service;

import java.util.ArrayList;
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
	@Autowired
	UnitService unitService;

	
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	

	public void deleteDepartment(Department dept) {
		departmentRepository.delete(dept);
	}

	public Department save(String name,List<Unit> unit){
		Department department=new Department();
		department.setId(System.nanoTime());
		department.setUnit(unit);
		department.setCheckFlag("TRUE");
		department.setName(name);
		departmentRepository.save(department);
		return department;
	}
	public Department update(Department department,String name,Unit  unit) {
			List<Unit> unitlst=department.getUnit();
			List<String> untname=new ArrayList<>();
			for(Unit unitobj:unitlst){
				untname.add(unitobj.getName());
			}
			if(!(untname.contains(unit.getName()))){
				unitlst.add(unit);
			}
			
			department.setUnit(unitlst);
			department.setCheckFlag("TRUE");
			department.setName(name);
			departmentRepository.save(department);
		
			return department;
		
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



	public Department updateDepartment(Department departmentobj, String name, List<Unit> unitList) {
		departmentobj.setUnit(unitList);
		departmentobj.setName(name);
		departmentRepository.save(departmentobj);
		return departmentobj;
		
	}
}
