package com.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	@Query("select count(docEmailId) from Doctor where docEmailId=:docEmailId")
	public Integer getEmailIdCount(String docEmailId);
	@Query("select count(docMobileNo) from Doctor where docMobileNo=:docMobileNo")
	public Integer getMobileNoCount(Long docMobileNo);
	
	@Query("select count(docEmailId) from Doctor where docEmailId=:docEmailId and id!=:id")
	public Integer getEmailIdCountWithId(String docEmailId,Long id);
	@Query("select count(docMobileNo) from Doctor where docMobileNo=:docMobileNo and id!=:id")
	public Integer getMobileNoCountWithId(Long docMobileNo,Long id);
	
	@Query("select id,docName from Doctor")
	List<Object[]> getAllDoctorIdAndName();

}
