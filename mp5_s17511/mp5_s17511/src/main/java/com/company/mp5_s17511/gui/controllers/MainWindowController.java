package com.company.mp5_s17511.gui.controllers;

import com.company.mp5_s17511.gui.views.MainWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainWindowController {

    private MainWindow view;

    @Autowired
    private CustomerListController customerListController;


    public MainWindowController() {
        view = new MainWindow();
        initMenuListeners();
        initCustomerSelectListener();
    }

    private void initCustomerSelectListener() {
    }

    private void initMenuListeners() {
        view.getMenuItemCustomerList().addActionListener(e -> {
            System.out.println("MainWindowController.initMenuListeners" + e);
            SwingUtilities.invokeLater(()->{
                customerListController.showGUI(this);
            });
        });
    }



    public  void showGUI(){
        view.setVisible(true);
    }

    public JMenuItem getMenuItem(){
        return view.getMenuItemCustomerList();
    }


    public JMenuItem getCustMenu(){
        return view.getMenuItemCustomerList();
    }

    public void showView(JPanel viewToShow){
        view.getContentPane().removeAll();
        view.getContentPane().add(viewToShow);
        view.revalidate();
    }

    public MainWindow getView(){
        return view;
    }

}
