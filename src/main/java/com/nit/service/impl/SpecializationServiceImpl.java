package com.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Specialization;
import com.nit.exception.SpecializationNotFoundException;
import com.nit.repo.SpecializationRepository;
import com.nit.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService {

	@Autowired
	private SpecializationRepository repo;
	
	@Override
	public String registerSpecialization(Specialization specialization) {
		Specialization spec=repo.save(specialization);
		return "Specialization Registered With id :"+spec.getId();
	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		return repo.getSpecCodeCount(specCode)>0;
	}
	
	@Override
	public boolean isSpecNameExist(String specName) {
		return repo.getSpecNameCount(specName)>0;
	}
	
	@Override
	public List<Specialization> getAllSpecialization() {
		
		return repo.findAll();
	}
	
	@Override
	public Specialization getOneSpecialization(Long id) {
		Optional<Specialization> opt= repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new SpecializationNotFoundException("Specialization With this ID is Not Found");
		}
		
	}
}
