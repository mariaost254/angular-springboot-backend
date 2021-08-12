package com.example.angularspringboot.config;

import com.example.angularspringboot.model.Employee;
import com.example.angularspringboot.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
@EnableJpaRepositories(excludeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = EmployeeRepo.class))
@Lazy
public class EmployeeConfig {

    /*
    *Spring annotations
    * https://dzone.com/articles/frequently-used-annotations-in-spring-boot-applica
    *https://www.upgrad.com/blog/spring-boot-annotations/
    * */

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeConfig(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Bean
    public void addEmployeeDummy(){
        List<Employee> studentList = new ArrayList<Employee>();

        studentList.add(Employee.builder()
                        .name("Daniel Builder")
                        .email("a@a.com")
                        .jobTitle("CTO")
                        .imageUrl("https://i.pinimg.com/474x/26/84/80/268480c65ce30db69157e2ff65687f7b.jpg")
                        .employeeCode(UUID.randomUUID().toString())
                        .build());
        studentList.add(Employee.builder()
                        .name("Daniel Builder2")
                        .email("a@a.com2")
                        .jobTitle("CTO2")
                        .imageUrl("https://i.pinimg.com/474x/26/84/80/268480c65ce30db69157e2ff65687f7b.jpg")
                        .employeeCode(UUID.randomUUID().toString())
                        .build());

        employeeRepo.saveAll(studentList);
    }
}
