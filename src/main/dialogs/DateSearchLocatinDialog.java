package main.dialogs;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.*;
import main.AddComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 * Created by alex on 25.10.17.
 */
public class DateSearchLocatinDialog implements Dialog {
    private final String INDEX = "Index:";
    private final String YEAR = "Year:";
    private final String DAY = "Day";
    private final String MOUNTH = "Mounth";
    private final String OK = "OK";
    private final String NOT_FOUND = "Not found";
    private final JTable table;
    private final JTable table1;
    private JTextField id;
    private JTextField date;



    private JFrame frame;

    public DateSearchLocatinDialog(String dialogType, DBService dbService) {
        frame = new JFrame(dialogType);
        frame.pack();
        table =new JTable();
        table1=new JTable();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        JLabel labelText = new JLabel();
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        labelText.setHorizontalAlignment(JLabel.CENTER);
        AddComponent.add(jPanelID, labelText, 0, 0, 3, 1);

        String[] labelString = {INDEX, YEAR, MOUNTH, DAY};
        labelText = new JLabel(labelString[0]);
        AddComponent.add(jPanelID, labelText, 0, 1, 1, 1);

        id = new JTextField(30);
        AddComponent.add(jPanelID, id, 1, 1, 3, 1);



        labelText = new JLabel(labelString[1]);
        AddComponent.add(jPanelID, labelText, 0, 2, 1, 1);

        date = new JTextField(30);
        AddComponent.add(jPanelID, date, 1, 2, 3, 1);


        frame.add(jPanelID, BorderLayout.NORTH);
        AddComponent.add(jPanelID, table, 0, 3, 4, 1);
        AddComponent.add(jPanelID, table1, 0, 4, 4, 1);
        //frame.add(table);
        //frame.add(table1);
        JButton deleteButton = new JButton(dialogType);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Date date = Date.valueOf(getDate());
                    FondToFondMoveDataSet fondLocation=dbService.getFondMove(getId(), date.getTime());
                    String col[] = {"index","start fond","end fond"};
                    DefaultTableModel tableModel = new DefaultTableModel(col, 0);

                    String fondStart=fondLocation.getStartFond();
                    String fondEnd=fondLocation.getEndFond();


                    Object[] data = {getId(), dbService.getFondName(Integer.parseInt(fondStart)), dbService.getFondName(Integer.parseInt(fondEnd))};

                    MuseumToOrganizationMoveDataSet set= dbService.getOrMove(getId(), date.getTime());
                    String cole[] = {"inventory_number","name","adress" ,"tel_number","FIO","exhibition_adress" ,"exhibition_name", "exhibition_start", "exhibition_end"};
                    DefaultTableModel tableModel1 = new DefaultTableModel(cole, 0);
                    Object[] data1 = {getId(), set.getExhibitName(),set.getAdress(),set.getPhoneNumber(),set.getFIO(),set.getExhibitionAdress(),set.getExhibitionName(),set.getExhibitionStart(),set.getExhibitionEnd()};
                    tableModel.addRow(col);
                    tableModel.addRow(data);

                    tableModel1.addRow(cole);
                    tableModel1.addRow(data1);

                    table.setModel(tableModel);
                    table1.setModel(tableModel1);

                } catch (IllegalArgumentException e2) {
                    JOptionPane.showMessageDialog(frame, "Неверная дата");
                } catch (Exception e1) {
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

    public String getDate() {
        return date.getText();
    }

}


