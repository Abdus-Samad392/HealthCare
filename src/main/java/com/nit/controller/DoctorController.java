package com.nit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.entity.Doctor;
import com.nit.exception.DoctorNotFoundException;
import com.nit.service.IDoctorService;
import com.nit.service.ISpecializationService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService docService;
	@Autowired
	private ISpecializationService specService; 
	
	@GetMapping("/register")
	public String launchRegisterPage(Model model) {
		model.addAttribute("specs", specService.fetchSpecIdAndSpecName());
		return "DoctorRegister";
	}
	
	@PostMapping("/save")
	public String saveDoctor(@ModelAttribute Doctor doctor,RedirectAttributes attrs) {
		String message=docService.registerDoctor(doctor);
		System.out.println("Doctor Photo Loc ::"+doctor.getDocPhotoLoc());
		attrs.addFlashAttribute("message", message);
		return "redirect:register";
	}
	
	@GetMapping("/all")
	public String fetchAllDoctors(Model model) {
		model.addAttribute("doctors", docService.findAllDoctors());
		return "DoctorData";
	}
	
	@GetMapping("/checkDocEmailId")
	@ResponseBody
	public String checkDuplicateEmailId(@RequestParam String docEmailId,@RequestParam Long id) {
		String duplicateEmailId="";
		if(id==0 && docService.isEmailIdExist(docEmailId)) {
			duplicateEmailId="Email Id Already Exist";	
		}else if(id!=0 && docService.isEmailIdWithIdExist(docEmailId, id)) {
			duplicateEmailId="Email Id Already Exist";
		}
		return duplicateEmailId;
	}
	
	@GetMapping("/checkDocMobileNo")
	@ResponseBody
	public String checkDuplicateMobileNo(@RequestParam Long docMobileNo,@RequestParam Long id) {
		String duplicateMobileNo="";
		if(id==0 && docService.isMobileNoExist(docMobileNo)) {
			duplicateMobileNo="Mobile No Already Exist";
		}else if(id!=0 && docService.isMobileNoWithIdExist(docMobileNo, id)) {
			duplicateMobileNo="Mobile No Already Exist";
		}
		return duplicateMobileNo;
	}
	
	
	
	@GetMapping("/editPage")
	public String launchEditPage(@RequestParam Long id,Model model,RedirectAttributes attrs) {
		String page="";
		try {
			model.addAttribute("doctor", docService.getOneDoctor(id));
			model.addAttribute("specs", specService.fetchSpecIdAndSpecName());
			page="DoctorEdit";
		}catch(DoctorNotFoundException e) {
			e.printStackTrace();
			attrs.addFlashAttribute("message", e.getMessage());
			page="redirect:all";
		}
		return page;
	}
	
	@PostMapping("/edit")
	public String editDoctor(@ModelAttribute Doctor doctor,RedirectAttributes attrs) {
		String view="";
		String result="";
		try {
			result=docService.updateDoctor(doctor);
			attrs.addFlashAttribute("message", result);
		}catch(DoctorNotFoundException e) {
			e.printStackTrace();
			attrs.addFlashAttribute("message", e.getMessage());
		}
		view="redirect:all";
		return view;
	}
	
	@GetMapping("/delete")
	public String deleteDoctor(@RequestParam Long id,RedirectAttributes attrs) {
		String result="";
		try {
			result=docService.deleteDoctor(id);
			attrs.addFlashAttribute("message",result);
		}catch(DoctorNotFoundException e) {
			e.printStackTrace();
			attrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}
}
