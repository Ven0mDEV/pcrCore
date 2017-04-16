package net.brightblack9911.pcrCore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.brightblack9911.pcrCore.managers.Settings;
import net.brightblack9911.pcrCore.utils.Utils;

public class ServerListPingListener implements Listener
{
	@EventHandler
	public void onSeverListPing(ServerListPingEvent ev) {
		ev.setMaxPlayers(Settings.getInstance().slots);
		ev.setMotd(Utils.formatColor(Settings.getInstance().motd));
	}
}
