package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.brightblack9911.pcrCore.basic.CorePlugin;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.Settings;
import net.brightblack9911.pcrCore.utils.Utils;

public class CoreReloadCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!cs.hasPermission("core.reload")) {
			Utils.sendMsg(cs, MM.NO_PERMISSION.replace("%permission", "core.reload"));
			return true;
		}
		
		CorePlugin.getInstance().reloadConfig();
		Settings.getInstance().loadConfiguration();
		MM.loadMessages();
		
		Utils.sendMsg(cs, MM.RELOAD_SUCCESS);
		return false;
	}
}
