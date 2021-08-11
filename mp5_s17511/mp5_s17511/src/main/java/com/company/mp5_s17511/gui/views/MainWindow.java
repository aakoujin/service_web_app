package com.company.mp5_s17511.gui.views;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JMenuItem menuItemCustomerList;

    public MainWindow()  {
        setTitle("MAS FINAL PROJECT");
        setSize(1024,768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initMenuBar();
    }

    private void initMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu menuCustomer = new JMenu("Customer");
        bar.add(menuCustomer);

        menuItemCustomerList = new JMenuItem("Customer list");
        menuCustomer.add(menuItemCustomerList);
        this.setJMenuBar(bar);

    }

    public JMenuItem getMenuItemCustomerList() {
        return menuItemCustomerList;
    }
}
