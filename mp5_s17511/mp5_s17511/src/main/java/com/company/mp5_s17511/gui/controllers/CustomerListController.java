package com.company.mp5_s17511.gui.controllers;

import com.company.mp5_s17511.gui.views.CustomerListView;
import com.company.mp5_s17511.model.Customer;
import com.company.mp5_s17511.model.Vehicle;
import com.company.mp5_s17511.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class CustomerListController {

    private CustomerListView view;

    @Autowired
    private CustomerVehicleController cvController;

    @Autowired
    private MainWindowController localmainWindowController;

    @Autowired
    private CustomerRepo customerRepo;


    public CustomerListController() {
        this.view = new CustomerListView();
        initListModel();
        initListListeners();

    }

    private void initListListeners() {
        view.getCustomerList().addListSelectionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                if (!e.getValueIsAdjusting()) {
                    Customer selectedValue = (Customer) view.getCustomerList().getSelectedValue();
                    Customer customerWithVehicles = customerRepo.findById(selectedValue.getId()).get();
                    shiftToNextView(customerWithVehicles);
                }
            });
        });
    }

    private void shiftToNextView(Customer customerWithVehicles) {
        cvController.initVehicleList(customerWithVehicles.getVehicles());
        localmainWindowController.showView(cvController.getView());
    }

    private void initListModel() {
        view.getCustomerList().setModel(new DefaultListModel<Customer>());

    }

    public void showGUI(MainWindowController mainWindowController) {
        updateCustomerListFromDB();
        mainWindowController.showView(view.getMainPannel());
    }

    private void updateCustomerListFromDB() {
        List<Customer> customers = customerRepo.findAll();
        DefaultListModel<Customer> listModel = (DefaultListModel<Customer>) view.getCustomerList().getModel();
        listModel.removeAllElements();

        for (Customer c : customers) {
            listModel.addElement(c);
        }
    }
}
