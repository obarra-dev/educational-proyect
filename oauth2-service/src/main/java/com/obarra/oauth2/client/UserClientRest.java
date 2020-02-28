package com.obarra.oauth2.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omm.common.model.entity.User;

@FeignClient(name="user-service")
public interface UserClientRest {
	
	@GetMapping("/users/search/findByUsername")
	User findByUsername(@RequestParam String username);

}
