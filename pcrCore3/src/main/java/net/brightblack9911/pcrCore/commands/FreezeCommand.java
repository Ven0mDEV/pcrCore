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

public class FreezeCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();
	private final UserManager UM = UserManager.getInstance();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!cs.hasPermission("core.freeze")) {
			Utils.sendMsg(cs, MM.NO_PERMISSION.replace("%permission", "core.freeze"));
			return true;
		}
		Player o = Bukkit.getPlayer(args[0]);
		if (o == null) {
			Utils.sendMsg(cs, MM.NOT_FINDED_PLAYER);
			return true;
		}
		User u = UM.getUser(o);
		if (u.isFreeze()) {
			u.setFreeze(false);
			// msg
			return true;
		}
		u.setFreeze(true);
		Utils.sendMsg(o, "&cJestes sprawdzany!");
		return false;
	}
}
