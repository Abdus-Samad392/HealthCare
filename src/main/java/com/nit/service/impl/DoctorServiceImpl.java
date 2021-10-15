package com.nit.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.constants.UserRoles;
import com.nit.entity.Doctor;
import com.nit.entity.User;
import com.nit.exception.DoctorNotFoundException;
import com.nit.password.PasswordGenerator;
import com.nit.repo.DoctorRepository;
import com.nit.service.IDoctorService;
import com.nit.service.IUserService;
@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;
	@Autowired
	private IUserService userService;
	
	@Override
	public String registerDoctor(Doctor doctor) {
		Doctor savedDoctor=repo.save(doctor);
		if(savedDoctor.getId()!=null) {
			User user=new User();
			user.setUserName(doctor.getDocName());
			user.setEmailId(doctor.getDocEmailId());
			user.setPassword(PasswordGenerator.getRandomPassword());
			user.setUserRole(UserRoles.DOCTOR.name());
			userService.saveUser(user);
		}
		return "Doctor is Registered With id :"+savedDoctor.getId();
	}
	
	@Override
	public List<Doctor> findAllDoctors() {
		
		return repo.findAll();
	}
	
	@Override
	public boolean isEmailIdExist(String docEmailId) {
		
		return repo.getEmailIdCount(docEmailId)>0;
	}
	
	@Override
	public boolean isMobileNoExist(Long docMobileNo) {
		
		return repo.getMobileNoCount(docMobileNo)>0;
	}
	
	@Override
	public boolean isEmailIdWithIdExist(String docEmailId, Long id) {
		
		return repo.getEmailIdCountWithId(docEmailId, id)>0;
	}
	
	@Override
	public boolean isMobileNoWithIdExist(Long docMobileNo, Long id) {
		
		return repo.getMobileNoCountWithId(docMobileNo, id)>0;
	}
	
	@Override
	public Doctor getOneDoctor(Long id) {
		Optional<Doctor> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new DoctorNotFoundException("Doctor With id :"+id+" Not Found");
		}
		
	}
	
	@Override
	public String updateDoctor(Doctor doctor) {
		String result="";
		Optional<Doctor> opt=repo.findById(doctor.getId());
		if(opt.isPresent()) {
			repo.save(doctor);
			result="Doctor is Updated";
		}else {
			throw new DoctorNotFoundException("Doctor With id :"+doctor.getId()+" Not Found");
		}
		return result;
		
	}
	
	@Override
	public String deleteDoctor(Long id) {
		String result="";
		Optional<Doctor> opt=repo.findById(id);
		if(opt.isPresent()) {
			repo.deleteById(id);
			result="Doctor With id :"+id+" Deleted";
		}else {
			throw new DoctorNotFoundException("Doctor With Id :"+id+" Not Found");
		}
		return result;
	}
	
	@Override
	public Map<Long, String> getAllDoctorIdAndName() {
		List<Object[]> list=repo.getAllDoctorIdAndName();
		return list.stream().collect(Collectors.toMap(ob->(Long)ob[0], ob->(String)ob[1]));
	}
}
