package com.example.angularspringboot.load;

import com.example.angularspringboot.model.Employee;
import com.example.angularspringboot.esrepo.ElasticRepo;
import com.example.angularspringboot.repository.EmployeeRepo;
import com.example.angularspringboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Lazy
public class Loaders {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    ElasticRepo elasticRepo;

    @Autowired
    EmployeeRepo employeeRepo;


    @PostConstruct
    @Transactional
    public void loadAll(){
        operations.save(Employee.class);
        System.out.println("Loading Data");
        List<Employee> data = employeeRepo.findAll();
        elasticRepo.saveAll(data); //loads into Elastic
        System.out.printf("Loading Completed");
    }

}