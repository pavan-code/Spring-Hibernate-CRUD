package com.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mobile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String mobile;
	private String operator;
	private String band;
	
	public Mobile() {
	}
	
	public Mobile(int id, String mobile, String operator, String band) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.operator = operator;
		this.band = band;
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	
	@Override
	public String toString() {
		return "Mobile [id=" + id + ", mobile=" + mobile + ", operator=" + operator + ", band=" + band + ", customer="
				+ "]";
	}
	
	
}
