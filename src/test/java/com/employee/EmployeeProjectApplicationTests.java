package com.employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeServiceImpl;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeProjectApplicationTests {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	private Employee employee;
	
	@DisplayName("Testing for save method")
	@Test
	public void whenSaveEmployee(){
		Employee employee= new Employee(987,"Dharani","Pokuri",31,765);
		given(employeeRepository.save(employee)).willReturn(employee);
		System.out.println(employeeRepository);
		System.out.println(employeeService);
		String savedEmployeeDetails=employeeService.saveEmployeeDetails(employee);
		System.out.println(savedEmployeeDetails);
		assertThat(savedEmployeeDetails).isNotNull();
	}
	
	@DisplayName("Testing for save method for exception")
	@Test
	public void whenSaveEmployeeExc() {
		given(employeeRepository.save(employee)).willReturn(employee);
		System.out.println(employeeRepository);
		System.out.println(employeeService);
		
		org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () ->{
			employeeService.saveEmployeeDetails(employee);
		});
	}
	
	@DisplayName("Testing for getAllEmployee Details")
	@Test
	public void whenGetAllEmployee() {
		Employee employee= new Employee(987,"Dharani","Pokuri",31,765);
		Employee employee1=new Employee(986,"Amisha","Shaik",22,560);
		given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));
		List<Employee> employeeList=employeeService.getEmployeeDetails();
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(2);
	}
	
	@DisplayName("Testing for get employee method exception")
	@Test
	public void whenGetAllEmployeeExc() {
		org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () ->{
			employeeService.getEmployeeDetails();
		});
		
	}
	
	
    @DisplayName("Testing for update method")
    @Test
    public void whenUpdateEmployee() {
    	Employee employee= new Employee(987,"Dharani","Pokuri",31,765);
    	given(employeeRepository.save(employee)).willReturn(employee);
    	employee.setAge(30);
    	employee.setFirstName("Hema");
    	
    	Employee updatedEmployee=employeeService.addEmp(employee);
    	
    	assertThat(updatedEmployee.getAge()).isEqualTo(30);
    	assertThat(updatedEmployee.getFirstName()).isEqualTo("Hema");
    }
    
    @DisplayName("Testing for delete method")
    @Test
    public void whenDeleteEmployee() {
    	long empid=987;
    	willDoNothing().given(employeeRepository).deleteById(empid);
    	employeeService.deleteEmployeeById(empid);
    	verify(employeeRepository,times(1)).deleteById(empid);
    }

}
