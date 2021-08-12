package com.example.angularspringboot.repository;

import com.example.angularspringboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e where e.employeeCode =?1")
    Optional<Employee> findEmployeeByCode(String employeeCode);

}
