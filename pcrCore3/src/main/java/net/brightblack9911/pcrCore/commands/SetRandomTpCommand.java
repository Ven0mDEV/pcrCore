package net.brightblack9911.pcrCore.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.RandomTpManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class SetRandomTpCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		final MessagesManager messages = MessagesManager.getInstance();
		
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, messages.NO_CONSOLE);
			return true;
		}
		
		Player p = (Player) cs;
		if (!p.hasPermission("core.randomtp")) {
			Utils.sendMsg(p, messages.NO_PERMISSION.replace("%permission", "core.randomtp"));
			return true;
		}
		
		System.out.println(RandomTpManager.getInstance().getLocations().size());
		
		@SuppressWarnings("deprecation")
		Block targetBlock = p.getTargetBlock(null, 5);

//		if () {
//			
//			return true;
//		}
		
		if (!targetBlock.getType().equals(Material.ENDER_STONE)) {
			
			return true;
		}
		
		RandomTpManager.getInstance().addLocation(targetBlock.getLocation());
		return false;
	}
}
