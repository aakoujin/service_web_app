package com.company.mp5_s17511.repository;

import com.company.mp5_s17511.model.Appointment;
import com.company.mp5_s17511.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepo extends CrudRepository<Appointment,Long> {


    //Optional<Appointment> findById(@Param("id") Long id);

    //Optional<Appointment> findByCustomer()


    @Override
    List<Appointment> findAll();
}
