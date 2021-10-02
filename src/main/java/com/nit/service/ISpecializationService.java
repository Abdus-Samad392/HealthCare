package com.nit.service;

import com.nit.entity.Specialization;

public interface ISpecializationService {

	String registerSpecialization(Specialization specialization);
	boolean isSpecCodeExist(String specCode);
	boolean isSpecNameExist(String specName);
}
