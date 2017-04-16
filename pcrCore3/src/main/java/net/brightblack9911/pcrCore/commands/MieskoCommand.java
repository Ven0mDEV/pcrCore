package net.brightblack9911.pcrCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class MieskoCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MessagesManager.getInstance().NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		p.getInventory().addItem(new ItemStack(Material.GRILLED_PORK, 64));
		Utils.sendMsg(p, "&a&oNajedz sie!");
		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl.getGameMode() == GameMode.CREATIVE) {
		System.out.println("Ma gm!!!!!!!!!!!!!!" + pl.getName());
			}
		}
		return false;
	
	}

}
