/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * GUI to diplay and take action in an customer account
 */

package gui;

import utils.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import account.*;

public class AccountWindow extends JFrame {
    private JFrame frame = new JFrame("Account Summary");
    private JPanel panel = new JPanel();
    private JButton depButton = new JButton("Deposit");
    private JButton wdButton = new JButton("Withdraw");
    private JButton transButton = new JButton("Transfer");
    private JButton actButton = new JButton("View Activity");
    private JButton secButton = new JButton("Open Security Account");
    private JButton backButton = new JButton("Back");

    private CustomerAccount curAccount;
    private CustomerMainFrame prevWin;
    
    public AccountWindow(CustomerAccount curAccount, CustomerMainFrame prevWin){
        this.curAccount = curAccount;
        this.prevWin = prevWin;
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
            if(Double.parseDouble(curAccount.getBalance())>5000){
                secButton.setBounds(150, 60, 130, 25);
                panel.add(secButton);
            }
        }else if (curAccount instanceof SavingAccount){
            panel.add(new JLabel("Saving Account "+curAccount.getId()));
        }else{
            panel.add(new JLabel("Securities Account "+curAccount.getId()));
        }
        panel.add(new JLabel("Current Balance: "+curAccount.getBalance()+" "+curAccount.getCurrencyType()));
    }
    
    private void placeComponents(JPanel panel){
        // deposit button
        depButton.setBounds(25, 30, 80, 25);
        panel.add(confirmButton);
        // withdraw buttom
        wdButton.setBounds(25, 60, 80, 25);
        panel.add(wdButton);
        // trans buttom
        transButton.setBounds(25, 90, 80, 25);
        panel.add(transButton);
        // act button
        actButton.setBounds(150, 30, 130, 25);
        panel.add(actButton);
        // set buttom
        secButton.setBounds(150, 90, 80, 25);
        panel.add(setButton);
    }
    
    public void initListener(){
        depButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                new DepositWindow(curAccount, this);
            }
        });
        
        wdButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                new WithdrawWindow(curAccount, this);
            }
        });
        
        transButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                new TransferWindow(curAccount, this);
            }
        });
        
        actButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                new ActivityHistoryFrame(curAccount.getId());
            }
        });
        
        secButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // return to previous window
                frame.dispose();
                new OpenSecAccountWindow(prevWin);
            }
        });
    }
    
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
