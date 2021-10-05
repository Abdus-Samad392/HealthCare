package com.nit.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	@Override
	public boolean isSpecCodeWithIdExist(String specCode, Long id) {
		return repo.getSpecCodeCountWithId(specCode, id)>0;
	}
	
	@Override
	public boolean isSpecNameWithIdExist(String specName, Long id) {
		return repo.getSpecNameCountWithId(specName, id)>0;
	}
	
	@Override
	public String updateSpecialization(Specialization spec) {
		String resultMessage="";
		Optional<Specialization> opt=repo.findById(spec.getId());
		if(opt.isPresent()) {
			repo.save(spec);
			resultMessage="Record With id :"+spec.getId()+" updated";
		}else {
			throw new SpecializationNotFoundException("Record With id :"+spec.getId()+" not found");
		}
		
		return resultMessage;
	}
	
	@Override
	public String deleteSpecializationById(Long id) {
		repo.deleteById(id);
		return "Record With id "+id+" Deleted";
	}
	
	@Override
	public Map<Long, String> fetchSpecIdAndSpecName() {
		List<Object[]> list=repo.getSpecIdAndSpecName();
		return list.stream().collect(Collectors.toMap(ob->(Long)ob[0], ob->(String)ob[1]));
	}
}
