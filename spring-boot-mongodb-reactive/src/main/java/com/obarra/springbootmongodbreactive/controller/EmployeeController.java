package com.obarra.springbootmongodbreactive.controller;

import com.obarra.springbootmongodbreactive.model.Employee;
import com.obarra.springbootmongodbreactive.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(final @RequestBody Employee employee) throws Exception {
        //TODO return object with id
        employeeService.create(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Employee>> findById(final @PathVariable Long id){
        Mono<Employee> employeeMono = employeeService.findById(id);
        //TODO can webflux retun null?? i dont belive
        //TODO is return 404 ok?
        HttpStatus status = employeeMono == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(employeeMono, status);
    }

    @GetMapping("/name/{name}")
    public Flux<Employee> findByName(final @PathVariable String name){
        return employeeService.findByName(name);
    }

    @GetMapping("/salary/{salary}")
    public Flux<Employee> findBySalary(final @PathVariable Long salary){
        return employeeService.findBySalary(salary);
    }

    @GetMapping("/salary-mono/{salary}")
    public Flux<Employee> findBySalaryMono(final @PathVariable Long salary){
        return employeeService.findBySalary(Mono.just(salary));
    }

    @GetMapping()
    public Flux<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/byFilter")
    public Flux<Employee> findAllBy(final Employee employee){
        return employeeService.findAllByFilter(employee);
    }

    @PutMapping("/update")
    public Mono<Employee> update(final @RequestBody Employee employee){
        return employeeService.update(employee);
    }

     @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        employeeService.delete(id);
     }


}
