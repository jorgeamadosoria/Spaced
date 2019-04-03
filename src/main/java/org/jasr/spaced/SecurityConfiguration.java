package org.jasr.spaced;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//	http.authorizeRequests().antMatchers("/admin/**").authenticated().and().antMatcher("/**").anonymous().and().logout().permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/admin/index").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		BCryptPasswordEncoder passwordEncoder = passwordEncoder();
		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> jdbc = builder.jdbcAuthentication();
		jdbc.dataSource(dataSource).passwordEncoder(passwordEncoder);
		JdbcUserDetailsManager usersService = jdbc.getUserDetailsService();
		usersService.setJdbcTemplate(new JdbcTemplate(dataSource));
		createFirstUser(usersService);
	}

	private void createFirstUser(JdbcUserDetailsManager usersService) {
		if (!usersService.userExists("admin"))
			usersService.createUser(new User("admin", passwordEncoder().encode("admin"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));

	}

}
