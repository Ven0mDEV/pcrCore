package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class HeadCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MM.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		if (!p.hasPermission("core.head")) {
			Utils.sendMsg(p, MM.NO_PERMISSION.replace("%permission", "core.head"));
			return true;
		}
		if (args.length != 1) {
			Utils.sendMsg(p, MM.USAGE.replace("%use", "/head <nick>"));
			return true;
		}
		p.getInventory().addItem(Utils.getSkull(args[0]));
		return false;
	}
}
