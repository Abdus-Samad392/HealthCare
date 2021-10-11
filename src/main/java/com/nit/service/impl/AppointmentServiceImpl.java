package com.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Appointment;
import com.nit.exception.AppointmentNotFoundException;
import com.nit.repo.AppointmentRepository;
import com.nit.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository repo;
	
	@Override
	public String registerAppointment(Appointment appointment) {
		Appointment app=repo.save(appointment);
		return "Appointment Registered With id :"+app.getId();
	}
	
	@Override
	public List<Appointment> getAllAppointment() {
		return repo.findAll();
	}
	
	@Override
	public Appointment getOneAppointment(Long id) {
		Optional<Appointment> opt=repo.findById(id);
		return opt.orElseThrow(()->new AppointmentNotFoundException("Appointment With Id :"+id+" Not Found"));
	}
	
	@Override
	public String updateAppointment(Appointment appointment) {
		repo.save(appointment);
		return "Appointment With Id :"+appointment.getId()+" Updated";
	}
	
	@Override
	public String deleteAppointment(Long id) {
		Optional<Appointment> opt=repo.findById(id);
		if(opt.isPresent()) {
			repo.deleteById(id);
			return "Appointment With Id :"+id+" Deleted";
		}else {
			throw new AppointmentNotFoundException("Appointment With Id :"+id+" Not Found");
		}
	}
}
