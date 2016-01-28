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
        s.useDelimiter("\\s|\n|:");
        s.next();
        int id = s.nextInt();
        String type = s.next().trim();
        String date = s.next().trim();
        String name = s.next().trim();
        String desc = s.nextLine();
        s.close();

        return new Task(name,date,desc,type,id);
    }

    public String[] getStringTask(){
        return new String[]{"name "+name,"date "+date,"desc "+desc,"type "+type, "id "+id};
    }
}
