package com.company.mp5_s17511.model;

import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String date;

	@ManyToMany(mappedBy = "orders")
	private Set<Item> items = new HashSet<Item>();

	@ManyToMany(mappedBy = "vehorders")
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();

	@ManyToMany(mappedBy = "orders")
	private  Set<Service> services = new HashSet<>();

	public Orders() {

	}

	public Orders(@NotBlank String date) {
		setDate(date);
	}

	public String getDate() {
		return date;
	}

	private void setDate(String date) {
		if (date == null)
			throw new IllegalArgumentException("Null value");
		if (date.equals("") || date.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.date = date;
	}

	public Set<Item> getItems() {
		return new HashSet<Item>(items);
	}

	public void setItems(Set<Item> items) {
		if (items == null)
			throw new IllegalArgumentException("Illegal value");
		if (items.size() > 0) {
			if (this.items.size() > 0) {
				for (Item i : this.items) {
					i.removeOrder(this);
				}
			}
			this.items = items;
			for (Item i : this.items) {
				i.addOrder(this);
			}
		}
		if (this.items.size() < 1) {
			this.items = items;
			for (Item o : this.items) {
				o.addOrder(this);
			}
		}
	}

	public void addVehicle(Vehicle vehicle) {
		if (vehicle == null)
			throw new IllegalArgumentException("Illegal value");
		if (!this.vehicles.contains(vehicle)) {
			this.vehicles.add(vehicle);
			vehicle.addVehorder(this);
		}
	}

	public void removeVehicle(Vehicle vehicle) {
		if (vehicle == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.vehicles.contains(vehicle)) {
			if (vehicle.getVehorders().contains(this)) {
				this.vehicles.remove(vehicle);
				vehicle.removeVehorder(this);
			}
		}
	}

	public void addItem(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Illegal value");
		if (!this.items.contains(item)) {
			this.items.add(item);
			item.addOrder(this);
		}
	}

	public void removeItem(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.items.contains(item)) {
			if (item.getOrders().contains(this)) {
				this.items.remove(item);
				item.removeOrder(this);
			}
		}
	}

	public Set<Vehicle> getVehicles() {
		return new HashSet<Vehicle>(vehicles);
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		if (vehicles == null)
			throw new IllegalArgumentException("Illegal value");
		if (vehicles.size() > 0) {
			if (this.vehicles.size() > 0) {
				for (Vehicle i : this.vehicles) {
					i.removeVehorder(this);
				}
			}
			this.vehicles = vehicles;
			for (Vehicle i : this.vehicles) {
				i.addVehorder(this);
			}
		}
		if (this.vehicles.size() < 1) {
			this.vehicles = vehicles;
			for (Vehicle o : this.vehicles) {
				o.addVehorder(this);
			}
		}
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", items=" + items + "]";
	}

    public void removeService(Service service) {
    }

	public void addService(Service service) {
		if (service == null)
			throw new IllegalArgumentException("Illegal value");
		if (!this.services.contains(service)) {
			this.services.add(service);
			service.addOrder(this);
		}
	}
}
