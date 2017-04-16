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

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class HelpOpCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();

	private final Map<String, Long> HELPOP = Maps.newHashMap();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MM.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		if (args.length == 0) {
			Utils.sendMsg(p, MM.USAGE.replace("%use", "/helpop <wiadomosc>"));
			return true;
		}
		if (HELPOP.containsKey(p.getName()) && !Utils.canUse(HELPOP.get(p.getName()), 60 * 1000)) {
			Utils.sendMsg(p, MM.TICKET_COOLDOWN.replace("%time", Long.toString(60 - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - HELPOP.get(p.getName())))));
			return true;
		}
		String msg = StringUtils.join(args, " ");
		Utils.sendMsg(p, MM.TICKET_SENDED);

		for (Player po : Bukkit.getOnlinePlayers()) {
			if (po.hasPermission("core.helpop.see")) {
				Utils.sendMsg(po, MM.TICKET_VIEW.replace("%player", p.getName()).replace("%message", msg));
			}
		}
		HELPOP.put(p.getName(), System.currentTimeMillis());
		return false;
	}
}
