package com.company.mp5_s17511.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.mp5_s17511.model.Building;
import com.company.mp5_s17511.model.Vehicle;

public interface BuildingRepo extends CrudRepository<Building, Long> {
	
	List<Building> findAll();
	
}
