package gui;

import assets.AccountInformation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AssociateAccountTablePanel extends JPanel {

    private JTable table;
    private AssociateAccountModel model;
    private String userName;

    public AssociateAccountTablePanel(String userName){
        model = new AssociateAccountModel();
        table = new JTable(model);

        Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border outerBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new BorderLayout());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);
                String accountType = (String) table.getModel().getValueAt(row, 0);
                String id = (String) table.getModel().getValueAt(row, 1);
                //TODO: Create an object of account

                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    //TODO: Create GUI of that account
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<AccountInformation> data){model.setData(data);}

}