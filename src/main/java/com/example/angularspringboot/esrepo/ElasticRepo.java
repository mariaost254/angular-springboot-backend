package com.example.angularspringboot.esrepo;

import com.example.angularspringboot.model.Employee;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

//Repositories provide the most convenient way to access data in Spring Data using finder methods. The Elasticsearch queries get created from method names.

public interface ElasticRepo extends ElasticsearchRepository <Employee,Long> {
}
