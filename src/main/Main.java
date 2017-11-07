package main;


import dbService.DBService;
import main.dialogs.*;
import main.dialogs.Dialog;

import javax.swing.*;
import java.awt.*;


/**
 * Created by alex on 17.7.17.
 */
public class Main {
    private DBService dbService;
    private Dialog dialog;

    private Main() {
        JFrame mainWindow = new JFrame("Museum");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dbService = new DBService();
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel jPanelID = new JPanel();
        jPanelID.setLayout(new GridBagLayout());
        JButton idSearchButton = new JButton("Index Search");
        idSearchButton.addActionListener(e -> dialog = new IndexSearchDialog("OK", dbService));
        AddComponent.add(jPanelID,idSearchButton,0,0,0,1);
        JButton addItemButton = new JButton("Add item");
        addItemButton.addActionListener(e -> dialog = new AddItemDialog("OK", dbService));
        AddComponent.add(jPanelID,addItemButton,0,1,0,1);

        JButton updateItemButton = new JButton("Update item");
        updateItemButton.addActionListener(e -> dialog = new UpdateDialog("OK", dbService));
        AddComponent.add(jPanelID,updateItemButton,0,2,0,1);

        JButton deleteItemButton = new JButton("Delete item");
        deleteItemButton.addActionListener(e -> dialog = new DeleteDialog("OK", dbService));
        AddComponent.add(jPanelID,deleteItemButton,0,3,0,1);

        JButton complexSearchButton = new JButton("Complex Search");
        complexSearchButton.addActionListener(e -> dialog = new ComplexSearchDialog("OK", dbService));
        AddComponent.add(jPanelID,complexSearchButton,0,4,0,1);

        JButton dateSearchButton = new JButton("Date Search");
        dateSearchButton.addActionListener(e -> dialog = new DateSearchLocatinDialog("OK", dbService));
        AddComponent.add(jPanelID,dateSearchButton,0,5,0,1);

        JButton addComplexMove = new JButton("Add Fond Move");
        addComplexMove.addActionListener(e -> dialog = new AddFondMove("OK", dbService));
        AddComponent.add(jPanelID,addComplexMove,0,6,0,1);

        JButton addOrMove = new JButton("Add Organization Move");
        addOrMove.addActionListener(e -> dialog = new AddOrMove("OK", dbService));
        AddComponent.add(jPanelID,addOrMove,0,7,0,1);
//        JButton fondMoveSearchButton = new JButton("Index Search");
//        fondMoveSearchButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                dialog = new IndexSearchDialog("Поиск информации2", dbService);
//            }
//        });
//        AddComponent.add(jPanelID,fondMoveSearchButton,0,2,0,1);
        mainWindow.add(jPanelID, BorderLayout.NORTH);
        mainWindow.setVisible(true);

//        try {
//
//            //System.out.println(dbService.getByComplex("Иконостас АСОИ"));
//            //dbService.addMuseumItems("ather","ather","ather",1,"name123","anotation",1000000,"complex","fond","keeper","keeper","keeper");
//            //dbService.updateMuseumItems(2,"ather1","ather1","ather1",1,"name1","anotation1",1000000,"complex","fond","keeper","keeper","keeper");
//            //dbService.deleteMuseumItems(10,"name");
//            //dbService.addFondToFondMove(13,2,new Date().getTime());
//            //System.out.println(dbService.getFondMove(13,500000));
//            //area.setText(dbService.getFondMove(13,500000).toString()+"\n");
//
//            //area.setText(dbService.getMuseumItem(13).toString()+"\n");
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
