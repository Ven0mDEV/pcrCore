/**
 * 
 */
package net.codesquad.backpack.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import net.codesquad.backpack.BackPack;
import net.codesquad.backpack.managers.BackPackManager;

/** @author nano3. */
public class InventoryCloseListener implements Listener
{
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		BackPack backpack = BackPackManager.getInstance().getBackPackByInventory(event.getInventory());
		if (backpack != null) backpack.save();
	}
}
