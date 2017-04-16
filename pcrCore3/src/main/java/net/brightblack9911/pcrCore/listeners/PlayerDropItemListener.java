package net.brightblack9911.pcrCore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import net.brightblack9911.pcrCore.enums.InventoryCat;

public class PlayerDropItemListener implements Listener
{
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent ev) {
		if (ev.getPlayer().getOpenInventory().getTitle().equals(InventoryCat.EXCHANGECOIN_GUI.getName())) {
			ev.setCancelled(true);
		}
	}
}
