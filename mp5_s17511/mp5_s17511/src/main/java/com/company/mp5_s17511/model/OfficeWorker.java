package com.company.mp5_s17511.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue(value = "OW")
public class OfficeWorker extends Staff {

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "office_id", nullable = true)
	private Office office;

	@ManyToMany(mappedBy = "issuers")
	private Set<Policy> policies = new HashSet<Policy>();

	public OfficeWorker() {
	}

	public OfficeWorker(@NotBlank String name, @NotBlank String surname, @NotBlank LocalDate birthDate,
			@Min(2000) Double salary, @NotBlank Office office) {
		super(name, surname, birthDate, salary);
		setOffice(office);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		if (office == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.office != office) {
			this.office = office;
			office.addOfficeWorkers(this);
		}
	}

	public void removeOffice(Office office) {
		if (office == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.office == office) {
			this.office = null;
			office.removeOfficeWorkers(this);
		}
	}

	public Set<Policy> getPolicies() {
		return policies;
	}

	public void removeIssuedPolicy(Policy policy) {
		if (policy == null)
			throw new IllegalArgumentException("Illegal value");
		if (this.policies.contains(policy)) {
			if (policy.getIssuers().contains(this)) {
				this.policies.remove(policy);
				policy.removeIssuer(this);
			}
		}
	}

	public void addIssuedPolicy(Policy policy) {
		if (policy == null)
			throw new IllegalArgumentException("Illegal value");
		if (!this.policies.contains(policy)) {
			this.policies.add(policy);
			policy.addIssuer(this);
		}
	}

	@Override
	public String toString() {
		return "OfficeWorker [office=" + office + "]";
	}


}
