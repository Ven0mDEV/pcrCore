package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class HatCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MM.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		if (!p.hasPermission("core.hat")) {
			Utils.sendMsg(p, MM.NO_PERMISSION.replace("%permission", "core.hat"));
			return true;
		}
		changeHat(p);
		return false;
	}

	public boolean changeHat(Player p) {
		PlayerInventory pi = p.getInventory();
		ItemStack item = pi.getItemInHand();
		pi.setItemInHand(null);
		if (pi.getHelmet() != null) {
			ItemStack helmet = pi.getHelmet();
			pi.setItemInHand(helmet);
		}
		pi.setHelmet(item);
		return false;
	}
}
