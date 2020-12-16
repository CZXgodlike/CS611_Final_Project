package gui;

import controller.AssociateAccountController;

import javax.swing.*;
import java.awt.*;

public class CustomerMainFrame extends JFrame {

    private AssociateAccountTablePanel tablePanel;
    private AssociateAccountController controller;
    private CustomerToolBar toolBar;

    public CustomerMainFrame(String userName){

        super("Accounts");

        tablePanel = new AssociateAccountTablePanel(userName);
        controller = new AssociateAccountController();
        toolBar = new CustomerToolBar();

        tablePanel.setData(controller.getData(userName));

        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(tablePanel, BorderLayout.CENTER);
        add(toolBar, BorderLayout.SOUTH);
    }
}
