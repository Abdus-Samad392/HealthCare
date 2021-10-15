package com.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.constants.UserRoles;
import com.nit.entity.Patient;
import com.nit.entity.User;
import com.nit.exception.PatientNotFoundException;
import com.nit.password.PasswordGenerator;
import com.nit.repo.PatientRepository;
import com.nit.service.IPatientService;
import com.nit.service.IUserService;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository repo;
	
	@Autowired
	private IUserService service;
	
	@Override
	public String registerPatient(Patient patient) {
		Patient pat=repo.save(patient);
		if(pat.getId()!=null) {
			User user =new User();
			user.setUserName(pat.getPatientName());
			user.setEmailId(pat.getPatientEmailId());
			user.setPassword(PasswordGenerator.getRandomPassword());
			user.setUserRole(UserRoles.PATIENT.name());
			service.saveUser(user);
		}
		return "Patient is Registered with Id :"+pat.getId();
	}
	
	@Override
	public List<Patient> getAllPatient() {
		List<Patient> list=repo.findAll();
		return list;
	}
	
	@Override
	public Patient getOnePatient(Long id) {
		Optional<Patient> opt=repo.findById(id);
		return opt.orElseThrow(()->new PatientNotFoundException("Patient With id :"+id+" Not Found"));
	}
	
	@Override
	public String updatePatient(Patient patient) {
		repo.save(patient);
		return "Patient With id :"+patient.getId()+" Updated";
	}
	
	@Override
	public boolean isMobileNoExist(Long patientMobileNo) {
		return repo.getMobileNoCount(patientMobileNo)>0;
	}
	
	@Override
	public boolean isMobileNoWithIdExist(Long patientMobileNo, Long id) {
		return repo.getMobileNoCountWithId(patientMobileNo, id)>0;
	}
	
	@Override
	public String deletePatient(Long id) {
		String message="";
		Optional<Patient> opt=repo.findById(id);
		if(opt.isPresent()) {
			repo.deleteById(id);
			message="Patient With id :"+id+" Deleted";
			return message;
		}else {
			throw new PatientNotFoundException("Patient With Id :"+id+" Not Found");
		}
	}
}
