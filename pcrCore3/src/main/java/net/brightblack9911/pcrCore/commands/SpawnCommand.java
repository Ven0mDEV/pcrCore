package net.brightblack9911.pcrCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.basic.Teleport;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class SpawnCommand implements CommandExecutor
{
	private final MessagesManager mm = MessagesManager.getInstance();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (!(cs instanceof Player)) {
				Utils.sendMsg(cs, mm.NO_CONSOLE);
				return true;
			}
			Player p = (Player) cs;
			if (!p.hasPermission("core.spawn")) {
				Utils.sendMsg(p, mm.NO_PERMISSION.replace("%permission", "core.spawn"));
				return true;
			}
			new Teleport(p, p.getWorld().getSpawnLocation());
			return true;
		}
		if (args.length > 0) {
			if (!cs.hasPermission("core.spawn.other")) {
				Utils.sendMsg(cs, mm.NO_PERMISSION.replace("%permission", "core.spawn.other"));
				return true;
			}
		}
		Player o = Bukkit.getPlayer(args[0]);
		if (o == null) {
			Utils.sendMsg(cs, mm.NOT_FINDED_PLAYER);
			return true;
		}
		o.teleport(o.getWorld().getSpawnLocation());

		return false;
	}
}
