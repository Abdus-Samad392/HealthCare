package com.nit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Appointment {
	
	@Id
	@GenericGenerator(name="gen1",strategy = "increment")
	@GeneratedValue(generator = "gen1")
	@Column(name = "app_id_col")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id_col")
	private Doctor appointmentWithdoctor;
	
	@Column(name="app_date_col",nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date appointmentDate;
	
	@Column(name="app_slots_available_col")
	private Integer noOfSlots;
	
	@Column(name="app_details_col")
	private String appointmentDetails;
	
	@Column(name="consultation_fee_col")
	private Double consultationFee;
}
