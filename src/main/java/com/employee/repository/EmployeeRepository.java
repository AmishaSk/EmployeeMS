package com.employee.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.employee.entity.Employee;
@Repository
@EnableJpaRepositories

public interface EmployeeRepository extends JpaRepository<Employee, Long>  {

}
