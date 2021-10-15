package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class User {
	
	@Id
	@GenericGenerator(name="gen1",strategy = "increment")
	@GeneratedValue(generator = "gen1")
	@Column(name="user_id_col")
	private Long id;
	@Column(name="user_name_col",nullable = false,length = 30)
	private String userName;
	@Column(name="user_emailId_col",nullable = false,length = 30,unique = true)
	private String emailId;
	@Column(name="user_pwd_col",nullable = false,unique = true)
	private String password;
	@Column(name="user_role_col",nullable = false,length = 30)
	private String userRole;
}
