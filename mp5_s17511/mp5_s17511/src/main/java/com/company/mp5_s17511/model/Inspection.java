package com.company.mp5_s17511.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String inspectionDate;


    private boolean isPassed;

    @ManyToOne(optional = true, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "vehicle_id", nullable = true)
    private Vehicle vehicle;

    public Inspection() {
    }

    public Inspection(@NotBlank String inspectionDate, @NotBlank boolean isPassed, Vehicle vehicle) {
       setInspectionDate(inspectionDate);
        setPassed(isPassed);
        setVehicle(vehicle);
    }

    private void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    private void setPassed(boolean passed) {
        isPassed = passed;
    }

    public void setVehicle(Vehicle vehicle) {
        if(vehicle != null){
            this.vehicle = vehicle;
            if(!vehicle.getInspections().contains(this)){
                vehicle.addInspection(this);
            }
        }
    }


    public long getId() {
        return id;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


    @Override
    public String toString() {
        return "Inspection{" +
                "inspectionDate='" + inspectionDate + '\'' +
                ", isPassed=" + isPassed +
                ", vehicle=" + vehicle +
                '}';
    }
}
