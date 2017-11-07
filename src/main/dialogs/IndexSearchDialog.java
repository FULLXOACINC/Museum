package main.dialogs;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.AuthorDataSet;
import dbService.dataSets.KeeperDataSet;
import dbService.dataSets.MuseumItemsDataSet;
import main.AddComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 * Created by alex on 22.10.17.
 */
public class IndexSearchDialog implements Dialog{
        private final String LAST_NAME = "Index:";
        private final String NOT_FOUND = "Not found";
        private JTextField index;
        private JTable table;

        private JFrame frame;

        public IndexSearchDialog(String dialogType, DBService dbService) {
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

            index = new JTextField(30);
            AddComponent.add(jPanelID, index, 1, 1, 3, 1);

            frame.add(jPanelID, BorderLayout.NORTH);
            frame.add(table);
            JButton deleteButton = new JButton(dialogType);
            ActionListener actionListener =new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        MuseumItemsDataSet item= dbService.getMuseumItem(getIndex());
                        String col[] = {"index","author","anotation", "name", "createDate", "complex", "fond", "keeper"};
                        DefaultTableModel tableModel = new DefaultTableModel(col, 0);


                        int id=item.getId();
                        AuthorDataSet author=item.getAuthor();
                        String anotation=item.getAnotation();
                        String name=item.getName();
                        Date createDate=item.getCreateDate();
                        String complex=item.getComplex();
                        String fond=item.getFond();
                        KeeperDataSet keeper=item.getKeeper();

                        Object[] data = {id, author, anotation, name, createDate, complex,
                                fond, keeper};

                        tableModel.addRow(col);
                        tableModel.addRow(data);
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

        public JFrame getFrame() {
            return frame;
        }

        public int getIndex() {
            return Integer.parseInt(index.getText());
        }

    }


