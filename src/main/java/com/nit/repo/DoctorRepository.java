package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
