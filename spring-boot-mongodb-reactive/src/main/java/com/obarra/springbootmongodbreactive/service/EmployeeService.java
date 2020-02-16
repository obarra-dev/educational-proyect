package com.obarra.springbootmongodbreactive.service;

import com.obarra.springbootmongodbreactive.model.Employee;
import com.obarra.springbootmongodbreactive.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //TODO return object with id
    public Disposable create(final Employee employee){
        return employeeRepository.insert(employee).subscribe();
    }

    public Mono<Employee> findById(final Long id){
        return employeeRepository.findById(id);
    }

    public Flux<Employee> findByName(final String name){
        return employeeRepository.findByName(name);
    }

    public Flux<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Mono<Employee> update(final Employee employee){
        //TODO why does not it need subscribe?
        return employeeRepository.save(employee);
    }

    public void delete(final Long id){
        employeeRepository.deleteById(id).subscribe();
    }
}
