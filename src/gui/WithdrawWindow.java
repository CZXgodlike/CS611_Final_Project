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

public class WithdrawWindow extends JFrame {
    private JFrame frame = new JFrame("Withdraw Money");
    private JPanel panel = new JPanel();
    private JLabel withdrawLabel = new JLabel("Amount withdraw:");
    private JTextField withdrawText = new JTextField();
    private JButton confirmButton = new JButton("confirm");
    private JButton backButton = new JButton("back");

    private CustomerAccount curAccount;
    private AccountWindow prevWin;
    
    public WithdrawWindow(CustomerAccount curAccount, AccountWindow prevWin){
        this.curAccount = curAccount;
        this.prevWin = prevWin;
        this.withdrawText.setDocument(new NumericTextControl());
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

    private void displayInfo(CustomerAccount curAccount){
        if (curAccount instanceof CheckingAccount){
            panel.add(new JLabel("Checking Account "+curAccount.getId()));
        }else{
            panel.add(new JLabel("Saving Account "+curAccount.getId()));
        }
        panel.add(new JLabel("Current Balance: "+curAccount.getAmount()+" "+curAccount.getCurrency()));
    }
    
    private void placeComponents(JPanel panel){
        // Withdraw money
        withdrawLabel.setBounds(30, 30, 80, 25);
        panel.add(withdrawLabel);
        withdrawText.setBounds(105, 30, 165, 25);
        panel.add(withdrawText);
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
                String money = withdrawText.getText();
                JOptionPane.showMessageDialog(null,"Withdraw "+money+" from current account.");
                // add to account object
                curAccount.subBalance(money);
                prevWin.setVisible(true);
            }
        });
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                prevWin.setVisible(true);
            }
        });
    }
}
