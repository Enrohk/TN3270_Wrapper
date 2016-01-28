package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class WrapperGUI extends JPanel {

    public WrapperGUI(){

        this.setLayout(new BorderLayout());

        JPanel btnPanel = new JPanel();

        this.addBtn(btnPanel);

        this.add(btnPanel,BorderLayout.SOUTH);

        this.add(new TasksGUIPanel(),BorderLayout.CENTER);

    }


    private void addBtn(JPanel panel){

        JButton addBtn = new JButton(GUIString.ADD_BTN);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActionsGUI.addTask();
                validateAndRepaint();
            }
        });

        panel.add(addBtn);

    }

    private void validateAndRepaint(){
        this.validate();
        this.repaint();
    }
}
