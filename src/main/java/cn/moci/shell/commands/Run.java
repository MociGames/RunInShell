package cn.moci.shell.commands;

import cn.moci.shell.methods.Config;
import cn.moci.shell.methods.RunCMD;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Run implements CommandExecutor {

		//非常弱智的嵌套if 不要看这段垃圾代码。
		@Override
		public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
				if (sender instanceof Player && !Config.onlyCONSOLE) {
						if (sender.hasPermission(Config.permission) || sender.isOp()) {
								if (args.length < 1) {
										sender.sendMessage(ChatColor.GRAY + "使用方法: " + ChatColor.AQUA + "/run " + ChatColor.DARK_GRAY + "[" + ChatColor.RED + "命令" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + "。");
										return true;
								} else {
										runCMD(args);
										return true;
								}
						} else {
								sender.sendMessage(ChatColor.GRAY + "很抱歉，您没有使用Shell的权限。");
								return true;
						}
				} else if (args.length < 1) {
						sender.sendMessage(ChatColor.GRAY + "使用方法: " + ChatColor.AQUA + "/run " + ChatColor.DARK_GRAY + "[" + ChatColor.RED + "命令" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + "。");
						return true;
				} else {
						runCMD(args);
						return true;
				}
		}

		private static void runCMD(String[] args) {
				StringBuilder sb = new StringBuilder();
				for (String arg : args) {
						sb.append(arg);
						sb.append(" ");
				}
				RunCMD.run(sb.toString());
		}

}
