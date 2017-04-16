package net.brightblack9911.pcrCore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class PlayerQuitListener implements Listener
{
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent ev) {
		ev.setQuitMessage(Utils.formatColor(MessagesManager.getInstance().quitMsg));
		
		UserManager.getInstance().clearMemory(ev.getPlayer());
	}
}
