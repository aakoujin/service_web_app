package com.company.mp5_s17511.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idItem;

    @NotBlank
    private String Name;

    @Min(1)
    private double price;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE,  CascadeType.REFRESH })
    @JoinTable(name = "service_in_order", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Orders> orders = new HashSet<Orders>();

    public Service() {
    }

    public Service(@NotBlank String name, @Min(1) double price) {
        Name = name;
        this.price = price;
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
                    o.removeService(this);
                }
            }
            this.orders = orders;
            for (Orders o : this.orders) {
                o.addService(this);
            }
        }
        if (this.orders.size() < 1) {
            this.orders = orders;
            for (Orders o : this.orders) {
                o.addService(this);
            }
        }
    }

    public void addOrder(Orders order) {
        if (order == null)
            throw new IllegalArgumentException("Illegal value");
        if (!this.orders.contains(order)) {
            this.orders.add(order);
            order.addService(this);
        }
    }

    public void removeOrder(Orders order) {
        if (order == null)
            throw new IllegalArgumentException("Illegal value");
        if (this.orders.contains(order)) {
            if (order.getItems().contains(this)) {
                this.orders.remove(order);
                order.removeService(this);
            }
        }
    }
}
