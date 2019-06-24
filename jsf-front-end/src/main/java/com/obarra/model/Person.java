package com.obarra.model;

import java.util.Date;

public class Person {
    private String name;
    private String gender;
    private Date birthDate;
    
    public Person() {
    	
    }

    public Person(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

    
}
