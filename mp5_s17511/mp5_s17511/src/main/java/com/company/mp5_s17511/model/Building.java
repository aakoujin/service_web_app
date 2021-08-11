package com.company.mp5_s17511.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Building implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String city;
	private String street;
	private String code;

	public Building() {
	}

	public Building(String city, String street, String code) {
		setCity(city);
		setStreet(street);
		setCode(code);
	}

	public String getCity() {
		return city;
	}

	private void setCity(String city) {
		if (city == null)
			throw new IllegalArgumentException("Null value");
		if (city.equals("") || city.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	private void setStreet(String street) {
		if (street == null)
			throw new IllegalArgumentException("Null value");
		if (street.equals("") || street.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.street = street;
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		if (code == null)
			throw new IllegalArgumentException("Null value");
		if (code.equals("") || code.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.code = code;
	}

	@Override
	public String toString() {
		return "Building [id=" + id + ", city=" + city + ", street=" + street + ", code=" + code + "]";
	}
	

}
