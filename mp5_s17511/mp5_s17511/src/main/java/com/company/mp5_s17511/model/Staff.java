package com.company.mp5_s17511.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Staff implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String surname;

	private LocalDate birthDate;

	@Min(2000)
	private Double salary;

	public Staff() {
	}

	public Staff(@NotBlank String name, @NotBlank String surname, @NotBlank LocalDate birthDate,
			@Min(2000) Double salary) {
		super();
		setName(name);
		setSurname(surname);
		setBirthDate(birthDate);
		setSalary(salary);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Null value");
		if (name.equals("") || name.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if (surname == null)
			throw new IllegalArgumentException("Null value");
		if (surname.equals("") || surname.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.surname = surname;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		if (birthDate == null) {
			throw new IllegalArgumentException("Registration date cannot be null");
		}
		if (birthDate.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Registration date cannot be from the future");
		}
		this.birthDate = birthDate;
	}

	public Double getSalary() {
		return salary;
	}

	private void setSalary(Double salary) {
		if (salary == null || salary <= 0)
			throw new IllegalArgumentException("Invalid value");
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", salary="
				+ salary + "]";
	}

}
