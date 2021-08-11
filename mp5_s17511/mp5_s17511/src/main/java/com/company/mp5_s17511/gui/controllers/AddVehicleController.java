package com.company.mp5_s17511.gui.controllers;

import com.company.mp5_s17511.gui.views.AddVehicleView;
import com.company.mp5_s17511.model.Customer;
import com.company.mp5_s17511.model.Orders;
import com.company.mp5_s17511.model.Vehicle;
import com.company.mp5_s17511.repository.CustomerRepo;
import com.company.mp5_s17511.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

@Component
public class AddVehicleController {

    private AddVehicleView view;
    private Set<Vehicle> tmpList;
    private Customer owner;
    private  Long idTmp;

    @Autowired
    private MainWindowController localmainWindowController;

    @Autowired
    private CustomerVehicleController cVController;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public AddVehicleController() {
        this.view = new AddVehicleView();
        initReturnListener();
        initSubmitListener();


    }

    public void initTmp(Vehicle selectedValue, Set<Vehicle> tmpList) {
        this.tmpList = tmpList;
        this.owner = selectedValue.getOwner();
    }

    public JPanel getView() {
        return view.getMainPannel();
    }

    public void initSubmitListener() {
        view.getSubmitButton().addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String make = view.getMakeField().getText();
                    String model = view.getModelField().getText();
                    String year = view.getYearField().getText();
                    String vin = view.getVinField().getText();
                    String reg = view.getRegField().getText();

                    Vehicle newVehicle = new Vehicle (make,model,year, vin, reg,
                            owner, new HashSet<Orders>());

                    vehicleRepo.save(newVehicle);
                });

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                //
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //
            }
        });
    }


    public void initReturnListener() {
        view.getReturnButton().addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    System.out.println(" ");

                    for(Vehicle v : tmpList){
                        idTmp = v.getOwner().getId();
                        break;
                    }
                    Customer customerWithVehicles = customerRepo.findById(idTmp).get();

                    cVController.initVehicleList(customerWithVehicles.getVehicles());
                    localmainWindowController.showView(cVController.getView());
                });

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                //
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //
            }
        });
    }

}
