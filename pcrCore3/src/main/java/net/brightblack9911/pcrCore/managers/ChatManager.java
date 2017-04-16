package net.brightblack9911.pcrCore.managers;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.utils.Utils;

public class ChatManager
{
	private static ChatManager instance;

	private boolean chat = true;

	public boolean getChatStatus() {
		return chat;
	}

	public void setChatStatus(boolean b) {
		chat = b;
	}

	public void clearChat(CommandSender cs) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("core.clear.bypass")) continue;
			for (int i = 0; i < 1000; i++) {
				Utils.sendMsg(p, "");
			}
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			Utils.sendMsg(p, "               &8&lPRO&7CRAFTERS.pl          ");
			Utils.sendMsg(p, "       &8● &7Chat zostal &6&lWYCZYSZCZONY&7!");
			Utils.sendMsg(p, "       &8● &ePrzez: &c" + cs.getName());
			Utils.sendMsg(p, "");
		}
	}

	public void onChat(CommandSender cs) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Utils.sendMsg(p, "");
			Utils.sendMsg(p, "               &8&lPRO&7CRAFTERS.pl          ");
			Utils.sendMsg(p, "       &8● &7Chat zostal &a&lWLACZONY&7!");
			Utils.sendMsg(p, "       &8● &ePrzez: &c" + cs.getName());
			Utils.sendMsg(p, "");
		}
	}

	public void offChat(CommandSender cs) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Utils.sendMsg(p, "");
			Utils.sendMsg(p, "               &8&lPRO&7CRAFTERS.pl          ");
			Utils.sendMsg(p, "       &8● &7Chat zostal &c&lWYLACZONY&7!");
			Utils.sendMsg(p, "       &8● &ePrzez: &c" + cs.getName());
			Utils.sendMsg(p, "");
		}
	}

	public static ChatManager getInstance() {
		if (instance == null) instance = new ChatManager();
		return instance;
	}
}
