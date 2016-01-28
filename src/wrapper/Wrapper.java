package wrapper;


import tasks.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Wrapper {

    //Attributes
    private static BufferedReader read;
    private static PrintWriter write;
    private static Process s3270;
    private static List<Task> taskList = new ArrayList<Task>();

    public static void startS3270() throws IOException {
        s3270 =  Runtime.getRuntime().exec(WrapperCodes.PROGRAM_NAME);
        read = new BufferedReader(new InputStreamReader(s3270.getInputStream()));
        write = new PrintWriter(new OutputStreamWriter(s3270.getOutputStream()));
        startTareas();
        writeInS3270(WrapperCodes.PRINTTEXT,true);
    }


    /**
     * Adds a mew task
     * @param name Name, if null -> GENERAL TASK
     * @param date Date formate DDMM
     * @param desc Description
     */
    public static void addTask(String name, String date, String desc){

        writeInS3270(WrapperCodes.click(1), false);             //ASIGN TASK
        enter_printtext();

        //GENERAL TASK
        if(name == null){
            writeInS3270(WrapperCodes.click(1), false);         //GENERAL TASK
        }
        //SPECIFIC TASK
        else{
            writeInS3270(WrapperCodes.click(2), false);         //SPECIFIC TASK
        }

        enter_printtext();

        writeInS3270(WrapperCodes.enterString(date),false);     //Enter date
        enter_printtext();

        //Enter name if SPECIFIC TASK
        if(name != null){
            writeInS3270(WrapperCodes.enterString(name),false); //Enter name
            enter_printtext();
        }

        writeInS3270(WrapperCodes.enterString(desc),false);     //Enter desc
        enter_printtext();

        writeInS3270(WrapperCodes.click(3), false);             //Back to main menu
        enter_printtext();

        writeInS3270(WrapperCodes.PRINTTEXT,true);
        listTasks();
    }

    /**
     * Add to the private attribute "tasks" all the tasks
     */
    public static void listTasks(){

        writeInS3270(WrapperCodes.click(2), false);             //LIST TASKS
        writeInS3270(WrapperCodes.ENTER,false);
        writeInS3270(WrapperCodes.click(1), false);             //GENERAL TASKS
        enter_printtext();
        writeInS3270(WrapperCodes.click(2), false);             //SPECIFIC TASKS
        enter_printtext();
        writeInS3270(WrapperCodes.click(3), false);             //Back to main menu
        enter_printtext();
    }

    public static void exit(){
        writeInS3270(WrapperCodes.click(3),false);              // Exit
        writeInS3270(WrapperCodes.ENTER,false);
        writeInS3270(WrapperCodes.ENTER,false);

        try {
            writeInS3270(WrapperCodes.DISCONNECT,false);
            read.close();
            write.close();
            s3270.destroy();
        }catch(IOException e) {
            System.err.println(e);
        }
        System.exit(0);

    }

    private static void enter_printtext(){
        writeInS3270(WrapperCodes.ENTER,false);
        writeInS3270(WrapperCodes.PRINTTEXT, true);
    }

    private static void startTareas() {
        writeInS3270(WrapperCodes.CONNECT, true);
        writeInS3270(WrapperCodes.ENTER,true);
        writeInS3270(WrapperCodes.ENTER,true);
        writeInS3270(WrapperCodes.PRINTTEXT,true);
        writeInS3270(WrapperCodes.enterString(WrapperCodes.LOGIN_ID),true);
        writeInS3270(WrapperCodes.TAB,true);
        writeInS3270(WrapperCodes.enterString(WrapperCodes.LOGIN_PASS),true);
        writeInS3270(WrapperCodes.PRINTTEXT,true);
        writeInS3270(WrapperCodes.ENTER,true);
        writeInS3270(WrapperCodes.ENTER,true);
        writeInS3270(WrapperCodes.enterString(WrapperCodes.FLIB),true);
        writeInS3270(WrapperCodes.ENTER,true);
        writeInS3270(WrapperCodes.enterString(WrapperCodes.TAREAS),true);
        writeInS3270(WrapperCodes.ENTER,true);
    }

    private static void writeInS3270(String line, boolean debug){
        write.println(line);
        write.flush();
        parseOutput(debug);
    }

    private static void parseOutput(boolean debug){
        String line = null;

        try {
            while((line = read.readLine()) != null && read.ready()){
                System.out.println(line);
                handleResponse(line,debug);
            }
            handleResponse(line,debug);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleResponse(String line, boolean debug) {
        if(line.indexOf(WrapperCodes.MORE) >= 0) {
            writeInS3270(WrapperCodes.ENTER,false);
        }
        else if(line.indexOf(WrapperCodes.WORKING) >= 0) {
            writeInS3270(WrapperCodes.PRINTTEXT,true);
        }
        taskParser(line,debug);
    }

    private static void taskParser(String line, boolean debug) {
        if(debug && line.indexOf(WrapperCodes.TASK) == 6) {
            Task newTask = Task.parseTaskFromCL(line.substring(6));
            try {
                if (taskList.get(newTask.getId()) == null) {
                    taskList.add(newTask.getId(), newTask);
                }
            }catch(Exception e){
                taskList.add(newTask.getId(), newTask);
            }
        }
    }

    private static String removeSpaces(String s){
        s.replaceAll(" ","_");
        s.replaceAll("\t","_");
        s.replaceAll("\n","_");
        s.replaceAll("\r","_");
        return s;
    }

    public static void printTasks(){
        for(Task t : taskList){
            System.out.println(t.getName());
        }
    }

    public static List<Task> getTaskList(){
        return taskList;
    }

}
