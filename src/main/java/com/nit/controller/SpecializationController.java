package com.nit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/specialization")
public class SpecializationController {

	@GetMapping("/register")
	public String launchRegisterPage() {
		return "SpecializationRegister";
	}
}
