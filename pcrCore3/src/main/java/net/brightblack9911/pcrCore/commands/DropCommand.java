package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.managers.GUIMenuManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class DropCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MessagesManager.getInstance().NO_CONSOLE);
			return true;
		}
		GUIMenuManager.getInstance().openDropGUI((Player) cs);
		return false;
	}
}
