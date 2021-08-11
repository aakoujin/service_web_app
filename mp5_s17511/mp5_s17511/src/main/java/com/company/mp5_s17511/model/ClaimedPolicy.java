package com.company.mp5_s17511.model;


import javax.persistence.*;




@Entity
public class ClaimedPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String claimDate;

    private String claimCat;

    @OneToOne(mappedBy = "claimedPolicy")
    private Policy policy;


    public ClaimedPolicy() {
    }


    public ClaimedPolicy(String claimDate, String claimCat, Policy policy) {
        setClaimDate(claimDate);
        setClaimCat(claimCat);
       setPolicy(policy);
    }

    private void setPolicy(Policy policy) {
        if(policy != null)
        this.policy = policy;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public void setClaimCat(String claimCat) {
        this.claimCat = claimCat;
    }

    public long getId() {
        return id;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public String getClaimCat() {
        return claimCat;
    }

    public Policy getPolicy() {
        return policy;
    }

    @Override
    public String toString() {
        return "ClaimedPolicy{" +
                "id=" + id +
                ", claimDate='" + claimDate + '\'' +
                ", claimCat=" + claimCat +
                ", policy=" + policy +
                '}';
    }
}
