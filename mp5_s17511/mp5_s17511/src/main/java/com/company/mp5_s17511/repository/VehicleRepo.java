package com.company.mp5_s17511.repository;

import java.util.List;
import java.util.Optional;

import com.company.mp5_s17511.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.mp5_s17511.model.Vehicle;
import org.springframework.data.repository.query.Param;

public interface VehicleRepo extends CrudRepository<Vehicle, Long> {


	Vehicle findByidVehicle(Long idVehicle);

	@Query("SELECT c FROM Vehicle c JOIN FETCH c.inspections where c.id = :id")
	public Optional<Vehicle> findById(@Param("id") Long id);


	List<Vehicle> findAll();
}
