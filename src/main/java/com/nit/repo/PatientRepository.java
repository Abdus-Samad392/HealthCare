package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("select count(patientMobileNo) from Patient where patientMobileNo=:patientMobileNo")
	Integer getMobileNoCount(Long patientMobileNo);
	@Query("select count(patientMobileNo) from Patient where patientMobileNo=:patientMobileNo and id!=:id")
	Integer getMobileNoCountWithId(Long patientMobileNo,Long id);
	
	Patient findByPatientName(String patientName);
}
