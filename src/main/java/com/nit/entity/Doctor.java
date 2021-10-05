package com.nit.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class Doctor {
	@Id
	@Column(name="doc_id_col")
	@GenericGenerator(name="gen1",strategy = "increment")
	@GeneratedValue(generator = "gen1")
	private Long id;
	@Column(name="doc_name_col",nullable = false,length = 30)
	private String docName;
	@Column(name="doc_email_col",nullable = false,unique = true, length = 40)
	private String docEmailId;
	@ManyToOne
	@JoinColumn(name = "spec_id_fk")
	private Specialization docSpecialization;
	@Column(name="doc_address_col",nullable = false,length = 250)
	private String docAddress;
	@Column(name="doc_mobile_col",nullable = false,unique = true,length = 15)
	private Long docMobileNo;
	@Column(name="doc_gender_col",nullable = false,length = 10)
	private String docGender;
	@Column(name="doc_note_col",nullable = false,length = 250)
	private String docNote;
	@Column(name="doc_photoloc_col",nullable = false)
	private String docPhotoLoc;
	
}
