package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener
{
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent ev) {
		ev.getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
	}
}
