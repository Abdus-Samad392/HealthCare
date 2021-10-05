package com.nit.service;

import java.util.List;
import java.util.Map;

import com.nit.entity.Specialization;

public interface ISpecializationService {

	String registerSpecialization(Specialization specialization);
	boolean isSpecCodeExist(String specCode);
	boolean isSpecNameExist(String specName);
	List<Specialization> getAllSpecialization();
	Specialization getOneSpecialization(Long id);
	boolean isSpecCodeWithIdExist(String specCode,Long id);
	boolean isSpecNameWithIdExist(String specName,Long id);
	String updateSpecialization(Specialization spec);
	String deleteSpecializationById(Long id);
	Map<Long, String> fetchSpecIdAndSpecName();
}
