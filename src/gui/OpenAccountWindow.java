/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Register window for BankATM
 */
package gui;

import utils.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OpenAccountWindow extends JFrame {
    private JFrame frame = new JFrame("Open New Account");
    private JPanel panel = new JPanel();
    private JLabel moneyLabel = new JLabel("Money add to account:");
    private JTextField moneyText = new JTextField();
    private JLabel currencyType = new JLabel("Currency Type:");
    private JComboBox currencySelect = new JComboBox();
    private JButton submitButton = new JButton("confirm");
    private JButton backButton = new JButton("back");
    
    public OpenAccountWindow(){
        this.moneyText.setDocument(new NumericTextControl());
        initListener();
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 170);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel){
        panel.setLayout(null);
        // money add to new account
        moneyLabel.setBounds(30, 30, 150, 25);
        panel.add(moneyLabel);
        moneyText.setBounds(200, 30, 165, 25);
        panel.add(moneyText);
        // Currency selection
        currencyType.setBounds(30, 60, 150, 25);
        panel.add(currencyType);
        currencySelect.setBounds(200, 60, 165, 25);
        currencySelect.addItem("USD");
        currencySelect.addItem("CNY");
        panel.add(currencySelect);
        // button
        submitButton.setBounds(75, 100, 80, 25);
        panel.add(submitButton);
        backButton.setBounds(190, 100, 80, 25);
        panel.add(backButton);
    }
    
    public void initListener(){
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // generate unique UserID
                String uniqueID = UUID.randomUUID().toString();
                String money = moneyText.getText();
                String currencyType = currencySelect.getSelectedItem().toString();
                // for testing, should be add to account info
                JOptionPane.showMessageDialog(null,uniqueID+" "+currencyType+" "+money);
            }
        });
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Open registeration window and close login window
                frame.dispose();
            }
        });
    }
}
