package net.brightblack9911.pcrCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class FeedCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (!(cs instanceof Player)) {
				Utils.sendMsg(cs, MM.NO_CONSOLE);
				return true;
			}
			Player p = (Player) cs;
			if (!p.hasPermission("core.feed")) {
				Utils.sendMsg(p, MM.NO_PERMISSION.replace("%permission", "core.feed"));
				return true;
			}
			p.setFoodLevel(20);
		}

		if (args.length > 1) {
			if (!cs.hasPermission("core.feed.other")) {
				Utils.sendMsg(cs, MM.NO_PERMISSION.replace("%permission", "core.feed.other"));
				return true;
			}
			Player o = Bukkit.getPlayer(args[0]);
			if (o == null) {
				Utils.sendMsg(cs, MM.NOT_FINDED_PLAYER);
				return true;
			}
			o.setFoodLevel(20);
		}
		return false;
	}
}
