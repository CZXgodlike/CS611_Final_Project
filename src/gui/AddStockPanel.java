package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddStockPanel extends JPanel {

    private JTextField symbolField;
    private JTextField companyField;
    private JTextField lastField;
    private JTextField changeField;
    private JTextField changePercentageField;
    private JTextField volumeField;
    private JTextField tradedField;
    private JButton addButton;
    private JLabel symbolLabel;
    private JLabel companyLabel;
    private JLabel lastLabel;
    private JLabel changeLabel;
    private JLabel changePercentageLabel;
    private JLabel volumeLabel;
    private JLabel tradedLabel;
    private AddStockListener addStockListener;


    public AddStockPanel(){
        symbolField = new JTextField(10);
        companyField = new JTextField(10);
        changeField = new JTextField(10);
        changePercentageField = new JTextField(10);
        lastField = new JTextField(10);
        volumeField = new JTextField(10);
        tradedField = new JTextField(10);
        addButton = new JButton("add");
        symbolLabel = new JLabel("Symbol:");
        companyLabel = new JLabel("Company:");
        lastLabel = new JLabel("Last:");
        changeLabel = new JLabel("Chng:");
        changePercentageLabel = new JLabel("% Chng:");
        volumeLabel = new JLabel("Volume:");
        tradedLabel = new JLabel("Traded:");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String symbol = symbolField.getText();
                String company = companyField.getText();
                String last = lastField.getText();
                String change = changeField.getText();
                String changePercentage = changePercentageField.getText();
                String volume = volumeField.getText();
                String traded = tradedField.getText();

                AddStockEvent addStockEvent = new AddStockEvent(this, symbol, company, last, change, changePercentage, volume, traded);

                if(addStockListener != null){
                    try {
                        addStockListener.addStockEventOccurred(addStockEvent);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });


        Border innerBorder = BorderFactory.createTitledBorder("Add stock");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 0.1;


        // First row //

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);

        add(symbolLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(symbolField, gc);

        // Second row //

        gc.gridy = 1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(companyLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(companyField, gc);

        // Third row //

        gc.gridy = 2;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(lastLabel, gc);

        gc.gridy = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(lastField, gc);

        // Forth row //

        gc.gridy = 3;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(changeLabel, gc);

        gc.gridy = 3;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(changeField, gc);

        // Fifth row //

        gc.gridy = 4;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(changePercentageLabel, gc);

        gc.gridy = 4;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(changePercentageField, gc);

        // Sixth row //

        gc.gridy = 5;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(volumeLabel, gc);

        gc.gridy = 5;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(volumeField, gc);

        // Seventh row //

        gc.gridy = 6;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(tradedLabel, gc);

        gc.gridy = 6;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(tradedField, gc);

        // Last row //

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridy = 7;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 0, 0, 0);
        add(addButton, gc);

    }

    public void setAddStockListener(AddStockListener addStockListener) {
        this.addStockListener = addStockListener;
    }
}
