package com.company.mp5_s17511.gui.controllers;

import com.company.mp5_s17511.gui.views.InspectionHistoryView;
import com.company.mp5_s17511.model.Customer;
import com.company.mp5_s17511.model.Inspection;
import com.company.mp5_s17511.model.Vehicle;
import com.company.mp5_s17511.repository.InspectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class InspectionHistoryController {
    private InspectionHistoryView view;

    @Autowired
    private InspectionRepo inspectionRepo;

    @Autowired
    private CustomerVehicleController cVController;

    @Autowired
    private MainWindowController localmainWindowController;

    private Vehicle tmp;
    private Set<Vehicle> tmpList;

    public InspectionHistoryController() {
        this.view = new InspectionHistoryView();
        initReturnListener();
    }

    public JPanel getView() {
        return view.getMainPannel();
    }

    public void initInspectionList(Vehicle selectedValue, Set<Vehicle> vehicles) {
        this.tmpList = vehicles;
        this.tmp = selectedValue;
        view.getHistoryList().setModel(new DefaultListModel<Inspection>());
        DefaultListModel<Inspection> listModel = (DefaultListModel<Inspection>) view.getHistoryList().getModel();
        listModel.removeAllElements();
        Set<Inspection> inspections = inspectionRepo.findByVehicle(selectedValue);

        for (Inspection i : inspections) {
            listModel.addElement(i);
        }

    }

    public void initReturnListener() {
        view.getReturnButton().addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {

                    cVController.initVehicleList(tmpList);
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
