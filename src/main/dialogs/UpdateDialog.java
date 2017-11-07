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
public class UpdateDialog implements Dialog {
    private final String ID="ID";
        private final String AUTHOR_NAME="AUTHOR NAME";
        private final String AUTHOR_SEDOND_NAME="AUTHOR SEDOND NAME";
        private final String AUTHOR_FATHER_NAME="AUTHOR FATHER NAME";
        private final String BIRTH_DATE="BIRTH DATE";
        private final String NAME="NAME";
        private final String ANATATION="ANATATION";
        private final String CREATE_DATE="CREATE DATE";
        private final String COMPLEX="COMPLEX";
        private final String FOND="FOND";
        private final String KEEPER_NAME="KEEPER NAME";
        private final String KEEPER_SECOND_NAME="KEEPER SECOND NAME";
        private final String KEEPER_FATHER_NAME="KEEPER FATHER NAME";

        private JTextField id;
        private JTextField authorName;
        private JTextField authorSecondName;
        private JTextField authorFatherName;
        private JTextField birthData;
        private JTextField name;
        private JTextField anotation;
        private JTextField createDate;
        private JTextField complex;
        private JTextField fond;
        private JTextField keeperName;
        private JTextField keeperSecondName;
        private JTextField keeperFatherName;

        private JFrame frame;

        public UpdateDialog(String dialogType, DBService dbService) {
            frame = new JFrame(dialogType);
            frame.pack();

            frame.setVisible(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


            JLabel labelText = new JLabel();
            JPanel jPanelID = new JPanel();
            jPanelID.setLayout(new GridBagLayout());
            labelText.setHorizontalAlignment(JLabel.CENTER);
            AddComponent.add(jPanelID, labelText, 0, 0, 3, 1);

            String[] labelString = {ID,AUTHOR_NAME,AUTHOR_SEDOND_NAME,AUTHOR_FATHER_NAME,BIRTH_DATE,
                    NAME,ANATATION,CREATE_DATE,COMPLEX,FOND,KEEPER_NAME,KEEPER_SECOND_NAME,
                    KEEPER_FATHER_NAME};
            int y=1;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            id = new JTextField(30);
            AddComponent.add(jPanelID, id, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            authorName = new JTextField(30);
            AddComponent.add(jPanelID, authorName, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            authorSecondName = new JTextField(30);
            AddComponent.add(jPanelID, authorSecondName, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            authorFatherName = new JTextField(30);
            AddComponent.add(jPanelID, authorFatherName, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            birthData = new JTextField(30);
            AddComponent.add(jPanelID, birthData, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            name = new JTextField(30);
            AddComponent.add(jPanelID, name, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            anotation = new JTextField(30);
            AddComponent.add(jPanelID, anotation, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            createDate = new JTextField(30);
            AddComponent.add(jPanelID, createDate, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            complex = new JTextField(30);
            AddComponent.add(jPanelID, complex, 1, y, 3, 1);
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
            keeperName = new JTextField(30);
            AddComponent.add(jPanelID, keeperName, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            keeperSecondName = new JTextField(30);
            AddComponent.add(jPanelID, keeperSecondName, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);
            y++;

            labelText = new JLabel(labelString[y-1]);
            AddComponent.add(jPanelID, labelText, 0, y, 1, 1);
            keeperFatherName = new JTextField(30);
            AddComponent.add(jPanelID, keeperFatherName, 1, y, 3, 1);
            frame.add(jPanelID, BorderLayout.NORTH);

            JButton deleteButton = new JButton(dialogType);
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        dbService.updateMuseumItems(getId(),getAuthorName(),getAuthorSecondName(),getAuthorFatherName(),
                                Date.valueOf(getBirthData()).getTime(),getName(),getAnotation(),
                                Date.valueOf(getCreateDate()).getTime(),getComplex(),getFond(),
                                getKeeperName(),getKeeperSecondName(),getKeeperFatherName());
                        JOptionPane.showMessageDialog(frame, "OK");

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(frame, "Can't update");
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

        public String getAuthorName() {
            return authorName.getText();
        }

        public String getAuthorSecondName() {
            return authorSecondName.getText();
        }

        public String getAuthorFatherName() {
            return authorFatherName.getText();
        }

        public String getBirthData() {
            return birthData.getText();
        }

        public String getName() {
            return name.getText();
        }

        public String getAnotation() {
            return anotation.getText();
        }

        public String getCreateDate() {
            return createDate.getText();
        }

        public String getComplex() {
            return complex.getText();
        }

        public String getFond() {
            return fond.getText();
        }

        public String getKeeperName() {
            return keeperName.getText();
        }

        public String getKeeperSecondName() {
            return keeperSecondName.getText();
        }

        public String getKeeperFatherName() {
            return keeperFatherName.getText();
        }
    }


