package main.dialogs;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.AuthorDataSet;
import dbService.dataSets.ComplexDataSet;
import dbService.dataSets.KeeperDataSet;

import main.AddComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

/**
 * Created by alex on 22.10.17.
 */
public class ComplexSearchDialog implements Dialog{
        private final String LAST_NAME = "Complex:";
        private final String NOT_FOUND = "Not found";
        private JTextField complex;
        private JTable table;

        private JFrame frame;

        public ComplexSearchDialog(String dialogType, DBService dbService) {
            frame = new JFrame(dialogType);
            table =new JTable();
            frame.pack();

            frame.setVisible(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);



            JLabel labelText = new JLabel();
            JPanel jPanelID = new JPanel();
            jPanelID.setLayout(new GridBagLayout());
            labelText.setHorizontalAlignment(JLabel.CENTER);
            AddComponent.add(jPanelID,labelText, 0, 0, 3, 1);

            String[] labelString = {LAST_NAME};
            labelText = new JLabel(labelString[0]);
            AddComponent.add(jPanelID,labelText, 0, 1, 1, 1);

            complex = new JTextField(30);
            AddComponent.add(jPanelID, complex, 1, 1, 3, 1);

            frame.add(jPanelID, BorderLayout.NORTH);
            frame.add(table);
            JButton deleteButton = new JButton(dialogType);
            ActionListener actionListener =new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        List<ComplexDataSet> item= dbService.getByComplex(getComplex());
                        String col[] = {"name","author","anotation", "createDate", "complex"};
                        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
                        tableModel.addRow(col);
                        for (ComplexDataSet set :item) {
                            String name=set.getName();
                            AuthorDataSet author=set.getAuthor();
                            String anotation=set.getAnotation();
                            Date createDate=set.getCreateDate();
                            String complex=set.getComplex();

                            Object[] data = {name, author, anotation,createDate, complex};
                            tableModel.addRow(data);
                        }

                        table.setModel(tableModel);

                    } catch (DBException e1) {
                        JOptionPane.showMessageDialog(frame,NOT_FOUND);
                    }
                }
            };
            deleteButton.addActionListener(actionListener);
            frame.add(deleteButton, BorderLayout.SOUTH);
            frame.revalidate();
            frame.repaint();
        }

        private String getComplex() {
            return complex.getText();
        }

    }

