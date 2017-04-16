package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import net.brightblack9911.pcrCore.managers.ItemManager;

public class PlayerItemConsumeListener implements Listener
{
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent ev) {
		if (ev.getItem().getType() == Material.COOKED_BEEF) {
			if (ev.getItem().hasItemMeta()) {
				if (ev.getItem().getItemMeta().getDisplayName().equals(ItemManager.getInstance().steak.getItemMeta().getDisplayName())) {
					ev.getPlayer().getInventory().addItem(ItemManager.getInstance().steak);
				}
			}
		}
	}
}
