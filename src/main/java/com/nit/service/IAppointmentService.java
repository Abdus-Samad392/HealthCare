package com.nit.service;

import java.util.List;

import com.nit.entity.Appointment;

public interface IAppointmentService {

	String registerAppointment(Appointment appointment);
	List<Appointment> getAllAppointment();
	Appointment getOneAppointment(Long id);
	String updateAppointment(Appointment appointment);
	String deleteAppointment(Long id);
	List<Object[]> getAppointmentForDoctor(Long id);
	List<Appointment> getAppointmentOfDoctorByName(String name);
}
