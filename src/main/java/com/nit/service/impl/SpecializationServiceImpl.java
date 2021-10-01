package com.nit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Specialization;
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

}
