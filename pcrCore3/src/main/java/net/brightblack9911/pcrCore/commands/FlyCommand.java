package net.brightblack9911.pcrCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class FlyCommand implements CommandExecutor
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
			if (!p.hasPermission("core.fly")) {
				Utils.sendMsg(p, MM.NO_PERMISSION.replace("%permission", "core.fly"));
				return true;
			}
			if (p.getAllowFlight()) p.setAllowFlight(false);
			else p.setAllowFlight(true);
		}

		if (args.length > 1) {
			if (!cs.hasPermission("core.fly.other")) {
				Utils.sendMsg(cs, MM.NO_PERMISSION.replace("%permission", "core.fly.other"));
				return true;
			}
			Player o = Bukkit.getPlayer(args[0]);
			if (o == null) {
				Utils.sendMsg(cs, MM.NOT_FINDED_PLAYER);
				return true;
			}
			if (o.getAllowFlight()) o.setAllowFlight(false);
			else o.setAllowFlight(true);
		}
		return false;
	}
}
