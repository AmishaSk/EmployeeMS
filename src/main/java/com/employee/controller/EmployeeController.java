package com.employee.controller;
import java.util.List;

import javax.servlet.http.HttpSession; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@PostMapping("save")
	public ResponseEntity<String> addEmploye(@RequestBody Employee employee) {
		String response = employeeService.saveEmployeeDetails(employee);
		if(response == null || response.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not saved");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<Employee>> getAllEmployeeDetails() {
		List<Employee> response = employeeService.getEmployeeDetails();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	@DeleteMapping("/deleteEmployee/{empid}")
	public String deleteEmployee(@PathVariable (value = "empid") long id) {
		this.employeeService.deleteEmployeeById(id);
		return "Deleted Successfully";
	}
	
	@PutMapping("/update")
	public String updateEmp(@RequestBody Employee e, HttpSession session) {
		employeeService.addEmp(e);
		session.setAttribute("msg", "Emp Data Update Sucessfully..");
		return "Updated Successfully!!";
	}
}
	
	


