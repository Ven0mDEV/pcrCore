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

public class PayCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		final MessagesManager messages = MessagesManager.getInstance();
		
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, messages.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		
		if (args.length < 2) {
			return true;
		}
		
		Player o = Bukkit.getPlayer(args[0]);
		if (o == null) {
			Utils.sendMsg(p, messages.NOT_FINDED_PLAYER);
			return true;
		}
		
		if (!Utils.isInteger(args[1])) {
			
			return true;
		}
		
		User u = UserManager.getInstance().getUser(p);
		if (!(u.getCoins() >= Integer.parseInt(args[1]))) {
			
			return true;
		}
		
		User uo = UserManager.getInstance().getUser(o);
		uo.setCoins(uo.getCoins() + Integer.parseInt(args[1]), true);
		u.setCoins(u.getCoins() - Integer.parseInt(args[1]), true);
		
		Utils.sendMsg(p, messages.PAY_COINS.replace("%amount", args[1]).replace("%player", args[0]));
		Utils.sendMsg(o, messages.GET_COINS_FROM.replace("%amount", args[1]).replace("%player", p.getName()));
		return false;
	}
}
