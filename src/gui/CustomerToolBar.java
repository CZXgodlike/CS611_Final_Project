package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerToolBar extends JPanel {

    private JButton newAccountButton;
    private JButton changePasswordButton;

    public CustomerToolBar(){

        newAccountButton = new JButton("New account");
        changePasswordButton = new JButton("Change password");

        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(newAccountButton);
        add(changePasswordButton);

        newAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:
            }
        });
    }
}
