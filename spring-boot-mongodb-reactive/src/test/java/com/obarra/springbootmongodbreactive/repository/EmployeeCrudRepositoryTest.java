package com.obarra.springbootmongodbreactive.repository;

import com.obarra.springbootmongodbreactive.SpringBootMongodbReactiveApplication;
import com.obarra.springbootmongodbreactive.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootMongodbReactiveApplication.class)
class EmployeeCrudRepositoryTest {

    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Test
    void findByName() {
        employeeCrudRepository.save(new Employee(1L, "test", 23L));

        Mono<Employee> employees = employeeCrudRepository.findById(1L);

        StepVerifier.create(employees)
                .assertNext(employee -> {
                    assertEquals("test", employee.getName());
                    assertEquals(23L, employee.getSalary());
                    assertNotNull(employee.getId());
                })
                .expectComplete()
                .verify();
    }
}