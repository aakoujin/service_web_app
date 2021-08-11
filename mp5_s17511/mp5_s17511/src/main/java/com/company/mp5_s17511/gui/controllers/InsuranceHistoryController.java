package com.company.mp5_s17511.gui.controllers;

import com.company.mp5_s17511.gui.views.InsuranceHistoryView;
import com.company.mp5_s17511.model.Inspection;
import com.company.mp5_s17511.model.Policy;
import com.company.mp5_s17511.model.Vehicle;
import com.company.mp5_s17511.repository.PolicyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class InsuranceHistoryController {

    private InsuranceHistoryView view;
    private Set<Vehicle> tmpList;

    @Autowired
    private MainWindowController localmainWindowController;

    @Autowired
    private CustomerVehicleController cVController;

    @Autowired
    private PolicyRepo policyRepo;

    public InsuranceHistoryController() {
        this.view = new InsuranceHistoryView();
        initReturnListener();
        view.getInsuranceHistoryList().setModel(new DefaultListModel<Policy>());
    }

    public JPanel getView() {

        return view.getMainPannel();
    }

    public void initReturnListener() {
        view.getReturnButton().addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    System.out.println();
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

    public void initInsuranceList(Vehicle selectedValue, Set<Vehicle> tmpList) {
        this.tmpList = tmpList;
        DefaultListModel<Policy> listModel = (DefaultListModel<Policy>) view.getInsuranceHistoryList().getModel();
        listModel.removeAllElements();
        Set<Policy> policies = policyRepo.findByInsuredVehicle(selectedValue);

        for (Policy p : policies) {
            listModel.addElement(p);
        }

    }
}
