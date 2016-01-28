package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WrapperGUI extends JPanel {

    private JPanel tablePanel = new JPanel();

    public WrapperGUI(){

        this.setLayout(new BorderLayout());

        JPanel btnPanel = new JPanel();
        this.addBtns(btnPanel);

        this.add(btnPanel,BorderLayout.SOUTH);
        this.add(tablePanel,BorderLayout.CENTER);


    }



    private void addBtns(JPanel panel){

        JButton addBtn = new JButton(GUIString.ADD_BTN);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionsGUI.addTask();
                updateTaskTable();
            }
        });
        JButton listBtn = new JButton(GUIString.LIST_BTN);
        listBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTaskTable();
            }
        });

        panel.add(addBtn);
        panel.add(listBtn);
    }

    private void updateTaskTable(){
        tablePanel.removeAll();
        tablePanel.add(actionsGUI.getTableTasks());
        this.validate();
        this.repaint();
    }

}
