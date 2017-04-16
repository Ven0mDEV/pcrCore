package net.brightblack9911.pcrCore.commands;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class TellCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();

	private final Map<String, Long> MESSAGE_COOLDOWN = Maps.newHashMap();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MM.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		if (args.length <= 1) {
			Utils.sendMsg(p, MM.USAGE.replace("%use", "/tell <nick> <wiadomosc>"));
			return true;
		}

		if (MESSAGE_COOLDOWN.containsKey(p.getName()) && !Utils.canUse(MESSAGE_COOLDOWN.get(p.getName()), 5 * 1000)) {
			Utils.sendMsg(p, MM.MESSAGE_COOLDOWN);
			return true;
		}

		Player o = Bukkit.getPlayerExact(args[0]);
		if (o == null) {
			Utils.sendMsg(cs, MM.NOT_FINDED_PLAYER);
			return true;
		}
		User u = UserManager.getInstance().getUser(p);
		User ou = UserManager.getInstance().getUser(o);

		Utils.sendMsg(p, MM.FORMAT_MESSAGE_TO.replace("%player", o.getName()).replace("%message", StringUtils.join(args, " ", 1, args.length)));
		Utils.sendMsg(o, MM.FORMAT_MESSAGE_FROM.replace("%player", cs.getName()).replace("%message", StringUtils.join(args, " ", 1, args.length)));

		u.setLastMessageSender(o.getName());
		ou.setLastMessageSender(p.getName());

		MESSAGE_COOLDOWN.put(p.getName(), System.currentTimeMillis());
		return false;
	}

}
