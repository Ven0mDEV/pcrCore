/**
 * 
 */
package net.codesquad.backpack.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.codesquad.backpack.managers.BackPackManager;

/** @author nano3. */
public class PlayerInteractListener implements Listener
{
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getType() != Material.AIR) {
				if (BackPackManager.getInstance().getBackPackItem().equalsName(player.getInventory().getItemInMainHand())) {
					player.openInventory(BackPackManager.getInstance().getBackPackByItemStack(player.getInventory().getItemInMainHand()));
					player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_CHEST_LOCKED, 1f, 1f);
				}
			}

		}
	}
}
