package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;

public class BroadCastCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Utils.sendMsg(cs, MM.USAGE.replace("%use", "/broadcast <wiadomosc>"));
			return true;
		}
		if (!cs.hasPermission("core.broadcast")) {
			Utils.sendMsg(cs, MM.NO_PERMISSION.replace("%permission", "core.broadcast"));
			return true;
		}
		Utils.bcMessage("&7[&cBROADCAST&7]: &f" + StringUtils.join(args, " "));
		return false;
	}
}
