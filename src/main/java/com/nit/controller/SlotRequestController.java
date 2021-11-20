package com.nit.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nit.constants.SlotStatus;
import com.nit.entity.Appointment;
import com.nit.entity.Patient;
import com.nit.entity.SlotRequest;
import com.nit.entity.User;
import com.nit.service.IAppointmentService;
import com.nit.service.IDoctorService;
import com.nit.service.IPatientService;
import com.nit.service.ISlotRequestService;
import com.nit.service.ISpecializationService;
import com.nit.util.DashBoardUtil;

@Controller
@RequestMapping("/slot")
public class SlotRequestController {
	
	@Autowired
	private IAppointmentService appService;
	
	@Autowired
	private IPatientService patService;
	
	@Autowired
	private IDoctorService docService;
	
	@Autowired
	private ISpecializationService specService;
	
	@Autowired
	private DashBoardUtil util;
	
	@Autowired
	private ISlotRequestService service;
	
	@Autowired
	private ServletContext ctx;
	
	@GetMapping("/book")
	public String bookRequest(@RequestParam Long id,HttpSession ses,Model model) {
		User user=(User) ses.getAttribute("user");
		
		Appointment app=appService.getOneAppointment(id);
		Patient patient=patService.findPatientByName(user.getUserName());
		
		SlotRequest slot=new SlotRequest();
		slot.setApp(app);
		slot.setPatient(patient);
		slot.setStatus(SlotStatus.PENDING.name());
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MMM/yyyy");
			String date=sdf.format(app.getAppointmentDate());
			service.bookSlotRequest(slot);
			String message="Your Appointment Status On "+date+" With Doctor "+app.getAppointmentWithdoctor().getDocName()+" is "+slot.getStatus();
			model.addAttribute("message", message);
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message","REQUEST IS ALREADY BOOKED FOR THIS APPOINTMENT, SEE THE STATUS");
		}
		
		return "SlotRequestStatus";
	}
	
	@GetMapping("/all")
	public String getAllSlotRequest(Model model) {
		model.addAttribute("slots", service.getAllSlotRequest());
		return "SlotRequestData";
	}
	
	@GetMapping("/accept")
	public String updateStatusOfSlotRequestOnAccept(@RequestParam Long id) {
		service.updateSlotRequestStatus(id, SlotStatus.ACCEPTED.name());
		SlotRequest sr=service.getOneSlotRequest(id);
		if(sr.getStatus().equalsIgnoreCase(SlotStatus.ACCEPTED.name())) {
			appService.updateAppointmentSlots(-1, sr.getApp().getId());
		}
		return "redirect:all";
	}
	
	@GetMapping("/reject")
	public String updateStatusOfSlotRequestOnReject(@RequestParam Long id) {
		service.updateSlotRequestStatus(id, SlotStatus.REJECTED.name());
		return "redirect:all";
	}
	
	@GetMapping("/patient")
	public String getSlotRequestStatusOfOnePatient(Principal principal,Model model) {
		List<SlotRequest> list=service.getSlotStatusOfOnePatient(principal.getName());
		model.addAttribute("slots", list);
		return "SlotRequestDataPatient";
	}
	
	@GetMapping("/cancel")
	public String cancelSlotRequestByPatient(@RequestParam Long id) {
		SlotRequest sr=service.getOneSlotRequest(id);
		if(sr.getStatus().equalsIgnoreCase(SlotStatus.ACCEPTED.name())) {
			service.updateSlotRequestStatus(id, SlotStatus.CANCELLED.name());
			appService.updateAppointmentSlots(1, sr.getApp().getId());
		}
		
		return "redirect:patient";
	}
	
	@GetMapping("/dashboard")
	public String dashBoard(Model model) {
		model.addAttribute("appointments", appService.appointmentCount());
		model.addAttribute("doctors", docService.getDoctorCount());
		model.addAttribute("patients", patService.getPatientCount());
		model.addAttribute("specialization", specService.getSpecializationCount());
		
		List<Object[]> list=service.getSlotStatusAndCount();
		String path=ctx.getRealPath("/");
		util.generatePieChart(list, path);
		util.generateBarChart(list, path);
		
		return "DashBoard";
	}
}
