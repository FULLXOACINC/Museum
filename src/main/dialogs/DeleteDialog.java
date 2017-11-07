package main.dialogs;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.AuthorDataSet;
import dbService.dataSets.ComplexDataSet;
import main.AddComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alex on 25.10.17.
 */
public class DeleteDialog implements Dialog {
    private final String INDEX = "Index:";
    private final String NAME = "Name:";
    private final String NOT_FOUND = "Not found";
    private final String OK = "Ok";
    private JTextField id;
    private JTextField name;

    private JFrame frame;

    public DeleteDialog(String dialogType, DBService dbService) {
        frame = new JFrame(dialogType);
        frame.pack();

        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        JLabel labelText = new JLabel();
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID, labelText, 0, 0, 3, 1);

        String[] labelString = {INDEX, NAME};
        labelText = new JLabel(labelString[0]);
        AddComponent.add(jPanelID, labelText, 0, 1, 1, 1);

        id = new JTextField(30);
        AddComponent.add(jPanelID, id, 1, 1, 3, 1);

        frame.add(jPanelID, BorderLayout.NORTH);

        labelText = new JLabel(labelString[1]);
        AddComponent.add(jPanelID, labelText, 0, 2, 1, 1);

        name = new JTextField(30);
        AddComponent.add(jPanelID, name, 1, 2, 3, 1);

        frame.add(jPanelID, BorderLayout.NORTH);

        JButton deleteButton = new JButton(dialogType);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dbService.deleteMuseumItems(getId(),getName().getText());
                    JOptionPane.showMessageDialog(frame, OK);

                } catch (DBException e1) {
                    JOptionPane.showMessageDialog(frame, NOT_FOUND);
                }
            }
        };
        deleteButton.addActionListener(actionListener);
        frame.add(deleteButton, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }

    public int getId() {
        return Integer.parseInt(id.getText());
    }

    public JTextField getName() {
        return name;
    }


}

