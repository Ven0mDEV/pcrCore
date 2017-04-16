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

public class AddCoinsCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs.hasPermission("core.getcoins")) {
			Utils.sendMsg(cs, MessagesManager.getInstance().NO_PERMISSION.replace("%permission", "core.getcoins"));
			return true;
		}
		if (args.length != 2) {
			Utils.sendMsg(cs, MessagesManager.getInstance().USAGE.replace("%use", "/addcoins <nick> <ilosc>"));
		}
		Player o = Bukkit.getPlayer(args[0]);
		User uo = UserManager.getInstance().getUser(o);
		uo.setCoins(uo.getCoins() + Integer.parseInt(args[1]), true);
		return false;
	}

}
