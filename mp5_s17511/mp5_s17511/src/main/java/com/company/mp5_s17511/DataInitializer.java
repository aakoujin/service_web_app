package com.company.mp5_s17511;

import com.company.mp5_s17511.model.*;
import com.company.mp5_s17511.repository.CustomerRepo;
import com.company.mp5_s17511.repository.InspectionRepo;
import com.company.mp5_s17511.repository.PolicyRepo;
import com.company.mp5_s17511.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Component
public class DataInitializer {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private InspectionRepo inspectionRepo;

    @Autowired
    private PolicyRepo policyRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    public void initData() {

/*
        Set<Orders> vehorders = new HashSet<Orders>();


        Customer c1 = new Customer("Name", "Surname");
        Vehicle v1 = new Vehicle("Fiat", "Punto", "2000", "testvin", "testreg", c1, vehorders);

        Vehicle v2 = new Vehicle("Fiat", "500", "2000", "testvin", "testreg", c1, vehorders);

        Inspection i1 = new Inspection("10-10-2020", true, v1);
        Inspection i2 = new Inspection("9-10-2020", false, v1);

        Inspection i3 = new Inspection("10-10-2020", true, v2);
        Inspection i4 = new Inspection("9-10-2020", false, v2);


        Policy p1 = new Policy("Valid", "01-08-2019", "01-08-2020", v1);
        Policy p2 = new Policy("Expired", "01-08-2018", "01-08-2019", v1);
        Policy p3 = new Policy("Valid", "01-08-2019", "01-08-2020", v2);
        Policy p4 = new Policy("Expired", "01-08-2018", "01-08-2019", v2);

        v1.addPolicy(p1);
        v1.addPolicy(p2);
        p1.setInsuredVehicle(v1);
        p2.setInsuredVehicle(v1);

        v2.addPolicy(p3);
        v2.addPolicy(p4);
        p3.setInsuredVehicle(v2);
        p3.setInsuredVehicle(v2);


        c1.addVehicle(v1);
        c1.addVehicle(v2);
        v1.setOwner(c1);
        v2.setOwner(c1);

        List<Policy> policies = new ArrayList<>();
        policies.add(p1);
        policies.add(p2);
        policies.add(p3);
        policies.add(p4);


        //customerRepo.save(c1);
        List<Vehicle> ss = new ArrayList<Vehicle>();
        ss.add(v1);
        ss.add(v2);

        List<Inspection> is = new ArrayList<Inspection>();
        is.add(i1);
        is.add(i2);
        is.add(i3);
        is.add(i4);
        System.out.println(v1.getInspections());


        List<Customer> check = customerRepo.findAll();

        if(!check.contains(c1)){
            //customerRepo.save(c1);
        }

        if(!vehicleRepo.findAll().containsAll(ss)){
            vehicleRepo.saveAll(ss);
        }

        if(!policyRepo.findAll().containsAll(policies)){
            policyRepo.saveAll(policies);
        }

       if(!inspectionRepo.findAll().containsAll(is)){
           inspectionRepo.saveAll(is);
       }


        //inspectionRepo.saveAll(is);
*/

    }
}
