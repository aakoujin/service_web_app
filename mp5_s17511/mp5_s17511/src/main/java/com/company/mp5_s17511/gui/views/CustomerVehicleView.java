package com.company.mp5_s17511.gui.views;

import javax.swing.*;

public class CustomerVehicleView {
    private JPanel mainPannel;
    private JList VehicleInfoList;
    private JLabel InspectionStatusLabel;
    private JButton addVehicleButton;
    private JButton inspectionHistoryButton;
    private JButton appointmentButton;
    private JLabel InsuranceStatusLabel;
    private JButton placeARequestButton;
    private JButton insuranceHistoryButton;
    private JButton needARepairButton;
    private JList selectVehicleJList;
    private JScrollBar scrollBar1;

    public JPanel getMainPannel() {
        return mainPannel;
    }


    public JList getVehicleInfoList() {
        return VehicleInfoList;
    }

    public JLabel getInspectionStatusLabel() {
        return InspectionStatusLabel;
    }

    public JButton getAddVehicleButton() {
        return addVehicleButton;
    }

    public JButton getInspectionHistoryButton() {
        return inspectionHistoryButton;
    }

    public JButton getAppointmentButton() {
        return appointmentButton;
    }

    public JLabel getInsuranceStatusLabel() {
        return InsuranceStatusLabel;
    }

    public JButton getPlaceARequestButton() {
        return placeARequestButton;
    }

    public JButton getInsuranceHistoryButton() {
        return insuranceHistoryButton;
    }

    public JButton getNeedARepairButton() {
        return needARepairButton;
    }

    public JList getSelectVehicleJList() {
        return selectVehicleJList;
    }
}
