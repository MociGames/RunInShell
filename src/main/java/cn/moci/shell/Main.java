package cn.moci.shell;

import cn.moci.shell.commands.Run;
import cn.moci.shell.methods.Config;
import cn.moci.shell.methods.SystemOS;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String pluginName = "Shell";
    public static String pluginVersion = "1.0-SNAPSHOT";

    private static Main instance;

    public static Main getInstance() {
        return Main.instance;
    }

    public static String color(final String text) {
        return text.replaceAll("&", "§");
    }

    @Override
    public void onEnable() {
        Main.instance = this;
        pluginMessage("已启用。");
        Config.init();
        SystemOS.init();
        regCmds();
    }

    @Override
    public void onDisable() {
        this.shutdown();
    }

    private void regCmds() {
        this.getCommand("run").setExecutor(new Run());
    }

    private void shutdown() {
        this.pluginMessage("已卸载。");
    }

    private void pluginMessage(String s) {
        this.getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "!" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + pluginName + " " + ChatColor.WHITE + pluginVersion + ChatColor.GRAY + s);
    }

}
