package com.obarra.springbootmongodbreactive.repository;

import com.obarra.springbootmongodbreactive.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class EmployeeRepository {

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<Employee> findById(final Long id){
        return reactiveMongoTemplate.findById(id, Employee.class);
    }

    public Flux<Employee> findAll(){
        return reactiveMongoTemplate.findAll(Employee.class);
    }

    public Flux<Employee> findByNameAndSalary(final String name, final Long salary){
        return reactiveMongoTemplate.find(Query
                .query(Criteria.where("name").is(name)
                        .andOperator(Criteria.where("salary").is(salary))), Employee.class);
    }
}
