package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class PlayerCommandPreprocessListener implements Listener
{
	private final String[] CRASH_COMMANDS = { "calc", "eval", "solve", "worldedit:/calc", "worldedit:/eval", "worldedit:/solve" };

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent ev) {
		Player p = ev.getPlayer();
		String msg = ev.getMessage().split(" ")[0].toLowerCase();
		final HelpTopic HP = Bukkit.getHelpMap().getHelpTopic(msg);
		if (HP == null) {
			for (String s : MessagesManager.getInstance().helpMsg) {
				Utils.sendMsg(p, s);
			}
			ev.setCancelled(true);
			return;
		}

		for (String str : CRASH_COMMANDS) {
			if (msg.startsWith("/" + str) || msg.endsWith("//" + str)) {
				ev.setCancelled(true);
				return;
			}
		}
		
	}
}
