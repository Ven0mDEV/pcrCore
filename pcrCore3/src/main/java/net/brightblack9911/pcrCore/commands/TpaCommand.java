package net.brightblack9911.pcrCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class TpaCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MM.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		if (args.length != 1) {
			Utils.sendMsg(p, MM.USAGE.replace("%use", "/tpa <nick>"));
			return true;
		}
		if (p.getName().equals(args[0])) {
			Utils.sendMsg(p, MM.TELEPORT_SAME_PLAYER);
			return true;
		}
		Player o = Bukkit.getPlayer(args[0]);
		if (o == null) {
			Utils.sendMsg(cs, MM.NOT_FINDED_PLAYER);
			return true;
		}
		User u = UserManager.getInstance().getUser(o);
		u.setLastRequestTeleport(p.getName());
		u.setLastRequestTeleportTime(System.currentTimeMillis());

		Utils.sendMsg(p, MM.TELEPORT_REQUEST_MESSAGE_TO.replace("%player", o.getName()));
		Utils.sendMsg(o, MM.TELEPORT_REQUEST_MESSAGE_FROM.replace("%player", p.getName()));
		return false;
	}
}
