package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.brightblack9911.pcrCore.basic.CorePlugin;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.Settings;
import net.brightblack9911.pcrCore.utils.Utils;

public class SetSlotsCommand implements CommandExecutor
{
	private final MessagesManager MM = MessagesManager.getInstance();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!cs.hasPermission("core.setslots")) {
			Utils.sendMsg(cs, MM.NO_PERMISSION.replace("%permission", "core.setslots"));
			return true;
		}
		if (args.length == 0) {
			Utils.sendMsg(cs, MM.USAGE.replace("%use", "/setslots <ilosc>"));
			return true;
		}
		if (!Utils.isInteger(args[0])) {

			return true;
		}
		int slots = Integer.parseInt(args[0]);
		CorePlugin.getInstance().getConfig().set("config.slots", slots);
		CorePlugin.getInstance().saveConfig();
		Utils.sendMsg(cs,
				MessagesManager.getInstance().SLOTS_CHANGED.replace("%lastSlots", Integer.toString(Settings.getInstance().slots)).replace("%newSlots", Integer.toString(slots)));
		Settings.getInstance().loadConfiguration();
		return false;
	}
}
