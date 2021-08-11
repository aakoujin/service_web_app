package com.company.mp5_s17511.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Workshop extends Building {

	@NotBlank
	private String type;

	@Min(2)
	private int places;
	@NotBlank
	private boolean hasCarWash;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workshop")
	private Set<ServiceWorker> serviceWorkers = new HashSet<ServiceWorker>();

	public Workshop() {
	}

	public Workshop(String city, String street, int places, String code, boolean hasCarWash,
			Set<ServiceWorker> serviceWorkers) {
		super(city, street, code);
		setHasCarWash(hasCarWash);
		setServiceWorkers(serviceWorkers);
		setPlaces(places);
	}

	public String getType() {
		return type;
	}

	private void setType(String type) {
		if (type == null)
			throw new IllegalArgumentException("Null value");
		if (type.equals("") || type.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.type = type;
	}

	public int getPlaces() {
		return places;
	}

	private void setPlaces(Integer places) {
		if (places == null || places < 1)
			throw new IllegalArgumentException("Illegal value");
		this.places = places;
	}

	public boolean isHasCarWash() {
		return hasCarWash;
	}

	private void setHasCarWash(Boolean hasCarWash) {
		if (hasCarWash == null)
			throw new IllegalArgumentException("Null value");
		this.hasCarWash = hasCarWash;
	}

	public Set<ServiceWorker> getServiceWorkers() {
		return new HashSet<ServiceWorker>(serviceWorkers);
	}

	public void setServiceWorkers(Set<ServiceWorker> serviceWorkers) {
		if (serviceWorkers == null)
			throw new IllegalArgumentException("Illegal value");
		if (serviceWorkers.size() > 0) {
			if (this.serviceWorkers.size() > 0) {
				for (ServiceWorker sw : this.serviceWorkers) {
					sw.removeWorkshop(this);
				}
			}
			this.serviceWorkers = serviceWorkers;
			for (ServiceWorker sw : this.serviceWorkers) {
				sw.setWorkshop(this);
			}
		}
	}

	public void addServiceWorkers(ServiceWorker serviceWorker) {
		if (serviceWorker == null)
			throw new IllegalArgumentException("Illegal value");
		if (!this.serviceWorkers.contains(serviceWorker)) {
			this.serviceWorkers.add(serviceWorker);
			serviceWorker.setWorkshop(this);
		}
	}

	public void removeServiceWorkers(ServiceWorker serviceWorker) {
		if (serviceWorker == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.serviceWorkers.contains(serviceWorker)) {
			if (serviceWorker.getWorkshop() == this) {
				this.serviceWorkers.remove(serviceWorker);
				serviceWorker.removeWorkshop(this);
			}
		}
	}

	@Override
	public String toString() {
		return "Workshop [type=" + type + ", places=" + places + ", hasCarWash=" + hasCarWash + ", serviceWorkers="
				+ serviceWorkers + "]";
	}

}
