package main;

import gui.GUIString;
import gui.WrapperGUI;
import wrapper.Wrapper;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                try {
                    Wrapper.startS3270();

                } catch (IOException e) {
                    e.printStackTrace();
                    Wrapper.exit();
                    System.exit(-1);
                }
                JFrame frame = new JFrame(GUIString.TITTLE);
                frame.add(new WrapperGUI());
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.setSize(500,500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setResizable(false);

                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        Wrapper.exit();
                        System.exit(-1);
                    }
                });
            }
        });
    }
}
