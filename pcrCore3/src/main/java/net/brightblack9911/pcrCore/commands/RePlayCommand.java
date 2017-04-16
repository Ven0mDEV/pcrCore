package net.brightblack9911.pcrCore.commands;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class RePlayCommand implements CommandExecutor
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
			Utils.sendMsg(p, MM.USAGE.replace("%use", "/reply <wiadomosc>"));
			return true;
		}
		User u = UserManager.getInstance().getUser(p);
		if (u.getLastMessageSender() == null) {
			
			return true;
		}
		Player o = Bukkit.getPlayer(u.getLastMessageSender());
		if (o == null) {
			Utils.sendMsg(p, MM.NOT_FINDED_PLAYER);
			return true;
		}

		Utils.sendMsg(p, MM.FORMAT_MESSAGE_TO.replace("%player", o.getName()).replace("%message", StringUtils.join(args, " ", 0, args.length)));
		Utils.sendMsg(o, MM.FORMAT_MESSAGE_FROM.replace("%player", p.getName()).replace("%message", StringUtils.join(args, " ", 0, args.length)));
		return false;
	}
}
