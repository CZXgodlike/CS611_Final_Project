package gui;

import account.SecuritiesAccount;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SecurityAccountToolBar extends JPanel {

    private JButton stockMarketButton;
    private JButton tradingHistoryButton;
    private SecuritiesAccount account;
    private JLabel balanceLabel;

    public SecurityAccountToolBar(SecuritiesAccount account){
        stockMarketButton = new JButton("Stock Market");
        tradingHistoryButton = new JButton("Trading History");
        this.account = account;

        //TODO: get balance of the account
        balanceLabel = new JLabel("Balance: $" + this.account.getBalance());
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        balanceLabel.setBorder(new EmptyBorder(0, 0, 0, 50));

        add(balanceLabel);
        add(stockMarketButton);
        add(tradingHistoryButton);


        stockMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new StockMarketFrame(account);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        tradingHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new TradeHistoryTableFrame(account);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
