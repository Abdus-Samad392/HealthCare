package com.nit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.entity.Appointment;
import com.nit.exception.AppointmentNotFoundException;
import com.nit.service.IAppointmentService;
import com.nit.service.IDoctorService;
import com.nit.view.AppointmentExcelView;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private IDoctorService docService;
	@Autowired
	private IAppointmentService appService;
	
	@GetMapping("/register")
	public String launchRegisterPage(Model model) {
		model.addAttribute("doctors", docService.getAllDoctorIdAndName());
		return "AppointmentRegister";
	}
	
	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment,RedirectAttributes attrs) {
		String message=appService.registerAppointment(appointment);
		attrs.addFlashAttribute("message", message);
		return "redirect:register";
	}

	@GetMapping("/all")
	public String getAllAppointment(Model model) {
		model.addAttribute("appointments", appService.getAllAppointment());
		return "AppointmentData";
	}
	
	@GetMapping("/editPage")
	public String launchEditPageWithOneAppointment(@RequestParam Long id,Model model,RedirectAttributes attrs) {
		String view="";
		String message="";
		try {
			model.addAttribute("doctors", docService.getAllDoctorIdAndName());
			model.addAttribute("appointment", appService.getOneAppointment(id));
			view ="AppointmentEdit";
		}catch(AppointmentNotFoundException e) {
			e.printStackTrace();
			attrs.addFlashAttribute("message", e.getMessage());
			view="redirect:all";
		}
		return view;
	}
	
	@PostMapping("/edit")
	public String editAppointment(@ModelAttribute Appointment appointment,RedirectAttributes attrs) {
		var message=appService.updateAppointment(appointment);
		attrs.addFlashAttribute("message", message);
		return "redirect:all";
	}
	
	@GetMapping("/delete")
	public String deleteAppointment(@RequestParam Long id,RedirectAttributes attrs) {
		try {
			attrs.addFlashAttribute("message", appService.deleteAppointment(id));
			return "redirect:all";
		}catch(AppointmentNotFoundException e) {
			attrs.addFlashAttribute("message", e.getMessage());
			return "redirect:all";
		}
	}
	
	@GetMapping("/excelReport")
	public ModelAndView getAppointmentsAsExcelFile() {
		ModelAndView mav=new ModelAndView();
		mav.setView(new AppointmentExcelView());
		mav.addObject("appointments", appService.getAllAppointment());
		return mav;
	}
}
