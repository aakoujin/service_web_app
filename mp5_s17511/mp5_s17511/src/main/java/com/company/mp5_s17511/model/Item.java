package com.company.mp5_s17511.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idItem;

	@NotBlank
	private String Name;

	@Min(1)
	private double price;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "items_in_order", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Set<Orders> orders = new HashSet<Orders>();

	public Item() {
	}

	public Item(@NotBlank String name, @NotBlank double price) {
		setName(name);
		setPrice(price);
	}

	public Long getIdItem() {
		return idItem;
	}

	public String getName() {
		return Name;
	}

	private void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Null value");
		if (name.equals("") || name.equals(" "))
			throw new IllegalArgumentException("Illegal value");
		this.Name = name;
	}

	public double getPrice() {
		return price;
	}

	private void setPrice(Double price) {
		if (price == null || price <= 0)
			throw new IllegalArgumentException("Invalid value");
		this.price = price;
	}

	public Set<Orders> getOrders() {
		return new HashSet<Orders>(orders);
	}

	public void setOrders(Set<Orders> orders) {
		if (orders == null)
			throw new IllegalArgumentException("Illegal value");
		if (orders.size() > 0) {
			if (this.orders.size() > 0) {
				for (Orders o : this.orders) {
					o.removeItem(this);
				}
			}
			this.orders = orders;
			for (Orders o : this.orders) {
				o.addItem(this);
			}
		}
		if (this.orders.size() < 1) {
			this.orders = orders;
			for (Orders o : this.orders) {
				o.addItem(this);
			}
		}
	}

	public void addOrder(Orders order) {
		if (order == null)
			throw new IllegalArgumentException("Illegal value");
		if (!this.orders.contains(order)) {
			this.orders.add(order);
			order.addItem(this);
		}
	}

	public void removeOrder(Orders order) {
		if (order == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.orders.contains(order)) {
			if (order.getItems().contains(this)) {
				this.orders.remove(order);
				order.removeItem(this);
			}
		}
	}

	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", Name=" + Name + ", price=" + price + ", orders=" + orders + "]";
	}

}
