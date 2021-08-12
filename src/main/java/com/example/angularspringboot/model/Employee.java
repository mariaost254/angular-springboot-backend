package com.example.angularspringboot.model;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data //generates all the boilerplate that is normally associated with simple POJOs
@Entity(name ="Employee") //hibernate
@Table //retional db
@Document(indexName = "employeeindex", shards = 1, replicas = 0, refreshInterval = "5s", createIndex = true) //ES
public class Employee implements Serializable { //transform the java class into different types of streams, saved as db,saved as json, just best practice

    //https://javabeat.net/jpa-annotations-generatedvalue-sequencegenerator-tablegenerator/
    //https://www.baeldung.com/spring-entityscan-vs-componentscan
    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1) //
    //It is the name of the primary key generator
    //The @SequenceGenerator annotation defines a primary key generator that may be referenced by name when a generator element is specified for the GeneratedValue annotation.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    // @GeneratedValue is used only to get the generated value. The two arguments strategy and generator are used to define how the value is obtained.
    @Column(name = "id")
    private Long id;
    @Field(type = FieldType.Text, name = "name")
    @Column(name = "name")
    private String name;
    @Field(type = FieldType.Text, name = "email")
    @Column(name = "email")
    private String email;
    @Field(type = FieldType.Text, name = "jobTitle")
    @Column(name = "jobTitle")
    private String jobTitle;
    @Field(type = FieldType.Text, name = "phone")
    @Column(name = "phone")
    private String phone;
    private String imageUrl;
//    @Field(type = FieldType.Text, name = "employeeCode")
    @Column(nullable = false, updatable = false)
    private String employeeCode;

//    public Employee(String name, String email, String jobTitle, String phone, String imageUrl, String employeeCode) {
//        this.name = name;
//        this.email = email;
//        this.jobTitle = jobTitle;
//        this.phone = phone;
//        this.imageUrl = imageUrl;
//        this.employeeCode = employeeCode;
//    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", employeeCode='" + employeeCode + '\'' +
                '}';
    }

}
