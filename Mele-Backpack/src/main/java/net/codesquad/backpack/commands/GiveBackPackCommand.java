/**
 * 
 */
package net.codesquad.backpack.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.codesquad.backpack.managers.BackPackManager;
import net.codesquad.backpack.managers.MessagesManager;
import net.codesquad.backpack.utils.Utils;

/**
 * @author nano3.
 *
 */
public class GiveBackPackCommand implements CommandExecutor
{
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		Player player = (Player) commandSender;
		player.getInventory().addItem(BackPackManager.getInstance().giveBackPackItem(player));
		Utils.sendMsg(player, MessagesManager.getInstance().getMessage("get-backpack"));
		return false;
	}
}
