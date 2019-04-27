package org.jasr.spaced.services.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService extends JdbcUserDetailsManager {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) throws AuthenticationException {
		super.changePassword(oldPassword, passwordEncoder.encode(newPassword));
	}
	
}
