package main.dialogs;

import dbService.DBService;
import main.AddComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 * Created by alex on 7.11.17.
 */
public class AddFondMove implements Dialog{
    private final String FOND="FOND";
    private final String DATE="DATE";
    private final String ID="ID";

    private JTextField id;
    private JTextField fond;
    private JTextField date;


    private JFrame frame;

    public AddFondMove(String dialogType, DBService dbService) {
        frame = new JFrame(dialogType);
        frame.pack();

        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        JLabel labelText = new JLabel();
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID, labelText, 0, 0, 3, 1);

        String[] labelString = {ID,FOND,DATE};
        int y=1;
        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        id = new JTextField(30);
        AddComponent.add(jPanelID, id, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);
        y++;

        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        fond = new JTextField(30);
        AddComponent.add(jPanelID, fond, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);
        y++;

        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        date = new JTextField(30);
        AddComponent.add(jPanelID, date, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);


        JButton deleteButton = new JButton(dialogType);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int fond = dbService.getFondId(getFond());
                    dbService.addFondToFondMove(getId(),fond,Date.valueOf(getDate()).getTime());
                    JOptionPane.showMessageDialog(frame, "OK");

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Can't add");
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

    public String getDate() {
        return date.getText();
    }

    public String getFond() {
        return fond.getText();
    }

}
