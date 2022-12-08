
package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;


@Service
public  class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	

	@Override
	public String saveEmployeeDetails(Employee employee) {
		Employee response = employeeRepository.save(employee);
		if (response == null) {
			//return null;
			throw new RuntimeException("Null");
			//throw new ResourceNotFoundException();
		}
		return "Data is saved successfully";
	}

	@Override
	public List<Employee> getEmployeeDetails() {
		List<Employee> response = employeeRepository.findAll();
		if (response.isEmpty() || response == null) {
			throw new RuntimeException("Data is empty");
		}
		return response;
	}
	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}
	/*
	@Override
	public void addEmp(Employee e) {
		employeeRepository.save(e);
	}
	*/
	
	@Override
	public Employee addEmp(Employee e) {
		return employeeRepository.save(e);
	}
	
	
}
	