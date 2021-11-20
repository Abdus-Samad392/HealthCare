package com.nit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nit.constants.UserRoles;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserDetailsService service;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(encoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/patient/register","/patient/save","/user/login","/user/forgotPwd","/user/forgot").permitAll()
		.antMatchers("/doctor/**","/specialization/**","/appointment/all","/appointment/register").hasAuthority("ADMIN")
		.antMatchers("/appointment/view").hasAuthority(UserRoles.PATIENT.name())
		.antMatchers("/appointment/doctor").hasAuthority(UserRoles.DOCTOR.name())
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/user/login")
		.defaultSuccessUrl("/user/setup", true)
		.loginProcessingUrl("/login")
		.failureUrl("/user/login?error=true")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/user/login?success=true")
		;
	}
}
