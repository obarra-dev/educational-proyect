package com.obarra;

import org.junit.Test;

import com.obarra.model.Person;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void canConstructAPersonWithAName() {
        Person person = new Person("Mariela");
        assertEquals("Mariela", person.getName());
    }
}
