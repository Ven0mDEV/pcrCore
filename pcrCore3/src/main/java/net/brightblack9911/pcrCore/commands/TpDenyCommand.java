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

public class TpDenyCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MM.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		User u = UserManager.getInstance().getUser(p);
		if (u.getLastRequestTeleport() == null) {
			Utils.sendMsg(p, MM.NOT_FINDED_TELEPORT_REQUEST);
			return true;
		}
		u.setLastRequestTeleportTime(0);
		Utils.sendMsg(p, MM.TELEPORT_REQUEST_MESSAGE_TO.replace("%player", u.getLastRequestTeleport()));

		Player o = Bukkit.getPlayer(u.getLastRequestTeleport());
		if (o != null) Utils.sendMsg(o, MM.REJECT_TELEPORT_REQUEST_MESSAGE_FROM.replace("%player", p.getName()));
		u.setLastRequestTeleport(null);
		return false;
	}
}
