package com.nit.service;

import java.util.Optional;

import com.nit.entity.User;

public interface IUserService {

	Long saveUser(User user);
	Optional<User> fetchUserByUserName(String userName);
}
