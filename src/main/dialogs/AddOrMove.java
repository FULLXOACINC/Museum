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
public class AddOrMove implements Dialog {
    private final String EXHIBIT_NAME="EXHIBIT NAME";
    private final String ADRESS="ADRESS";
    private final String PHONE_NUMBER="PHONE NUMBER";
    private final String F_I_O="FIO";
    private final String EXHIBITON_ADRESS="EXHIBITON ADRESS";
    private final String EXHIBITON_NAME="EXHIBITON NAME";
    private final String EXHIBITON_START="EXHIBITON START";
    private final String EXHIBITON_END="EXHIBITON END";


    private JTextField exhibitName;
    private JTextField adress;
    private JTextField phoneNumber;
    private JTextField FIO;
    private JTextField exhibitionAdress;
    private JTextField exhibitionName;
    private JTextField exhibitionStart;
    private JTextField exhibitionEnd;

    private JFrame frame;

    public AddOrMove(String dialogType, DBService dbService) {
        frame = new JFrame(dialogType);
        frame.pack();

        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        JLabel labelText = new JLabel();
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID, labelText, 0, 0, 3, 1);

        String[] labelString = {EXHIBIT_NAME,ADRESS,PHONE_NUMBER,F_I_O,EXHIBITON_ADRESS,EXHIBITON_NAME,EXHIBITON_START,EXHIBITON_END};


        int y=1;
        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        exhibitName = new JTextField(30);
        AddComponent.add(jPanelID, exhibitName, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);
        y++;

        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        adress = new JTextField(30);
        AddComponent.add(jPanelID, adress, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);
        y++;

        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        phoneNumber = new JTextField(30);
        AddComponent.add(jPanelID, phoneNumber, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);
        y++;

        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        FIO = new JTextField(30);
        AddComponent.add(jPanelID, FIO, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);
        y++;

        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        exhibitionAdress = new JTextField(30);
        AddComponent.add(jPanelID, exhibitionAdress, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);
        y++;

        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        exhibitionName = new JTextField(30);
        AddComponent.add(jPanelID, exhibitionName, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);
        y++;

        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        exhibitionStart = new JTextField(30);
        AddComponent.add(jPanelID, exhibitionStart, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);
        y++;

        labelText = new JLabel(labelString[y-1]);
        AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
        exhibitionEnd= new JTextField(30);
        AddComponent.add(jPanelID, exhibitionEnd, 1, y, 3, 1);
        frame.add(jPanelID, BorderLayout.NORTH);


        JButton deleteButton = new JButton(dialogType);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dbService.addOrMove(getExhibitName(),getAdress(),getPhoneNumber(),getFIO(),getExhibitionAdress(),getExhibitionName(),Date.valueOf(getExhibitionStart()),Date.valueOf(getExhibitionEnd()));
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

    public String getExhibitName() {
        return exhibitName.getText();
    }

    public String getAdress() {
        return adress.getText();
    }

    public String getPhoneNumber() {
        return phoneNumber.getText();
    }

    public String getFIO() {
        return FIO.getText();
    }

    public String getExhibitionAdress() {
        return exhibitionAdress.getText();
    }

    public String getExhibitionName() {
        return exhibitionName.getText();
    }

    public String getExhibitionStart() {
        return exhibitionStart.getText();
    }

    public String getExhibitionEnd() {
        return exhibitionEnd.getText();
    }
}
