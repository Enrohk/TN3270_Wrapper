package wrapper;


public class WrapperCodes {
    public static final String PROGRAM_NAME = "C:\\Program Files (x86)\\wc3270\\ws3270.exe";

    //s3270 console options
    public static final String CONNECT = "connect 155.210.152.51:102";
    public static final String LOGIN_ID = "\"grupo_13\"";
    public static final String LOGIN_PASS = "secreto6";
    public static final String FLIB = "FLIB";
    public static final String TAREAS = "TAREAS.C";

    public static final String ENTER = "Enter";
    public static final String TAB = "Tab";
    public static final String PRINTTEXT = "printtext string";



    public static String click(int key){ return "key("+key+")"; }
    public static String enterString(String data){ return"string("+data+")"; }

    public static final String DISCONNECT = "Disconnect";

    public static final String MORE = "More...";
    public static final String WORKING = "Working";
    public static final String TASK = "TASK";
}
