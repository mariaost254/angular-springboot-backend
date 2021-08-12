package com.example.angularspringboot.config;

import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.context.annotation.*;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.angularspringboot.repository")
@EnableElasticsearchRepositories(basePackages = "com.example.angularspringboot.esrepo")
public class ElasticConfig {

    //https://codecurated.com/blog/how-to-connect-java-with-elasticsearch/
    //http://localhost:9200/employeeindex/_mappings
    //https://reflectoring.io/spring-boot-elasticsearch/
    /**
     Elasticsearch	->	Database
     Index	->	Table
     Document	->	Row
     Field	->	Column
     Any data we want to search or analyze is stored as a document in an index. In Spring Data, we represent a document in the form of a POJO and decorate it with annotations to define the mapping into an Elasticsearch document.
    Field data types:  https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-types.html
     * */

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration =
                ClientConfiguration
                        .builder()
                        .connectedTo("localhost:9200")
                        .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }

/**
 *
 * ElasticsearchRestTemplate: We create queries with method chaining and native queries to have more control over creating Elasticsearch queries in relatively complex scenarios.
 * Spring Data Elasticsearch uses Java High Level REST Client (JHLC) to connect to the Elasticsearch server. JHLC is the default client of Elasticsearch.
 *  **/

}
