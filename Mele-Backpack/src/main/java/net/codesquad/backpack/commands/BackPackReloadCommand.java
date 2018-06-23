/**
 * 
 */
package net.codesquad.backpack.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.codesquad.backpack.managers.MessagesManager;
import net.codesquad.backpack.utils.Utils;

/**
 * @author nano3.
 *
 */
public class BackPackReloadCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (!(commandSender instanceof Player)) { 
			MessagesManager.getInstance().load();
			Utils.sendMsg(commandSender, MessagesManager.getInstance().getMessage("reload"));
			return true;
		} 
		return false;
	}
}
