package com.nit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.entity.Patient;
import com.nit.exception.PatientNotFoundException;
import com.nit.service.IPatientService;
import com.nit.view.PatientExcelView;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private IPatientService patService;
	
	@GetMapping("/register")
	public String loadRegisterPage() {
		return "PatientRegister";
	}
	
	@PostMapping("/save")
	public String savePatient(@ModelAttribute Patient patient,RedirectAttributes attrs) {
		String message=patService.registerPatient(patient);
		attrs.addFlashAttribute("message", message);
		return "redirect:register";
	}
	
	@GetMapping("/all")
	public String getAllPatient(Model model) {
		List<Patient> list=patService.getAllPatient();
		model.addAttribute("patients", list);
		return "PatientData";
	}
	
	@GetMapping("/editPage")
	public String launchEditPageForOnePatient(@RequestParam Long id,Model model,RedirectAttributes attrs) {
		String view="";
		try {
			Patient patient=patService.getOnePatient(id);
			model.addAttribute("patient", patient);
			view="PatientEdit";
		}catch(PatientNotFoundException e) {
			e.printStackTrace();
			attrs.addFlashAttribute("message", e.getMessage());
			view="redirect:all";
		}
		return view;
		
	}
	
	@PostMapping("/edit")
	public String editPatient(@ModelAttribute Patient patient,RedirectAttributes attrs) {
		String message=patService.updatePatient(patient);
		attrs.addFlashAttribute("message", message);
		return "redirect:all";
	}
	
	@GetMapping("/checkPatientMobileNo")
	@ResponseBody
	public String checkDuplicateMobileNo(@RequestParam Long patientMobileNo,@RequestParam Long id) {
		String message="";
		if(id==0 && patService.isMobileNoExist(patientMobileNo)) {
			message="Mobile No Already Exist";
		}else if(id!=0 && patService.isMobileNoWithIdExist(patientMobileNo, id)) {
			message="Mobile No Already Exist";
		}
		return message;
	}
	
	@GetMapping("/delete")
	public String deletePatient(@RequestParam Long id,RedirectAttributes attrs) {
		String view="";
		String message="";
		try {
			message=patService.deletePatient(id);
			attrs.addFlashAttribute("message", message);
			view="redirect:all";
		}catch(PatientNotFoundException e) {
			e.printStackTrace();
			attrs.addFlashAttribute("message", e.getMessage());
			view="redirect:all";
		}
		return view;
	}
	
	@GetMapping("/excelReport")
	public ModelAndView getExcelReport() {
		ModelAndView mav=new ModelAndView();
		mav.setView(new PatientExcelView());
		List<Patient> list=patService.getAllPatient();
		mav.addObject("patients", list);
		return mav;
	}
}
