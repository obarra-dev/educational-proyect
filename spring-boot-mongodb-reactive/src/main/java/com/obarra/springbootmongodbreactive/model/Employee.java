package com.obarra.springbootmongodbreactive.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Employee {
    private Long id;
    private String name;
    private Long salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
