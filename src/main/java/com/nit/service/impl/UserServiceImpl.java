package com.nit.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nit.entity.User;
import com.nit.repo.UserRepository;
import com.nit.service.IUserService;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Long saveUser(User user) {
		String pwd=encoder.encode(user.getPassword());
		user.setPassword(pwd);
		return repo.save(user).getId();
		
	}
	
	@Override
	public Optional<User> fetchUserByUserName(String userName) {
		return repo.findByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt=fetchUserByUserName(username);
		if(!opt.isPresent()) {
			throw new UsernameNotFoundException(username);
		}else {
			User user=opt.get();
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole())));
		}
		
	}

	
}
