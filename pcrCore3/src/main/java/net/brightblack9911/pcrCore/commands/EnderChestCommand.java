package net.brightblack9911.pcrCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class EnderChestCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MM.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		if (args.length == 0) {
			if (!p.hasPermission("core.enderchest")) {
				Utils.sendMsg(p, MM.NO_PERMISSION.replace("%permission", "core.enderchest"));
				return true;
			}
			p.openInventory(p.getEnderChest());
			return true;
		}
		
		if (args.length > 0) {
			if (!p.hasPermission("core.enderchest.other")) {
				Utils.sendMsg(p, MM.NO_PERMISSION.replace("%permission", "core.enderchest.other"));
				return true;
			}
			Player o = Bukkit.getPlayer(args[0]);
			if (o == null) {
				Utils.sendMsg(cs, MM.NOT_FINDED_PLAYER);
				return true;
			}
			p.openInventory(o.getEnderChest());
		}
		return false;
	}
}
