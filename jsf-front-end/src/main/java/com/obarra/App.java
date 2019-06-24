package com.obarra;

import com.google.common.base.Splitter;
import com.obarra.model.Person;

public class App {
	public static void main(String[] args) {
		Person person = new Person("M-a-r-i-e-l-a");
		System.out.println(Splitter.on('-').split(person.getName()));
	}

}
