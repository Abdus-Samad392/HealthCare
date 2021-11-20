package com.nit.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.entity.User;
import com.nit.password.PasswordGenerator;
import com.nit.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	
	@GetMapping("/login")
	public String launchLoginPage() {
		return "UserLogin";
	}
	
	@GetMapping("/setup")
	public String userSetup(HttpSession ses,Principal principal) {
		String userName=principal.getName();
		User user=service.fetchUserByUserName(userName).get();
		ses.setAttribute("user", user);
		return "UserHome";
	}
	
	@GetMapping("/profile")
	public String showProfile(HttpSession ses,Model model) {
		User user = (User) ses.getAttribute("user");
		model.addAttribute("user", user);
		return "UserProfile";
	}
	
	@GetMapping("/home")
	public String showHomePage() {
		return "UserHome";
	}
	
	@GetMapping("/update")
	public String showPwdUpdatePage() {
		return "UserPasswordUpdate";
	}
	
	@PostMapping("/passwordUpdate")
	public String updatePassword(@RequestParam String password,HttpSession ses,RedirectAttributes attrs) {
		System.out.println("password : "+password);
		User user=(User) ses.getAttribute("user");
		service.updatePassword(password, user.getId());
		attrs.addFlashAttribute("message", "Password Updated");
		return "redirect:login";
	}
		
	@GetMapping("/forgotPwd")
	public String forgotPasswordLaunchPage() {
		return "UserForgotPwd";
	}
	
	@GetMapping("/forgot")
	public String forgotPassword(@RequestParam String username,Model model) {
		String message="";
		Optional<User> opt= service.fetchUserByUserName(username);
		if(opt.isPresent()) {
			String pwd=PasswordGenerator.getRandomPassword();
			System.out.println("password : "+pwd);
			service.updatePassword(pwd, opt.get().getId());
			message="Password Updated, Check Your Email";
		}else {
			message="UserName Not Found";
		}
		model.addAttribute("message", message);
		return "UserForgotPwd";
	}
}
