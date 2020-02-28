package com.obarra.oauth2.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import feign.FeignException;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		if (Objects.isNull(username) || username.isBlank()) {
			LOG.error("Empty User");
			throw new IllegalArgumentException("Empty User");
		}

		try {
			com.omm.common.model.entity.User user = userService.findByUsername(username);
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName()))
					.peek(authority -> LOG.info("User " + user.getUsername() + " - Role" + authority.getAuthority()))
					.collect(Collectors.toList());

			UserDetails userDetails = new User(user.getUsername(), user.getPassword(), user.getEnable(), true, true,
					true, authorities);

			return userDetails;
		} catch (FeignException e) {
			LOG.error("Error loadUserByUsername ", e);
			throw new UsernameNotFoundException("Do not exist User: " + username);
		}

	}

}
