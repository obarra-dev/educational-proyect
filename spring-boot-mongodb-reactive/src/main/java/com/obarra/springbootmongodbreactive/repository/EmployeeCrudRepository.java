package com.obarra.springbootmongodbreactive.repository;

import com.obarra.springbootmongodbreactive.model.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeCrudRepository extends ReactiveCrudRepository<Employee, Long> {
    @Query("{'name': ?0}")
    Flux<Employee> findByName(final String name);

    Flux<Employee> findBySalary(final Mono<Long> salary);

    Flux<Employee> findBySalary(final Long salary);
}
