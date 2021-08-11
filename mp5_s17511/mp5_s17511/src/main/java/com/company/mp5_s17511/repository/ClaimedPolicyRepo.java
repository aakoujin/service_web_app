package com.company.mp5_s17511.repository;

import com.company.mp5_s17511.model.ClaimedPolicy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClaimedPolicyRepo extends CrudRepository<ClaimedPolicy, Long> {


    @Override
    List<ClaimedPolicy> findAll();
}
