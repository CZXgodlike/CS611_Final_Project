/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Login window for BankATM
 */

package gui;

import utils.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DepositWindow extends JFrame {
    private JFrame frame = new JFrame("Deposit Money");
    private JPanel panel = new JPanel();
    private JLabel depositLabel = new JLabel("Amount deposit:");
    private JTextField depositText = new JTextField();
    private JButton confirmButton = new JButton("confirm");
    private JButton backButton = new JButton("back");
    
    public DepositWindow(Account curAccount, AccountWindow accWin){
        initListener();
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 200);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        displayInfo(curAccount, accWin);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void displayInfo(Account curAccount){
        panel.add(new JLabel("Account ID: "+curAccount.getAccountId()));
        panel.add(new JLabel("Account Type: "+curAccount.getAccountType()));
        panel.add(new JLabel("Current Balance: "+curAccount.getAmount()+" "+curAccount.getCurrency()));
    }
    
    private void placeComponents(JPanel panel){
        // Deposit money
        depositLabel.setBounds(30, 30, 80, 25);
        panel.add(depositLabel);
        depositText.setBounds(105, 30, 165, 25);
        panel.add(depositText);
        // confirm button
        confirmButton.setBounds(25, 100, 80, 25);
        panel.add(confirmButton);
        // back buttom
        backButton.setBounds(190, 100, 80, 25);
        panel.add(backButton);
    }
    
    public void initListener(){
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String money = depositText.getText();
                JOptionPane.showMessageDialog(null,"Deposit "+money+" to current account.");
                // add to account object
            }
        });
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                
            }
        });
    }
}
