package com.cp.kku.demo;

import java.util.List;

import com.cp.kku.demo.model.Employee;

public interface IEmployeeService {
	
	List<Employee> findAll();
	
	void save(Employee a);

	Employee findById(int id);
	
	void delete(int id);

}
