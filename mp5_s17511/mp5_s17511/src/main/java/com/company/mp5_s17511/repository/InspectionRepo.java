package com.company.mp5_s17511.repository;

import com.company.mp5_s17511.model.Customer;
import com.company.mp5_s17511.model.Inspection;
import com.company.mp5_s17511.model.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InspectionRepo extends CrudRepository<Inspection, Long> {

 //   @Query("SELECT c FROM Inspection c JOIN FETCH c.vehicles where c.id = :id")
  //  public Optional<Inspection> findById(@Param("id") Long id);

    public Set<Inspection> findByVehicle(@Param("Vehicle")Vehicle vehicle);
    
    List<Inspection> findAll();
}
