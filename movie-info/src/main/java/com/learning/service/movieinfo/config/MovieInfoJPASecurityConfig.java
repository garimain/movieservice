package com.learning.service.movieinfo.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class MovieInfoJPASecurityConfig extends WebSecurityConfigurerAdapter {
	
	Logger logger = LoggerFactory.getLogger(MovieInfoJPASecurityConfig.class);
	
	
	private static final String[] AUTH_WHITE_LIST = {
	          "/v2/api-docs"
	        , "/swagger-resources"
	        , "/swagger-resources/**"
	        , "/configuration/security"
	        , "/swagger-ui.html"
	        , "/webjars/**"
	        , "/security/**"
	        , "/actuator"
	        , "/actuator/**"
	    };
	
	@Autowired
	private UserDetailsService userDetailsService;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
		
	}
	
	
	//@Bean
	/* protected PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(4);
		
	} */
	
	@Bean
	protected PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		logger.info("Inside configure method of MovieCatalogJDBCSecurityConfig - HttpSecurity");
		
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/api/*/movies/movie").hasAnyRole("RWUSER", "ROUSER")
			.antMatchers("/api/*/movies").hasAnyRole("ROUSER")
			.antMatchers(AUTH_WHITE_LIST).permitAll()
			.and().httpBasic();
		
	} 
	
}
