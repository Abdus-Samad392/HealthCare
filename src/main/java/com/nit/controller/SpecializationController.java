package com.nit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.entity.Specialization;
import com.nit.exception.SpecializationNotFoundException;
import com.nit.service.ISpecializationService;

@Controller
@RequestMapping("/specialization")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;
	
	@GetMapping("/register")
	public String launchRegisterPage() {
		return "SpecializationRegister";
	}
	
	@PostMapping("/save")
	public String registerSpecialization(@ModelAttribute Specialization specialization,RedirectAttributes attrs) {
		String registrationConfirmation=service.registerSpecialization(specialization);
		attrs.addFlashAttribute("registrationConfirmation", registrationConfirmation);
		return "redirect:register";
	}
	
	@GetMapping("/checkSpecCode")
	@ResponseBody
	public String validateDuplicateSpecCode(@RequestParam String specCode) {
		String duplicateSpecCode="";
		if(service.isSpecCodeExist(specCode)) {
			duplicateSpecCode="Specialization Code Already Exist,Choose Any Other";
			return duplicateSpecCode;
		}else {
			return duplicateSpecCode;
		}
	}
	
	@GetMapping("/checkSpecName")
	@ResponseBody
	public String validateDuplicateSpecName(@RequestParam String specName) {
		String duplicateSpecName="";
		if(service.isSpecNameExist(specName)) {
			duplicateSpecName="Specialization Name Already Exist";
			return duplicateSpecName;
		}else {
			return duplicateSpecName;
		}
	}
	
	@GetMapping("/all")
	public String getAllSpecialization(Model model) {
		List<Specialization> list=service.getAllSpecialization();
		model.addAttribute("specs", list);
		return "SpecializationData";
	}
	
	@GetMapping("/editPage")
	public String loadEditPageWithData(@RequestParam Long id,Model model) {
		String page="";
		try {
			Specialization spec=service.getOneSpecialization(id);
			model.addAttribute("spec", spec);
			page="SpecializationEdit";
		}catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
			page="redirect:all";
		}
		return page;
	}
}
