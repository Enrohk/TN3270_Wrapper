package gui;


import tasks.Task;
import wrapper.Wrapper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class actionsGUI{

    private static JTextField name = new JTextField();
    private static JTextField date = new JTextField();
    private static JTextArea desc = new JTextArea();

    public static Container getTableTasks(){

        List<Task> l=Wrapper.getTaskList();

        String[][] dd = {l.get(0).getStringTask()};

        return new TasksGUIPanel(dd,GUIString.TABLE_COLS);
    }

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
            System.err.println("NAME_"+nameS);
            System.err.println("DATE_"+dateS);
            System.err.println("DESC_"+descS);
        }
        Wrapper.printTasks();
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
