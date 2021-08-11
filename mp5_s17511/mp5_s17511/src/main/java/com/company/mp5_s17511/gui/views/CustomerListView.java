package com.company.mp5_s17511.gui.views;

import org.springframework.stereotype.Component;

import javax.swing.*;

public class CustomerListView {
    private JPanel mainPannel;
    private JList CustomerList;

    public JPanel getMainPannel() {
        return mainPannel;
    }

    public JList getCustomerList() {
        return CustomerList;
    }
}
