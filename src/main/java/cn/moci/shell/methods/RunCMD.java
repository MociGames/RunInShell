package cn.moci.shell.methods;

import cn.moci.shell.Main;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import org.bukkit.scheduler.BukkitRunnable;

public class RunCMD {

    /**
     * 使用异步执行命令。
     *
     * @param cmd 所要执行的命令。
     */
    public static void run(String cmd) {
        new BukkitRunnable() {
            @Override
            public void run() {
                exec(cmd);
                cancel();
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public static void run(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
            sb.append(" ");
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                exec(sb.toString());
                cancel();
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    /**
     * 执行命令。
     *
     * @param cmd 所要执行的命令。
     * @return 输出结果。
     */
    public static Object exec(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(getCMD(cmd));
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (Config.output) { //判断是否开启了output
                    System.out.println(line);
                }
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到当前系统所执行command的格式。
     *
     * @return 转码完成的CMD。
     */
    private static String[] getCMD(String s) {
        if (SystemOS.isWindows) {
            return new String[]{"cmd /c " + s};
        } else {
            return new String[]{"/bin/sh", "-c", s};
        }
    }

}
