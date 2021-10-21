package com.nit.service;

import java.util.List;
import java.util.Map;

import com.nit.entity.Doctor;

public interface IDoctorService {

	String registerDoctor(Doctor doctor);
	List<Doctor> findAllDoctors();
	boolean isEmailIdExist(String docEmailId);
	boolean isMobileNoExist(Long docMobileNo);
	boolean isEmailIdWithIdExist(String docEmailId,Long id);
	boolean isMobileNoWithIdExist(Long docMobileNo,Long id);
	Doctor getOneDoctor(Long id);
	String updateDoctor(Doctor doctor);
	String deleteDoctor(Long id);
	Map<Long, String> getAllDoctorIdAndName();
	List<Doctor> findDoctorsBySpecialization(Long specId);
}
