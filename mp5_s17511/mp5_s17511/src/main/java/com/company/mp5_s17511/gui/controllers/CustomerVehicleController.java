package com.company.mp5_s17511.gui.controllers;

import com.company.mp5_s17511.gui.views.CustomerVehicleView;
import com.company.mp5_s17511.gui.views.MainWindow;
import com.company.mp5_s17511.model.Inspection;
import com.company.mp5_s17511.model.Vehicle;
import com.company.mp5_s17511.repository.CustomerRepo;
import com.company.mp5_s17511.repository.InspectionRepo;
import com.company.mp5_s17511.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

@Component
public class CustomerVehicleController {
    private MainWindow mainWindow;
    private CustomerVehicleView view;
    private Set<Vehicle> tmpList;

    @Autowired
    private InspectionHistoryController inspectionHistoryController;

    @Autowired
    private AddVehicleController addVehicleController;

    @Autowired
    private MainWindowController localmainWindowController;

    @Autowired
    private InsuranceHistoryController insuranceHistoryController;

    @Autowired
    private CustomerListController customerListController;


    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private InspectionRepo inspectionRepo;

    @Autowired
    private VehicleRepo vehicleRepo;


    public CustomerVehicleController() {
        this.view = new CustomerVehicleView();
        initVehListListener();
        initInspectionHistoryListener();
        initInsuranceHistoryListener();
        initAddVehicleListener();

    }

    private void shiftToInspectionView(Vehicle selectedValue ) {
        SwingUtilities.invokeLater(() -> {
            inspectionHistoryController.initInspectionList(selectedValue, tmpList);
            localmainWindowController.showView(inspectionHistoryController.getView());
        });
    }

    private void shiftToIsuranceView(Vehicle selectedValue) {
        insuranceHistoryController.initInsuranceList(selectedValue, tmpList);

        localmainWindowController.showView(insuranceHistoryController.getView());
    }

    private void shiftToAddVehicleView(Vehicle selectedValue, Set<Vehicle> tmpList) {
        addVehicleController.initTmp(selectedValue,tmpList);
        localmainWindowController.showView(addVehicleController.getView());
    }

    private void initAddVehicleListener() {
        view.getAddVehicleButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Vehicle selectedValue = (Vehicle) view.getSelectVehicleJList().getSelectedValue();
                shiftToAddVehicleView(selectedValue ,tmpList);
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
    }


    private void initInspectionHistoryListener() {
        view.getInspectionHistoryButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Vehicle selectedValue = (Vehicle) view.getSelectVehicleJList().getSelectedValue();
                shiftToInspectionView(selectedValue);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //
            }
        });
    }

    private void initInsuranceHistoryListener() {
        view.getInsuranceHistoryButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Vehicle selectedValue = (Vehicle) view.getSelectVehicleJList().getSelectedValue();

                System.out.println("--------");
                shiftToIsuranceView(selectedValue);
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
    }


    private void initVehListListener() {
        view.getSelectVehicleJList().addListSelectionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                if (!e.getValueIsAdjusting()) {
                    Vehicle selectedValue = (Vehicle) view.getSelectVehicleJList().getSelectedValue();
                }


            });

        });
    }

    public void initVehicleList(Set<Vehicle> vehicleList) {
        this.tmpList = vehicleList;
        DefaultListModel<Vehicle> listModel = (DefaultListModel<Vehicle>) view.getSelectVehicleJList().getModel();
        listModel.removeAllElements();

        for (Vehicle v : vehicleList) {
            listModel.addElement(v);
        }

    }


    public JPanel getView() {
        return view.getMainPannel();
    }
}
