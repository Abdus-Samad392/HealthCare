package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class Specialization {
	@Id
	@GenericGenerator(name = "specGen",strategy = "increment")
	@GeneratedValue(generator = "specGen")
	@Column(name="spec_id_col")
	private Long id;
	@Column(name="spec_code_col",length = 10,nullable = false,unique = true)
	private String specCode;
	@Column(name="spec_name_col",length = 60,nullable = false,unique = true)
	private String specName;
	@Column(name="spec_note_col",length = 250,nullable = false)
	private String specNote;
}
