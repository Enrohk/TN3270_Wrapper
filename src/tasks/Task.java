package tasks;

import java.util.Scanner;

public class Task {

    //Attributes
    private String name;
    private String date;
    private String desc;
    private String type;
    private int id;


    public Task(String name, String date, String desc, String type, int id) {
        this.name = name;
        this.date = date;
        this.desc = desc;
        this.type = type;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public static Task parseTaskFromCL(String substring) {
        Scanner s = new Scanner(substring);
        s.useDelimiter(" ");
        s.next();                           //Skip TASK
        String idS = s.next();               //get id
        int id = Integer.parseInt(idS.substring(0,1));
        String type = s.next().trim();      //get type
        String date = s.next().trim();      //get date
        String name = s.next().trim();      //get name
        String desc = s.nextLine();         //get desc
        s.close();

        return new Task(name,date,desc,type,id);
    }
}
