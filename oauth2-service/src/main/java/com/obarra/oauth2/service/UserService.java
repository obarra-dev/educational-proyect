package com.obarra.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obarra.oauth2.client.UserClientRest;
import com.omm.common.model.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserClientRest userClientRest;

	public User findByUsername(final String username) {
		return  userClientRest.findByUsername(username);
	}

}
