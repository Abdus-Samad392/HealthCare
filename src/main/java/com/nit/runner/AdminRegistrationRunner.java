package com.nit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nit.constants.UserRoles;
import com.nit.entity.User;
import com.nit.password.PasswordGenerator;
import com.nit.service.IUserService;

@Component
public class AdminRegistrationRunner implements CommandLineRunner {

	@Autowired
	private IUserService service;
	
	@Override
	public void run(String... args) throws Exception {
		if(!service.fetchUserByUserName("SAM").isPresent()) {
			User user=new User();
			user.setUserName("SAM");
			user.setPassword(PasswordGenerator.getRandomPassword());
			user.setUserRole(UserRoles.ADMIN.name());
			user.setEmailId("sam@gmail.com");
			service.saveUser(user);
		}
	}

}
