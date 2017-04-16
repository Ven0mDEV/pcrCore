package net.brightblack9911.pcrCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.basic.Teleport;
import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class TpAcceptCommand implements CommandExecutor
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
		Player o = Bukkit.getPlayer(u.getLastRequestTeleport());
		if (o == null) {
			Utils.sendMsg(p, MM.NOT_FINDED_PLAYER);
			return true;
		}
		if (Utils.canUse(u.getLastRequestTeleportTime(), 60 * 1000)) {
			Utils.sendMsg(p, MM.TELEPORT_REQUEST_TIMEOUT);
			return true;
		}

		new Teleport(o, p.getLocation());
		return false;
	}
}
