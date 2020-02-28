package com.obarra.oauth2.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.obarra.oauth2.service.UserService;
import com.omm.common.model.entity.User;

@Component
public class AdditionalInfoToken implements TokenEnhancer{
	
	@Autowired
	private UserService userService;


	//TODO buscar para evita hacer una segunda consulta para obtener info adicional si ya la tengo en la primera consulta
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = userService.findByUsername(authentication.getName());
		Map<String, Object> additionalInformation = new HashMap<>();
		additionalInformation.put("firstName", user.getFirstName());
		additionalInformation.put("lastName", user.getLastName());
		additionalInformation.put("email", user.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
		return accessToken;
	}

}
