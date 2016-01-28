package gui;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;


public class TasksGUIPanel extends JPanel {

    private ButtonGroup shortCriteria;

    public TasksGUIPanel(String[][] rows, String[] cols){
        this.setLayout(new BorderLayout());
        this.add(new JLabel(GUIString.TASKS_LIST_LABEL), BorderLayout.NORTH);

        this.add(addShortCriteriaPanel(),BorderLayout.SOUTH);

        JPanel l = new JPanel();
        l.setLayout(new BoxLayout(l, BoxLayout.Y_AXIS));
        String s = "";
        for(String[] row : rows){
            for(String col: row){
                s = s + col;
            }
            l.add(new JLabel(s));
            s = "";
        }

        this.add(l,BorderLayout.CENTER);


    }

    private JPanel addShortCriteriaPanel() {
        shortCriteria = new ButtonGroup();
        JPanel shortCriteriaPanel = new JPanel();

        shortCriteriaPanel.add(new JLabel(GUIString.SHORT_LABEL));

        JRadioButton type = new JRadioButton(GUIString.SHORT_TYPE);
        type.setActionCommand(GUIString.SHORT_TYPE);
        JRadioButton date = new JRadioButton(GUIString.SHORT_DATE);
        date.setActionCommand(GUIString.SHORT_DATE);
        JRadioButton name = new JRadioButton(GUIString.SHORT_NAME);
        name.setActionCommand(GUIString.SHORT_NAME);

        type.setSelected(true);

        shortCriteria.add(type);
        shortCriteria.add(date);
        shortCriteria.add(name);
        shortCriteriaPanel.add(type);
        shortCriteriaPanel.add(date);
        shortCriteriaPanel.add(name);
        return shortCriteriaPanel;
    }
}
