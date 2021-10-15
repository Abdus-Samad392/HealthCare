package com.nit.password;

import java.util.UUID;

public class PasswordGenerator {

	public static String getRandomPassword() {
		UUID pwd=UUID.randomUUID();
		String generatedPwd= pwd.toString().replaceAll("-", "").substring(0, 7);
		System.out.println(generatedPwd);
		return generatedPwd;
	}
}
