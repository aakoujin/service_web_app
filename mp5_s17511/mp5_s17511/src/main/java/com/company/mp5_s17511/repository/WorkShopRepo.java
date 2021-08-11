package com.company.mp5_s17511.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.mp5_s17511.model.Building;
import com.company.mp5_s17511.model.Workshop;

public interface WorkShopRepo extends CrudRepository<Workshop, Long> {
	List<Workshop> findAll();
}
