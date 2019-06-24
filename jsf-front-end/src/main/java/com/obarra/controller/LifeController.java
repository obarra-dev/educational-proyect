package com.obarra.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.obarra.model.Person;

@ManagedBean(name ="lifeController")
@RequestScoped
public class LifeController {

	private Person person;
	private String message;
	private BigDecimal percentage;
	
	private UIComponent messageIdentificationValid;


	
	public LifeController() {
		this.message = "My Front";
		this.percentage = new BigDecimal("99.9");
		this.person = new Person();
		this.person.setGender("2");
		this.person.setBirthDate(new Date());
	}
	
	public void completeSection(String sectionId) throws Exception {
		
		if(true) {
			FacesMessage message = new FacesMessage("Invalid something");
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(messageIdentificationValid.getClientId(context), message);
	        return ;
		}
		
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public UIComponent getMessageIdentificationValid() {
		return messageIdentificationValid;
	}

	public void setMessageIdentificationValid(UIComponent messageIdentificationValid) {
		this.messageIdentificationValid = messageIdentificationValid;
	}
	
}
