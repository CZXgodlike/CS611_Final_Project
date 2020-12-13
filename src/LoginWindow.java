/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Login window for BankATM
 */
import java.util.*;
import javax.swing.*;

public class LoginWindow extends JFrame {
    private JFrame frame = new JFrame("Welcome");
    private JPanel panel = new JPanel();
    private JLabel userLabel = new JLabel("Username:");
    private JTextField userText = new JTextField();
    private JLabel passLabel = new JLabel("Password:");
    private JPasswordField passText = new JPasswordField(20);
    private JButton registerButton = new JButton("register");
    private JButton loginButton = new JButton("login");
    
    public LoginWindow() {
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
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
}
