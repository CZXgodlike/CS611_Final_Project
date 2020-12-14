/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Login window for BankATM
 */

package gui;

import util.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginWindow extends JFrame {
    private JFrame frame = new JFrame("Welcome to Bank ATM");
    private JPanel panel = new JPanel();
    private JLabel userLabel = new JLabel("Username:");
    private JTextField userText = new JTextField();
    private JLabel passLabel = new JLabel("Password:");
    private JPasswordField passText = new JPasswordField(20);
    private JButton registerButton = new JButton("register");
    private JButton loginButton = new JButton("login");
    
    public LoginWindow(){
        initListener();
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 200);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel){
        panel.setLayout(null);
        userLabel.setBounds(30, 30, 80, 25);
        panel.add(userLabel);
        userText.setBounds(105, 30, 165, 25);
        panel.add(userText);
        passLabel.setBounds(30, 60, 80, 25);
        panel.add(passLabel);
        passText.setBounds(105, 60, 165, 25);
        panel.add(passText);
        loginButton.setBounds(25, 100, 80, 25);
        panel.add(loginButton);
        registerButton.setBounds(190, 100, 80, 25);
        panel.add(registerButton);
    }
    
    public void initListener(){
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // check if user name exist in customer accounts
                String username = userText.getText();
                String pass = new String(passText.getPassword());
                // seek account info, currently just use default for testing
                if (ReadFileUtil.findMatch("data/customer.csv",0,username) && ReadFileUtil.findMatch("data/customer.csv",0,username)){
                    JOptionPane.showMessageDialog(null,"Login success");
                    return;
                }else{
                    JOptionPane.showMessageDialog(null,"Login Failed");
                    return;
                }
            }
        });
        
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // check if username exist
                // add info to customer.csv
                // display account window
                JOptionPane.showMessageDialog(null,"registered");
                frame.dispose();
                return;
            }
        });
    }
}
