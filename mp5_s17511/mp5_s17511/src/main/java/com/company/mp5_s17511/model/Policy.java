package com.company.mp5_s17511.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String status;

    private String startDate;

    private String endDate;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,  CascadeType.REFRESH})
    @JoinColumn(name = "claimed_policy_id", referencedColumnName = "id", nullable = true)
    private ClaimedPolicy claimedPolicy;

    @ManyToOne(optional = true, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "insured_veh_id", nullable = true)
    private Vehicle insuredVehicle;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "policy_issue", joinColumns = @JoinColumn(name = "policy_id"), inverseJoinColumns = @JoinColumn(name = "office_worker_id"))
    private Set<OfficeWorker> issuers = new HashSet<OfficeWorker>();

    public Policy() {
    }

    public Policy(String status, String startDate, String endDate, Vehicle insuredVehicle) {
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.insuredVehicle = insuredVehicle;
    }


    private void setStatus(String status) {
        this.status = status;
    }

    private void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    private void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setInsuredVehicle(Vehicle insuredVehicle) {
        this.insuredVehicle = insuredVehicle;
    }

    public void setClaimedPolicy(ClaimedPolicy claimedPolicy) {
        this.claimedPolicy = claimedPolicy;
    }

    public ClaimedPolicy getClaimedPolicy() {
        return claimedPolicy;
    }


    public void removeIssuer(OfficeWorker officeWorker) {
        if (officeWorker == null)
            throw new IllegalArgumentException("Illegal value");
        if (this.issuers.contains(officeWorker)) {
            if (officeWorker.getPolicies().contains(this)) {
                this.issuers.remove(officeWorker);
                officeWorker.removeIssuedPolicy(this);
            }
        }
    }

    public void addIssuer(OfficeWorker officeWorker) {
        if (officeWorker == null)
            throw new IllegalArgumentException("Illegal value");
        if (!this.issuers.contains(officeWorker)) {
            this.issuers.add(officeWorker);
            officeWorker.addIssuedPolicy(this);
        }
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Vehicle getInsuredVehicle() {
        return insuredVehicle;
    }


    public Set<OfficeWorker> getIssuers() {
        return issuers;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", claimedPolicy=" + claimedPolicy +
                ", insuredVehicle=" + insuredVehicle +
                '}';
    }
}
