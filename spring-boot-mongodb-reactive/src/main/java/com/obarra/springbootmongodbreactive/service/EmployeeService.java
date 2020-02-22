package com.obarra.springbootmongodbreactive.service;

import com.obarra.springbootmongodbreactive.model.Employee;
import com.obarra.springbootmongodbreactive.repository.EmployeeCrudRepository;
import com.obarra.springbootmongodbreactive.repository.EmployeeMongoRepository;
import com.obarra.springbootmongodbreactive.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMongoRepository employeeMongoRepository;

    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Flux<Employee> findByWithTemplate(final String name, final Long salary){
        return employeeRepository.findByNameAndSalary(name, salary);
    }

    public Mono<Employee> findById(final Long id){
        return employeeCrudRepository.findById(id);
    }

    public Flux<Employee> findByName(final String name){
        return employeeCrudRepository.findByName(name);
    }

    public Flux<Employee> findBySalary(final Long salary){
        return employeeCrudRepository.findBySalary(salary);
    }

    public Flux<Employee> findBySalary(final Mono<Long> salary){
        return employeeCrudRepository.findBySalary(salary);
    }

    public Flux<Employee> findAll(){
        return employeeCrudRepository.findAll();
    }

    public Mono<Employee> update(final Employee employee){
        //TODO why does not it need subscribe?
        return employeeCrudRepository.save(employee);
    }

    public void delete(final Long id){
        employeeCrudRepository.deleteById(id).subscribe();
    }

    //TODO return object with id
    public Disposable create(final Employee employee){
        return employeeMongoRepository.insert(employee).subscribe();
    }

    public Flux<Employee> findAllByFilter(final Employee employee){
        return employeeMongoRepository.findAll(Example.of(employee));
    }
}
