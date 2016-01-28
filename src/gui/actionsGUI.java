package gui;

import tasks.Task;
import wrapper.Wrapper;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ActionsGUI {

    private static JTextField name = new JTextField();
    private static JTextField date = new JTextField();
    private static JTextArea desc = new JTextArea();
    private static JPanel generalTasksPanel, specificTasksPanel;

    public static void addTask() {
        clearFields();
        Object[] message = {
                GUIString.NAME_INPUT, name,
                GUIString.DATE_INPUT, date,
                GUIString.DESC_INPUT, desc,
        };

        int option = JOptionPane.showConfirmDialog(null, message, GUIString.ADD_TITTLE,
                JOptionPane.OK_CANCEL_OPTION);

        if(option == JOptionPane.OK_OPTION){
            String nameS = name.getText().trim();
            String dateS = date.getText().trim();
            String descS = desc.getText().trim();

            if(nameS.equals("")) nameS = null;
            Wrapper.addTask(nameS,dateS,descS);
        }

    }

    public static void setPanels(JPanel specific, JPanel general){
        generalTasksPanel = general;
        specificTasksPanel = specific;
    }

    public static void updateGeneralPanel(){
        generalTasksPanel.removeAll();
        JLabel date, desc;
        for(Task task : Wrapper.getSpecificTasks()){
            date = new JLabel(task.getDate(),JLabel.CENTER);
            desc = new JLabel(task.getDesc(),JLabel.CENTER);
            specificTasksPanel.add(date);
            specificTasksPanel.add(desc);
        }
    }

    public static void updateSpecificPanel(){
        specificTasksPanel.removeAll();
        JLabel date, name, desc;
        for(Task task : Wrapper.getSpecificTasks()){
            date = new JLabel(task.getDate(),JLabel.CENTER);
            name = new JLabel(task.getName(),JLabel.CENTER);
            desc = new JLabel(task.getDesc(),JLabel.CENTER);
            specificTasksPanel.add(date);
            specificTasksPanel.add(name);
            specificTasksPanel.add(desc);
        }
    }

    private static void clearFields(){
        name.setText(GUIString.EMPTY);
        date.setText(GUIString.EMPTY);
        desc.setText(GUIString.EMPTY);
        desc.setColumns(GUIString.DESC_COLS);
        desc.setRows(GUIString.DESC_ROWS);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        desc.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    }

}
