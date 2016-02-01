package gui;

import wrapper.WrapperCodes;
import javax.swing.*;
import java.awt.*;


public class TasksGUIPanel extends JPanel {

    private JPanel specificTasks, generalTasks;

    public TasksGUIPanel(){
        this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel specificTaskPanel = new JPanel();
        JPanel generalTaskPanel = new JPanel();

        specificTaskPanel.setLayout(new BorderLayout());
        generalTaskPanel.setLayout(new BorderLayout());


        specificTaskPanel.add(new JLabel(WrapperCodes.SPECIFIC_TASK, JLabel.CENTER), BorderLayout.NORTH);
        generalTaskPanel.add(new JLabel(WrapperCodes.GENERAL_TASK, JLabel.CENTER),BorderLayout.NORTH);

        JPanel generalCenter = new JPanel();
        generalCenter.setLayout(new BorderLayout());

        JPanel generalLabelTitle = new JPanel();
        generalLabelTitle.setLayout(new GridLayout(0,2));
        for(String col: GUIString.TABLE_COLS_GENERAL){
            generalLabelTitle.add(new JLabel(col,JLabel.CENTER));
        }

        generalCenter.add(generalLabelTitle,BorderLayout.NORTH);

        JPanel specificCenter = new JPanel();
        specificCenter.setLayout(new BorderLayout());

        JPanel specificLabelTitle = new JPanel();
        specificLabelTitle.setLayout(new GridLayout(0,3));
        for(String col: GUIString.TABLE_COLS){
            specificLabelTitle.add(new JLabel(col,JLabel.CENTER));
        }

        specificCenter.add(specificLabelTitle,BorderLayout.NORTH);

        createTasksPanels(generalCenter, specificCenter);

        generalTaskPanel.add(generalCenter, BorderLayout.CENTER);
        specificTaskPanel.add(specificCenter, BorderLayout.CENTER);

        this.add(generalTaskPanel);
        this.add(specificTaskPanel);

        ActionsGUI.setPanels(specificTasks, generalTasks);

    }

    private void createTasksPanels(JPanel panel1, JPanel panel2){
        specificTasks = new JPanel();
        specificTasks.setLayout(new GridLayout(0,3));
        specificTasks.setBorder(BorderFactory.createLineBorder(Color.black));
        JScrollPane specificScroll = new JScrollPane(specificTasks);
        panel2.add(specificScroll,BorderLayout.CENTER);

        generalTasks = new JPanel();
        generalTasks.setLayout(new GridLayout(0,2));
        generalTasks.setBorder(BorderFactory.createLineBorder(Color.black));
        JScrollPane generalScroll = new JScrollPane(generalTasks);
        panel1.add(generalScroll,BorderLayout.CENTER);
    }
}
