package net.brightblack9911.pcrCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.managers.ItemManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.ItemUtils;
import net.brightblack9911.pcrCore.utils.Utils;

public class CaseCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs.hasPermission("core.case")) {
			Utils.sendMsg(cs, MessagesManager.getInstance().NO_PERMISSION.replace("%permission", "core.case"));
			return true;
		}
		
		if (args.length != 2) {
			Utils.sendMsg(cs, MessagesManager.getInstance().USAGE.replace("%use", "/case <nick> <ilosc>"));
			return true;
		}
		
		Player o = Bukkit.getPlayer(args[0]);
		if (o == null) {
			Utils.sendMsg(cs, MessagesManager.getInstance().NOT_FINDED_PLAYER);
		}
		
		ItemUtils.giveToInventory(o, ItemManager.getInstance().cased);
		return false;
	}
}
