package com.example.angularspringboot.service;

import com.example.angularspringboot.errorhandling.UserNotFoundException;
import com.example.angularspringboot.model.Employee;
import com.example.angularspringboot.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id){ // or use Optional <Employee>
        return employeeRepo.findById(id).orElseThrow(() -> new UserNotFoundException("wasn't found"));
    }

    public Employee saveEmployee(Employee employee)  {
        Optional <Employee> employeeOptn = employeeRepo.findEmployeeByCode(employee.getEmployeeCode());
        if(employeeOptn.isPresent()){
            throw new IllegalStateException("employee already exists");
        }
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    //https://dzone.com/articles/how-does-spring-transactional
    //https://www.journaldev.com/7655/spring-orm-example-jpa-hibernate-transaction
    @Transactional // ?name={}&
    public Employee updateEmployeeById(Long id, String name, String email, String jobTitle){
       Employee employee = employeeRepo.findById(id).orElseThrow(() -> new IllegalStateException("Employee doesn't exist"));

        if (name != null && name.length()>0 && !Objects.equals(employee.getName(),name)){
            employee.setName(name);
        }

        if (email != null && email.length()>0 && !Objects.equals(employee.getEmail(),email)){
            employee.setEmail(email);
        }

        if (jobTitle != null && jobTitle.length()>0 && !Objects.equals(employee.getJobTitle(),jobTitle)){
            employee.setJobTitle(jobTitle);
        }
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void deleteEmployeeById(Long id){
        boolean exist = employeeRepo.existsById(id);
        if(!exist){
            throw new IllegalStateException("Employee with this id doesnt exist");
        }
        employeeRepo.deleteById(id);
    }

    public void deleteEmployee(Employee employee){
        boolean exist = employeeRepo.existsById(employee.getId());
        if(!exist){
            throw new IllegalStateException("Employee doesn't exists");
        }
        employeeRepo.delete(employee);
    }
}
