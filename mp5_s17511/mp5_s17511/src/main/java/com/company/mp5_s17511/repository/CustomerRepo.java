package com.company.mp5_s17511.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.company.mp5_s17511.model.Vehicle;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.mp5_s17511.model.Building;
import com.company.mp5_s17511.model.Customer;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends CrudRepository<Customer, Long>{
		
	Customer findByName(String Name);
	
	//@Query("SELECT c FROM Customer c LEFT JOIN FETCH c.vehicles")
	@Query ("SELECT c FROM Customer c JOIN FETCH c.vehicles where c.id = :id")
	public Optional<Customer> findById(@Param("id") Long id);


	@Override
	List<Customer> findAll();
}
