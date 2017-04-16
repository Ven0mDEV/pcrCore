package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.brightblack9911.pcrCore.managers.ChatManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class ChatCommand implements CommandExecutor
{
	private final ChatManager CM = ChatManager.getInstance();
	private final MessagesManager MM = MessagesManager.getInstance();

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length != 1) {
			Utils.sendMsg(cs, MM.USAGE.replace("%use", "/chat <clear, on, off>"));
			return true;
		}
		switch (args[0]) {
			case "clear":
				if (!cs.hasPermission("core.chat.clear")) {
					Utils.sendMsg(cs, MM.NO_PERMISSION.replace("%permission", "core.chat.clear"));
					return true;
				}
				CM.clearChat(cs);
				return true;
			case "on":
				if (!cs.hasPermission("core.chat.on")) {
					Utils.sendMsg(cs, MM.NO_PERMISSION.replace("%permission", "core.chat.on"));
					return true;
				}
				if (CM.getChatStatus()) {
					Utils.sendMsg(cs, MM.CHAT_ALREDY_ON);
					return true;
				}
				CM.setChatStatus(true);
				return true;
			case "off":
				if (!cs.hasPermission("core.chat.off")) {
					Utils.sendMsg(cs, MM.NO_PERMISSION.replace("%permission", "core.chat.off"));
					return true;
				}
				if (!CM.getChatStatus()) {
					Utils.sendMsg(cs, MM.CHAT_ALREDY_OFF);
					return true;
				}
				CM.setChatStatus(false);
				return true;
			default:
				Utils.sendMsg(cs, MM.USAGE.replace("%use", "/chat <clear, on, off>"));

		}
		return false;
	}

}
