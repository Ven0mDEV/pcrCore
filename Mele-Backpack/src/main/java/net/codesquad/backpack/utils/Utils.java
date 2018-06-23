package net.codesquad.backpack.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils
{
	public static String formatColor(String text) {
		if (text == null) return "";
		return ChatColor.translateAlternateColorCodes('&', text);
	} 
	
	public static boolean sendMsg(CommandSender commandSender, String text) {
		if (!(commandSender instanceof Player)) {
			ChatColor.stripColor(formatColor(text));
		}
		
		if ((text != null) || (text != "")) commandSender.sendMessage(formatColor(text));
		return true;
	}

}
