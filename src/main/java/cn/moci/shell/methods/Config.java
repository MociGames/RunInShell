package cn.moci.shell.methods;

import cn.moci.shell.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    //Config类
    public static String permission;
    public static boolean onlyCONSOLE;
    public static boolean output;

    public static void init() {
        Main.getInstance().saveDefaultConfig();
        Main.getInstance().reloadConfig();

        FileConfiguration cfg = Main.getInstance().getConfig();
        permission = cfg.getString("permission");
        onlyCONSOLE = cfg.getBoolean("onlyCONSOLE");
        output = cfg.getBoolean("output");
    }

}
