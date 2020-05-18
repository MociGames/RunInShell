package cn.moci.shell.methods;

public class SystemOS {

    public static boolean isWindows;

    public static void init() {
        isWindows = System.getProperty("os.name").toLowerCase().startsWith("win"); //得到系统OS。
    }

}
