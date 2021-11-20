package com.nit.service;

import java.util.List;

import com.nit.entity.Patient;

public interface IPatientService {

	String registerPatient(Patient patient);
	List<Patient> getAllPatient();
	Patient getOnePatient(Long id);
	String updatePatient(Patient patient);
	boolean isMobileNoExist(Long patientMobileNo);
	boolean isMobileNoWithIdExist(Long patientMobileNo,Long id);
	String deletePatient(Long id);
	Patient findPatientByName(String name);
	long getPatientCount();
}
