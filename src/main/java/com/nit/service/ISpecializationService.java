package com.nit.service;

import java.util.List;

import com.nit.entity.Specialization;

public interface ISpecializationService {

	String registerSpecialization(Specialization specialization);
	boolean isSpecCodeExist(String specCode);
	boolean isSpecNameExist(String specName);
	List<Specialization> getAllSpecialization();
	Specialization getOneSpecialization(Long id);
}
