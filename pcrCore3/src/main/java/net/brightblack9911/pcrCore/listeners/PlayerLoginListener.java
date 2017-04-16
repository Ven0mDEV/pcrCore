package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import net.brightblack9911.pcrCore.commands.SafeCommand;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.Settings;
import net.brightblack9911.pcrCore.utils.Utils;

public class PlayerLoginListener implements Listener
{
	private final MessagesManager MM = MessagesManager.getInstance();

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent ev) {
		if (ev.getResult() == Result.KICK_WHITELIST) {
			ev.setKickMessage(Utils.formatColor(MM.whitelistMsg));
		}

		if (ev.getResult() == Result.KICK_FULL) {
			ev.allow();
		}
		if (Bukkit.getOnlinePlayers().size() >= Settings.getInstance().slots && !ev.getPlayer().hasPermission("core.full.bypass")) {
			ev.disallow(Result.KICK_OTHER, Utils.formatColor("&cWpuszczamy graczy partiami!"));
		}
		
		if (Bukkit.getPlayer(ev.getPlayer().getName()) != null) {
			ev.disallow(Result.KICK_OTHER, Utils.formatColor("&cERROR - Ktos juz gra na tym nicku!"));
		}
		
		if (SafeCommand.players.containsKey(ev.getPlayer().getName())) {
			if (!SafeCommand.players.get(ev.getPlayer()).equals(ev.getAddress())) {
				ev.disallow(Result.KICK_OTHER, Utils.formatColor("&cblad"));
			}
		}

		// if (!ev.getPlayer().hasPlayedBefore()) {
		// String[] lines = new String[3];
		// lines[0] = "&8####### &f&o&lANTY-BOT &8#######";
		// lines[1] = " &7Wejdz ponownie na serwer.";
		// lines[2] = "&8########################";
		// String text = StringUtils.join(lines, "\n");
		// ev.disallow(Result.KICK_OTHER, Utils.formatColor(text));
		// }
	}
}
