package com.company.mp5_s17511.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVehicle;

    @NotBlank
    private String Make;

    @NotBlank
    private String Model;

    @NotBlank
    private String ProductionYear;

    @NotBlank
    private String vin;

    @NotBlank
    private String reg;

    @ManyToOne(optional = true, cascade = {CascadeType.DETACH, CascadeType.MERGE,  CascadeType.REFRESH})
    @JoinColumn(name = "owner_id", nullable = true)
    private Customer owner;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "vehicles_in_order", joinColumns = @JoinColumn(name = "vehicle_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Orders> vehorders = new HashSet<Orders>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
    private Set<Inspection> inspections = new HashSet<Inspection>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "insuredVehicle")
    private Set<Policy> policies = new HashSet<Policy>();

    public Vehicle() {

    }

    public Vehicle(@NotBlank String make, @NotBlank String model, @NotBlank String productionYear, @NotBlank String vin,
                   @NotBlank String reg, Customer owner, Set<Orders> vehorders) {
        super();
        setMake(make);
        setModel(model);
        setProductionYear(productionYear);
        setVin(vin);
        setReg(reg);
        setOwner(owner);
        setVehorders(vehorders);
    }

    public void addInspection(Inspection inspection) {
        if (inspection != null) {
            this.inspections.add(inspection);
            if (inspection.getVehicle() != this) {
                inspection.setVehicle(this);
            }
        }
    }

    public void addPolicy(Policy policy) {
        if (policy != null) {
            this.policies.add(policy);
            if (policy.getInsuredVehicle() != this) {
                policy.setInsuredVehicle(this);
            }
        }
    }

    public void removePolicy(Policy policy) {
        if (policy != null) {
            if (this.policies.contains(policy) && policy.getInsuredVehicle().equals(this)) {
                this.policies.remove(policy);

                policy.setInsuredVehicle(null);
            }
        }
    }


    public Set<Inspection> getInspections() {
        return inspections;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        if (make == null)
            throw new IllegalArgumentException("Null value");
        if (make.equals("") || make.equals(" "))
            throw new IllegalArgumentException("Illegal value");
        this.Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        if (model == null)
            throw new IllegalArgumentException("Null value");
        if (model.equals("") || model.equals(" "))
            throw new IllegalArgumentException("Illegal value");
        this.Model = model;
    }

    public String getProductionYear() {
        return ProductionYear;
    }

    public void setProductionYear(String productionYear) {
        if (productionYear == null)
            throw new IllegalArgumentException("Null value");
        if (productionYear.equals("") || productionYear.equals(" "))
            throw new IllegalArgumentException("Illegal value");
        this.ProductionYear = productionYear;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        if (vin == null)
            throw new IllegalArgumentException("Null value");
        if (vin.equals("") || vin.equals(" "))
            throw new IllegalArgumentException("Illegal value");
        this.vin = vin;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        if (reg == null)
            throw new IllegalArgumentException("Null value");
        if (reg.equals("") || reg.equals(" "))
            throw new IllegalArgumentException("Illegal value");
        this.reg = reg;
    }

    public Customer getOwner() {
        return owner;
    }

    public Set<Orders> getVehorders() {
        return new HashSet<Orders>(vehorders);
    }

    public void setVehorders(Set<Orders> vehorders) {
        if (vehorders == null)
            throw new IllegalArgumentException("Illegal value");
        if (vehorders.size() > 0) {
            if (this.vehorders.size() > 0) {
                for (Orders vo : this.vehorders) {
                    vo.removeVehicle(this);
                }
            }
            this.vehorders = vehorders;
            for (Orders ov : this.vehorders) {
                ov.addVehicle(this);
            }
        }
        if (this.vehorders.size() < 1) {
            this.vehorders = vehorders;
            for (Orders o : this.vehorders) {
                o.addVehicle(this);
            }
        }
    }

    public void setOwner(Customer owner) {
        if (owner == null)
            throw new IllegalArgumentException("Illegal value");
        if (this.owner != owner) {
            this.owner = owner;
            owner.addVehicle(this);
        }
    }

    public void removeOwner(Customer owner) {
        if (owner == null)
            throw new IllegalArgumentException("Illegal value");
        if (this.owner == owner) {
            this.owner = null;
            owner.removeVehicle(this);
        }
    }

    public void removeVehorder(Orders orders) {
        if (orders == null)
            throw new IllegalArgumentException("Illegal value");
        if (this.vehorders.contains(orders)) {
            if (orders.getVehicles().contains(this)) {
                this.vehorders.remove(orders);
                orders.removeVehicle(this);
            }
        }
    }

    public void addVehorder(Orders orders) {
        if (orders == null)
            throw new IllegalArgumentException("Illegal value");
        if (!this.vehorders.contains(orders)) {
            this.vehorders.add(orders);
            orders.addVehicle(this);
        }
    }

    public Set<Policy> getPolicies() {
        return policies;
    }

    @Override
    public String toString() {
        return "Vehicle [" + "Make=" + Make + ", Model=" + Model + ", reg=" + reg + "]";
    }

}
