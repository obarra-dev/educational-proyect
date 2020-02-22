package com.obarra.springbootmongodbreactive.repository;

import com.obarra.springbootmongodbreactive.model.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EmployeeMongoRepository extends ReactiveMongoRepository<Employee, Long> {
}
