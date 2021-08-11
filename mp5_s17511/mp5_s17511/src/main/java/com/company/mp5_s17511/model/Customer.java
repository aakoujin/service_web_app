package com.company.mp5_s17511.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name, surname;

	@Size(min = 1, message = "customer without vehicles")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	private Set<Vehicle> vehicles = new HashSet<>();


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Appointment> appointments = new HashSet<>();

	public Customer() {

	}

	public Customer(@NotBlank String name, @NotBlank String surname) {
		setName(name);
		setSurname(surname);
	}

	public void addAppointment(Appointment appointment){
		if(appointment != null){
			this.appointments.add(appointment);
		}
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Null value");
		if (name.equals("") || name.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	private void setSurname(String surname) {
		if (surname == null)
			throw new IllegalArgumentException("Null value");
		if (surname.equals("") || surname.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.surname = surname;
	}

	public Long getId() {
		return id;
	}

	public Set<Vehicle> getVehicles() {
		return new HashSet<Vehicle>(vehicles);
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		if (vehicles == null)
			throw new IllegalArgumentException("Illegal value");
		if (vehicles.size() > 0) {
			if (this.vehicles.size() > 0) {
				for (Vehicle v : this.vehicles) {
					v.removeOwner(this);
				}
			}
			this.vehicles = vehicles;
			for (Vehicle v : this.vehicles) {
				v.setOwner(this);
			}
		}
		if (this.vehicles.size() < 1) {
			this.vehicles = vehicles;
			for (Vehicle o : this.vehicles) {
				o.setOwner(this);
			}
		}
	}

	public void addVehicle(Vehicle vehicle) {
		if (vehicle == null)
			throw new IllegalArgumentException("Illegal value");
		if (!this.vehicles.contains(vehicle)) {
			this.vehicles.add(vehicle);
			vehicle.setOwner(this);
		}
	}

	public void removeVehicle(Vehicle vehicle) {
		if (vehicle == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.vehicles.contains(vehicle)) {
			if (vehicle.getOwner() == this) {
				this.vehicles.remove(vehicle);
				vehicle.removeOwner(this);
			}
		}
	}

	@Override
	public String toString() {
		return "Customer [" + " Name=" + name + ", surname=" + surname + " " + "]";
	}

}
