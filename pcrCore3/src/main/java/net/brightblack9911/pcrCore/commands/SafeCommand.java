package net.brightblack9911.pcrCore.commands;

import java.util.Map;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.minecraft.util.com.google.common.collect.Maps;

public class SafeCommand implements CommandExecutor
{
	public static final Map<String, String> players = Maps.newHashMap();
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!cs.hasPermission("safe.add")) {
			return true;
		}
		if (args.length >= 2) {
			players.put(args[0], args[1]);
		}
		return false;
	}
}