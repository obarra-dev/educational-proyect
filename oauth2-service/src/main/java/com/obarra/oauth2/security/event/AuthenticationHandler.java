package com.obarra.oauth2.security.event;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.obarra.oauth2.service.UserService;
import com.omm.common.model.entity.User;

import feign.FeignException;

@Component
public class AuthenticationHandler implements AuthenticationEventPublisher{
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationHandler.class);
	
	@Autowired
	private UserService userService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		LOG.info("Username: "+userDetails.getUsername());
		
		User user = userService.findByUsername(authentication.getName());
		if(Objects.nonNull(user.getIntent()) && user.getIntent() > 0) {
			user.setIntent(0);
			userService.update(user);
		}
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String username = (String)authentication.getPrincipal();
		LOG.error("Error with username: "+username, exception);
		try {
			User user = userService.findByUsername(authentication.getName());
			Integer intent = user.getIntent();
			
			if(Objects.isNull(intent)) {
				intent = 0;
			}
			
			intent+=1;
			
			LOG.info(String.format("%d failed intents: ", intent));
			
			if(intent>2) {
				user.setEnable(Boolean.FALSE);
				LOG.info(String.format("Username: %s disabled for maximun intent", user.getUsername()));
			}
			
			user.setIntent(intent);
			
			userService.update(user);
		}catch(FeignException e) {
			LOG.error("Error handling error", e);
		}
		
	}

}
