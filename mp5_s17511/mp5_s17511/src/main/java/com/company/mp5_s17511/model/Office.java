package com.company.mp5_s17511.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Office extends Building {

	
	private int capacity;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "office")
	private Set<OfficeWorker> officeWorkers = new HashSet<OfficeWorker>();

	public Office() {
	}

	public Office(String city, String street, String code, Set<OfficeWorker> officeWorkers, int capacity) {
		super(city, street, code);
		this.officeWorkers = officeWorkers;
		setCapacity(capacity);
	}

	public int getCapacity() {
		return capacity;
	}

	private void setCapacity(Integer capacity) {
		if (capacity == null || capacity < 0)
			throw new IllegalArgumentException("Invalid value");
		this.capacity = capacity;
	}

	public Set<OfficeWorker> getOfficeWorkers() {
		return new HashSet<OfficeWorker>(officeWorkers);
	}

	public void setOfficeWorkers(Set<OfficeWorker> officeWorkers) {
		if (officeWorkers == null)
			throw new IllegalArgumentException("Illegal value");
		if (officeWorkers.size() > 0) {
			if (this.officeWorkers.size() > 0) {
				for (OfficeWorker ow : this.officeWorkers) {
					ow.removeOffice(this);
				}
			}
			this.officeWorkers = officeWorkers;
			for (OfficeWorker ow : this.officeWorkers) {
				ow.setOffice(this);
			}
		}
		if (this.officeWorkers.size() < 1) {
			this.officeWorkers = officeWorkers;
			for (OfficeWorker o : this.officeWorkers) {
				o.setOffice(this);
			}
		}
	}

	public void addOfficeWorkers(OfficeWorker officeWorker) {
		if (officeWorker == null)
			throw new IllegalArgumentException("Illegal value");
		if (!this.officeWorkers.contains(officeWorker)) {
			this.officeWorkers.add(officeWorker);
			officeWorker.setOffice(this);
		}
	}

	public void removeOfficeWorkers(OfficeWorker officeWorker) {
		if (officeWorker == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.officeWorkers.contains(officeWorker)) {
			if (officeWorker.getOffice() == this) {
				this.officeWorkers.remove(officeWorker);
				officeWorker.removeOffice(this);
			}
		}
	}

	@Override
	public String toString() {
		return "Office [capacity=" + capacity + ", officeWorkers=" + officeWorkers + "]";
	}

	
}
