/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * main ATM class
 */
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;

public class BankATM{
    
    public JFrame window;
    
    /** Default Constructor */
    public BankATM(){
        window = new JFrame("BankATM")
        window.setSize(400,200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        JLabel jl = new JLabel("Welcome to Bank ATM, please login to start.");
        jp.add(jl);
        JTextField nameText = new JTextField();
        nameText.setText("Username");
        JTextField passText = new JTextField();
        passText.setText("Password");
        jp.add(nameText);
        jp.add(passText);
        JButton submit = new JButton("Submit");
        window.setVisible(true);
    }
    
    // Check login
    public void login(){
    }
}
