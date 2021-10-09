package com.nit.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@Entity
public class Patient {
	@Id
	@GenericGenerator(name="gen1",strategy = "increment")
	@GeneratedValue(generator = "gen1")
	@Column(name="pat_id_col")
	private Long id;
	@Column(name="pat_name_col",length = 30,nullable = false)
	private String patientName;
	@Column(name="pat_gender_col",length = 20,nullable = false)
	private String patientGender;
	@Column(name="pat_emailid_col",length = 30,nullable = false)
	private String patientEmailId;
	@Column(name="pat_mobileno_col",length = 20,nullable = false,unique = true)
	private Long patientMobileNo;
	@Column(name="pat_dob_col",length = 30,nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date patientDOB;
	@Column(name="pat_maritalstatus_col",length = 30,nullable = false)
	private String patientMaritalStatus;
	@Column(name="pat_presentaddress_col",length = 250,nullable = false)
	private String patientPresentAddress;
	@Column(name="pat_permanentaddress_col",length = 250,nullable = false)
	private String patientPermanentAddress;
	@Column(name="pat_pastmedicalhistory_col",length = 30,nullable = false)
	@ElementCollection
	@CollectionTable(name = "pat_medical_history")
	@JoinColumn(name = "pat_id")
	private Set<String> patientPastMedicalHistory;
	}
