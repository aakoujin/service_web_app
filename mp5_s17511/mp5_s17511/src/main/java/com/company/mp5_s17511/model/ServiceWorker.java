package com.company.mp5_s17511.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue(value = "SW")
public class ServiceWorker extends Staff {

	@NotBlank
	private String spec;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,  CascadeType.REFRESH })
	@JoinColumn(name = "workshop_id", nullable = true)
	private Workshop workshop;

	public ServiceWorker() {
	}

	public ServiceWorker(@NotBlank String name, @NotBlank String surname, @NotBlank LocalDate birthDate,
			@Min(2000) Double salary, @NotBlank String spec, Workshop workshop) {
		super(name, surname, birthDate, salary);
		setWorkshop(workshop);
		setSpec(spec);
	}

	public String getSpec() {
		return spec;
	}

	private void setSpec(String spec) {
		if (spec == null)
			throw new IllegalArgumentException("Null value");
		if (spec.equals("") || spec.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.spec = spec;
	}

	public Workshop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(Workshop workshop) {
		if (workshop == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.workshop != workshop) {
			this.workshop = workshop;
			workshop.addServiceWorkers(this);
		}
	}

	public void removeWorkshop(Workshop workshop) {
		if (workshop == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.workshop == workshop) {
			this.workshop = null;
			workshop.removeServiceWorkers(this);
		}
	}

	@Override
	public String toString() {
		return "ServiceWorker [spec=" + spec + ", workshop=" + workshop + "]";
	}

}
