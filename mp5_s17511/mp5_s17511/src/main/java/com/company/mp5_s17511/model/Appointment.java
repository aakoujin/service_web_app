package com.company.mp5_s17511.model;


import javax.persistence.*;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String appointmentDate;

    private String timeFrame;

    @ManyToOne(optional = true, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;


    public Appointment() {
    }

    public Appointment(String appointmentDate, String timeFrame, Customer customer) {
        setAppointmentDate(appointmentDate);
        setTimeFrame(timeFrame);
        setCustomer(customer);
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setTimeFrame(String timeFrame) {
        this.timeFrame = timeFrame;
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customer = customer;
            customer.addAppointment(this);
        }

    }

    public Long getId() {
        return id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    public Customer getCustomer() {
        return customer;
    }
}
