package com.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("select app.appointmentDate,app.noOfSlots,app.consultationFee,app.id from Appointment app inner join app.appointmentWithdoctor doc where doc.id=:id")
	List<Object[]> getAppointmentsRelatedToOneDoctor(Long id);
	
	@Query("select app from Appointment app inner join app.appointmentWithdoctor doc where doc.docName=:name")
	List<Appointment> getAppointmentByDoctorName(String name);
	
	@Modifying
	@Query("Update Appointment set noOfSlots=noOfSlots + :count where id=:id")
	void updateAppointmentSlots(Integer count,Long id);
	
}
