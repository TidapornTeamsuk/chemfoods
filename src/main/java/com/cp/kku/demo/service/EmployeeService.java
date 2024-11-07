package com.cp.kku.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.kku.demo.IEmployeeService;
import com.cp.kku.demo.model.Employee;
import com.cp.kku.demo.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService{
	
	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public List<Employee> findAll(){
		return (List<Employee>) repository.findAll();
	}
	
	@Override
	public void save(Employee e) {
		repository.save(e);
	}
	
	@Override
	public Employee findById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	public void delete(int id) {
		repository.deleteById(id);
	}

	public EmployeeRepository getRepository() {
		return repository;
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	public EmployeeService(EmployeeRepository repository) {
		super();
		this.repository = repository;
	}

}
