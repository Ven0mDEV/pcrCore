/**
 * 
 */
package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class WorkBenchCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MM.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		if (!p.hasPermission("core.open.workbench")) {
			Utils.sendMsg(p, MM.NO_PERMISSION.replace("%permission", "core.open.workbench"));
			return true;
		}
		p.openWorkbench(null, true);
		return false;
	}
}
