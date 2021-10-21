package com.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("select app.appointmentDate,app.noOfSlots,app.consultationFee from Appointment app inner join app.appointmentWithdoctor doc where doc.id=:id")
	List<Object[]> getAppointmentsRelatedToOneDoctor(Long id);
	
}
