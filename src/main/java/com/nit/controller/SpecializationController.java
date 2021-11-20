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

import com.nit.entity.Specialization;
import com.nit.exception.SpecializationNotFoundException;
import com.nit.service.ISpecializationService;
import com.nit.view.SpecializationExcelView;
import com.nit.view.SpecializationPDFView;

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
	public String validateDuplicateSpecCode(@RequestParam String specCode,@RequestParam Long id) {
		String duplicateSpecCode="";
		if(id==0 && service.isSpecCodeExist(specCode)) {
			duplicateSpecCode="Specialization Code Already Exist,Choose Any Other";
			return duplicateSpecCode;
		}else if(id!=0 && service.isSpecCodeWithIdExist(specCode, id)){
			duplicateSpecCode="Specialization Code Already Exist,Choose Any Other";
			return duplicateSpecCode;
		}else {
			return duplicateSpecCode;
		}
	}
	
	@GetMapping("/checkSpecName")
	@ResponseBody
	public String validateDuplicateSpecName(@RequestParam String specName,@RequestParam Long id) {
		String duplicateSpecName="";
		if(id==0 && service.isSpecNameExist(specName)) {
			duplicateSpecName="Specialization Name Already Exist";
			return duplicateSpecName;
		}else if(id!=0 && service.isSpecNameWithIdExist(specName, id)) {
			duplicateSpecName="Specialization Name Already Exist";
			return duplicateSpecName;
		}
		else {
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
	public String loadEditPageWithData(@RequestParam Long id,Model model,RedirectAttributes attrs) {
		String page="";
		try {
			Specialization spec=service.getOneSpecialization(id);
			model.addAttribute("spec", spec);
			page="SpecializationEdit";
		}catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attrs.addFlashAttribute("message", e.getMessage());
			page="redirect:all";
		}
		return page;
	}
	
	@PostMapping("/edit")
	public String specializationEdit(@ModelAttribute Specialization spec,RedirectAttributes attrs) {
		String view="";
		try {
			String resultMessage=service.updateSpecialization(spec);
			attrs.addFlashAttribute("message", resultMessage);
			view="redirect:all";
		}catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attrs.addFlashAttribute("message",e.getMessage());
			view="redirect:all";
		}
		return view;
	}
	
	@GetMapping("/delete")
	public String specializationDelete(@RequestParam Long id,RedirectAttributes attrs) {
		String resultMessage=service.deleteSpecializationById(id);
		attrs.addFlashAttribute("message", resultMessage);
		return "redirect:all";
	}
	
	@GetMapping("/excelReport")
	public ModelAndView specializationExcelReport() {
		ModelAndView model=new ModelAndView();
		model.setView(new SpecializationExcelView());
		List<Specialization> list=service.getAllSpecialization();
		model.addObject("specs", list);
		return model;
	}
	
	
	@GetMapping("/pdfReport")
	public ModelAndView specializationPDFReport() {
		ModelAndView mav=new ModelAndView();
		mav.setView(new SpecializationPDFView());
		List<Specialization> list=service.getAllSpecialization();
		mav.addObject("specs", list);
		return mav;
	}
}
