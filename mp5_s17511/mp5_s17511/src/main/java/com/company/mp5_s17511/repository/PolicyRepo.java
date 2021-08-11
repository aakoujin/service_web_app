package com.company.mp5_s17511.repository;

import com.company.mp5_s17511.model.Customer;
import com.company.mp5_s17511.model.Policy;
import com.company.mp5_s17511.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PolicyRepo  extends CrudRepository<Policy, Long> {


    Set<Policy> findByInsuredVehicle(@Param("Insuredvehicle")Vehicle vehicle);

    @Override
    List<Policy> findAll();
}
