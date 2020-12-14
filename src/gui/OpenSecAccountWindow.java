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

public class OpenSecAccountWindow extends JFrame {
    private JFrame frame = new JFrame("Open New Security SecAccount");
    private JPanel panel = new JPanel();
    private JLabel moneyLabel = new JLabel("Money add to Account:");
    private JTextField moneyText = new JTextField();
    private JLabel currencyType = new JLabel("Currency Type:");
    private JComboBox currencySelect = new JComboBox();
    private JButton submitButton = new JButton("confirm");
    private JButton backButton = new JButton("back");
    
    private AccountWindow prevWin;
    
    public OpenSecAccountWindow(AssociateSecAccountFrame prevWin){
        this.prevWin = prevWin;
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
        // money add to new secAccount
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
                String secAccountType = accSelect.getSelectedItem().toString();
                String money = moneyText.getText();
                String curType = currencySelect.getSelectedItem().toString();
                // for testing, should be add to secAccount info
                JOptionPane.showMessageDialog(null,uniqueID+" "+curType+" "+money);
                new SecurityAccount();
                frame.dispose();
                prevWin.setVisible(true);
            }
        });
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                // back to the customer window
                prevWin.setVisible(true);
            }
        });
    }
}
