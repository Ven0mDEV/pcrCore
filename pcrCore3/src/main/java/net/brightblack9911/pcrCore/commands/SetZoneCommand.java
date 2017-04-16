package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.basic.CorePlugin;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.LocationUtils;
import net.brightblack9911.pcrCore.utils.Utils;

public class SetZoneCommand implements CommandExecutor
{
	private final CorePlugin C = CorePlugin.getInstance();

	private final MessagesManager MM = MessagesManager.getInstance();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			Utils.sendMsg(cs, MM.NO_CONSOLE);
			return true;
		}
		Player p = (Player) cs;
		if (args.length < 2) {
			return true;
		}
		
		if (!Utils.isInteger(args[0])) {
			return true;
		}
		
		if (!Utils.isInteger(args[1])) {
			return true;
		}
		
		int arg1 = Integer.parseInt(args[0]);
		int arg2 = Integer.parseInt(args[1]);

		if ((arg1 < 1 || arg1 > 2) || (arg2 < 1 || arg2 > 2)) {
			return true;
		}
		
		C.getConfig().set("config.zone.wall" + arg1 + ".point" + arg2, LocationUtils.serializationLocation(p.getLocation()));
		C.saveConfig();
		return false;
	}
}
