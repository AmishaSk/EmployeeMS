package com.employee.service;
import java.util.List;
import com.employee.entity.Employee;
public interface EmployeeService {
	String saveEmployeeDetails(Employee employee);
	List<Employee> getEmployeeDetails();
	void deleteEmployeeById(long id);
	Employee addEmp(Employee e);
	
}
