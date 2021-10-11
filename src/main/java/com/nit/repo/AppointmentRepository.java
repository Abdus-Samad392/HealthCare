package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nit.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	
}
